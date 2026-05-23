package org.example.dao;

import org.example.model.Producto;
import org.example.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public List<Producto> listarTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setMarca(rs.getString("marca"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setPrecioProveedor(rs.getDouble("precio_proveedor"));
                p.setPorcentajeGanancia(rs.getDouble("porcentaje_ganancia"));
                p.setPrecioVenta(rs.getDouble("precio_venta"));
                p.setIdProveedor(rs.getInt("id_proveedor"));
                productos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public Producto buscarPorId(int idProducto) {
        Producto producto = null;
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProducto);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setIdProducto(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setMarca(rs.getString("marca"));
                    producto.setCantidad(rs.getInt("cantidad"));
                    producto.setPrecioProveedor(rs.getDouble("precio_proveedor"));
                    producto.setPorcentajeGanancia(rs.getDouble("porcentaje_ganancia"));
                    producto.setPrecioVenta(rs.getDouble("precio_venta"));
                    producto.setIdProveedor(rs.getInt("id_proveedor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public boolean agregar(Producto producto) {
        String sql = "INSERT INTO productos (nombre, marca, cantidad, precio_proveedor, porcentaje_ganancia, precio_venta, id_proveedor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getMarca());
            statement.setInt(3, producto.getCantidad());
            statement.setDouble(4, producto.getPrecioProveedor());
            statement.setDouble(5, producto.getPorcentajeGanancia());
            statement.setDouble(6, producto.getPrecioVenta());
            statement.setInt(7, producto.getIdProveedor());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, marca = ?, cantidad = ?, precio_proveedor = ?, porcentaje_ganancia = ?, precio_venta = ?, id_proveedor = ? WHERE id_producto = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getMarca());
            statement.setInt(3, producto.getCantidad());
            statement.setDouble(4, producto.getPrecioProveedor());
            statement.setDouble(5, producto.getPorcentajeGanancia());
            statement.setDouble(6, producto.getPrecioVenta());
            statement.setInt(7, producto.getIdProveedor());
            statement.setInt(8, producto.getIdProducto());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProducto);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}