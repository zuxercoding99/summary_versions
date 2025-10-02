package org.example.v8;

import java.util.concurrent.CompletableFuture;

public class ComparacionAsync {

    // Simula operación pesada
    private static int operacionPesada(String nombre, int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(nombre + " completada en " + Thread.currentThread().getName());
        return ms / 100; // devuelve un valor cualquiera
    }

    // 1️⃣ Caso 1: Secuencial (sin concurrencia)
    public static int metodoSecuencial() {
        int a = operacionPesada("Op1", 1000);
        int b = operacionPesada("Op2", 1000);
        return a + b;
    }

    // 2️⃣ Caso 2: Concurrente pero SINCRONO (usa join)
    public static int metodoConcurrenteSincrono() {
        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> operacionPesada("Op1", 1000));
        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> operacionPesada("Op2", 1000));
        return a.join() + b.join(); // espera resultados y combina
    }

    // 3️⃣ Caso 3: Concurrente ASINCRONO (devuelve CompletableFuture)
    public static CompletableFuture<Integer> metodoConcurrenteAsincrono() {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> operacionPesada("Op1", 1000));
            CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> operacionPesada("Op2", 1000));
            return a.join() + b.join();
        });
    }

    public static void main(String[] args) {
        long t1, t2;

        // Caso 1
        t1 = System.currentTimeMillis();
        int r1 = metodoSecuencial();
        t2 = System.currentTimeMillis();
        System.out.println("Resultado Caso 1: " + r1 + " en " + (t2 - t1) + "ms\n");

        // Caso 2
        t1 = System.currentTimeMillis();
        int r2 = metodoConcurrenteSincrono();
        t2 = System.currentTimeMillis();
        System.out.println("Resultado Caso 2: " + r2 + " en " + (t2 - t1) + "ms\n");

        // Caso 3
        t1 = System.currentTimeMillis();
        CompletableFuture<Integer> futuro = metodoConcurrenteAsincrono();
        futuro.thenAccept(r -> System.out.println("Resultado Caso 3 procesado: " + r));
        futuro.join(); // esperar para ver salida
        t2 = System.currentTimeMillis();
        System.out.println("Caso 3 completado en " + (t2 - t1) + "ms");
    }
}
