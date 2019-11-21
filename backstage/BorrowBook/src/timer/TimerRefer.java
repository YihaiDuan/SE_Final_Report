package timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ReferSys.ReferDao;
import dao.BookDao;
import dao.ReferTableDao;
import dao.UserDao;
import entity.Book;
import entity.ReferTable;
import entity.User;
import entity.UserToBooks;

public class TimerRefer extends Thread{
	
	
	private String userid;
	private  int timecount=0;
	
	
  
	
	
	public TimerRefer(String userid) 
	{
		super();
		this.userid = userid;
	}






	public void run()
	{
		
	try 
	{
		
		sleep(1000);
		
		timecount++;
		
		System.out.println(timecount);
		
		
		//获取用户的设置的频率
		UserDao ud=new UserDao();
		User u=ud.getUserbyid(userid);
		
		int userp=u.getP();
		
		
		
		//判断用户是否开启了推荐提醒
		int referstatus=u.getReferstatus();
		
		if(referstatus==0)
		{
				
		System.out.println("用户关闭了推荐");
			
		
		User u2=new User();
		 
		u2.setUserid(userid);
		u2.setMaxnumber(0);
		   
	    ud.UpdateOpenReferStatus(u2);
		
		
		
		
		}
		else
		{
			
			if(timecount==userp*20)
			{
				
				timecount=0;
				System.out.println("我要提醒你");
				
				
				
				
			
				   
				   
				   ReferDao rd=new ReferDao();
				   
				   UserToBooks utb=rd.getUserToBooks(userid);	
				
				    String bs[]=utb.getBooks().split("@");
				
				
				int length=bs.length;
				
				int i = (int)(Math.random()*length); 
				
				
				BookDao bd=new BookDao();
				Book b=bd.getBookbyid(bs[i]);
				
				
				if(b!=null)
				{
					
					Date date= new Date();//创建一个时间对象，获取到当前的时间
					
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
					 
			        String time= sdf.format(date);//将当前时间格式化为需要的类型
					
					
					 

						ReferTableDao rtd=new ReferTableDao();
						ReferTable rt=new ReferTable();	
						
						
						 
						 
				         rt.setBook(b);
					     rt.setUserid(userid);
					     rt.setDate(time);
						   
						 rtd.SavaReferTable(rt);
						 System.out.println("添加成功");
					
				}
				
	         }
			
			this.run();
			
		}
		
		
	
		
	}
		catch (Exception e) 
		{
			
			System.out.println("线程错误"+e);
			e.printStackTrace();
		}
		
	}
	
	

}
