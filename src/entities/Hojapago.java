package entities;
// Generated 10-24-2019 05:29:51 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Hojapago generated by hbm2java
 */
public class Hojapago  implements java.io.Serializable {


     private Integer idhoja;
     private Programaciones programaciones;
     private Set detalleHojapagos = new HashSet(0);

    public Hojapago() {
    }

	
    public Hojapago(Programaciones programaciones) {
        this.programaciones = programaciones;
    }
    public Hojapago(Programaciones programaciones, Set detalleHojapagos) {
       this.programaciones = programaciones;
       this.detalleHojapagos = detalleHojapagos;
    }
   
    public Integer getIdhoja() {
        return this.idhoja;
    }
    
    public void setIdhoja(Integer idhoja) {
        this.idhoja = idhoja;
    }
    public Programaciones getProgramaciones() {
        return this.programaciones;
    }
    
    public void setProgramaciones(Programaciones programaciones) {
        this.programaciones = programaciones;
    }
    public Set getDetalleHojapagos() {
        return this.detalleHojapagos;
    }
    
    public void setDetalleHojapagos(Set detalleHojapagos) {
        this.detalleHojapagos = detalleHojapagos;
    }




}


