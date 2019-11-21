package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.DiscountUserDao;
import dao.UserDao;
import entity.DiscountUser;
import entity.User;


@WebServlet("/getUserInformationServlet")
public class getUserInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getUserInformationServlet() {
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
	      
	      
	      
	      UserDao ud=new UserDao();
	
		   User u2=ud.getUserbyid(userid);
		    
	        StringBuilder str=new StringBuilder();
	        
	        if(u2!=null)
	        {
	        	
	        	
	        	DiscountUserDao dcu=new DiscountUserDao();
	           int discountnum= dcu.getMyDiscountSize(userid);
			   
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
				.append(",")
				.append("\"grade\":\""+u2.getGrade()+"\"")
				.append(",")
				.append("\"paystatus\":\""+u2.getPaystatus()+"\"")
				.append(",")
				.append("\"money\":\""+u2.getMoney()+"\"")
				.append(",")
				.append("\"score\":\""+u2.getScore()+"\"")
				.append(",")
				.append("\"discountnum\":\""+discountnum+"\"")
				.append(",")
				.append("\"backimg\":\""+u2.getBackimg()+"\"")
				.append(",")
				.append("\"starttime\":\""+u2.getStarttime()+"\"")
				.append(",")
				.append("\"nicesign\":\""+u2.getNicesign()+"\"")
				.append(",")
				.append("\"deadline\":\""+u2.getDeadline()+"\"")
				.append(",")
				.append("\"sign\":\""+u2.getSign()+"\"")
				
				 .append("}");
		        
	    	
	     	out.write(str.toString());
	    
	        }
		    
	       
		
		
	}

}
