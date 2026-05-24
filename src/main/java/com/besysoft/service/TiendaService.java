package com.besysoft.service;
import com.besysoft.exception.ProductoNoEncontradoException;
import com.besysoft.exception.VendedorNoEncontradoException;
import com.besysoft.exception.VentaInvalidaException;
import com.besysoft.model.*;
import com.besysoft.repository.*;

import java.util.List;

public class TiendaService {

    //simulo persistenca en una ddb real aca
    private final ProductoRepository productoRepo = new ProductoRepository();
    private final VendedorRepository vendedorRepo = new VendedorRepository();
    private final VentaRepository ventaRepo = new VentaRepository();

    //productos
    public void agregarProducto(Producto producto) {
        if(producto.getPrecio() <= 0){
            throw new VentaInvalidaException("El precio del producto debe ser mayor a 0");
        }
        if(producto.getCodigo()<0){
            throw new VentaInvalidaException("El codigo del producto debe ser mayor a 0");
        }
        if(productoRepo.findByCodigo(producto.getCodigo()) != null){
            throw new VentaInvalidaException("Ya existe un producto con el codgio: " + producto.getCodigo());
        }
        productoRepo.save(producto);
    }

    public List <Producto> mostrarProductos() {
        return productoRepo.findAll();
    }

    // vendedores
    public void agregarVendedor(Vendedor vendedor) {
        if (vendedor.getSueldo() <= 0) {
            throw new VentaInvalidaException("El sueldo ingresado debe ser mayor a 0");
        }
        if (vendedor.getCodigo()<0){
            throw new VentaInvalidaException("El codigo de vendedor debe ser mayor a 0");
        }
        if(vendedor.getNombre() == null || vendedor.getNombre().isBlank()){
            throw new VentaInvalidaException("El nombre del vendedor no puede estar vacio");
        }
        if(!vendedor.getNombre().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")){ //me sugirieron usar regex en estos casos yo no lo conocia
            throw new VentaInvalidaException("El nombre del vendedor solo puede contener letras y espacios");
        }
        vendedorRepo.save(vendedor);
    }

    public List<Vendedor> mostrarVendedores() {
        return vendedorRepo.findAll();
    }

    //ventas
    public void registrarVenta(Integer codigoProducto, Integer codigoVendedor) {

        Producto producto = productoRepo.findByCodigo(codigoProducto);
        Vendedor vendedor = vendedorRepo.findByCodigo(codigoVendedor);
        if (producto == null) {
            throw new ProductoNoEncontradoException("No existe un producto con ese codigo ");
        }
        if (vendedor == null) {
            throw new VendedorNoEncontradoException("No existe un vendedor con ese codigo");
        }
        Venta venta = new Venta(producto, vendedor);
        ventaRepo.save(venta);
    }

    public List<Venta> mostrarVentas() {
        return ventaRepo.findAll();
    }

    // use stream con exp lambdas
    public List<Producto> buscarProductoPorCategoria(String categoria) {
        return productoRepo.findAll()
                .stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }
    public List<Producto> buscarProductoPorNombre(String nombre) {
       return productoRepo.findAll()
               .stream()
               .filter(p->p.getNombre().equalsIgnoreCase(nombre))
               .toList();
    }
    public Producto buscarProductoPorCodigo(Integer codigo) {
        Producto producto = productoRepo.findByCodigo(codigo);
        if (producto == null) {
            throw new ProductoNoEncontradoException("No existe un producto con ese código");
        }
        return producto;
    }

    public Double calcularComision(Integer codigoVendedor) {
        int cantidadVentas = 0;
        double totalVendido = 0.0;
        for (Venta venta : ventaRepo.findAll()) {
            if (venta.getVendedor().getCodigo().equals(codigoVendedor)) {
                cantidadVentas++;
                totalVendido += venta.getProducto().getPrecio();
            }
        }
        if(vendedorRepo.findByCodigo(codigoVendedor) == null){
            throw new VentaInvalidaException("No existe un vendedor con ese codigo");
        }

        if (cantidadVentas == 0) {
            throw new VentaInvalidaException("El vendedor no tiene ventas registradas");
        }

        return (cantidadVentas <= 2) ? totalVendido * 0.05 : totalVendido * 0.10; //op ternario que me sugirieron
    }

}