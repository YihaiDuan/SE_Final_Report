package hfhdao;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;
import entity.BookSon;

/**
 * 书籍详细信息操作
 * 
 * */
public class DetailBookDao {

	//通过book_id获取书籍详细信息
	public List<BookSon> getDetalisList(int book_id){
		Session session=HibernateUtil.getSession();
		List<BookSon> list=null;
		try {
			session.beginTransaction();
			String hql="from BookSon as b where b.book.book_id=:book_id ";
			list=session.createQuery(hql).setInteger("book_id", book_id).list();
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
	//删除书籍信息
	public boolean deleteDailsBook(int booksonid){
		Session session=HibernateUtil.getSession();
		BookSon bookson=null;
		try {
			session.beginTransaction();
			bookson=(BookSon)session.get(BookSon.class,booksonid);
			session.delete(bookson);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
		}
	    return true;
	}
	
	
	
	
}
