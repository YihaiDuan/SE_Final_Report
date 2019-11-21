package newServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Topic;
import Extenddao.Topicdao;
@WebServlet(name="ChangeTopicServlet",urlPatterns="/ChangeTopicServlet")
public class ChangeTopicServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeTopicServlet() {
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
	     int id = 0;
	     if(request.getParameter("id")!=null)
	     {
	      id =Integer.parseInt(request.getParameter("id"));
	    
	      Topicdao topicdao =new Topicdao();
	      Topic topic =topicdao.getTopic(id);
	      if(topic.getShowstatus()==1)
	      {
	    	 topic.setShowstatus(0);
	      }else{
	    	  
	    	  topic.setShowstatus(1);
	    	  
	      }
	      topicdao.changeTopic(topic);
	     }
		PrintWriter out = response.getWriter();
		out.println("1");
		
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
