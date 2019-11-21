package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
import hfhdao.BookDao;
@WebServlet(name="publishnumberPageServlet",urlPatterns="/publishnumberPageServlet")
public class publishnumberPageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public publishnumberPageServlet() {
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

	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String content=request.getParameter("content");
		BookDao bookdao=new BookDao();
		Book book=new Book();
		book=bookdao.getpublishnumberBook(content);
		request.setAttribute("book", book);
		
		request.getRequestDispatcher("/retrieval-search-isbn.jsp").forward(request, response);
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
