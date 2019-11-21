package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import entity.Book;


@WebServlet("/ScanIsbnServlet")
public class ScanIsbnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ScanIsbnServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		  response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		   
		  String isbn=request.getParameter("isbn");
		System.out.println(isbn);
		  
		  BookDao bd=new BookDao();
		  
		  Book b=bd.getBookByisbn(isbn);
		  
		         
		  if(b!=null)
		  {
			  
			String bookid= b.getBookid();
			String categoryid= b.getCategory().getCategoryid();
			  
			  
	out.write("{"+"\"bookid\":"+"\""+bookid+"\","+"\"categoryid\":"+"\""+categoryid+"\""+"}");
			  
		  }
		  else
		  {
			  
			  out.write("failure");
		  }
		
		  
		  
		  
		
		
		
		
	}

}
