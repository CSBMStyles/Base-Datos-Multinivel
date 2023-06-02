/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.bo;

import co.edu.uniquindio.dao.DetalleConsulta;
import co.edu.uniquindio.dao.FacturaConsulta;
import co.edu.uniquindio.db.Base;
import co.edu.uniquindio.entiti.DetalleFactura;


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
public class DetalleControlador {
    
    private String mensaje = "";
    private DetalleConsulta detdao = new DetalleConsulta();
    
    public String agregarFactura(Factura enc) {

        Connection conexion = Base.conectar();
                
        try {
            mensaje = detdao.agregarFactura(conexion, enc);
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
            mensaje = detdao.modificarFactura(conexion, enc);
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
    
    public String eliminarDetalle(Integer producto, Integer venta) {
        Connection conexion = Base.conectar();
                
        try {
            mensaje = detdao.eliminarDetalle(conexion, producto, venta);
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
    
    public void listarDetalle(JTable tabla) {
         Connection conexion = Base.conectar();
        
        detdao.listarDetalle(conexion, tabla);
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Integer getMaximoId () {
        Connection conexion = Base.conectar();
        
        int id = detdao.getIdMaximo(conexion);
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public void buscarDetalle(Integer factura, JTable tabla) {
        Connection conexion = Base.conectar();
        
        detdao.buscarDetalle(factura, conexion, tabla);
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String crearDetalle(DetalleFactura detalle) {
        Connection conexion = Base.conectar();
                
        try {
            mensaje = detdao.crearDetalle(conexion, detalle);
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
}
