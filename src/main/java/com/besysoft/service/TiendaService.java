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

    //Productos
    public void agregarProducto(Producto producto) {
        if(producto.getPrecio() <= 0){
            throw new VentaInvalidaException("El precio del producto debe ser mayor a 0");
        }
        productoRepo.save(producto);
    }

    public List <Producto> mostrarProductos() {
       return productoRepo.findAll();
    }

    // Vendedores
    public void agregarVendedor(Vendedor vendedor) {
        if (vendedor.getSueldo() <= 0) {
            throw new VentaInvalidaException("El sueldo ingresado debe ser mayor a 0");
        }
        vendedorRepo.save(vendedor);
    }

    public List<Vendedor> mostrarVendedores() {
        return vendedorRepo.findAll();
    }

    //Ventas //voy a tratar de ponerle mas onda a mis exceptions
    public void registrarVenta(Integer codigoProducto, Integer codigoVendedor) {

        Producto producto = productoRepo.findByCodigo(codigoProducto);
        Vendedor vendedor = vendedorRepo.findByCodigo(codigoVendedor);
        if (producto == null) {
            throw new ProductoNoEncontradoException("No existe un producto con ese codigo: " + codigoProducto);
        }
        if (vendedor == null) {
            throw new VendedorNoEncontradoException("No existe un vendedor con ese codigo: " + codigoVendedor);
        }
        Venta venta = new Venta(producto, vendedor);
        ventaRepo.save(venta);
    }

    public List<Venta> mostrarVentas() {
        return ventaRepo.findAll();
    }

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
            throw new ProductoNoEncontradoException("No existe producto con el código: " + codigo);
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
        if (cantidadVentas == 0) {
            throw new VentaInvalidaException("El vendedor no tiene ventas registradas");
        }
        return (cantidadVentas <= 2)
                ? totalVendido * 0.05
                : totalVendido * 0.10;
    }

}