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
    private String tipoValorado;
    private float calificacion;
    private String detalle;
    private Long idValorador;
    private Long idValorado;

    public Valoracion(Long idValoracion, Long idValorado, Long idValorador,  String tipoValorado, String detalle, float calificacion) {
        this.idValoracion = idValoracion;
        this.tipoValorado = tipoValorado;
        this.calificacion = calificacion;
        this.detalle = detalle;
        this.idValorador = idValorador;
        this.idValorado = idValorado;
    }

    public Valoracion(Long idValorado, Long idValorador,  String tipoValorado, String detalle, float calificacion) {
        this.tipoValorado = tipoValorado;
        this.calificacion = calificacion;
        this.detalle = detalle;
        this.idValorador = idValorador;
        this.idValorado = idValorado;
    }

    public Long getIdValoracion() {
        return idValoracion;
    }

    public String getTipoValorado() {
        return tipoValorado;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public Long getIdValorador() {
        return idValorador;
    }

    public Long getIdValorado() {
        return idValorado;
    }

    public void setIdValoracion(Long idValoracion) {
        this.idValoracion = idValoracion;
    }

    public void setTipoValorado(String tipoValorado) {
        this.tipoValorado = tipoValorado;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public void setIdValorador(Long idValorador) {
        this.idValorador = idValorador;
    }

    public void setIdValorado(Long idValorado) {
        this.idValorado = idValorado;
    }
    
    
}
