package com.empresa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento implements Serializable {
    private String tipo; // "Ingreso" o "Retirada"
    private double importe;
    private String fecha;

    public Movimiento(String tipo, double importe) {
        this.tipo = tipo;
        this.importe = importe;
        this.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public String getTipo() {
        return tipo;
    }

    public double getImporte() {
        return importe;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return fecha + " - " + tipo + ": " + importe;
    }
}

