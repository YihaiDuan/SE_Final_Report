package applyservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.OutPayDao;
import dao.BookSonDao;
import dao.BorrowTableDao;
import dao.UserDao;
import entity.BookSon;
import entity.BorrowTable;
import entity.OutPay;
import entity.User;
import timer.TimerBorrowBookWarn;


@WebServlet("/TransmitServlet")
public class TransmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public TransmitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		     doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("application/json;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
	       
		     String userid=request.getParameter("userid");
		     String borrowid=request.getParameter("borrowid");
		     String formid=request.getParameter("formid");
		     
		     
		     String cash=request.getParameter("cash");
		       
		        
		        
		        
		          UserDao ud=new UserDao();
			       User u=ud.getUserbyid(userid);
			       
			       String oldmoney=u.getMoney();
			       
			       Double newmoney=Double.parseDouble(oldmoney)-Double.parseDouble(cash);
			      
			       
			       //更新用户钱包
			        u.setMoney(newmoney.toString());
			        
			        ud.UpdatePersonMoney(u);
			        
			        
			        //更新用户积分
			        int oldscore=u.getScore();
			        int newscore=oldscore+5;
			        
			        u.setScore(newscore);
			        
			        ud.UpdatePersonScore(u);
			        
			        
			      
		        
		     
		     Date date= new Date();//创建一个时间对象，获取到当前的时间
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置时间显示格式
			  String time= sdf.format(date);//将当前时间格式化为需要的类型
		     
			  
				 //生成到期时间
			    Calendar rightNow = Calendar.getInstance();
			   
			    rightNow.setTime(date);
		      
			    rightNow.add(Calendar.MONTH,1);
			    
			    Date dt1=rightNow.getTime();
			    String deadline = sdf.format(dt1);
			    System.out.println(deadline);
		     
		      BorrowTableDao btd=new BorrowTableDao();
		    
		      BorrowTable bt=btd.getBorrowTablebyid(Integer.parseInt(borrowid));
		     String booksonid=bt.getBookson().getBooksonid();
		     
		     
		     //给用户退款
		     String otherid=bt.getUser().getUserid();
		         
		        User u2=ud.getUserbyid(otherid);
		        
		        
		        
		        
		        //退款金额
			       double outmomey=Double.valueOf(cash);
			       
			       
			  
			       
			       
			       //退款时间
			       Date date2=new Date();
				   SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				   String nowdate=sdf2.format(date2);
				   
				   
				   //退款描述
				   String fromdes="来自图书转借押金退款";
				   
				   
				   //更新用户的钱包
				       User u3=ud.getUserbyid(otherid);
				      String oldmoney1=u3.getMoney();
				      Double newmoney1=Double.parseDouble(oldmoney1)+Double.parseDouble(cash);
				      //更新用户钱包
				        u3.setMoney(newmoney1.toString());
				        ud.UpdatePersonMoney(u3);
				   
				        
				        //添加退款记录
				   
				     OutPayDao opd=new OutPayDao();   
				     OutPay op=new OutPay(); 
				     op.setCash(Double.valueOf(cash));
				     op.setDate(nowdate);
				     op.setFromdes(fromdes);
				     op.setReadstatus(0);
				     op.setUser(u3);

			          opd.addOutPay(op);
			          
		        
		        
		       
		     
		      //修改图书的被借标志位为已经还书的标志位
		      //表示图书已经被借
		      
		       BorrowTable bt1=new BorrowTable();
		       
		      
		       bt1.setId(Integer.parseInt(borrowid));
		       bt1.setStatus(1);
		       
		       btd.UpdateBorrowStatus(bt1);
		       
		       
		       //更新还书时间
		       bt1.setReturndate(time);
		       btd.UpdateReturnBookDate(bt1);
		       
		       //转借者将转借书添加到借阅表
		       
		       BookSonDao bsd=new BookSonDao();
		       BookSon bs=bsd.getBookSon(booksonid);
		      
		       UserDao ud2=new UserDao();
		       User u4=ud2.getUserbyid(userid);
		       
		       BorrowTable bt2=new BorrowTable();
		       
		       bt2.setBookson(bs);
		       bt2.setScanstatus(1);
		       bt2.setUser(u4);
		       bt2.setBorrowdate(time);
		       bt2.setDeadline(deadline);
		       bt2.setFormid(formid);
		       
		       
		       btd.SaveBorrowTableData(bt2);
		       
		       
		         User u1=ud.getUserbyid(userid);
		       
		 		int warn=u1.getBorrowwarnstatus(); 
		 		String openid=u1.getOpenid();
		 		
		 		//获取刚刚借的书的borrowid
		 		BorrowTable td2=btd.getbBorrowInformateionbyDate(userid,time);
		 		
		 	
		 	  int id=td2.getId();
		 	
		    if(warn==1)  
		    {
		    //鍚姩杩樹功鎻愰啋 
		    	
		   System.out.println("用户的还书提醒标志"+warn);
		   System.out.println("用户的openid"+openid);
		   	
			TimerBorrowBookWarn timer=new TimerBorrowBookWarn(20000,booksonid,userid,id,formid,openid);
		    timer.start();
		       
		       
	
		
	}
	}

}
