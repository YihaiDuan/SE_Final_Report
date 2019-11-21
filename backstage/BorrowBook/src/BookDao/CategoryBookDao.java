/**
 * 
 */
package BookDao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.Page;
import util.HibernateUtil;

public class CategoryBookDao {
	
	
	
	
	
	 public Page<Book> getBookHotPage(int pc,String cateid) 
		{
			
			int ps=10;  //每页10条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Book> list=null;	
			list=session.createQuery("from Book  as b where b.category.categoryid=?  order by borrownum desc")
					 .setParameter(0,cateid)
					 .list();
		
			if(list!=null)
			{
				
				all=list.size();
				
				
			}
			
			tx.commit();
		   
			
			
			Session session2=HibernateUtil.getSession();
			Transaction	tx2=session2.beginTransaction();

			
		
			Page<Book> pageBean=new Page<Book>();

			pageBean.setAll(all);
			pageBean.setPc(pc);       
			pageBean.setPs(ps);
		
			List<Book> list2=new ArrayList<Book>(); 
			
	list2=session.createQuery("from Book  as b where b.category.categoryid=?  order by borrownum desc")
			 .setParameter(0,cateid)
			 .setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
			
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public Page<Book> getGoodBookPage(int pc,String cateid) 
		{
			
			int ps=10;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Book> list=null;	
			list=session.createQuery("from Book  as b where b.category.categoryid=?  order by b.evaluatescore desc")
					 .setParameter(0,cateid)
					 .list();
		
			if(list!=null)
			{
				
				all=list.size();
				
				
			}
			
			tx.commit();
		   
			
			
			Session session2=HibernateUtil.getSession();
			Transaction	tx2=session2.beginTransaction();

			
		
			Page<Book> pageBean=new Page<Book>();

			pageBean.setAll(all);
			pageBean.setPc(pc);       
			pageBean.setPs(ps);
		
			List<Book> list2=new ArrayList<Book>(); 
			
	list2=session.createQuery("from Book  as b where b.category.categoryid=?  order by b.evaluatescore desc")
			 .setParameter(0,cateid)
			 .setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
			
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	
		
		public static void main(String [] args)
		{
			
			
			
			
			CategoryBookDao cd=new CategoryBookDao();
			
			entity.Page<Book> pagebean=  cd.getGoodBookPage(3, "1");
			
			List <Book>   blist= pagebean.getBeanlist();
			if(blist!=null)
			{
				
				for(int i=0;i<blist.size();i++)
				{
					
					Book b=blist.get(i);
					System.out.print(b.getEvaluatescore()+"  ");
				}
				
				
			}
			
			
		}
			
	
	
	
	
}
