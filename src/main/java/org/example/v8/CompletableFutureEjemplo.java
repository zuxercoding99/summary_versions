package org.example.v8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureEjemplo {

    // ---------- 1. Métodos asincrónicos "manuales" ----------
    // Void asincrónico (fire & forget)
    public static void tareaAsyncVoid() {
        CompletableFuture.runAsync(() -> {
            sleep(500);
            System.out.println("Tarea runAsync en: " + Thread.currentThread().getName());
        });
    }

    // Devuelve valor asincrónico
    public static CompletableFuture<Integer> calcularAsync() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return 42;
        });
    }

    // ---------- 2. Encadenamientos ----------
    public static void main(String[] args) {
        // runAsync → no devuelve valor
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync inicial");
        }).thenRun(() -> System.out.println("Encadenado con thenRun (sin valor)"));

        // supplyAsync → devuelve valor
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 5)
                .thenApply(n -> n * 2) // transforma valor
                .thenApply(n -> n + 3); // se puede seguir transformando

        // consumir valor
        f2.thenAccept(n -> System.out.println("Resultado final: " + n));

        // método asincrónico de instancia
        calcularAsync().thenAccept(r -> System.out.println("Método async -> " + r));

        // bloquear solo si queremos ver resultados
        f1.join();
        f2.join();
    }

    private static void sleep(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}
