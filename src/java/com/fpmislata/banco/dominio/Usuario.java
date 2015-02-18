package com.fpmislata.banco.dominio;

import java.io.Serializable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;


public class Usuario implements Serializable{
    @Digits(integer=11,fraction=0)
    private int id;
    @Size(min=1, max=50)
    private String nombre;
    @Size(min=1, max=50)
    private String apellidos;
    @Size(min=1, max=100)
    private String email;
    @Size(min=1, max=50)
    private String direccion;
    @Size(min=1, max=50)
    private String telefono;
    @Size(min=1, max=100)
    private String contrasenya;
    Rol rol;
    @Digits(integer=11,fraction=0)
    private int idCuentaBancaria;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellidos, String email, String direccion, String telefono, String contrasenya, Rol rol, int idCuentaBancaria) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contrasenya = contrasenya;
        this.rol = rol;
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
}
