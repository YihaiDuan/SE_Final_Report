/**
 * 
 */
package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Collect;
import entity.ConfirmOrder;
import util.HibernateUtil;

public class ConfirmOrderDao
{
	
	
	//
	public void SaveConfireOrder(ConfirmOrder co)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		session.save(co);
		tx.commit();
	    HibernateUtil.closeSession(session);
	
	}
	
	 public List<ConfirmOrder>  getCollectByUserid(String adminname)
	  {
		  
		  
		  Session session=HibernateUtil.getSession();
		  Transaction tx=session.beginTransaction();
		  
		  
          List<ConfirmOrder> clist=session.createQuery("from ConfirmOrder  where adminname=?").
						setParameter(0,adminname).list();
		  
		  tx.commit();
          HibernateUtil.closeSession(session);
	
	      return clist;
		  
		  
	  }
	
	public static void main(String [] args)
	{
		
		ConfirmOrderDao cod=new ConfirmOrderDao();
		List<ConfirmOrder> clist=cod.getCollectByUserid("管理员");
		
		System.out.println(clist.size());
		
	}
	
}
