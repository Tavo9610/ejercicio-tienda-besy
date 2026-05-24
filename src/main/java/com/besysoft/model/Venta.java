package com.besysoft.model;

public class Venta {
    //instancio Objetos del tipo especificado
    // lo que crea la relacion entre venta, producto y vendedor
    // (simula lo que seria una inyeccion de dependencias en Spring)
    private Producto producto;
    private Vendedor vendedor;

    //creo un constructor c parametros para crear una venta con producto y vendedor
    public Venta(Producto producto, Vendedor vendedor){
        this.producto = producto;
        this.vendedor = vendedor;
    }

    //voy a crear getters, setters no es recomendable porque no deberian poder cambiarse las ventas ya realiuzadas

    public Producto getProducto(){
        return producto;
    }

    public Vendedor getVendedor(){
        return vendedor;
    }

    //le sobrescribo el toString para mostarlo mejor por consola
    @Override
    public String toString(){
        return "Venta{ " +
                "producto= " + producto.getNombre()+
                ", vendedor= " + vendedor.getNombre()+
                '}';

    }


}
