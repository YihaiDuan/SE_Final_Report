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
import entity.Book;
import util.JsonFormat;

/**
 * Servlet implementation class ShowBookNew
 */
@WebServlet("/ShowBookNew")
public class ShowBookNew extends HttpServlet {

	
	public ShowBookNew() {
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
	      
	      
		  PrintWriter out=response.getWriter() ;
		  
		  BookDao bd=new BookDao();
		  
		    List<Book>  blist=bd.getBookAll();
		    StringBuilder str=new StringBuilder();
		    
		    
		    if(blist!=null&&blist.size()>0)
		    {
		    
		    		
		    		for(int i=blist.size()-1;i>=0;i--)
			    	{
			    		
			    		

			    		Book b=blist.get(i);
			    		
			    		 String introduce=null;
			    		 
			    	      
			  	         if(b.getIntroduce()!=null)
			  	         {
			  	        	 
			  	        	
			  	        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
			  	         }
			  	         else
			  	         {
			  	        	 introduce="没有相关内容";
			  	        	 
			  	         }	 
			        	 
				        	 
			    		str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
						.append(",")
						.append("\"bookimages\":\""+b.getBookimages() +"\"")
						.append(",")
						.append("\"booktitle\":\""+b.getBooktitle()+"\"")
						.append(",")
					   .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
					   .append(",")
					   .append("\"borrownum\":\""+b.getBorrownum()+"\"")
					   .append(",")
					   .append("\"bookintroduce\":\""+introduce+"\"")
					   .append(",")
					   .append("\"author\":\""+b.getAuthor()+"\"")
					   .append(",")
					   .append("\"typename\":\""+b.getCategory().getName()+"\"")
						.append("}").append(",");
			    		
			    	
			    		
			    		
			    	}
		    		
		  
		
		    	   out.write("["+str.substring(0,str.length()-1)+"]");
		    	
		    }
		  
		  
		  
		
		
		
		

	
	}

	
	public void init() throws ServletException {
	
	}

}
