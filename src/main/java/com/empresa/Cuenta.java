package com.empresa;

import java.io.*;
import java.util.ArrayList;

public class Cuenta implements Serializable {
    private Cliente cliente;
    private double saldo;
    private ArrayList<Movimiento> movimientos;

    public Cuenta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
        this.movimientos = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void ingresar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            movimientos.add(new Movimiento("Ingreso", cantidad));
            System.out.println("Ingreso realizado: " + cantidad);
        } else {
            System.out.println("La cantidad ingresada debe ser mayor que cero.");
        }
    }

    public void retirar(double cantidad) {
        if (cantidad > saldo) {
            System.out.println("Saldo insuficiente para retirar esa cantidad.");
        } else if (cantidad <= 0) {
            System.out.println("La cantidad retirada debe ser mayor que cero.");
        } else {
            saldo -= cantidad;
            movimientos.add(new Movimiento("Retirada", cantidad));
            System.out.println("Retirada realizada: " + cantidad);
        }
    }

    public void mostrarMovimientos() {
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos.");
        } else {
            for (Movimiento movimiento : movimientos) {
                System.out.println(movimiento);
            }
        }
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    // Serializar cuenta
    public void guardarCuenta() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cuenta.dat"))) {
            oos.writeObject(this);
            System.out.println("Cuenta guardada en disco.");
        } catch (IOException e) {
            System.out.println("Error al guardar la cuenta.");
        }
    }

    // Deserializar cuenta
    public static Cuenta recuperarCuenta() {
        Cuenta cuenta = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cuenta.dat"))) {
            cuenta = (Cuenta) ois.readObject();
            System.out.println("Cuenta cargada desde disco.");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró una cuenta guardada. Se creará una nueva cuenta.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cuenta;
    }
}

