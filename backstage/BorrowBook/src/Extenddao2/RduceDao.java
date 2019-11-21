/**
 * 
 */
package Extenddao2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Reduce;
import entity.Topic;
import util.HibernateUtil;

public class RduceDao {
	
	public List<Reduce>  getAllReduce()
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<Reduce> list=session.createQuery("from Reduce").list();
		
		tx.commit();
	    HibernateUtil.closeSession(session);
	   if(list!=null&&list.size()>0)
	   {
	    return list;
	   }
	   else
	   {
		   
		   return null;
	   }
	}
	
	
	
	
	
	
}
