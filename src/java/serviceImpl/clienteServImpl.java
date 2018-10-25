/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import dao.ClienteDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Cliente;
import service.clienteServ;

/**
 *
 * @author Sebas
 */
public class clienteServImpl implements clienteServ {

    
    public void crearCliente(String nombre, String apellido, String usuario, String password, String nroFiscal, Date fechaNacimiento, int telefono, String mail, String domicilio, int altura, String localidad, String provincia) {
        Cliente c = new Cliente(nombre, apellido, usuario, password, nroFiscal, fechaNacimiento, telefono, mail, domicilio, altura, localidad, provincia);
        try {
            ClienteDAO.grabarCliente(c);
        } catch (ConexionException ex) {
            Logger.getLogger(clienteServImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccesoException ex) {
            Logger.getLogger(clienteServImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
