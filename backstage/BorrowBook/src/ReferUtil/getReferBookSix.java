package ReferUtil;

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
import dao.BorrowTableDao;
import entity.Book;
import entity.BookToBooks;
import entity.BorrowTable;
import entity.OnlineOrder;
import hfhdao.OnlineOrderDao;


@WebServlet("/getReferBookSix")
public class getReferBookSix extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public getReferBookSix() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		   response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   String borrowid=request.getParameter("borrowid");
		   String action=request.getParameter("action");
		   String orderid=request.getParameter("orderid");
		   
		   System.out.println("///////////////"+borrowid);
		   ReferDao rd=new ReferDao();
		   
		   if(action.equals("borrow"))
		   {
		   BorrowTableDao btd=new BorrowTableDao();
		  BorrowTable bt=btd.getBorrowTablebyid(Integer.parseInt(borrowid));
		   
		  String bookid=bt.getBookson().getBook().getBookid();
		  
		
		  BookToBooks bb= rd.getBookToBooks(bookid);
	  
		   StringBuilder str=new StringBuilder(); 
		   
		   if(bb!=null)
	  	      {
		    	   
		    	   
	  	    	String books=bb.getBooks();
	  	    	
	  	    	
	  	    	 if(books!=null)
	  	    	 {
	  	    		
	  	    		String advice[]=books.split("@");
	  	    		
	  	    		for(int i=advice.length-1;i>=4;i--)
	  	    		{
	  	    			
	  	    			
	  	    			BookDao bd=new BookDao();
	  	    			
	  	    			if(bd.getBookbyid(advice[i])!=null)
							{
							Book b=bd.getBookbyid(advice[i]);
							

							str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
							.append(",")
							.append("\"typeid\":\""+b.getCategory().getCategoryid() +"\"")
							.append(",")
							.append("\"booktitle\":\""+b.getBooktitle()+"\"")
							.append(",")
							.append("\"cash\":\""+b.getCash()+"\"")
							.append(",")
							.append("\"bookimages\":\""+b.getBookimages()+"\"")
							 .append("}").append(",");
			       	      }//if
							
	  	             }//for
	  	    		
	  	    	
	  	    	}//if
	  	   	String json1=str.substring(0, str.length()-1);
			  
			   out.write("["+json1+"]");
			  
	  	    }//if
		   
		 }
		   else
		   {
		
			   
			   OnlineOrderDao od=new OnlineOrderDao();
			   
			   OnlineOrder oo=od.getOnlineOrder(Integer.parseInt(orderid));
			   
			   BookToBooks bb= rd.getBookToBooks(oo.getBookson().getBook().getBookid());
		  
			   StringBuilder str=new StringBuilder(); 
			   
			   if(bb!=null)
		  	      {
			    	   
			    	   
		  	    	String books=bb.getBooks();
		  	    	
		  	    	
		  	    	 if(books!=null)
		  	    	 {
		  	    		
		  	    		String advice[]=books.split("@");
		  	    		
		  	    		for(int i=advice.length-1;i>=4;i--)
		  	    		{
		  	    			
		  	    			
		  	    			BookDao bd=new BookDao();
		  	    			
		  	    			if(bd.getBookbyid(advice[i])!=null)
								{
								Book b=bd.getBookbyid(advice[i]);
								

								str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
								.append(",")
								.append("\"typeid\":\""+b.getCategory().getCategoryid() +"\"")
								.append(",")
								.append("\"booktitle\":\""+b.getBooktitle()+"\"")
								.append(",")
								.append("\"cash\":\""+b.getCash()+"\"")
								.append(",")
								.append("\"bookimages\":\""+b.getBookimages()+"\"")
								 .append("}").append(",");
				       	      }//if
								
		  	             }//for
		  	    		
		  	    	
		  	    	}//if
		  	   	String json1=str.substring(0, str.length()-1);
				  
				   out.write("["+json1+"]");
				  
		  	    }//if
			   
		   }
	}

}
