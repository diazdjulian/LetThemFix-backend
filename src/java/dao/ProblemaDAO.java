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
import negocio.Cliente;
import negocio.Problema;

/**
 *
 * @author Sebas
 */
public class ProblemaDAO {
    
        static public Problema obtenerProblemaPorId(String idProblema) throws ConexionException, AccesoException/*, ClienteException*/ {
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
        String SQL = "SELECT  * FROM Problemas where idProblema = " + idProblema;
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }
        try {

            if (rs.next()) {
                Problema problema = new Problema(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getLong(4), rs.getLong(5));
                return problema;
            } else {
                return null;
                //throw new ClienteException("El cliente con DNI: " + dni + " no existe");
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public void grabarProblema(Problema problema) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("insert into Problemas values(?,?,?,?)");
            stm.setString(1, problema.getDescripcion());
            stm.setLong(2, problema.getRubro().getIdRubro());
            stm.setLong(3, problema.getPresupuestoAceptado().getIdPresupuesto());
            stm.setLong(4, problema.getTrabajo().getIdTrabajo());


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
