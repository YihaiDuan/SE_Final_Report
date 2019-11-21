package hfhdao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;






import entity.Admin;
import entity.Book;
import entity.Page;
import entity.User;
import util.HibernateUtil;


public class UserPageDao
{
	
	
	public Page<User> findByPage(int pc) 
	{
		
		int ps=10; 
		int all=0;
		
		
			
		
	     Session session=HibernateUtil.getSession();
		Transaction	tx=session.beginTransaction();
	    List<User> list=null;	
		list=session.createQuery("from User ").list();
		if(list!=null)
		{
			
			all=list.size();
			
			
		}
		
		tx.commit();
	   
		
		
		Session session2=HibernateUtil.getSession();
		Transaction	tx2=session2.beginTransaction();

		
	
		Page<User> pageBean=new Page<User>();

		pageBean.setAll(all);
		pageBean.setPc(pc);        //��jsp������
		pageBean.setPs(ps);
	
		List<User> list2=new ArrayList<User>(); 
		
       list2=session.createQuery("from User order by userid asc").
				setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
	
             tx2.commit();

			pageBean.setBeanlist(list2);
	
		    HibernateUtil.closeSession(session2);
			
			 return pageBean;
		
	}


}
