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

/**
 * Servlet implementation class UpBookNum
 */
@WebServlet("/UpBookNum")
public class UpBookNum extends HttpServlet {

	
	public UpBookNum() {
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
	       
	       String bookid=request.getParameter("bookid");
	       
	       BookDao bd=new BookDao();
	       
	        int current=bd.getBooknum(bookid);
	       
	       Book b=new Book();
	       b.setBookid(bookid);
	       b.setBooknum(current+1);
	       
	       bd.UpdateBooknum(b);
	       out.write("人气值上升成功");
		
	}


	public void init() throws ServletException {
	
	}

}
