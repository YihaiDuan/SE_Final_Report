package Extenddao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.Page;
import util.HibernateUtil;

public class RemainNumPageDao
{

    public Page<Book> RemainNumPageAsc(int pc) 
		{
			
			int ps=8;  //每页6条记录ֵ
			int all=0;
			
			//select count(*) from news" 
			//"select * from news limit"+(pc-1)*ps+" , "+ps;
			//"select * from news limit  a[1 ,3;
		
				
			
		    Session session=HibernateUtil.getSession();
			Transaction	tx=session.beginTransaction();
		    List<Book> list=null;	
			list=session.createQuery("from Book as b").list();
		
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
			
	list2=session.createQuery("from Book as b order by remainnum asc").
					setFirstResult((pc-1)*ps).
					setMaxResults(ps).list();
		
	             tx2.commit();

				pageBean.setBeanlist(list2);
		
			    HibernateUtil.closeSession(session2);
				
				 return pageBean;
			
		}
    public Page<Book> RemainNumPageAscType(int pc,String categoryid) 
	{
		
		int ps=8;  //每页6条记录ֵ
		int all=0;
		
		//select count(*) from news" 
		//"select * from news limit"+(pc-1)*ps+" , "+ps;
		//"select * from news limit  a[1 ,3;
	
			
		
	    Session session=HibernateUtil.getSession();
		Transaction	tx=session.beginTransaction();
	    List<Book> list=null;	
		list=session.createQuery("from Book as b where b.category.categoryid =:categoryid").setParameter("categoryid",categoryid).list();
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
		
list2=session.createQuery("from Book as b where b.category.categoryid =:categoryid order by remainnum asc").setParameter("categoryid",categoryid).
				setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
	
             tx2.commit();

			pageBean.setBeanlist(list2);
	
		    HibernateUtil.closeSession(session2);
			
			 return pageBean;
		
	}

    public Page<Book> RemainNumPageAscBookType(int pc,int booktypeid) 
   	{
   		
   		int ps=8;  //每页6条记录ֵ
   		int all=0;
   		
   		//select count(*) from news" 
   		//"select * from news limit"+(pc-1)*ps+" , "+ps;
   		//"select * from news limit  a[1 ,3;
   	
   			
   		
   	    Session session=HibernateUtil.getSession();
   		Transaction	tx=session.beginTransaction();
   	    List<Book> list=null;	
   	    String sql = "select book.* from book,booktype,category where book.category_id=category.categoryid and booktype.id=category.type_id and booktype.id=:typeid";
		list=((SQLQuery) session.createSQLQuery(sql).setParameter("typeid",booktypeid)).addEntity(Book.class).list();
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
   	  String sql2="select book.* from book,booktype,category where book.category_id=category.categoryid and booktype.id=category.type_id and booktype.id=:typeid order by remainnum asc";
      list2=((SQLQuery) session.createSQLQuery(sql2).setParameter("typeid", booktypeid)).addEntity(Book.class) .
   				setFirstResult((pc-1)*ps).
   				setMaxResults(ps).list();
   	
                tx2.commit();

   			pageBean.setBeanlist(list2);
   	
   		    HibernateUtil.closeSession(session2);
   			
   			 return pageBean;
   		
   	}

   	
    
    public Page<Book> RemianNumPageDesc(int pc) 
	{
		
		int ps=8;  //每页6条记录ֵ
		int all=0;
		
		//select count(*) from news" 
		//"select * from news limit"+(pc-1)*ps+" , "+ps;
		//"select * from news limit  a[1 ,3;
	
			
		
	    Session session=HibernateUtil.getSession();
		Transaction	tx=session.beginTransaction();
	    List<Book> list=null;	
		list=session.createQuery("from Book as b").list();
	
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
		
list2=session.createQuery("from Book as b order by remainnum desc").
				setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
	
             tx2.commit();

			pageBean.setBeanlist(list2);
	
		    HibernateUtil.closeSession(session2);
			
			 return pageBean;
		
	}
    public Page<Book> RemianNumPageDescType(int pc,String categoryid) 
   	{
   		
   		int ps=8;  //每页6条记录ֵ
   		int all=0;
   		
   		//select count(*) from news" 
   		//"select * from news limit"+(pc-1)*ps+" , "+ps;
   		//"select * from news limit  a[1 ,3;
   	
   			
   		
   	    Session session=HibernateUtil.getSession();
   		Transaction	tx=session.beginTransaction();
   	    List<Book> list=null;	
   		list=session.createQuery("from Book as b where b.category.categoryid =:categoryid").setParameter("categoryid",categoryid).list();
   	
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
   		
   list2=session.createQuery("from Book as b where b.category.categoryid =:categoryid order by remainnum desc").setParameter("categoryid",categoryid).
   				setFirstResult((pc-1)*ps).
   				setMaxResults(ps).list();
   	
                tx2.commit();

   			pageBean.setBeanlist(list2);
   	
   		    HibernateUtil.closeSession(session2);
   			
   			 return pageBean;
   		
   	}
    public Page<Book> RemianNumPageDescBookType(int pc,int booktypeid) 
   	{
   		
   		int ps=8;  //每页6条记录ֵ
   		int all=0;
   		
   		//select count(*) from news" 
   		//"select * from news limit"+(pc-1)*ps+" , "+ps;
   		//"select * from news limit  a[1 ,3;
   	
   			
   		
   	    Session session=HibernateUtil.getSession();
   		Transaction	tx=session.beginTransaction();
   	    List<Book> list=null;	
   	 String sql = "select book.* from book,booktype,category where book.category_id=category.categoryid and booktype.id=category.type_id and booktype.id=:typeid";
	 list=((SQLQuery) session.createSQLQuery(sql).setParameter("typeid",booktypeid)).addEntity(Book.class).list();
   	
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
   	 String sql2="select book.* from book,booktype,category where book.category_id=category.categoryid and booktype.id=category.type_id and booktype.id=:typeid order by remainnum desc";
     list2=((SQLQuery) session.createSQLQuery(sql2).setParameter("typeid", booktypeid)).addEntity(Book.class) .
   				setFirstResult((pc-1)*ps).
   				setMaxResults(ps).list();
   	
                tx2.commit();

   			pageBean.setBeanlist(list2);
   	
   		    HibernateUtil.closeSession(session2);
   			
   			 return pageBean;
   		
   	}
	
}
