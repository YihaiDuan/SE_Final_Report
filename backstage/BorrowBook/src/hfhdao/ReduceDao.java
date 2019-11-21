package hfhdao;

import org.hibernate.Session;

import util.HibernateUtil;
import entity.Reduce;

public class ReduceDao {
	
	//根据id获得对象
	public Reduce getIdReduce(int id){
		Session session = HibernateUtil.getSession();
		Reduce reduce = null;
		try {
			session.beginTransaction();
			reduce = (Reduce) session.get(Reduce.class,id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
		}
	     return reduce;
	}
	public void deleteReduce(Reduce reduce){
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.delete(reduce);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
		}
	}
	public void updateReduce(Reduce reduce){
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.update(reduce);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
		}
	
	}

}
