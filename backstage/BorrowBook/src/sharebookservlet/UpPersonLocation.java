package sharebookservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;


@WebServlet("/UpPersonLocation")
public class UpPersonLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpPersonLocation() {
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
	       String latitude=request.getParameter("latitude");
	       String longitude=request.getParameter("longitude");
	       
	       
	       
	       UserDao ud=new UserDao();
	  	 
		    User u=ud.getUserbyid(userid);
			
		    u.setUserid(userid);
		    u.setLatitude(latitude);
		    u.setLongitude(longitude);
		    
		    ud.UpdatePersonLocation(u);
	       
	       
		    User u2=ud.getUserbyid(userid);
		    
	        StringBuilder str=new StringBuilder();
	        
	        if(u2!=null)
	        {
			   
		        str.append("{").append("\"userid\":\""+u2.getUserid()+"\"")
		        .append(",")
				.append("\"nickname\":\""+u2.getNickname() +"\"")
				
				.append(",")
				.append("\"userimages\":\""+u2.getUserimages() +"\"")
				
				.append(",")
				.append("\"sex\":\""+u2.getSex()+"\"")
				
				
				.append(",")
				.append("\"e_mail\":\""+u2.getE_mail()+"\"")
				
				.append(",")
				.append("\"phone\":\""+u2.getPhone()+"\"")
				
				.append(",")
				.append("\"p\":\""+u2.getP()+"\"")
				
				.append(",")
				.append("\"referstatus\":\""+u2.getReferstatus()+"\"")
				
				.append(",")
				.append("\"borrowwarnstatus\":\""+u2.getBorrowwarnstatus()+"\"")
				.append(",")
				.append("\"personQR\":\""+u2.getPersonQR()+"\"")
				.append(",")
				.append("\"latitude\":\""+u2.getLatitude()+"\"")
				.append(",")
				.append("\"longitude\":\""+u2.getLongitude()+"\"")
				
				 .append("}");
		        
	    	
	     	out.write(str.toString());
	        }
		    
	       
	       
	}

}
