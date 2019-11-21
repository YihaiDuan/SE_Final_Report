package timer;


import java.util.List;

import Extenddao.GroupBookDao;
import Extenddao.GroupDao;
import dao.BookDao;
import entity.Book;
import entity.GroupBook;
import entity.GroupMore;


public class TimerGroupStart   extends Thread
{
	
	
	private int time;
	private int groupid;
	
	
	
	

	
	
	public TimerGroupStart(int time,int groupid) {
		super();
		this.time = time;
		this.groupid = groupid;
		
	}






	public void run()
	{
		
	try {
		
	

		 Thread.sleep(time);
		 System.out.println("线程启动");
		
		
		 System.out.println("我是过去的组团id"+groupid);
		 
		 GroupDao gd=new GroupDao();
		 GroupMore gm= gd.getGroupMoreById(groupid);
		 
		 
		 //1为活动开启
		 if(gm!=null)
		 {
		 
		  gm.setId(groupid);
		  gm.setShowstatus(1);
		  gd.UpdateGroupStatus(gm);
		 
		 
		 }
		
		}
		
		catch (Exception e) 
		{
			
			System.out.println("组团过期定时器出错"+e);
			e.printStackTrace();
		}
		
	
	}
	
	
	
	
	
	

}
