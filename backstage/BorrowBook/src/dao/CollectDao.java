package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import entity.Collect;
import entity.OnlineOrder;
import util.HibernateUtil;


/**
 * 
 * @author hjm
 * @todo 图书收藏数据访问层
 */
public class CollectDao {
	
	
	
	//收藏添加
	public void SaveCollectData(Collect c)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(c);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	
	
	//判断该用户是否已经将该图书添加到收藏库
	
	  public boolean CollectBol(String userid,String bookid)
	  {
		  
		  Session session=HibernateUtil.getSession();
		  Transaction tx=session.beginTransaction();
		  
		  
		  Collect c=null;
		  
	 c=(Collect)session.createQuery("from Collect as c where userid=? and c.book.bookid=?").
					setParameter(0,userid).setParameter(1,bookid).uniqueResult();
		  
		  
		  
			tx.commit();
		    HibernateUtil.closeSession(session);
		  
		 if(c!=null)
		 {
			 
			 return   true; 
		 }
		 else
		 {
			 
			 return false;
		 }
		  
	  }
	  
	  
	  
		//判断该用户是否已经将该图书添加到收藏库获取对象
		
	  public Collect  CollectObject(String userid,String bookid)
	  {
		  
		  Session session=HibernateUtil.getSession();
		  Transaction tx=session.beginTransaction();
		  
		  
		  Collect c=null;
		  
	 c=(Collect)session.createQuery("from Collect as c where userid=? and c.book.bookid=?").
					setParameter(0,userid).setParameter(1,bookid).uniqueResult();
		  
		  
		  
			tx.commit();
		    HibernateUtil.closeSession(session);
		  
		 if(c!=null)
		 {
			 
			 return  c; 
		 }
		 else
		 {
			 
			 return null;
		 }
		
		  
	  }
	  
	  //删除收藏的书籍
	  public void deleteCollect(int id)
	  {
		  
		  
		  Session session=HibernateUtil.getSession();
		  Transaction tx=session.beginTransaction();
		  Collect c=null;
		  c=(Collect) session.get(Collect.class,id);
		  
		  if(c!=null)
		  {
			  session.delete(c);
			  
		  }
		  
		  tx.commit();
		    HibernateUtil.closeSession(session);
	  }
	  
	  
	  //获取用户收藏的图书
	  public List<Collect>  getCollectByUserid(String userid)
	  {
		  
		  
		  Session session=HibernateUtil.getSession();
		  Transaction tx=session.beginTransaction();
		  
		  
	List<Collect> clist=session.createQuery("from Collect  where userid=?").
						setParameter(0,userid).list();
		  
		  
	
	   tx.commit();
       HibernateUtil.closeSession(session);
	
	      return clist;
		  
		  
	  }
	  
	  
	  

}
