package entities;
// Generated 10-24-2019 05:29:51 PM by Hibernate Tools 4.3.1



/**
 * DetalleActProgramaciones generated by hbm2java
 */
public class DetalleActProgramaciones  implements java.io.Serializable {


     private Integer iddetalleact;
     private Eventos eventos;
     private Programaciones programaciones;

    public DetalleActProgramaciones() {
    }

    public DetalleActProgramaciones(Eventos eventos, Programaciones programaciones) {
       this.eventos = eventos;
       this.programaciones = programaciones;
    }
   
    public Integer getIddetalleact() {
        return this.iddetalleact;
    }
    
    public void setIddetalleact(Integer iddetalleact) {
        this.iddetalleact = iddetalleact;
    }
    public Eventos getEventos() {
        return this.eventos;
    }
    
    public void setEventos(Eventos eventos) {
        this.eventos = eventos;
    }
    public Programaciones getProgramaciones() {
        return this.programaciones;
    }
    
    public void setProgramaciones(Programaciones programaciones) {
        this.programaciones = programaciones;
    }




}


