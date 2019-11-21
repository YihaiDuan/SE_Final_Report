package EleBookServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EleBookDao.EleBookDao;
import dao.BookDao;
import dao.UserDao;
import entity.Book;
import entity.MyEleBook;
import entity.User;


@WebServlet("/AddMyEle")
public class AddMyEle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddMyEle() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		      doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		
		response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
	       
		
		         String userid=request.getParameter("userid");
		         String bookid=request.getParameter("bookid");
		
		 	    EleBookDao ebd=new EleBookDao();
		
		         MyEleBook mm=new MyEleBook();
	        	 UserDao ud=new UserDao();
	        	 User u=ud.getUserbyid(userid);
	        	 BookDao bd=new BookDao();
	        	 Book b=bd.getBookbyid(bookid);
	        	 
	        	 
	        	 mm.setAddstatus(0);
	        	 mm.setBook(b);
	        	 mm.setUser(u);
	        	 
	        	 ebd.SaveMyEleBook(mm);
		
		
	}

}
