package entities;
// Generated 10-24-2019 05:29:51 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Eventos generated by hbm2java
 */
public class Eventos  implements java.io.Serializable {


     private Integer idevento;
     private Programacion programacion;
     private String descripcion;
     private Double precioA;
     private Double precioB;
     private Double precioC;
     private Set detalleActProgramacioneses = new HashSet(0);

    public Eventos() {
    }

	
    public Eventos(Programacion programacion) {
        this.programacion = programacion;
    }
    public Eventos(Programacion programacion, String descripcion, Double precioA, Double precioB, Double precioC, Set detalleActProgramacioneses) {
       this.programacion = programacion;
       this.descripcion = descripcion;
       this.precioA = precioA;
       this.precioB = precioB;
       this.precioC = precioC;
       this.detalleActProgramacioneses = detalleActProgramacioneses;
    }
   
    public Integer getIdevento() {
        return this.idevento;
    }
    
    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }
    public Programacion getProgramacion() {
        return this.programacion;
    }
    
    public void setProgramacion(Programacion programacion) {
        this.programacion = programacion;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Double getPrecioA() {
        return this.precioA;
    }
    
    public void setPrecioA(Double precioA) {
        this.precioA = precioA;
    }
    public Double getPrecioB() {
        return this.precioB;
    }
    
    public void setPrecioB(Double precioB) {
        this.precioB = precioB;
    }
    public Double getPrecioC() {
        return this.precioC;
    }
    
    public void setPrecioC(Double precioC) {
        this.precioC = precioC;
    }
    public Set getDetalleActProgramacioneses() {
        return this.detalleActProgramacioneses;
    }
    
    public void setDetalleActProgramacioneses(Set detalleActProgramacioneses) {
        this.detalleActProgramacioneses = detalleActProgramacioneses;
    }




}

