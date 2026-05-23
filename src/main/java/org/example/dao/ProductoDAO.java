package org.example.dao;

import org.example.model.Producto;
import java.util.List;

public interface ProductoDAO {
    List<Producto> listarTodos();
    Producto buscarPorId(int idProducto);
    boolean agregar(Producto producto);
    boolean actualizar(Producto producto);
    boolean eliminar(int idProducto);
}