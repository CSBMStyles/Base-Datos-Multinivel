/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.bo;

import co.edu.uniquindio.dao.FacturaConsulta;
import co.edu.uniquindio.dao.PagoConsulta;
import co.edu.uniquindio.db.Base;


import co.edu.uniquindio.entiti.Factura;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;


/**
 *
 * @author Cristian
 */
public class PagoControlador {
    
    private String mensaje = "";
    private PagoConsulta pagodao = new PagoConsulta();
    
    public String agregarFactura(Factura enc) {

        Connection conexion = Base.conectar();
                
        try {
            mensaje = pagodao.agregarFactura(conexion, enc);
            //conexion.rollback();
        } catch (Exception e) {
            mensaje = mensaje + " " + e.getMessage();
        } finally {
            try {
                if (conexion != null) {
                conexion.close();
            }
            } catch (Exception e) {
                e.printStackTrace();
                mensaje = mensaje + " " + e.getMessage();
            }
        }
        return mensaje;
    }
    
    public String modificarFactura(Factura enc) {
       
        Connection conexion = Base.conectar();
                
        try {
            mensaje = pagodao.modificarFactura(conexion, enc);
            //conexion.rollback();
        } catch (Exception e) {
            mensaje = mensaje + " " + e.getMessage();
        } finally {
            try {
                if (conexion != null) {
                conexion.close();
            }
            } catch (Exception e) {
                e.printStackTrace();
                mensaje = mensaje + " " + e.getMessage();
            }
        }
        return mensaje;
    }
    
    public String eliminarFactura(Integer id) {
        Connection conexion = Base.conectar();
                
        try {
            mensaje = pagodao.eliminarFactura(conexion, id);
            //conexion.rollback();
        } catch (Exception e) {
            mensaje = mensaje + " " + e.getMessage();
        } finally {
            try {
                if (conexion != null) {
                conexion.close();
            }
            } catch (Exception e) {
                e.printStackTrace();
                mensaje = mensaje + " " + e.getMessage();
            }
        }
        return mensaje;
    }
    
    public void listarFactura(JTable tabla) {
         Connection conexion = Base.conectar();
        
        pagodao.listarFactura(conexion, tabla);
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Integer getMaximoId () {
        Connection conexion = Base.conectar();
        
        int id = pagodao.getIdMaximo(conexion);
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public void buscarPago(Integer cliente, JTable tabla) {
        Connection conexion = Base.conectar();
        
        pagodao.buscarPago(cliente, conexion, tabla);
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
