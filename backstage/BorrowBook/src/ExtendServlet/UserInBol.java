package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;


@WebServlet("/UserInBol")
public class UserInBol extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserInBol() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		      doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		    String userid=request.getParameter("userid");
		    
		    UserDao ud=new UserDao();
		    User u=ud.getUserbyid(userid);
		    
		    if(u!=null)
		    {
		    	
		    	out.write("1");
		   
		    }
		    else
		    {
		    	
		    	out.write("0");
		    }
		   
	
	}

}
