
package EleBookDao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.MyEleBook;
import entity.User;
import util.HibernateUtil;

public class EleBookDao {
	
	
	
	public boolean BolOwnEleBook(String userid,String bookid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<MyEleBook>  mlist=
				session.
				createQuery("from MyEleBook as my where my.user.userid=? and my.book.bookid=?  and addstatus=1")
				.setParameter(0,userid).setParameter(1,bookid).list();
	
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		if(mlist!=null&&mlist.size()>0)
		{
			
			return true;
			
			
		}
		else
		{
			return false;
			
		}
	
	}
	
	//判断是否已经添加到书架
	public boolean BolMyEleBook(String userid,String bookid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<MyEleBook>  mlist=
				session.
				createQuery("from MyEleBook as my where my.user.userid=? and my.book.bookid=?")
				.setParameter(0,userid).setParameter(1,bookid).list();
	
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		if(mlist!=null&&mlist.size()>0)
		{
			
			return true;
			
			
		}
		else
		{
			return false;
			
		}
	
	}
	
	
	
	
	//判断要购买的电子书是否已经在书架中
	public MyEleBook  getMyEleBook(String userid,String bookid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		MyEleBook ele=null;
		
		
		ele=(MyEleBook) session.
				createQuery("from MyEleBook as my where my.user.userid=? and my.book.bookid=?  and addstatus=0")
				.setParameter(0,userid).setParameter(1,bookid).uniqueResult();
	
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
	    
		if(ele!=null)
		{
			
			return ele;
			
			
		}
		else
		{
			return null;
			
		}
	
	}
	
	
	//更新addstatus标志位
	

	
	public void UpdateMyEleBookAddStatus(MyEleBook  my)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		MyEleBook my1=(MyEleBook)session.get(MyEleBook.class,my.getId());
		
		my1.setAddstatus(my.getAddstatus());
		
	     session.update(my1);
		 tx.commit();
		 HibernateUtil.closeSession(session);
		 
	}
	
	
	
	//添加电子书到书架
	
	public void SaveMyEleBook(MyEleBook  my)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(my);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	
	
	//获取用户的书架全部图书
	public 	List<MyEleBook> getAllMyEleBook(String userid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<MyEleBook>  mlist=
				session.
				createQuery("from MyEleBook as my where my.user.userid=?")
				.setParameter(0,userid).list();
	
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		if(mlist!=null&&mlist.size()>0)
		{
			
			return mlist;
			
			
		}
		else
		{
			return null;
			
		}
	
	}
	
	
}
