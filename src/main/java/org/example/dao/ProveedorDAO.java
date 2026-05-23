package org.example.dao;

import org.example.model.Proveedor;
import java.util.List;

public interface ProveedorDAO {
    List<Proveedor> listarTodos();
    Proveedor buscarPorId(int idProveedor);
    boolean agregar(Proveedor proveedor);
    boolean actualizar(Proveedor proveedor);
    boolean eliminar(int idProveedor);
}