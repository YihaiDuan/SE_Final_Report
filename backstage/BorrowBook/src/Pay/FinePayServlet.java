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


@WebServlet("/FinePayServlet")
public class FinePayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FinePayServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		   String cash=request.getParameter("cash");
		   String borrowid=request.getParameter("borrowid");
		   
		   System.out.println(cash);
		   
		       UserDao ud=new UserDao();
		       User u=ud.getUserbyid(userid);
		       
		       String oldmoney=u.getMoney();
		       
		       Double newmoney=Double.parseDouble(oldmoney)-Double.parseDouble(cash);
		      
		       
		       //更新用户钱包
		        u.setMoney(newmoney.toString());
		        
		        ud.UpdatePersonMoney(u);
		        
		        //更新还书日期
		        
		   	 //还书时间
			      Date date= new Date();
		          SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置时间显示格式
				  String newdate= sdf.format(date);
		        
				   Calendar rightNow = Calendar.getInstance();
				    
				    rightNow.setTime(date);
			      
				    rightNow.add(Calendar.DAY_OF_WEEK,1);
				    
				    Date dt1=rightNow.getTime();
				    String deadline = sdf.format(dt1);
				    System.out.println(deadline);
				    
				  BorrowTableDao  btd=new BorrowTableDao();  
				  BorrowTable bt= btd.getBorrowTablebyid(Integer.parseInt(borrowid));
				   
				    bt.setDeadline(deadline);
		            btd. UpdateDeadline(bt);
	
	}

}
