package org.example.v14;

import java.util.List;

public class RecordsCompleto {

    // ---------- 1️⃣ Record básico ----------
    record Persona(String nombre, int edad) {
    }

    // ---------- 2️⃣ Record con validación en el constructor ----------
    record Producto(String nombre, double precio) {
        public Producto {
            if (precio < 0)
                throw new IllegalArgumentException("Precio no puede ser negativo");
            if (nombre == null || nombre.isBlank())
                throw new IllegalArgumentException("Nombre obligatorio");
        }

        // Getter extra (opcional)
        public String nombreMayusculas() {
            return nombre.toUpperCase();
        }

        // Método adicional
        public double precioConIva() {
            return precio * 1.21;
        }

        // Método estático
        public static Producto crearGratis(String nombre) {
            return new Producto(nombre, 0.0);
        }
    }

    // ---------- 3️⃣ Record implementando interfaces ----------
    interface Describible {
        String descripcion();
    }

    record Libro(String titulo, String autor, int paginas) implements Describible {
        @Override
        public String descripcion() {
            return titulo + " de " + autor + ", " + paginas + " páginas";
        }
    }

    // ---------- 4️⃣ Record con lista o composición ----------
    record Pedido(int id, List<Producto> productos) {
        public double total() {
            return productos.stream().mapToDouble(Producto::precio).sum();
        }
    }

    public static void main(String[] args) {

        // 1️⃣ Uso de record simple
        Persona p = new Persona("Ana", 25);
        System.out.println("Persona: " + p); // toString()
        System.out.println("Nombre: " + p.nombre()); // getter
        System.out.println("Edad: " + p.edad());

        // 2️⃣ Uso de record con validación y métodos extra
        Producto lap = new Producto("Laptop", 1000);
        System.out.println("Producto: " + lap);
        System.out.println("Precio con IVA: " + lap.precioConIva());
        System.out.println("Nombre mayúsculas: " + lap.nombreMayusculas());

        Producto gratis = Producto.crearGratis("Muestra");
        System.out.println("Producto gratis: " + gratis);

        // 3️⃣ Record implementando interfaz
        Libro libro = new Libro("Java 14", "Juan", 350);
        System.out.println(libro.descripcion());

        // 4️⃣ Record con composición y lista
        Pedido pedido = new Pedido(1, List.of(
                new Producto("Mouse", 20),
                new Producto("Teclado", 50)));
        System.out.println("Total del pedido: " + pedido.total());

        // 5️⃣ Comparación de records
        Persona p2 = new Persona("Ana", 25);
        System.out.println("p.equals(p2)? " + p.equals(p2));
        System.out.println("p.hashCode() = " + p.hashCode());
    }
}
