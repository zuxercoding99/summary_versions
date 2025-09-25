package org.example.v1_0.poo;

// Importamos clases útiles
import java.util.Objects;

// Interfaz funcional (un solo método abstracto)
@FunctionalInterface
interface Operacion {
    int aplicar(int a, int b);

    // Puede tener métodos default y static
    default void descripcion() {
        System.out.println("Interfaz funcional con un método aplicar");
    }

    static void info() {
        System.out.println("Método estático en interfaz funcional");
    }
}

// Clase base (superclase)
class Persona {
    // Atributos
    private String nombre; // variable de instancia
    protected int edad; // accesible en subclases
    public static final String ESPECIE = "Humano"; // constante de clase

    // Constructor
    public Persona(String nombre, int edad) {
        this.nombre = nombre; // referencia al objeto actual
        this.edad = edad;
    }

    // Sobrecarga de constructores
    public Persona(String nombre) {
        this(nombre, 0); // llama a otro constructor
    }

    // Métodos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void cumplirAnios() {
        this.edad++;
    }

    // Método que será sobrescrito en subclase
    public String presentarse() {
        return "Soy " + nombre + " y tengo " + edad + " años.";
    }

    // Método estático
    public static void mostrarEspecie() {
        System.out.println("Todos somos de la especie: " + ESPECIE);
    }
}

// Subclase que hereda de Persona
class Estudiante extends Persona {
    private String carrera;

    public Estudiante(String nombre, int edad, String carrera) {
        super(nombre, edad); // referencia a superclase
        this.carrera = carrera;
    }

    // Sobrescritura de método
    @Override
    public String presentarse() {
        return super.presentarse() + " Estudio " + carrera + ".";
    }
}

// Clase inmutable (atributos finales y sin setters)
final class PuntoInmutable {
    private final int x;
    private final int y;

    public PuntoInmutable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

// Clase principal
public class PooResumen {
    public static void main(String[] args) {
        // Creación de objetos (instancias de clases)
        Persona persona1 = new Persona("Ana", 30);
        Estudiante estudiante1 = new Estudiante("Luis", 20, "Ingeniería");

        // Uso de atributos y métodos
        persona1.cumplirAnios();
        System.out.println(persona1.presentarse());
        System.out.println(estudiante1.presentarse());

        // Uso de constante y método estático
        Persona.mostrarEspecie();

        // Uso de objeto inmutable
        PuntoInmutable punto = new PuntoInmutable(5, 10);
        System.out.println("Punto inmutable: " + punto);

        // Uso de interfaz funcional con lambda (implementación de método abstracto)
        Operacion suma = (a, b) -> a + b;
        Operacion multiplicacion = (a, b) -> a * b;

        System.out.println("Suma: " + suma.aplicar(3, 4));
        System.out.println("Multiplicación: " + multiplicacion.aplicar(3, 4));

        suma.descripcion();
        Operacion.info();
    }
}
