/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.entiti;

/**
 *
 * @author Cristian
 */
public class DetalleFactura {

    private Integer productoId;
    private Integer ventaId;
    private Integer cantidad;
    private Double subtotal; //En la base esta NUMBER, afortunadamente este tipo de dato lo soporta, y si cambio en la base de datos


    public DetalleFactura() {

    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleFactura{" + "productoId=" + productoId + ", ventaId=" + ventaId + ", cantidad=" + cantidad + ", subtotal=" + subtotal + '}';
    }

    

}
