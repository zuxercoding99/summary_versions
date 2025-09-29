package org.example.v7;

import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.apache.tika.Tika;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/files")
public class FileController {

    private final Path root = Paths.get("uploads");

    public FileController() throws IOException {
        Files.createDirectories(root); // crea carpeta si no existe
    }

    // ---- Subir archivo ----
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) throws IOException {
        // 1️⃣ Validar tamaño y vacío
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("Archivo vacío");
        if (file.getSize() > 5_000_000)
            return ResponseEntity.badRequest().body("Archivo demasiado grande"); // 5MB

        // 2️⃣ Detectar tipo real y obtener extensión segura
        Map<String, String> allowedMime = Map.of(
                "image/png", ".png",
                "image/jpeg", ".jpg");

        String detectedType;
        try (InputStream is = file.getInputStream()) {
            detectedType = new Tika().detect(is);
        }

        String extension = allowedMime.get(detectedType);
        if (extension == null)
            return ResponseEntity.badRequest().body("Solo se permiten PNG o JPEG");

        // 3️⃣ Nombre seguro con UUID
        String filename = UUID.randomUUID() + extension;
        Path dest = root.resolve(filename);

        // 4️⃣ Guardar archivo (binario)
        try (InputStream in = file.getInputStream()) {
            Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
        }

        return ResponseEntity.ok("Subido: " + filename);
    }

    // ---- Descargar archivo (fuerza descarga) ----
    @GetMapping("/download/{name}")
    public ResponseEntity<Resource> download(@PathVariable String name) throws IOException {
        Path file = root.resolve(name);
        if (!Files.exists(file))
            return ResponseEntity.notFound().build();

        String mimeType = Files.probeContentType(file);
        if (mimeType == null)
            mimeType = "application/octet-stream";

        Resource resource = new InputStreamResource(Files.newInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(mimeType))
                .body(resource);
    }

    // ---- Ver archivo en navegador (inline) ----
    @GetMapping("/view/{name}")
    public ResponseEntity<Resource> view(@PathVariable String name) throws IOException {
        Path file = root.resolve(name);
        if (!Files.exists(file))
            return ResponseEntity.notFound().build();

        String mimeType = Files.probeContentType(file);
        if (mimeType == null)
            mimeType = "application/octet-stream";

        Resource resource = new InputStreamResource(Files.newInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(mimeType))
                .body(resource);
    }

    // ---- Eliminar archivo ----
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) throws IOException {
        return Files.deleteIfExists(root.resolve(name))
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
