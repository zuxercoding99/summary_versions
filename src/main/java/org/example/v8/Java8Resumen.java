package org.example.v8;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;

public class Java8Resumen {
    public static void main(String[] args) throws Exception {

        // ✅ LAMBDA = función sin nombre para interfaz funcional
        List<String> lista = List.of("A", "BB", "CCC");
        lista.forEach(s -> System.out.println(s)); // Consumer<String>

        // ✅ INTERFACES FUNCIONALES (Function, Predicate, Supplier, etc.)
        Function<String, Integer> f = s -> s.length();
        Predicate<String> p = s -> s.length() > 1;
        Supplier<Double> sup = Math::random;
        System.out.println(f.apply("Hola")); // 4
        System.out.println(p.test("A")); // false
        System.out.println(sup.get()); // num aleatorio

        // ✅ METHOD REFERENCES
        Consumer<String> imp = System.out::println;
        imp.accept("Hola con referencia");

        // ✅ STREAMS = pipelines funcionales sobre colecciones
        List<String> resultado = lista.stream()
                .filter(s -> s.length() > 1) // Predicate
                .map(String::toLowerCase) // Function
                .sorted() // Comparator
                .collect(Collectors.toList()); // Terminal
        System.out.println(resultado);

        // ✅ OPTIONAL = evitar null
        String posibleNull = "posible";
        String v1 = Optional.ofNullable(posibleNull) // valor opcional
                .filter(s -> s.length() > 2) // descarta si no cumple
                .map(String::toUpperCase) // transforma
                .orElseGet(() -> "DEFAULT"); // caso con valor por defecto

        String v2 = Optional.ofNullable(posibleNull)
                .map(String::trim)
                .orElseThrow(); // obligatorio, sin default

        /*
         * Usar siempre ofNullable (a menos que vos mismo generes el valor y garantices
         * no null).
         * 
         * Nunca .get() salvo en casos internos controlados. (IsPresent + get es un
         * antipatron).
         * 
         * Decidir entre orElseGet o orElseThrow según si podés continuar con un default
         * o no.
         * 
         * filter y map son como los de Stream, pero operan sobre 1 valor (presente o
         * ausente).
         */

        // ✅ JAVA.TIME = API moderna de fecha/hora (inmutable, segura)
        // 1️⃣ LocalDate y Period (solo partes)
        LocalDate nacimiento = LocalDate.of(1995, 5, 23);
        LocalDate hoy = LocalDate.now();
        Period edad = Period.between(nacimiento, hoy);
        System.out.printf("Edad: %d años, %d meses, %d días%n",
                edad.getYears(), edad.getMonths(), edad.getDays());

        // 2️⃣ LocalDateTime y Duration (totales + partes)
        LocalDateTime inicio = LocalDateTime.of(2025, 6, 26, 8, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2025, 6, 26, 10, 30, 45);
        Duration dur = Duration.between(inicio, fin);
        System.out.printf("Duración: %d horas, %d min, %d seg (total seg=%d)%n",
                dur.toHours(), dur.toMinutesPart(), dur.toSecondsPart(), dur.toSeconds());

        // 3️⃣ Instant (absoluto)
        Instant i1 = Instant.parse("2025-06-26T08:00:00Z");
        Instant i2 = Instant.parse("2025-06-26T10:30:45Z");
        System.out.println("i1 es antes que i2? " + i1.isBefore(i2));
        System.out.println("i2 es después de i1? " + i2.isAfter(i1));

        // Calculamos la duración del vuelo
        // Vuelo: sale de Buenos Aires (UTC-3) a las 08:00
        // Llega a Madrid (UTC+2) a las 22:30
        Instant salida = Instant.parse("2025-06-26T08:00:00-03:00");
        Instant llegada = Instant.parse("2025-06-26T22:30:00+02:00");

        Duration duracion = Duration.between(salida, llegada);
        System.out.printf("Duración del vuelo: %d horas, %d minutos%n",
                duracion.toHours(),
                duracion.toMinutesPart());

        // 4️⃣ Totales de días usando ChronoUnit
        long totalDias = ChronoUnit.DAYS.between(nacimiento, hoy);
        System.out.println("Total días desde nacimiento: " + totalDias);

        // ✅ CONCURRENCIA con CompletableFuture
        CompletableFuture.supplyAsync(() -> 5)
                .thenApply(n -> n * 2)
                .thenAccept(System.out::println) // imprime 10
                .join();

        // ✅ EXECUTOR SERVICE = pools de hilos
        ExecutorService exec = Executors.newFixedThreadPool(2);
        Future<Integer> fut = exec.submit(() -> 42);
        System.out.println("Future: " + fut.get());
        exec.shutdown();
    }
}
