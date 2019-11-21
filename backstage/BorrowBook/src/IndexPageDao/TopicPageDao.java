/**
 * 
 *//*
package IndexPageDao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Page;
import entity.Topic;
import util.HibernateUtil;

public class TopicPageDao {
	
	
	 public Page<Topic> getTopicData(int pc) 
		{
			
			int ps=4;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Topic> list=null;	
			list=session.createQuery("from Topic as t").list();
		
			if(list!=null)
			{
				
				all=list.size();
				
				
			}
			
			tx.commit();
		   
			
			
			Session session2=HibernateUtil.getSession();
			Transaction	tx2=session2.beginTransaction();

			
		
			Page<Topic> pageBean=new Page<Topic>();

			pageBean.setAll(all);
			pageBean.setPc(pc);       
			pageBean.setPs(ps);
		
			List<Topic> list2=new ArrayList<Topic>(); 
			
	list2=session.createQuery("from Topic").
					setFirstResult((pc-1)*ps).
					setMaxResults(ps).list();
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	
	
}
*/