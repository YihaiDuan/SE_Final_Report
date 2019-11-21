package timer;

import dao.BookSonDao;
import dao.OrderLineDao;
import entity.BookSon;
import entity.OnlineOrder;


public class TimerOnlinDead extends Thread{
	
	private int time;
	private String booksonid;
	private String userid;
	
	
	
	
	public TimerOnlinDead(int time, String booksonid, String userid) {
		super();
		this.time = time;
		this.booksonid = booksonid;
		this.userid = userid;
	}
	
	
	
	


	public void run()
	{
		
try {
	
	    Thread.sleep(time);
		
		//判断该改预定单是否到期
		
		OrderLineDao old =new OrderLineDao();
	   
		OnlineOrder ol=old.getOrderByid(userid,booksonid);
		
		if(ol!=null)
		{
		int id=ol.getId();
			
		OnlineOrder o=new OnlineOrder();
		o.setId(id);
		o.setStatus(2);
		
		old.UpdateBorrowStatus(o);
		
		
		//修改预定位
		BookSonDao bsd=new BookSonDao();
		BookSon b=new BookSon();
		
		b.setBooksonid(booksonid);;
//		b.setOrderstatus(0);
		
		bsd.UpdateBookOrderStatus(b);
		
		}
		
	
	
		}
		catch (Exception e) 
		{
			
			System.out.println("线程错误"+e);
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	

}
