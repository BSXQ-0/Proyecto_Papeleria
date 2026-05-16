
package org.example.model;

public class Producto {
    private int idProducto;
    private String nombre;
    private String marca;
    private int cantidad;
    private double precioProveedor;
    private double porcentajeGanancia;
    private double precioVenta;
    private int idProveedor;

    public Producto() {}

    public Producto(int idProducto, String nombre, String marca, int cantidad, double precioProveedor, double porcentajeGanancia, double precioVenta, int idProveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.cantidad = cantidad;
        this.precioProveedor = precioProveedor;
        this.porcentajeGanancia = porcentajeGanancia;
        this.precioVenta = precioVenta;
        this.idProveedor = idProveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioProveedor() {
        return precioProveedor;
    }

    public void setPrecioProveedor(double precioProveedor) {
        this.precioProveedor = precioProveedor;
    }

    public double getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(double porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", cantidad=" + cantidad +
                ", precioProveedor=" + precioProveedor +
                ", porcentajeGanancia=" + porcentajeGanancia +
                ", precioVenta=" + precioVenta +
                ", idProveedor=" + idProveedor +
                '}';
    }
}
