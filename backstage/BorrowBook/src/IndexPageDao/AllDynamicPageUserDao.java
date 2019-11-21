
package IndexPageDao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.Dynamic;
import entity.Page;
import util.HibernateUtil;

public class AllDynamicPageUserDao {
	
	
	 public Page<Dynamic> getAllDynamicPageUser(int pc) 
		{
			
			int ps=6;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Book> list=null;	
			list=session.createQuery("from Dynamic   order by date desc").list();
		
			if(list!=null)
			{
				
				all=list.size();
				
				
			}
			
			tx.commit();
		   
			
			
			Session session2=HibernateUtil.getSession();
			Transaction	tx2=session2.beginTransaction();

			
		
			Page<Dynamic> pageBean=new Page<Dynamic>();

			pageBean.setAll(all);
			pageBean.setPc(pc);       
			pageBean.setPs(ps);
		
			List<Dynamic> list2=new ArrayList<Dynamic>(); 
			
	list2=session.createQuery("from Dynamic   order by date desc").
					setFirstResult((pc-1)*ps).
					setMaxResults(ps).list();
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	
	
	
	public static void main(String [] args)
	{
		
		AllDynamicPageUserDao apd=new AllDynamicPageUserDao();
		
		Page<Dynamic>  page=apd.getAllDynamicPageUser(200);
		
		if(page!=null)
		{
			
			System.out.println(page.getAll());
			
			
			
		}
	}
	
	
	
	
}
