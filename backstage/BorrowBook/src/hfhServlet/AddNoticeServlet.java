package hfhServlet;

import hfhdao.NoticeDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Notice;
@WebServlet(name="AddNoticeServlet",urlPatterns="/AddNoticeServlet")
public class AddNoticeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddNoticeServlet() {
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
		response.setContentType("text/html,charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		
		String[] types = request.getParameterValues("type");
		String content = request.getParameter("content");
		
		
		
		Notice notice = new Notice();
		notice.setDate(dateString);
		if(types[0].equals("manage"))
		{
			 notice.setTypestatus(0);
		}else{
			
			 notice.setTypestatus(1);
		}
		notice.setContent(content);
		
	    NoticeDao noticedao = new NoticeDao();
		
		noticedao.addNotice(notice);
		
		request.getRequestDispatcher("noticemanage.jsp").forward(request, response);
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
