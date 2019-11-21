package Pay;

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

import dao.BorrowTableDao;
import dao.UserDao;
import entity.BorrowTable;
import entity.User;
import timer.TimerBorrowBookWarn;


@WebServlet("/RenewPayServlet")
public class RenewPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RenewPayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		   
		   String borrowid=request.getParameter("borrowid");
		  //String cash=request.getParameter("cash");
		   String formid=request.getParameter("formid");
		   BorrowTableDao btd=new BorrowTableDao();
		   
		   BorrowTable bt= btd.getBorrowbyId(Integer.parseInt(borrowid));
		   
		  
		    UserDao ud=new UserDao();
		       User u=ud.getUserbyid(bt.getUser().getUserid());
		       
		       String oldmoney=u.getMoney();
		       
		       Double newmoney=Double.parseDouble(oldmoney)-bt.getBookson().getBook().getCash();
		      
		       
		       //更新用户钱包
		        u.setMoney(newmoney.toString());
		        
		        ud.UpdatePersonMoney(u);
		        
		        
		        
		        //借书时间
		        
		    	//借书时间
			      Date date= new Date();
		          SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置时间显示格式
				  String borrowdate= sdf.format(date);
		        
		        
				  bt.setBorrowdate(borrowdate);
				  
				  btd.UpdateBorrowDate(bt);
				  
		     
					 //生成到期时间
				    Calendar rightNow = Calendar.getInstance();
				   
				    rightNow.setTime(date);
			      
				    rightNow.add(Calendar.MONTH,1);
				    
				    Date dt1=rightNow.getTime();
				    String deadline = sdf.format(dt1);
				    System.out.println(deadline);
				    
				    bt.setDeadline(deadline);
				    btd.UpdateDeadline(bt);
			      
				    //更新formid
				    
				    //更新续借标志位
				    
				    bt.setRenewstatus(1);
				    btd.UpdateRenewStatus(bt);
				    
				    //生成警告时间
				  
					 String userid = bt.getUser().getUserid();
					 String openid=bt.getUser().getOpenid();
					// String formid=bt.getFormid();
					 String booksonid=bt.getBookson().getBooksonid();
			       
			 		int warn=u.getBorrowwarnstatus();  
			 		
				    if(warn==1)  
				    {
				  
				    	
				   System.out.println("用户的还书提醒标志"+warn);
				   System.out.println("用户的openid"+openid);
				   	
					TimerBorrowBookWarn timer=new TimerBorrowBookWarn(20000,booksonid,userid,Integer.parseInt(borrowid),formid,openid);
				    timer.start();
				   
				    }		    
				    
				    
				    
				  
		        
		   
		   
	}

}
