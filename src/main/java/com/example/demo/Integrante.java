package com.example.demo;

public class Integrante {

    private String nombre;
    private String apellidos;
    private String codigo;
    private String correo;
    private String celular;

    public Integrante(String nombre, String apellidos, String codigo, String correo, String celular) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.codigo = codigo;
        this.correo = correo;
        this.celular = celular;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
