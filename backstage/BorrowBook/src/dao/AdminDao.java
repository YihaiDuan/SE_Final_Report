package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import entity.Admin;
import entity.BorrowTable;
import entity.User;



/**
 * 
 * @author hjm
 * @todo 管理员数据访问层
 */

public class AdminDao {
	
	
	
	
	
	
	
		public boolean AdminLogin(String username,String password)
		{
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
		  Admin a=null;
			
		  a=(Admin) session.createQuery("from Admin where username=? and password=? ").
					setParameter(0,username).setParameter(1,password).uniqueResult();
			
		  
		  
		    tx.commit();
		    HibernateUtil.closeSession(session);
		  
		  
		  
	        if(a!=null)
	        {
	       
	        	return true;	
	        	
	        	
	        }
	        else
	        {
	           return false;
	        }
			
		}
		
		
		
		public Admin getAdminbyid(String username)
		{
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			
			Admin a=null;
			
			a=(Admin)session.createQuery("from Admin  where username=?").
 					setParameter(0,username).uniqueResult();
			
			
		    tx.commit();
		    HibernateUtil.closeSession(session);
			
			if(a!=null)
			{
				
				return a;
			}
			else
			{
				return null;
				
			}

		}
		
		
	
		

}
