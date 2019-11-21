/**
 * 
 */
package IndexPageDao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import util.HibernateUtil;

public class AllVipDao {
	
	
	
public 	List<Book>  getAllVipBook()
{
	
	   Session session=HibernateUtil.getSession();
		Transaction	tx=session.beginTransaction();
	List<Book> list=null;	
	list=session.createQuery("from Book where freestatus=1 and vipfreestatus=1").list();


	
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
	
	
	
	


public 	List<Book>  getAllEleBook()
{

   Session session=HibernateUtil.getSession();
	Transaction	tx=session.beginTransaction();
List<Book> list=null;	
list=session.createQuery("from Book where elestatus=1").list();



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

	


public 	List<Book>  getAllFreeBook()
{

   Session session=HibernateUtil.getSession();
	Transaction	tx=session.beginTransaction();
List<Book> list=null;	
list=session.createQuery("from Book where freestatus=0").list();



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
