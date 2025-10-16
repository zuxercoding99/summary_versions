package org.example.v1_0.poo;

// 💡 Regla general:
// - Usa **sobrecarga** cuando varios métodos hacen lo mismo con distintos parámetros.
// - Usa **sobrescritura** cuando querés comportamientos distintos según el tipo real del objeto.

class Calculadora {

    // 🔹 SOBRECARGA → mismo método, distintos parámetros
    int sumar(int a, int b) {
        return a + b;
    }

    double sumar(double a, double b) {
        return a + b;
    }

    // Todas las versiones hacen "la misma acción": sumar.
    // La elección se hace en TIEMPO DE COMPILACIÓN.
}

class Animal {
    void hablar() {
        System.out.println("Sonido genérico de animal");
    }
}

class Perro extends Animal {
    // 🔹 SOBRESCRITURA → redefine el comportamiento del método heredado
    @Override
    void hablar() {
        System.out.println("Guau guau!");
    }
}

class Gato extends Animal {
    @Override
    void hablar() {
        System.out.println("Miau!");
    }
}

public class SobreescrituraVsSobrecarga {
    public static void main(String[] args) {

        // ----- Ejemplo de SOBRECARGA -----
        Calculadora calc = new Calculadora();
        System.out.println(calc.sumar(2, 3)); // → 5 (usa int)
        System.out.println(calc.sumar(2.5, 3.1)); // → 5.6 (usa double)

        // ----- Ejemplo de SOBRESCRITURA -----
        Animal a1 = new Animal();
        Animal a2 = new Perro();
        Animal a3 = new Gato();

        a1.hablar(); // → Sonido genérico de animal
        a2.hablar(); // → Guau guau! (Perro)
        a3.hablar(); // → Miau! (Gato)

        // 🔸 Todas las variables son del tipo declarado Animal,
        // pero el método que se ejecuta depende del tipo REAL en tiempo de ejecución.
    }
}

/*
 * 📘 Resumen conceptual:
 * 
 * ✅ SOBRECARGA (overloading)
 * - Mismo nombre, distintos parámetros.
 * - Se decide en COMPILACIÓN.
 * - Se usa para tareas similares con distintos tipos o cantidades de datos.
 * 
 * ✅ SOBRESCRITURA (overriding)
 * - Mismo nombre, mismos parámetros, redefinido en una subclase.
 * - Se decide en EJECUCIÓN (polimorfismo).
 * - Se usa para comportamientos distintos según el tipo real del objeto.
 * 
 */
