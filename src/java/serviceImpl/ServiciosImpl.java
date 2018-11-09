/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import dao.ClienteDAO;
import dao.PresupuestoDAO;
import dao.ProblemaDAO;
import dao.ProfesionalDAO;
import dao.TrabajoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Cliente;
import negocio.Presupuesto;
import negocio.Problema;
import negocio.Profesional;
import negocio.Trabajo;
import service.Servicios;

/**
 *
 * @author Sebas
 */
public class ServiciosImpl implements Servicios {

    public void crearCliente(String nombre, String apellido, String usuario, String password, String nroFiscal, Date fechaNacimiento, int telefono, String mail, String domicilio, int altura, String localidad, String provincia) {
        Cliente c = new Cliente(nombre, apellido, usuario, password, nroFiscal, fechaNacimiento, telefono, mail, domicilio, altura, localidad, provincia);
        try {
            ClienteDAO.grabarCliente(c);
        } catch (ConexionException ex) {
            Logger.getLogger(ServiciosImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccesoException ex) {
            Logger.getLogger(ServiciosImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void aceptarPresupuesto(Long idProblema, Long idPresupuesto) {

        try {
            Trabajo trabajo = new Trabajo(new Date(), null, null, "", "A");
            Long idTrabajoGenerado = TrabajoDAO.grabarTrabajo(trabajo);
            ProblemaDAO.actualizarProblema(idProblema, idPresupuesto, idTrabajoGenerado);

        } catch (ConexionException ex) {
            Logger.getLogger(ServiciosImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccesoException ex) {
            Logger.getLogger(ServiciosImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
