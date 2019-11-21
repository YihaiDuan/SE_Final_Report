/**
 * 
 */
package IndexPageDao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.Page;
import util.HibernateUtil;

public class SearchPageDao 
{
	
	
	
	 public Page<Book> getBookbyKeyPage(int pc,String search) 
		{
			
			int ps=10;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Book> list=null;	
			list=session.createQuery("from Book where booktitle like ?  or chinesespelling like ?")
					 .setParameter(0,"%"+search+"%").setParameter(1,"%"+search+"%")
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
			
	list2=session.createQuery("from Book where booktitle like ?  or chinesespelling like ?")
			 .setParameter(0,"%"+search+"%").setParameter(1,"%"+search+"%")
			 .setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
			
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	
	
	
	
	
	//根据图书标题搜索

		
	 public Page<Book> getBookbyBooktitlePage(int pc,String search) 
		{
			
			int ps=10;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Book> list=null;	
			list=session.createQuery("from Book where booktitle like ?")
					 .setParameter(0,"%"+search+"%")
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
			
	list2=session.createQuery("from Book where booktitle like ?")
			 .setParameter(0,"%"+search+"%") 
		     .setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
			
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	
	
	
	
	
	
	
	
	
	 
		//根据作者搜索

		
	 public Page<Book> getBookbyAuthorPage(int pc,String search) 
		{
			
			int ps=10;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Book> list=null;	
			list=session.createQuery("from Book where  author like ?")
					 .setParameter(0,"%"+search+"%")
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
			
	list2=session.createQuery("from Book where  author like ?")
			 .setParameter(0,"%"+search+"%") 
		     .setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
			
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	
	
	
	
	
	
	
		//全部搜索

		
	 public Page<Book>  getBookbyAllPage(int pc,String search) 
		{
			
			int ps=10;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Book> list=null;	
			list=session.createQuery("from Book where author like ? or booktitle like ?  or chinesespelling like ?")
					 .setParameter(0,search).setParameter(1,"%"+search+"%").setParameter(2,"%"+search+"%").list();
		
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
			
	list2=session.createQuery("from Book where author like ? or booktitle like ?  or  chinesespelling like ?")
			.setParameter(0,search).setParameter(1,"%"+search+"%").setParameter(2,"%"+search+"%")
		      .setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
			
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
