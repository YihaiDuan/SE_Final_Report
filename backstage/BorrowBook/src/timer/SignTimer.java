package timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.BookDao;
import dao.ReferDao;
import dao.ReferTableDao;
import dao.UserDao;
import entity.Book;
import entity.ReferTable;
import entity.User;

public class SignTimer extends Thread{
	
	
   private boolean flag=true;
   private String userid;






public SignTimer(String userid) {
	super();

	this.userid = userid;
}





	public void run()
	{
		
	try 
	{
		
//;
		while(flag)
		{
			
			sleep(1000);
			 Date  d=new Date();
				
			   SimpleDateFormat stf=new SimpleDateFormat("HH:mm:ss");
					
			    String nowdate=stf.format(d);
			    //System.out.println(nowdate);
					
				if(nowdate.equals("00:00:00"))
				{
						
				System.out.println("我要去更新标志位了");
				
			UserDao ud=new UserDao();
			   User u=ud.getUserbyid(userid);
			   if(u.getSign()==1)
			   {
				   
				   u.setSign(0);
				   ud.UpdateSignStatus(u);
				   
				   
			   }
				
				flag=false;
				
				}
			
		}
		
		
		 
	
	
	}
		catch (Exception e) 
		{
			
			System.out.println("线程错误"+e);
			e.printStackTrace();
		}
		
	}
	
	

}
