package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timer.TimerBorrowLan;

import dao.BookDao;
import dao.BookSonDao;
import dao.BorrowTableDao;
import dao.OrderLineDao;
import dao.UserDao;
import entity.Book;
import entity.BookSon;
import entity.BorrowTable;
import entity.OnlineOrder;
import entity.User;


@WebServlet("/AddBorrowMain")
public class AddBorrowMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddBorrowMain() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		
		doPost(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		  String userid=request.getParameter("userid");
	      String booksonid=request.getParameter("bookid");
	      String formId=request.getParameter("formId");
	     
	      
	      
	      
	      BorrowTableDao bdt=new BorrowTableDao();
	     BookSonDao bsd=new BookSonDao();
	     
	    BookSon bs= bsd.getBookSon(booksonid);
	    
	     OrderLineDao od=new OrderLineDao();
	
	      
	      //如果没有该图书
	      if(!bsd.getBooleanBookSonbyBookSonid(booksonid))
	      {
	    	  
	    	  out.write("2");
	    	  
	      }
	      
	      //如果图书已经被借
	      else if(bs.getBorrowstatus()==1)
	      {
	    	  
	    	  
	    	  
	    	  out.write("1");
	    	  
	      }
	    //该图书你已经添加到借书栏
	      else if(bdt.BooleanAddBorrowLan(userid, booksonid))
	      {
	    	  
	    	  
	    	  out.write("borrowlan");
	    	  
	    	  
	      }
	      //你已经添加到借书栏两本
	      else if(bdt.getBorrowLanNum(userid)==2)
	      {
	    	  
	    	  out.write("borrowflow");
	    	  
	    	  
	      }
	     //如果改图书已经被用户预订
//	      else if(bs.getOrderstatus()==1)
//	      {
	    	  
	    	if(od.OrderBolUser(userid,booksonid))
	    	{
	    	 //修改预订表的预订状态
	    	 OnlineOrder o=new OnlineOrder();
	    	 
	    	 
	    	 
	    	 //预定的书已经取出
	    	 o.setId(od.getOrderByid(userid, booksonid).getId());
	    	 o.setStatus(1);
	    	 od.UpdateBorrowStatus(o);
	    	  
	    	  
	   
	    	//借书时间
		      Date date= new Date();
	          SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置时间显示格式
			  String borrowdate= sdf.format(date);
		      
		
				  try {
					date= sdf.parse(borrowdate);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				  
				  
				  
				  
				 //生成到期时间
			    Calendar rightNow = Calendar.getInstance();
			   
			    rightNow.setTime(date);
		      
			    rightNow.add(Calendar.MONTH,1);
			    
			    Date dt1=rightNow.getTime();
			    String deadline = sdf.format(dt1);
			    System.out.println(deadline);
		      
			   //生成警告时间
			    
			    
			    Calendar rightNow2 = Calendar.getInstance();
			    rightNow2.add(Calendar.DAY_OF_YEAR,23);
			    Date dt2=rightNow2.getTime();
			    String warndate=sdf.format(dt2);
			    System.out.println(warndate);
			    
			    
			    
			    
			  //生成订单销毁时间
			
		        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置时间显示格式
		
		      
			    Calendar rightNow3 = Calendar.getInstance();
			    rightNow3.add(Calendar.HOUR_OF_DAY,3);
			    Date dt3=rightNow3.getTime();
			    
			    String deadborrow=sdf2.format(dt3);
			    System.out.println(deadborrow);
			    
			    
			   
		
		   BorrowTableDao  btd=new BorrowTableDao(); 
		   BorrowTable bt=new BorrowTable();
		 
		   
		 
		   bt.setDeadline(deadline);
		   bt.setWarndate(warndate);
		   bt.setBorrowdate(borrowdate);
		   bt.setFormid(formId);
		   bt.setDeadborrow(deadborrow);
		   
		   
		    BookSonDao bd=new BookSonDao();
		    
		    BookSon b=bd.getBookSon(booksonid);
		   
		    bt.setBookson(b);
		    
		    UserDao ud=new UserDao();
		    User u=ud.getUserbyid(userid);
		    bt.setUser(u);
		  
		    btd.SaveBorrowTableData(bt);
		    
		    
		    
		    //此时不允许其他人预订这本书
		    //修改预定位的值
		   b.setBooksonid(booksonid);
//		   b.setOrderstatus(1);
		   bd.UpdateBookOrderStatus(b);
		   
		   
		   
		   
		  BorrowTableDao btd1=new BorrowTableDao();
		  BorrowTable  bt1=btd1.getNoDeadline(userid, booksonid);
		   
		   int borrowid=bt1.getId();
		   System.out.println(borrowid);
		   
		   TimerBorrowLan t=new TimerBorrowLan(300000,booksonid,userid,borrowid);
		  
		   t.start();
		   
		   
		   
		      out.write("ordersuccess");
		    
		    
	    	}
	    	else
	    	{
	    		
	    		
	    		out.write("orderfail");
	    		
	    		
	    		
	//    	}
	  	    
	    	  
	     }
//	      else
//	      {
	      
	      //借书时间
	      Date date= new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置时间显示格式
		  String borrowdate= sdf.format(date);
	      
	
			  try {
				date= sdf.parse(borrowdate);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			  
			  
			  
			  
			 //生成到期时间
		  Calendar rightNow = Calendar.getInstance();
		   
		    rightNow.setTime(date);
	      
		    rightNow.add(Calendar.MONTH,1);
		    
		    Date dt1=rightNow.getTime();
		    String deadline = sdf.format(dt1);
		    System.out.println(deadline);
	      
		   //生成警告时间
		    
		    
		    Calendar rightNow2 = Calendar.getInstance();
		    rightNow2.add(Calendar.DAY_OF_YEAR,23);
		    Date dt2=rightNow2.getTime();
		    String warndate=sdf.format(dt2);
		    System.out.println(warndate);
		    
		    
		    
		    
		  //生成订单销毁时间
		
	        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置时间显示格式
	
	      
		    Calendar rightNow3 = Calendar.getInstance();
		    rightNow3.add(Calendar.HOUR_OF_DAY,3);
		    Date dt3=rightNow3.getTime();
		    
		    String deadborrow=sdf2.format(dt3);
		    System.out.println(deadborrow);
		    
		    
		   
	
	   BorrowTableDao  btd=new BorrowTableDao(); 
	   BorrowTable bt=new BorrowTable();
	 
	   
	 
	   bt.setDeadline(deadline);
	   bt.setWarndate(warndate);
	   bt.setBorrowdate(borrowdate);
	   bt.setFormid(formId);
	   bt.setDeadborrow(deadborrow);
	   
	   
	   BookSonDao bd=new BookSonDao();
	    
	    BookSon b=bd.getBookSon(booksonid);
	   
	    bt.setBookson(b);
	    
	    UserDao ud=new UserDao();
	    User u=ud.getUserbyid(userid);
	    bt.setUser(u);
	  
	    btd.SaveBorrowTableData(bt);
	    
	    
	    
	    
	    //修改预定位的值
	   b.setBooksonid(booksonid);;
//	   b.setOrderstatus(1);
	   bd.UpdateBookOrderStatus(b);
	   
	   
	   BorrowTableDao btd1=new BorrowTableDao();
		  BorrowTable  bt1=btd1.getNoDeadline(userid, booksonid);
		   
		   int borrowid=bt1.getId();
		   System.out.println(borrowid);
		   
		   TimerBorrowLan t=new TimerBorrowLan(300000,booksonid,userid,borrowid);
		  
		   t.start();
	   
	   
	  out.write("success");
	      
	  
	    }

//	}


	private TimerBorrowLan TimerBorrowLan(int i, String bookid, String userid, int borrowid) {
		// TODO Auto-generated method stub
		return null;
	}

}
