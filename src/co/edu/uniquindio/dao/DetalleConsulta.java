/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.dao;

import co.edu.uniquindio.entiti.DetalleFactura;
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
public class DetalleConsulta {

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

    public String eliminarDetalle(Connection conn, Integer producto, Integer venta) {
        PreparedStatement stmt = null;

        String sql = "delete from DETALLEVENTA where PRODUCTO_ID = ? AND VENTA_ID = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, producto);
            stmt.setInt(2, venta);

            mensaje = "Eliminado correctamente";

            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "No se pudo eliminar: " + e;
        }

        return mensaje;
    }

    public void listarDetalle(Connection conn, JTable tabla) {

        DefaultTableModel model;
        String[] columnas = {"PRODUCTO_ID", "VENTA_ID", "CANTIDAD", "SUBTOTAL"};

        model = new DefaultTableModel(null, columnas);

        String sql = "select * from DETALLEVENTA";

        String[] filas = new String[4];
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < 4; i++) {
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

    public void buscarDetalle(Integer factura, Connection conn, JTable tabla) {
                DefaultTableModel model;
        String[] columnas = {"PRODUCTO_ID", "VENTA_ID", "CANTIDAD", "SUBTOTAL"};

        model = new DefaultTableModel(null, columnas);

        String sql = "select * from DETALLEVENTA where VENTA_ID = ?";

        String[] filas = new String[4];
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, factura);
            rs = stmt.executeQuery(); // Ejecutar la consulta utilizando el PreparedStatement

            while (rs.next()) {
                for (int i = 0; i < 4; i++) {
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

    public String crearDetalle(Connection conn, DetalleFactura detalle) {
        PreparedStatement stmt = null;

        String sql = "{ ? = call CREAR_DETALLE_VENTA(?, ?, ?) }";

        try {
            CallableStatement call = conn.prepareCall(sql);
            call.registerOutParameter(1, Types.NUMERIC);
            call.setInt(2, detalle.getProductoId()); 
            call.setInt(3, detalle.getVentaId());   
            call.setInt(4, detalle.getCantidad());  
            
            call.execute();

            Double subtotal = call.getDouble(1);

            System.out.println("Subtotal: " + subtotal);
            mensaje = "Guardado correctamente";

            call.close();
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "No se pudo guardar: " + e;
        }
        return mensaje;
    }
}
