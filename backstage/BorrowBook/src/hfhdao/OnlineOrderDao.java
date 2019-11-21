package hfhdao;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import util.HibernateUtil;
import entity.Book;
import entity.BookSon;
import entity.Category;
import entity.OnlineOrder;
import entity.User;
  /**
   * 预定书操作
   * */
public class OnlineOrderDao {
   //修改预定记录标志位
	public void uploadOnlineOrderDao(int id){
		Session session=HibernateUtil.getSession();
		OnlineOrder onlineorder=null;
		try {
			session.beginTransaction();
			onlineorder=(OnlineOrder) session.get(OnlineOrder.class,id);
			onlineorder.setClearstatus(1);
			session.update(onlineorder);
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
	//通过id获取对象
	public OnlineOrder getOnlineOrder(int id){
		Session session=HibernateUtil.getSession();
		OnlineOrder onlineorder=null;
		try {
			session.beginTransaction();
			String hql="from OnlineOrder where status=0 and clearstatus=0 and id=:id";
			
			onlineorder=(OnlineOrder) session.createQuery(hql).setInteger("id",id).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			HibernateUtil.closeSession(session);

		}	
		return onlineorder;
	}
	  public User getUserOnlineOrder(int id){
		  
		   Session session = HibernateUtil.getSession();
		   User user = null;
		   try {
				session.beginTransaction();
				String hql="from User as u inner join fetch  u.onlineOrder o where o.id=?";
				user=(User)session.createQuery(hql).setInteger(0,id).uniqueResult();
				
				session.getTransaction().commit();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			finally{
				
				HibernateUtil.closeSession(session);
			}
			return user;
		  
		  
	  }
	  public static void main(String[] args) {
		OnlineOrderDao dao = new OnlineOrderDao();
	User user =	dao.getUserOnlineOrder(3);
	System.out.println(user);
		}


}
