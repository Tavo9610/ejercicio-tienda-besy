package com.besysoft.repository;
import com.besysoft.model.Vendedor;
import java.util.ArrayList;
import java.util.List;

public class VendedorRepository {

    private final List<Vendedor> vendedores = new ArrayList<>();

    public void save(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public List<Vendedor> findAll() {
        return vendedores;
    }

    public Vendedor findByCodigo(Integer codigo) {
        for (Vendedor v : vendedores) {
            if (v.getCodigo().equals(codigo)) {
                return v;
            }
        }
        return null;
    }
}