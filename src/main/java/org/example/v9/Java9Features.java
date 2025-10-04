package org.example.v9;

import java.util.*;

public class Java9Features {
    public static void main(String[] args) {
        // 1️⃣ Colecciones inmutables
        List<String> lista = List.of("A", "B", "C");
        Set<Integer> set = Set.of(1, 2, 3);
        Map<String, Integer> mapa = Map.of("a", 1, "b", 2);

        System.out.println(lista); // [A, B, C]
        // lista.add("D"); // ❌ UnsupportedOperationException

        // Ventaja: colecciones seguras, inmutables, útiles en concurrencia
    }
}

interface Imprimible {

    // Métodos default que reutilizan el helper privado estático
    public default void encender() {
        formatear("encendiendo el dispositivo");
    }

    public default void apagar() {
        formatear("apagando el dispositivo");
    }

    // Método static de la interfaz que también reutiliza el helper
    public static void diagnosticar() {
        formatear("realizando diagnóstico...");
    }

    // 🔒 Método privado static: helper interno reutilizable
    private static void formatear(String mensaje) {
        System.out.println("**********");
        System.out.println(mensaje);
        System.out.println("**********");
    }

    // Método abstracto que deben implementar las clases
    public void imprimir();
}

class Impresora implements Imprimible {
    @Override
    public void imprimir() {
        System.out.println("Imprimiendo documento...");
    }

    public static void main(String[] args) {
        Imprimible impresora = new Impresora();

        impresora.encender();
        impresora.imprimir();
        impresora.apagar();

        // Llamada a método static de la interfaz
        Imprimible.diagnosticar();
    }
}
