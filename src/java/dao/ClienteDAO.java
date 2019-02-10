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
            throw new ConexionException("No esta disponible el acceso al Servidor.");
        }

        try {
            stmt = con.createStatement();
        } catch (SQLException e1) {
            throw new AccesoException("Error de acceso");
        }

        String SQL = "SELECT  * FROM Clientes WHERE idCliente = " + clienteId + " AND estado = 'A' ";

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getFloat(14), rs.getString(15));
                cliente.setValoraciones(ValoracionDAO.obtenerValoraciones(cliente.getIdCliente(), "user"));
                cliente.calcularCalificacionPromedio();
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

        String SQL = "SELECT * FROM Clientes WHERE nro_fiscal = '" + nroFiscal + "'";

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getFloat(14), rs.getString(15));
                cliente.setValoraciones(ValoracionDAO.obtenerValoraciones(cliente.getIdCliente(), "user"));
                cliente.calcularCalificacionPromedio();
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
            stm = con.prepareStatement("INSERT INTO Clientes VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
            stm.setString(14, "A");

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
            SQL = "SELECT * FROM Clientes WHERE mail = '" + userMail + "' AND password = '" + password + "' AND estado = 'A'";
        } else {
            SQL = "SELECT * FROM Clientes WHERE usuario = '" + userMail + "' AND password = '" + password + "' AND estado = 'A'";
        }
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }
        try {

            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getFloat(14), rs.getString(15));
                cliente.setValoraciones(ValoracionDAO.obtenerValoraciones(cliente.getIdCliente(), "user"));
                cliente.calcularCalificacionPromedio();
                return cliente;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public void bajaCliente(Long idCliente) throws ConexionException, AccesoException {

        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {

            stm = con.prepareStatement("UPDATE Clientes SET estado = 'B' WHERE idCliente = ?");
            stm.setLong(1, idCliente);

        } catch (SQLException e) {
            throw new AccesoException("Error de acceso");
        }

        try {
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();

            throw new AccesoException("No se pudo guardar");
        }

    }

    static public void actualizarCliente(Cliente c, Long idCliente) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {

            stm = con.prepareStatement("UPDATE Clientes SET nro_fiscal = ?, fecha_nacimiento = ?, telefono= ?, mail = ?,  domicilio = ?, altura = ?, localidad = ?, provincia = ? WHERE idCliente = ?");

            stm.setString(1, c.getNroFiscal());
            stm.setDate(2, new java.sql.Date(c.getFechaNacimiento().getTime()));
            stm.setString(3, String.valueOf(c.getTelefono()));
            stm.setString(4, c.getMail());
            stm.setString(5, c.getDomicilio());
            stm.setInt(6, c.getAltura());
            stm.setString(7, c.getLocalidad());
            stm.setString(8, c.getProvincia());
            stm.setLong(9, idCliente);
            

        } catch (SQLException e) {
            throw new AccesoException("Error de acceso");
        }

        try {
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();

            throw new AccesoException("No se pudo guardar");
        }
    }

    static public boolean datoExiste(String usuario, String email, String nroFiscal) throws ConexionException, AccesoException/*, ClienteException*/ {
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

        String SQL = "SELECT * FROM Clientes WHERE ";
        
        if (usuario != null) {
            SQL = SQL + "usuario = '" + usuario + "'";
        } else if (email != null) {
            SQL = SQL + "mail = '" + email + "'";
        } else if (nroFiscal != null) {
            SQL = SQL + "nro_fiscal = '" + nroFiscal + "'";
        }

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            return !rs.next();
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

}
