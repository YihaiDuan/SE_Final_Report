package dao;

import java.util.ArrayList;


import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

import entity.BorrowTable;

/**
 * 
 * @author hjm
 * @todo 图书借阅数据访问层
 */

public class BorrowTableDao 
{
	
    //保存借书记录
	
	public void SaveBorrowTableData(BorrowTable  bt)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(bt);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	
	
	//获取还书栏信息
	
	
  public   List<BorrowTable>  getBookBorrowData(String userid)
  {
	  
	 Session session=HibernateUtil.getSession();
	 Transaction tx=session.beginTransaction();
	  
 List<BorrowTable> list=session.createQuery("from BorrowTable as b where b.user.userid =? and scanstatus=1  and status=0  and returnstatus=0").
					setParameter(0,userid).list();
	  
	 	 tx.commit();
	 	  HibernateUtil.closeSession(session);
        
         
         return list;
	  
      }
  
  
  
  
  
  //获取借书记录
  public   List<BorrowTable>  getBookReturnData(String userid)
  {
	  
	 Session session=HibernateUtil.getSession();
	 Transaction tx=session.beginTransaction();
	 List<BorrowTable> list=new ArrayList<BorrowTable>(0);  
list=session.createQuery("from BorrowTable as b where b.user.userid =? and status=1").
					setParameter(0,userid).list();
	  
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
	
	
  
  //根据id获取借书对象
  
  public BorrowTable getBorrowTablebyid(int id)
  {
	     Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 
		 BorrowTable bt=(BorrowTable)session.get(BorrowTable.class,id);
	  
	  
		 tx.commit();
		  HibernateUtil.closeSession(session);
         
         return bt;
	  
	  
  }
	
  
  
  
  //根基用户和书id和借书时间获得借书记录
  public BorrowTable getbBorrowInformateion(String userid,String bookid,String borrowdate)
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	  
 BorrowTable bt=(BorrowTable) session.createQuery("from BorrowTable  where user_id =? and book_id=?  and borrowdate=?").
 					setParameter(0,userid).setParameter(1,bookid).setParameter(2,borrowdate).uniqueResult();
 	  
 	 	 tx.commit();
 	 	  HibernateUtil.closeSession(session);
         
          
          return bt;
 	  
  }
	
  
  

 
  
  //根据用户和借书时间获得借书记录
  public BorrowTable getbBorrowInformateionbyDate(String userid,String borrowdate)
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	  
 BorrowTable bt=(BorrowTable) session.createQuery("from BorrowTable  where user_id =?  and borrowdate=?").
 					setParameter(0,userid).setParameter(1,borrowdate).uniqueResult();
 	  
 	 	 tx.commit();
 	 	  HibernateUtil.closeSession(session);
         
          
          return bt;
 	  
  }
	
  
  
  public void UpdateScanStatus(BorrowTable bt)   
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 
 	 
 	 BorrowTable bt1=(BorrowTable) session.get(BorrowTable.class,bt.getId());
 	 
 	 bt1.setScanstatus(bt.getScanstatus());
 	
 	 session.update(bt1);
 	 
 	 tx.commit();
 	
 	  HibernateUtil.closeSession(session);
 	 
  }
  
  
  
  
  public void UpdateDeadline(BorrowTable bt)   
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 
 	 
 	 BorrowTable bt1=(BorrowTable) session.get(BorrowTable.class,bt.getId());
 	 
 	 bt1.setDeadline(bt.getDeadline());
 	
 	 session.update(bt1);
 	 
 	 tx.commit();
 	
 	  HibernateUtil.closeSession(session);
 	 
  }
  
  
  
  
  public void UpdateBorrowDate(BorrowTable bt)   
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 
 	 
 	 BorrowTable bt1=(BorrowTable) session.get(BorrowTable.class,bt.getId());
 	 
 	 bt1.setBorrowdate(bt.getBorrowdate());;
 	
 	 session.update(bt1);
 	 
 	 tx.commit();
 	
 	  HibernateUtil.closeSession(session);
 	 
  }
  
  public void UpdateRenewStatus(BorrowTable bt)   
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 
 	 
 	 BorrowTable bt1=(BorrowTable) session.get(BorrowTable.class,bt.getId());
 	 
 	 bt1.setRenewstatus(bt.getRenewstatus());
 	
 	 session.update(bt1);
 	 
 	 tx.commit();
 	
 	  HibernateUtil.closeSession(session);
 	 
  }
  
  
  //更新评论位
  public void UpdateEvaluateStatus(BorrowTable bt)   
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 
 	 
 	 BorrowTable bt1=(BorrowTable) session.get(BorrowTable.class,bt.getId());
 	 
 	 bt1.setEvaluatestatus(bt.getEvaluatestatus());;
 	
 	 session.update(bt1);
 	 
 	 tx.commit();
 	
 	  HibernateUtil.closeSession(session);
 	 
  }
  
  
  

  //更新借单的借还位
  public void UpdateBorrowStatus(BorrowTable bt)
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 BorrowTable bt1=(BorrowTable) session.get(BorrowTable.class,bt.getId());
 	 
 	 bt1.setStatus(bt.getStatus());
 	
 	 session.update(bt1);
 	 
 	 tx.commit();
 	
 	  HibernateUtil.closeSession(session);
 	 
  }
  
  
  
  
  
	

  //获取还没有扫描过得借书二维码
  public   List<BorrowTable>  getBookNoScan(String userid)
  {
	  
	 Session session=HibernateUtil.getSession();
	 Transaction tx=session.beginTransaction();
	  
 List<BorrowTable> list=session.createQuery("from BorrowTable as b where b.user.userid =? and status=0 and scanstatus=0   and typestatus=0").
					setParameter(0,userid).list();
	  
	 	 tx.commit();
	 	  HibernateUtil.closeSession(session);
        
         
         return list;
	  
    }
	
   
  

  public void UpdateBorrowReturnDate(BorrowTable bt)
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 BorrowTable bt1=(BorrowTable) session.get(BorrowTable.class,bt.getId());
 	 
 	 bt1.setReturndate(bt.getReturndate());
 	
 	 session.update(bt1);
 	 
 	 tx.commit();
 	
 	  HibernateUtil.closeSession(session);
 	 
  }
  
