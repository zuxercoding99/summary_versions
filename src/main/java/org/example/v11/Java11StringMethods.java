package org.example.v11;

import java.util.*;
import java.util.stream.*;

public class Java11StringMethods {
    public static void main(String[] args) {

        String texto = "  Hola Mundo  ";
        String multiLinea = "line1\nline2\nline3";

        // isBlank
        System.out.println("  ".isBlank()); // true
        System.out.println("Hola".isBlank()); // false

        // strip / stripLeading / stripTrailing
        System.out.println("  Hola  ".strip()); // "Hola"
        System.out.println("  Hola  ".stripLeading()); // "Hola "
        System.out.println("  Hola  ".stripTrailing()); // " Hola"

        // repeat
        System.out.println("eco ".repeat(3)); // "eco eco eco "

        // lines -> stream
        multiLinea.lines().forEach(System.out::println); // imprime cada línea

        // combinación práctica
        long count = multiLinea.lines()
                .filter(line -> !line.isBlank())
                .count();
        System.out.println("Líneas no vacías: " + count);

        // Linea mas larga
        String texto2 = "Ana\nJuanito\nLuis";

        String max = texto2.lines()
                .max(Comparator.comparingInt(String::length))
                .orElse("");

        System.out.println(max); // Juanito

        // Recomponer
        String texto3 = "ana\njuan\nluis";

        String mayus = texto3.lines()
                .map(String::toUpperCase)
                .collect(Collectors.joining("\n"));

        System.out.println(mayus);
        /*
         * ANA
         * JUAN
         * LUIS
         */

    }

}
