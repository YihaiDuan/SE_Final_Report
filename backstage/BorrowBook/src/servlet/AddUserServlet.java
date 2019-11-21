package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;


@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddUserServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		
		response.setContentType("application/json;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
	       
	       String userid=request.getParameter("userid");
	       String password=request.getParameter("password");
	       String nickname=request.getParameter("nickname");
	       String e_mail=request.getParameter("e_mail");
	       String phone=userid;
	      
	       String sex=request.getParameter("sex");
	       String openid=request.getParameter("openid");
	       String nicesign=request.getParameter("nicesign");
	       
	       
	       Date date=new Date();
	       SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
	       String curdate=sdf.format(date);
	       
	       
	       
	      
	       System.out.println("用户名:"+userid);
	       System.out.println("password:"+password);
	       System.out.println("nickname:"+nickname);
	       System.out.println("e_mail:"+e_mail);
	       System.out.println("phone:"+phone);
	       System.out.println("sex:"+sex);
	       System.out.println("openid:"+openid);
	       System.out.println("nicesign:"+nicesign);
	       System.out.println("curdate:"+curdate);
	       
	    
	     
	    
	       if(e_mail.equals("wu"))
	       {
	    	   
	    	   e_mail="未填";
	    	   
	    	   
	       }
	       if(phone.equals("wu"))
	       {
	    	   
	    	   phone="未填";
	    	   
	    	   
	       }
	       
	       if(nicesign.equals("wu"))
	       {
	    	   
	    	   phone="未填";
	    	   
	    	   
	       }
	       System.out.println(e_mail);
	       System.out.println(phone);
	       
	       
	       
	       UserDao ud=new UserDao();
	        User u=new User();
	      
	        u.setUserid(userid);
	        u.setNickname(nickname);
	        u.setE_mail(e_mail);
	        u.setPassword(password);
	        u.setPhone(phone);
	        u.setSex(sex);
	        u.setBorrowwarnstatus(1);
	        u.setOpenid(openid);
	        u.setP(6);
	        u.setNicesign(nicesign);
	        u.setStarttime(curdate);
	        u.setMoney("0.0");
	        u.setScore(0);
	        u.setUserimages("http://localhost：8080/BorrowBook/findbookimg/userimages.png");
	        
	        ud.SaveUserData(u);
	       
	        out.write("success");
	       
	       
		
	}

}
