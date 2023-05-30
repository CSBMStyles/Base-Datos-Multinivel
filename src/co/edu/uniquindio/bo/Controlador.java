/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.bo;

import co.edu.uniquindio.dao.EncuestaConsulta;
import co.edu.uniquindio.db.ConexionBase;


import co.edu.uniquindio.entiti.Encuesta;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;


/**
 *
 * @author Cristian
 */
public class Controlador {
    
    private String mensaje = "";
    private EncuestaConsulta encdao = new EncuestaConsulta();
    
    public String agregarEncuesta(Encuesta enc) {

        Connection conexion = ConexionBase.conectar();
                
        try {
            mensaje = encdao.agregarEncuesta(conexion, enc);
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
    
    public String modificarEncuesta(Encuesta enc) {
       
        Connection conexion = ConexionBase.conectar();
                
        try {
            mensaje = encdao.modificarEncuesta(conexion, enc);
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
    
    public String eliminarEncuesta(Integer id) {
        Connection conexion = ConexionBase.conectar();
                
        try {
            mensaje = encdao.eliminarEncuesta(conexion, id);
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
    
    public void listarEncuesta(JTable tabla) {
         Connection conexion = ConexionBase.conectar();
        
        encdao.listarEncuesta(conexion, tabla);
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Integer getMaximoId () {
        Connection conexion = ConexionBase.conectar();
        
        int id = encdao.getIdMaximo(conexion);
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }
}
