package hfhdao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.cfg.HbmBinder;

import entity.Admin;
import util.HibernateUtil;

//����Ա������
public class AdminDao {
	//����Ա��¼
	public Admin login(String username,String password)
	{
		Session session=HibernateUtil.getSession();
		Admin admin=null;
		try {
			session.beginTransaction();
			String hql="from Admin where username=:username and password=:password";
			admin=(Admin)session.createQuery(hql)
					.setString("username",username)
					.setString("password",password)
					.uniqueResult();
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
		return admin;
		
	}
	//ɾ������Ա
	public void deleteAdmin(int id){
		Session session=HibernateUtil.getSession();
		Admin admin=null;
		try {
			session.beginTransaction();
			admin=(Admin)session.get(Admin.class, id);
			session.delete(admin);
			session.getTransaction().commit();
			HibernateUtil.closeSession(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		
	}
	//��ӹ���Ա
	public void addAdmin(Admin admin){
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.save(admin);
			session.getTransaction().commit();
			HibernateUtil.closeSession(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	
		
	}
	//根据id查询管理员信息
	public Admin getAdmin(int id){
		Session session=HibernateUtil.getSession();
		Admin admin = null;
		try {
			session.beginTransaction();
			admin =(Admin)session.get(Admin.class,id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
		}
         return admin;
	}
	//修改管理员信息
	public void updateAdmin(Admin admin){
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.update(admin);
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
	
}
