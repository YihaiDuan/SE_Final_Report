package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import Extenddao.BookTypeDao;

import com.jspsmart.upload.SmartUpload;

import hfhdao.CategoryDao;
import entity.BookType;
import entity.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="AddCategoryServlet",urlPatterns="/AddCategoryServlet")
public class AddCategoryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddCategoryServlet() {
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
		  String filePath2=getServletContext().getRealPath("/")+"images";	      
	      String filePath="C://javastore//BorrowBook//WebContent//images";
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
	  		su.setMaxFileSize(1024*1024*100*1024*10*1024);
	  		//鐠佸墽鐤嗛幍锟介張澶嬫瀮娴犺泛銇囩亸锟�
	  		su.setTotalMaxFileSize(1024*1024*100*1024*10*1024);
	  		//娑撳﹣绱堕弬鍥︽缁鐎�*
	  		su.setAllowedFilesList("txt,jpg,gif,doc,mp4,png");
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
      String typeclass="https://www.xunjia.net.cn/BorrowBook/images/"+tempFile.getFileName(); 
      String name=su.getRequest().getParameter("name"); 
      String categoryid=su.getRequest().getParameter("categoryid");
     int booktypeid = Integer.parseInt(su.getRequest().getParameter("booktypeid"));
      System.out.println(booktypeid+"=================");
      BookTypeDao booktypedao = new BookTypeDao();
      BookType booktype = booktypedao.getIdBookType(booktypeid);
      Category category =new Category();
      category.setCategoryid(categoryid);
      category.setName(name);
      category.setTypeclass(typeclass);
      category.setType(booktype);
      CategoryDao categorydao=new CategoryDao();
      categorydao.addCategory(category);
      response.sendRedirect("BookPageServlet");
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
