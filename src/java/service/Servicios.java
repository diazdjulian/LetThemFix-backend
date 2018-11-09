/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Date;

/**
 *
 * @author Sebas
 */
public interface Servicios {
    
    public void crearCliente(String nombre, String apellido, String usuario, String password, String nroFiscal, Date fechaNacimiento, int telefono, String mail, String domicilio, int altura, String localidad, String provincia);
    
    public void aceptarPresupuesto(Long idProblema, Long idPresupuesto);
}
