package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hfhdao.BookDao;
import entity.Book;
import entity.Topic;
import entity.TopicBook;
import Extenddao.TopicBookDao;
import Extenddao.Topicdao;
@WebServlet(name="AddTopicBookServlet",urlPatterns="/AddTopicBookServlet")
public class AddTopicBookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddTopicBookServlet() {
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
		String bookid =request.getParameter("bookid");
		System.out.println(id+"===========");
		BookDao bookdao = new BookDao();
		List<Book> list = bookdao.getTopicBook(id);
		int a = 0;
		for(int i=0;i<list.size();i++)
		{
			Book book = list.get(i);
			if(book.getBookid().equals(bookid))
			{
				PrintWriter out = response.getWriter();
			    out.println(1);
			    a = 1;
			    break;
			}
		}
		if(a == 0)
		{
		Topicdao topicdao = new Topicdao();
		TopicBookDao topicbookdao = new TopicBookDao();
		//TopicBookDao topicbookdao1 = new TopicBookDao();
		Topic topic = topicdao.getTopic1(id);
	//	System.out.println("++++++++++++++++++");
	//	TopicBook topicbook = topicbookdao1.getIdTopicBook(bookid);
	//	 System.out.println("======================"+topicbook);
		//topicbook.setTopic(topic);
		
		Book book =bookdao.getIdBook(bookid);
		TopicBook topicbook = new TopicBook();
		topicbook.setBook(book);
		topicbook.setTopic(topic);
		topicbook = topicbookdao.SaveTopicBook(topicbook);
        int t=topicbook.getId();
		//int t=topicbook1.getId();
	    System.out.println(t+"ttttttttttttttttt");
		StringBuilder str = new StringBuilder();
		str.append("{");
		str.append("bookid:"+book.getBookid()+",");
		str.append("booktitle:"+book.getBooktitle()+",");
		str.append("author:"+book.getAuthor()+",");
		str.append("boknum:"+book.getBooknum()+",");
		str.append("topicbookid:"+t);
		str.append("}");
		System.out.println(str+"=================");
		PrintWriter out = response.getWriter();
	    out.println(str);
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
