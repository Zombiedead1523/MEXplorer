package com.example.myapplication;

public class Publicacion {
    private String titulo;
    private String ubicacion;
    private String descripcion;
    private String review;
    private int foto; // Referencia al recurso de la imagen
    private String presupuesto; // Nuevo atributo: presupuesto
    private String tamañoGrupo; // Nuevo atributo: tamaño del grupo
    private String tipoLugar; // Nuevo atributo: tipo de lugar

    // Constructor
    public Publicacion(String titulo, String ubicacion, String descripcion, String review, int foto,
                       String presupuesto, String tamañoGrupo, String tipoLugar) {
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.review = review;
        this.foto = foto;
        this.presupuesto = presupuesto;
        this.tamañoGrupo = tamañoGrupo;
        this.tipoLugar = tipoLugar;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getReview() {
        return review;
    }

    public int getFoto() {
        return foto;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public String getTamañoGrupo() {
        return tamañoGrupo;
    }

    public String getTipoLugar() {
        return tipoLugar;
    }
}
