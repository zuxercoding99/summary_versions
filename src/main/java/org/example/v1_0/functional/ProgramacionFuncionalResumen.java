package org.example.v1_0.functional;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class ProgramacionFuncionalResumen {

    // ✅ Funcion pura de primer orden
    static int cuadrado(int x) {
        return x * x;
    }

    // ✅ Funcion pura de orden superior (recibe otra funcion)
    static int aplicarFuncion(int x, Function<Integer, Integer> f) {
        return f.apply(x);
    }

    public static void main(String[] args) {
        // ----------------------------
        // 🔹 INTERFACES FUNCIONALES
        // ----------------------------
        Function<Integer, Integer> doble = n -> n * 2;
        Predicate<Integer> esPar = n -> n % 2 == 0;
        Consumer<String> imprimir = System.out::println;
        Supplier<Double> aleatorio = Math::random;
        UnaryOperator<Integer> inc = n -> n + 1;
        BinaryOperator<Integer> suma = (a, b) -> a + b;

        imprimir.accept("Doble de 4 = " + doble.apply(4));
        imprimir.accept("¿4 es par? " + esPar.test(4));
        imprimir.accept("Número aleatorio = " + aleatorio.get());
        imprimir.accept("Incrementar 5 = " + inc.apply(5));
        imprimir.accept("Suma 3+7 = " + suma.apply(3, 7));

        // ----------------------------
        // 🔹 FUNCIONES ANÓNIMAS
        // ----------------------------
        // Antes de lambdas
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hola (clase anónima)");
            }
        };
        r1.run();

        // Lambda
        Runnable r2 = () -> System.out.println("Hola (lambda)");
        r2.run();

        // Referencia a método
        Consumer<String> c = System.out::println;
        c.accept("Hola (method reference)");

        // ----------------------------
        // 🔹 COMPOSICIÓN DE FUNCIONES
        // ----------------------------
        Function<Integer, Integer> cuadrado = x -> x * x;
        Function<Integer, Integer> dobleLuegoCuadrado = doble.andThen(cuadrado);
        Function<Integer, Integer> cuadradoLuegoDoble = doble.compose(cuadrado);

        imprimir.accept("Doble->Cuadrado de 3 = " + dobleLuegoCuadrado.apply(3)); // (3*2)^2
        imprimir.accept("Cuadrado->Doble de 3 = " + cuadradoLuegoDoble.apply(3)); // (3^2)*2

        // ----------------------------
        // 🔹 API STREAM
        // ----------------------------
        List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "Ana");

        List<String> resultado = nombres.stream()
                .filter(n -> n.length() > 3) // intermedia
                .distinct() // intermedia
                .sorted() // intermedia
                .peek(System.out::println) // intermedia (debug)
                .collect(Collectors.toList()); // terminal

        imprimir.accept("Resultado final: " + resultado);

        // Ejemplo con reduce y match
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
        int sumaTotal = numeros.stream().reduce(0, Integer::sum);
        boolean hayPares = numeros.stream().anyMatch(esPar);
        imprimir.accept("Suma total = " + sumaTotal);
        imprimir.accept("¿Hay pares? " + hayPares);
    }
}
