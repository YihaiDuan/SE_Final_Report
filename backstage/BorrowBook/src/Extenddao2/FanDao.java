package Extenddao2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.DynamicAdmire;
import entity.Fan;
import util.HibernateUtil;

public class FanDao 
{
	

	
	public int  FanBol(String userid,String otherid)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
 List<Fan>  flist=session.
createQuery("from Fan as f where f.user.userid=? and otherid=?").
setParameter(0,userid).setParameter(1,otherid).list();
		
		

         tx.commit();
         HibernateUtil.closeSession(session);
         

		if(flist!=null&&flist.size()>0)
		{
			
			return 1;
			
		}
		else
		{
			
			return 0;
		}
	}
	
	
	//获取我关注的人
	public List<Fan>  getGodByUserid(String userid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
 List<Fan>  flist=session.
createQuery("from Fan as f where f.user.userid=?").
setParameter(0,userid).list();
		
 
 tx.commit();
 HibernateUtil.closeSession(session);
		
		  if(flist!=null&&flist.size()>0)
		  {
			  
			  return flist;
			  
		  }else
		  {
			  
			  return null;
			  
		  }
		
		
	}
	
	
	
	//获取关注我的人
	public List<Fan>  getFanByUserid(String userid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
 List<Fan>  flist=session.
createQuery("from Fan as f where otherid=?").
setParameter(0,userid).list();
		
 
 tx.commit();
 HibernateUtil.closeSession(session);
		
		  if(flist!=null&&flist.size()>0)
		  {
			  
			  return flist;
			  
		  }else
		  {
			  
			  return null;
			  
		  }
		
		
	}
	
	public static void  main(String[] args)
	{
		
		
		
		/*FanDao fd=new FanDao();
		boolean b=fd.FanBol("o50AL0U9sAMhbI3ZrqfTF7nmWduo","o50AL0U9sAMhbI3ZrqfTF7nmWduo");
		System.out.println(b);*/
		
		
	}
	
	
	
	
	
}
