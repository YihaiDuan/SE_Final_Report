package hfhServlet;

import hfhdao.BookDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
@WebServlet(name="AddTopicBokkSearchServlet",urlPatterns="/AddTopicBokkSearchServlet")
public class AddTopicBokkSearchServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddTopicBokkSearchServlet() {
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
		System.out.println("我来了");
		String searchtype = request.getParameter("searchtype");
		String content = request.getParameter("content");
		System.out.println(content);
		BookDao bookdao = new BookDao();
		List<Book> list = null;
		PrintWriter out = response.getWriter();
		if(searchtype.equals("书名"))
		{
			list = bookdao.getBooktitle(content);
			StringBuilder str =new StringBuilder();
			str.append("[");
			for(int i=0;i<list.size();i++)
			{
				Book book = list.get(i);
				if(i != list.size()-1)
				{
				str.append("{");
				str.append("bookid:\""+book.getBookid()+"\",");
				str.append("booktitle:\""+book.getBooktitle()+"\",");
				str.append("author:\""+book.getAuthor()+"\",");
				str.append("publish:\""+book.getPublish()+"\",");
				str.append("booknum:\""+book.getBooknum());
				str.append("\"},");
				}
			}
			str.append("]");
			String str1 = str.toString();
			String str2 =URLEncoder.encode(str1, "utf-8");
			System.out.println(str2);
			out.println(str2);
		}else{
			Book book = bookdao.getpublishnumberBook(content);
			StringBuilder str = new StringBuilder();
			str.append("{");
			str.append("bookid:\""+book.getBookid()+"\",");
			str.append("booktitle:\""+book.getBooktitle()+"\",");
			str.append("author:\""+book.getAuthor()+"\",");
			str.append("publish:\""+book.getPublish()+"\",");
			str.append("booknum:\""+book.getBooknum());
			str.append("\"}");
			String str1 = str.toString();
			String str2 =URLEncoder.encode(str1, "utf-8");
			System.out.println(str2);
			out.println(str2);
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
