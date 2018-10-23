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
    
    private Long idProblema;
    private String descripcion;
    private Rubro rubro;
    private Presupuesto presupuestoAceptado;
    private Trabajo trabajo;

    public Problema(Long idProblema, String descripcion, Long idRubro, Long idPresupuesto, Long idTrabajo){
        
        this.idProblema = idProblema;
        this.descripcion = descripcion;
        
    
    }
    
    public Long getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(Long idProblema) {
        this.idProblema = idProblema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public Presupuesto getPresupuestoAceptado() {
        return presupuestoAceptado;
    }

    public void setPresupuestoAceptado(Presupuesto presupuestoAceptado) {
        this.presupuestoAceptado = presupuestoAceptado;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }
    
    
    
}
