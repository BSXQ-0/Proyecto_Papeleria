package org.example.dao;

import org.example.model.DetalleVenta;
import java.util.List;

public interface DetalleVentaDAO {
    List<DetalleVenta> listarTodos();
    DetalleVenta buscarPorId(int idDetalleVenta);
    boolean agregar(DetalleVenta detalleVenta);
    boolean actualizar(DetalleVenta detalleVenta);
    boolean eliminar(int idDetalleVenta);
    List<DetalleVenta> listarPorVenta(int idVenta); // Para traer los detalles de una venta específica
}