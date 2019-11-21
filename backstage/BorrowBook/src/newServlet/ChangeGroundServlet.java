package newServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.GroupMore;
import Extenddao.GroupDao;
@WebServlet(name="ChangeGroundServlet",urlPatterns="/ChangeGroundServlet")
public class ChangeGroundServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeGroundServlet() {
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
		
		int id = Integer.parseInt(request.getParameter("id"));
		String createdate = request.getParameter("createdate");
		String deadline = request.getParameter("deadline");
        String maxnum = request.getParameter("maxnum");
        String groupnum = request.getParameter("groupnum");
		
		GroupDao groupdao = new GroupDao();
		GroupMore groupmore = groupdao.getGroupMoreById(id);
		groupmore.setCreatedate(createdate);
		groupmore.setDeadline(deadline);
		groupmore.setMaxnum(maxnum);
		groupmore.setGroupnum(groupnum);
		
		groupdao.updateGroupMore(groupmore);
		
		request.getRequestDispatcher("grounpmanage.jsp").forward(request,response);
	
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
