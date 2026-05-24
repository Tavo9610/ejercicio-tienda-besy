# Tienda Besy

Sistema de gestion de ventas por consola desarrollado en Java puro para el desafio de tienda,
sin frameworks externos.

## Tecnologías

- Java 21
- Maven como gestor y build tool
- IDE utilizado IntelliJidea

## Estructura basica del proyecto

```
src/main/java/com/besysoft/
├── app/
│   └── Main.java                        # Punto de entrada, menú por consola
├── model/
│   ├── Producto.java                    # Entidad producto
│   ├── Vendedor.java                    # Entidad vendedor
│   └── Venta.java                       # Entidad venta (inmutable)
├── repository/
│   ├── ProductoRepository.java          # Persistencia en memoria de productos
│   ├── VendedorRepository.java          # Persistencia en memoria de vendedores
│   └── VentaRepository.java             # Persistencia en memoria de ventas
├── service/
│   └── TiendaService.java               # Lógica de negocio
└── exception/
    ├── ProductoNoEncontradoException.java
    ├── VendedorNoEncontradoException.java
    └── VentaInvalidaException.java
```
## Funcionalidades

| Opción | Descripción                      |
|--------|----------------------------------|
| 1      | Agregar producto                 |
| 2      | Agregar vendedor                 |
| 3      | Mostrar todos los productos      |
| 4      | Mostrar todos los vendedores     |
| 5      | Registrar venta                  |
| 6      | Mostrar todas las ventas         |
| 7      | Buscar producto por categoría    |
| 8      | Buscar producto por nombre       |
| 9      | Buscar producto por código       |
| 10     | Calcular comisión de un vendedor |
| 0      | Salir                            |

## Reglas de negocio

- El precio de un producto debe ser mayor a 0
- El sueldo de un vendedor debe ser mayor a 0
- El nombre del vendedor solo puede contener letras y espacios
- No se permiten códigos duplicados de productos (se valida)
- **Comisión:** 5% del total vendido si realizó hasta 2 ventas, 10% si realizó más de 2

## Diagrama de Entidad Relación (como lo represento en una ddbb relacional)

```
PRODUCTO                VENDEDOR
--------                --------
codigo (PK)             codigo (PK)
nombre                  nombre
precio                  sueldo
categoria
    \                      /
     \                    /
          VENTA
          -----
          id (PK)
          producto_codigo (FK)
          vendedor_codigo (FK)
```

- Un **Producto** puede aparecer en muchas ventas (1:N)
- Un **Vendedor** puede realizar muchas ventas (1:N)
- Cada **Venta** tiene exactamente un producto y un vendedor (la uso como intermedia para generar la relacion)

## Cómo ejecutarlo o correrlo

1. Clonar el repositorio
2. Abrir con IntelliJ IDEA u otro IDE compatible con Maven
3. Ejecutar la clase `Main.java`

> Los datos se almacenan en memoria ram. y al cerrar la aplicación los datos se pierden.

## Decisiones de diseño que tome

- **Repositories separados por entidad:** simula la estructura de un proyecto real con JPA, donde cada entidad tiene su propia interfaz de persistencia.
- **Venta inmutable:** no tiene setters porque una venta registrada representa un hecho ocurrido y no debe modificarse.
- **Excepciones personalizadas por tipo:** permite manejar cada error de forma específica y con mensajes claros.
- **Validaciones en el Service:** las reglas de negocio se centralizan en la capa de servicio y no en el main, respetando la separación de capas.