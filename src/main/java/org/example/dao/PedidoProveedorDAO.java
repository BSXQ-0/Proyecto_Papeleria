package org.example.dao;

import org.example.model.PedidoProveedor;
import java.util.List;

public interface PedidoProveedorDAO {
    List<PedidoProveedor> listarTodos();
    PedidoProveedor buscarPorId(int idPedido);
    boolean agregar(PedidoProveedor pedidoProveedor);
    boolean actualizar(PedidoProveedor pedidoProveedor);
    boolean eliminar(int idPedido);
    List<PedidoProveedor> listarPorProveedor(int idProveedor); // útil si quieres ver pedidos de un proveedor específico
}