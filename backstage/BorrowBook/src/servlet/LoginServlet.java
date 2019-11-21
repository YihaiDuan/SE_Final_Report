package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	public LoginServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); 
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	      doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
			{

	
		
		  response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	   
	      
	       PrintWriter out=response.getWriter() ;
	       
	       String userid=request.getParameter("userid");
	       String password=request.getParameter("password");
	       
	       
	       UserDao ud=new UserDao();
	     
	        if(ud.UserLogin(userid, password))
	        {
	        	
	           User u=ud.getUserbyid(userid);
	           request.getSession().setAttribute("user",u);
	  
	           
	           
	           
	           StringBuilder str=new StringBuilder();
			   
		        str.append("{").append("\"userid\":\""+u.getUserid()+"\"")
		        .append(",")
				.append("\"nickname\":\""+u.getNickname() +"\"")
				
				.append(",")
				.append("\"userimages\":\""+u.getUserimages() +"\"")
				
				.append(",")
				.append("\"sex\":\""+u.getSex()+"\"")
				
				
				
				.append(",")
				.append("\"e_mail\":\""+u.getE_mail()+"\"")
				
				.append(",")
				.append("\"phone\":\""+u.getPhone()+"\"")
				
				.append(",")
				.append("\"p\":\""+u.getP()+"\"")
				
				.append(",")
				.append("\"referstatus\":\""+u.getReferstatus()+"\"")
				
				.append(",")
				.append("\"borrowwarnstatus\":\""+u.getBorrowwarnstatus()+"\"")
				.append(",")
				.append("\"personQR\":\""+u.getPersonQR()+"\"")
				.append(",")
				.append("\"latitude\":\""+u.getLatitude()+"\"")
				.append(",")
				.append("\"longitude\":\""+u.getLongitude()+"\"")
				.append(",")
				.append("\"backimg\":\""+u.getBackimg()+"\"")
				.append(",")
				.append("\"starttime\":\""+u.getStarttime()+"\"")
				
				.append(",")
				.append("\"nicesign\":\""+u.getNicesign()+"\"")
				
				 .append("}");
		        
	       	
	        	out.write(str.toString());
	        	
	        }
	        else
	        {
	        	
	        	out.write("failure");
	        	
	        	
	        }
	    
	    
	
		
		
	
	     }

	
	public void init() throws ServletException {
		// Put your code here
	}

}
