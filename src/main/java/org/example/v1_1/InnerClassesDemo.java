package org.example.v1_1;

// Clase contenedora de todas las inner classes
class InnerClassesDemo {

    private String mensaje = "Estado externo";

    // 1. Inner normal (dentro de la clase externa)
    class Normal {
        void mostrar() {
            System.out.println("Normal: " + mensaje);
        }
    }

    // 2. Static nested (dentro de la clase externa con static)
    static class Estatica {
        void mostrar() {
            System.out.println("Estática");
        }

        static void diagnostico() {
            System.out.println("Método estático en Estática");
        }
    }

    void metodo() {
        int base = 10;

        // 3. Local inner (definida dentro de un método)
        class Local {
            int sumar(int x) {
                return x + base;
            }
        }
        System.out.println("Local: " + new Local().sumar(5));

        // 4. Anonymous inner (definida en la expresión new)
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("Anónima ejecutando");
            }
        };
        r.run();
    }

    public static void main(String[] args) {
        InnerClassesDemo demo = new InnerClassesDemo();

        // Inner normal
        InnerClassesDemo.Normal normal = demo.new Normal();
        normal.mostrar();

        // Static nested
        InnerClassesDemo.Estatica estatica = new InnerClassesDemo.Estatica();
        estatica.mostrar();
        InnerClassesDemo.Estatica.diagnostico();

        // Local inner y Anonymous inner
        demo.metodo();
    }
}
