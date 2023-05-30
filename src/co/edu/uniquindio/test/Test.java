/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.test;

import co.edu.uniquindio.bo.FacturaControlador;
import co.edu.uniquindio.entiti.Factura;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 *
 * @author Cristian
 */
public class Test {
    
    FacturaControlador enbo = new FacturaControlador();
    Factura enc = new Factura();
    String mensaje = "";
    
    public void insertar() {
        LocalDate fechaLocal = LocalDate.now();
        Date fecha = Date.valueOf(fechaLocal);
        enc.setId(26);
        enc.setFechaVenta(fecha);
        Double total = null;
        
            enc.setTotalVenta(total);
        
        
        enc.setVendedorId(15);
        enc.setEstadoId(1);
        Integer pago = null;
        
            enc.setPagoId(pago);
        
        
        enc.setClienteCedula(25);
        mensaje = enbo.agregarFactura(enc);
        
        System.out.println(mensaje);
    }
    
    public void modificar() {
        LocalDate fechaLocal = LocalDate.now();
        Date fecha = Date.valueOf(fechaLocal);
        enc.setId(26);
        enc.setFechaVenta(fecha);
        Double total = null;
        
            enc.setTotalVenta(total);
        
        
        enc.setVendedorId(14);
        enc.setEstadoId(2);
        Integer pago = null;
        
            enc.setPagoId(pago);
        
        
        enc.setClienteCedula(25);
        mensaje = enbo.modificarFactura(enc);
        
        System.out.println(mensaje);
    }
    
    public void eliminar() {
        mensaje = enbo.eliminarFactura(26);
        
        System.out.println(mensaje);
    }
    
    public static void main(String[] args) {
        Test test = new Test();
        
        //test.insertar();
        //test.modificar();
        //test.eliminar();
    }
}
