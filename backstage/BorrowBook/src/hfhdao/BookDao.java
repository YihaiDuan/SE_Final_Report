package hfhdao;

import java.util.List;

import org.apache.commons.collections.functors.ForClosure;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.cfg.HbmBinder;

import entity.Book;
import entity.BookSon;
import util.HibernateUtil;

public class BookDao {
	public void deleteBook(String Bookid){
		Session session=HibernateUtil.getSession();
		Book book=null;
		try {
			session.beginTransaction();
			book=(Book) session.get(Book.class, Bookid);
			book.getCategory().getBook().remove(book);
			book.setCategory(null);
			session.delete(book);
			session.getTransaction().commit();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
			
		}
	}
	public void addBook(Book book)
	{
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.save(book);
			session.getTransaction().commit();
			HibernateUtil.closeSession(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	public Book getpublishnumberBook(String publishnumber){
		Session session=HibernateUtil.getSession();
		Book book=null;
		try {
			session.beginTransaction();
			String hql="from Book where publishnumber=:publishnumber";
			book=(Book)session.createQuery(hql).setString("publishnumber", publishnumber).uniqueResult();
			session.getTransaction().commit();
			HibernateUtil.closeSession(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return book;
		
		
		
	}
	//根据书名查询book
	public List<Book> getBooktitle(String booktitle){
	     Session session = HibernateUtil.getSession();
	     List<Book> list = null;
	     try {
			session.beginTransaction();
			String hql = "from Book where booktitle like :booktitle";
			list = session.createQuery(hql).setParameter("booktitle","%"+booktitle+"%").list();
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
	//通过Bookid获得book对象
	public Book getIdBook(String bookid)
	{
	Session session=HibernateUtil.getSession();
	Book book=null;
	try {
		session.beginTransaction();
		String hql="from Book where  bookid=:bookid";
		book=(Book)session.createQuery(hql).setString("bookid",  bookid).uniqueResult();
		Hibernate.initialize(BookSon.class);
		session.getTransaction().commit();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		session.getTransaction().rollback();
	}
	finally{
		
		HibernateUtil.closeSession(session);
	}
	return book;
	}
	//通过topicbookid获得book对象
		public Book getIdTopic(String bookid)
		{
		Session session=HibernateUtil.getSession();
		Book book=null;
		try {
			session.beginTransaction();
			String hql="from Book as b where  b.topicbook.id=:bookid";
			book=(Book)session.createQuery(hql).setString("bookid",  bookid).uniqueResult();
			Hibernate.initialize(BookSon.class);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			
			HibernateUtil.closeSession(session);
		}
		return book;
		}
	//更新book数据
	public void UpdateBook(Book book){
		Session session=HibernateUtil.getSession();
		
		try {
		  session.beginTransaction();
		  session.update(book);
		  session.getTransaction().commit();
		  	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		   finally{
			   HibernateUtil.closeSession(session);
		   }
	}
	//获得Topic下的所有book
	public List<Book> getTopicBook(int id){
		Session session = HibernateUtil.getSession();
		List<Book> list = null;
		try {
			session.beginTransaction();
			String sql = "select book.* from topic,topicbook,book where book.bookid=topicbook.book_id and topicbook.topic_id=topic.id and topic.id=:id";
			list=((SQLQuery) session.createSQLQuery(sql).setParameter("id",id)).addEntity(Book.class).list();
			session.getTransaction().rollback();
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
		/*   Book book=null;
		   BookDao bookdao=new BookDao();
           book=bookdao.getpublishnumberBook("2");
           System.out.println(book+"��Һ�");
        System.out.println(book.getCategory().getCategoryid());
		   bookdao.deleteBook("1"); */
//		 String bookid="1";
//         BookDao bookdao=new BookDao();
//         Book book=bookdao.getIdBook(bookid); 
//         System.out.println(book);   
		 BookDao bookdao=new BookDao();
		 Book book = bookdao.getIdBook("AA0001");
		 System.out.println(book);
	}


}
