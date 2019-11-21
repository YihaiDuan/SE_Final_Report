package hfhdao;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import entity.User;
import util.HibernateUtil;

/**
 * 对用户类的操作
 * */
public class UserDao {
  //通过userid获得user对象
	public User getUser(String userid){
		Session session=HibernateUtil.getSession();
		User user =null;
		try {
			session.beginTransaction();
			user=(User) session.get(User.class,userid);
			session.getTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return user;	
	}
	//修改user对象的权限值
	 public void changeUser(User user)
	 {
			Session session=HibernateUtil.getSession();
			try {
				session.beginTransaction();
				session.update(user);
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			session.close();
	 }
	//统计用户总人数
	 public int getUserNumber(){
		Session session=HibernateUtil.getSession();
		 Number number = null;
		try {
			session.beginTransaction();
			String hql="select count(*) from User";
			number =(Number) session.createQuery(hql).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();;
		}
		finally{
			HibernateUtil.closeSession(session);
		}
		 return number.intValue();
		 
	 }
	//统计VIP1总人数
		 public int getUserVIP1Number(){
			Session session=HibernateUtil.getSession();
			 Number number = null;
			try {
				session.beginTransaction();
				String hql="select count(*) from User where grade=1";
				number =(Number) session.createQuery(hql).uniqueResult();
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();;
			}
			finally{
				HibernateUtil.closeSession(session);
			}
			 return number.intValue();
			 
		 }
		//统计VIP2总人数
		 public int getUserVIP2Number(){
			Session session=HibernateUtil.getSession();
			 Number number = null;
			try {
				session.beginTransaction();
				String hql="select count(*) from User where grade=2";
				number =(Number) session.createQuery(hql).uniqueResult();
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();;
			}
			finally{
				HibernateUtil.closeSession(session);
			}
			 return number.intValue();
			 
		 }
		//统计VIP3总人数
		 public int getUserVIP3Number(){
			Session session=HibernateUtil.getSession();
			 Number number = null;
			try {
				session.beginTransaction();
				String hql="select count(*) from User where grade=3";
				number =(Number) session.createQuery(hql).uniqueResult();
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();;
			}
			finally{
				HibernateUtil.closeSession(session);
			}
			 return number.intValue();
			 
		 }
	  //统计黑名单总人数
		 public int getUserBlockNumber(){
			 Session session=HibernateUtil.getSession();
			 Number number = null;
			try {
				session.beginTransaction();
				String hql="select count(*) from User where jurisdiction=1";
				number =(Number) session.createQuery(hql).uniqueResult();
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();;
			}
			finally{
				HibernateUtil.closeSession(session);
			}
			 return number.intValue(); 		 
		 }
	 //统计今日新增的人数
		 public int getADDTodayUser(String ToDay){
			 Session session =HibernateUtil.getSession();
			 Number number=null;
			 try {
				session.beginTransaction();
				String hql="select count(*) from User where starttime like :starttime ";
				number=(Number) session.createQuery(hql).setParameter("starttime", "%"+ToDay+"%").uniqueResult();
			    session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
				session.getTransaction().rollback();
			}
				finally{
					HibernateUtil.closeSession(session);
				}
			 return number.intValue();
		 }
		 
}
