package org.example.v1_2;

import java.util.*;

public class DataStructuresCheatsheet {

    // --- LIST ---
    static void listExamples() {
        List<String> arrayList = new ArrayList<>(); // acceso O(1) por índice
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add(1, "X"); // insertar en índice
        arrayList.remove("B"); // eliminar por valor
        arrayList.remove(0); // eliminar por índice
        String val = arrayList.get(0);
        boolean exists = arrayList.contains("X");
        int size = arrayList.size();

        Collections.sort(arrayList); // ordenar
        Collections.reverse(arrayList); // invertir orden
        Collections.shuffle(arrayList); // mezclar aleatoriamente

        LinkedList<String> linkedList = new LinkedList<>(); // inserciones/eliminaciones rápidas en extremos
        linkedList.addFirst("first");
        linkedList.addLast("last");
        linkedList.poll(); // elimina cabeza
    }

    // --- SET ---
    static void setExamples() {
        Set<String> hashSet = new HashSet<>(); // no duplicados, O(1)
        hashSet.add("A");
        hashSet.add("B");
        hashSet.contains("A");
        hashSet.remove("B");

        Set<String> linkedHashSet = new LinkedHashSet<>(); // mantiene orden de inserción

        Set<String> treeSet = new TreeSet<>(); // orden natural, O(log n)
        treeSet.add("c");
        treeSet.add("a");
        String first = ((TreeSet<String>) treeSet).first();
        String last = ((TreeSet<String>) treeSet).last();
        ((TreeSet<String>) treeSet).headSet("c"); // elementos menores que "c"
    }

    // --- MAP ---
    static void mapExamples() {
        Map<String, Integer> hashMap = new HashMap<>(); // O(1)
        hashMap.put("uno", 1);
        hashMap.put("dos", 2);
        int v = hashMap.getOrDefault("tres", 0);
        boolean hasKey = hashMap.containsKey("uno");
        boolean hasVal = hashMap.containsValue(2);
        hashMap.remove("uno");

        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(); // mantiene orden de inserción

        Map<String, Integer> treeMap = new TreeMap<>(); // claves ordenadas
        ((TreeMap<String, Integer>) treeMap).firstKey();
        ((TreeMap<String, Integer>) treeMap).lastKey();
        ((TreeMap<String, Integer>) treeMap).headMap("clave");

        // Iterar
        for (Map.Entry<String, Integer> e : hashMap.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }

        // Actualizar valores
        hashMap.computeIfAbsent("x", k -> 0);
        hashMap.merge("dos", 5, Integer::sum);
    }

    public static void main(String[] args) {
        listExamples();
        setExamples();
        mapExamples();
    }
}
