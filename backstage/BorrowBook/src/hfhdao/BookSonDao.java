package hfhdao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entity.Book;
import entity.BookSon;
import util.HibernateUtil;

/**
 * 藏书信息书籍记录
 * */
public class BookSonDao {
  //根据id删除藏书信息
	public boolean deleteBoolSon(String booksonid)
	{
		Session session=HibernateUtil.getSession();
		BookSon bookson=null;
		try {
			session.beginTransaction();
			bookson=(BookSon) session.get(BookSon.class,booksonid);
			bookson.getBook().getBookson().remove(bookson);
			bookson.setBook(null);
			session.delete(bookson);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		finally{
			HibernateUtil.closeSession(session);
			
		}
		return true;
		
	}
	//添加bookson对象
	public boolean addBookSon(BookSon bookson){
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.save(bookson);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		finally{
			HibernateUtil.closeSession(session);
			
		}
		return true;
	}
	public BookSon getBookSon(String booksonid){
		Session session=HibernateUtil.getSession();
		BookSon bookson=null;
		try {
			session.beginTransaction();
			bookson=(BookSon) session.get(BookSon.class,booksonid);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
			
		}
		return bookson;
	}
	public Integer getBookSonNumber(String bookid){
		   BookDao bookdao=new BookDao();
		   Book book= bookdao.getIdBook(bookid);
		   
		   List<BookSon> booksons=new ArrayList<BookSon>(book.getBookson());
		   int number=0;
	      if(booksons!=null&&booksons.size()>=0)
	      {
          	number=booksons.size(); 
	      }
		  return number;		  
	    } 
    public Integer getBookSonDetailNumber()
    {
    	      Session session=HibernateUtil.getSession();
    	      Number number = null;
    	      try {
    	    	  session.beginTransaction();
    	    	  String hql="select count(*) from BookSon";
    			  number = (Number) session.createQuery(hql).uniqueResult();
    			  session.getTransaction().commit();
    		      } catch (Exception e) {
    			  e.printStackTrace();
    			  session.getTransaction().rollback();
    		      }
    		  return number.intValue();		
	
    }
	//通过bookid获得bookson的集合
	public List<BookSon> getListBook(String bookid)
	{
		Session session = HibernateUtil.getSession();
		List<BookSon> list = null;
		try {
			session.beginTransaction();
			String hql = "from BookSon as b where b.book.bookid =:bookid";
			list = session.createQuery(hql).setParameter("bookid",bookid).list();
			session.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
		}
	    return list;
	}
	public static void main(String[] args) {
		BookSonDao dao=new BookSonDao();
		System.out.println(dao.getListBook("1"));
		
		
		
	}
}
