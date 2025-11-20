package org.example.v1_0.poo;

/**
 * Archivo compacto que resume las reglas principales de Clean Code.
 * Cada regla incluye una explicación breve y un ejemplo mínimo.
 */
public class CleanCodeSummary {

    // =============================================================
    // 1. Nombres claros (Reveal Intent)
    // =============================================================
    // Los nombres deben explicar qué hacen y por qué existen.
    int calculateTotal() {
        return 0;
    } // ✔

    int calc() {
        return 0;
    } // ❌

    // =============================================================
    // 2. Métodos cortos (Hacen una sola cosa)
    // =============================================================
    // Ideal: 5–15 líneas. Si necesita comentarios para entenderse → dividir.
    void processUser() {
        validateUser();
        saveUser();
        notifyUser();
    }

    // =============================================================
    // 3. Clases pequeñas (Una sola responsabilidad)
    // =============================================================
    // 100–200 líneas aprox. Si hay demasiada lógica → dividir.
    static class OrderValidator {
    }

    static class OrderRepository {
    }

    static class OrderNotifier {
    }

    // =============================================================
    // 4. Mantener dependencias al mínimo
    // =============================================================
    // Menos dependencias = menos acoplamiento.
    static class ReportService {
        private final DataSource source; // ✔ solo lo necesario

        ReportService(DataSource source) {
            this.source = source;
        }
    }

    interface DataSource {
    }

    // =============================================================
    // 5. Evitar God Classes
    // =============================================================
    // Grandes, hacen de todo, conocen todo.
    // Solución: SRP + composición.
    static class PaymentProcessor {
        /* dividir en servicios */ }

    // =============================================================
    // 6. Evitar números mágicos
    // =============================================================
    static final int MAX_SPEED = 120; // ✔

    void checkSpeed(int speed) {
        if (speed > MAX_SPEED) {
        } // ✔
    }

    // =============================================================
    // 7. DRY – No duplicar lógica
    // =============================================================
    int discount(int price) {
        return price - calculateFee(price);
    }

    int tax(int price) {
        return price - calculateFee(price);
    } // ❌ duplicado

    int calculateFee(int price) {
        return price * 5 / 100;
    } // ✔ reutilizar

    // =============================================================
    // 8. KISS – Simple > complejo
    // =============================================================
    // Evitar sobreingeniería.
    boolean isEven(int n) {
        return n % 2 == 0;
    } // ✔ simple

    // =============================================================
    // 9. Evitar if anidados (Early Return)
    // =============================================================
    void doWork(boolean a, boolean b) {
        if (!a)
            return;
        if (!b)
            return;
        // código final simple
    }

    // =============================================================
    // 10. Tell, Don’t Ask
    // =============================================================
    // No pedir datos para decidir afuera. Encapsular comportamiento.
    static class Order {
        private String status;

        boolean isPaid() {
            return "PAID".equals(status);
        }

        void refundIfPaid() { // ✔ lógica interna
            if (isPaid()) {
                /* refund */ }
        }
    }

    // =============================================================
    // 11. Composición sobre herencia
    // =============================================================
    static class Engine {
        void start() {
        }
    }

    static class Car {
        private final Engine engine; // ✔ composición

        Car(Engine e) {
            this.engine = e;
        }

        void start() {
            engine.start();
        }
    }

    // =============================================================
    // 12. Evitar parámetros booleanos
    // =============================================================
    void printReport(boolean detailed) {
    } // ❌ hace 2 cosas

    void printSummaryReport() {
    } // ✔

    void printDetailedReport() {
    } // ✔

    // =============================================================
    // 13. Evitar muchos parámetros (preferir 0–2)
    // =============================================================
    static class UserData {
        String name;
        int age;
    } // ✔ Parameter Object

    void register(UserData data) {
    } // ✔

    // =============================================================
    // 14. Eliminar código muerto
    // =============================================================
    void unused() {
    } // ❌ eliminar

    // =============================================================
    // 15. Formato consistente
    // =============================================================
    // Sangría, espacios y estilo uniforme. Ejemplo simple:
    void goodFormat() {
        int x = 1;
        if (x > 0) {
            System.out.println(x);
        }
    }

    // =============================================================
    // 16. Preferir inmutabilidad
    // =============================================================
    // Reducir errores: final variables > mutables.
    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // =============================================================
    // 17. Command–Query Separation (CQS)
    // =============================================================
    // Un método debe o modificar (command) o devolver datos (query), pero no ambos.
    int currentBalance() {
        return 0;
    } // query

    void withdraw(int amount) {
    } // command
}
