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
public class Valoracion {
    
    private Long idValoracion;
    private float Calificacion;
    private String detalle;
    private Long idValorador;
    private Long idValorado;

    public Long getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(Long idValoracion) {
        this.idValoracion = idValoracion;
    }

    public float getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(float Calificacion) {
        this.Calificacion = Calificacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Long getIdValorador() {
        return idValorador;
    }

    public void setIdValorador(Long idValorador) {
        this.idValorador = idValorador;
    }

    public Long getIdValorado() {
        return idValorado;
    }

    public void setIdValorado(Long idValorado) {
        this.idValorado = idValorado;
    }
    
    
    
    
}
