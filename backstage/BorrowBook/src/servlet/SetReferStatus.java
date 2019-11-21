package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timer.TimerRefer;

import dao.UserDao;
import entity.User;

@WebServlet("/SetReferStatus")
public class SetReferStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SetReferStatus() {
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
	       String action=request.getParameter("action");
	       UserDao ud=new UserDao();
	       

		     User u=new User();
		     
		     
		  if(action.equals("open"))
		  {
		     
		     u.setUserid(userid);
		     u.setReferstatus(1);
	       
		   ud.UpdateReferStatus(u);
		   
		   
		   User u2=new User();
		   u2=ud.getUserbyid(userid);
		   
		   
		   //如果用户还没有开启推荐提醒线程则开启
		   if(u2.getMaxnumber()==0)
		   {
			   
			   
		   System.out.println("如果用户还没有开启推荐提醒线程则开启");
		   TimerRefer tr=new TimerRefer(userid);
		   tr.start();
		   
		   u2.setUserid(userid);
		   u2.setMaxnumber(1);
		   
		   ud.UpdateOpenReferStatus(u2);
		   
		   
		   }
		 
		   
		   
		  }
		  else
		  {
			  
			  u.setUserid(userid);
			  u.setReferstatus(0);
			  
			  ud.UpdateReferStatus(u);
		   
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
