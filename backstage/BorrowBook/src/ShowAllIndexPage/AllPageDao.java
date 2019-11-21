/**
 * 
 */
package ShowAllIndexPage;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.GroupBook;
import entity.Page;
import entity.Reduce;
import util.HibernateUtil;

public class AllPageDao {

	
	//减价
	 public Page<Reduce> getReduceData(int pc) 
		{
			
			int ps=6;  //每页6条记录ֵ
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
	
	
	
	 
//	 public Page<GroupBook> getGroupData(int pc) 
//		{
//			
//			int ps=6;  //每页6条记录ֵ
//			int all=0;
//			
//		    Session session=HibernateUtil.getSession();
//			Transaction	tx=session.beginTransaction();
//		    List<GroupBook> list=null;	
//			list=session.createQuery("select gb from GroupBook as gb inner join gb.groupmore as g  where g.showstatus=1").list();
//		
//			if(list!=null)
//			{
//				
//				all=list.size();
//				
//				
//			}
//			
//			tx.commit();
//		   
//			
//			
//			Session session2=HibernateUtil.getSession();
//			Transaction	tx2=session2.beginTransaction();
//
//			
//		
//			Page<GroupBook> pageBean=new Page<GroupBook>();
//
//			pageBean.setAll(all);
//			pageBean.setPc(pc);       
//			pageBean.setPs(ps);
//		
//			List<GroupBook> list2=new ArrayList<GroupBook>(); 
//			
//	list2=session.createQuery("select gb from GroupBook as gb inner join gb.groupmore as g  where g.showstatus=1").
//					setFirstResult((pc-1)*ps).
//					setMaxResults(ps).list();
//		
//	             tx2.commit();
//
//				pageBean.setBeanlist(list2);
//		
//			    HibernateUtil.closeSession(session2);
//				
//				 return pageBean;
//			
//		}
	
	

		
	 public Page<Book> getVipBookData(int pc) 
		{
			
			int ps=6;  //每页6条记录ֵ
			int all=0;
			
		
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Book> list=null;	
	list=session.createQuery("from  Book as b where freestatus=1 and vipfreestatus=1  and elestatus=1").list();
		
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
			
	list2=session.createQuery("from Book where elestatus=1  and freestatus=1 and vipfreestatus=1").
					setFirstResult((pc-1)*ps).
					setMaxResults(ps).list();
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	
	 
	 

	 public Page<Book> getEleBookData(int pc) 
		{
			
			int ps=6;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Book> list=null;	
			list=session.createQuery("from Book where elestatus=1  and freestatus=1 and vipfreestatus=0").list();
		
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
			
	list2=session.createQuery("from Book where elestatus=1  and freestatus=1 and vipfreestatus=0").
					setFirstResult((pc-1)*ps).
					setMaxResults(ps).list();
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	 

	 public Page<Book> getFreeBookData(int pc) 
		{
			
			int ps=6;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Book> list=null;	
			list=session.createQuery("from Book where freestatus=0   and elestatus=1").list();
		
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
			
	list2=session.createQuery("from Book where freestatus=0 and elestatus=1").
					setFirstResult((pc-1)*ps).
					setMaxResults(ps).list();
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
	 
	 public static void main(String [] args)
	 {
		 AllPageDao apd=new AllPageDao();
		 entity.Page<Book> pagebean=apd.getVipBookData(1);
		 
		 List<Book>   blist=pagebean.getBeanlist();
		 
		 
		 if(blist!=null&&blist.size()>0)
		 {
			 
			 for(int i=0;i<blist.size();i++)
			 {
				 Book b=(Book)blist.get(i);
				 
				System.out.println(b.getBookid());
				 
				 
			 }
			 
		 }
		 
	 }

	
}