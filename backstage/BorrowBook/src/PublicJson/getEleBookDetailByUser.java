package PublicJson;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import entity.Book;


@WebServlet("/getEleBookDetailByUser")
public class getEleBookDetailByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public getEleBookDetailByUser() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	         	doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		   String userid= request.getParameter("userid");
		    String bookid=request.getParameter("bookid"); 
		    
		    
		    
		    BookDao bd=new BookDao();
	        
	        Book b=bd.getBookbyid(bookid);
	       
	         
	         
	         if(b!=null)
	         {
	        	 
	       
	      EleBookDetailJson bdj=new EleBookDetailJson();
	        	 
	        	 
	       out.write(bdj.EleBookDetailJson(request, response, b, userid)); 
	        	
	     
	        	
	 	     }
	
	
	}

}
