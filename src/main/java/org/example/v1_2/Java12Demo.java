package org.example.v1_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Java12Demo {

    // ====== Ejemplo Swing ======
    static class MiVentana extends JFrame {
        private JTextField campoTexto;
        private JTextArea areaSalida;

        public MiVentana() {
            setTitle("Demo Swing (Java 2 - 1998)");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Panel con BorderLayout
            JPanel panel = new JPanel(new BorderLayout());

            // Campo de texto arriba
            campoTexto = new JTextField();
            JButton boton = new JButton("Mostrar");
            JPanel panelEntrada = new JPanel();
            panelEntrada.add(campoTexto);
            panelEntrada.add(boton);

            // Área de salida al centro
            areaSalida = new JTextArea();
            areaSalida.setEditable(false);
            JScrollPane scroll = new JScrollPane(areaSalida);

            panel.add(panelEntrada, BorderLayout.NORTH);
            panel.add(scroll, BorderLayout.CENTER);
            add(panel);

            // Evento con lambda (desde Java 8, antes era clase anónima)
            boton.addActionListener(e -> {
                areaSalida.setText("Ingresado: " + campoTexto.getText());
            });
        }
    }

    // ====== Ejemplo Collections Framework ======
    static void demoColecciones() {
        List<String> lista = new ArrayList<>();
        lista.add("Manzana");
        lista.add("Banana");
        lista.add("Manzana"); // duplicado permitido

        Set<String> conjunto = new HashSet<>();
        conjunto.add("Rojo");
        conjunto.add("Azul");
        conjunto.add("Rojo"); // ignorado

        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("Juan", 30);
        mapa.put("Ana", 25);
        mapa.put("Juan", 40); // reemplaza valor

        System.out.println("Lista: " + lista);
        System.out.println("Set: " + conjunto);
        System.out.println("Mapa: " + mapa);
    }

    // ====== Main ======
    public static void main(String[] args) {
        // Swing: correr en Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new MiVentana().setVisible(true));

        // Collections
        demoColecciones();
    }
}
