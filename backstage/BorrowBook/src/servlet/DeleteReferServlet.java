package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.ReferTableDao;
import entity.Book;
import entity.ReferTable;
import util.JsonFormat;


@WebServlet("/DeleteReferServlet")
public class DeleteReferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteReferServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    doPost(request,response);
		          
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		 response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
		
	       String referid=request.getParameter("referid");
	       String userid=request.getParameter("userid");
	       
	       
	      ReferTableDao   rtd=new ReferTableDao();
	      
	      
	      
	      //删除回复
	      rtd.DeleteRefer(Integer.parseInt(referid));
	      
	      
	      
	       
	       List<ReferTable> rlist=rtd.getReferData(userid);
	       StringBuilder  str=new StringBuilder();
	       
	       
	       if(rlist!=null&rlist.size()>0)
	       {
	    	   
	    	   for(int i=rlist.size()-1;i>=0;i--)
	    	   {
	    		   
	    		  ReferTable rt=rlist.get(i);
	    		 BookDao bd=new BookDao();
	    		 Book b=bd.getBookbyid(rt.getBook().getBookid());
	    		 
	    		 
	    		 String introduce=null;
	    				 
	    		 if(b.getIntroduce()!=null)
	    		 {
	    			JsonFormat js=new JsonFormat();
	    			 
	    			introduce=js.stringToJson(b.getIntroduce());
	    			 
	    		 }
	    		 else
	    		 {
	    			 introduce=b.getIntroduce();
	    			 
	    		 }
	    		 
	    		 
	    		 
	    		 if(b!=null)
	    		 {
	    		   
	    		  str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
				  .append(",")
					.append("\"bookimages\":\""+b.getBookimages() +"\"")
					.append(",")
					.append("\"booktitle\":\""+b.getBooktitle()+"\"")
					.append(",")
				   .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
				   .append(",")
				   .append("\"booknum\":\""+b.getBooknum()+"\"")
				   .append(",")
				   .append("\"bookintroduce\":\""+introduce+"\"")
				   .append(",")
				   .append("\"status\":\""+rt.getStatus()+"\"")
				   .append(",")
				   .append("\"id\":\""+rt.getId()+"\"")
				   .append(",")
				   .append("\"date\":\""+rt.getDate()+"\"")
					
					.append("}").append(",");
	    		   
	    		 }
	    		   
	    	   }
	    	   
	    	   out.write("["+str.substring(0,str.length()-1)+"]");
	    	
	       }
	       
	
	}

}
