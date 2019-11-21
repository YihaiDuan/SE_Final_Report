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

import AccountServlet.ReChargeDao;
import dao.UserDao;
import entity.User;
import entity.UserVipRecharge;


@WebServlet("/PayGrade")
public class PayGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PayGrade() {
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
		   String totalmoney=request.getParameter("totalmoney");
		   String grade=request.getParameter("grade");
		   String month=request.getParameter("month");
		   
		   System.out.println(userid);
		   System.out.println("总价"+totalmoney);
           System.out.println("vip级别"+grade);	
           System.out.println("月份"+month);
		   
		   
		   Date date= new Date();
	          SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");//设置时间显示格式
			  String createdate= sdf.format(date);
		   
			  Calendar rightNow = Calendar.getInstance();
			   
			    rightNow.setTime(date);
		      
			    rightNow.add(Calendar.MONTH,Integer.parseInt(month));
			    
			    Date dt1=rightNow.getTime();
			    String deadline = sdf.format(dt1);
			    System.out.println(deadline);
				  
			          SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置时间显示格式
					  String redate= sdf1.format(date);
		  
           
					  
					  
           
           UserDao ud=new UserDao();
          
         
           
           User u=ud.getUserbyid(userid);
           
           ReChargeDao rd=new ReChargeDao();
           UserVipRecharge uvr=new UserVipRecharge();
           
           uvr.setTime(redate);
           uvr.setUser(u);
           uvr.setPrice(Double.valueOf(totalmoney));
           
           rd.SaveVipRecharge(uvr);
           
           UserDao ud2=new UserDao();
              User u2=ud2.getUserbyid(userid);
           u2.setGrade(Integer.parseInt(grade));
           u2.setDeadline(deadline);
           
           ud2.UpdateGrade(u2);
        
           
		
		
	}

}
