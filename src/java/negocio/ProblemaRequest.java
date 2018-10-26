/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Sebas
 */
    public class ProblemaRequest {
    
	private Long idCliente;
	private String titulo;
	private String descripcion;
	private int presupuestoMinimo;
	private int presupuestoMaximo;
	private String zona;
	private Long idRubro;

    public ProblemaRequest(Long idCliente, String titulo, String descripcion, int presupuestoMinimo, int presupuestoMaximo, String zona, Long idRubro) {
        this.idCliente = idCliente;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.presupuestoMinimo = presupuestoMinimo;
        this.presupuestoMaximo = presupuestoMaximo;
        this.zona = zona;
        this.idRubro = idRubro;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPresupuestoMinimo() {
        return presupuestoMinimo;
    }

    public int getPresupuestoMaximo() {
        return presupuestoMaximo;
    }

    public String getZona() {
        return zona;
    }

    public Long getIdRubro() {
        return idRubro;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPresupuestoMinimo(int presupuestoMinimo) {
        this.presupuestoMinimo = presupuestoMinimo;
    }

    public void setPresupuestoMaximo(int presupuestoMaximo) {
        this.presupuestoMaximo = presupuestoMaximo;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setIdRubro(Long idRubro) {
        this.idRubro = idRubro;
    }
    
        
}
