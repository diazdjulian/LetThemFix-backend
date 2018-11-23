/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Sebas
 */
public class Profesional {

    private Long idProfesional;
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String nroFiscal;
    private Date fechaNacimiento;
    private int telefono;
    private String mail;
    private String domicilio;
    private int altura;
    private String localidad;
    private String provincia;
    private float calificacionPromedio;
    private List<Valoracion> valoraciones;
    private String estado;

    public Profesional(String nombre, String apellido, String usuario, String password, String nroFiscal, Date fechaNacimiento, int telefono, String mail, String domicilio, int altura, String localidad, String provincia) {
       
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
        this.nroFiscal = nroFiscal;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.mail = mail;
        this.domicilio = domicilio;
        this.altura = altura;
        this.localidad = localidad;
        this.provincia = provincia;
        this.calificacionPromedio = 0;
        
    }
    
    public Profesional(Long idProfesional, String nombre, String apellido, String usuario, String password, String nroFiscal, Date fechaNacimiento, int telefono, String mail, String domicilio, int altura, String localidad, String provincia, float promedio, String estado) {
        
        this.idProfesional = idProfesional;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
        this.nroFiscal = nroFiscal;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.mail = mail;
        this.domicilio = domicilio;
        this.altura = altura;
        this.localidad = localidad;
        this.provincia = provincia;
        this.calificacionPromedio = promedio;
        this.estado = estado;
        
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
    
    public Long getIdProfesional() {
        return idProfesional;
    }
    
    public void setIdProfesional(Long idProfesional) {
        this.idProfesional = idProfesional;
    }
    
    public String getNroFiscal() {
        return nroFiscal;
    }
    
    public void setNroFiscal(String nroFiscal) {
        this.nroFiscal = nroFiscal;
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
       
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
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

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public int getAltura() {
        return altura;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
