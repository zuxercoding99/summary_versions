package org.example.v1_0.poo;

class A {
    int x = 1;

    void show() {
        System.out.println("A.show: " + x);
    }
}

class B extends A {
    int x = 2;

    void show() {
        System.out.println("B.show: " + x);
    }

    void onlyInB() {
        System.out.println("Método solo en B");
    }
}

public class MoreInheritance {
    public static void main(String[] args) {
        A obj = new B(); // Referencia A, objeto real B
        System.out.println(obj.x); // → 1 (campo de A, compile-time)
        obj.show(); // → B.show: 2 (método de B, runtime)
        // obj.onlyInB(); // ❌ No compila (A no declara ese método)

        B real = (B) obj; // Cast para acceder a miembros de B
        System.out.println(real.x); // → 2 (campo de B)
        real.onlyInB(); // ✅ Ahora accesible
    }
}
