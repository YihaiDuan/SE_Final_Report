package timer;


import java.util.List;

import Extenddao.DiscountDao;
import Extenddao.GroupBookDao;
import Extenddao.GroupDao;
import dao.BookDao;
import entity.Book;
import entity.Discount;
import entity.GroupBook;
import entity.GroupMore;


public class TimerDiscountDead   extends Thread
{
	
	
	private int time;
	private int discountid;
	
	
	
	

	
	
	public TimerDiscountDead(int time,int discountid) {
		super();
		this.time = time;
		this.discountid = discountid;
		
	}






	public void run()
	{
		
	try {
		
	

		 Thread.sleep(time);
		 System.out.println("线程启动");
		
		
		 System.out.println("我是过来线程的打折id"+discountid);
		 
		 DiscountDao dsd=new DiscountDao();
		 Discount ds=dsd.getDisCountByid(discountid);
		 
		 //1为活动开启
		 if(ds!=null)
		 {
			 
			ds.setShowstatus(2);
		    dsd.UpdateShowStaus(ds);
		 System.out.println("我更新了为2");
		 }
		
		}
		
		catch (Exception e) 
		{
			
			System.out.println("优惠定时器出错"+e);
			e.printStackTrace();
		}
		
	
	}
	
	
	
	
	
	

}
