package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;


@WebServlet("/WXlogin")
public class WXlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public WXlogin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
	       
	       
	       String userid=request.getParameter("userid");
	       String password=request.getParameter("password");
	       String nickname=request.getParameter("nickname");
	       String e_mail=request.getParameter("e_mail");
	       String phone=request.getParameter("phone");
	      String  userimages=request.getParameter("userimages");
	       String sex=request.getParameter("sex");
	       String openid=request.getParameter("openid");
	       String nicesign=request.getParameter("nicesign");
	      
	       System.out.println("用户名:"+userid);
	       System.out.println("头像:"+userimages);
	    
	       
	    
	       if(e_mail.equals("wu"))
	       {
	    	   
	    	   e_mail="未填";
	    	   
	    	   
	       }
	       if(phone.equals("wu"))
	       {
	    	   
	    	   phone="未填";
	    	   
	    	   
	       }
	       if(nicesign.equals("wu"))
	       {
	    	   
	    	   nicesign="未填";
	    	   
	    	   
	       }
	       System.out.println(e_mail);
	       System.out.println(phone);
	       
	       
	       
	       UserDao ud=new UserDao();
	      
	        UserDao ud1=new UserDao();
	        User u2=ud1.getUserbyid(userid);
	        
	      
	        //如果用户已经存在
	        if(u2!=null)
	        {
	        if(u2.getOpenid().equals(userid))
	        {
	        	
	        	System.out.println("我来了");
	        	
		           request.getSession().setAttribute("user",u2);
		  
		          StringBuilder str=new StringBuilder();
				   
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
					.append("\"backimg\":\""+u2.getBackimg()+"\"")
					.append(",")
					.append("\"starttime\":\""+u2.getStarttime()+"\"")
					
					.append(",")
					.append("\"nicesign\":\""+u2.getNicesign()+"\"")
					
					 .append("}");
			        
		       	
		        	out.write(str.toString());
	        	
	        	
	        	
	        }
	        
	        }
	        //如果用户不存在，添加用户，再获取用户
	        else
	        {
	        	  Date date=new Date();
	   	       SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
	   	       String curdate=sdf.format(date);
	        	
	         User u=new User();
	        
	        u.setUserid(userid);
	        u.setNickname(nickname);
	        u.setE_mail(e_mail);
	        u.setPassword(password);
	        u.setPhone(phone);
	        u.setSex(sex);
	        u.setOpenid(openid);
	        u.setP(6);
	        u.setUserimages(userimages);
	        u.setBorrowwarnstatus(1);
	        u.setMoney("0.0");
	        u.setScore(0);
	        u.setNicesign(nicesign);
	       u.setStarttime(curdate);
	        
	        ud.SaveUserData(u);
	        
	        
	        
	        
	        User u3=ud.getUserbyid(userid);
	           request.getSession().setAttribute("user",u3);
	  
	           
	           
	           
	           StringBuilder str=new StringBuilder();
			   
		        str.append("{").append("\"userid\":\""+u3.getUserid()+"\"")
		        .append(",")
				.append("\"nickname\":\""+u3.getNickname() +"\"")
				
				.append(",")
				.append("\"userimages\":\""+u3.getUserimages() +"\"")
				
				.append(",")
				.append("\"sex\":\""+u3.getSex()+"\"")
				
				
				
				.append(",")
				.append("\"e_mail\":\""+u3.getE_mail()+"\"")
				
				.append(",")
				.append("\"phone\":\""+u3.getPhone()+"\"")
				
				.append(",")
				.append("\"p\":\""+u3.getP()+"\"")
				
				.append(",")
				.append("\"referstatus\":\""+u3.getReferstatus()+"\"")
				
				.append(",")
				.append("\"borrowwarnstatus\":\""+u3.getBorrowwarnstatus()+"\"")
				.append(",")
				.append("\"personQR\":\""+u3.getPersonQR()+"\"")
				.append(",")
				.append("\"latitude\":\""+u3.getLatitude()+"\"")
				.append(",")
				.append("\"longitude\":\""+u3.getLongitude()+"\"")
				.append(",")
				.append("\"backimg\":\""+u3.getBackimg()+"\"")
				.append(",")
				.append("\"starttime\":\""+u3.getStarttime()+"\"")
				
				
				 .append("}");
		        
	       	
	        	out.write(str.toString());  
	        
	        }
	       
	       
	       
	}

}
