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
public class Presupuesto {
    
    private Long idPresupuesto;
    private String observacion;
    private float valor;
    private int cantJornadasLaborables;
    private float valorMateriales;

    public Long getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Long idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getCantJornadasLaborables() {
        return cantJornadasLaborables;
    }

    public void setCantJornadasLaborables(int cantJornadasLaborables) {
        this.cantJornadasLaborables = cantJornadasLaborables;
    }

    public float getValorMateriales() {
        return valorMateriales;
    }

    public void setValorMateriales(float valorMateriales) {
        this.valorMateriales = valorMateriales;
    }
    
    
    
}
