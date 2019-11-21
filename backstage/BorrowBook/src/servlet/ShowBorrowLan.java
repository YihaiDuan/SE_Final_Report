package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import entity.BorrowTable;
import util.JsonFormat;


@WebServlet("/ShowBorrowLan")
public class ShowBorrowLan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowBorrowLan() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		   doPost(request,response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	       PrintWriter out=response.getWriter() ;
		
		  
	       String userid=request.getParameter("userid");
	         System.out.println("用户id"+userid);
	       
	       
	       BorrowTableDao btd=new BorrowTableDao();
	      
	     List<BorrowTable>  blist= btd.getBookNoScan(userid);
	     StringBuilder str=new StringBuilder();
	     
	     if(blist!=null&&blist.size()>0)
	     {
	    	 for(int i=blist.size()-1;i>=0;i--)
	    	 {
	    		 BorrowTable bt=blist.get(i);
	    		 System.out.println(bt.getId());
	    		 
	    		 
	    		 String introduce=null;
	    		 
	    		   if(bt.getBookson().getBook().getIntroduce()!=null)
		  	         {
		  	        	 
		  	        	
		  	      introduce=JsonFormat.stringToJson( bt.getBookson().getBook().getIntroduce());
		  	    		
		  	         }
		  	         else
		  	         {
		  	        	 introduce="没有相关内容";
		  	        	 
		  	         }	 
		        	 
	    		 
	    		 
	    			str.append("{").append("\"id\":\""+bt.getId()+"\"")
					.append(",")
					.append("\"bookid\":\""+bt.getBookson().getBooksonid() +"\"")  
					.append(",")
					.append("\"booktitle\":\""+bt.getBookson().getBook().getBooktitle()+"\"")
					.append(",")
					.append("\"bookimages\":\""+bt.getBookson().getBook().getBookimages()+"\"")
					.append(",")
					.append("\"author\":\""+bt.getBookson().getBook().getAuthor()+"\"")
					.append(",")
					.append("\"publish\":\""+bt.getBookson().getBook().getPublish()+"\"")
					.append(",")
					.append("\"deadstatus\":\""+bt.getDeadstatus()+"\"")
					.append(",")
					.append("\"borrowdate\":\""+bt.getBorrowdate()+"\"")
					.append(",")
					.append("\"introduce\":\""+introduce+"\"")
					.append(",")
					.append("\"category_id\":\""+bt.getBookson().getBook().getCategory().getCategoryid()+"\"")
					.append(",")
					.append("\"deadborrow\":\""+bt.getDeadborrow()+"\"")
					.append("}").append(",");
	    	
	    	 }
	    	 
	    	 out.write("["+str.substring(0,str.length()-1)+"]");
	    	 System.out.println(str);
	     }
	        
		
		
		
	}

}
