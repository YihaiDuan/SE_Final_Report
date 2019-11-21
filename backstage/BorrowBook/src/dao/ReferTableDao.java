package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.BorrowTable;
import entity.Refer;
import entity.ReferTable;
import util.HibernateUtil;


/**
 * 
 * @author hjm
 * @todo 推荐提醒数据访问层
 */
public class ReferTableDao {
	
	
	//添加推荐信息
	
	 public void SavaReferTable(ReferTable r)
	 {
		 Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			session.save(r);
		
			tx.commit();
			HibernateUtil.closeSession(session);
	
	 }
	
	 
	 
	 
	 
	 
	  public   List<ReferTable>  getReferData(String userid)
	  {
		  
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		  
	 List<ReferTable> list=session.createQuery("from ReferTable where userid=?").
						setParameter(0,userid).list();
		  
		 	  tx.commit();
		 	  HibernateUtil.closeSession(session);
	        
	         
	         return list;
		  
	      }
	  
	  
	  //更新用户打开过的推荐阅读
		public void UpReferStatus(ReferTable r)
		{
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			ReferTable r1=(ReferTable)session.get(ReferTable.class,r.getId());
			
			r1.setStatus(r.getStatus());
			
		    session.update(r1);
		    tx.commit();
			HibernateUtil.closeSession(session);
		
		}
	  
	  // 判断用户是否还有未浏览的推荐信息
		
		public List<ReferTable> getReferTableLook(String userid)
		{
			
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			
			List<ReferTable>  list=new ArrayList<ReferTable>(0);
     
			list=session.createQuery("from ReferTable where status=0  and userid=?").
						setParameter(0,userid).list();

          tx.commit();
	      HibernateUtil.closeSession(session);
	     
	     if(list.size()>0)
	     {
	    	 
	    	 return list;
	    	 
	     }
	     else
	     {
	    	 
	    	return  null;
	    	 
	     }
	     
 }
	
	
	//删除推荐
		public void DeleteRefer(int referid)
		{
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			
			ReferTable rt=null;
			
			rt=(ReferTable) session.get(ReferTable.class,referid);
			
			
			
			if(rt!=null)
			{
				session.delete(rt);
				
			}
			
	        tx.commit();
		      HibernateUtil.closeSession(session);
			
		}

}
