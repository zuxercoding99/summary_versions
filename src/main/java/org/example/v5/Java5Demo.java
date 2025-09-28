package org.example.v5;


// 🔹 1. Generics: seguridad de tipos
class Caja<T> {
    private T valor;

    public void set(T v) {
        valor = v;
    }

    public T get() {
        return valor;
    }
}

// 🔹 2. Enum: conjunto fijo de constantes con lógica extra
enum Dia {
    LUNES("Inicio"), VIERNES("Casi finde"), DOMINGO("Descanso");

    private String desc;

    Dia(String d) {
        desc = d;
    }

    public String getDesc() {
        return desc;
    }
}

public class Java5Demo {

    // 🔹 3. Varargs: número variable de argumentos
    static int sumar(int... nums) {
        int total = 0;
        for (int n : nums)
            total += n;
        return total;
    }

    // 🔹 4. Annotation personalizada
    @interface MiAnotacion {
        String autor();
    }

    @MiAnotacion(autor = "Ken")
    static void metodoConAnotacion() {
        System.out.println("Método anotado ejecutado.");
    }

    public static void main(String[] args) {
        // Generics
        Caja<String> c = new Caja<>();
        c.set("Hola Genéricos");
        System.out.println(c.get());

        // Autoboxing / Unboxing
        Integer x = 5; // autoboxing de int -> Integer
        int y = x + 10; // unboxing automático
        System.out.println("Autoboxing: " + y);

        // Enum
        Dia d = Dia.VIERNES;
        System.out.println(d + " → " + d.getDesc());

        // Varargs
        System.out.println("Suma: " + sumar(1, 2, 3, 4, 5));

        // Anotaciones
        metodoConAnotacion();
    }
}
