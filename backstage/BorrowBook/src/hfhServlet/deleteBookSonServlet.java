package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hfhdao.BookSonDao;


@WebServlet(name="deleteBookSonServlet",urlPatterns="/deleteBookSonServlet")
public class deleteBookSonServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public deleteBookSonServlet() {
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
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; charset=UTF-8");
		String booksonid=request.getParameter("booksonid");
		BookSonDao dao=new BookSonDao();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(dao.deleteBoolSon(booksonid)==true)
		{
			
			out.println("1");
			
		}
		else
		{
			out.println("0");
			
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
