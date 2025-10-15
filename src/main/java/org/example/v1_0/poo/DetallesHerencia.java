package org.example.v1_0.poo;

public class DetallesHerencia {

    public static void main(String[] args) {

        System.out.println("=== Campos no son polimórficos ===");
        A1 obj1 = new B1();
        System.out.println(obj1.x); // 1
        obj1.print();               // B

        System.out.println("\n=== 1️⃣ Campo con mismo nombre y método que lo usa ===");
        A2 obj2 = new B2();
        System.out.println(obj2.getX()); // 1

        System.out.println("\n=== 2️⃣ Si B sobrescribe getX() ===");
        A3 obj3 = new B3();
        System.out.println(obj3.getX()); // 2

        System.out.println("\n=== 3️⃣ Cuando el padre no tiene el campo ===");
        A4 obj4 = new B4();
        // System.out.println(obj4.x); // ❌ Error de compilación
        System.out.println(((B4) obj4).x); // ✅ 2

        System.out.println("\n=== 4️⃣ Acceso a ambos campos desde la subclase ===");
        new B5().show();

        System.out.println("\n=== 5️⃣ Métodos estáticos → tampoco son polimórficos ===");
        A6 obj6 = new B6();
        obj6.show(); // A
    }
}

// 🔹 Campos no son polimórficos
class A1 { int x = 1; void print() { System.out.println("A"); } }
class B1 extends A1 { int x = 2; void print() { System.out.println("B"); } }

// 1️⃣ Campo con mismo nombre y método que lo usa
class A2 { int x = 1; int getX() { return x; } }
class B2 extends A2 { int x = 2; }

// 2️⃣ Si B sobrescribe getX()
class A3 { int x = 1; int getX() { return x; } }
class B3 extends A3 { int x = 2; int getX() { return x; } }

// 3️⃣ Cuando el padre no tiene el campo
class A4 {}
class B4 extends A4 { int x = 2; }

// 4️⃣ Acceso a ambos campos desde la subclase
class A5 { int x = 1; }
class B5 extends A5 {
    int x = 2;
    void show() {
        System.out.println(this.x);  // 2
        System.out.println(super.x); // 1
    }
}

// 5️⃣ Métodos estáticos → tampoco son polimórficos
class A6 { static void show() { System.out.println("A"); } }
class B6 extends A6 { static void show() { System.out.println("B"); } }

