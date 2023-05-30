/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Cristian
 */
public class ConexionBase {

    private static Connection conexion = null;
    private static String usuario = "MULTINIVEL_PARCIAL";
    private static String clave = "111";
    private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    
    public static Connection conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection(url, usuario, clave);
            conexion.setAutoCommit(false);
            System.out.println("Conectado");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }

    public void cerrar(ResultSet resultado, Statement declaracion, Connection conexion) {
        if (resultado != null) {
            try {
                resultado.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (declaracion != null) {
            try {
                declaracion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        ConexionBase c = new ConexionBase();
        
        c.conectar();
    }
}
