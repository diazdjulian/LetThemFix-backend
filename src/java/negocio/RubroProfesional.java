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
public class RubroProfesional {

    private Long idProfesional;
    private Long idRubro;
    private String matricula;

    public RubroProfesional(Long idProfesional, Long idRubro, String matricula) {
        this.idProfesional = idProfesional;
        this.idRubro = idRubro;
        this.matricula = matricula;
    }
    
    public Long getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Long idRubro) {
        this.idRubro = idRubro;
    }

    public Long getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(Long idProfesional) {
        this.idProfesional = idProfesional;
    }    

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}
