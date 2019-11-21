package ReferSys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;
import entity.UserToUsers;


@WebServlet("/ReferPerson")
public class ReferPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReferPerson() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      
		
		          doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      PrintWriter out = response.getWriter();
	      String userid=request.getParameter("userid");
	      StringBuilder str=new StringBuilder();
	      
	      ReferDao rd=new ReferDao();
	      
	      UserToUsers uu=rd.getUserToUsers(userid);
	      
	      if(uu!=null)
	      {
	    	  String us=uu.getUsers();
	    	  
	    	  if(us!=null)
	    	  {
	    		  
	    		  String uus[]=us.split("@");
	    		  
	    		 for(int i=0;i<uus.length;i++)
	    		 {
	    			 
	    			 UserDao ud=new UserDao();
	    			   User u=ud.getUserbyid(uus[i]);
	    			 if(u!=null)
	    			 {
	    			
	    			 str.append("{").append("\"userid\":\""+u.getUserid()+"\"")
						.append(",")
						.append("\"userimages\":\""+u.getUserimages() +"\"")
						.append(",")
						.append("\"nickname\":\""+u.getNickname()+"\"")
						 .append("}").append(",");
	    			 }
	    			
	    		 
	    		 }//for
	    		  
	    		 
	    		   out.write("["+str.substring(0,str.length()-1)+"]");
	    	  }//if
	    	  
	    	  
	    	  
	      }
			

	
	}

}
