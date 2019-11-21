package applyservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplyNearDao;
import dao.BorrowTableDao;
import dao.UserDao;
import entity.ApplyNear;
import entity.BorrowTable;
import entity.User;


@WebServlet("/UpApply")
public class UpApply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UpApply() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		     doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
		  PrintWriter out=response.getWriter();
		  
		  
		  String userid=request.getParameter("userid");
		  String otherid=request.getParameter("otherid");
		  String borrowid=request.getParameter("id");
		  
		  
		  System.out.println(userid);
		  System.out.println(otherid);
		  System.out.println(borrowid);
		  
		  Date date=new Date();
		  SimpleDateFormat  sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		  String time=sdf.format(date);
		  
		  
		  ApplyNearDao and=new ApplyNearDao();
		  
		  ApplyNear a=new ApplyNear();
		  UserDao ud=new UserDao();
		  User u=ud.getUserbyid(userid);
		  BorrowTableDao btd=new  BorrowTableDao();
		  BorrowTable bt=btd.getBorrowbyId(Integer.parseInt(borrowid));
		  
		  
		  
		  
		  a.setUser(u);
		  a.setOtherid(otherid);
		  a.setBorrowtable(bt);
		  a.setDate(time);
		  
		  
		  and.SaveBookData(a);
		  
		  
		 
			  
		     BorrowTable b=btd.getBorrowbyId(Integer.parseInt(borrowid));
		     StringBuilder str=new StringBuilder();
		     
		     if(b!=null)
		     {
		    	 
		    	 int  day=(int) ((Date.parse(b.getDeadline())-Date.parse(time))/86400000);
		         System.out.println("输出剩下的天数"+day);
		    	 
		        
		         
		      boolean applybol= and.ApplyBoolean(userid, Integer.parseInt(borrowid));
		         
		    	 str.append("{").append("\"borrowid\":\""+b.getId()+"\"")

		    	 .append(",")
		    	 .append("\"booktitle\":\""+b.getBookson().getBook().getBooktitle()+"\"")
		    	 .append(",")
		    	 .append("\"bookimages\":\""+b.getBookson().getBook().getBookimages()+"\"")
		    	 .append(",")
		    	 .append("\"bookid\":\""+b.getBookson().getBook().getBookid()+"\"")
		    	 .append(",")
		    	 .append("\"booksonid\":\""+b.getBookson().getBooksonid()+"\"")
		    	 .append(",")
		    	 .append("\"author\":\""+b.getBookson().getBook().getAuthor()+"\"")
		    	 .append(",")
		    	 .append("\"deadline\":\""+b.getDeadline()+"\"")
		    	 .append(",")
		    	 .append("\"userid\":\""+b.getUser().getUserid()+"\"")
		    	 .append(",")
		    	 .append("\"userimages\":\""+b.getUser().getUserimages()+"\"")
		    	 .append(",")
		    	 .append("\"nickname\":\""+b.getUser().getNickname()+"\"")
		    	 .append(",")
		    	 .append("\"sex\":\""+b.getUser().getSex()+"\"")
		    	 .append(",")
		    	 .append("\"personQR\":\""+b.getUser().getPersonQR()+"\"")
		    	 .append(",")
		    	 .append("\"type\":\""+b.getBookson().getBook().getCategory().getCategoryid()+"\"")
		    	 .append(",")
		    	 .append("\"day\":\""+day+"\"")
		    	 .append(",")
		    	 .append("\"applybol\":\""+applybol+"\"")
		    	 
		    	 .append("}");  
		    	 
		    	 
		    	 out.write(str.toString());
		    	 
		     }
		
	}

}
