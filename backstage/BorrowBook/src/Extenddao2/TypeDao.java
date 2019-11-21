/**
 * 
 */
package Extenddao2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.*;
import util.HibernateUtil;
/**
 * @author Administrator
 * @date Aug 7, 2017
 * @todoTODO
 */
public class TypeDao {
	
	//获取全部类型
	public List<BookType>  getAllType()
	{
		
		Session s=HibernateUtil.getSession();
		Transaction tx=s.beginTransaction();
		
		List<BookType>  tlist=s.createQuery("from BookType").list();
		
		tx.commit();
		HibernateUtil.closeSession(s);
		
		if(tlist!=null&&tlist.size()>0)
		{
			return tlist;
		}
		else
		{
			
			return null;
		}
	}
	
	
	
	public BookType getBookTypeByid(int id)
	{
		Session s=HibernateUtil.getSession();
		Transaction tx=s.beginTransaction();
		
		BookType bt=(BookType) s.get(BookType.class, id);
		
		
		
		tx.commit();
		HibernateUtil.closeSession(s);
		
		if(bt!=null)
		{
			
			return bt;
		}
		return null;
		
	}
	
	public static void main(String[] args)
	{
		TypeDao td=new TypeDao();
		
		
		List<BookType>   tlist=td.getAllType();
		
		if(tlist.size()>0)
		{
			
			
			System.out.println(tlist.size());
		}
		
	}
	
	
	
	
}
