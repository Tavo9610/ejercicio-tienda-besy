package com.besysoft.service;

import com.besysoft.model.Producto;
import com.besysoft.model.Vendedor;
import com.besysoft.model.Venta;

import java.util.ArrayList;
import java.util.List;

public class TiendaService {

    /*
    * creo listas para guardar los datos en memoria de cada tipo como pide la consigna
    */
    private List<Producto> productos = new ArrayList<>();
    private List<Vendedor> vendedores = new ArrayList<>();
    private List<Venta> ventas = new ArrayList<>();

    //los metodos para manipular los datos
    public void agregarProducto(Producto producto){
        productos.add(producto);
        System.out.println( "Producto agregado correctamente!");
    }

    public void agregarVendedor(Vendedor vendedor){
        vendedores.add(vendedor);
        System.out.println( "Vendedor agregado correctamente!");
    }

    //muestro los productos con un for each buena idea me tiro de validar si hay datos o no cargados
    public void mostrarProductos(){

        if(productos.isEmpty()){

            System.out.println("No hay productos cargados");
            return;
        }

        for(Producto producto : productos){

            System.out.println(producto);
        }
    }

    public void mostrarVentas(){

        if(ventas.isEmpty()){
            System.out.println("No hay ventas cargadas");
            return;
        }
        for(Venta venta : ventas){

            System.out.println(venta);
        }
    }

    public void mostrarVendedores(){

        if(vendedores.isEmpty()){
            System.out.println("No hay vendedores cargados");
            return;
        }
        for(Vendedor vendedor:vendedores){
            System.out.println(vendedor);
        }
    }

    public void registrarVenta(Integer codigoProducto, Integer codigoVendedor){

        Producto productoEncontrado = null;
        Vendedor vendedorEncontrado = null;

        // buscar producto
        for(Producto producto : productos){

            if(producto.getCodigo().equals(codigoProducto)){

                productoEncontrado = producto;
            }
        }

        // buscar vendedor
        for(Vendedor vendedor : vendedores){
            if(vendedor.getCodigo().equals(codigoVendedor)){
                vendedorEncontrado = vendedor;
            }
        }

        // validar
        if(productoEncontrado == null){
            System.out.println("Producto no encontrado");
            return;
        }

        if(vendedorEncontrado == null){
            System.out.println("Vendedor no encontrado");
            return;
        }

        // crear venta
        Venta venta = new Venta(productoEncontrado, vendedorEncontrado);
        ventas.add(venta);
        System.out.println("Venta registrada correctamente");
    }

    //metodos para busquedas que implemente x categoria, x nombre

    public void buscarProductoPorCategoria(String categoria){
        boolean encontrado = false;
        for(Producto producto : productos){
            if(producto.getCategoria().equalsIgnoreCase(categoria)){
                System.out.println(producto);
                encontrado = true;
            }
        }
        if(!encontrado){
            System.out.println("No se encontraron productos de esa categoría");
        }
    }

    public void buscarProductoPorNombre(String nombre){
        boolean encontrado = false;
        for(Producto producto : productos){
            if(producto.getNombre().equalsIgnoreCase(nombre)){
                System.out.println(producto);
                encontrado = true;
            }
        }
        if(!encontrado){
            System.out.println("No se encontró un producto con ese nombre");
        }
    }

    /*
    public void buscarProductoPorCodigo(Integer codigo){
        boolean encontrado = false;
        for(Producto producto : productos){
            if(producto.getCodigo().equals(codigo)){
                System.out.println(producto);
            } else if (!encontrado) {
                System.out.println("No se enconttro un producto con ese codigo");
            }

        }

       este metodo me equivoque esta mal
     */

    public void buscarProductoPorCodigo(Integer codigo){
        boolean encontrado = false;
        for(Producto producto : productos){
            if(producto.getCodigo().equals(codigo)){
                System.out.println(producto);
                encontrado = true;
            }
        }
        if(!encontrado){
            System.out.println("No se encontró un producto con ese código");
        }
    }

    public void calcularComision(Integer codigoVendedor){
        int cantidadVentas = 0;
        Double totalVendido = 0.0;
        for(Venta venta : ventas){
            if(venta.getVendedor().getCodigo().equals(codigoVendedor)){
                cantidadVentas++;
                totalVendido += venta.getProducto().getPrecio();
            }
        }
        if(cantidadVentas == 0){
            System.out.println("El vendedor no tiene ventas registradas");
            return;
        }
        Double comision;
        if(cantidadVentas <= 2){
            comision = totalVendido * 0.05;
        }
        else {
            comision = totalVendido * 0.10;
        }
        System.out.println("Cantidad de ventas: " + cantidadVentas);
        System.out.println("Total vendido: $" + totalVendido);
        System.out.println("Comision total: $" + comision);
    }


}





