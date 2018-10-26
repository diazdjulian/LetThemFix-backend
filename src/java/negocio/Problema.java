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
    public class Problema {
    
	private int idProblema;
	private Cliente cliente;
	private String titulo;
	private String descripcion;
	private int presupuestoMinimo;
	private int presupuestoMaximo;
	private String zona;
	private Rubro rubro;
	private Presupuesto presupuesto;
	private Trabajo trabajo;

    public Problema(int idProblema, Cliente cliente, String titulo, String descripcion, int presupuestoMinimo, int presupuestoMaximo, String zona, Rubro rubro, Presupuesto presupuesto, Trabajo trabajo) {
        this.idProblema = idProblema;
        this.cliente = cliente;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.presupuestoMinimo = presupuestoMinimo;
        this.presupuestoMaximo = presupuestoMaximo;
        this.zona = zona;
        this.rubro = rubro;
        this.presupuesto = presupuesto;
        this.trabajo = trabajo;
    }

    public Problema(Cliente cliente, String titulo, String descripcion, int presupuestoMinimo, int presupuestoMaximo, String zona, Rubro rubro, Presupuesto presupuesto, Trabajo trabajo) {
        this.cliente = cliente;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.presupuestoMinimo = presupuestoMinimo;
        this.presupuestoMaximo = presupuestoMaximo;
        this.zona = zona;
        this.rubro = rubro;
        this.presupuesto = presupuesto;
        this.trabajo = trabajo;
    }

    public int getIdProblema() {
        return idProblema;
    }

    public Cliente getCliente() {
        return cliente;
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

    public Rubro getRubro() {
        return rubro;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }
        
    
}
