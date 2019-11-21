package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.Category;
import entity.BookType;
import util.HibernateUtil;


/**
 * 
 * @author hjm
 * @todo 图书类别管理数据访问层
 */
public class CategoryDao {
	
	
	public void SaveCategory (Category c)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(c);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	
	
  public List<Category>  getAllCategory()
	  {
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			
			List<Category> list=session.createQuery("from Category").list();
			
			tx.commit();
		HibernateUtil.closeSession(session);
		   
		    return list;
		  
	  }
	

    public Category getCategoryByid(String id)
    {
    	
    	Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		Category c=null;
		
		c=(Category) session.get(Category.class,id);
		
		tx.commit();
	HibernateUtil.closeSession(session);
	   
	    return c;
    	
    	
    }
    //获取子类型
    public List<Category> getCategoryByid(int typeid)
    {
    	
    	Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		Category c=null;
		
List<Category>  clist=session.createQuery("from Category  as c where c.type.id=?").setParameter(0,typeid).list();
		


      tx.commit();
    HibernateUtil.closeSession(session);
      if(clist!=null&&clist.size()>0)
      {
    	  
    	  
    	  return clist;
      }
   
        return null;
	
    }
    
    
    //获取该类型的图书量
    public int getBookNum(String cateid)
    {
    	
    	Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
    	Object num=0;
    	
    	num=session.createQuery("select count(b.category.categoryid)  from Book as b where b.category.categoryid=?").setParameter(0,cateid).uniqueResult();
    	 tx.commit();
    	  HibernateUtil.closeSession(session);
        if(num!=null)
    	{
    		
    		return Integer.parseInt(String.valueOf(num.toString()));
    	}
    	
    	else
    	{
    		
    		return 0;
    	}
    	
    }
    
    
    public static void main(String [] args)
    {
    	
    	CategoryDao  cd=new  CategoryDao();
    			int b=cd.getBookNum("1");
    	
    	System.out.println(b);
    }
    
    
}
