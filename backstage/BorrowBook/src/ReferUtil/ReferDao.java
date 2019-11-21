/**
 * 
 */
package ReferUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import util.HibernateUtil;

public class ReferDao {
	
	
	//根据类型获取前6本
	public List<Book>  getBookByCateSix(String cateid)
	{
		
		Session session =HibernateUtil.getSession();
		
		Transaction tx=session.beginTransaction();
		List<Book>  blist=session.createQuery("from Book as b where  b.category.categoryid=?")
				.setParameter(0,cateid).setFirstResult(0).setMaxResults(6).list();
	
		tx.commit();
		HibernateUtil.closeSession(session);
		
		if(blist!=null&&blist.size()>0)
		{
			return blist;
		}else
		{
			
			return null;
		}
	}
	
	
	
	//获取类型图书3本
	//根据类型获取前6本
		public List<Book>  getBookByCateThree(String cateid)
		{
			
			Session session =HibernateUtil.getSession();
			
			Transaction tx=session.beginTransaction();
			List<Book>  blist=session.createQuery("from Book as b where  b.category.categoryid=?")
					.setParameter(0,cateid).setFirstResult(0).setMaxResults(3).list();
		
			tx.commit();
			HibernateUtil.closeSession(session);
			
			if(blist!=null&&blist.size()>0)
			{
				return blist;
			}else
			{
				
				return null;
			}
		}
		
	
	
}
