package testdao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import Extenddao.TopicBookDao;
import Extenddao.Topicdao;
import Extenddao2.GroupMainDao;
import Extenddao2.OutPayDao;
import MyTotalUtil.DataDao;
import MyTotalUtil.UserReadBorrow;
import dao.*;
import dao.BookSonDao;
import dao.CategoryDao;
import dao.UserDao;
import entity.Book;
import entity.BookSon;
import entity.BookType;
import entity.Category;
import entity.ComparePrice;
import entity.Discount;
import entity.GroupMain;
import entity.GroupMember;
import entity.OutPay;
import entity.Reduce;
import entity.Slide;
import entity.Topic;
import entity.TopicBook;
import entity.User;
import timer.SignTimer;
import util.HibernateUtil;



public class test1 {
	
	public static void main(String[] args) 
	{
		
	/*    Date  d=new Date();
		
		SimpleDateFormat stf=new SimpleDateFormat("HH:mm:ss");
		
		String nowdate=stf.format(d);
		
		if(nowdate.equals("00:00:00"))
		{
			
			System.out.println("我要去更新标志位了");
		}*/
	 
	
		/* Configuration  cfg =new Configuration().configure();
		 SchemaExport se=new SchemaExport(cfg);
		 se.create(true,true);*/
		
	
	   
	    //deleteCate("104");
	       BookDao bd=new BookDao();
		List<Book> blist= bd.getBookAll2();
		if(blist!=null)
		{
			
			
			int  result=0;
		
		for(int i=0;i<blist.size();i++)
		{
			
			Book b=blist.get(i);
			
			result = (int)(500*Math.random()+1);
			b.setBookid(b.getBookid());
           b.setBorrownum(result);
           update(b);
           System.out.println(result);
           System.out.println(i);
		}
		    
		
		
		}
	
		
		
		count();
	    
	}
	public static void count()
	{
		int result=0;
		result = (int)(500*Math.random()+1);
		System.out.println(result);
		
	}
	
	
	public static void SavaReduce(Reduce r)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();	
		
		session.save(r);
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
	}
	
	
	
	
	public static void update(Book b)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		Book b2=(Book) session.get(Book.class,b.getBookid());
		
		  b2.setBorrownum(b.getBorrownum());
		  
		session.update(b2);
		tx.commit();
		HibernateUtil.closeSession(session);
		
	}
	
	
	
	
	
	
	public static List<Book> getDis()
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();	
		
		List<Book>   blist=session.createQuery("from Book as b where b.oldprice>b.newprice").list();
		
		
		
		
		tx.commit();
		HibernateUtil.closeSession(session);
		if(blist!=null)
		{
			
			return blist;
			
		}
		else
		{
			
			return null;
			
		}
		
	}
	
	
	
	public  static void  deleteCate(String cateid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();	
		
		Category c=(Category) session.get(Category.class,cateid);
		
		if(c!=null)
		{
			
			
			session.delete(c);
		}
		tx.commit();
		
	}

	

	
}
