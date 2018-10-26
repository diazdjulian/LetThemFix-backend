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
import negocio.Presupuesto;
import negocio.Problema;
import negocio.ProblemaRequest;
import negocio.ProblemaUpdate;
import negocio.Rubro;
import negocio.Trabajo;

/**
 *
 * @author Sebas
 */
public class ProblemaDAO {
    
    static public Problema obtenerProblemaPorId(int idProblema) throws ConexionException, AccesoException/*, ClienteException*/ {
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
                Cliente problemaCliente = ClienteDAO.obtenerClientePorId(rs.getLong(2));
                Rubro problemaRubro = RubroDAO.obtenerRubroPorId(rs.getInt(8));
                Trabajo trabajoProblema = null;
                Presupuesto presupuestoProblema = null;
                if (rs.getLong(9) > 0) {
                    presupuestoProblema = PresupuestoDAO.obtenerPresupuestoPorId(rs.getLong(9));
                }
                if (rs.getLong(10) > 0) {
                    trabajoProblema = TrabajoDAO.obtenerTrabajoPorId(rs.getLong(10));
                }
                Problema problema = new Problema(rs.getInt(1), problemaCliente, rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), problemaRubro, presupuestoProblema, trabajoProblema);
                return problema;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }

    static public List<Problema> obtenerProblemas() throws ConexionException, AccesoException/*, ClienteException*/ {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    List<Problema> clienteProblemas = new LinkedList<Problema>();

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

    String SQL = "SELECT  * FROM Problemas";

    try {
        rs = stmt.executeQuery(SQL);
    } catch (SQLException e1) {
        throw new AccesoException("Error de consulta");
    }

    try {
        if (rs.next()) {
            Cliente problemaCliente = ClienteDAO.obtenerClientePorId(rs.getLong(2));
            Rubro problemaRubro = RubroDAO.obtenerRubroPorId(rs.getInt(8));
            Trabajo trabajoProblema = null;
            Presupuesto presupuestoProblema = null;
            if (rs.getLong(9) > 0) {
                presupuestoProblema = PresupuestoDAO.obtenerPresupuestoPorId(rs.getLong(9));
            }
            if (rs.getLong(10) > 0) {
                trabajoProblema = TrabajoDAO.obtenerTrabajoPorId(rs.getLong(10));
            }
            Problema problema = new Problema(rs.getInt(1), problemaCliente, rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), problemaRubro, presupuestoProblema, trabajoProblema);
            clienteProblemas.add(problema);

            while(rs.next()) {
                problemaCliente = ClienteDAO.obtenerClientePorId(rs.getLong(2));
                problemaRubro = RubroDAO.obtenerRubroPorId(rs.getInt(8));
                if (rs.getLong(9) > 0) {
                    presupuestoProblema = PresupuestoDAO.obtenerPresupuestoPorId(rs.getLong(9));
                }
                if (rs.getLong(10) > 0) {
                    trabajoProblema = TrabajoDAO.obtenerTrabajoPorId(rs.getLong(10));
                }
                problema = new Problema(rs.getInt(1), problemaCliente, rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), problemaRubro, presupuestoProblema, trabajoProblema);
                clienteProblemas.add(problema);
            }

            return clienteProblemas;
        } else {
            return null;
        }
    } catch (SQLException e) {
        throw new ConexionException("No es posible acceder a los datos");
    }
}
    
    static public List<Problema> obtenerProblemasPorCliente(int idCliente) throws ConexionException, AccesoException/*, ClienteException*/ {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Problema> clienteProblemas = new LinkedList<Problema>();

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

        String SQL = "SELECT  * FROM Problemas where idCliente = " + idCliente;

        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

        try {
            if (rs.next()) {
                Cliente problemaCliente = ClienteDAO.obtenerClientePorId(rs.getLong(2));
                Rubro problemaRubro = RubroDAO.obtenerRubroPorId(rs.getInt(8));
                Trabajo trabajoProblema = null;
                Presupuesto presupuestoProblema = null;
                if (rs.getLong(9) > 0) {
                    presupuestoProblema = PresupuestoDAO.obtenerPresupuestoPorId(rs.getLong(9));
                }
                if (rs.getLong(10) > 0) {
                    trabajoProblema = TrabajoDAO.obtenerTrabajoPorId(rs.getLong(10));
                }
                Problema problema = new Problema(rs.getInt(1), problemaCliente, rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), problemaRubro, presupuestoProblema, trabajoProblema);
                clienteProblemas.add(problema);

                while(rs.next()) {
                    problemaCliente = ClienteDAO.obtenerClientePorId(rs.getLong(2));
                    problemaRubro = RubroDAO.obtenerRubroPorId(rs.getInt(8));
                    if (rs.getLong(9) > 0) {
                        presupuestoProblema = PresupuestoDAO.obtenerPresupuestoPorId(rs.getLong(9));
                    }
                    if (rs.getLong(10) > 0) {
                        trabajoProblema = TrabajoDAO.obtenerTrabajoPorId(rs.getLong(10));
                    }
                    problema = new Problema(rs.getInt(1), problemaCliente, rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), problemaRubro, presupuestoProblema, trabajoProblema);
                    clienteProblemas.add(problema);
                }
                
                return clienteProblemas;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConexionException("No es posible acceder a los datos");
        }
    }
    
    static public void grabarProblema(ProblemaRequest problema) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("insert into Problemas values(?,?,?,?,?,?,?,?,?)");
            stm.setLong(1, problema.getIdCliente());
            stm.setString(2, problema.getTitulo());
            stm.setString(3, problema.getDescripcion());
            stm.setLong(4, problema.getPresupuestoMinimo());
            stm.setLong(5, problema.getPresupuestoMaximo());
            stm.setString(6, problema.getZona());
            stm.setLong(7, problema.getIdRubro());
            stm.setLong(8, 0);
            stm.setLong(9, 0);
        } catch (SQLException e) {
            throw new AccesoException("Error de acceso");
        }

        try {
            stm.execute();
        } catch (SQLException e) {
            throw new AccesoException("No se pudo guardar");
        }
    }
    
    static public void actualizarProblema(ProblemaUpdate problema) throws ConexionException, AccesoException {
        Connection con;

        try {
            con = ConnectionFactory.getInstancia().getConection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConexionException("No esta disponible el acceso al Servidor");
        }

        PreparedStatement stm;
        try {
            stm = con.prepareStatement("update Problemas set idPresupuesto = ? WHERE idProblema = ?");
            stm.setLong(1, problema.getIdPresupuesto());
            stm.setLong(2, problema.getIdProblema());
        } catch (SQLException e) {
            throw new AccesoException("Error de acceso");
        }

        try {
            stm.execute();
        } catch (SQLException e) {
            throw new AccesoException("No se pudo actualizar");
        }
    }

    static public void eliminarProblema(int idProblema) throws ConexionException, AccesoException/*, ClienteException*/ {
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

        String SQL = "DELETE FROM Problemas where idProblema = " + idProblema;

        try {
            stmt.executeQuery(SQL);
        } catch (SQLException e1) {
            throw new AccesoException("Error de consulta");
        }

    }
}
