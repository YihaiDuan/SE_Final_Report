package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hfhdao.BookDao;


@WebServlet(name="deleteBookServlet",urlPatterns="/deleteBookServlet")
public class deleteBookServlet extends HttpServlet {

	
	public deleteBookServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		   doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");  
		 String Bookid=request.getParameter("Bookid");
		 int pc=1;
		 if(request.getParameter("pc")!=null)
		 {
			 pc=Integer.parseInt(request.getParameter("pc"));
		 }
		 String booktypeid="0";
		 String categoryid="0";
		 String type="borrow";
		 String mode="bigsmall";
		 String searchtype="书名";
		 String content="";
		 if(request.getParameter("booktypeid")!=null)
		 {
		  booktypeid=request.getParameter("booktypeid");
		 }
		 if(request.getParameter("categoryid")!=null)
		 {
		  categoryid=request.getParameter("categoryid");
		 }
		 if(request.getParameter("type")!=null)
		 {
		  type = request.getParameter("type");
		 }
		 if(request.getParameter("mode")!=null)
		 {
		 mode = request.getParameter("mode");
		 }
		 if(request.getParameter("searchtype")!=null)
		 {
		  searchtype = request.getParameter("searchtype");
		 }
		 if(request.getParameter("content")!=null)
		 {
		 content = request.getParameter("content");
		 }
	
		 BookDao bookdao=new BookDao();
		 bookdao.deleteBook(Bookid);
		 if(request.getParameter("booktypeid")!=null)
		 {
			 request.getRequestDispatcher("BookPageServlet?categoryid="+categoryid+"&booktypeid="+booktypeid+"&mode="+mode+"&type="+type+"&pc="+pc).forward(request, response);
		 }else{
			 if(request.getParameter("action")!=null)
			 {
				 request.getRequestDispatcher("BookPageServlet").forward(request, response);; 
			 }else{
			 request.getRequestDispatcher("BookSearchServlet?pc="+pc+"&searchtype="+searchtype+"&content="+content).forward(request, response);
			 }
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
