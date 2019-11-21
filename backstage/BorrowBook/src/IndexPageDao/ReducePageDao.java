/**
 * 
 *//*
package IndexPageDao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Page;
import entity.Reduce;
import entity.Topic;
import util.HibernateUtil;

public class ReducePageDao {
	
	
	 public Page<Reduce> getReduceData(int pc) 
		{
			
			int ps=3;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Reduce> list=null;	
			list=session.createQuery("from Reduce as r").list();
		
			if(list!=null)
			{
				
				all=list.size();
				
				
			}
			
			tx.commit();
		   
			
			
			Session session2=HibernateUtil.getSession();
			Transaction	tx2=session2.beginTransaction();

			
		
			Page<Reduce> pageBean=new Page<Reduce>();

			pageBean.setAll(all);
			pageBean.setPc(pc);       
			pageBean.setPs(ps);
		
			List<Reduce> list2=new ArrayList<Reduce>(); 
			
	list2=session.createQuery("from Reduce").
					setFirstResult((pc-1)*ps).
					setMaxResults(ps).list();
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	
	
}
*/