
package daogeneral;

import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author DiegoArevalo
 */
public class DaoGeneral {
    //generar conexion con la base de datos
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = null;
    
    //metodo que permite guardar registros en la base de datos
     public String Save(Object objeto) {
        String SMS = "";
        session = factory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.save(objeto);
            SMS = "¡Registro agregado correctamente!";
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            SMS = "¡El registro no fue insertado correctamente error: " + e.getCause().getMessage();
        } finally {
            session.close();
        }
        return SMS;
    }
     //metodo que permite actualizar los registros en la base de datos
     public String Update(Object objeto) {
        String SMS = "";
        session = factory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.update(objeto);
            SMS = "Registro actualizado correctamente";
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            SMS = "¡El registro no fué actualizado correctamente error: " + e.getCause().getMessage();
        } finally {
            session.close();
        }
        return SMS;
    }
     //metodo que permite eliminar registros
     
      public String Delete(Object objeto) {
        String SMS = "";
        session = factory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();        
            session.delete(objeto);
            SMS = "¡Registro eliminado correctamente!";
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            SMS = "El registro no fué eliminadó correctamente error: " + e.getCause().getMessage();
        } finally {
            session.close();
        }
        return SMS;
    }

}
