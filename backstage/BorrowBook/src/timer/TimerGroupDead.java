package timer;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Extenddao.GroupBookDao;
import Extenddao.GroupDao;
import Extenddao2.GroupMainDao;
import Extenddao2.OutPayDao;
import dao.BookDao;
import dao.UserDao;
import entity.Book;
import entity.GroupBook;
import entity.GroupMain;
import entity.GroupMember;
import entity.GroupMore;
import entity.OutPay;
import entity.User;


public class TimerGroupDead   extends Thread
{
	
	
	private int time;
	private int groupid;
	
	
	
	

	
	
	public TimerGroupDead(int time,int groupid) {
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
		 
		 if(gm!=null)
		 {
		 //showstatus  0 表示未开始  1表示进行中  2表示活动截止
		  gm.setId(groupid);
		  gm.setShowstatus(2);
		  gd.UpdateGroupStatus(gm);
		 
		  //将该团下的所有书的团修改为过期，同时找到该下的所有用户未完成团status=0进行退款同时设置status=2
		  
		  
		  //获取退款用户
		  GroupMainDao  gmd=new GroupMainDao(); 
		  List<GroupMain>  gmlist=gmd.getGroupMainDead(groupid);
		  
			if(gmlist!=null&&gmlist.size()>0)
	    	{
				
				for(int i=0;i<gmlist.size();i++)
				{
					
					GroupMain gm2=gmlist.get(i);
					
					
					//退款给的用户
					   UserDao ud=new UserDao();
					   User u=ud.getUserbyid(gm2.getUserid());
				       String userid=u.getUserid();
				       
				       BookDao bd=new BookDao();
				       Book b=bd.getBookbyid(gm2.getBookid());
				       
				       //退款金额
				      double cash=b.getCash();
				       
				       
				  
				       
				       
				       //退款时间
				       Date date=new Date();
					   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					   String nowdate=sdf.format(date);
					   
					   
					   //退款描述
					   String fromdes="来自拼团借书押金退款";
					   
					   
					   //更新用户的钱包
					      String oldmoney=u.getMoney();
					      Double newmoney=Double.parseDouble(oldmoney)+cash;
					      //更新用户钱包
					        u.setMoney(newmoney.toString());
					        ud.UpdatePersonMoney(u);
					   
					        
					        //添加退款记录
					   
					     OutPayDao opd=new OutPayDao();   
					     OutPay op=new OutPay(); 
					     op.setCash(cash);
					     op.setDate(nowdate);
					     op.setFromdes(fromdes);
					     op.setReadstatus(0);
					     op.setUser(u);
		
				          opd.addOutPay(op);
				          
				          
				          //更新团过期标志位
				          GroupMain  gmain=gmd.getGroupMainByid(gm2.getId());
				          gmain.setStatus(2);
				          gmd.UpdateGroupmainFull(gmain);
				          
				       
			List<GroupMember>  gmelist=gmd.getAllMemberbyid(gm2.getId());
		
		      
		
					
		           if(gmelist!=null&&gmelist.size()>0)
					{
						
						for(int j=0;j<gmelist.size();j++)
						{
							
							GroupMember gme=gmelist.get(j);
							

							
							//退款给的用户
							
							   User u2=ud.getUserbyid(gme.getUserid());
						       String userid2=u2.getUserid();
						       
						      
						       Book b2=bd.getBookbyid(gme.getBookid());
						       
						       //退款金额
						       double cash2=b2.getCash();
						       
						       
						  
						       
						       
						       //退款时间
						      /* Date date=new Date();
							   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							   String nowdate=sdf.format(date);*/
							   
							   
							   //退款描述
							/*   String fromdes="来自拼团借书押金退款";*/
							   
							   
							   //更新用户的钱包
							      String oldmoney2=u2.getMoney();
							      Double newmoney2=Double.parseDouble(oldmoney2)+cash2;
							      //更新用户钱包
							        u2.setMoney(newmoney2.toString());
							        ud.UpdatePersonMoney(u2);
							   
							        
							        //添加退款记录
							   
							 
							     OutPay op2=new OutPay(); 
							     op2.setCash(cash2);
							     op2.setDate(nowdate);
							     op2.setFromdes(fromdes);
							     op2.setReadstatus(0);
							     op2.setUser(u2);
				
						          opd.addOutPay(op2);		
							
							
							
							
						}
						
					}
					
					
				}
	    	}
		  
		 
		 }
	}
		
		catch (Exception e) 
		{
			
			System.out.println("组团过期定时器出错"+e);
			e.printStackTrace();
		}
		
	
	}
	
	
	
	
	
	

}
