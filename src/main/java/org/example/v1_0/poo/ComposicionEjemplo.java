package org.example.v1_0.poo;

// -------------------------------------------
// Principio: "Preferir Composición sobre Herencia"
// La composición permite reutilizar comportamiento
// delegando en otros objetos en lugar de heredarlo,
// aumentando flexibilidad y reduciendo acoplamiento.

// 1️⃣ Interfaces de comportamiento (contratos)
interface Loginable {
    void login();
}

interface AdminActions {
    void deleteUser();

    void banUser();
}

// 2️⃣ Implementaciones concretas
class BasicLogin implements Loginable {
    public void login() {
        System.out.println("Usuario logueado con autenticación básica");
    }
}

class OAuthLogin implements Loginable {
    public void login() {
        System.out.println("Usuario logueado con OAuth");
    }
}

class AdminTools implements AdminActions {
    public void deleteUser() {
        System.out.println("Usuario eliminado");
    }

    public void banUser() {
        System.out.println("Usuario baneado");
    }
}

// 3️⃣ Clases usando COMPOSICIÓN (has-a)
class Guest {
    private Loginable login;

    public Guest(Loginable login) {
        this.login = login;
    }

    public void login() {
        login.login(); // delegación
    }
}

class RegularUser {
    private Loginable login;

    public RegularUser(Loginable login) {
        this.login = login;
    }

    public void login() {
        login.login(); // delegación
    }
}

class SuperUser {
    private Loginable login;
    private AdminActions adminActions;

    public SuperUser(Loginable login, AdminActions adminActions) {
        this.login = login;
        this.adminActions = adminActions;
    }

    public void login() {
        login.login(); // delegación
    }

    public void deleteUser() {
        adminActions.deleteUser();
    }

    public void banUser() {
        adminActions.banUser();
    }
}

// 4️⃣ Ejemplo de uso en main
public class ComposicionEjemplo {
    public static void main(String[] args) {
        // Guest solo puede loguearse
        Guest guest = new Guest(new BasicLogin());
        guest.login();

        // RegularUser con otro tipo de login
        RegularUser user = new RegularUser(new OAuthLogin());
        user.login();

        // SuperUser con login + herramientas admin
        SuperUser superUser = new SuperUser(new BasicLogin(), new AdminTools());
        superUser.login();
        superUser.deleteUser();
        superUser.banUser();
    }
}

/*
 * -------------------------------------------------
 * 📌 COMPOSICIÓN VS HERENCIA
 * 
 * HERENCIA (is-a):
 * - Subclase hereda TODOS los métodos de la superclase.
 * - Ejemplo: class Car extends Vehicle
 * - Problema: acoplamiento fuerte, subclases heredan cosas que no necesitan.
 * 
 * COMPOSICIÓN (has-a):
 * - Clase contiene otros objetos y delega en ellos.
 * - Ejemplo: class Car { Engine motor; }
 * - Ventaja: reutiliza solo lo necesario, implementaciones intercambiables.
 * 
 * Regla práctica:
 * ➡ Usa herencia solo en jerarquías estables con clara relación "es un".
 * ➡ Usa composición cuando quieras flexibilidad y bajo acoplamiento.
 * -------------------------------------------------
 */
