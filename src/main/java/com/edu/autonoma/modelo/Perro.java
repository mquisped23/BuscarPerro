package com.edu.autonoma.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity

public class Perro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;


    @Column(nullable = false)
    private String diaPerdido;
    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false)
    private String tamano;

    @Column(nullable = false)
    private String raza;

    // Almacena la foto como un arreglo de bytes en la base de datos.
    @Column(name = "foto")

    private String foto;
    // Constructor, getters y setters

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Perro() {
    }

    public Perro(String nombre, String diaPerdido, String ubicacion, String sexo, int edad, String tamano, String raza, String foto) {
        this.nombre = nombre;
        this.diaPerdido = diaPerdido;
        this.ubicacion = ubicacion;
        this.sexo = sexo;
        this.edad = edad;
        this.tamano = tamano;
        this.raza = raza;
        this.foto = foto;
    }
    public Perro(String nombre, String diaPerdido, String ubicacion, String sexo, int edad, String tamano, String raza, String foto, Usuario usuario) {
        this.nombre = nombre;
        this.diaPerdido = diaPerdido;
        this.ubicacion = ubicacion;
        this.sexo = sexo;
        this.edad = edad;
        this.tamano = tamano;
        this.raza = raza;
        this.foto = foto;
        this.usuario = usuario;
    }


    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDiaPerdido() {
        return diaPerdido;
    }

    public void setDiaPerdido(String diaPerdido) {
        this.diaPerdido = diaPerdido;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

