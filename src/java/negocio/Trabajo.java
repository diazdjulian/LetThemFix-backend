/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.Date;

/**
 *
 * @author Sebas
 */
public class Trabajo {

    private Long idTrabajo;
    private Date fechaAceptacion;
    private Date fechaInicio;
    private Date fechaFin;
    private String observaciones;
    private String estado;

    public Trabajo(Long idTrabajo, Date fechaAceptacion, Date fechaInicio, Date fechaFin, String observaciones, String estado) {
        this.idTrabajo = idTrabajo;
        this.fechaAceptacion = fechaAceptacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    public Trabajo(Date fechaAceptacion, Date fechaInicio, Date fechaFin, String observaciones, String estado) {
        this.fechaAceptacion = fechaAceptacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    public Long getIdTrabajo() {
        return idTrabajo;
    }

    public Date getFechaAceptacion() {
        return fechaAceptacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdTrabajo(Long idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public void setFechaAceptacion(Date fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
