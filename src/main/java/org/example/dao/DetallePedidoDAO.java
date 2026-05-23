package org.example.dao;

import org.example.model.DetallePedido;
import java.util.List;

public interface DetallePedidoDAO {
    List<DetallePedido> listarTodos();
    DetallePedido buscarPorId(int idDetalle);
    boolean agregar(DetallePedido detallePedido);
    boolean actualizar(DetallePedido detallePedido);
    boolean eliminar(int idDetalle);
    List<DetallePedido> listarPorPedido(int idPedido); // Para obtener detalles de un pedido específico
}