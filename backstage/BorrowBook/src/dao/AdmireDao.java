package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Admire;
import util.HibernateUtil;

/**
 * 
 * @author hjm
 * @todo 评论点赞数据访问层
 */


public class AdmireDao {
	
	
	
	

	 public void addAdmire(Admire a)
	{
		
		 
		 
		 
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			session.save(a);
			tx.commit();
		    HibernateUtil.closeSession(session);
		
		
		
	}
	
	
	
	 
	 //撤销赞
	 public void deleteAdmire(String  userid,int commentid)
		{
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			Admire	admire=null;
			
	         admire=(Admire)session.createQuery("from Admire as a where  a.userid=? and a.comment.commentid=?").
						setParameter(0,userid).setParameter(1, commentid).uniqueResult();	
			
			if(admire!=null)
			{
				
			  session.delete(admire);
		
			}
			
			tx.commit();
		    HibernateUtil.closeSession(session);

		}
	
	 
	
	
	
	   public boolean  getAdmirebyuserid(String userid,int commentid)
	   {
		   
		   Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			Admire	admire=null;
			
          admire=(Admire)session.createQuery("from Admire as a where  userid=? and a.comment.commentid=?").
					setParameter(0,userid).setParameter(1, commentid).uniqueResult();	
			
			
		   
			tx.commit();
		    HibernateUtil.closeSession(session);
		    
		    if(admire!=null)
		    {
		    	
		    	return true;
		    	
		    }
		    else
		    {
		    	
		    	
		    	return false;
		    }
		   
	   }
	
	

}
