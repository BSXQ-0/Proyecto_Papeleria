package org.example;

import org.example.dao.*;
import org.example.model.*;
import org.example.util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = ConexionBD.obtenerConexion()) {
            Scanner scanner = new Scanner(System.in);

            ProveedorDAO proveedorDAO = new ProveedorDAOImpl();
            ProductoDAO productoDAO = new ProductoDAOImpl();
            PedidoProveedorDAO pedidoProveedorDAO = new PedidoProveedorDAOImpl();
            DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl();
            VentaDAO ventaDAO = new VentaDAOImpl();
            DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAOImpl();

            int opcion;
            do {
                System.out.println("\n      MENÚ PAPELERÍA");
                System.out.println("1. Proveedores");
                System.out.println("2. Productos");
                System.out.println("3. Pedidos a Proveedor");
                System.out.println("4. Detalles de Pedido");
                System.out.println("5. Ventas");
                System.out.println("6. Detalles de Venta");
                System.out.println("7. Salir");
                System.out.print("Opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1: menuProveedores(proveedorDAO, scanner); break;
                    case 2: menuProductos(productoDAO, scanner); break;
                    case 3: menuPedidosProveedor(pedidoProveedorDAO, scanner); break;
                    case 4: menuDetallesPedido(detallePedidoDAO, scanner); break;
                    case 5: menuVentas(ventaDAO, scanner); break;
                    case 6: menuDetallesVenta(detalleVentaDAO, scanner); break;
                }
            } while (opcion != 7);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void menuProveedores(ProveedorDAO dao, Scanner scanner) {
        int op;
        do {
            System.out.println("\n      CRUD PROVEEDORES");
            System.out.println("1. Registrar");
            System.out.println("2. Leer");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar Todos");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            op = scanner.nextInt(); scanner.nextLine();

            switch (op) {
                case 1:
                    Proveedor proveedor = new Proveedor();
                    System.out.print("Nombre: ");
                    proveedor.setNombre(scanner.nextLine());
                    System.out.print("Contacto: ");
                    proveedor.setContacto(scanner.nextLine());
                    System.out.println(dao.agregar(proveedor));
                    break;
                case 2:
                    System.out.print("ID proveedor: ");
                    Proveedor l = dao.buscarPorId(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("ID proveedor: ");
                    int idupt = scanner.nextInt(); scanner.nextLine();
                    Proveedor up = dao.buscarPorId(idupt);
                    if (up != null) {
                        System.out.print("Nuevo nombre [" + up.getNombre() + "]: ");
                        String nuevoNom = scanner.nextLine();
                        System.out.print("Nuevo contacto [" + up.getContacto() + "]: ");
                        String newCon = scanner.nextLine();
                    } else System.out.println("No existe.");
                    break;
                case 4:
                    System.out.print("ID eliminar: ");
                    scanner.nextLine(); break;
                case 5:
                    dao.listarTodos().forEach(System.out::println); break;
            }
        } while (op != 6);
    }

    private static void menuProductos(ProductoDAO dao, Scanner scanner) {
        int op;
        do {
            System.out.println("\n      CRUD PRODUCTOS");
            System.out.println("1. Registrar");
            System.out.println("2. Leer");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar Todos");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            op = scanner.nextInt(); scanner.nextLine();
            switch (op) {
                case 1:
                    Producto p = new Producto();
                    System.out.print("Nombre: ");
                    p.setNombre(scanner.nextLine());
                    System.out.print("Marca: ");
                    p.setMarca(scanner.nextLine());
                    System.out.print("Cantidad: ");
                    p.setCantidad(scanner.nextInt());
                    System.out.print("Precio proveedor: ");
                    p.setPrecioProveedor(scanner.nextDouble());
                    System.out.print("Porcentaje ganancia: ");
                    p.setPorcentajeGanancia(scanner.nextDouble());
                    System.out.print("Precio venta: ");
                    p.setPrecioVenta(scanner.nextDouble());
                    System.out.print("ID proveedor: ");
                    p.setIdProveedor(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.print("ID producto: ");
                    Producto pl = dao.buscarPorId(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("ID producto: "); int idprod = scanner.nextInt(); scanner.nextLine();
                    Producto pup = dao.buscarPorId(idprod);
                    if (pup != null) {
                        System.out.print("Nuevo nombre [" + pup.getNombre() + "]: ");
                        String nn = scanner.nextLine();
                        System.out.print("Nueva marca [" + pup.getMarca() + "]: ");
                        String nm = scanner.nextLine();
                        System.out.print("Nueva cantidad [" + pup.getCantidad() + "]: ");
                        String nc = scanner.nextLine();
                        System.out.print("Nuevo precio proveedor [" + pup.getPrecioProveedor() + "]: ");
                        String np = scanner.nextLine();
                        System.out.print("Nuevo porcentaje ganancia [" + pup.getPorcentajeGanancia() + "]: ");
                        String ng = scanner.nextLine();
                        System.out.print("Nuevo precio venta [" + pup.getPrecioVenta() + "]: ");
                        String nv = scanner.nextLine();
                        System.out.print("Nuevo ID proveedor [" + pup.getIdProveedor() + "]: ");
                        String nip = scanner.nextLine();
                        System.out.println(dao.actualizar(pup));
                    } else System.out.println("No existe.");
                    break;
                case 4:
                    System.out.print("ID eliminar: "); System.out.println(dao.eliminar(scanner.nextInt()) ? "✔ Eliminado" : "✗ Error");
                    scanner.nextLine(); break;
                case 5:
                    dao.listarTodos().forEach(System.out::println); break;
            }
        } while (op != 6);
    }

    private static void menuPedidosProveedor(PedidoProveedorDAO dao, Scanner scanner) {
        int op;
        do {
            System.out.println("\n      CRUD PEDIDOS A PROVEEDOR");
            System.out.println("1. Registrar");
            System.out.println("2. Leer");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar Todos");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            op = scanner.nextInt(); scanner.nextLine();
            switch (op) {
                case 1:
                    PedidoProveedor pedidoP = new PedidoProveedor();
                    System.out.print("ID Proveedor: ");
                    pedidoP.setIdProveedor(scanner.nextInt());
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    pedidoP.setFecha(java.sql.Date.valueOf(scanner.next()));
                    System.out.print("Total: ");
                    pedidoP.setTotal(scanner.nextDouble());
                    scanner.nextLine();
                    System.out.println(dao.agregar(pedidoP));
                    break;
                case 2:
                    System.out.print("ID pedido: ");
                    PedidoProveedor pedl = dao.buscarPorId(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("ID pedido: ");
                    int idpp = scanner.nextInt();
                    scanner.nextLine();
                    PedidoProveedor pp = dao.buscarPorId(idpp);
                    if (pp != null) {
                        System.out.print("Nuevo ID proveedor [" + pp.getIdProveedor() + "]: ");
                        String newp = scanner.nextLine();
                        System.out.print("Nueva fecha [" + pp.getFecha() + "]: ");
                        String nf = scanner.nextLine();
                        System.out.print("Nuevo total [" + pp.getTotal() + "]: ");
                        String nt = scanner.nextLine();
                        System.out.println(dao.actualizar(pp));
                    } else System.out.println("No existe.");
                    break;
                case 4:
                    System.out.print("ID eliminar: ");
                    System.out.println(dao.eliminar(scanner.nextInt()));
                    break;
                case 5:
                    dao.listarTodos().forEach(System.out::println);
                    break;
            }
        } while (op != 6);
    }


    private static void menuDetallesPedido(DetallePedidoDAO dao, Scanner scanner) {
        int op;
        do {
            System.out.println("\n      CRUD DETALLE PEDIDO");
            System.out.println("1. Registrar");
            System.out.println("2. Leer");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar Todos");
            System.out.println("6. Listar por Pedido");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            op = scanner.nextInt(); scanner.nextLine();
            switch (op) {
                case 1:
                    DetallePedido dp = new DetallePedido();
                    System.out.print("ID Pedido: ");
                    dp.setIdPedido(scanner.nextInt());
                    System.out.print("ID Producto: ");
                    dp.setIdProducto(scanner.nextInt());
                    System.out.print("Cantidad: ");
                    dp.setCantidad(scanner.nextInt());
                    System.out.print("Precio unitario: ");
                    dp.setPrecioUnitario(scanner.nextDouble());
                    scanner.nextLine();
                    System.out.println(dao.agregar(dp));
                    break;
                case 2:
                    System.out.print("ID detalle: ");
                    DetallePedido dpl = dao.buscarPorId(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("ID detalle: ");
                    int iddp = scanner.nextInt();
                    scanner.nextLine();
                    DetallePedido dpu = dao.buscarPorId(iddp);
                    if (dpu != null) {
                        System.out.print("Nuevo ID pedido [" + dpu.getIdPedido() + "]: ");
                        String nidp = scanner.nextLine();
                        System.out.print("Nuevo ID producto [" + dpu.getIdProducto() + "]: ");
                        String nidpr = scanner.nextLine();
                        System.out.print("Nueva cantidad [" + dpu.getCantidad() + "]: ");
                        String nc = scanner.nextLine();
                        System.out.print("Nuevo precio unitario [" + dpu.getPrecioUnitario() + "]: ");
                        String npu = scanner.nextLine();
                        System.out.println(dao.actualizar(dpu) ? "✔ Actualizado" : "✗ Error");
                    } else System.out.println("No existe.");
                    break;
                case 4:
                    System.out.print("ID eliminar: ");
                    System.out.println(dao.eliminar(scanner.nextInt()));
                    break;
                case 5:
                    dao.listarTodos().forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("ID pedido: ");
                    dao.listarPorPedido(scanner.nextInt()).forEach(System.out::println);
                    break;
            }
        } while (op != 7);
    }

    private static void menuVentas(VentaDAO dao, Scanner scanner) {
        int op;
        do {
            System.out.println("\n      CRUD VENTAS");
            System.out.println("1. Registrar");
            System.out.println("2. Leer");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar Todos");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            op = scanner.nextInt(); scanner.nextLine();
            switch (op) {
                case 1:
                    Venta v = new Venta();
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    v.setFecha(java.sql.Date.valueOf(scanner.next()));
                    System.out.print("Total: ");
                    v.setTotal(scanner.nextDouble());
                    scanner.nextLine();
                    System.out.println(dao.agregar(v));
                    break;
                case 2:
                    System.out.print("ID venta: ");
                    Venta vl = dao.buscarPorId(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("ID venta: ");
                    int idv = scanner.nextInt();
                    scanner.nextLine();
                    Venta vu = dao.buscarPorId(idv);
                    if (vu != null) {
                        System.out.print("Nueva fecha [" + vu.getFecha() + "]: ");
                        String nf = scanner.nextLine();
                        System.out.print("Nuevo total [" + vu.getTotal() + "]: ");
                        String nt = scanner.nextLine();
                        System.out.println(dao.actualizar(vu) ? "✔ Actualizado" : "✗ Error");
                    } else System.out.println("No existe.");
                    break;
                case 4:
                    System.out.print("ID eliminar: ");
                    System.out.println(dao.eliminar(scanner.nextInt()));
                    break;
                case 5:
                    dao.listarTodos().forEach(System.out::println);
                    break;
            }
        } while (op != 6);
    }

    private static void menuDetallesVenta(DetalleVentaDAO dao, Scanner scanner) {
        int op;
        do {
            System.out.println("\n      CRUD DETALLE VENTA");
            System.out.println("1. Registrar");
            System.out.println("2. Leer");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar Todos");
            System.out.println("6. Listar por Venta");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            op = scanner.nextInt(); scanner.nextLine();
            switch (op) {
                case 1:
                    DetalleVenta dv = new DetalleVenta();
                    System.out.print("ID Venta: ");
                    dv.setIdVenta(scanner.nextInt());
                    System.out.print("ID Producto: ");
                    dv.setIdProducto(scanner.nextInt());
                    System.out.print("Cantidad: ");
                    dv.setCantidad(scanner.nextInt());
                    System.out.print("Precio unitario: ");
                    dv.setPrecioUnitario(scanner.nextDouble());
                    System.out.print("Subtotal: ");
                    dv.setSubtotal(scanner.nextDouble());
                    scanner.nextLine();
                    System.out.println(dao.agregar(dv));
                    break;
                case 2:
                    System.out.print("ID detalle: ");
                    DetalleVenta dvl = dao.buscarPorId(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("ID detalle: ");
                    int iddv = scanner.nextInt();
                    scanner.nextLine();
                    DetalleVenta dvu = dao.buscarPorId(iddv);
                    if (dvu != null) {
                        System.out.print("Nuevo ID venta [" + dvu.getIdVenta() + "]: ");
                        String nidv = scanner.nextLine();
                        System.out.print("Nuevo ID producto [" + dvu.getIdProducto() + "]: ");
                        String nidpr = scanner.nextLine();
                        System.out.print("Nueva cantidad [" + dvu.getCantidad() + "]: ");
                        String nc = scanner.nextLine();
                        System.out.print("Nuevo precio unitario [" + dvu.getPrecioUnitario() + "]: ");
                        String npu = scanner.nextLine();
                        System.out.print("Nuevo subtotal [" + dvu.getSubtotal() + "]: ");
                        String ns = scanner.nextLine();
                        System.out.println(dao.actualizar(dvu));
                    } else System.out.println("No existe.");
                    break;
                case 4:
                    System.out.print("ID eliminar: ");
                    System.out.println(dao.eliminar(scanner.nextInt()));
                    break;
                case 5:
                    dao.listarTodos().forEach(System.out::println); break;
                case 6:
                    System.out.print("ID venta: ");
                    dao.listarPorVenta(scanner.nextInt()).forEach(System.out::println);
                    break;
            }
        } while (op != 7);
    }
}