package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Refer;
import util.HibernateUtil;


/**
 * 
 * @author hjm
 * @todo 推荐阅读数据访问层
 */
public class ReferDao
{

	
	//添加用户浏览记录
	
	 public void SavaRefer(Refer r)
	 {
		 Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			session.save(r);
		
			tx.commit();
			HibernateUtil.closeSession(session);
	
	 }
	
	 //获取该用户访问最多的类型号
	 
	 
	 public String  getCategorybyMaxNum(String userid)
	 {
		 
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 
		Integer num=0;
		 
	num= (Integer) session.createQuery("select Max(num) from Refer where userid=?").
			setParameter(0,userid).uniqueResult();
		 
		System.out.println(num);
		 
	  
	    List<Refer> rlist=new ArrayList<Refer>();
	 
	    rlist=session.createQuery("from Refer where userid=? and num=?").
				setParameter(0,userid).setParameter(1,num).list();
		
	    
	    tx.commit();
		HibernateUtil.closeSession(session);
		
	 if(rlist!=null&&rlist.size()>0)
	 {
		 
		 Refer r=rlist.get(0);
		  
		 System.out.println(r.getCategory().getCategoryid());
		 return r.getCategory().getCategoryid();
	 }
	 else
	 {
		 
		 return null;
		 
	 }
		 
	 }
	 
	 
	 //更新用户对类型的访问数量
	public void UpDateNum(Refer r)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Refer r1=(Refer)session.get(Refer.class,r.getId());
		
		r1.setNum(r.getNum());
		
	    session.update(r1);
	    tx.commit();
		HibernateUtil.closeSession(session);
	
	}
	
	
	//判断盖类型和该用户是否已经存在
	public Refer getBolUserCategoryid(String userid,String categoryid)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		Refer r=null;
		r=(Refer) session.createQuery("from Refer as r where userid=? and r.category.categoryid=?").
				setParameter(0,userid).setParameter(1,categoryid).uniqueResult() ;
		
		
		tx.commit();
		HibernateUtil.closeSession(session);
		if(r!=null)
		{
			return r;
			
		}
		else{
			
			return null;
		}
	}
	 
	 
	 
	 
	public  static void main(String [] args)
	{
		
		
		ReferDao rd=new ReferDao();
		
		if(rd.getBolUserCategoryid("123", "9")!=null)
		{
			
			Refer r=rd.getBolUserCategoryid("123", "9");
			
			int id=r.getId();
			int num=r.getNum();
			
			r.setId(id);
			r.setNum(num+1);
			
			
			rd.UpDateNum(r);
		   System.out.println("更新成功");
		
		}
		else
		{
			
			Refer r=new Refer();
			/*r.setCategoryid("9");*/
			r.setUserid("123");
			rd.SavaRefer(r);
			
			
			  System.out.println("添加成功");
			
			
		}
		
		
	}
	
	
	
	
	
	
	
}
