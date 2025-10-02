package org.example.v8;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableAsync // habilita @Async
public class AsyncSpringBootEjemplo {
    public static void main(String[] args) {
        SpringApplication.run(AsyncSpringBootEjemplo.class, args);
    }
}

@Service
class MiServicioAsync {

    // 1️⃣ Método asincrónico void (fire & forget)
    @Async
    public void tareaAsyncVoid() {
        sleep(500);
        System.out.println("Tarea @Async void en: " + Thread.currentThread().getName());
    }

    // 2️⃣ Método asincrónico que devuelve valor
    @Async
    public CompletableFuture<Integer> calcularAsync() {
        sleep(500);
        int resultado = 42;
        return CompletableFuture.completedFuture(resultado);
    }

    private void sleep(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}
