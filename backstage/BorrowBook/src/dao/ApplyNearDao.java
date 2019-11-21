package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.ApplyNear;
import util.HibernateUtil;

/**
 * 
 * @author hjm
 * @todo   附近的人申请转借数据访问层
 * 
 */


public class ApplyNearDao
{
	
	
	
	//申请书保存
	public void SaveBookData(ApplyNear  a)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(a);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	
	
	   //获取申请内容otheruserid
	
	public List<ApplyNear>  getApplyByOtherid(String userid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		List<ApplyNear>  list=new ArrayList<ApplyNear>(0);
		
		list=session.createQuery("from ApplyNear where  otherid=?").setParameter(0,userid).list();
		
		
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		if(list.size()>0)
		{
			
			return list;
		}
		else
		{
			
			return null;
		}
		
	}
	
	
	
	
	
	  //获取自己的申请单   userid
	
	public List<ApplyNear>  getApplyByUserselfid(String userid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		List<ApplyNear>  list=new ArrayList<ApplyNear>(0);
		
		list=session.createQuery("from ApplyNear where  user_id=?").setParameter(0,userid).list();
		
		
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		if(list.size()>0)
		{
			
			return list;
		}
		else
		{
			
			return null;
		}
		
	}
	
	
	
	
	//更新申请的状态位
	public void UpateStatus(ApplyNear an)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		ApplyNear an1=(ApplyNear) session.get(ApplyNear.class,an.getId());
		
		 an1.setStatus(an.getStatus());
		 
		 session.update(an1);
		 
		 tx.commit();
		 HibernateUtil.closeSession(session);
	
	}
	
	
	//更新是否已经读了的状态位
	public void UpateReadStatus(ApplyNear an)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		ApplyNear an1=(ApplyNear) session.get(ApplyNear.class,an.getId());
		
		an1.setReadstatus(an.getReadstatus());
		 
		 session.update(an1);
		 
		 tx.commit();
		 HibernateUtil.closeSession(session);
	
	}
	
	
	
	
	//更新是否操作已经读了的状态位
	public void UpateDoStatus(ApplyNear an)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		ApplyNear an1=(ApplyNear) session.get(ApplyNear.class,an.getId());
		
		an1.setDostatus(an.getDostatus());
		 
		 session.update(an1);
		 
		 tx.commit();
		 HibernateUtil.closeSession(session);
	
	}
	
	//更新二维码的状态位
	public void UpateQR(ApplyNear an)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		ApplyNear an1=(ApplyNear) session.get(ApplyNear.class,an.getId());
		
		an1.setPersonQR(an.getPersonQR());
		 
		 session.update(an1);
		 
		 tx.commit();
		 HibernateUtil.closeSession(session);
	
	}
	
	
	//获取转让根据id
	public ApplyNear getApplynearbyid(int id)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		ApplyNear an=null;
		
		an=(ApplyNear) session.get(ApplyNear.class,id);
		
		 tx.commit();
		 HibernateUtil.closeSession(session);
		 
		  return an;
	
	}
	
	
	
	
	    //获取未读消息的状态
	public boolean getReadStatus(String userid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		List<ApplyNear>  list=new ArrayList<ApplyNear>(0);
		
		list=session.createQuery("from ApplyNear where  otherid=?  and readstatus=0").setParameter(0,userid).list();
		
		
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		if(list.size()>0)
		{
			
			return true;
		}
		else
		{
			
			return false;
		}
		
	
	}
	
	
	  //获取用户回复了你的申请的状态位
		public boolean getDoStatus(String userid)
		{
			
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			List<ApplyNear>  list=new ArrayList<ApplyNear>(0);
			
			list=session.createQuery("from ApplyNear where  user_id=?  and dostatus=1").setParameter(0,userid).list();
			
			
			
			tx.commit();
		    HibernateUtil.closeSession(session);
			
			if(list.size()>0)
			{
				
				return true;
			}
			else
			{
				
				return false;
			}
			
		
		}
		
	//判断用户是否已经申请
		public boolean ApplyBoolean(String userid,int borrowid)
		{
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			List<ApplyNear>  list=new ArrayList<ApplyNear>(0);
			
			list=session.createQuery("from ApplyNear where  user_id=?  and    dostatus<>1  and borrowid=?").setParameter(0,userid).setParameter(1,borrowid).list();
			
			
			
			tx.commit();
		    HibernateUtil.closeSession(session);
			
			if(list.size()>0)
			{
				
				return true;
			}
			else
			{
				
				return false;
			}	
		
			
		}
	
	

}
