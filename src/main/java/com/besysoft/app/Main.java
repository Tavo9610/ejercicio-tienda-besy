package com.besysoft.app;
import com.besysoft.exception.ProductoNoEncontradoException;
import com.besysoft.exception.VendedorNoEncontradoException;
import com.besysoft.exception.VentaInvalidaException;
import com.besysoft.model.Producto;
import com.besysoft.model.Vendedor;
import com.besysoft.service.TiendaService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TiendaService service = new TiendaService();
        Scanner entrada = new Scanner(System.in);

        int opcion = -1;

        do {
            System.out.println("\n===== TIENDA BESYSOFT =====");
            System.out.println("1 - Agregar producto");
            System.out.println("2 - Agregar vendedor");
            System.out.println("3 - Mostrar productos");
            System.out.println("4 - Mostrar vendedores");
            System.out.println("5 - Registrar venta");
            System.out.println("6 - Mostrar ventas");
            System.out.println("7 - Buscar producto por categoría");
            System.out.println("8 - Buscar producto por nombre");
            System.out.println("9 - Buscar producto por código");
            System.out.println("10 - Calcular comisión");
            System.out.println("0 - Salir");
            try {
                /*
            atrapo en el main las excepciones
            (se que no es lo correcto, pero lo hice para que en caso de que se
            lanze una exception la atrape y no rompa el flujo de ejecucion del codigo y se detenga
            */
                opcion = entrada.nextInt();
                entrada.nextLine();
                switch (opcion) {
                    case 1 -> {
                        try {
                            System.out.println("Código:");
                            int codigo = entrada.nextInt();
                            entrada.nextLine();
                            System.out.println("Nombre:");
                            String nombre = entrada.nextLine();
                            System.out.println("Precio:");
                            double precio = entrada.nextDouble();
                            entrada.nextLine();
                            System.out.println("Categoría:");
                            String categoria = entrada.nextLine();
                            service.agregarProducto(new Producto(codigo, nombre, precio, categoria));
                            System.out.println("Producto agregado correctamente");
                        } catch (VentaInvalidaException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        System.out.println("Código:");
                        int codigo = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Nombre:");
                        String nombre = entrada.nextLine();
                        System.out.println("Sueldo:");
                        double sueldo = entrada.nextDouble();
                        service.agregarVendedor(new Vendedor(codigo, nombre, sueldo));
                        System.out.println("Vendedor agregado correctamente");
                    }
                    case 3 -> {
                        List<Producto> productos = service.mostrarProductos();
                        if (productos.isEmpty()) {
                            System.out.println("No hay productos");
                        } else {
                            productos.forEach(System.out::println);
                        }
                    }
                    case 4 -> {
                        List<Vendedor> vendedores = service.mostrarVendedores();
                        if (vendedores.isEmpty()) {
                            System.out.println("No hay vendedores");
                        } else {
                            vendedores.forEach(System.out::println);
                        }
                    }
                    case 5 -> {
                        try {
                            System.out.println("Código producto:");
                            int codProd = entrada.nextInt();
                            System.out.println("Código vendedor:");
                            int codVend = entrada.nextInt();
                            service.registrarVenta(codProd, codVend);
                            System.out.println("Venta registrada correctamente");
                        } catch (ProductoNoEncontradoException | VendedorNoEncontradoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    case 6 -> {
                        var ventas = service.mostrarVentas();
                        if (ventas.isEmpty()) {
                            System.out.println("No hay ventas");
                        } else {
                            ventas.forEach(System.out::println);
                        }
                    }
                    case 7 -> {
                        System.out.println("Categoría:");
                        String categoria = entrada.nextLine();
                        var resultado = service.buscarProductoPorCategoria(categoria);
                        if (resultado.isEmpty()) {
                            System.out.println("No se encontraron productos");
                        } else {
                            resultado.forEach(System.out::println);
                        }
                    }
                    case 8 -> {
                        System.out.println("Nombre:");
                        String nombre = entrada.nextLine();
                        var resultado = service.buscarProductoPorNombre(nombre);
                        if (resultado.isEmpty()) {
                            System.out.println("No se encontraron productos");
                        } else {
                            resultado.forEach(System.out::println);
                        }
                    }
                    case 9 -> {
                        try {
                            System.out.println("Código:");
                            int codigo = entrada.nextInt();

                            Producto producto = service.buscarProductoPorCodigo(codigo);

                            System.out.println(producto);

                        } catch (ProductoNoEncontradoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    case 10 -> {
                        try {
                            System.out.println("Código vendedor:");
                            int codigoComision = entrada.nextInt();
                            service.mostrarResumenComision(codigoComision);

                        } catch (VentaInvalidaException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    case 0 -> System.out.println("Saliendo del sistema...");

                    default -> System.out.println("Opción inválida");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número válido y no texto");
                entrada.nextLine();
                opcion = -1;
            }
        } while (opcion != 0);

        entrada.close(); //esto para cerrar el Scanner y dejar de consumir recursos
    }
}