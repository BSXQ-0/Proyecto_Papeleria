package org.example.dao;

import org.example.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    private final Connection connection;

    public ProductoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Producto p) {
        String sql = "INSERT INTO productos VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, p.getIdProducto());
            st.setString(2, p.getNombre());
            st.setString(3, p.getMarca());
            st.setInt(4, p.getCantidad());
            st.setDouble(5, p.getPrecioProveedor());
            st.setDouble(6, p.getPorcentajeGanancia());
            st.setDouble(7, p.getPrecioVenta());
            st.setInt(8, p.getIdProveedor());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto leer(int id) {
        String sql = "SELECT * FROM productos WHERE id_producto=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setMarca(rs.getString("marca"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setPrecioProveedor(rs.getDouble("precio_proveedor"));
                p.setPorcentajeGanancia(rs.getDouble("porcentaje_ganancia"));
                p.setPrecioVenta(rs.getDouble("precio_venta"));
                p.setIdProveedor(rs.getInt("id_proveedor"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Producto p) {
        String sql = "UPDATE productos SET nombre=?, marca=?, cantidad=?, precio_proveedor=?, porcentaje_ganancia=?, precio_venta=?, id_proveedor=? WHERE id_producto=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, p.getNombre());
            st.setString(2, p.getMarca());
            st.setInt(3, p.getCantidad());
            st.setDouble(4, p.getPrecioProveedor());
            st.setDouble(5, p.getPorcentajeGanancia());
            st.setDouble(6, p.getPrecioVenta());
            st.setInt(7, p.getIdProveedor());
            st.setInt(8, p.getIdProducto());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id_producto=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

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
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
