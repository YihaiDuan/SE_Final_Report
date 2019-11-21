/**
 * 
 */
package Extenddao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.HotSearch;
import util.HibernateUtil;

public class HotSearchDao 
{
	
	
	
	public boolean SaveHotSearch(HotSearch  b)
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
	
	public HotSearch getHotSearchBook(String bookid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
HotSearch b=(HotSearch) session.createQuery("from HotSearch as hs where  hs.book.bookid=?").setParameter(0, bookid).uniqueResult();
	
		
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
	     
	
	public void UpdateHotnum(HotSearch hs)
	{
		
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 HotSearch c1=(HotSearch)session.get(HotSearch.class,hs.getId());
		
	     c1.setNum(hs.getNum());
	     
	     session.update(c1);

		    tx.commit();
		    HibernateUtil.closeSession(session);
		

	}
	
	//获取前10热搜
	public List<HotSearch> getTopSearch()
	{

		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
List<HotSearch> b=session.createQuery("from HotSearch").setFirstResult(0).setMaxResults(5).list();
	
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
		if(b!=null&&b.size()>0)
		{
			
			return b;
		}
		else
		{
			
			return null;
		}
		
		
		
		
	}
	
	
	
	
}
