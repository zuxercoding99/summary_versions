package org.example.v1_0.poo;

// -------------------- ENCAPSULAMIENTO --------------------
class CuentaBancaria {
    private double saldo; // datos ocultos

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0)
            this.saldo += cantidad; // uso de this
    }

    public void retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= this.saldo)
            this.saldo -= cantidad;
    }
}

// -------------------- ABSTRACCIÓN --------------------
abstract class Animal {
    protected String nombre;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    abstract void hacerSonido(); // método abstracto

    public void dormir() {
        System.out.println(nombre + " está durmiendo");
    }
}

// -------------------- HERENCIA y USO de super --------------------
class Perro extends Animal {
    public Perro(String nombre) {
        super(nombre); // llama al constructor de Animal
    }

    @Override
    void hacerSonido() {
        System.out.println(this.nombre + " dice: Guau!");
    }
}

class Gato extends Animal {
    public Gato(String nombre) {
        super(nombre);
    }

    @Override
    void hacerSonido() {
        System.out.println(this.nombre + " dice: Miau!");
    }
}

// -------------------- POLIMORFISMO --------------------
interface Pago {
    void procesar(double monto);
}

class PagoTarjeta implements Pago {
    public void procesar(double monto) {
        System.out.println("Procesando pago de $" + monto + " con Tarjeta de Crédito");
    }
}

class PagoPayPal implements Pago {
    public void procesar(double monto) {
        System.out.println("Procesando pago de $" + monto + " con PayPal");
    }
}

class Tienda {
    public void cobrar(Pago metodoPago, double monto) {
        metodoPago.procesar(monto); // polimorfismo en tiempo de ejecución
    }

    // Polimorfismo en tiempo de compilación (sobrecarga)
    public void cobrar(String metodo, double monto) {
        if (metodo.equals("tarjeta"))
            System.out.println("Procesando pago de $" + monto + " con Tarjeta (sobrecarga)");
        else if (metodo.equals("paypal"))
            System.out.println("Procesando pago de $" + monto + " con PayPal (sobrecarga)");
    }
}

// -------------------- SOBRECARGA --------------------
class Calculadora {
    int sumar(int a, int b) {
        return a + b;
    }

    double sumar(double a, double b) {
        return a + b;
    } // sobrecarga
}

// -------------------- INTERFAZ FUNCIONAL --------------------
@FunctionalInterface
interface Operacion {
    int ejecutar(int a, int b);

    default void mostrarTipo() {
        System.out.println("Operación binaria");
    }

    static void info() {
        System.out.println("Interfaz funcional para operaciones");
    }
}

// -------------------- USO de this y super --------------------
class Vehiculo {
    String tipo;

    public Vehiculo(String tipo) {
        this.tipo = tipo;
    }

    void mostrarTipo() {
        System.out.println("Vehículo: " + tipo);
    }
}

class Coche extends Vehiculo {
    int ruedas;

    public Coche(String tipo, int ruedas) {
        super(tipo); // llama al constructor de la superclase
        this.ruedas = ruedas;
    }

    void mostrar() {
        super.mostrarTipo(); // llama al método de la superclase
        System.out.println("Ruedas: " + this.ruedas);
    }
}

// -------------------- MAIN --------------------
public class PrincipiosPOO {
    public static void main(String[] args) {
        // Encapsulamiento
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.depositar(100);
        cuenta.retirar(40);
        System.out.println("Saldo: " + cuenta.getSaldo());

        // Abstracción + Herencia + Polimorfismo
        Animal perro = new Perro("Firulais");
        Animal gato = new Gato("Misu");
        perro.hacerSonido();
        gato.hacerSonido();
        perro.dormir();

        // Polimorfismo con interfaces
        Tienda tienda = new Tienda();
        tienda.cobrar(new PagoTarjeta(), 200);
        tienda.cobrar(new PagoPayPal(), 150);
        tienda.cobrar("tarjeta", 100); // sobrecarga

        // Sobrecarga en Calculadora
        Calculadora calc = new Calculadora();
        System.out.println("Suma int: " + calc.sumar(2, 3));
        System.out.println("Suma double: " + calc.sumar(2.5, 3.5));

        // Interfaz funcional con lambda
        Operacion suma = (a, b) -> a + b;
        System.out.println("Lambda suma: " + suma.ejecutar(5, 7));
        suma.mostrarTipo();
        Operacion.info();

        // Uso de this y super
        Coche coche = new Coche("Sedán", 4);
        coche.mostrar();
    }
}
