package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import entity.OnlineOrder;
import util.HibernateUtil;

/**
 * 
 * @author hjm
 * @todo 在线预订数据访问层
 */
public class OrderLineDao {
	
	
	
	
	public void   SavaOrderLine(OnlineOrder o)
	{
		
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(o);
	
		tx.commit();
		  HibernateUtil.closeSession(session);
		

	}
	
	
	
	//更新预订状态位
    public void UpdateBorrowStatus(OnlineOrder o)
    {
   	 
   	 
   	 Session session=HibernateUtil.getSession();
   	 Transaction tx=session.beginTransaction();
   	 
   	OnlineOrder o1=(OnlineOrder)session.get(OnlineOrder.class,o.getId());
   	 
   	 o1.setStatus(o.getStatus());
   	
   	 session.update(o1);
   	 
   	 tx.commit();
   	
     HibernateUtil.closeSession(session);
   	 
    }
    
    
    //锟斤拷锟斤拷锟矫伙拷锟斤拷锟皆わ拷锟斤拷锟较�
	public List<OnlineOrder>  getOrderByuserid(String userid)
	{
		
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		  
	 List<OnlineOrder> list=session.createQuery("from OnlineOrder  as o where o.user.userid=?").
						setParameter(0,userid).list();
		  
		 	 tx.commit();
		 	  HibernateUtil.closeSession(session);
	        if(list!=null&&list.size()>0)
	        {
	         return list;
	        }
	        else
	        {
	        	
	        	return null;
	        }
		
	}
	
	public static void main(String [] args)
	{
		
		OrderLineDao old=new OrderLineDao();
		 List<OnlineOrder>  olist=old.getOrderByuserid("o50AL0U9sAMhbI3ZrqfTF7nmWduo");
		
		if(olist!=null)
		{
			
			System.out.println(olist.size());
		}
		
	}
	
	

    //是否是该用户预订
    public boolean  OrderBolUser(String userid,String booksonid)
    {
   	 
   	 Session session=HibernateUtil.getSession();
   	 Transaction tx=session.beginTransaction();
   	 
   	 OnlineOrder bt=null;
   	  
  bt=(OnlineOrder)session.createQuery("from OnlineOrder  as o  where o.user.userid =? and o.bookson.booksonid=?  and status=0").
   					setParameter(0,userid).setParameter(1,booksonid).uniqueResult();
   	  
  
             tx.commit();
             HibernateUtil.closeSession(session);
  
   	 	if(bt!=null)
   	 	{
   	 		
   	 		return true;
   	 		
   	 	}
   	 	else
   	 	{
   	 		
   	 		return false;
   	 		
   	 	}
           
          
   	 
    }
    
    //获取预订的单子
    public OnlineOrder getOrderByid(String userid,String booksonid)
    {
    	
    	 Session session=HibernateUtil.getSession();
       	 Transaction tx=session.beginTransaction();
    	
       	 
       	 OnlineOrder o=null;
      	  
         o=(OnlineOrder)session.createQuery("from OnlineOrder  as o  where o.user.userid =? and o.bookson.booksonid=?  and status=0").
          					setParameter(0,userid).setParameter(1,booksonid).uniqueResult();
          	  
         
         tx.commit();
         HibernateUtil.closeSession(session);
    	
    	
       	 if(o!=null)
       	 {
       		 return o;
       		 
       		
       	 }
       	 else
       	 {
       		 
       		 
       		 return null;
       	 }
   
    	
    }
	
    
    
    //获取预订的单子
    public OnlineOrder getOrderLineByid(int id)
    {
    	
    	 Session session=HibernateUtil.getSession();
       	 Transaction tx=session.beginTransaction();
    	
       	 
       	 OnlineOrder o=null;
      	  
         /*o=(OnlineOrder)session.createQuery("from OnlineOrder  as o  where id  =? ").
          					setParameter(0,id)uniqueResult();*/
          	  
         o=(OnlineOrder) session.get(OnlineOrder.class,id);
         tx.commit();
         HibernateUtil.closeSession(session);
    	
    	
       	 if(o!=null)
       	 {
       		 return o;
       		 
       		
       	 }
       	 else
       	 {
       		 
       		 
       		 return null;
       	 }
   
    	
    }
   
    public void DeleteOrder(int id)
    {
    	 Session session=HibernateUtil.getSession();
       	 Transaction tx=session.beginTransaction();
    	
    	OnlineOrder o=(OnlineOrder) session.get(OnlineOrder.class,id);
    	
    	if(o!=null)
    	{
    		
    		session.delete(o);
    		
    		
    	}
    	
    	  tx.commit();
          HibernateUtil.closeSession(session);
    	
    }
    
    public List<OnlineOrder> getOrderByidWarn(String bookid)
    {
    	
    	 Session session=HibernateUtil.getSession();
       	 Transaction tx=session.beginTransaction();
    	
       	 
       	 List<OnlineOrder> olist=null;
      	  
         olist=session.createQuery("from OnlineOrder  as o  where  o.book.bookid=?  and status=1").
          					setParameter(0,bookid).list();
          	  
         
                    tx.commit();
                    HibernateUtil.closeSession(session);
    	
    	
       	 if(olist.size()>0)
       	 {
       		 return olist;
       		 
       		
       	 }
       	 else
       	 {
       		 
       		 
       		 return null;
       	 }
    	
    	
    	
    }
	
	
  
    
    public boolean  OrderWarn(String userid,String bookid)
    {
   	 
   	 Session session=HibernateUtil.getSession();
   	 Transaction tx=session.beginTransaction();
   	 
   	 OnlineOrder bt=null;
   	  
  bt=(OnlineOrder)session.createQuery("from OnlineOrder  as o  where o.user.userid =? and o.bookson.booksonid=?  and status=1").
   					setParameter(0,userid).setParameter(1,bookid).uniqueResult();
   	  
  
             tx.commit();
             HibernateUtil.closeSession(session);
  
   	 	if(bt!=null)
   	 	{
   	 		
   	 		return true;
   	 		
   	 	}
   	 	else
   	 	{
   	 		
   	 		return false;
   	 		
   	 	}
           
          
   	 
    }
    
//判断用户预订的数量，超过两本不能预订
    public int getOnlineOrderNum(String userid)
    {
    	
    	 Session session=HibernateUtil.getSession();
       	 Transaction tx=session.beginTransaction();
       	 
       	List<OnlineOrder> olist=new ArrayList<OnlineOrder>(0);
    	
       	olist=session.createQuery("from OnlineOrder  as o  where o.user.userid =?  and status=0").
			setParameter(0,userid).list();
    	
       	
       	tx.commit();
        HibernateUtil.closeSession(session);
       	
       	if(olist.size()>0)
       	{
       		
       		return olist.size();
       	}
       	else
       	{
       		
       		return 0;
       	}
   	
    }
	
	

}
