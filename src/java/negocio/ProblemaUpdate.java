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
    public class ProblemaUpdate {
    
	private Long idProblema;
        private Long idPresupuesto;

    public ProblemaUpdate(Long idProblema, Long idPresupuesto) {
        this.idProblema = idProblema;
        this.idPresupuesto = idPresupuesto;
    }

    public Long getIdProblema() {
        return idProblema;
    }

    public Long getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdProblema(Long idProblema) {
        this.idProblema = idProblema;
    }

    public void setIdPresupuesto(Long idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

        
}
