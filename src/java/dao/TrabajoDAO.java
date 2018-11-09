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
import negocio.Presupuesto;
import negocio.Problema;
import negocio.Profesional;
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
                Trabajo trabajo = new Trabajo(rs.getLong(1), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8));
                return trabajo;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public List<Trabajo> obtenerTrabajosPorProfesional(int idProfesional) throws ConexionException, AccesoException/*, ClienteException*/ {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Trabajo> profesionalTrabajos = new LinkedList<Trabajo>();

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

        String SQL = "SELECT  * FROM Trabajos where idProfesional = " + idProfesional;

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            if (rs.next()) {
//                Problema trabajoProblema = ProblemaDAO.obtenerProblemaPorId(rs.getInt(2));
//                Profesional profesionalProblema = ProfesionalDAO.obtenerProfesionalPorId(rs.getInt(3));
                Trabajo trabajo = new Trabajo(rs.getLong(1), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8));
                profesionalTrabajos.add(trabajo);

                while (rs.next()) {
//                    trabajoProblema = ProblemaDAO.obtenerProblemaPorId(rs.getInt(2));
//                    profesionalProblema = ProfesionalDAO.obtenerProfesionalPorId(rs.getInt(3));
                    trabajo = new Trabajo(rs.getLong(1), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8));
                    profesionalTrabajos.add(trabajo);
                }

                return profesionalTrabajos;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public Long grabarTrabajo(Trabajo trabajo) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("insert into Trabajos values(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
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

            ResultSet rs = stm.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long llave = rs.getLong(1);
                return llave;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new AccesoException("No se pudo guardar");
        }
    }

}
