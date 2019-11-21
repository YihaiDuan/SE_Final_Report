/**
 * 
 */
package AccountServlet;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.BookSon;
import entity.UserRecharge;
import entity.UserVipRecharge;
import util.HibernateUtil;

public class ReChargeDao {
	
	
	
	//保存充值记录
	public void SaveRecharge(UserRecharge  ur)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(ur);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	
	
	
	//保存Vip充值记录
	public void SaveVipRecharge(UserVipRecharge  ur)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(ur);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	
	
	
	
	
	
	
}
