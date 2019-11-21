package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
import entity.BookSon;
import hfhdao.BookDao;
import hfhdao.BookSonDao;
@WebServlet(name="addBookSonServlet",urlPatterns="/addBookSonServlet")
public class addBookSonServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public addBookSonServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; charset=UTF-8");  
		 String booksonid=request.getParameter("booksonid");
		 String bookid=request.getParameter("bookid");
		 BookDao bookdao=new BookDao();
		 Book book=bookdao.getIdBook(bookid);
		 BookSonDao booksondao=new BookSonDao();
		 BookSon b=booksondao.getBookSon(booksonid);
		
		 
		 
		 
		 BookSon bookson=new BookSon();
		 bookson.setBook(book);
		 bookson.setBooksonid(booksonid);
			PrintWriter out = response.getWriter();
		 if(b!=null||booksonid.equals(""))
		 {
			 out.print("0");
		 }
		 else
		 {
			  booksondao.addBookSon(bookson);
			 out.print("1"); 
		 }
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
