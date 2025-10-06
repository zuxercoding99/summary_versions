package org.example.v12;

public class Java12Switch {
    public static void main(String[] args) {

        // ========================
        // Switch expression simple
        // ========================
        int x = 2;
        String resultado = switch (x) {
            case 1 -> "uno";
            case 2 -> "dos";
            default -> "otro";
        };
        System.out.println(resultado); // dos

        // ========================
        // Múltiples casos
        // ========================
        String dia = "MONDAY";
        String mensaje = switch (dia) {
            case "MONDAY", "FRIDAY" -> "Inicio o fin de semana";
            case "SATURDAY", "SUNDAY" -> "Fin de semana";
            default -> "Día normal";
        };
        System.out.println(mensaje); // Inicio o fin de semana

        // ========================
        // Bloque con yield para varias líneas
        // ========================
        int edad = 4;
        String tipo = switch (edad) {
            case 0, 1, 2 -> "Bebé";
            case 3, 4, 5 -> {
                System.out.println("Preescolar");
                yield "Niño";
            }
            default -> "Otro";
        };
        System.out.println(tipo); // Niño

        // ========================
        // Switch clásico (solo ejecutar código)
        // ========================
        switch (x) {
            case 1 -> System.out.println("uno clásico");
            case 2 -> System.out.println("dos clásico");
            default -> System.out.println("otro clásico");
        }

    }
}
