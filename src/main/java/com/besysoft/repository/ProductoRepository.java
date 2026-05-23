package com.besysoft.repository;

import com.besysoft.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {

    private final List<Producto> productos = new ArrayList<>();

    public void save(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> findAll() {
        return productos;
    }

    public Producto findByCodigo(Integer codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }
}