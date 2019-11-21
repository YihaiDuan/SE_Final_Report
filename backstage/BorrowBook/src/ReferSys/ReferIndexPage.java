package ReferSys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import entity.Book;
import entity.UserToBooks;
import util.JsonFormat;


@WebServlet("/ReferIndexPage")
public class ReferIndexPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ReferIndexPage() {
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
		   
		   
		   ReferDao rd=new ReferDao();
		   
		   UserToBooks utb=rd.getUserToBooks(userid);
		     
		     String pc=request.getParameter("referpagenum");
		   System.out.println(pc);
	   System.out.println(userid);
		   
		    int  start=(Integer.parseInt(pc)-1)*3;
		    int end=Integer.parseInt(pc)*3;
		    
		     
		      String b[]=utb.getBooks().split("@");
		     
		      
		      StringBuilder str=new StringBuilder();
		      
		      for(int i=start;i<end;i++)
		      {
		    	  
		    	  BookDao bd=new BookDao();
		    	  Book bb=bd.getBookbyid(b[i]);
		    	  
		    	   String introduce=null;
	    		   
	    		    if(bb.getIntroduce()!=null)
		  	         {
		  	        	 
		  	        	
		  	        	 introduce=JsonFormat.stringToJson(bb.getIntroduce());
		  	         }
		  	         else
		  	         {
		  	        	 introduce="没有相关内容";
		  	        	 
		  	         }	 
		  	         
		            str.append("{").append("\"bookid\":\""+bb.getBookid()+"\"")
					.append(",")
					.append("\"bookimages\":\""+bb.getBookimages() +"\"")
					.append(",")
					.append("\"booktitle\":\""+bb.getBooktitle()+"\"")
					.append(",")
				   .append("\"category_id\":\""+bb.getCategory().getCategoryid()+"\"")
				   .append(",")
				   .append("\"borrownum\":\""+bb.getBorrownum()+"\"")
				   .append(",")
				   .append("\"author\":\""+bb.getAuthor()+"\"")
				   .append(",")
				   .append("\"typename\":\""+bb.getCategory().getName()+"\"")
				   .append(",")
				   .append("\"bookintroduce\":\""+introduce+"\"")
				   .append(",")
				   .append("\"oldprice\":\""+bb.getOldprice()+"\"")
				   .append(",")
				   .append("\"newprice\":\""+bb.getNewprice()+"\"")
					.append("}").append(",");
		    		   
		    	  
		    	  
		    	  
		      }
		      
		    out.write("{"+"\"maxsize\":"+"\""+b.length+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");
	}

}
