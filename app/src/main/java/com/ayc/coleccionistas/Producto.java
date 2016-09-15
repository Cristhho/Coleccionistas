package com.ayc.coleccionistas;

public class Producto {
    private long id;
    private String nombre, descripcion, precio, imagen, categoria, correo;

    public Producto (long id, String nombre, String descripcion, String precio, String imagen, String categoria, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria = categoria;
        this.correo = correo;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCorreo() {
        return correo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
