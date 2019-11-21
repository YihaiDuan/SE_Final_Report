
package Extenddao2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Discount;
import entity.GroupBook;
import entity.GroupMore;
import entity.Reduce;
import util.HibernateUtil;


public class ActivityDao 
{
	
	
	
	 public  List<Reduce>  getAllReduce()
	 {
		 
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 List<Reduce>  rlist=session.createQuery("from Reduce").list();
		 
		    tx.commit();
		    HibernateUtil.closeSession(session);
		 
		    return rlist;
		 
	
		 
	 }
	
	
	 
	 public List<Discount>   getAllDiscount()
	 {
		 
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 List<Discount>  dlist=session.createQuery("from Discount where showstatus=1  and status=0").list();
		 
		    tx.commit();
		    HibernateUtil.closeSession(session);
		 
		 return dlist;
		 
		 
	 }
	 
	 
	 public List<GroupBook>   getAllGroupBook()
	 {
		 
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		List<GroupBook>  glist=session.createQuery("select gb from GroupBook as gb inner join gb.groupmore as g  where g.showstatus=1").list();
//select r  from Reply  as r inner join r.comment as c  with  c.userid=?		 
		    tx.commit();
		    HibernateUtil.closeSession(session);
		 
		    return glist;
		 
		 
	 }
	 
	 
	 
	
	public static void main(String[] args)
	{
		
		   
		
		   ActivityDao ad=new ActivityDao();
		   
		   List<GroupBook> dlist=ad.getAllGroupBook();
		   
		   
		   if(dlist!=null)
		   {
			   
			   System.out.println(dlist.size());
			   
		   }
		
	
	}
	
	
	
}
