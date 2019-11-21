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


@WebServlet("/ChangeP")
public class ChangeP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ChangeP() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		      doPost(request,response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

	       response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
	       
	       
	       
	          String userid=request.getParameter("userid");
	          
	          String p=request.getParameter("p");
	          
	          
	           UserDao ud=new UserDao();
	          
	          User u=new User();
	          
	          u.setUserid(userid);
	          u.setP(Integer.parseInt(p));
	          
	          
	          ud.UpdateP(u);
	          User u2=ud.getUserbyid(userid);
		      StringBuilder str=new StringBuilder();
	          
	          
	          
	          if(u2!=null)
	          {
			   
		        str.append("{").append("\"userid\":\""+u2.getUserid()+"\"")
		       .append(",")
				.append("\"p\":\""+u2.getP()+"\"")
				
				.append(",")
				.append("\"referstatus\":\""+u2.getReferstatus()+"\"")
				
				.append(",")
				.append("\"borrowwarnstatus\":\""+u2.getBorrowwarnstatus()+"\"")
				.append("}");
		    
	       	
	        	out.write(str.toString());
	        	
	        }
	          
		
		
		
	}

}
