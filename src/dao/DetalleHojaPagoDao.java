
package dao;

import daogeneral.DaoGeneral;
import entities.DetalleHojapago;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class DetalleHojaPagoDao extends DaoGeneral{
  SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = null;
     public List<DetalleHojapago> listAll() {
       
//lista que contendra todos los registros de todos los cargos
        List<DetalleHojapago> listado = null;
        
        //transaccion que permite ejecutar la consulta exitosamente o no ejecutarla
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            //hquery que permite obtener todos los registros
            Query query = session.createQuery("From DetalleHojapago");
            //permite llenar la lista de todos los bancos
            listado =(List<DetalleHojapago>) query.list();
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
}
