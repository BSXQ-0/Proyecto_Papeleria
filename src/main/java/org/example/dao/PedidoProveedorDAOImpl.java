package org.example.dao;

import org.example.model.PedidoProveedor;
import org.example.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoProveedorDAOImpl implements PedidoProveedorDAO {

    @Override
    public List<PedidoProveedor> listarTodos() {
        List<PedidoProveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos_proveedor";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                PedidoProveedor pp = new PedidoProveedor();
                pp.setIdPedido(rs.getInt("id_pedido"));
                pp.setIdProveedor(rs.getInt("id_proveedor"));
                pp.setFecha(rs.getDate("fecha"));
                pp.setTotal(rs.getDouble("total"));
                lista.add(pp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public PedidoProveedor buscarPorId(int idPedido) {
        PedidoProveedor pedidoProveedor = null;
        String sql = "SELECT * FROM pedidos_proveedor WHERE id_pedido = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idPedido);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    pedidoProveedor = new PedidoProveedor();
                    pedidoProveedor.setIdPedido(rs.getInt("id_pedido"));
                    pedidoProveedor.setIdProveedor(rs.getInt("id_proveedor"));
                    pedidoProveedor.setFecha(rs.getDate("fecha"));
                    pedidoProveedor.setTotal(rs.getDouble("total"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidoProveedor;
    }

    @Override
    public boolean agregar(PedidoProveedor pedidoProveedor) {
        String sql = "INSERT INTO pedidos_proveedor (id_proveedor, fecha, total) VALUES (?, ?, ?)";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, pedidoProveedor.getIdProveedor());
            st.setDate(2, new java.sql.Date(pedidoProveedor.getFecha().getTime()));
            st.setDouble(3, pedidoProveedor.getTotal());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(PedidoProveedor pedidoProveedor) {
        String sql = "UPDATE pedidos_proveedor SET id_proveedor=?, fecha=?, total=? WHERE id_pedido=?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, pedidoProveedor.getIdProveedor());
            st.setDate(2, new java.sql.Date(pedidoProveedor.getFecha().getTime()));
            st.setDouble(3, pedidoProveedor.getTotal());
            st.setInt(4, pedidoProveedor.getIdPedido());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idPedido) {
        String sql = "DELETE FROM pedidos_proveedor WHERE id_pedido=?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idPedido);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PedidoProveedor> listarPorProveedor(int idProveedor) {
        List<PedidoProveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos_proveedor WHERE id_proveedor=?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, idProveedor);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    PedidoProveedor pp = new PedidoProveedor();
                    pp.setIdPedido(rs.getInt("id_pedido"));
                    pp.setIdProveedor(rs.getInt("id_proveedor"));
                    pp.setFecha(rs.getDate("fecha"));
                    pp.setTotal(rs.getDouble("total"));
                    lista.add(pp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}