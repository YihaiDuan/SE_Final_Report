/**
 * 
 */
package Extenddao2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.Topic;
import entity.TopicBook;
import util.HibernateUtil;

/**
 * @author Administrator
 * @date Jul 25, 2017
 * @todoTODO     
 */
public class TopicDao
{
	
	//获取显示4个主题
	
	public void SavaTopic(Topic t)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(t);
		tx.commit();
	    HibernateUtil.closeSession(session);
		
	}
	  
	public List<Topic>  getAllTopic()
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<Topic> list=session.createQuery("from Topic  where showstatus=1").list();
		
		tx.commit();
	    HibernateUtil.closeSession(session);
	   if(list!=null&&list.size()>0)
	   {
	    return list;
	   }
	   else
	   {
		   
		   return null;
	   }
	}
	
	
    //根据主题ID显示主题
	public Topic getTopicByid(int id)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Topic to=(Topic) session.get(Topic.class,id);
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		if(to!=null)
		{
			
			return to;
		}
		
		return null;
	
	}
	
	
	public List<TopicBook> getTopicBookByid(int id)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<TopicBook> tlist=session.createQuery("from TopicBook as tb where tb.topic.id=?").
				setParameter(0,id).list();
		
		
		
		return tlist;
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
