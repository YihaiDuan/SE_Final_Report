package hfhServlet;

import hfhdao.BookDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
@WebServlet(name="TopicBookSearchServlet",urlPatterns="/TopicBookSearchServlet")
public class TopicBookSearchServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TopicBookSearchServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String searchtype = request.getParameter("searchtype");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		BookDao bookdao = new BookDao();
		List<Book> list = null;
		if(searchtype.equals("书名"))
		{
			list = bookdao.getBooktitle(content);
			request.setAttribute("list",list);
			request.getRequestDispatcher("changespecialdetail.jsp?id="+id).forward(request, response);
		}else{
			Book book = bookdao.getpublishnumberBook(content);
			request.setAttribute("book",book);
			request.getRequestDispatcher("changespecialdetail.jsp?id="+id).forward(request,response);
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
