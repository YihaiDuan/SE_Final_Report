package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.BookSon;
import util.HibernateUtil;

/**
 * 
 * @author hjm
 * @todo 图书分页显示数据访问层
 */
public class BookSonDao
{

	
	//子书添加
	public void SaveBookSonData(BookSon  bs)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(bs);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	
	
	public Object getTotalNumByBookid(String bookid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Object num=0;
		
		num=session.createQuery("select count(bs.book.bookid) from BookSon as bs where bs.book.bookid=?").setParameter(0,bookid).uniqueResult();
		
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
		return num;
		
	}
	
	
	
	
	
	public  Object  getBookSnoBorrowStatusByBookid(String bookid)
	{
	    Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Object num=0;
		
		num=session.createQuery("select count(bs.book.bookid) from BookSon as bs where bs.book.bookid=?  and borrowstatus=0  and orderstatus=0").setParameter(0,bookid).uniqueResult();
		
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
		return num;
	
	}
	
	
    
	public List<BookSon>  getAllBookSon(String bookid)
	{
		
		   Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			List<BookSon> blist=null;
			
			blist=session.createQuery("from BookSon as bs where bs.book.bookid=?").setParameter(0,bookid).list();
			
			
			tx.commit();
			HibernateUtil.closeSession(session);
			
			return blist;

	}
	
	 //获取该图书没有被借的数量
		public List<BookSon>  getNoBorrowBookSon(String bookid)
		{
			
			   Session session=HibernateUtil.getSession();
				Transaction tx=session.beginTransaction();
				
				List<BookSon> blist=null;
				
				blist=session.createQuery("from BookSon as bs where bs.book.bookid=? and borrowstatus=0 and orderstatus=0").setParameter(0,bookid).list();
				
				
				tx.commit();
				HibernateUtil.closeSession(session);
				
				return blist;

		}
		
	
	
	
	//获取没有被借和预订的第一个booksonid
	
	public BookSon  getNoBorrowNoOrderBookSon(String bookid)
	{
		
		   Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			List<BookSon> blist=new ArrayList<BookSon>(0);
			
			blist=session.createQuery("from BookSon as bs where bs.book.bookid=?  and borrowstatus=0 and orderstatus=0").setParameter(0,bookid).list();
			
			
			tx.commit();
			HibernateUtil.closeSession(session);
			
			if(blist!=null&&blist.size()>0)
			{
				BookSon bs=blist.get(0);
				
				return bs;
				
			}
			else
			{
				
				return null;
				
			}

	}
	
	
	public BookSon getBookSon(String booksonid)
	{
		
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 BookSon bs=null;
		
		bs=(BookSon)session.get(BookSon.class,booksonid);
		
		tx.commit();
		HibernateUtil.closeSession(session);
		 
		if(bs!=null)
		{
		return bs;
		}
		else
		{
			
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	

	
	public void UpdateBookBorrowStatus(BookSon b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		BookSon b1=(BookSon) session.get(BookSon.class,b.getBooksonid());
		
		b1.setBorrowstatus(b.getBorrowstatus());
		
	    session.update(b1);
	    
		 tx.commit();
		HibernateUtil.closeSession(session);
		 
		
	}
	
	
	
	
	public void UpdateBookOrderStatus(BookSon b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		BookSon b1=(BookSon)session.get(BookSon.class,b.getBooksonid());
		
	
		
	    session.update(b1);
		 tx.commit();
		
		 
	}
	
	//判断是否有该图书
			public boolean   getBooleanBookSonbyBookSonid(String booksonid)
			{
				
				  Session session=HibernateUtil.getSession();
				    Transaction tx=session.beginTransaction();
				    
				    BookSon b=null;
					
				    b=(BookSon)session.get(BookSon.class,booksonid);
				    
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
			
			
	

	
}
