package hfhServlet;

import hfhdao.BookDao;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

import entity.Book;
@WebServlet(name="AddEBookServlet",urlPatterns="/AddEBookServlet")
public class AddEBookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddEBookServlet() {
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
		 String filePath2=getServletContext().getRealPath("/")+"txt";
		 String filePath="C://javastore//BorrowBook//WebContent//txt";
		 File file=new File(filePath); 
		  File file2=new File(filePath2);
	  		if(!file.exists())
	  		{
	  			file.mkdir();
	  			
	  		}
	  		if(!file2.exists())
	  		{
	  			file2.mkdir();
	  			
	  		}
	  		SmartUpload su=new SmartUpload();
	  	
	  		su.initialize(getServletConfig(), request, response);
	  		
	  		//鐠佸墽鐤嗘稉濠佺炊閺傚洣娆㈤惃鍕亣鐏忥拷
	  		su.setMaxFileSize(1024*1024*100*1024*10*1024*1024);
	  		//鐠佸墽鐤嗛幍锟介張澶嬫瀮娴犺泛銇囩亸锟�
	  		su.setTotalMaxFileSize(1024*1024*100*1024*10*1024*1024);
	  		//娑撳﹣绱堕弬鍥︽缁鐎�*
	  		su.setAllowedFilesList("txt");
	      //  String result="娑撳﹣绱堕幋鎰";
	       
	  		//鐠佸墽鐤嗙粋浣诡剾娑撳﹣绱堕弬鍥︽缁鐎�
	  		try {
	  			su.setDeniedFilesList("rar,jsp,js");
	  			//娑撳﹣绱堕弬鍥︽
	  			su.upload();
	  			su.save(filePath);
	  			su.save(filePath2);
	  			
	  		} catch (Exception e) {
	  			//result="娑撳﹣绱舵径杈Е";
	  			e.printStackTrace();
	  			
	  		}
	  	
	  		
	  com.jspsmart.upload.File tempFile=su.getFiles().getFile(0);
      //  String filename=tempFile.getFileName();
       // int filesize=tempFile.getSize();
		  //  String title=su.getRequest().getParameter("title");
      String eletext=tempFile.getFileName(); 
      String freestatus = su.getRequest().getParameter("freestatus");
      String eleprice = su.getRequest().getParameter("eleprice");
      String vipfreestatus = su.getRequest().getParameter("vipfreestatus");
      String bookid  = su.getRequest().getParameter("bookid");
      BookDao bookdao = new BookDao();
      Book book = bookdao.getIdBook(bookid);
      book.setFreestatus(Integer.parseInt(freestatus));
      book.setEleprice((double) Integer.parseInt(eleprice));
      book.setVipfreestatus(Integer.parseInt(vipfreestatus));
      book.setEletext(eletext);
      book.setElestatus(1);
      bookdao.UpdateBook(book);
      request.getRequestDispatcher("BookPageServlet").forward(request,response);
      
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
