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
@WebServlet(name="ModifyBookServlet",urlPatterns="/ModifyBookServlet")
public class ModifyBookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ModifyBookServlet() {
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
		String filePath=getServletContext().getRealPath("/")+"images";
	  		File file=new File(filePath);
	  		if(!file.exists())
	  		{
	  			file.mkdir();
	  			
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
	  			
	  			
	  		} catch (Exception e) {
	  			//result="娑撳﹣绱舵径杈Е";
	  			e.printStackTrace();
	  			
	  		}
	  	
	  		
	  		com.jspsmart.upload.File tempFile=su.getFiles().getFile(0);
       //  String filename=tempFile.getFileName();
        // int filesize=tempFile.getSize();
		  //  String title=su.getRequest().getParameter("title");
       String bookimages="https://www.xunjia.net.cn/BorrowBook/images/"+tempFile.getFileName(); 
        int pc = 0;
        if(request.getParameter("pc")!=null)
        {
        	pc = Integer.parseInt(request.getParameter("pc"));        	
        }
        int booktypeid = 0;
		if(request.getParameter("booktypeid")!=null)
		{
	    booktypeid = Integer.parseInt(request.getParameter("booktypeid"));
		}
	    String categoryid = "0";
	    if(request.getParameter("categoryid")!=null)
	    {
		categoryid = request.getParameter("categoryid");
	    }
		String type="borrow";
		if(request.getParameter("type")!=null){
		type = request.getParameter("type");
		}
		String mode="bigsmall";
		if(request.getParameter("mode")!=null)
		{
		mode = request.getParameter("mode");
		}
		String searchtype = "书名";
		if(request.getParameter("searchtype")!=null)
		{
		searchtype = request.getParameter("searchtype");
		}
		String content ="";
		if(request.getParameter("content")!=null)
		{
	      content = request.getParameter("content");
		}
		
       String floor=su.getRequest().getParameter("floor");
	   String room=su.getRequest().getParameter("room");
	   String position =floor+room;
	   System.out.println(position);
	   String bookid=su.getRequest().getParameter("bookid");
	   System.out.println(bookid);
	   String booktitle=su.getRequest().getParameter("booktitle");
	   System.out.println(booktitle);
	   String author=su.getRequest().getParameter("author");
	   System.out.println(author);
	   String publish=su.getRequest().getParameter("publish");
	   System.out.println(publish);
	   String publishnumber=su.getRequest().getParameter("publishnumber");
	   System.out.println(publishnumber);
	   String introduce=su.getRequest().getParameter("introduce");
	   System.out.println(introduce);
	   String review=su.getRequest().getParameter("review");
	   System.out.println(review);
	   String guidreading=su.getRequest().getParameter("guidreading");
	   System.out.println(guidreading);
	   BookDao bookdao = new BookDao();
	   Book book = bookdao.getIdBook(bookid);
	   book.setPosition(position);
	   book.setBookid(bookid);
	   book.setBooktitle(booktitle);
	   book.setAuthor(author);
	   book.setPublish(publish);
	   book.setPublishnumber(publishnumber);
	   book.setIntroduce(introduce);
	   book.setReview(review);
	   book.setGuidreading(guidreading);
	   if(!tempFile.getFileName().equals(""))
	   {
		   book.setBookimages(bookimages);
	   }
	     bookdao.UpdateBook(book);   
	   if(request.getParameter("booktypeid")!=null)
	   {
		     request.getRequestDispatcher("BookPageServlet?pc="+pc+"&booktypeid="+booktypeid+"&categoryid="+categoryid+"&type="+type+"&mode="+mode).forward(request,response);
		   
	   }else{
		   
		   
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
