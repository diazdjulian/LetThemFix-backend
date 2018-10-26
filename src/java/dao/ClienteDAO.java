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
    static public Cliente obtenerClientePorId(Long clienteId) throws ConexionException, AccesoException/*, ClienteException*/ {
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

        String SQL = "SELECT  * FROM Clientes where idCliente = " + clienteId;

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getFloat(14));
                return cliente;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }
    
    static public Cliente obtenerClientePorNroFiscal(String nroFiscal) throws ConexionException, AccesoException/*, ClienteException*/ {
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

        String SQL = "SELECT  * FROM Clientes where nro_fiscal = '" + nroFiscal + "'";

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getFloat(14));
                return cliente;
            } else {
                return null;
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
            stm = con.prepareStatement("insert into Clientes values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1, c.getNombre());
            stm.setString(2, c.getApellido());
            stm.setString(3, c.getUsuario());
            stm.setString(4, c.getPassword());
            stm.setString(5, c.getNroFiscal());
            stm.setDate(6, new java.sql.Date(c.getFechaNacimiento().getTime()));
            stm.setString(7, String.valueOf(c.getTelefono()));
            stm.setString(8, c.getMail());
            stm.setString(9, c.getDomicilio());
            stm.setInt(10, c.getAltura());
            stm.setString(11, c.getLocalidad());
            stm.setString(12, c.getProvincia());
            stm.setFloat(13, 0.0F);

        } catch (SQLException e) {
            throw new AccesoException("Error de acceso");
        }

        try {
            stm.execute();

        } catch (SQLException e) {
            throw new AccesoException("No se pudo guardar");
        }
    }
    
    static public Cliente login(String userMail, String password) throws ConexionException, AccesoException/*, ClienteException*/ {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SQL = "";
        
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
        if (userMail.contains("@")) {
            SQL = "SELECT * FROM Clientes where mail = '" + userMail + "' and password = '" + password + "'";
        } else {
            SQL = "SELECT * FROM Clientes where usuario = '" + userMail + "' and password = '" + password + "'";
        }
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }
        try {

            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getFloat(14));
                return cliente;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }
}
