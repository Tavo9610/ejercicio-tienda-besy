package com.besysoft.model;

public class Producto {

    private Integer codigo;
    private String nombre;
    private Double precio;
    private String categoria;

    public Producto(Integer codigo, String nombre, Double precio, String categoria){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Integer getCodigo(){
        return codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public Double getPrecio(){
        return precio;
    }

    public String getCategoria(){
        return categoria;
    }


    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                '}';
    }

}
