/**
 * 
 *//*
package IndexPageDao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.GroupBook;
import entity.Page;
import entity.Topic;
import util.HibernateUtil;

public class GroupPageDao {
	
	
	 public Page<GroupBook> getGroupData(int pc) 
		{
			
			int ps=3;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<GroupBook> list=null;	
			list=session.createQuery("select gb from GroupBook as gb inner join gb.groupmore as g  where g.showstatus=1").list();
		
			if(list!=null)
			{
				
				all=list.size();
				
				
			}
			
			tx.commit();
		   
			
			
			Session session2=HibernateUtil.getSession();
			Transaction	tx2=session2.beginTransaction();

			
		
			Page<GroupBook> pageBean=new Page<GroupBook>();

			pageBean.setAll(all);
			pageBean.setPc(pc);       
			pageBean.setPs(ps);
		
			List<GroupBook> list2=new ArrayList<GroupBook>(); 
			
	list2=session.createQuery("select gb from GroupBook as gb inner join gb.groupmore as g  where g.showstatus=1").
					setFirstResult((pc-1)*ps).
					setMaxResults(ps).list();
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	
	
}
*/