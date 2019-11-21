
package Extenddao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Discount;
import util.HibernateUtil;

//打折卷和优惠劵
public class DiscountDao 
{
	
	
	//添加打折卷和优惠劵
	public int SaveDiscount(Discount  d)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(d);
	
		tx.commit();
	    HibernateUtil.closeSession(session);
	    
	    return d.getId();
	    
   }
	
	//根据id获取打折卷
	public Discount  getDisCountByid(int id)
	{
		
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		Discount ds=(Discount) session.get(Discount.class,id);
		
		
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
		
		if(ds!=null)
		{
			
			
			return ds;
		}
		else
		{
			
			
			return null;
		}
		
	}
	
	//更新打折的标志位
	public void UpdateShowStaus(Discount ds)
	{
		
		
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		Discount ds1=(Discount) session.get(Discount.class,ds.getId());
		
		ds1.setShowstatus(ds.getShowstatus());
		
		session.update(ds1);
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
	}
	
	
	public List<Discount>  getScoreDiscount()
	{
		
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<Discount> ds=session.createQuery("from Discount where status=1").list();
		
		
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
		
		if(ds!=null&&ds.size()>0)
		{
			
			
			return ds;
		}
		else
		{
			
			
			return null;
		}
	
	}
	//删除对象
		public void deleteDiscount(Discount discount){
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				session.delete(discount);
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			finally{
				HibernateUtil.closeSession(session);
			}

		}//删除全部已过期
		public void deleteOutTimeDiscount(){
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				String hql = "delete from Discount where showstatus=2";
				session.createQuery(hql).executeUpdate();
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
		//修改对象
		public void updateDiscount(Discount discount){
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				session.update(discount);
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
