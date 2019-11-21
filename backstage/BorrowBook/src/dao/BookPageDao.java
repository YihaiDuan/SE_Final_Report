package dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;




import entity.Book;
import entity.Page;
import util.HibernateUtil;

/**
 * 
 * @author hjm
 * @todo 图书分页显示赞数据访问层
 */
public class BookPageDao
{
	
	
	public Page<Book> findByPage(int pc,int categoryid) 
	{
		
		int ps=6;  //每页6条记录ֵ
		int all=0;
		
		
	
			
		
	     Session session=HibernateUtil.getSession();
		Transaction	tx=session.beginTransaction();
	    List<Book> list=null;	
		list=session.createQuery("from Book as b where b.category.categoryid=:categoryid")
				.setInteger("categoryid", categoryid)
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
		
list2=session.createQuery("from Book as b  where b.category.categoryid=:categoryid order by bookid asc").
                setInteger("categoryid", categoryid).
				setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
	
             tx2.commit();

			pageBean.setBeanlist(list2);
	
		    HibernateUtil.closeSession(session2);
			
			 return pageBean;
		
	}


}
