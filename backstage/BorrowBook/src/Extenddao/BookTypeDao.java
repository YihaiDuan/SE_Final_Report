package Extenddao;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;
import entity.BookType;

//大类
public class BookTypeDao {
   //获得所有大类
	public List<BookType> getListBookType(){
		Session session = HibernateUtil.getSession();
		List<BookType> list = null;
		try {
			session.beginTransaction();
			String hql ="from BookType";
			list = session.createQuery(hql).list();
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
	//根据id获得大类对象
	public BookType getIdBookType(int id){
		Session session = HibernateUtil.getSession();
		BookType booktype = null;
		try {
			session.beginTransaction();
			booktype = (BookType) session.get(BookType.class,id);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
			
		}
		return booktype;
		
	}
	//根据名称获得大类对象
	public BookType getNameBookType(String name){
		Session session = HibernateUtil.getSession();
		BookType booktype = null;
		try {
			session.beginTransaction();
			String hql = "from BookType where name=:name";
			booktype = (BookType) session.createQuery(hql).setParameter("name", name).uniqueResult();
		    session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}
		finally{
			HibernateUtil.closeSession(session);
		}
		return booktype;
		
	}
	//添加大类
	public void addBookType(BookType booktype)
	{
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.save(booktype);
			session.getTransaction().commit();;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	
	
}
