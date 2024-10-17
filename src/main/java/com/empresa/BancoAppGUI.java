package com.empresa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BancoAppGUI extends JFrame {
    private Cuenta cuenta;
    private JLabel labelSaldo;
    private JTextArea areaMovimientos;
    private JTextField fieldCantidad;

    public BancoAppGUI(Cuenta cuenta) {
        this.cuenta = cuenta;
        initComponents();
    }

    private void initComponents() {
        // Configuración básica de la ventana
        setTitle("Gestión de Cuenta Bancaria");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para mostrar el saldo
        JPanel panelSuperior = new JPanel();
        labelSaldo = new JLabel("Saldo actual: " + cuenta.getSaldo());
        panelSuperior.add(labelSaldo);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central para mostrar movimientos
        areaMovimientos = new JTextArea(10, 30);
        areaMovimientos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaMovimientos);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para las operaciones
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());

        JLabel labelCantidad = new JLabel("Cantidad:");
        fieldCantidad = new JTextField(10);
        JButton botonIngresar = new JButton("Ingresar");
        JButton botonRetirar = new JButton("Retirar");
        JButton botonGuardar = new JButton("Guardar y Salir");

        // Añadir componentes al panel inferior
        panelInferior.add(labelCantidad);
        panelInferior.add(fieldCantidad);
        panelInferior.add(botonIngresar);
        panelInferior.add(botonRetirar);
        panelInferior.add(botonGuardar);

        add(panelInferior, BorderLayout.SOUTH);

        // Acción de botón Ingresar
        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double cantidad = Double.parseDouble(fieldCantidad.getText());
                    cuenta.ingresar(cantidad);
                    actualizarSaldoYMovimientos();
                    fieldCantidad.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce una cantidad válida.");
                }
            }
        });

        // Acción de botón Retirar
        botonRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double cantidad = Double.parseDouble(fieldCantidad.getText());
                    cuenta.retirar(cantidad);
                    actualizarSaldoYMovimientos();
                    fieldCantidad.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce una cantidad válida.");
                }
            }
        });

        // Acción de botón Guardar y Salir
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cuenta.guardarCuenta();
                JOptionPane.showMessageDialog(null, "Cuenta guardada exitosamente.");
                System.exit(0);
            }
        });

        // Mostrar movimientos al iniciar
        actualizarSaldoYMovimientos();
    }

    private void actualizarSaldoYMovimientos() {
        labelSaldo.setText("Saldo actual: " + cuenta.getSaldo());
        areaMovimientos.setText("");
        for (Movimiento m : cuenta.getMovimientos()) {
            areaMovimientos.append(m + "\n");
        }
    }

    public static void main(String[] args) {
        Cuenta cuenta = Cuenta.recuperarCuenta();
        if (cuenta == null) {
            String nombre = JOptionPane.showInputDialog("Introduce el nombre del cliente:");
            String apellidos = JOptionPane.showInputDialog("Introduce los apellidos del cliente:");
            String dni = JOptionPane.showInputDialog("Introduce el DNI del cliente:");
            cuenta = new Cuenta(new Cliente(nombre, apellidos, dni));
        }
        BancoAppGUI app = new BancoAppGUI(cuenta);
        app.setVisible(true);
    }
}