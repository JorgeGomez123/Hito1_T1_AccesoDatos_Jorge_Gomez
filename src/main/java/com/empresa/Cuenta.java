package com.empresa;

import java.io.*;
import java.util.ArrayList;

public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
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
        }
    }

    public void retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            movimientos.add(new Movimiento("Retirada", cantidad));
        }
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void guardarCuenta() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cuenta.dat"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Cuenta recuperarCuenta() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cuenta.dat"))) {
            return (Cuenta) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}