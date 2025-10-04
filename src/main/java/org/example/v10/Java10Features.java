package org.example.v10;

import java.util.*;
import java.util.stream.*;

public class Java10Features {
    public static void main(String[] args) {
        var texto = "Hola"; // String
        var numero = 42; // int
        var lista = List.of("A", "B", "C"); // List<String> inmutable
        var mapa = new HashMap<String, Integer>(); // HashMap mutable

        var nombres = List.of("Ana", "Juan", "Luis");
        var mayus = nombres.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toUnmodifiableList()); // List<String> inmutable

        System.out.println(texto + ", " + numero);
        System.out.println("Lista: " + lista);
        System.out.println("Mapa: " + mapa);
        System.out.println("Mayúsculas: " + mayus);
    }
}
