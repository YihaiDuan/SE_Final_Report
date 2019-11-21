package ReferSys;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.BookToBooks;
import entity.UserToBooks;
import entity.UserToUsers;
import util.HibernateUtil;

public class ReferDao 
{
	
	//获取推荐分页显示
    public UserToBooks  getUserToBooks(String userid)
	{
		
    	
    	Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
   UserToBooks  b= (UserToBooks) session.createQuery("from UserToBooks where userid =?")
				 .setParameter(0,userid).uniqueResult();
		
       
	tx.commit();
    HibernateUtil.closeSession(session);
    
    if(b!=null)
    {
    	
    	
    	return b;
    	
    }
    else
    {
    	
    	
    	return null;
    }
	}
    
    
    
    

	//获取相关推挤分页显示
    public BookToBooks  getBookToBooks(String bookid)
	{
		
    	
    	Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		BookToBooks  b= (BookToBooks) session.createQuery("from BookToBooks where bookid =?")
				 .setParameter(0,bookid).uniqueResult();
		
       
	tx.commit();
    HibernateUtil.closeSession(session);
    
    if(b!=null)
    {
    	
    	
    	return b;
    	
    }
    else
    {
    	
    	
    	return null;
    }
	}
    
    
    

	//获取相关推挤分页显示
    public UserToUsers  getUserToUsers(String userid)
	{
		
    	
    	Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		UserToUsers  b= (UserToUsers ) session.createQuery("from UserToUsers  where userid =?")
				 .setParameter(0,userid).uniqueResult();
		
       
	tx.commit();
    HibernateUtil.closeSession(session);
    
    if(b!=null)
    {
    	
    	
    	return b;
    	
    }
    else
    {
    	
    	
    	return null;
    }
	}
    
    
	
}
