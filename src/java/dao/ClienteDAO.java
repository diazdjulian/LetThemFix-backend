package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;

import java.sql.SQLException;

import negocio.Cliente;

public class ClienteDAO {

    static public Cliente obtenerClientePorDni(String dni) throws ConexionException, AccesoException/*, ClienteException*/ {
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
        String SQL = "SELECT  * FROM clientes where dni = " + dni;
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }
        try {

            if (rs.next()) {
//				Cliente cliente = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
//				return cliente;
                return null;
            } else {
                return null;
                //throw new ClienteException("El cliente con DNI: " + dni + " no existe");
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public void grabarCliente(Cliente c) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("insert into clientes values(?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1, c.getNombre());
            stm.setString(2, c.getApellido());
            stm.setString(3, c.getTipoDocumento());
            stm.setString(4, c.getNroDocumento());
            stm.setDate(5, new java.sql.Date(c.getFechaNacimiento().getTime()));
            stm.setInt(6, c.getTelefono());
            stm.setString(7, c.getMail());
            stm.setString(8, c.getDomicilio());
            stm.setString(9, c.getLocalidad());
            stm.setFloat(10, c.getCalificacionPromedio());

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
