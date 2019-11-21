package hfhdao;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;
import entity.Admin;
import entity.Category;

public class CategoryDao {
    //ͨ��id���category����
	public  Category getidCategory(String categoryid)
	{
		Session session=HibernateUtil.getSession();
		Category category=null;
		try {
			session.beginTransaction();
			category=(Category) session.get(Category.class,categoryid);
			session.getTransaction().commit();
			HibernateUtil.closeSession(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return category;
	}
	//遍历得到所有数据
	public List<Category> getCategory(){
		Session session=HibernateUtil.getSession();
		List<Category> list=null;
		try {
			session.beginTransaction();
			String hql="from Category";
			list=session.createQuery(hql).list();
			session.getTransaction().commit();
			HibernateUtil.closeSession(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	
	}
	//添加新类别
	public void addCategory(Category category){
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.save(category);
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
	//根据大类id获得小类集合
	public List<Category> getListCategory(int id){
		Session session = HibernateUtil.getSession();
		List<Category> list =null;
		try {
			session.beginTransaction();
			String hql = "from Category as c where c.type.id=:id";
			list = session.createQuery(hql).setParameter("id",id).list();
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
	//根据名称获得小类对象
	public Category getNameCategory(String name){
		Session session = HibernateUtil.getSession();
		Category category = null;
		try {
			session.beginTransaction();
			String hql = "from Category where name:name";
			category = (Category) session.createQuery(hql).setParameter("name",name).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);
		}
		   return category;
		
	}
	public static void main(String[] args) {
	CategoryDao c=new CategoryDao();
	Category category=c.getidCategory("1");
	System.out.println(category);
	}
	
}
