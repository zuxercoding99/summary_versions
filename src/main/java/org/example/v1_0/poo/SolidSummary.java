package org.example.v1_0.poo;

/**
 * Archivo compacto para estudiar SOLID.
 * Explica cada principio con un ejemplo mínimo y correcto.
 */
public class SolidSummary {

    // ======================= SRP =======================
    /**
     * SRP: Una clase debe tener una sola responsabilidad.
     * Ejemplo: separar lógica de negocio y persistencia.
     */
    static class OrderService { // negocio
        void processOrder() {
            /* lógica */ }
    }

    static class OrderRepository { // persistencia
        void save() {
            /* guardar en DB */ }
    }

    // ======================= OCP =======================
    /**
     * OCP: Abierto a extensión, cerrado a modificación.
     * Se logra con interfaces + polimorfismo.
     */
    interface Payment {
        void pay();
    }

    static class CardPayment implements Payment {
        public void pay() {
            /* tarjeta */ }
    }

    static class PaypalPayment implements Payment {
        public void pay() {
            /* paypal */ }
    }

    static class PaymentProcessor {
        void process(Payment method) {
            method.pay();
        }
    }

    // ======================= LSP =======================
    /**
     * LSP: Las subclases deben ser sustituibles por su clase base.
     * Solución típica: separar interfaces cuando no todas las subclases
     * pueden hacer lo mismo.
     */
    interface Bird {
    }

    interface FlyingBird extends Bird {
        void fly();
    }

    static class Sparrow implements FlyingBird {
        public void fly() {
        }
    }

    static class Penguin implements Bird {
    }

    // ======================= ISP =======================
    /**
     * ISP: Interfaces pequeñas y específicas.
     * Evita obligar a implementar métodos que no se usan.
     */
    interface Workable {
        void work();
    }

    interface Eatable {
        void eat();
    }

    static class Robot implements Workable {
        public void work() {
        }
    }

    // ======================= DIP =======================
    /**
     * DIP: Las clases deben depender de abstracciones, no de implementaciones
     * concretas.
     * Se aplica usando interfaces + inyección de dependencias.
     */
    interface MessageSender {
        void send(String msg);
    }

    static class EmailSender implements MessageSender {
        public void send(String msg) {
            /* enviar email */ }
    }

    static class NotificationService {
        private final MessageSender sender;

        // DI por constructor
        NotificationService(MessageSender sender) {
            this.sender = sender;
        }

        void notify(String msg) {
            sender.send(msg);
        }
    }
}
