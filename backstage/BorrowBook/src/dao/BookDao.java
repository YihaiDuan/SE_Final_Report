package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entity.Book;

/**
 * 
 * @author hjm
 * @todo 图书书目录数据访问层
 * 
 */

public class BookDao {
	
	
	
/*public List<Book>  getAllBook()
{
	Session session=HibernateUtil.getSession();
	

       List<Book>  blist=session.createQuery("from Book").list();
       
       if(blist!=null&&blist.size()>0)
       {
    	   
    	   return blist;
       }
       else
       {
    	   
    	   return null;
       }
}

public void book(Book b)
{
	
	Session session=HibernateUtil.getSession();
	session.save(b);

}*/

	public boolean SaveBookData(Book  b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		try{
			
			session.save(b);
			tx.commit();
			return true;
		   
		  }
	    	catch(Exception e)
		   {
			e.printStackTrace();
			return false;
			
		   }
		finally
		{
		
		    HibernateUtil.closeSession(session);
			
			
		}
	
     }
	
	
	
	
	public List<Book> getBookAll()
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<Book> list=session.createQuery("from Book").setFirstResult(0).setMaxResults(30).list();
		
		tx.commit();
	  HibernateUtil.closeSession(session);
	   
	    return list;

	}
	
	public List<Book> getBookAll2()
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<Book> list=session.createQuery("from Book").list();
		
		tx.commit();
	  HibernateUtil.closeSession(session);
	   
	    return list;

	}
	
	//获取最新图书前4本
	public List<Book> getBookAllTop()
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<Book> list=session.createQuery("from Book").
				setFetchSize(0).setMaxResults(3).list();
		
		tx.commit();
	  HibernateUtil.closeSession(session);
	   
	    return list;

	}

	

	public  Book  getBookbyid(String id)
	{
		
	    Session session=HibernateUtil.getSession();
	    Transaction tx=session.beginTransaction();
		
	    Book b=(Book)session.get(Book.class,id);
	  
		tx.commit();
	 HibernateUtil.closeSession(session);
		
	     return b;
	
	}
	
	
	
	
	
	public List<Book> getBookbytypeid(String typeid)
	{
		
		
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
			  
   List<Book>  list=session.createQuery("from Book as b where b.category.categoryid =?").
						setParameter(0,typeid).list();
		
         tx.commit();
        HibernateUtil.closeSession(session);
		 
         return list;
		
	}
	

	

	//获取图书人气值
	public int getBooknum(String bookid)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		
	  Book c=(Book) session.createQuery("from Book   where bookid=?").
				setParameter(0,bookid).uniqueResult();
		
	    tx.commit();
	
	    
	    if(c!=null)
	    {
	    return c.getBooknum();

	    }
	    else
	    {
	    	
	    	return 0;
	    }
		

	}
	
	
	//更新图书人气值
	public void UpdateBooknum(Book c)
	{
		
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 Book c1=(Book)session.get(Book.class,c.getBookid());
		
	     c1.setBooknum(c.getBooknum());
	     
	     session.update(c1);

		    tx.commit();
		    HibernateUtil.closeSession(session);
		

	}
	

	//更新图书的评分
	public void UpdateBookEvaluate(Book c)
	{
		
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 Book c1=(Book)session.get(Book.class,c.getBookid());
		
	    c1.setEvaluatescore(c.getEvaluatescore());
	     
	     session.update(c1);

		    tx.commit();
		    HibernateUtil.closeSession(session);
		

	}
	
//获取好评图书
	
	//前3本
	public List<Book>  getGoodTop()
	{
		
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		
		
        List<Book>  list=
        		session.createQuery("from Book order by evaluatescore Desc").
        		setFirstResult(0).setMaxResults(3).list();
		
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
	

	
	public List<Book>  getGoodTopAll()
	{
		
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		
		
        List<Book>  list=
        		session.createQuery("from Book order by evaluatescore Desc").setFirstResult(0).setMaxResults(30).list();
		
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
	
	//根据人气从高到低排列
		public List<Book>  getBookbyBooknum()
		{
			
			 Session session=HibernateUtil.getSession();
			 Transaction tx=session.beginTransaction();
			
			
	        List<Book>  list=session.createQuery("from Book order by borrownum Desc").setFirstResult(0).setMaxResults(30).list();
			
	        tx.commit();
		  HibernateUtil.closeSession(session);
		    
		    return list;
		}
		
		
		//人气从高到低前3本
		public List<Book>  getBookbyBookTopnum()
		{
			
			 Session session=HibernateUtil.getSession();
			 Transaction tx=session.beginTransaction();
			
			
	        List<Book>  list=
	        		session.createQuery("from Book order by borrownum Desc").
	        		setFirstResult(0).setMaxResults(3).list();
			
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
		
		
		
		
		
		//判断是否有该图书
		public boolean   getBooleanBookbyBookid(String bookid)
		{
			
			  Session session=HibernateUtil.getSession();
			    Transaction tx=session.beginTransaction();
			    
			    Book b=null;
				
			    b=(Book)session.get(Book.class,bookid);
			    
			    tx.commit();
			    HibernateUtil.closeSession(session);
			
				if(b!=null)
				{
					
					
					return true;
					
				}
				else
				{
					
					return false;
					
				}
				
		}
		
		
		
	//根据isbn获取图示
		public Book getBookByisbn(String isbn)
		{
			
			  Session session=HibernateUtil.getSession();
			    Transaction tx=session.beginTransaction();
			Book b=null;
			
			b=(Book) session.createQuery("from Book where publishnumber=?").setParameter(0,isbn)
.uniqueResult();
			
			
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
		
		
 //上传的各类型书直接大于4本
	public List<Book>  getBookbyCategoryid(String categoryid)
	{
		
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		
			List<Book> blist=new ArrayList<Book>(); 
			
	blist=session.createQuery("from Book as b  where b.category.categoryid=? order by bookid asc").setParameter(0,categoryid)
			               .setFirstResult(0).
							setMaxResults(4).list();
		
		
	
	           tx.commit();
                HibernateUtil.closeSession(session);
                
		    if(blist!=null&&blist.size()>0)
		    {
		    	return blist;
		    }
		    else
		    {
		    	return null;
		    	
		    }
	
	}
		
		
		

	
	
	
	
}
