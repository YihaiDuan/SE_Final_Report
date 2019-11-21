package newServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Topic;
import entity.TopicBook;
import Extenddao.TopicBookDao;
import Extenddao.Topicdao;
@WebServlet(name="DeleteTopicBookServlet",urlPatterns="/DeleteTopicBookServlet")
public class DeleteTopicBookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteTopicBookServlet() {
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
		int id;
		if(request.getParameter("id")!=null){
		   id = Integer.parseInt(request.getParameter("id"));
		}
		int topicbookid = Integer.parseInt(request.getParameter("topicbookid"));
		//System.out.println(id+"=======");
		System.out.println(topicbookid+"===============");
		TopicBookDao topicbookdao = new TopicBookDao();
		TopicBook topicbook = topicbookdao.getIdTopicBook(topicbookid);
		topicbookdao.deleteTopicBook(topicbook);
		PrintWriter out = response.getWriter();
	    out.println(topicbookid);
	  /*  response.sendRedirect("changespecialdetail.jsp?id="+id);*/
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
