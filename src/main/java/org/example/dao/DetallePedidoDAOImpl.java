package org.example.dao;

import org.example.model.DetallePedido;
import org.example.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAOImpl implements DetallePedidoDAO {

    @Override
    public List<DetallePedido> listarTodos() {
        List<DetallePedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedido";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                DetallePedido dp = new DetallePedido();
                dp.setIdDetalle(rs.getInt("id_detalle"));
                dp.setIdPedido(rs.getInt("id_pedido"));
                dp.setIdProducto(rs.getInt("id_producto"));
                dp.setCantidad(rs.getInt("cantidad"));
                dp.setPrecioUnitario(rs.getDouble("precio_unitario"));
                lista.add(dp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public DetallePedido buscarPorId(int idDetalle) {
        DetallePedido detallePedido = null;
        String sql = "SELECT * FROM detalle_pedido WHERE id_detalle = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)
        ) {
            st.setInt(1, idDetalle);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                detallePedido = new DetallePedido();
                detallePedido.setIdDetalle(rs.getInt("id_detalle"));
                detallePedido.setIdPedido(rs.getInt("id_pedido"));
                detallePedido.setIdProducto(rs.getInt("id_producto"));
                detallePedido.setCantidad(rs.getInt("cantidad"));
                detallePedido.setPrecioUnitario(rs.getDouble("precio_unitario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallePedido;
    }

    @Override
    public boolean agregar(DetallePedido detallePedido) {
        String sql = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, detallePedido.getIdPedido());
            st.setInt(2, detallePedido.getIdProducto());
            st.setInt(3, detallePedido.getCantidad());
            st.setDouble(4, detallePedido.getPrecioUnitario());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(DetallePedido detallePedido) {
        String sql = "UPDATE detalle_pedido SET id_pedido=?, id_producto=?, cantidad=?, precio_unitario=? WHERE id_detalle=?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, detallePedido.getIdPedido());
            st.setInt(2, detallePedido.getIdProducto());
            st.setInt(3, detallePedido.getCantidad());
            st.setDouble(4, detallePedido.getPrecioUnitario());
            st.setInt(5, detallePedido.getIdDetalle());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idDetalle) {
        String sql = "DELETE FROM detalle_pedido WHERE id_detalle=?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idDetalle);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DetallePedido> listarPorPedido(int idPedido) {
        List<DetallePedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedido WHERE id_pedido=?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idPedido);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                DetallePedido dp = new DetallePedido();
                dp.setIdDetalle(rs.getInt("id_detalle"));
                dp.setIdPedido(rs.getInt("id_pedido"));
                dp.setIdProducto(rs.getInt("id_producto"));
                dp.setCantidad(rs.getInt("cantidad"));
                dp.setPrecioUnitario(rs.getDouble("precio_unitario"));
                lista.add(dp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}