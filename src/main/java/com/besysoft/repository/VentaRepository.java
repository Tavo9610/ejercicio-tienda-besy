package com.besysoft.repository;

import com.besysoft.model.Venta;
import java.util.ArrayList;
import java.util.List;

public class VentaRepository {

    private final List<Venta> ventas = new ArrayList<>();

    public void save(Venta venta) {
        ventas.add(venta);
    }

    public List<Venta> findAll() {
        return ventas;
    }
}