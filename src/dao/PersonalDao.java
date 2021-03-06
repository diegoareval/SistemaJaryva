
package dao;

import entities.Cargos;
import entities.CatPersonal;
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
public class PersonalDao extends daogeneral.DaoGeneral{
     SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = null;
    
    
     
      public List<CatPersonal> listAllParameter(String parameter) {
       //atributo que contendra los registros encontrados
        List<CatPersonal> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From CatPersonal where nombres like '%"+parameter+"%' or apellidos like '%"+parameter+"%'");
            listado =(List<CatPersonal>) query.list();
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
     
    
    
      public List<CatPersonal> listAll() {
       //atributo que contendra los registros encontrados
        List<CatPersonal> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From CatPersonal");
            listado =(List<CatPersonal>) query.list();
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
      
        public List<CatPersonal> Buscar(String codJaryva) {
       //atributo que contendra los registros encontrados
        List<CatPersonal> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From CatPersonal where codJaryva= '"+codJaryva+"'");
            listado =(List<CatPersonal>) query.list();
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

      public List<CatPersonal> Buscarpornombreyapellido(String cad) {
       //atributo que contendra los registros encontrados
        List<CatPersonal> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From CatPersonal where nombres= '"+cad+"' or apellidos= '"+cad+"'");
            listado =(List<CatPersonal>) query.list();
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
