package Extenddao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.Topic;
import entity.TopicBook;
import util.HibernateUtil;





//专题下的书
public class TopicBookDao 
{
	
	
	//添加专题下的书
		public TopicBook SaveTopicBook(TopicBook  tob)
		{
			Session session = HibernateUtil.getSession();
			try {
				
				session.beginTransaction();
				session.evict(tob);
				session.save(tob);
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}

		    HibernateUtil.closeSession(session);
		   return tob;
	   }
	  //删除专题下的书
		public void deleteTopicBook(TopicBook topicbook)
		{
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				session.delete(topicbook);
				session.getTransaction().commit();
				HibernateUtil.closeSession(session);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			
		}
		//通过id获得对象
		public TopicBook getIdTopicBook(int id){
			Session session = HibernateUtil.getSession();
			TopicBook topicbook = null;
			try {
				session.beginTransaction();
				topicbook = (TopicBook) session.get(TopicBook.class,id);
				session.getTransaction().commit();
				HibernateUtil.closeSession(session);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			return topicbook;
		}
		public TopicBook getIdTopicBook1(int id){
			Session session = HibernateUtil.getSession();
			TopicBook topicbook = null;
			try {
				session.beginTransaction();
				
				topicbook = (TopicBook) session.load(TopicBook.class,id);
				session.getTransaction().commit();
				
				//HibernateUtil.closeSession(session);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			finally{
				HibernateUtil.closeSession(session);
			}
			return topicbook;
		}
		//通过id获得相应的集合
		public List<TopicBook> getListTopicBook(int id){
			Session session = HibernateUtil.getSession();
			List<TopicBook> list = null;
			try {
				session.beginTransaction();
				String hql = "from TopicBook as t where t.topic.id =:topic_id";
				list = session.createQuery(hql).setParameter("topic_id", id).list();
			    session.getTransaction().commit();
			    HibernateUtil.closeSession(session);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			
			return list;
		}
		//通过id获得book对象
		public Book getBook(int id)
		{
			Session session = HibernateUtil.getSession();
			Book book = null;
			try {
				session.beginTransaction();
			    String hql = "from Book as b inner join fetch b.topicbook t where t.id =:id";
			    book = (Book)session.createQuery(hql).setParameter("id",id).uniqueResult();
				session.getTransaction().commit();
				HibernateUtil.closeSession(session);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			return book;
		}
	    //修改对象
		  public void updateTopicBook(TopicBook topicbook){
			  Session session = HibernateUtil.getSession();
			  try {
				session.beginTransaction();
				session.evict(topicbook);
				
				session.update(topicbook);
				session.getTransaction().commit();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			  
			  finally{
				  
				  HibernateUtil.closeSession(session);
			  }
		  }
	
	
	
}
