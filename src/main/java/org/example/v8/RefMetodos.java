package org.example.v8;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class RefMetodos {
    public static void main(String[] args) {
        // 1️⃣ Método de un objeto específico
        String saludo = "hola";
        Supplier<String> f1 = saludo::toUpperCase; // referencia
        Supplier<String> f1Lambda = () -> saludo.toUpperCase(); // equivalente
        System.out.println(f1.get()); // HOLA

        // 2️⃣ Método de instancia de un argumento
        List<String> lista = Arrays.asList("a", "bb", "ccc");
        lista.stream().map(String::length).forEach(System.out::println); // referencia
        // equivalente: lista.stream().map(s -> s.length()).forEach(x ->
        // System.out.println(x));

        // 3️⃣ Método estático
        Function<String, Integer> f3 = Integer::parseInt; // referencia
        Function<String, Integer> f3Lambda = s -> Integer.parseInt(s); // equivalente
        System.out.println(f3.apply("123") + 1); // 124

        // 4️⃣ Referencia a constructor
        Supplier<List<String>> f4 = ArrayList::new; // referencia
        Supplier<List<String>> f4Lambda = () -> new ArrayList<>(); // equivalente
        List<String> nueva = f4.get();
        nueva.add("ok");
        System.out.println(nueva);
    }
}
