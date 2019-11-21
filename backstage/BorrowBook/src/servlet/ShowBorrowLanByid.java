package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import entity.BorrowTable;


@WebServlet("/ShowBorrowLanByid")
public class ShowBorrowLanByid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ShowBorrowLanByid() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		     doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 response.setContentType("application/json;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
	       
	       
	       String borrowlanid=request.getParameter("borrowid");
	       
		   System.out.println(borrowlanid);
		   
		   
	       BorrowTableDao btd=new BorrowTableDao();
	         
	        BorrowTable bt=btd.getBorrowTablebyid(Integer.parseInt(borrowlanid));
	         
	         StringBuilder str=new StringBuilder();
	         if(bt!=null)
	         {
	        	
	 
		     		str.append("{").append("\"id\":\""+bt.getId()+"\"")
					.append(",")
					.append("\"booksonid\":\""+bt.getBookson().getBooksonid()+"\"")  
					.append(",")
					.append("\"booktitle\":\""+bt.getBookson().getBook().getBooktitle()+"\"")
					.append(",")
					.append("\"bookimages\":\""+bt.getBookson().getBook().getBookimages()+"\"")
					.append(",")
					.append("\"author\":\""+bt.getBookson().getBook().getAuthor()+"\"")
					.append(",")
					.append("\"publish\":\""+bt.getBookson().getBook().getPublish()+"\"")
					.append(",")
				   .append("\"borrowdate\":\""+bt.getBorrowdate()+"\"")
				   .append(",")
				   .append("\"deadborrow\":\""+bt.getDeadborrow()+"\"")
				   .append(",")
				   .append("\"userimages\":\""+bt.getUser().getUserimages()+"\"")
				   .append(",")
				   .append("\"userid\":\""+bt.getUser().getUserid()+"\"")
				   .append(",")
				   .append("\"nickname\":\""+bt.getUser().getNickname()+"\"")
				   .append(",")
				   .append("\"isbn\":\""+bt.getBookson().getBook().getPublishnumber()+"\"")
				   .append(",")
					.append("\"bookid\":\""+bt.getBookson().getBook().getBookid() +"\"")
					.append(",")
					.append("\"category_id\":\""+bt.getBookson().getBook().getCategory().getCategoryid()+"\"")
					.append(",")
					.append("\"typename\":\""+bt.getBookson().getBook().getCategory().getName()+"\"")
					.append(",")
					.append("\"borrowdate\":\""+bt.getBorrowdate()+"\"")
					.append(",")
					.append("\"isbn\":\""+bt.getBookson().getBook().getPublishnumber()+"\"")
					.append(",")
					.append("\"deadstatus\":\""+bt.getDeadstatus()+"\"")
					.append(",")
					.append("\"cash\":\""+bt.getBookson().getBook().getCash()+"\"")
				   .append("}");
		    	    
		     		 out.write(str.toString());

	         }
	         
	         
		
		     
		 
		      
	}

}
