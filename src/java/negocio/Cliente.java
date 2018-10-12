/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Sebas
 */
public class Cliente {

    private static Long numeradorCliente = 0L;
    private Long idCliente;
    private String tipoDocumento;
    private String nroDocumento;
    private String nombre;
    private String apellido;
    private Calendar fechaNacimiento;
    private int telefono;
    private String mail;
    private String domicilio;
    private String localidad;
    private float calificacionPromedio;
    private List<Valoracion> valoraciones;

    public Cliente(String tipoDocumento, String nroDocumento, String nombre, String apellido, Calendar fechaNacimiento, int telefono, String mail, String domicilio, String localidad, float calificacionPromedio) {

        this.idCliente = numeradorCliente++;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.mail = mail;
        this.domicilio = domicilio;
        this.localidad = localidad;

    }

    public float calcularCalificacionPromedio() {
        float total = 0L;
        for (Valoracion valoracion : this.getValoraciones()) {
            total = total + valoracion.getCalificacion();
        }
        if (total != 0L) {
            return (this.calificacionPromedio = total / this.getValoraciones().size());
        } else {
            return 0L;
        }
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public float getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(float calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

}
