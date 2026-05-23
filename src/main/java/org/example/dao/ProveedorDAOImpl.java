package org.example.dao;

import org.example.model.Proveedor;
import org.example.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAOImpl implements ProveedorDAO {

    @Override
    public List<Proveedor> listarTodos() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("id_proveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setContacto(rs.getString("contacto"));
                // Agrega aquí otros campos si tu modelo de Proveedor los tiene
                lista.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Proveedor buscarPorId(int idProveedor) {
        Proveedor proveedor = null;
        String sql = "SELECT * FROM proveedores WHERE id_proveedor = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idProveedor);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    proveedor = new Proveedor();
                    proveedor.setIdProveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre(rs.getString("nombre"));
                    proveedor.setContacto(rs.getString("contacto"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedor;
    }

    @Override
    public boolean agregar(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (nombre, contacto) VALUES (?, ?)";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, proveedor.getNombre());
            st.setString(2, proveedor.getContacto());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Proveedor proveedor) {
        String sql = "UPDATE proveedores SET nombre = ?, contacto = ? WHERE id_proveedor = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, proveedor.getNombre());
            st.setString(2, proveedor.getContacto());
            st.setInt(3, proveedor.getIdProveedor());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idProveedor) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idProveedor);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}