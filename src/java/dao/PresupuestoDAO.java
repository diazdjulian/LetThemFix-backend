/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excepciones.AccesoException;
import excepciones.ConexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import negocio.Cliente;
import negocio.Presupuesto;

/**
 *
 * @author Sebas
 */
public class PresupuestoDAO {

    static public Presupuesto obtenerPresupuestoPorId(Long idPresupuesto) throws ConexionException, AccesoException/*, ClienteException*/ {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        try {
            stmt = con.createStatement();
        } catch (SQLException e1) {
            throw new AccesoException("Error de acceso");
        }
        String SQL = "SELECT  * FROM Presupuestos where idPresupuesto = " + idPresupuesto;
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }
        try {

            if (rs.next()) {
                Presupuesto presupuesto = new Presupuesto(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getFloat(5), rs.getLong(6));
                return presupuesto;
            } else {
                return null;
                //throw new ClienteException("El cliente con DNI: " + dni + " no existe");
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }
    
    static public List<Presupuesto> obtenerPresupuestosPorProblemaId(Long idProblema) throws ConexionException, AccesoException/*, ClienteException*/ {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Presupuesto> problemaPresupuestos = new LinkedList<Presupuesto>();

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        try {
            stmt = con.createStatement();
        } catch (SQLException e1) {
            throw new AccesoException("Error de acceso");
        }

        String SQL = "SELECT  * FROM Presupuestos where idProblema = " + idProblema;

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            if (rs.next()) {
                Presupuesto presupuesto = new Presupuesto(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getFloat(5), rs.getLong(6));
                problemaPresupuestos.add(presupuesto);

                while(rs.next()) {
                    presupuesto = new Presupuesto(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getFloat(5), rs.getLong(6));
                    problemaPresupuestos.add(presupuesto);
                }
                
                return problemaPresupuestos;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }
    
    static public void grabarPresupuesto(Presupuesto presu) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("insert into Presupuestos values(?,?,?,?,?)");
            stm.setString(1, presu.getObservacion());
            stm.setFloat(2, presu.getValor());
            stm.setInt(3, presu.getCantJornadasLaborables());
            stm.setFloat(4, presu.getValorMateriales());
            stm.setLong(5, presu.getIdProblema());

        } catch (SQLException e) {
            throw new AccesoException("Error de acceso");
        }

        try {
            stm.execute();
        } catch (SQLException e) {
            throw new AccesoException("No se pudo guardar");
        }
    }
}
