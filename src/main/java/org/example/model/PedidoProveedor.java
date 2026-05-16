package org.example.model;

import java.util.Date;

public class PedidoProveedor {
    private int idPedido;
    private int idProveedor;
    private Date fecha;
    private double total;

    public PedidoProveedor() {
    }

    public PedidoProveedor(int idPedido, int idProveedor, Date fecha, double total) {
        this.idPedido = idPedido;
        this.idProveedor = idProveedor;
        this.fecha = fecha;
        this.total = total;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PedidoProveedor{" +
                "idPedido=" + idPedido +
                ", idProveedor=" + idProveedor +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}