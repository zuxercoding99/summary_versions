package org.example.v7;

/* 
   Ejemplos de Java 7:
   1) Try-with-resources
   2) Switch con String
   3) NIO.2 (archivos y directorios)
*/

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Java7Features {

    // ---------- 1. Try-with-resources ----------
    static void ejemploTryWithResources() throws IOException {
        // Cierra automáticamente BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader("archivo.txt"))) {
            System.out.println(br.readLine());
        } // br.close() se llama automáticamente

    }

    // ---------- 2. Switch con String ----------
    static void ejemploSwitchString(String color) {
        switch (color) {
            case "rojo":
                System.out.println("Parar");
                break;
            case "verde":
                System.out.println("Avanzar");
                break;
            case "amarillo":
                System.out.println("Precaución");
                break;
            default:
                System.out.println("Color no reconocido");
        }
    }

    // ---------- 3. NIO.2 (archivos y directorios) ----------
    static void ejemploNIO2() throws IOException {
        Path file = Paths.get("archivo.txt");

        // --- Lectura de archivo ---
        if (Files.exists(file)) {
            // 1️⃣ Leer todas las líneas como lista
            Files.readAllLines(file).forEach(System.out::println);

            // 2️⃣ Leer todo el contenido como String (útil para texto pequeño)
            String contenido = Files.readString(file);
            System.out.println("Contenido completo: " + contenido);

            // 3️⃣ Leer bytes (para binarios o imágenes)
            byte[] bytes = Files.readAllBytes(file);
            System.out.println("Cantidad de bytes: " + bytes.length);
        }

        // --- Escritura ---
        Files.writeString(file, "Hola Mundo"); // texto
        Files.write(file, "Bytes".getBytes()); // binario

        // --- Directorios ---
        Path dir = Paths.get("miCarpeta");
        if (!Files.exists(dir))
            Files.createDirectory(dir);

        // Listar archivos
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path p : stream)
                System.out.println(p);
        }

        // --- Copiar / Mover / Eliminar ---
        Path copy = Paths.get("archivo_copia.txt");
        Files.copy(file, copy, StandardCopyOption.REPLACE_EXISTING);
        Files.move(copy, Paths.get("archivo_renombrado.txt"), StandardCopyOption.REPLACE_EXISTING);
        Files.deleteIfExists(Paths.get("archivo_renombrado.txt"));

    }

    public static void main(String[] args) throws IOException {
        // Try-with-resources
        ejemploTryWithResources();

        // Switch con String
        ejemploSwitchString("rojo");
        ejemploSwitchString("verde");
        ejemploSwitchString("azul");

        // NIO.2
        ejemploNIO2();
    }
}