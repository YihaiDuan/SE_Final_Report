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

import dao.BookSonDao;
import dao.BorrowTableDao;
import dao.UserDao;
import entity.BookSon;
import entity.BorrowTable;
import entity.User;
import timer.TimerBorrowLan;


@WebServlet("/AddBorrowLan")
public class AddBorrowLan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddBorrowLan() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		
		  String userid=request.getParameter("userid");
	      String bookid=request.getParameter("bookid");
	      String formId=request.getParameter("formId");
	     
	      
	       BorrowTableDao bdt=new BorrowTableDao();
	       BookSonDao bsd=new BookSonDao();
	       BookSon bs=bsd.getNoBorrowNoOrderBookSon(bookid);
	      
	      
	      //先判断用户是否已经借阅两本   
	      if(bdt.getBorrowLanNum(userid)==2)
	      {
	    	  
	    	  out.write("borrowflow");
	    	  
	      }
	      else
	      {
	      
        //获取book下的一本图书进行添加到借书栏
	      //根据书目录id获取下面的所有可借书籍
          String booksonid=null;
	       if(bs!=null)
	       {
	    	   
	    	 booksonid=bs.getBooksonid();
	    	 
	    	 
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
//		   b.setOrderstatus(1);
		   bd.UpdateBookOrderStatus(b);
		   
		   
		   BorrowTableDao btd1=new BorrowTableDao();
			  BorrowTable  bt1=btd1.getNoDeadline(userid, booksonid);
			   
			   int borrowid=bt1.getId();
			   System.out.println(borrowid);
			   
			  TimerBorrowLan t=new TimerBorrowLan(60*60*3*1000,booksonid,userid,borrowid);
			  
			   t.start();
		   
		   
		  out.write("success");
	    	 
	    	 
	    	 
	    	 
	    	 
	       }
	       else
	       {
	    	   
	    	   out.write("failure");
	       }
	      }
		
	}

}
