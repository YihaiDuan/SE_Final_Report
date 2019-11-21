package hfhServlet;

import hfhdao.BookDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.TopicBookDao;
import entity.Book;
import entity.TopicBook;
@WebServlet(name="AddTopicBookManageServlet",urlPatterns="/AddTopicBookManageServlet")
public class AddTopicBookManageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddTopicBookManageServlet() {
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
		String bookid = request.getParameter("bookid");
		BookDao bookdao = new BookDao();
		Book book = bookdao.getIdBook(bookid);
		TopicBook topicbook =new TopicBook();
		topicbook.setBook(book);
		TopicBookDao topicbookdao =new TopicBookDao();
		
		topicbook = topicbookdao.SaveTopicBook(topicbook);
		StringBuilder str = new StringBuilder();
		str.append("{");
		str.append("bookid:\""+book.getBookid()+"\",");
		str.append("booktitle:\""+book.getBooktitle()+"\",");
		str.append("author:\""+book.getAuthor()+"\",");
		str.append("publish:\""+book.getPublish()+"\",");
		str.append("boknum:\""+book.getBooknum()+"\",");
		str.append("topicbookid:\""+topicbook.getId());
		str.append("\"}");
		
		PrintWriter out = response.getWriter();
		String str1 = str.toString();
		String str2 =URLEncoder.encode(str1, "utf-8");
		out.println(str2);
		
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
