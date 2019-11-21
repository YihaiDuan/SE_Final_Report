package hfhServlet;

import hfhdao.BookDao;
import hfhdao.BookSonDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
import entity.BookSon;
@WebServlet(name="ModifyBookSonServlet",urlPatterns="/ModifyBookSonServlet")
public class ModifyBookSonServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ModifyBookSonServlet() {
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
		String bookid = request.getParameter("bookid");
		String booksonid = request.getParameter("booksonid");
		BookDao bookdao = new BookDao();
		Book book = bookdao.getIdBook(bookid);
		BookSon bookson = new BookSon();
		bookson.setBooksonid(booksonid);
		bookson.setBook(book);
		BookSonDao booksondao = new BookSonDao();
		booksondao.addBookSon(bookson);
		String booktitle = book.getBooktitle();
		String author = book.getAuthor();
		String publish = book.getPublish();
		String publishnumber = book.getPublishnumber();
		StringBuilder str = new StringBuilder();
		str.append("{");
	    str.append("\"bookid\":"+bookid+",");
	    str.append("\"booktitle\":"+booktitle+",");
	    str.append("\"author\":"+author+",");
	    str.append("\"publish\":"+publish+",");
	    str.append("\"publishnumber\":"+publishnumber+",");
	    str.append("\"booksonid\":"+booksonid);
	    str.append("}");
		PrintWriter out = response.getWriter();
		out.print(str);
		
		
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
