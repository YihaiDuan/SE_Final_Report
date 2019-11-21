package Appservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.BookSonDao;
import dao.CategoryDao;
import entity.Book;
import entity.BookSon;
import entity.Category;
import util.ChineseSpelling;
/**
 * 
 * @author hjm
 * @todo   app端图书扫码上传
 * 
 */
@WebServlet("/ScanUpBook")
public class ScanUpBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ScanUpBook() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
				doPost(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		       
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter();
		   
		          String bookid=request.getParameter("bookId");    
				  String booktitle=request.getParameter("bookName");
				  String author=request.getParameter("author");
				  String publish=request.getParameter("publish");
				  String publishnumber=request.getParameter("publishNum");
				  String bookimages=request.getParameter("image");
				  String introduce=request.getParameter("introduce");
				  String cash=request.getParameter("cash");
				  String review=request.getParameter("comment");
				  String guidreading=request.getParameter("guide");
		          String list=request.getParameter("list");
		          String categoryid=request.getParameter("type");		  
		          String number=request.getParameter("number");
		          String position="五层504";
		          
		          
		          
		          
		          System.out.println("bookId"+bookid);
		          System.out.println("categoryid"+categoryid);
		          System.out.println("publishnumber"+publishnumber);
		          System.out.println("image"+bookimages);
		        
		        		  
		          
		          
		          CategoryDao cd=new CategoryDao();
		          Category c=cd.getCategoryByid(categoryid);

		              
		           BookDao bd=new BookDao();
		             Book b=new Book();
		      
		           
		             if(bd.getBooleanBookbyBookid(bookid))
			          {
			        	  
		            	 out.write("610");
			        	  
			          }
		             else
		             {
		              
		              b.setCash(Double.valueOf(cash));
		              b.setBookid(bookid);
		  			  b.setBooktitle(booktitle);
		  			
		  		    ChineseSpelling finder = ChineseSpelling.getInstance();        
		  			finder.setResource(booktitle);  
		  	            
		  			b.setChinesespelling(finder.getSpelling());
		  			b.setBookimages(bookimages);
		  		    b.setAuthor(author);
		  			b.setIntroduce(introduce);
		  			b.setList(list);
		  			b.setPublishnumber(publishnumber);
		  			b.setPublish(publish);
		  			b.setReview(review);
		  			b.setAuthor(author);
		  			b.setGuidreading(guidreading);
		  			b.setCategory(c);
		  			b.setPosition(position);
		  			
		  			
		  			if(bd.SaveBookData(b))
		  			{
		  				
		  				out.write("609");
		  			}
		  			else
		  			{
		  				out.write("610");
		  				
		  			}
		  			
		  			
		  			
		  			
		           String bookson[]=new String[Integer.parseInt(number)];
		           
		           
		           for(int m=0;m<Integer.parseInt(number);m++)
		           {
		        	   
		        	   bookson[m]=String.valueOf(m+1);
		        	   
		        	   
		           }
		           
		  			
		  			for(int j=0;j<bookson.length;j++)
		  			{
		  				
		  				
		  			String booksonid=bookid+bookson[j];
		  			
		  			 BookSonDao bso=new BookSonDao();
		  			 BookSon bs=new  BookSon();
		  		    
		  		     BookDao bdo=new BookDao();
		  		     Book b1=bdo.getBookbyid(bookid);
		  		     
		  		        bs.setBook(b1);
		  		        bs.setBooksonid(booksonid);
		  		        bso.SaveBookSonData(bs);
		  		        
		  			}
		             }
		
	}

}
