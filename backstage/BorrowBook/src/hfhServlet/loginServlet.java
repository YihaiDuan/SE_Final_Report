package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Admin;
import hfhdao.AdminDao;
import util.MD5;
@WebServlet(name="loginServlet",urlPatterns="/loginServlet")
public class loginServlet extends HttpServlet {

	public loginServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
              doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");    
		if(request.getParameter("action").equals("login"))
		{
			 String username=request.getParameter("username");
			 String password=request.getParameter("password");
			 AdminDao admindao=new AdminDao();
			 Admin admin=new Admin();
			 MD5 md=new MD5();
			 String newpassword=md.encrypt_30(password);
			 System.out.println(username);
			 System.out.println(newpassword);
			 
			 admin=admindao.login(username, newpassword);
			
			 if(admin!=null)
			 {
				 request.getSession().setAttribute("admin", username);
				 response.sendRedirect("index.jsp");
				 			 
			 }
			 else{
				// request.setAttribute("action","success");
				 response.sendRedirect("register.jsp?action=success");
				 
			 }
		
		}
		
		
		
		
	}

	
	   public static void main(String[] args)
	   {
		   
		  
		   AdminDao admindao=new AdminDao();
		  Admin admin=admindao.login("systemadmin","30D94CFB51ADEC726B87B4F0E33CE2");
		   System.out.println(admin);
	   }
	
	public void init() throws ServletException {
		// Put your code here
	}

}
