package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.OnlineOrder;
import entity.OnlineWarn;
import util.HibernateUtil;



/**
 * 
 * @author hjm
 * @todo 预订提醒数据访问层
 */
public class OnlineWarnDao 
{

	
	
	
	//添加提醒
	public void   SavaOrderWarn(OnlineWarn o)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(o);
	
		tx.commit();
	    HibernateUtil.closeSession(session);
		

	}
	
	//有书提醒
	
	 public boolean  OrderWarn(String userid,String bookid)
	    {
	   	 
	   	 Session session=HibernateUtil.getSession();
	   	 Transaction tx=session.beginTransaction();
	   	 
	   	 OnlineWarn bt=null;
	   	  
	  bt=(OnlineWarn)session.createQuery("from OnlineWarn  as o  where o.user.userid =? and o.book.bookid=?  and status=0").
	   					setParameter(0,userid).setParameter(1,bookid).uniqueResult();
	   	  
	  
	             tx.commit();
	             HibernateUtil.closeSession(session);
	  
	   	 	if(bt!=null)
	   	 	{
	   	 		
	   	 		return true;
	   	 		
	   	 	}
	   	 	else
	   	 	{
	   	 		
	   	 		return false;
	   	 		
	   	 	}
	  
	    }
	
	 //根据图书id进行查看是否有人进行有书提醒
	 
	 public List<OnlineWarn> getOrderByidWarn(String bookid)
	    {
	    	
	    	 Session session=HibernateUtil.getSession();
	       	 Transaction tx=session.beginTransaction();
	    	
	       	 
	       	 List<OnlineWarn> olist=new ArrayList<OnlineWarn>(0);
	      	  
	    olist=session.createQuery("from OnlineWarn  as o  where  o.book.bookid=?  and status=0").
	          					setParameter(0,bookid).list();
	          	  
	         
	                    tx.commit();
	                    HibernateUtil.closeSession(session);
	    	
	    	
	       	 if(olist.size()>0)
	       	 {
	       		 return olist;
	       		 
	       		
	       	 }
	       	 else
	       	 {
	       		 
	       		 
	       		 return null;
	       	 }
	    }
	    	
	 
	       //更新有书提醒的标志位
	         public void UpdateOnlinWarnStatus(OnlineWarn o)
	         {
	        	 
	        	 
	        	 Session session=HibernateUtil.getSession();
	        	 Transaction tx=session.beginTransaction();
	        	 
	        	 OnlineWarn o1=(OnlineWarn)session.get(OnlineWarn.class,o.getId());
	        	 
	        	 o1.setStatus(o.getStatus());
	        	
	        	 session.update(o1);
	        	 
	        	 tx.commit();
	        	
	          HibernateUtil.closeSession(session);
	        	 
	         }
	 
	   
	 
}
