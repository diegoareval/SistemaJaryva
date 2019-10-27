
package dao;


import daogeneral.DaoGeneral;
import entities.Eventos;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class EventoDao extends DaoGeneral{
     SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = null;
    
     public List<Eventos> listAll() {
       
//lista que contendra todos los registros de todos los bancos
        List<Eventos> listado = null;
        
        //transaccion que permite ejecutar la consulta exitosamente o no ejecutarla
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            //hquery que permite obtener todos los registros
            Query query = session.createQuery("From Eventos");
            //permite llenar la lista de todos los bancos
            listado =(List<Eventos>) query.list();
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
     //metodo que permite realizar una busqueda de registros por medio de un parametro
    public List<Eventos> listAllParameter(String parameter) {
       //atributo que contendra los registros encontrados
        List<Eventos> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From Eventos where descripcion like '%"+parameter+"%' or idevento like '%"+parameter+"%'");
            listado =(List<Eventos>) query.list();
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
