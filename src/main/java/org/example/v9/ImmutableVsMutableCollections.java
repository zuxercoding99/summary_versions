package org.example.v9;

import java.util.*;
import java.util.stream.*;

public class ImmutableVsMutableCollections {
    public static void main(String[] args) {

        // ========================
        // Java 8 (2014)
        // ========================

        // ✅ Mutables (por defecto con Collectors.toXxx)
        List<String> list8 = Stream.of("A", "B", "C")
                .collect(Collectors.toList()); // -> ArrayList
        Set<String> set8 = Stream.of("A", "B", "C")
                .collect(Collectors.toSet()); // -> HashSet
        Map<Integer, String> map8 = Stream.of("A", "BB", "CCC")
                .collect(Collectors.toMap(String::length, s -> s)); // -> HashMap
        // Colisiones: si dos claves son iguales → lanza IllegalStateException.
        // Implementación de mapa: HashMap por defecto.

        // ✅ Mutable eligiendo implementación
        List<String> linkedList = Stream.of("A", "B", "C")
                .collect(Collectors.toCollection(LinkedList::new));
        Set<String> treeSet = Stream.of("C", "A", "B")
                .collect(Collectors.toCollection(TreeSet::new));
        Map<Integer, String> linkedHashMap = Stream.of("AA", "BBB", "C")
                .collect(Collectors.toMap(
                        String::length,
                        s -> s,
                        (v1, v2) -> v2, // manejo de colisiones
                        LinkedHashMap::new // implementación elegida
                ));

        // ❌ No había soporte directo para inmutables
        List<String> list8Imm = Collections.unmodifiableList(list8);
        Set<String> set8Imm = Collections.unmodifiableSet(set8);
        Map<Integer, String> map8Imm = Collections.unmodifiableMap(map8);

        // ========================
        // Java 9 (2017)
        // ========================

        // ✅ Inmutables directos
        List<String> list9Imm = List.of("A", "B", "C");
        Set<String> set9Imm = Set.of("A", "B", "C");
        Map<Integer, String> map9Imm = Map.of(1, "A", 2, "BB", 3, "CCC");

        // ↔ Equivalentes mutables
        List<String> list9 = new ArrayList<>(List.of("A", "B", "C"));
        Set<String> set9 = new HashSet<>(Set.of("A", "B", "C"));
        Map<Integer, String> map9 = new HashMap<>(Map.of(1, "A", 2, "BB", 3, "CCC"));

        // ========================
        // Java 10 (2018)
        // ========================

        // ✅ Inmutables desde Streams
        List<String> list10Imm = Stream.of("A", "B", "C")
                .collect(Collectors.toUnmodifiableList());
        Set<String> set10Imm = Stream.of("A", "B", "C")
                .collect(Collectors.toUnmodifiableSet());
        Map<Integer, String> map10Imm = Stream.of("A", "BB", "CCC")
                .collect(Collectors.toUnmodifiableMap(String::length, s -> s));

        // ↔ Equivalentes mutables
        List<String> list10 = Stream.of("A", "B", "C")
                .collect(Collectors.toList());
        Set<String> set10 = Stream.of("A", "B", "C")
                .collect(Collectors.toSet());
        Map<Integer, String> map10 = Stream.of("A", "BB", "CCC")
                .collect(Collectors.toMap(String::length, s -> s));

        // ========================
        // Java 16 (2021)
        // ========================

        // ✅ Nuevo método terminal inmutable en Stream
        List<String> list16Imm = Stream.of("A", "B", "C").toList();

        // ↔ Equivalente mutable
        List<String> list16 = new ArrayList<>(Stream.of("A", "B", "C").toList());

        // ❌ Stream.toSet() y Stream.toMap() NO existen en Java 16
        // Para Set y Map se sigue usando Collectors
    }
}
