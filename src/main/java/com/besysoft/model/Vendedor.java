package com.besysoft.model;

public class Vendedor {

    private Integer codigo;
    private String nombre;
    private Double sueldo;

    public Vendedor(Integer codigo, String nombre, Double sueldo){
        this.codigo = codigo;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public Integer getCodigo(){
        return codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public Double getSueldo(){
        return sueldo;
    }


    public void setCodigo(Integer codigo){
        this.codigo = codigo;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setSueldo(Double sueldo){
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }

}
