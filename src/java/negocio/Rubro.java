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
public class Rubro {

    private Long idRubro;
    private String descripcion;

    public Rubro(Long idRubro, String descripcion) {
        this.idRubro = idRubro;
        this.descripcion = descripcion;
    }
    
    public Rubro(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Long getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Long idRubro) {
        this.idRubro = idRubro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
