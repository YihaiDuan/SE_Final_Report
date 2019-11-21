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
import timer.TimerRefer;


@WebServlet("/SetBorrowWarnStatus")
public class SetBorrowWarnStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SetBorrowWarnStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		  doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		 response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
	       
	      
	       String userid=request.getParameter("userid");
	       String action=request.getParameter("action");
	       
	       
	       
	       
	       UserDao ud=new UserDao();
	       

		     User u=new User();
		     
		     
		  if(action.equals("open"))
		  {
		     
		     u.setUserid(userid);
		     u.setBorrowwarnstatus(1);;
	       
		   ud.UpdateBorrowWarnStatus(u);
		
		   
		   
		  }
		  else
		  {
			  
			  u.setUserid(userid);
			  u.setBorrowwarnstatus(0);
			  
			  ud.UpdateBorrowWarnStatus(u);
		   
		  }
		
		

	     
	     
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