//当用户点击了要还的书修改其状态

  public void UpdateBorrowReturnStatus(BorrowTable bt)
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 BorrowTable bt1=(BorrowTable) session.get(BorrowTable.class,bt.getId());
 	 
 	 bt1.setReturnstatus(bt.getReturnstatus());
 	
 	 session.update(bt1);
 	 
 	 tx.commit();
 	
 	  HibernateUtil.closeSession(session);
 	 
  }
  
  

  //根据借书id获取借书单信息
  public BorrowTable getBorrowbyId(int id)
  {
	  
	  
	 	 Session session=HibernateUtil.getSession();
	 	 Transaction tx=session.beginTransaction();
	 	 
	 	 
	 	BorrowTable bt=null;
	  
	 	bt=(BorrowTable) session.get(BorrowTable.class,id);
	 
	  
	  tx.commit();
	 	
 	  HibernateUtil.closeSession(session);
	 	
	  if(bt!=null)
	  {
		  
		  
		  return bt;
	  }
	  else
	  {
		  
		  return null;
		  
	  }
	 	 
	
  }
  
  
  
  //更新借单的还书时间间
  public void UpdateReturnBookDate(BorrowTable bt)
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 BorrowTable bt1=(BorrowTable) session.get(BorrowTable.class,bt.getId());
 	 
 	 bt1.setReturndate(bt.getReturndate());
 	
 	 session.update(bt1);
 	 
 	 tx.commit();
 	
 	  HibernateUtil.closeSession(session);
 	 
  }
  
  
  
  
  
  
 
  //更新借书栏借单的过期位
  public void UpdateDeadLineStauts(BorrowTable bt)
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 BorrowTable bt1=(BorrowTable) session.get(BorrowTable.class,bt.getId());
 	 
 	 bt1.setDeadstatus(bt.getDeadstatus());
 	
 	 session.update(bt1);
 	 
 	 tx.commit();
 	
 	  HibernateUtil.closeSession(session);
 	 
  }
  

  //获取借书栏未扫描未过期的二维码给管理员进行扫描
  public   List<BorrowTable>  getBookNoScanNoDeadLine(String userid)
  {
	  
	 Session session=HibernateUtil.getSession();
	 Transaction tx=session.beginTransaction();
	  
	 List<BorrowTable>  list=new ArrayList<BorrowTable>(0);
 list=session.createQuery("from BorrowTable as b where b.user.userid =? and status=0 and scanstatus=0  and deadstatus=0").
					setParameter(0,userid).list();
	  
	 	 tx.commit();
	 	  HibernateUtil.closeSession(session);
        
         if(list.size()>0)
         {
        	 return list; 
         }else
         {
        	 return null;
         }
	 
	  
    }
  
  
  
  
 
  //给管理员进行扫描准备还该书
  public   List<BorrowTable>  getBookOkScan(String userid)
  {
	  
	 Session session=HibernateUtil.getSession();
	 Transaction tx=session.beginTransaction();
	  
 List<BorrowTable> list=session.createQuery("from BorrowTable as b where b.user.userid =? and status=0 and returnstatus=0  and scanstatus=1").
					setParameter(0,userid).list();
	  
	 	 tx.commit();
	 	  HibernateUtil.closeSession(session);
        
         
         return list;
	  
    }
  
  
  
  //判断用户的借书栏二维码有没有超过二个，防止一次借阅超过两本
  public boolean BooleanAddBorrowLan(String userid,String booksonid)
  {
 	 
 	 
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 BorrowTable bt=null;
 	  
  bt=(BorrowTable) session.createQuery("from BorrowTable as bt where bt.user.userid =? and bt.bookson.booksonid=?  and deadstatus=0 and scanstatus=0").
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
	
  
  //获取没有过期的二维码,给管理员进行扫描
  public BorrowTable getNoDeadline(String userid,String booksonid)
  {
 	
 	 Session session=HibernateUtil.getSession();
 	 Transaction tx=session.beginTransaction();
 	 
 	 BorrowTable bt=null;
 	  
  bt=(BorrowTable)session.createQuery("from BorrowTable as bt where bt.user.userid =? and bt.bookson.booksonid=?  and deadstatus=0 and scanstatus=0").
 					setParameter(0,userid).setParameter(1,booksonid).uniqueResult();
 	  
 	 	 tx.commit();
 	 	  HibernateUtil.closeSession(session);
         
          
       return bt;
 	  
  }
	
  
 //删除借书栏信息
  public void DeleteBorrowLan(int id)
  {
	  Session session=HibernateUtil.getSession();
	 	 Transaction tx=session.beginTransaction();
	 	 
	 	 
	 	
	 	 BorrowTable bt=null;
	 	 bt=(BorrowTable) session.get(BorrowTable.class,id);
	 	
	 	 if(bt!=null)
	 	 {
	 		 
	 		 session.delete(bt);
	 		 
	 	 }
	 	 
	 	 tx.commit();
	 	  HibernateUtil.closeSession(session);
  }
  
  
 //借书栏数量
  
  public int getBorrowLanNum(String userid)
  {
	     Session session=HibernateUtil.getSession();
	 	 Transaction tx=session.beginTransaction();
	 	 
	 	 List<BorrowTable> blist=new ArrayList<BorrowTable>(0);
	 	  
	  blist=session.createQuery("from BorrowTable as b where b.user.userid =? and  deadstatus=0 and scanstatus=0").
	 					setParameter(0,userid).list();
	 	  
	 	 	 tx.commit();
	 	 	  HibernateUtil.closeSession(session);
	         
	 	if(blist.size()>0)
	 	{
	 	 	  return blist.size();	  
	 	}
	 	else
	 	{
	 		
	 		return 0;
	 	}
	          
  }
	 //获取所有用户用户的借单
  
      public List<BorrowTable>  getAllBorrow()
      {
    	  
    	  
    		 Session session=HibernateUtil.getSession();
    		 Transaction tx=session.beginTransaction();
    		 
    		 List<BorrowTable> list=new ArrayList<BorrowTable>(0);
    		  
    	 list=session.createQuery("from BorrowTable as b where status=0  and scanstatus=1").list();
    		  
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
  
  
      
        //获取用户的拼多多借书单
      public List<BorrowTable>  getAllGroupBorrow(String userid)
      {
    	  
    	  
    		 Session session=HibernateUtil.getSession();
    		 Transaction tx=session.beginTransaction();
    		 
    		 List<BorrowTable> list=new ArrayList<BorrowTable>(0);
    		  
    	 list=session.createQuery("from BorrowTable as b where status=0  and scanstatus=0 and typestatus=1 and b.user.userid=?").setParameter(0,userid).list();
    		  
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

         public static void main(String []  args)
         {
        	 BorrowTableDao btd=new BorrowTableDao();
        	 
        	 List<BorrowTable> list= btd.getAllGroupBorrow("o50AL0c4flWw6EwbKjvyzXDE20-w");
        	 
        	 if(list!=null)
        	 {
        		 
        		 
        		 System.out.println("===================================="+0);
        		 
        	 }
        	 
        	 
         }
  
}
