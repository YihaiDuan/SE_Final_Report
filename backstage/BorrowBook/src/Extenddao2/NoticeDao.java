/**
 * 
 */
package Extenddao2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Notice;
import util.HibernateUtil;

public class NoticeDao {
	
	
	
	//获取公告
	public  List<Notice>   getSystemNotice()
	{
		
		
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		List<Notice>  nlist=session.createQuery("from Notice where typestatus=0").list();
		
		
		if(nlist!=null&&nlist.size()>0)
		{
			
			   return nlist;
			
		}
		else
		{
			
			return  null;
		}
		
	}
	
	
	
	
	
}
