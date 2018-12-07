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
import negocio.Rubro;

/**
 *
 * @author Sebas
 */
public class RubroDAO {

    static public Rubro obtenerRubroPorId(int rubroId) throws ConexionException, AccesoException/*, ClienteException*/ {
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

        String SQL = "SELECT  * FROM Rubros where idRubro = " + rubroId;

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            if (rs.next()) {
                Rubro rubro = new Rubro(rs.getLong(1), rs.getString(2));
                return rubro;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public List<Rubro> obtenerRubros() throws ConexionException, AccesoException/*, ClienteException*/ {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Rubro> rubros = new LinkedList<Rubro>();
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
        String SQL = "SELECT * FROM Rubros";
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }
        try {

            if (rs.next()) {
                Rubro rubro = new Rubro(rs.getLong(1), rs.getString(2));
                rubros.add(rubro);
                while (rs.next()) {
                    rubro = new Rubro(rs.getLong(1), rs.getString(2));
                    rubros.add(rubro);
                }
                return rubros;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public void grabarRubro(Rubro rubro) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("insert into Rubros values(?)");
            stm.setString(1, rubro.getDescripcion());

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
