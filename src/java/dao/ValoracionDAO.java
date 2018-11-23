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
import java.util.ArrayList;
import java.util.List;
import negocio.Trabajo;
import negocio.Valoracion;

/**
 *
 * @author Sebas
 */
public class ValoracionDAO {

    static public Valoracion obtenerValoracionPorId(Long idValoracion) throws ConexionException, AccesoException/*, ClienteException*/ {
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

        String SQL = "SELECT  * FROM Valoraciones where idValoracion = " + idValoracion;

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {

            if (rs.next()) {
                Valoracion valoracion = new Valoracion(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                return valoracion;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public List<Valoracion> obtenerValoraciones(Long idValorado) throws ConexionException, AccesoException/*, ClienteException*/ {
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

        String SQL = "SELECT  * FROM Valoraciones where idValorado = " + idValorado;

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            List<Valoracion> listaValoraciones = new ArrayList<>();
            while (rs.next()) {
                Valoracion valoracion = new Valoracion(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                listaValoraciones.add(valoracion);
            }
            return listaValoraciones;
            
            
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    
    
    static public Long grabarValoracion(Valoracion valoracion) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("insert into Valoraciones values(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setLong(1, valoracion.getIdValorado());
            stm.setLong(2, valoracion.getIdValorador());
            stm.setString(3, valoracion.getTipoValorado());
            stm.setString(4, valoracion.getDetalle());
            stm.setFloat(5, valoracion.getCalificacion());

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
            e.printStackTrace();
            throw new AccesoException("No se pudo guardar");
        }
    }

}
