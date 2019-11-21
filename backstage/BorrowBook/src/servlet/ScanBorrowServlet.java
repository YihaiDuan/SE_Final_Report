package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timer.TimerBorrowBookWarn;

import dao.BookDao;
import dao.BookSonDao;
import dao.BorrowTableDao;
import dao.ConfirmOrderDao;
import dao.UserDao;
import entity.Book;
import entity.BookSon;
import entity.BorrowTable;
import entity.ConfirmOrder;
import entity.User;

import javax.servlet.annotation.WebServlet;

@WebServlet("/ScanBorrowServlet")
public class ScanBorrowServlet extends HttpServlet {

	
	public ScanBorrowServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}


	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
			{
		
		
	             doPost(request,response);

		
	       }


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
		{
		
		  response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      
		  PrintWriter out=response.getWriter() ;
		  
		  Date date =new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  String confirmdate= sdf.format(date);
		  
		  
		 
		  String adminname=request.getParameter("userId");
		  //保存此次的处理订单下的图书号
		  String borrowinf=request.getParameter("bookid");
		  
		  String count=request.getParameter("count");
		  //String id[]=
		  
		  
	
		  String id[]=borrowinf.split(",");
		  
		  for(int i=0;i<id.length;i++)
		  {
		
	      System.out.println("管理员点击了确认借书");
	  
     	System.out.println("borrowid="+id[i]);
	
		  }
		   
		  BorrowTableDao bd=new BorrowTableDao();
          BorrowTable bt=new BorrowTable();
          String username=null;
			 
		 for(int i=0;i<id.length;i++)
		  {
			 
			 
			 
			
			 BorrowTable btt=bd.getBorrowTablebyid(Integer.parseInt(id[i]));
			  bt.setScanstatus(1);
			
			 
			 
			 
			 username=btt.getUser().getNickname();
		 
		   //修改扫描位
		   bt.setId(Integer.parseInt(id[i]));
		 
		   bd.UpdateScanStatus(bt);
		   
		   
		 BorrowTable  bt2=bd.getBorrowTablebyid(Integer.parseInt(id[i]));
		 
		 
		
		 String booksonid=bt2.getBookson().getBooksonid();
		   
		 
		 //更新预订状态位
		 BookSonDao bsd=new BookSonDao();
		 BookSon bs=new BookSon();
//		 bs.setBooksonid(booksonid);
//		 bs.setOrderstatus(0);
//		 bsd.UpdateBookOrderStatus(bs);
		 
		
		 //更新借书状态位
		
		 bs.setBooksonid(booksonid);
		 bs.setBorrowstatus(1);
		 bsd.UpdateBookBorrowStatus(bs);
		 
		 
		 String userid = bt2.getUser().getUserid();
		 String openid=bt2.getUser().getOpenid();
		 String formid=bt2.getFormid();
		 
         int borrowid=bt2.getId();
		 
         
    

         UserDao ud=new UserDao();
         User u=ud.getUserbyid(userid);
       
 		int warn=u.getBorrowwarnstatus();  
 		
 		
 		
 	
    if(warn==1)  
    {
  
    	
   System.out.println("用户的还书提醒标志"+warn);
   System.out.println("用户的openid"+openid);
   	
	TimerBorrowBookWarn timer=new TimerBorrowBookWarn(20000,booksonid,userid,borrowid,formid,openid);
    timer.start();
   
    }
		 
		 
		 
	 }//for
		 
		 
		 //保存确认单
		         
		      ConfirmOrderDao cod=new ConfirmOrderDao();
			  ConfirmOrder cf=new ConfirmOrder();
			
			  cf.setStatus(0);
			  cf.setUsername(username);
			  cf.setAdminname(adminname);
			  cf.setBorrowinf(borrowinf);
			  cf.setDate(confirmdate.substring(0,confirmdate.length()-3));
			  cf.setCount(count);
			  cod.SaveConfireOrder(cf);
		  
		  
	           out.write("609");
		  
		  
 }
  
	
	public void init() throws ServletException 
	      
	{
	
	}

}
