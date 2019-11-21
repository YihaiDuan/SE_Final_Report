package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entity.Book;
import entity.BorrowTable;
import entity.User;



public class UserDao {
	
	
	
	public void SaveUserData(User  u)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(u);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	

	
	

	public boolean UserLogin(String userid,String password)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
	     User u=null;
		
	  u=(User) session.createQuery("from User where userid=? and password=? ").
				setParameter(0,userid).setParameter(1,password).uniqueResult();
		
	    tx.commit();
	    HibernateUtil.closeSession(session);
	  
	  
	  
        if(u!=null)
        {
       
        	return true;	
        	
        	
        }
        else
        {
           return false;
        }
		
	}
	
	public User getUserbyid(String userid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		User u=null;
		u=(User)session.get(User.class,userid);
		
		
	    tx.commit();
	    
	    HibernateUtil.closeSession(session);
	 
		
		  if(u!=null)
		  {
			  
			  return u;
		  }
		  else
		  {
			  
			  return null;
		  }

	}
	
	
	public User getUserbyid2(String userid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		User u=null;
		u=(User)session.load(User.class,userid);
		
		
	    tx.commit();
	    
	    HibernateUtil.closeSession(session);
	 
		
		  if(u!=null)
		  {
			  
			  return u;
		  }
		  else
		  {
			  
			  return null;
		  }

	}
	
	
	
	
	
	//更新还书提醒标志位
	
	public void UpdateBorrowWarnStatus(User b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		User b1=(User)session.get(User.class,b.getUserid());
		
		b1.setBorrowwarnstatus(b.getBorrowwarnstatus());
		
	    session.update(b1);
		 tx.commit();
		  HibernateUtil.closeSession(session);
		 
	}
	
	
	//更新用户签到标志位
	
	public void UpdateSignStatus(User b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		User b1=(User)session.get(User.class,b.getUserid());
		b1.setSign(b.getSign());
		
	    session.update(b1);
		 tx.commit();
		  HibernateUtil.closeSession(session);
		 
	}
	
	
	//更新推荐提醒标志位
	
	public void UpdateReferStatus(User b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		User b1=(User)session.get(User.class,b.getUserid());
		
		b1.setReferstatus(b.getReferstatus());
		
	    session.update(b1);
		 tx.commit();
		  HibernateUtil.closeSession(session);
		 
	}
	
	
	//更新用户频率
	public void UpdateP(User b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		User b1=(User)session.get(User.class,b.getUserid());
		
		b1.setP(b.getP());
		
	    session.update(b1);
		 tx.commit();
		  HibernateUtil.closeSession(session);
		 
	}
	
	//更新用户头像
	public  void UpdatePersonImages(User u)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		User b1=(User)session.get(User.class,u.getUserid());
		
		b1.setUserimages(u.getUserimages());
		
	    session.update(b1);
		 tx.commit();
		 
		  HibernateUtil.closeSession(session);
		
		
		
	}
	
	
	
	//更新用户背景
	public  void UpdatePersonBackImages(User u)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		User b1=(User)session.get(User.class,u.getUserid());
		
		b1.setBackimg(u.getBackimg());
		
	    session.update(b1);
		 tx.commit();
		 
		  HibernateUtil.closeSession(session);
		
		
		
	}
	
	//更新用户个人二维码
	
	public void UpdatePersonQR(User b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		User b1=(User)session.get(User.class,b.getUserid());
		
		b1.setPersonQR(b.getPersonQR());
		
	    session.update(b1);
		 tx.commit();
		
		  HibernateUtil.closeSession(session);
	}
	
	//更新用户分享的位置
	
	public void UpdatePersonLocation(User b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		User b1=(User)session.get(User.class,b.getUserid());
		
		b1.setLatitude(b.getLatitude());
		b1.setLongitude(b.getLongitude());
		
	    session.update(b1);
		 tx.commit();
		  HibernateUtil.closeSession(session);
		 
	}

	
	//更新用户开启了推荐的标志位
	
		public void UpdateOpenReferStatus(User b)
		{
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			User b1=(User)session.get(User.class,b.getUserid());
			
			b1.setMaxnumber(b.getMaxnumber());;
			
		    session.update(b1);
			 tx.commit();
			  HibernateUtil.closeSession(session);
			 
		}
	
		
		public List<User> getAllUser()
		{
			
			
			 Session session=HibernateUtil.getSession();
			 Transaction tx=session.beginTransaction();
				  
	   List<User>  list=session.createQuery("from User").list();
			
	         tx.commit();
	        HibernateUtil.closeSession(session);
			 
	         return list;
			
		}
		
		
		
		
	
			

	
	
	//更新昵称
	

	
	public void UpdateNickname(User b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		User b1=(User)session.get(User.class,b.getUserid());
		
		b1.setNickname(b.getNickname());
		
	     session.update(b1);
		 tx.commit();
		 HibernateUtil.closeSession(session);
	}
	
	
	//更新e_mail
	

	
		public void UpdateE_mail(User b)
		{
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			User b1=(User)session.get(User.class,b.getUserid());
			
			b1.setE_mail(b.getE_mail());
			
		     session.update(b1);
			 tx.commit();
			 HibernateUtil.closeSession(session);
		}
		
		
		//更新手机号
		

		
		public void UpdatePhone(User b)
		{
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			User b1=(User)session.get(User.class,b.getUserid());
			
			b1.setPhone(b.getPhone());
			
		     session.update(b1);
			 tx.commit();
			// HibernateUtil.closeSession(session);
		}
		
		
		
	//更新手机号
		

		
		public void UpdateNickSign(User b)
		{
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			User b1=(User)session.get(User.class,b.getUserid());
			
			b1.setNicesign(b.getNicesign());
			
		     session.update(b1);
			 tx.commit();
			HibernateUtil.closeSession(session);
		}

		//更新sex
		public void UpdateSex(User b)
			{
				Session session=HibernateUtil.getSession();
				Transaction tx=session.beginTransaction();
				
				User b1=(User)session.get(User.class,b.getUserid());
				
				b1.setSex(b.getSex());
				
			     session.update(b1);
				 tx.commit();
				 HibernateUtil.closeSession(session);
			}
		

		
   
		
		//更新经度和纬度
		public void UpdatePosition(User b)
			{
				Session session=HibernateUtil.getSession();
				Transaction tx=session.beginTransaction();
				
				User b1=(User)session.get(User.class,b.getUserid());
				
				b1.setLatitude(b.getLatitude());
				b1.setLongitude(b.getLongitude());
				
			     session.update(b1);
				 tx.commit();
				 HibernateUtil.closeSession(session);
			}
		
		
		//更新用户钱包
		public  void UpdatePersonMoney(User u)
		{
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			User b1=(User)session.get(User.class,u.getUserid());
			
			b1.setMoney(u.getMoney());
			
		    session.update(b1);
			 tx.commit();
			 
			  HibernateUtil.closeSession(session);
			
		}
		
		
		//更新用户积分
				public  void UpdatePersonScore(User u)
				{
					
					Session session=HibernateUtil.getSession();
					Transaction tx=session.beginTransaction();
					
					User b1=(User)session.get(User.class,u.getUserid());
					
					b1.setScore(u.getScore());
					
				    session.update(b1);
					 tx.commit();
					 
					  HibernateUtil.closeSession(session);
					
				}

				
		/*		//更新开通vip标志位
				public void UpdatePayStatus(User b)
				{
					Session session=HibernateUtil.getSession();
					Transaction tx=session.beginTransaction();
					
					User b1=(User)session.get(User.class,b.getUserid());
					
					b1.setPaystatus(b.getPaystatus());
					
				    session.update(b1);
					 tx.commit();
					  HibernateUtil.closeSession(session);
					 
				}*/
				
				//更新vip等级标志位
				public void UpdateGrade(User b)
				{
					
					
				
					Session session=HibernateUtil.getSession();
					Transaction tx=session.beginTransaction();
					
				//	session.clear();
				//User b1=(User)session.get(User.class,b.getUserid());
					
					//b1.setGrade(b.getGrade());
					//b1.setDeadline(b.getDeadline());
					
				    session.update(b);
					 tx.commit();
					  HibernateUtil.closeSession(session);
					 
				}
				
				
				
				
				
				
		/*				
				//更新vip到期时间
				public void UpdateGradeDeadline(User b)
				{
					Session session=HibernateUtil.getSession();
					Transaction tx=session.beginTransaction();
					
					User b1=(User)session.get(User.class,b.getUserid());
					
					b1.setDeadline(b.getDeadline());
					
				    session.update(b1);
					 tx.commit();
					  HibernateUtil.closeSession(session);
					 
				}
				*/
				
				
				
				
				
				
		public static void main(String [] args)
		{
			
			
			
			  UserDao ud=new UserDao();
		       User u=ud.getUserbyid("o50AL0U9sAMhbI3ZrqfTF7nmWduo");
			System.out.println(u.getMoney());
			
		}
		
		
		
		
		
		
		
}
