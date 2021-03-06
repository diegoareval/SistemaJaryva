package entities;
// Generated 10-24-2019 05:29:51 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Programaciones generated by hbm2java
 */
public class Programaciones  implements java.io.Serializable {


     private Integer idprogramaciones;
     private Movimientoprogramacion movimientoprogramacion;
     private Planillas planillas;
     private Programacion programacion;
     private Salones salones;
     private Date fecha;
     private String descripcion;
     private String pax;
     private String entrada;
     private String capitan;
     private Set hojapagos = new HashSet(0);
     private Set detalleActProgramacioneses = new HashSet(0);

    public Programaciones() {
    }

	
    public Programaciones(Movimientoprogramacion movimientoprogramacion, Planillas planillas, Programacion programacion, Salones salones, Date fecha, String descripcion, String pax, String entrada, String capitan) {
        this.movimientoprogramacion = movimientoprogramacion;
        this.planillas = planillas;
        this.programacion = programacion;
        this.salones = salones;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.pax = pax;
        this.entrada = entrada;
        this.capitan = capitan;
    }
    public Programaciones(Movimientoprogramacion movimientoprogramacion, Planillas planillas, Programacion programacion, Salones salones, Date fecha, String descripcion, String pax, String entrada, String capitan, Set hojapagos, Set detalleActProgramacioneses) {
       this.movimientoprogramacion = movimientoprogramacion;
       this.planillas = planillas;
       this.programacion = programacion;
       this.salones = salones;
       this.fecha = fecha;
       this.descripcion = descripcion;
       this.pax = pax;
       this.entrada = entrada;
       this.capitan = capitan;
       this.hojapagos = hojapagos;
       this.detalleActProgramacioneses = detalleActProgramacioneses;
    }
   
    public Integer getIdprogramaciones() {
        return this.idprogramaciones;
    }
    
    public void setIdprogramaciones(Integer idprogramaciones) {
        this.idprogramaciones = idprogramaciones;
    }
    public Movimientoprogramacion getMovimientoprogramacion() {
        return this.movimientoprogramacion;
    }
    
    public void setMovimientoprogramacion(Movimientoprogramacion movimientoprogramacion) {
        this.movimientoprogramacion = movimientoprogramacion;
    }
    public Planillas getPlanillas() {
        return this.planillas;
    }
    
    public void setPlanillas(Planillas planillas) {
        this.planillas = planillas;
    }
    public Programacion getProgramacion() {
        return this.programacion;
    }
    
    public void setProgramacion(Programacion programacion) {
        this.programacion = programacion;
    }
    public Salones getSalones() {
        return this.salones;
    }
    
    public void setSalones(Salones salones) {
        this.salones = salones;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getPax() {
        return this.pax;
    }
    
    public void setPax(String pax) {
        this.pax = pax;
    }
    public String getEntrada() {
        return this.entrada;
    }
    
    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }
    public String getCapitan() {
        return this.capitan;
    }
    
    public void setCapitan(String capitan) {
        this.capitan = capitan;
    }
    public Set getHojapagos() {
        return this.hojapagos;
    }
    
    public void setHojapagos(Set hojapagos) {
        this.hojapagos = hojapagos;
    }
    public Set getDetalleActProgramacioneses() {
        return this.detalleActProgramacioneses;
    }
    
    public void setDetalleActProgramacioneses(Set detalleActProgramacioneses) {
        this.detalleActProgramacioneses = detalleActProgramacioneses;
    }




}


