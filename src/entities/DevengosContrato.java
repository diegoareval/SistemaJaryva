package entities;
// Generated 10-24-2019 05:29:51 PM by Hibernate Tools 4.3.1



/**
 * DevengosContrato generated by hbm2java
 */
public class DevengosContrato  implements java.io.Serializable {


     private Integer iddevengo;
     private ContratoTrabajo contratoTrabajo;
     private String devengospactados;

    public DevengosContrato() {
    }

    public DevengosContrato(ContratoTrabajo contratoTrabajo, String devengospactados) {
       this.contratoTrabajo = contratoTrabajo;
       this.devengospactados = devengospactados;
    }
   
    public Integer getIddevengo() {
        return this.iddevengo;
    }
    
    public void setIddevengo(Integer iddevengo) {
        this.iddevengo = iddevengo;
    }
    public ContratoTrabajo getContratoTrabajo() {
        return this.contratoTrabajo;
    }
    
    public void setContratoTrabajo(ContratoTrabajo contratoTrabajo) {
        this.contratoTrabajo = contratoTrabajo;
    }
    public String getDevengospactados() {
        return this.devengospactados;
    }
    
    public void setDevengospactados(String devengospactados) {
        this.devengospactados = devengospactados;
    }




}


