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
import negocio.Profesional;

/**
 *
 * @author Sebas
 */
public class ProfesionalDAO {
    
        static public Profesional obtenerProfesionalPorDni(String dni) throws ConexionException, AccesoException/*, ClienteException*/ {
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
        String SQL = "SELECT  * FROM Profesionales where dni = " + dni;
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }
        try {

            if (rs.next()) {
                Profesional profesional = new Profesional(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
                return profesional;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public void grabarProfesional(Profesional profesional) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("insert into Profesionales values(?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1, profesional.getNombre());
            stm.setString(2, profesional.getApellido());
            stm.setString(3, profesional.getTipoDocumento());
            stm.setInt(4, profesional.getNroDocumento());
            stm.setDate(5, new java.sql.Date(profesional.getFechaNacimiento().getTime()));
            stm.setInt(6, profesional.getTelefono());
            stm.setString(7, profesional.getMail());
            stm.setString(8, profesional.getMatricula());
            stm.setString(9, profesional.getLocalidad());
            stm.setFloat(10, profesional.getValoracion());

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
