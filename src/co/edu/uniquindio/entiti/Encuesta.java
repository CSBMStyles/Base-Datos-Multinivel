/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.entiti;

import java.sql.Date;

/**
 *
 * @author Cristian
 */
public class Encuesta {

    private Integer id;
    private Date fechaVenta;
    private Double totalVenta;
    private Integer vendedorId;
    private Integer estadoId;
    private Integer pagoId;
    private Integer clienteCedula;

    public Encuesta() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Integer getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Integer vendedorId) {
        this.vendedorId = vendedorId;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public Integer getPagoId() {
        return pagoId;
    }

    public void setPagoId(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public Integer getClienteCedula() {
        return clienteCedula;
    }

    public void setClienteCedula(Integer clienteCedula) {
        this.clienteCedula = clienteCedula;
    }

    @Override
    public String toString() {
        return "Encuesta{" + "id=" + id + ", fechaVenta=" + fechaVenta + ", totalVenta=" + totalVenta + ", vendedorId=" + vendedorId + ", estadoId=" + estadoId + ", pagoId=" + pagoId + ", clienteCedula=" + clienteCedula + '}';
    }

}
