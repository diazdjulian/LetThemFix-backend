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
import negocio.Profesional;
import negocio.RubroProfesional;

/**
 *
 * @author Sebas
 */
public class ProfesionalDAO {

    static public Profesional obtenerProfesionalPorNroFiscal(String nroFiscal) throws ConexionException, AccesoException/*, ClienteException*/ {
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

        String SQL = "SELECT * FROM Profesionales WHERE nro_fiscal = '" + nroFiscal + "'";

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            if (rs.next()) {
                Profesional profesional = new Profesional(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getFloat(14), rs.getString(15));
                profesional.setValoraciones(ValoracionDAO.obtenerValoraciones(profesional.getIdProfesional(), "fixer"));
                profesional.calcularCalificacionPromedio();
                return profesional;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public Profesional obtenerProfesionalPorId(Long idProfesional) throws ConexionException, AccesoException/*, ClienteException*/ {
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

        String SQL = "SELECT  * FROM Profesionales WHERE idProfesional = " + idProfesional;

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            if (rs.next()) {
                Profesional profesional = new Profesional(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getFloat(14), rs.getString(15));
                profesional.setValoraciones(ValoracionDAO.obtenerValoraciones(profesional.getIdProfesional(), "fixer"));
                profesional.calcularCalificacionPromedio();
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
            stm = con.prepareStatement("INSERT INTO Profesionales VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1, profesional.getNombre());
            stm.setString(2, profesional.getApellido());
            stm.setString(3, profesional.getUsuario());
            stm.setString(4, profesional.getPassword());
            stm.setString(5, profesional.getNroFiscal());
            stm.setDate(6, new java.sql.Date(profesional.getFechaNacimiento().getTime()));
            stm.setString(7, String.valueOf(profesional.getTelefono()));
            stm.setString(8, profesional.getMail());
            stm.setString(9, profesional.getDomicilio());
            stm.setInt(10, profesional.getAltura());
            stm.setString(11, profesional.getLocalidad());
            stm.setString(12, profesional.getProvincia());
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

    static public Profesional login(String userMail, String password) throws ConexionException, AccesoException/*, ClienteException*/ {
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
            SQL = "SELECT * FROM Profesionales WHERE mail = '" + userMail + "' AND password = '" + password + "'";
        } else {
            SQL = "SELECT * FROM Profesionales WHERE usuario = '" + userMail + "' AND password = '" + password + "'";
        }
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }
        try {

            if (rs.next()) {
                Profesional profesional = new Profesional(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getFloat(14), rs.getString(15));
                profesional.setValoraciones(ValoracionDAO.obtenerValoraciones(profesional.getIdProfesional(), "fixer"));
                profesional.calcularCalificacionPromedio();
                return profesional;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public void bajaProfesional(Long idProfesional) throws ConexionException, AccesoException {

        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {

            stm = con.prepareStatement("UPDATE Profesionales SET estado = 'B' WHERE idProfesional = ?");
            stm.setLong(1, idProfesional);

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

    static public void actualizarProfesional(Profesional profesional, Long idProfesional) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {

            stm = con.prepareStatement("UPDATE Profesionales SET nombre = ?, apellido = ?, usuario = ?, password = ?, nro_fiscal = ?, fecha_nacimiento = ?, telefono= ?, mail = ?,  domicilio = ?, altura = ?, localidad = ?, provincia = ?, valoracion = ?, estado = ? WHERE idProfesional = ?");

            stm.setString(1, profesional.getNombre());
            stm.setString(2, profesional.getApellido());
            stm.setString(3, profesional.getUsuario());
            stm.setString(4, profesional.getPassword());
            stm.setString(5, profesional.getNroFiscal());
            stm.setDate(6, new java.sql.Date(profesional.getFechaNacimiento().getTime()));
            stm.setString(7, String.valueOf(profesional.getTelefono()));
            stm.setString(8, profesional.getMail());
            stm.setString(9, profesional.getDomicilio());
            stm.setInt(10, profesional.getAltura());
            stm.setString(11, profesional.getLocalidad());
            stm.setString(12, profesional.getProvincia());
            stm.setFloat(13, 0.0F);
            stm.setString(14, profesional.getEstado());
            stm.setLong(15, idProfesional);
            

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

    static public void agregarProfesion(Long idProfesional, Long idRubro, String matricula) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("INSERT INTO RubroProfesional VALUES(?,?,?)");
            stm.setLong(1, idProfesional);
            stm.setLong(2, idRubro);
            stm.setString(3, matricula);
        } catch (SQLException e) {
            throw new AccesoException("Error de acceso");
        }

        try {
            stm.execute();
        } catch (SQLException e) {
            throw new AccesoException("No se pudo guardar");
        }
    }

    public static void quitarProfesion(Long idProfesional, Long idRubro) throws ConexionException, AccesoException  {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("DELETE FROM RubroProfesional WHERE idProfesional = ? AND idRubro = ?");
            stm.setLong(1, idProfesional);
            stm.setLong(2, idRubro);
        } catch (SQLException e) {
            throw new AccesoException("Error de acceso");
        }

        try {
            stm.execute();
        } catch (SQLException e) {
            throw new AccesoException("No se pudo guardar");
        }
    }

    public static List<RubroProfesional> obtenerRubrosPorProfesional(Long idProfesional) throws ConexionException, AccesoException  {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<RubroProfesional> rubrosProfesional = new LinkedList<RubroProfesional>();

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

        String SQL = "SELECT * FROM RubroProfesional WHERE idProfesional = " + idProfesional;

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }
        try {

            if (rs.next()) {
                RubroProfesional rubroProfesional = new RubroProfesional(rs.getLong(2), rs.getLong(3), rs.getString(4));
                rubrosProfesional.add(rubroProfesional);
                while (rs.next()) {
                    rubroProfesional = new RubroProfesional(rs.getLong(2), rs.getLong(3), rs.getString(4));
                    rubrosProfesional.add(rubroProfesional);
                }
                return rubrosProfesional;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public int habilitadoACerrarCuenta(Long idProfesional) throws ConexionException, AccesoException/*, ClienteException*/ {
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

        String SQL = "SELECT COUNT(*) AS total FROM Trabajos WHERE idTrabajo IN (SELECT idTrabajo FROM Problemas WHERE idPresupuesto IN (SELECT idPresupuesto FROM Presupuestos WHERE idProfesional = " + idProfesional + ")) AND estado != 'D'";

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            if (rs.next()) {
                return rs.getInt("total");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
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

        String SQL = "SELECT * FROM Profesionales WHERE ";
        
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
