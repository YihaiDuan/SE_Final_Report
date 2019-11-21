/**
 * 
 */
package UserAction;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.BookSon;
import entity.UserBrowse;
import entity.UserSearch;
import util.HibernateUtil;

public class UserActionDao 
{
	
	public void SaveUserBrowse(UserBrowse  bs)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(bs);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	
	
	public void SaveUserSearch(UserSearch  bs)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(bs);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	
	
	public UserBrowse  getUserBrowse(String userid,String bookid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		 UserBrowse  ub=(UserBrowse) session.createQuery("from UserBrowse as ub where ub.user.userid=? and ub.book.bookid=? ").setParameter(0, userid)
.setParameter(1,bookid).uniqueResult();
		
		 
		 
		 tx.commit();
		HibernateUtil.closeSession(session);
		 
		 
		 
		 if(ub!=null)
		 {
			 
			 return ub;
		 }else
		 {
			 return null;
			 
		 }
		
	}
	
	
	
	public UserSearch  getUserSearch(String userid,String bookid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		 UserSearch  ub=(UserSearch) session.createQuery("from UserSearch as ub where ub.user.userid=? and ub.book.bookid=? ").setParameter(0, userid)
.setParameter(1,bookid).uniqueResult();
		
		 
		 
		 tx.commit();
		HibernateUtil.closeSession(session);
		 
		 
		 
		 if(ub!=null)
		 {
			 
			 return ub;
		 }else
		 {
			 return null;
			 
		 }
		
	}
	
	
	public void UpdateUserBrowseNum(UserBrowse b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		UserBrowse b1=(UserBrowse) session.get(UserBrowse.class,b.getId());
		
		b1.setNum(b.getNum());
		
	    session.update(b1);
	    
		 tx.commit();
		HibernateUtil.closeSession(session);
		 
		
	}
	
	
	
	public void UpdateUserSearchNum(UserSearch b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		UserSearch b1=(UserSearch) session.get(UserSearch.class,b.getId());
		
		b1.setNum(b.getNum());
		
	    session.update(b1);
	    
		 tx.commit();
		HibernateUtil.closeSession(session);
		 
		
	}
	
	
	
}
