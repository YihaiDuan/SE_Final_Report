/**
 * 
 */
package EleBookDao;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.MyEleBook;
import entity.ReadHistroy;
import util.HibernateUtil;

public class ReadHistroyDao {
	
	
	
	//获取用户的阅读记录
	public List<ReadHistroy>  getReadByUser(String userid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		ReadHistroy rh=null;
	List<ReadHistroy>  rhlist= session.
				createQuery("from ReadHistroy as re where re.user.userid=?")
				.setParameter(0,userid).list();
	
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		if(rhlist!=null&&rhlist.size()>0)
		{
			
			return rhlist;
			
			
		}
		else
		{
			return null;
			
		}
		
	
	}
	
	//添加电子书到书架
	
		public void SaveReadHistroy(ReadHistroy re)
		{
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			session.save(re);
		
			tx.commit();
		    HibernateUtil.closeSession(session);

	     }
	
		
		
		
		//判断是否已经在历史记录中
	
		public ReadHistroy  getReadHistroy(String userid,String bookid)
		{
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			ReadHistroy rh=null;
			rh=(ReadHistroy) session.
					createQuery("from ReadHistroy as re where re.user.userid=? and re.book.bookid=?")
					.setParameter(0,userid).setParameter(1,bookid).uniqueResult();
		
			
			tx.commit();
		    HibernateUtil.closeSession(session);
			
			if(rh!=null)
			{
				
				return rh;
				
				
			}
			else
			{
				return null;
				
			}
		
		}
		
		//更新最大进度
		
		public void UpdateMaxPage(ReadHistroy rh)
		{
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			ReadHistroy rh1=(ReadHistroy)session.get(ReadHistroy.class,rh.getId());
			
			rh1.setMaxpage(rh.getMaxpage());
			
		     session.update(rh1);
			 tx.commit();
			 HibernateUtil.closeSession(session);
			 
		}
	
		  //更新其他信息
		public void UpdateAllInf(ReadHistroy rh)
		{
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			ReadHistroy rh1=(ReadHistroy)session.get(ReadHistroy.class,rh.getId());
			
			rh1.setAllpage(rh.getAllpage());
			rh1.setCurrentpage(rh.getCurrentpage());
			rh1.setShowtitle(rh.getShowtitle());
			rh1.setStart(rh.getStart());
			rh1.setEnd(rh.getEnd());
			
		     session.update(rh1);
			 tx.commit();
			 HibernateUtil.closeSession(session);
			 
		}
	
	public static void main(String [] args)
	{
		
		
		  ReadHistroyDao rhd=new ReadHistroyDao();
		
		  List <ReadHistroy>  rhlist=rhd.getReadByUser("o50AL0U9sAMhbI3ZrqfTF7nmWduo");
		  Set<String>  rhset=new HashSet<String>();
		  
		  if(rhlist!=null&&rhlist.size()>0)
		  {
			  
			  for(int i=0;i<rhlist.size();i++)
			  {
				  
				  
				  ReadHistroy rh=rhlist.get(i);
				  System.out.println(rh.getBook().getCategory().getName());
				  rhset.add(rh.getBook().getCategory().getName());
				  
				  
			  }
			  
			}
		  
		  
		   for(String i :rhset )
		   {
			   
			   System.out.println(i);
			   
			   
		   }	
		
		
		
	}
	
	
}
