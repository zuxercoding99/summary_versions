package org.example.v8;

import java.util.*;
import java.util.stream.*;

public class StreamCheatSheet {
    public static void main(String[] args) {
        List<String> lista = Arrays.asList("a", "bb", "ccc", "dd", "e");

        // ---------- Métodos intermedios (transforman el stream) ----------
        lista.stream()
                .filter(s -> s.length() > 1) // filtra → ["bb","ccc","dd"]
                .map(String::toUpperCase) // transforma → ["BB","CCC","DD"]
                .flatMap(s -> Arrays.stream(s.split(""))) // aplana → ["B","B","C","C","C","D","D"]
                .distinct() // únicos → ["B","C","D"]
                .sorted() // ordena natural → ["B","C","D"]
                .sorted(Comparator.reverseOrder()) // ordena custom → ["D","C","B"]
                .peek(x -> System.out.print(x + " ")) // inspección (debug)
                .limit(2) // toma 2 → ["D","C"]
                .skip(1) // salta 1 → ["C"]
                // ⚠️ Hasta aquí es LAZY (no ejecuta nada sin un terminal)

                // ---------- Métodos finales (consumen el stream) ----------
                .forEach(System.out::println); // acción terminal

        // Otros terminales:
        long count = lista.stream().count(); // cuenta → 5
        Optional<String> any = lista.stream().findAny(); // cualquiera
        Optional<String> first = lista.stream().findFirst(); // primero
        boolean hayLargos = lista.stream().anyMatch(s -> s.length() > 2); // true
        boolean todosLargos = lista.stream().allMatch(s -> s.length() > 0); // true
        boolean ningunoLargo = lista.stream().noneMatch(s -> s.length() > 10); // true
        Optional<String> max = lista.stream().max(Comparator.comparingInt(String::length)); // "ccc"
        Optional<String> min = lista.stream().min(Comparator.comparingInt(String::length)); // "a"
        int suma = lista.stream().mapToInt(String::length).sum(); // 9
        double promedio = lista.stream().mapToInt(String::length).average().orElse(0); // 1.8

        // Reducciones
        String concat = lista.stream().reduce("", (a, b) -> a + b); // concatena todo
        int suma2 = IntStream.range(1, 5).reduce(0, Integer::sum); // 1+2+3+4 = 10

        // Colecciones
        List<String> nuevaLista = lista.stream().collect(Collectors.toList());
        Set<String> nuevoSet = lista.stream().collect(Collectors.toSet());
        String join = lista.stream().collect(Collectors.joining(",")); // "a,bb,ccc,dd,e"
        Map<Integer, List<String>> porLongitud = lista.stream()
                .collect(Collectors.groupingBy(String::length));
        // {1=[a, e], 2=[bb, dd], 3=[ccc]}
    }
}
