package org.example.dao;

import org.example.model.Venta;
import org.example.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImpl implements VentaDAO {

    @Override
    public List<Venta> listarTodos() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setTotal(rs.getDouble("total"));
                lista.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Venta buscarPorId(int idVenta) {
        Venta venta = null;
        String sql = "SELECT * FROM ventas WHERE id_venta = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idVenta);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    venta = new Venta();
                    venta.setIdVenta(rs.getInt("id_venta"));
                    venta.setFecha(rs.getDate("fecha"));
                    venta.setTotal(rs.getDouble("total"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venta;
    }

    @Override
    public boolean agregar(Venta venta) {
        String sql = "INSERT INTO ventas (fecha, total) VALUES (?, ?)";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setDate(1, new java.sql.Date(venta.getFecha().getTime()));
            st.setDouble(2, venta.getTotal());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Venta venta) {
        String sql = "UPDATE ventas SET fecha = ?, total = ? WHERE id_venta = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setDate(1, new java.sql.Date(venta.getFecha().getTime()));
            st.setDouble(2, venta.getTotal());
            st.setInt(3, venta.getIdVenta());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idVenta) {
        String sql = "DELETE FROM ventas WHERE id_venta = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idVenta);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}