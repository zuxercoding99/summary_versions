package org.example.v1_0.poo;

import java.util.*;

// -------------------- ASOCIACIÓN SIMPLE --------------------
class Profesor {
    private String nombre;
    private List<Curso> cursos = new ArrayList<>(); // conoce a Curso (N:M)

    public Profesor(String nombre) {
        this.nombre = nombre;
    }

    public void agregarCurso(Curso c) {
        cursos.add(c);
    }

    public void mostrarCursos() {
        System.out.println("Profesor " + nombre + " enseña:");
        for (Curso c : cursos)
            System.out.println("- " + c.getNombre());
    }
}

class Curso {
    private String nombre;

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

// -------------------- AGREGACIÓN --------------------
class Equipo {
    private String nombre;
    private List<Jugador> jugadores = new ArrayList<>(); // jugadores pueden existir fuera del equipo

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public void agregarJugador(Jugador j) {
        jugadores.add(j);
    }

    public void mostrarJugadores() {
        System.out.println("Equipo: " + nombre);
        for (Jugador j : jugadores)
            System.out.println("- " + j.getNombre());
    }
}

class Jugador {
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

// -------------------- COMPOSICIÓN --------------------
class Casa {
    private List<Habitacion> habitaciones = new ArrayList<>();

    public Casa() {
        habitaciones.add(new Habitacion("Cocina"));
        habitaciones.add(new Habitacion("Sala"));
    }

    public void mostrarHabitaciones() {
        System.out.println("Casa con habitaciones:");
        for (Habitacion h : habitaciones)
            System.out.println("- " + h.getNombre());
    }
}

class Habitacion {
    private String nombre;

    public Habitacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

// -------------------- DEPENDENCIA --------------------
class Cliente {
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

class Servicio {
    public void procesar(Cliente cliente) { // usa Cliente temporalmente
        System.out.println("Procesando cliente " + cliente.getNombre());
    }
}

// -------------------- HERENCIA --------------------
class Animal {
    void hacerSonido() {
        System.out.println("Sonido genérico");
    }
}

class Perro extends Animal {
    @Override
    void hacerSonido() {
        System.out.println("Guau");
    }
}

// -------------------- MAIN --------------------
public class RelacionesPOO {
    public static void main(String[] args) {
        // Asociación simple
        Profesor prof = new Profesor("Ana");
        Curso poo = new Curso("POO");
        Curso bd = new Curso("Bases de Datos");
        prof.agregarCurso(poo);
        prof.agregarCurso(bd);
        prof.mostrarCursos();

        // Agregación
        Equipo equipo = new Equipo("Tigres");
        Jugador j1 = new Jugador("Luis");
        Jugador j2 = new Jugador("Carlos");
        equipo.agregarJugador(j1);
        equipo.agregarJugador(j2);
        equipo.mostrarJugadores();

        // Composición
        Casa casa = new Casa();
        casa.mostrarHabitaciones();

        // Dependencia
        Cliente cliente = new Cliente("María");
        Servicio servicio = new Servicio();
        servicio.procesar(cliente);

        // Herencia
        Animal a = new Perro();
        a.hacerSonido();
    }
}
