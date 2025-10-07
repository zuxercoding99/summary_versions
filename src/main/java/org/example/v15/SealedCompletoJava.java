package org.example.v15;

import java.util.List;

public class SealedCompletoJava {

    // ---------- 1️⃣ Interfaz sellada ----------
    sealed interface Shape permits Circle, Rectangle, Triangle {
    }

    // Subclases permitidas
    final static class Circle implements Shape {
        double radio;

        Circle(double r) {
            radio = r;
        }

        double area() {
            return Math.PI * radio * radio;
        }
    }

    non-sealed static class Rectangle implements Shape {
        double ancho, alto;

        Rectangle(double a, double h) {
            ancho = a;
            alto = h;
        }

        double area() {
            return ancho * alto;
        }
    }

    sealed static class Triangle implements Shape permits EquilateralTriangle, IsoscelesTriangle {
        double base, altura;

        Triangle(double b, double h) {
            base = b;
            altura = h;
        }

        double area() {
            return (base * altura) / 2;
        }
    }

    final static class EquilateralTriangle extends Triangle {
        EquilateralTriangle(double lado) {
            super(lado, Math.sqrt(3) / 2 * lado);
        }
    }

    non-sealed static class IsoscelesTriangle extends Triangle {
        IsoscelesTriangle(double base, double altura) {
            super(base, altura);
        }
    }

    // ---------- 2️⃣ Uso de la jerarquía ----------
    public static void main(String[] args) {
        Shape c = new Circle(5);
        Shape r = new Rectangle(4, 6);
        Shape t1 = new EquilateralTriangle(3);
        Shape t2 = new IsoscelesTriangle(4, 5);

        List<Shape> figuras = List.of(c, r, t1, t2);

        for (Shape f : figuras) {
            double area = 0;
            // Uso clásico de instanceof + cast, compatible hasta Java 17
            if (f instanceof Circle)
                area = ((Circle) f).area();
            else if (f instanceof Rectangle)
                area = ((Rectangle) f).area();
            else if (f instanceof Triangle)
                area = ((Triangle) f).area();

            System.out.println(f.getClass().getSimpleName() + " área: " + area);
        }
    }
}
