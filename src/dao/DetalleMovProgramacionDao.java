
package dao;

import daogeneral.DaoGeneral;
import entities.DetalleMovimientoProgramacion;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author DiegoArevalo
 */
public class DetalleMovProgramacionDao extends DaoGeneral{
     //Establecer conexion con la base de datos
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = null;
     public List<DetalleMovimientoProgramacion> listAll() {
       
//lista que contendra todos los registros de todos los cargos
        List<DetalleMovimientoProgramacion> listado = null;
        
        //transaccion que permite ejecutar la consulta exitosamente o no ejecutarla
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            //hquery que permite obtener todos los registros
            Query query = session.createQuery("From DetalleMovimientoProgramacion");
            //permite llenar la lista de todos los bancos
            listado =(List<DetalleMovimientoProgramacion>) query.list();
            //permite ejecutar la consulta
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                //en caso de excepcion permite volver a el estado normal
                tx.rollback();
            }
            System.out.println("Excepcion: "+e.getMessage());
        } finally {
            session.close();
        }
        return listado;
    }
     
     public List<DetalleMovimientoProgramacion> Buscar(int cod) {
       //atributo que contendra los registros encontrados
        List<DetalleMovimientoProgramacion> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From DetalleMovimientoProgramacion where id= '"+cod+"'");
            listado =(List<DetalleMovimientoProgramacion>) query.list();
            //permite ejecutar toda la consulta
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                //permite volver al estado normal
                tx.rollback();
            }
            System.out.println("Error: "+e.getMessage());
        } finally {
            session.close();
        }
        return listado;    
    }
     public List<DetalleMovimientoProgramacion> listAllParameter(String parameter) {
       //atributo que contendra los registros encontrados
        List<DetalleMovimientoProgramacion> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From DetalleMovimientoProgramacion where id like '%"+parameter+"%' or idpersonal like '%"+parameter+"%' or fecha like '%"+parameter+"%' or idactividad like '%"+parameter+"%' or horas like '%"+parameter+"%' or precio like '%"+parameter+"%' or valor like '%"+parameter+"%' or idmovimiento like '%"+parameter+"%'");
            listado =(List<DetalleMovimientoProgramacion>) query.list();
            //permite ejecutar toda la consulta
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                //permite volver al estado normal
                tx.rollback();
            }
            System.out.println("Error: "+e.getMessage());
        } finally {
            session.close();
        }
        return listado;
    }
    
}
