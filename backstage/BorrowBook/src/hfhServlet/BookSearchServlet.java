package hfhServlet;

import hfhdao.BookDao;
import hfhdao.BooktitlePageDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
@WebServlet(name="BookSearchServlet",urlPatterns="/BookSearchServlet")
public class BookSearchServlet extends HttpServlet {
	
	private int getPc(HttpServletRequest request)
	{
		
		int pc=1;  //��ʼ�ǵ�һҳ
		String parm=request.getParameter("pc");//�������ҳ
		if(parm!=null&&!parm.trim().isEmpty())
		{
			
			pc=Integer.parseInt(parm);
			
		}
				return pc;
	
	}
	
	
	
	
	
	private String getUrl(HttpServletRequest request)
	{
		
		String url=request.getRequestURI()+"?"+request.getQueryString();
		
		int index=url.lastIndexOf("&pc=");
		if(index!=-1)
		{
			url=url.substring(0,index);
		}
		
				return url;
	
	}
	
	/**
	 * Constructor of the object.
	 */
	public BookSearchServlet() {
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
		String searchtype = request.getParameter("searchtype");
	    String content = request.getParameter("content");
	  
		if(searchtype.equals("书名")){
			int pc=getPc(request);
			String url=getUrl(request);
			entity.Page<Book> pageBean = null;
		  BooktitlePageDao bp = new BooktitlePageDao();
		  pageBean = bp.findByPage(pc, content);
		 System.out.println(url);
		  pageBean.setUrl(url);
		  request.setAttribute("pb", pageBean);
		  request.setAttribute("searchtype","书名");
		  request.setAttribute("content",content);
		
		 // response.sendRedirect("BookSearchTitle.jsp");
		 request.getRequestDispatcher("BookSearchTitle.jsp?searchtype=书名&content="+content).forward(request, response);
		}else{
		  BookDao bookdao = new BookDao();
		  Book book = bookdao.getpublishnumberBook(content);
		  request.setAttribute("book",book);
		  request.setAttribute("searchtype","ISBN");
		  request.setAttribute("content",content);
		  request.getRequestDispatcher("BookSearchISBN.jsp?Bookid="+content).forward(request, response);
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
