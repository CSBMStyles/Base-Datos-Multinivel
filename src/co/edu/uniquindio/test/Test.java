/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.test;

import co.edu.uniquindio.bo.Controlador;
import co.edu.uniquindio.entiti.Encuesta;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 *
 * @author Cristian
 */
public class Test {
    
    Controlador enbo = new Controlador();
    Encuesta enc = new Encuesta();
    String mensaje = "";
    
    public void insertar() {
        LocalDate fechaLocal = LocalDate.now();
        Date fecha = Date.valueOf(fechaLocal);
        enc.setId(26);
        enc.setFechaVenta(fecha);
        Double total = null;
        if (total != null) {
            enc.setTotalVenta(total);
        }
        
        enc.setVendedorId(15);
        enc.setEstadoId(1);
        Integer pago = null;
        if (pago != null) {
            enc.setPagoId(pago);
        }
        
        enc.setClienteCedula(25);
        mensaje = enbo.agregarEncuesta(enc);
        
        System.out.println(mensaje);
    }
    
    public void modificar() {
        enc.setId(111);
        enc.setNombre("Nuevo nombre para encuesta");
        //enc.setObjetivo("Objetivo");
        //enc.setTipoId(101);
        //enc.setDescripcion("Descripcion");
        //enc.setCodigo("Codigo");
        mensaje = enbo.modificarEncuesta(enc);
        
        System.out.println(mensaje);
    }
    
    public void eliminar() {
        mensaje = enbo.eliminarEncuesta(111);
        
        System.out.println(mensaje);
    }
    
    public static void main(String[] args) {
        Test test = new Test();
        
        test.insertar();
        //test.modificar();
        //test.eliminar();
    }
}
