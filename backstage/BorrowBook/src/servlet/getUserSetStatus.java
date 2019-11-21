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


@WebServlet("/getUserSetStatus")
public class getUserSetStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getUserSetStatus() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		     doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		   
		PrintWriter out=response.getWriter() ;
	       
	       String userid=request.getParameter("userid");
	  
	        UserDao ud=new UserDao();
	     
	         User u=ud.getUserbyid(userid);
	         
	         
	          StringBuilder str=new StringBuilder();
	          
	          if(u!=null)
	          {
			   
		        str.append("{").append("\"userid\":\""+u.getUserid()+"\"")
		       .append(",")
				.append("\"p\":\""+u.getP()+"\"")
				
				.append(",")
				.append("\"referstatus\":\""+u.getReferstatus()+"\"")
				
				.append(",")
				.append("\"borrowwarnstatus\":\""+u.getBorrowwarnstatus()+"\"")
				.append("}");
		        
	       	
	        	out.write(str.toString());
	        	
	        }
		
	
		
	}

}
