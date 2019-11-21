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
import entity.User;


@WebServlet("/ChangeInfoServlet")
public class ChangeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ChangeInfoServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		    doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
		 
		
	       String action=request.getParameter("action");
	       String userid=request.getParameter("userid");
	       UserDao ud=new UserDao();
	       User u=new User();
	       
	       if(action.equals("nickname"))
	       {
	    	   
	    	   String nickname=request.getParameter("nickname");
	    	  
	       
	      
	         
	           u.setUserid(userid);
	           u.setNickname(nickname);
	           
	           ud.UpdateNickname(u);
	          
	       }
	       else if(action.equals("e_mail"))
	       {
	    	   
	    	   String e_mail=request.getParameter("e_mail");
		    	  
		       System.out.println(e_mail);
	 	      
		         
	           u.setUserid(userid);
	           u.setE_mail(e_mail);
	           
	           ud.UpdateE_mail(u);
	    	   
	    	   
	    	   
	       }
	       else if(action.equals("sex"))
	       {
	    	   
	    	   String sex=request.getParameter("sex");
		    	  
		       
		 	      
		         
	           u.setUserid(userid);
	           u.setSex(sex);
	           
	           ud.UpdateSex(u);
	    	   
	       }
	       else if(action.equals("phone"))
	       {
	    	   
	    	   String phone=request.getParameter("phone");
		    	  
		       System.out.println(phone);
	 	      
		         
	           u.setUserid(userid);
	           u.setPhone(phone);
	           
	           ud.UpdatePhone(u);
	    	   
	       }
	       else if(action.equals("sign"))
	       {
	    	   
	    	   String sign=request.getParameter("sign");
		    	  
		       System.out.println(sign);
	 	      
		         
	           u.setUserid(userid);
	           u.setNicesign(sign);
	           
	           ud.UpdateNickSign(u);;
	    	   
	       }
	     
		    
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
					
					 .append("}");
		       
	     	out.write(str.toString());
	        
	        }
		    
	       
	       
	      
		  
		  
	}

}
