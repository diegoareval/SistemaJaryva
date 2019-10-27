
package dao;

import entities.ContratoTrabajo;
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
public class ContratoDao extends daogeneral.DaoGeneral{
   SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = null;
    
     public List<ContratoTrabajo> listAll() {
       
//lista que contendra todos los registros de todos los cargos
        List<ContratoTrabajo> listado = null;
        
        //transaccion que permite ejecutar la consulta exitosamente o no ejecutarla
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            //hquery que permite obtener todos los registros
            Query query = session.createQuery("From ContratoTrabajo");
            //permite llenar la lista de todos los bancos
            listado =(List<ContratoTrabajo>) query.list();
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
    
      public List<ContratoTrabajo> listAllParameter(String parameter) {
       //atributo que contendra los registros encontrados
        List<ContratoTrabajo> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From ContratoTrabajo where cod_personal like '%"+parameter+"%'");
            listado =(List<ContratoTrabajo>) query.list();
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
      
        public List<ContratoTrabajo> Buscar(int codpersonal) {
       //atributo que contendra los registros encontrados
        List<ContratoTrabajo> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From ContratoTrabajo where cod_personal= '"+codpersonal+"'");
            listado =(List<ContratoTrabajo>) query.list();
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

      public List<ContratoTrabajo> Buscarpornombreyapellido(String cad) {
       //atributo que contendra los registros encontrados
        List<ContratoTrabajo> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From CatPersonal where cod_personal= '"+cad+"'");
            listado =(List<ContratoTrabajo>) query.list();
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
