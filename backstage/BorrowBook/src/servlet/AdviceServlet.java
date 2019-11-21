package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ReferSys.ReferDao;
import dao.BookDao;

import entity.Book;
import entity.BookToBooks;

/**
 * Servlet implementation class AdviceServlet
 */
@WebServlet("/AdviceServlet")
public class AdviceServlet extends HttpServlet {

	
	public AdviceServlet() {
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
	      
	      ReferDao rd=new ReferDao();
	      String bookid=request.getParameter("bookid");
 	     
  	    BookToBooks bb= rd.getBookToBooks(bookid);
  	    StringBuilder str1 =new StringBuilder();
	      
	       if(bb!=null)
  	      {
	    	   
	    	   
  	    	String books=bb.getBooks();
  	    	
  	    	
  	    	 if(books!=null)
  	    	 {
  	    		
  	    		String advice[]=books.split("@");
  	    		
  	    		for(int i=0;i<8;i++)
  	    		{
  	    			
  	    			
  	    			BookDao bd=new BookDao();
  	    			
  	    			if(bd.getBookbyid(advice[i])!=null)
						{
						Book b=bd.getBookbyid(advice[i]);
						

						str1.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
						.append(",")
						.append("\"bookimages\":\""+b.getBookimages() +"\"")
						.append(",")
						.append("\"booktitle\":\""+b.getBooktitle()+"\"")
						.append(",")
					   .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
						
						.append("}").append(",");
		       	      }//if
						
  	             }//for
  	    		
  	    	
  	    	}//if
  	   	String json1=str1.substring(0, str1.length()-1);
		  
		   out.write("["+json1+"]");
		  
  	    }//if
	     
	     
	}
	     
	

	
	public void init() throws ServletException {
		
	}

}
