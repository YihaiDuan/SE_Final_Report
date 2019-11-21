package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import entity.Admin;
import util.MD5;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AdminLoginServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		


                 doPost(request,response);
	    
	    
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
		        
		        response.setContentType("text/html;charset=utf-8");
			      request.setCharacterEncoding("utf-8");
			      
			      PrintWriter out=response.getWriter();
			      
			      String userid=request.getParameter("userId");
			      String password=request.getParameter("password");
			      
			      userid = URLDecoder.decode(userid,"utf-8");
			      
			      
			      
			       AdminDao ad=new AdminDao();
			  
			       
			       System.out.println("userid"+userid);
			       System.out.println("password"+password);
				
			 
			       
			      if(ad.AdminLogin(userid, password))
			        {
			        	
			    	  Admin a=ad.getAdminbyid(userid);
			          request.getSession().setAttribute("admin",a);
			         out.write("609");
			         
			        	
			        }
			        else
			        {
			        	
			        	out.write("610");
			        	
			        	
			        }
	       
	       
		     
	}

}
