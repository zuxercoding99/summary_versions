package org.example.v1_0.poo;

// Interfaz
interface Motor {
    void arrancar();
}

// Implementación concreta
class MotorElectrico implements Motor {
    @Override
    public void arrancar() {
        System.out.println("Motor eléctrico arrancando");
    }
}

// Extender comportamiento
class MotorElectricoConTurbo extends MotorElectrico {
    @Override
    public void arrancar() {
        super.arrancar(); // mantiene el comportamiento base
        System.out.println("Turbo activado!");
    }
}

// Cambiar comportamiento: nueva implementación
class MotorGasolina implements Motor {
    @Override
    public void arrancar() {
        System.out.println("Motor a gasolina arrancando");
    }
}

// Clase que delega en Motor (composición)
class Auto {
    private Motor motor;

    public Auto(Motor motor) {
        this.motor = motor;
    }

    public void encender() {
        motor.arrancar();
    }
}

// Ejemplo de uso
public class ComposicionPrincipio {
    public static void main(String[] args) {
        // Cambiar comportamiento
        Auto autoGasolina = new Auto(new MotorGasolina());
        autoGasolina.encender();
        // Salida: Motor a gasolina arrancando

        // Extender comportamiento
        Auto autoTurbo = new Auto(new MotorElectricoConTurbo());
        autoTurbo.encender();
        // Salida:
        // Motor eléctrico arrancando
        // Turbo activado!
    }
}
