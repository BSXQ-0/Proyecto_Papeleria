package org.example.dao;

import org.example.model.DetalleVenta;
import org.example.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAOImpl implements DetalleVentaDAO {

    @Override
    public List<DetalleVenta> listarTodos() {
        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                DetalleVenta dv = new DetalleVenta();
                dv.setIdDetalleVenta(rs.getInt("id_detalle_venta"));
                dv.setIdVenta(rs.getInt("id_venta"));
                dv.setIdProducto(rs.getInt("id_producto"));
                dv.setCantidad(rs.getInt("cantidad"));
                dv.setPrecioUnitario(rs.getDouble("precio_unitario"));
                dv.setSubtotal(rs.getDouble("subtotal"));
                lista.add(dv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public DetalleVenta buscarPorId(int idDetalleVenta) {
        DetalleVenta detalleVenta = null;
        String sql = "SELECT * FROM detalle_venta WHERE id_detalle_venta = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idDetalleVenta);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    detalleVenta = new DetalleVenta();
                    detalleVenta.setIdDetalleVenta(rs.getInt("id_detalle_venta"));
                    detalleVenta.setIdVenta(rs.getInt("id_venta"));
                    detalleVenta.setIdProducto(rs.getInt("id_producto"));
                    detalleVenta.setCantidad(rs.getInt("cantidad"));
                    detalleVenta.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    detalleVenta.setSubtotal(rs.getDouble("subtotal"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleVenta;
    }

    @Override
    public boolean agregar(DetalleVenta detalleVenta) {
        String sql = "INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, detalleVenta.getIdVenta());
            st.setInt(2, detalleVenta.getIdProducto());
            st.setInt(3, detalleVenta.getCantidad());
            st.setDouble(4, detalleVenta.getPrecioUnitario());
            st.setDouble(5, detalleVenta.getSubtotal());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(DetalleVenta detalleVenta) {
        String sql = "UPDATE detalle_venta SET id_venta=?, id_producto=?, cantidad=?, precio_unitario=?, subtotal=? WHERE id_detalle_venta=?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, detalleVenta.getIdVenta());
            st.setInt(2, detalleVenta.getIdProducto());
            st.setInt(3, detalleVenta.getCantidad());
            st.setDouble(4, detalleVenta.getPrecioUnitario());
            st.setDouble(5, detalleVenta.getSubtotal());
            st.setInt(6, detalleVenta.getIdDetalleVenta());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idDetalleVenta) {
        String sql = "DELETE FROM detalle_venta WHERE id_detalle_venta=?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idDetalleVenta);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DetalleVenta> listarPorVenta(int idVenta) {
        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta WHERE id_venta = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idVenta);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    DetalleVenta dv = new DetalleVenta();
                    dv.setIdDetalleVenta(rs.getInt("id_detalle_venta"));
                    dv.setIdVenta(rs.getInt("id_venta"));
                    dv.setIdProducto(rs.getInt("id_producto"));
                    dv.setCantidad(rs.getInt("cantidad"));
                    dv.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    dv.setSubtotal(rs.getDouble("subtotal"));
                    lista.add(dv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}