/**
 * 
 */
package Extenddao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Discount;
import entity.DiscountUser;
import util.HibernateUtil;

/**
 * @author Administrator
 * @date Jul 25, 2017
 * @todoTODO  用户获取优惠劵实体
 */
public class DiscountUserDao 
{

	
	//添加用户获取的优惠劵
	public void SaveDiscountUser(DiscountUser  du)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(du);
	
		tx.commit();
	    HibernateUtil.closeSession(session);
   }
	
	
	public boolean BolDiscount(String userid,int countid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
List<DiscountUser>  dlist=
session.createQuery("from DiscountUser as du where  userid=? and du.discount.id=? and status=0")
.setParameter(0,userid).setParameter(1,countid).list();
		
		


tx.commit();
HibernateUtil.closeSession(session);
if(dlist!=null&&dlist.size()>0)
{
	
return  true;
}
else
{
	
return false;

}
		
	}
	
	
public List<DiscountUser>   getMyDiscount(String userid)
{
	
	Session session=HibernateUtil.getSession();
	Transaction tx=session.beginTransaction();
	
List<DiscountUser>  dlist=
session.createQuery("from DiscountUser  where  userid=? and status=0")
.setParameter(0,userid).list();
tx.commit();
HibernateUtil.closeSession(session);

if(dlist!=null&&dlist.size()>0)
{
	
return  dlist;
}
else
{
	
return null;

}


}
	

public int  getMyDiscountSize(String userid)
{
	
	Session session=HibernateUtil.getSession();
	Transaction tx=session.beginTransaction();
	
	Object num=0;
	
num=
session.createQuery("select count(du.userid) from DiscountUser  as du where  du.userid=? and du.status=0")
.setParameter(0,userid).uniqueResult();
tx.commit();
HibernateUtil.closeSession(session);

    return Integer.parseInt(num.toString());


}



public void  UpdateDiscountUser(DiscountUser du)
{
	
	Session session=HibernateUtil.getSession();
	Transaction tx=session.beginTransaction();
	

	
	 DiscountUser du1=(DiscountUser) session.get(DiscountUser.class,du.getId());
	 
	 
	 if(du1!=null)
	 {
		 
		 du1.setStatus(du.getStatus());
		 session.update(du1);
		 
	 }

tx.commit();
HibernateUtil.closeSession(session);


}



public DiscountUser  getDiscountUserByid(int id)
{
	
	Session session=HibernateUtil.getSession();
	Transaction tx=session.beginTransaction();
	

	
	 DiscountUser du=(DiscountUser) session.get(DiscountUser.class,id);
	 
	 

tx.commit();
HibernateUtil.closeSession(session);
	 if(du!=null)
	 {
		 
		 return du;
		 
	 }


  return null;

}

}
