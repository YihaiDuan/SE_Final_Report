package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="searcBookhServlet",urlPatterns="/searcBookhServlet")
public class searcBookhServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public searcBookhServlet() {
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		String publishnumber=request.getParameter("publishnumber");
		
		request.getRequestDispatcher("index-search.jsp?publishnumber="+publishnumber).forward(request, response);
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
