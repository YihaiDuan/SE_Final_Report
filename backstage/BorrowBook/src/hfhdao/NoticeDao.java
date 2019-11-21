package hfhdao;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;
import entity.Notice;

/**
 * 公告类
 * 
 * */
public class NoticeDao {
    //获得全部公告
	public List<Notice> getListNotice(){
		Session session = HibernateUtil.getSession();
		List<Notice> list = null;
		try {
			session.beginTransaction();
			String hql = "from Notice ";
			list = session.createQuery(hql).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
		}
		return list;
	}
	//添加公告
	 public void addNotice(Notice notice){
		 Session session = HibernateUtil.getSession();
		 try {
			session.beginTransaction();
			session.save(notice);
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
	 //删除公告
	 public void deleteNotice(Notice notice){
		 Session session = HibernateUtil.getSession();
		 try {
			session.beginTransaction();
			session.delete(notice);
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
	 //根据id获得一条公告
	 public Notice getIdNotice(int id){
		 Session session = HibernateUtil.getSession();
		 Notice notice = null;
		 try {
			session.beginTransaction();
			notice = (Notice) session.get(Notice.class,id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
			finally{
				HibernateUtil.closeSession(session);
			}
		return notice;
	 }
	
	
}
