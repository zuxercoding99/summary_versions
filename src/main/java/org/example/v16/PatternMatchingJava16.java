package org.example.v16;

import java.util.List;

public class PatternMatchingJava16 {

    sealed interface Shape permits Circle, Rectangle, Triangle {
    }

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

    // ---------- Uso de Pattern Matching ----------
    public static void main(String[] args) {
        List<Shape> figuras = List.of(
                new Circle(5),
                new Rectangle(4, 6),
                new EquilateralTriangle(3),
                new IsoscelesTriangle(4, 5));

        for (Shape f : figuras) {
            double area = 0;

            // ✅ Pattern matching con instanceof
            if (f instanceof Circle c)
                area = c.area();
            else if (f instanceof Rectangle r)
                area = r.area();
            else if (f instanceof Triangle t)
                area = t.area();

            System.out.println(f.getClass().getSimpleName() + " área: " + area);
        }

        // ✅ Uso con lógica adicional
        Object valor = "Hola Mundo";
        if (valor instanceof String s && !s.isEmpty()) {
            System.out.println("Texto: " + s.toUpperCase());
        }
    }
}
