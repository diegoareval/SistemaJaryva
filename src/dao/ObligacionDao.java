
package dao;

import daogeneral.DaoGeneral;
import entities.ObligacionesContrato;
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
public class ObligacionDao extends DaoGeneral{
    //Establecer conexion con la base de datos
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = null;
    
     public List<ObligacionesContrato> listAll() {
       
//lista que contendra todos los registros de todos los cargos
        List<ObligacionesContrato> listado = null;
        
        //transaccion que permite ejecutar la consulta exitosamente o no ejecutarla
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            //hquery que permite obtener todos los registros
            Query query = session.createQuery("From ObligacionesContrato");
            //permite llenar la lista de todos los bancos
            listado =(List<ObligacionesContrato>) query.list();
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
    
  
    
     public List<ObligacionesContrato> Buscar(int cod) {
       //atributo que contendra los registros encontrados
        List<ObligacionesContrato> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From ObligacionesContrato where idobligaciones= '"+cod+"'");
            listado =(List<ObligacionesContrato>) query.list();
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
      public List<ObligacionesContrato> BuscarporContrato(int cod) {
       //atributo que contendra los registros encontrados
        List<ObligacionesContrato> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From ObligacionesContrato where idcontrato= '"+cod+"'");
            listado =(List<ObligacionesContrato>) query.list();
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
