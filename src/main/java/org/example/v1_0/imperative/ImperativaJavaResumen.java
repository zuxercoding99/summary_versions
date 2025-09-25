package org.example.v1_0.imperative;

import java.math.*;
import java.util.*;

import java.util.concurrent.ThreadLocalRandom;

public class ImperativaJavaResumen {

    public static void main(String[] args) {
        // Ejemplos básicos
        introduccion();
        variablesYPrimitivos();
        operadores();
        controlFlujo();
        bucles();
        entradaSalida();
        arraysBasicos();
        stringsBasicos();
        stringBuilder();
        mathYBigDecimal();
        conversiones();

        // Ejercicios prácticos
        System.out.println("\n--- Ejercicios de práctica ---");
        System.out.println("Clasificar número: " + clasificarNumero(-5));
        System.out.println("Par o impar: " + parImpar(7));
        System.out.println("Contar vocales: " + contarVocales("Hola Mundo"));
        System.out.println("Factorial de 5: " + factorial(5));
        System.out.println("Número aleatorio entre 1 y 10: " + numeroAleatorio(1, 10));
    }

    // ---------------- Temas Clave ----------------
    
    static void introduccion() {
    System.out.println("La programación imperativa consiste en dar instrucciones paso a paso usando variables, operadores y estructuras de control para manejar datos y resolver problemas.");
    System.out.println("En Java se trabaja principalmente con tipos primitivos, arrays y Strings.");
}

    static void variablesYPrimitivos() {
        System.out.println("\n--- Variables y Tipos Primitivos ---");
        int edad = 25;
        double precio = 19.99;
        boolean activo = true;
        char inicial = 'J';
        long grande = 123456789L;

        System.out.printf("Edad: %d, Precio: %.2f, Activo: %b, Inicial: %c, Long: %d%n",
                edad, precio, activo, inicial, grande);
    }

    static void operadores() {
        System.out.println("\n--- Operadores ---");
        int a = 10, b = 3;
        System.out.println("a+b=" + (a+b));
        System.out.println("a>b? " + (a>b));
        System.out.println("a&&b: " + (true && false));
        System.out.println("Ternario: " + (a>b ? "Mayor" : "Menor"));
    }

    static void controlFlujo() {
        System.out.println("\n--- Control de Flujo ---");
        int edad = 18;
        if (edad >= 18) System.out.println("Mayor de edad");
        else System.out.println("Menor de edad");

        int dia = 3;
        switch (dia) {
            case 1,2,3,4,5 -> System.out.println("Día de semana");
            case 6,7 -> System.out.println("Fin de semana");
            default -> System.out.println("Día inválido");
        }
    }

    static void bucles() {
        System.out.println("\n--- Bucles ---");
        for (int i=0; i<5; i++) {
            if (i==3) continue;
            System.out.println("for: " + i);
        }

        int j=0;
        while (j<3) {
            System.out.println("while: " + j);
            j++;
        }

        int k=0;
        do {
            System.out.println("do-while: " + k);
            k++;
        } while (k<2);
    }

    static void entradaSalida() {
        System.out.println("\n--- Entrada/Salida ---");
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Hola, " + nombre);
        // ⚠️ sc.close(); // No cerrar Scanner si se usa System.in en más lugares
    }

    static void arraysBasicos() {
        System.out.println("\n--- Arrays ---");
        int[] numeros = {10, 20, 30};
        for (int i=0; i<numeros.length; i++) {
            System.out.println("Pos " + i + ": " + numeros[i]);
        }
    }

    static void stringsBasicos() {
        System.out.println("\n--- Strings ---");
        String texto = "Java es poderoso";
        System.out.println("Length: " + texto.length());
        System.out.println("Substring: " + texto.substring(5,7));
        System.out.println("Replace: " + texto.replace("Java", "Kotlin"));
        System.out.println("Upper: " + texto.toUpperCase());
    }

    static void stringBuilder() {
        System.out.println("\n--- StringBuilder ---");
        StringBuilder sb = new StringBuilder("Hola");
        sb.append(" Mundo").reverse();
        System.out.println(sb.toString());
    }

    static void mathYBigDecimal() {
        System.out.println("\n--- Math y BigDecimal ---");
        System.out.println("Abs: " + Math.abs(-10));
        System.out.println("Pow: " + Math.pow(2,3));
        BigDecimal a = new BigDecimal("10.75");
        BigDecimal b = new BigDecimal("3");
        BigDecimal div = a.divide(b, 2, RoundingMode.HALF_UP);
        System.out.println("División precisa: " + div);
    }

    static void conversiones() {
        System.out.println("\n--- Conversiones ---");
        int x = Integer.parseInt("123");
        String s = String.valueOf(456);
        System.out.println("String->int: " + x + ", int->String: " + s);

        int entero = (int) 3.14;
        double d = 42;
        System.out.println("Casting: " + entero + ", " + d);
    }

    // ---------------- Ejercicios prácticos ----------------

    static String clasificarNumero(int n) {
        return n > 0 ? "Positivo" : (n < 0 ? "Negativo" : "Cero");
    }

    static String parImpar(int n) {
        return n % 2 == 0 ? "Par" : "Impar";
    }

    static long contarVocales(String texto) {
        return texto.chars()
                .filter(c -> "aeiouAEIOU".indexOf(c) >= 0)
                .count();
    }

    static long factorial(int n) {
        long res = 1;
        for (int i=1; i<=n; i++) res *= i;
        return res;
    }

    static int numeroAleatorio(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }
}
