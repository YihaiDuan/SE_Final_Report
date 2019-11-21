package hfhServlet;

import hfhdao.BookDao;
import hfhdao.CategoryDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import util.DataUp;
import Extenddao.BookTypeDao;

import com.jspsmart.upload.SmartUpload;

import entity.Book;
import entity.BookType;
import entity.Category;
@WebServlet(name="BatchAddBookServlet",urlPatterns="/BatchAddBookServlet")
public class BatchAddBookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BatchAddBookServlet() {
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
		System.out.println("进来了");
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
	  		su.setAllowedFilesList("txt,jpg,gif,doc,mp4,png,xls,xlsx");
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
           String bookimages="C://javastore//BorrowBook//WebContent//images//"+tempFile.getFileName(); 
           System.out.println(bookimages);
           DataUp d=new DataUp();
           d.readerExcel(bookimages);
           request.getRequestDispatcher("batchaddbook.jsp?action=success").forward(request, response);
           
       /*    InputStream is = new FileInputStream(bookimages);
           Workbook book = null;
           try {
			book = Workbook.getWorkbook(is);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
           
           List<Book> list = new ArrayList<Book>();
           Book b = new Book();
           Sheet sheet =  book.getSheet(0);
           int rows = sheet.getRows();
           int cols = sheet.getColumns();
           for(int i=0;i<cols;i++)
           {
        	   Cell[] cells = sheet.getRow(i+1);
        	   String bookid = cells[0].getContents();
        	   String booktitle = cells[1].getContents();
               String author = cells[2].getContents();
               String publish = cells[3].getContents();
               String publishnumber = cells[4].getContents();
               String bookimage = cells[5].getContents();
               String catalog = cells[6].getContents();
               String introduce = cells[7].getContents();
               String review = cells[8].getContents();
               String guidreading = cells[9].getContents();
               String categoryname = cells[10].getContents();
               String position = cells[11].getContents();
               b.setBookid(bookid);
               b.setBooktitle(booktitle);
               b.setAuthor(author);
               b.setPublish(publish);
               b.setPublishnumber(publishnumber);
               b.setBookimages(bookimage);
               b.setList(catalog);
               b.setIntroduce(introduce);
               b.setReview(review);
               b.setGuidreading(guidreading);
               b.setPosition(position);
               CategoryDao categorydao = new CategoryDao();
               Category category = categorydao.getNameCategory(categoryname);
               b.setCategory(category);
               list.add(b);
           }
               BookDao bookdao = new BookDao();
               for(int i = 0 ; i <list.size(); i++)
               {
            	   bookdao.addBook(list.get(i));  
               }
               request.setAttribute("list",list);
               
               */
               
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
