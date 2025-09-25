package org.example.v1_0.imperative;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class AllImperative {
    /*
     * El paradigma imperativo se basa en escribir una secuencia de instrucciones,
     * usando variables, operadores y estructuras de control, para manejar datos y
     * resolver problemas -w-
     */
    public static void run() {
        /* Tipos primitivos existen 8 */
        byte myByte = 127;
        short myShort = 30_000;
        int myInt = 1_000_000;
        long myLong = 1_123_456_789L;

        float myFloat = 312321.3432f;
        double myDouble = 3424.321312;

        char myChar = 'Z';
        boolean myBoolean = true;

        /*
         * Wrapper classes
         * Version objecto de los tipos primitivos
         * Permiten usar primitivos como objectos
         * Ofrecen metodos utiles
         * Pueden ser null
         * Necesarios para trabajar con APIs Genericas
         */
        /* autoboxing and unboxing entre un primitivo y su version objecto */
        Integer myIntObj = 31;
        int myIntValue = myIntObj;

        Boolean isValid = true;
        Character letter = '_';
        Double decimal = 9.95;

        int numInt = Integer.parseInt("26");
        double numDouble = Double.parseDouble("26.6");
        boolean myBool = Boolean.parseBoolean("true");

        String stringNumInt = Integer.toString(numInt);
        String stringNumDouble = Double.toString(numDouble);
        String stringBool = Boolean.toString(myBool);

        Integer numInteger = Integer.valueOf(stringNumInt);
        Double numDouble2 = Double.valueOf(stringNumDouble);
        Boolean boolValue2 = Boolean.valueOf(stringBool);

        int numInt1 = numInteger.intValue();
        double numDouble1 = numDouble2.doubleValue();
        boolean boolValue = boolValue2.booleanValue();

        System.out.println(numInteger + " el binario es: " + Integer.toBinaryString(numInteger));

        System.out.println(numInteger + " el hex es: " + Integer.toHexString(numInteger));

        Boolean result = Character.isLetterOrDigit(letter);
        System.out.println(letter + " is letter or digit? " + result);

        /*
         * Array
         * Estructura de datos que almacena un conjunto fijo de datos del mismo tipo,
         * indexados desde el 0.
         */
        // Crear array con 3 posiciones
        int[] numeros = new int[3];

        // Escritura
        numeros[0] = 10;
        numeros[1] = 20;
        numeros[2] = 30;

        // Lectura
        System.out.println("Primero valor: " + numeros[0]);

        // Atributos
        System.out.println("Tamaño del array: " + numeros.length);

        // Recorrido
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Valor en indice " + i + ": " + numeros[i]);
        }

        // Inicializacion directa
        String[] dias = { "Lunes", "Martes", "Miercoles" };
        for (String dia : dias) {
            System.out.println(dia);
        }

        /*
         * String
         * Tipo objecto que representa texto.
         * Es inmutable ( no se puede modificar )
         */

        String saludo = "Hola";
        String saludo2 = new String("Hola");

        String texto = "Java es poderoso";
        int size = texto.length(); // Tamaño
        char firstCharacter = texto.charAt(0); // 'J';
        String sub = texto.substring(5); // Subcadena "es poderoso"
        String subend = texto.substring(0, 4); // "Java"
        int index = texto.indexOf("es"); // buscar indice de substring
        boolean contains = texto.contains("poderoso"); // preguntar si existe substring
        String replace1 = texto.replace("poderoso", "el mejor"); // Java es el mejor
        String replace2 = texto.replaceAll("[aiueoAIUEO]", "*"); // J*v* *s p*d*r*s*;
        String upper = texto.toUpperCase(); // JAVA ES PODEROSO
        String lower = texto.toLowerCase(); // java es poderoso
        String trimmed = "      Hello World       ".trim(); // Eliminar espacios
        boolean igual = saludo.equals(saludo2);
        boolean igual2 = "HolA".equalsIgnoreCase(saludo); // comparar

        // Revertir string, util ex Palindromo
        String invertido = new StringBuilder("Hola").reverse().toString(); // aloH

        // Anagrama: Dos String son anagramas si tienen las mismas letras con la misma
        // cantidad, aunque en distinto orden.
        boolean isAnagrama = sonAnagramas("Hola", "aLoH");
        System.out.println("Es anagrama?: " + isAnagrama);

        // Conversiones usando el API STreams java 8
        int[] numsInt = { 1, 2, 3 };
        Integer[] numsInteger = Arrays.stream(numsInt).boxed().toArray(n -> new Integer[n]);

        

        numsInt = Arrays.stream(numsInteger).mapToInt(n -> n.intValue()).toArray();

        List<Integer> numList = Arrays.stream(numsInt).boxed().collect(Collectors.toList());

        numsInt = numList.stream().mapToInt(n -> n.intValue()).toArray();

        String[] arrayString = List.of("a", "b").toArray(n -> new String[n]);

        char[] chars = "hello world".toCharArray();
        String charString = new String(chars);

        System.out.println(
                "Redondeo 2.4325 a 2 decimales: " + new BigDecimal(2.4325).setScale(2, RoundingMode.HALF_EVEN));

        System.out.println("Numero random del 1 al 100: " + ThreadLocalRandom.current().nextInt(1, 100));

        String hi = "Hi";
        int sizex = switch (hi) {
            case "Hello", "Holis" -> 5;
            case "Greetings" -> 9;
            case "Hi" -> 2;
            default -> -1;

        };

        System.out.println(hi + " : " + sizex);

        String test = "Text to test 2314 ok";
        // Get vowels count
        long sizeTest = test.chars().filter(c -> "aiueoAIUEO".indexOf(c) >= 0).count();
        // Get letters
        List<Character> letterTest = test.chars().filter(c -> Character.isLetter(c)).mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        letterTest.forEach(l -> System.out.println(l));

    }

    public static boolean sonAnagramas(String s1, String s2) {
        char[] a1 = s1.toLowerCase().toCharArray();
        char[] a2 = s2.toLowerCase().toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }
}
