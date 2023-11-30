package com.edu.autonoma.modelo;

import javax.persistence.*;

@Entity
public class Encontrado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;


    @Column(nullable = false)
    private String diaEncontrado;
    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private String sexo;
    @Column(nullable = false)
    private String contacto;

    // Almacena la foto como un arreglo de bytes en la base de datos.
    @Column(name = "foto")

    private String foto;
    // Constructor, getters y setters

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    public Encontrado() {
    }

    public Encontrado(Long id, String nombre, String diaEncontrado, String ubicacion, String sexo, String contacto, String foto, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.diaEncontrado = diaEncontrado;
        this.ubicacion = ubicacion;
        this.sexo = sexo;
        this.contacto = contacto;
        this.foto = foto;
        this.usuario = usuario;
    }

    public Encontrado(String nombre, String diaEncontrado, String ubicacion, String sexo, String contacto, String foto) {
        this.nombre = nombre;
        this.diaEncontrado = diaEncontrado;
        this.ubicacion = ubicacion;
        this.sexo = sexo;
        this.contacto = contacto;
        this.foto = foto;
    }

    public Encontrado(String nombre, String diaEncontrado, String ubicacion, String sexo, String contacto, String foto, Usuario usuario) {
        this.nombre = nombre;
        this.diaEncontrado = diaEncontrado;
        this.ubicacion = ubicacion;
        this.sexo = sexo;
        this.contacto = contacto;
        this.foto = foto;
        this.usuario = usuario;
    }

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

    public String getDiaEncontrado() {
        return diaEncontrado;
    }

    public void setDiaEncontrado(String diaEncontrado) {
        this.diaEncontrado = diaEncontrado;
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
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
