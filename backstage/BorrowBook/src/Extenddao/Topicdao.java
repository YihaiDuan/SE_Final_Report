
package Extenddao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Topic;
import entity.TopicBook;
import util.HibernateUtil;



//专题
 public class Topicdao
 {
		public Topic SaveTopic(Topic  to)
		{
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			 session.save(to);

			 tx.commit();
		    HibernateUtil.closeSession(session);
		    return to;
	   }
	
	
	
	public Topic getTopicByid(int id)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Topic t=(Topic) session.load(Topic.class,id);
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		return t;
	
		
   }
	
	//获得所有专题记录	
    public List<Topic> getAllTopic(){
    	Session session =HibernateUtil.getSession();
    	List<Topic> list=null;
    	try {
			session.beginTransaction();
			String hql="from Topic";
			list=session.createQuery(hql).list();
		    session.getTransaction().commit();
		    HibernateUtil.closeSession(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
    	
    	return list;
    }
	//修改主题
    public void changeTopic(Topic topic){
    	Session session =HibernateUtil.getSession();
    	try {
			session.beginTransaction();
			session.update(topic);
    		session.getTransaction().commit();
    		HibernateUtil.closeSession(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
    	finally{
    		HibernateUtil.closeSession(session);
    	}

    }
    //根据id获得主题
      public Topic getTopic(int id){
    	  Session session=HibernateUtil.getSession();
    	  Topic topic=null;
    	  try {
			session.beginTransaction();
			topic = (Topic) session.get(Topic.class,id);
			session.getTransaction().commit();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
      	finally{
    		HibernateUtil.closeSession(session);
    	}
    	 return topic;
    	  
      }
      public Topic getTopic1(int id){
    	  Session session=HibernateUtil.getSession();
    	  Topic topic=null;
    	  try {
			session.beginTransaction();
			topic = (Topic) session.load(Topic.class,id);
			session.getTransaction().commit();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
      	finally{
    		HibernateUtil.closeSession(session);
    	}
    	 return topic;
    	  
      }
	//删除主题
      public void deleteTopic(Topic topic)
      {
    	  Session session =HibernateUtil.getSession();
    	  try {
			  session.beginTransaction();
			  session.delete(topic);
			  session.getTransaction().commit();
			  HibernateUtil.closeSession(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
      }
	
	
	
	
	
	
	
	
	
}
