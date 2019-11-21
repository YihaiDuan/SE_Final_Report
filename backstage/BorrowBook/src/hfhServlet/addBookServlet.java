package hfhServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;


import entity.Book;
import entity.BookSon;
import entity.Category;
import hfhdao.BookDao;
import hfhdao.CategoryDao;
@WebServlet(name="addBookServlet",urlPatterns="/addBookServlet")
public class addBookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public addBookServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType("text/html;charset=utf-8");
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
         String bookimages="https://www.xunjia.net.cn/BorrowBook/images/"+tempFile.getFileName(); 
		 String categoryid=su.getRequest().getParameter("categoryid");
		 String floor=su.getRequest().getParameter("floor");
		 // System.out.println(floor);
		 String room=su.getRequest().getParameter("room");
		 String position =floor+room;
		 String bookid=su.getRequest().getParameter("bookid");
		 String booktitle=su.getRequest().getParameter("booktitle");
		 String author=su.getRequest().getParameter("author");
		 System.out.println(author);
		 String publish=su.getRequest().getParameter("publish");
		 String publishnumber=su.getRequest().getParameter("publishnumber");
		 String introduce=su.getRequest().getParameter("introduce");
		 String review=su.getRequest().getParameter("review");
		 String guidreading=su.getRequest().getParameter("guidreading");
	     String []booksonid=su.getRequest().getParameterValues("booksonid");
	     
	     Category category=new Category();
	     CategoryDao categorydao=new CategoryDao();
	     category=categorydao.getidCategory(categoryid);
	     
	     
	     int booknum=booksonid.length;
	     Book book=new Book();
	     book.setPosition(position);
		 book.setBookid(bookid);
		 book.setBooktitle(booktitle);
		 book.setAuthor(author);
		 book.setBookimages(bookimages);
		 book.setPublish(publish);
		 book.setPublishnumber(publishnumber);
		 book.setIntroduce(introduce);
		 book.setBooknum(booknum);
		 book.setReview(review);
		 book.setGuidreading(guidreading);
	     Set<BookSon> booksons=new HashSet<BookSon>();
	     for(int i=0;i<booksonid.length;i++)
	     {
	    	 BookSon bookson=new BookSon();
	    	 bookson.setBooksonid(booksonid[i]);
	    	 booksons.add(bookson); 
	     }
	      book.setBookson(booksons);
	      book.setCategory(category);
	      BookDao bookdao=new BookDao();
		  bookdao.addBook(book);
		 
		  request.getRequestDispatcher("addBook.jsp?action=success").forward(request, response);
	/* 
		 Book book=new Book();
		 book.setBookid(bookid);
		 book.setBooktitle(booktitle);
		 book.setAuthor(author);
		 
		 book.setPublish(publish);
		 book.setPublishnumber(publishnumber);
		 book.setIntroduce(introduce);
		 book.setBooknum(booknum);
		 book.setReview(review);
		 book.setGuidreading(guidreading);
		 CategoryDao categorydao=new CategoryDao();
		 Category category =categorydao.getidCategory(categoryid);
		
		 book.setCategory(category);
		 BookDao bookdao=new BookDao();
		 bookdao.addBook(book);
		 
		 request.getRequestDispatcher("BookPageServlet?pc="+pc+"&categoryid="+categoryid).forward(request, response);*/
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
