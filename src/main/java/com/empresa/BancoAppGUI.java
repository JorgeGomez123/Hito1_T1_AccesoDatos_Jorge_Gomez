// src/main/java/com/empresa/BancoAppGUI.java
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
        setTitle("Gestión de Cuenta Bancaria");
        setSize(1000, 800); // Cambia el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Saldo"));
        labelSaldo = new JLabel("Saldo actual: " + cuenta.getSaldo());
        panelSuperior.add(labelSaldo);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(panelSuperior, gbc);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Movimientos"));
        areaMovimientos = new JTextArea(20, 50); // Aumenta el tamaño del área de texto
        areaMovimientos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaMovimientos);
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(panelCentral, gbc);

        JPanel panelInferior = new JPanel(new GridBagLayout());
        panelInferior.setBorder(BorderFactory.createTitledBorder("Operaciones"));
        gbc.gridwidth = 1;

        JLabel labelCantidad = new JLabel("Cantidad:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelInferior.add(labelCantidad, gbc);

        fieldCantidad = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelInferior.add(fieldCantidad, gbc);

        JButton botonIngresar = new JButton("Ingresar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelInferior.add(botonIngresar, gbc);

        JButton botonRetirar = new JButton("Retirar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelInferior.add(botonRetirar, gbc);

        JButton botonGuardar = new JButton("Guardar y Salir");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelInferior.add(botonGuardar, gbc);

        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelInferior.add(botonCerrarSesion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(panelInferior, gbc);

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

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cuenta.guardarCuenta();
                JOptionPane.showMessageDialog(null, "Cuenta guardada exitosamente.");
                System.exit(0);
            }
        });

        botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cuenta.guardarCuenta();
                setVisible(false);
                dispose();
                LoginGUI login = new LoginGUI();
                login.setVisible(true);
            }
        });

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