package com.empresa;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nombre;
    private String apellidos;
    private String dni;

    public Cliente(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " (DNI: " + dni + ")";
    }
}
