package timer;

import dao.BookSonDao;
import dao.BorrowTableDao;
import entity.BookSon;
import entity.BorrowTable;

public class TimerBorrowLan extends Thread
{

	private int time;
	private String booksonid;
	private String userid;
	private int borrowid;
	
	
	public TimerBorrowLan(int time,String booksonid,String userid,int borrowid)
	{
		
		
		this.time=time;
		this.booksonid=booksonid;
		this.userid=userid;
		this.borrowid=borrowid;
	
	}
	
	
	

	public void run()
	{
		
	try {
		
	
		Thread.sleep(time);
		
		BorrowTableDao btd=new BorrowTableDao();
		BorrowTable  bt=btd.getBorrowTablebyid(borrowid);
	    int scanstatus=bt.getScanstatus();
		
		if(scanstatus==0)
		{
			
		System.out.println("我是过期了.....我的预订位将被修改"+"用户名:"+userid+"书本id:"+booksonid);
		
		
		BookSonDao bd=new BookSonDao();
		BookSon b=new BookSon();
		b.setBooksonid(booksonid);
//		b.setOrderstatus(0);
		bd.UpdateBookOrderStatus(b);
		
		
	    bt.setId(borrowid);
		bt.setDeadstatus(1);
		btd.UpdateDeadLineStauts(bt);
		
		
		
		}
		else
		{
			
	   System.out.println("我是没有过期的.....我的预订位不会被修改"+"用户名:"+userid+"书本id:"+booksonid);
			
			
		}
		
			
	
		}
		catch (Exception e) 
		{
			
			System.out.println("线程错误"+e);
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
}
