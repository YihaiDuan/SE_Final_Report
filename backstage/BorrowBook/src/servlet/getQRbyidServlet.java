package servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BorrowTableDao;
import dao.UserDao;
import entity.BorrowTable;
import entity.User;
import javax.servlet.annotation.WebServlet;


@WebServlet("/getQRbyidServlet")
public class getQRbyidServlet extends HttpServlet {

	
	public getQRbyidServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	               doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		     response.setContentType("text/html;charset=utf-8");
	         request.setCharacterEncoding("utf-8");
	       
	         PrintWriter out=response.getWriter();
	         
	         
	         String userid=request.getParameter("userid");
	         String id=request.getParameter("bookid");
	        // String count=request.getParameter("count");
	         
	        
	         
	         System.out.println("-----------------------"+userid);
	         System.out.println("-----------------------"+id);
	        // System.out.println("-----------------------"+count);
	         
	         
	         String borrowid[]=id.split(",");
	         
	         
	         BorrowTableDao bto=new BorrowTableDao();
	         StringBuilder str=new StringBuilder();
	         String json=null;
	         
	         if(borrowid!=null&&borrowid.length>0)
	         {
	        	 
	        	 for(int i=0;i<borrowid.length;i++)
	        	 {
	        		 
	        		 
	        	BorrowTable bt=bto.getBorrowTablebyid(Integer.parseInt(borrowid[i]));
	        		 if(bt!=null)
	        		 {
	        			 str.append("{").append("\"id\":\""+bt.getId()+"\"")
	     				.append(",")
	     				.append("\"bookid\":\""+bt.getBookson().getBook().getBookid() +"\"")  
	     				.append(",")
	     				.append("\"booktitle\":\""+bt.getBookson().getBook().getBooktitle()+"\"")
	     				.append(",")
	     				.append("\"bookimages\":\""+bt.getBookson().getBook().getBookimages()+"\"")
	     				.append(",")
	     				.append("\"author\":\""+bt.getBookson().getBook().getAuthor()+"\"")
	     				.append(",")
	     				.append("\"publish\":\""+bt.getBookson().getBook().getPublish()+"\"")
	     				.append(",")
	     			   .append("\"borrowdate\":\""+bt.getBorrowdate()+"\"")
	     			   .append(",")
	     			   .append("\"booksonid\":\""+bt.getBookson().getBooksonid()+"\"")
	     			   .append(",")
	     			   .append("\"isbn\":\""+bt.getBookson().getBook().getPublishnumber()+"\"")
	     			  .append(",")
	     			   .append("\"cash\":\""+bt.getBookson().getBook().getCash()+"\"")
	     				.append("}").append(",");	 
	        			 
	        		 }//if
	        	
	        	
	        		 
	        	 }//for
	        	 
	        	 
	        	   UserDao ud=new UserDao();
	    	       User u=ud.getUserbyid(userid);
	    	        
	    	        
	json="fb_borrow"+"{"+"\"userid\":"+"\""+userid+"\","+"\"nickname\":"+"\""+u.getNickname()+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}";
	    	        
	               out.write(json);
	    	    
	    	       }//if  
	    	    
	         else
  	       {
  	    	   
  	    	   
  	    	   out.write("610");
  	       }
	      
	      
	    }

	
	public void init() throws ServletException {
	
	}

}
