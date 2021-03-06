
package dao;


import daogeneral.DaoGeneral;
import entities.Afp;
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
public class AfpDao extends DaoGeneral{
     SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = null;
    //metodo que permite listar todos los registros de la base de datos
    public List<Afp> listAll() {
       
//lista que contendra todos los registros de todos los bancos
        List<Afp> listado = null;
        
        //transaccion que permite ejecutar la consulta exitosamente o no ejecutarla
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            //hquery que permite obtener todos los registros
            Query query = session.createQuery("From Afp");
            //permite llenar la lista de todos los afp
            listado =(List<Afp>) query.list();
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
    public List<Afp> listAllParameter(String parameter) {
       //atributo que contendra los registros encontrados
        List<Afp> listado = null;
        Transaction tx = null;
        try {
            //abrir la conexion
            session = factory.openSession();
            tx = session.beginTransaction();
           //query que permite encontrar los registros por medio de una busqueda
            Query query = session.createQuery("From Afp where descripcion like '%"+parameter+"%' or cod_afp like '%"+parameter+"%'");
            listado =(List<Afp>) query.list();
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
