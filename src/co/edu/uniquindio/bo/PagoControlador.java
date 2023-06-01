/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.bo;

import co.edu.uniquindio.dao.FacturaConsulta;
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
    private FacturaConsulta encdao = new FacturaConsulta();
    
    public String agregarFactura(Factura enc) {

        Connection conexion = Base.conectar();
                
        try {
            mensaje = encdao.agregarFactura(conexion, enc);
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
            mensaje = encdao.modificarFactura(conexion, enc);
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
            mensaje = encdao.eliminarFactura(conexion, id);
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
        
        encdao.listarFactura(conexion, tabla);
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Integer getMaximoId () {
        Connection conexion = Base.conectar();
        
        int id = encdao.getIdMaximo(conexion);
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }
}
