package com.besysoft.app;
import com.besysoft.model.Producto;
import com.besysoft.model.Vendedor;
import com.besysoft.service.TiendaService;
import com.besysoft.exception.DatoInvalidoException;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        TiendaService service = new TiendaService(); //creo un obj de la clase TiendaService
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {

            System.out.println("\n===== TIENDA BESYSOFT =====");
            System.out.println("1 - Agregar producto");
            System.out.println("2 - Agregar vendedor");
            System.out.println("3 - Mostrar productos");
            System.out.println("4 - Mostrar vendedores");
            System.out.println("5 - Registrar Venta");
            System.out.println("6 - Mostrar ventas");
            System.out.println("7 - Buscar producto por categoria");
            System.out.println("8 - Buscar producto por nombre");
            System.out.println("9 - Buscar producto por codigo");
            System.out.println("10- Calcular comision");
            System.out.println("0 - Salir");

            opcion = entrada.nextInt();

            switch (opcion){

                case 1:
                    System.out.println("Ingrese código:");
                    Integer codigoProducto = entrada.nextInt();

                    entrada.nextLine();

                    System.out.println("Ingrese nombre:");
                    String nombreProducto = entrada.nextLine();

                    System.out.println("Ingrese precio:");
                    Double precioProducto = entrada.nextDouble();

                    entrada.nextLine();

                    System.out.println("Ingrese categoría:");
                    String categoriaProducto = entrada.nextLine();

                    Producto producto = new Producto(
                            codigoProducto,
                            nombreProducto,
                            precioProducto,
                            categoriaProducto
                    );

                    service.agregarProducto(producto);
                    break;

                case 2:
                    System.out.println("Ingrese código:");
                    Integer codigoVendedor = entrada.nextInt();

                    entrada.nextLine();

                    System.out.println("Ingrese nombre:");
                    String nombreVendedor = entrada.nextLine();

                    System.out.println("Ingrese sueldo:");
                    Double sueldoVendedor = entrada.nextDouble();

                    Vendedor vendedor = new Vendedor(
                            codigoVendedor,
                            nombreVendedor,
                            sueldoVendedor
                    );

                    service.agregarVendedor(vendedor);
                    break;

                case 3:
                    service.mostrarProductos();
                    break;

                case 4:
                    service.mostrarVendedores();
                    break;

                case 5:
                    System.out.println("Ingrese código producto:");
                    Integer codigoProductoVenta = entrada.nextInt();

                    System.out.println("Ingrese código vendedor:");
                    Integer codigoVendedorVenta = entrada.nextInt();

                    service.registrarVenta(
                            codigoProductoVenta,
                            codigoVendedorVenta
                    );
                    break;

                case 6:
                    service.mostrarVentas();
                    break;

                case 7:
                    entrada.nextLine();
                    System.out.println("Ingrese categoria: ");
                    String categoriaBusqueda = entrada.nextLine();
                    service.buscarProductoPorCategoria(categoriaBusqueda);
                    break;
                case 8:

                    entrada.nextLine();
                    System.out.println("Ingrese nombre del producto: ");
                    String nombreBusqueda = entrada.nextLine();
                    service.buscarProductoPorNombre(nombreBusqueda);
                    break;

                case 9:

                    System.out.println("Ingrese codigo de producto: ");
                    Integer codigoBusqueda = entrada.nextInt();
                    service.buscarProductoPorCodigo(codigoBusqueda);
                    break;

                case 10:

                    System.out.println("Ingrese codigo del vendedor: ");
                    Integer codigoComision = entrada.nextInt();
                    service.calcularComision(codigoComision);
                    break;

                case 0:

                    System.out.println("Saliendo del sistema...");
                    break;


                default:

                    System.out.println("Opción no valida");

            }

        } while(opcion != 0);

        entrada.close(); //cerre el recurso Scanner

    }

}
