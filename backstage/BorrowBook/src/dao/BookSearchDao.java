package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entity.Book;


/**
 * 
 * @author hjm
 * @todo 图书搜索数据访问层
 */
public class BookSearchDao 
{
	public List<Book>  getBookbyKeyPage(String search)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
	
List<Book>  blist= session.createQuery("from Book where booktitle like ? ")
				 .setParameter(0,"%"+search+"%")
			 .setFirstResult(0).setMaxResults(10).list();
		
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		return blist;
	
	}
	
	
	
	
	
	
	
	
	

	public List<Book>  getBookbyKey(String search)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
	
List<Book>  blist= session.createQuery("from Book where booktitle like ?")
				 .setParameter(0,"%"+search+"%").list();
		
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		return blist;
	
	}
	
	
	
	public List<Book>  getBookbyBooktitle(String search)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
	
List<Book>  blist= session.createQuery("from Book where booktitle like ?")
				 .setParameter(0,"%"+search+"%")
			 .list();
		
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		return blist;
	
	}
	
	
	
	
	public List<Book>  getBookbyAuthor(String search)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
	
List<Book>  blist= session.createQuery("from Book where author like ?")
				 .setParameter(0,"%"+search+"%")
			 .list();
		
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		return blist;
	
	}
	
	
	
	
	public List<Book>  getBookbyBooksonid(String search)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
	
List<Book>  blist= session.createQuery("from Book where bookid = ?")
				 .setParameter(0,search)
			 .list();
		
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		return blist;
	
	}
	
	
	
	
	
	
	
	
	//根据搜索记录搜索
		public List<Book>  getBookbyAll(String search)
		{
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
		
	List<Book>  blist= session.createQuery("from Book where author like ? or  bookid=?  or booktitle like ?  or chinesespelling like ?")
					 .setParameter(0,"%"+search+"%").setParameter(1,search).setParameter(2,"%"+search+"%").setParameter(3,"%"+search+"%")
				 .list();
			
			
			tx.commit();
		    HibernateUtil.closeSession(session);
			
			return blist;
		
		}
		
	
	
	

	
	
	
	

}
