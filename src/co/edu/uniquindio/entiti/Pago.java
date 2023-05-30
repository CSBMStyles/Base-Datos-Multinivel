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
public class Pago {

    private Integer id;
    private Double monto; //En la base esta NUMBER, afortunadamente este tipo de dato lo soporta, y si cambio en la base de datos
    private Integer estadoId;
    private Integer clienteCedula;
    private Integer formaPago;
    private Double cambio;

    public Pago() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public Integer getClienteCedula() {
        return clienteCedula;
    }

    public void setClienteCedula(Integer clienteCedula) {
        this.clienteCedula = clienteCedula;
    }

    public Integer getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(Integer formaPago) {
        this.formaPago = formaPago;
    }

    public Double getCambio() {
        return cambio;
    }

    public void setCambio(Double cambio) {
        this.cambio = cambio;
    }

    @Override
    public String toString() {
        return "Pago{" + "id=" + id + ", monto=" + monto + ", estadoId=" + estadoId + ", clienteCedula=" + clienteCedula + ", formaPago=" + formaPago + ", cambio=" + cambio + '}';
    }
}
