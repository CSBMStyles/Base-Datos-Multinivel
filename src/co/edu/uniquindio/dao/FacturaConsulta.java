/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.dao;

import co.edu.uniquindio.entiti.Factura;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristian
 */
public class FacturaConsulta {

    private String mensaje = "";

    public String agregarFactura(Connection conn, Factura enc) {
        PreparedStatement stmt = null;

        String sql = "insert into FACTURAVENTA (ID, FECHAVENTA, TOTALVENTA, VENDEDOR_ID, ESTADO_ID, PAGO_ID, CLIENTE_CEDULA) "
                + "values (?, ?, ?, ?, ?, ?, ?)";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enc.getId());
            stmt.setDate(2, enc.getFechaVenta());
            
            if (enc.getTotalVenta() != null) {
                stmt.setDouble(3, enc.getTotalVenta());
            } else {
                stmt.setNull(3, java.sql.Types.DOUBLE);
            }

            stmt.setInt(4, enc.getVendedorId());
            stmt.setInt(5, enc.getEstadoId());
            
            if (enc.getPagoId() != null) {
                stmt.setInt(6, enc.getPagoId());
            } else {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }

            stmt.setInt(7, enc.getClienteCedula());

            mensaje = "Guardado correctamente";

            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "No se pudo guardar: " + e;
        }
        return mensaje;
    }

    public String modificarFactura(Connection conn, Factura enc) {

        PreparedStatement stmt = null;

        String sql = "update FACTURAVENTA set ID = ?, FECHAVENTA = ?, TOTALVENTA = ?, VENDEDOR_ID = ?, ESTADO_ID = ?, PAGO_ID = ?, CLIENTE_CEDULA = ? "
                + "where ID = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enc.getId());
            stmt.setDate(2, (Date) enc.getFechaVenta());
            
            if (enc.getTotalVenta() != null) {
                stmt.setDouble(3, enc.getTotalVenta());
            } else {
                stmt.setNull(3, java.sql.Types.DOUBLE);
            }
            
            stmt.setInt(4, enc.getVendedorId());
            stmt.setInt(5, enc.getEstadoId());
            
            if (enc.getPagoId() != null) {
                stmt.setInt(6, enc.getPagoId());
            } else {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }
            
            stmt.setInt(7, enc.getClienteCedula());
            stmt.setInt(8, enc.getId());

            mensaje = "Modificado correctamente";

            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "No se pudo modificar: " + e;
        }
        return mensaje;
    }

    public String eliminarFactura(Connection conn, Integer id) {
        PreparedStatement stmt = null;

        String sql = "delete from FACTURAVENTA where ID = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            mensaje = "Eliminado correctamente";

            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "No se pudo eliminar: " + e;
        }

        return mensaje;
    }

    public void listarFactura(Connection conn, JTable tabla) {

        DefaultTableModel model;
        String[] columnas = {"ID", "FECHAVENTA", "TOTALVENTA", "VENDEDOR_ID", "ESTADO_ID", "PAGO_ID", "CLIENTE_CEDULA"};

        model = new DefaultTableModel(null, columnas);

        String sql = "select * from FACTURAVENTA";

        String[] filas = new String[7];
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < 7; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getIdMaximo(Connection con) {

        int id = 0;

        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select max(ID) from FACTURAVENTA";

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Double calcularTotalFactura(Connection conn, Integer factura) {
        PreparedStatement stmt = null;

        String sql = "{ ? = call CALCULAR_TOTALVENTA(?) }";

        Double total = 0.0;
        
        try {
            CallableStatement call = conn.prepareCall(sql);
            call.registerOutParameter(1, Types.NUMERIC);
            call.setInt(2, factura);   
            
            call.execute();

            total = call.getDouble(1);

            System.out.println("Subtotal: " + total);
            mensaje = "Guardado correctamente";

            call.close();
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "No se pudo guardar: " + e;
        }
        return total;
    }

    public void buscarDetalle(Integer cliente, Connection conn, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"ID", "FECHAVENTA", "TOTALVENTA", "VENDEDOR_ID", "ESTADO_ID", "PAGO_ID", "CLIENTE_CEDULA"};

        model = new DefaultTableModel(null, columnas);

        String sql = "select * from FACTURAVENTA where CLIENTE_CEDULA = ?";

        String[] filas = new String[7];
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente);
            rs = stmt.executeQuery(); // Ejecutar la consulta utilizando el PreparedStatement

            while (rs.next()) {
                for (int i = 0; i < 7; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos en un bloque finally
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
