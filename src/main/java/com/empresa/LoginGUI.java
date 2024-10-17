package com.empresa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    public LoginGUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Inicio de Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelNombre, gbc);

        JTextField fieldNombre = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(fieldNombre, gbc);

        JLabel labelApellidos = new JLabel("Apellidos:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelApellidos, gbc);

        JTextField fieldApellidos = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(fieldApellidos, gbc);

        JLabel labelDni = new JLabel("DNI:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelDni, gbc);

        JTextField fieldDni = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(fieldDni, gbc);

        JButton botonIniciarSesion = new JButton("Iniciar Sesión");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(botonIniciarSesion, gbc);

        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = fieldNombre.getText();
                String apellidos = fieldApellidos.getText();
                String dni = fieldDni.getText();
                Cuenta cuenta = new Cuenta(new Cliente(nombre, apellidos, dni));
                BancoAppGUI app = new BancoAppGUI(cuenta);
                app.setVisible(true);
                setVisible(false);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
    }
}
