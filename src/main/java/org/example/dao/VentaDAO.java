package org.example.dao;

import org.example.model.Venta;
import java.util.List;

public interface VentaDAO {
    List<Venta> listarTodos();
    Venta buscarPorId(int idVenta);
    boolean agregar(Venta venta);
    boolean actualizar(Venta venta);
    boolean eliminar(int idVenta);
}