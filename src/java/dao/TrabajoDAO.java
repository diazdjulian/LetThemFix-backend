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
import negocio.Trabajo;

/**
 *
 * @author Sebas
 */
public class TrabajoDAO {

    static public Trabajo obtenerTrabajoPorId(Long idTrabajo) throws ConexionException, AccesoException/*, ClienteException*/ {
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
        String SQL = "SELECT  * FROM Trabajos where idTrabajo = " + idTrabajo;
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }
        try {

            if (rs.next()) {
                Trabajo trabajo = new Trabajo(rs.getLong(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getString(6));
                return trabajo;
            } else {
                return null;
                //throw new ClienteException("El cliente con DNI: " + dni + " no existe");
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public void grabarTrabajo(Trabajo trabajo) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("insert into Trabajos values(?,?,?,?,?)");
            stm.setDate(1, new java.sql.Date(trabajo.getFechaAceptacion().getTime()));
            stm.setDate(2, new java.sql.Date(trabajo.getFechaInicio().getTime()));
            stm.setDate(3, new java.sql.Date(trabajo.getFechaFin().getTime()));
            stm.setString(4, trabajo.getObservaciones());
            stm.setString(5, trabajo.getEstado());


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
