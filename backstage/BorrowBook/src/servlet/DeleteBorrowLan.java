package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.BookSonDao;
import dao.BorrowTableDao;
import entity.Book;
import entity.BookSon;
import entity.BorrowTable;


@WebServlet("/DeleteBorrowLan")
public class DeleteBorrowLan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteBorrowLan() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		 response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   String  id=request.getParameter("id");
		   
		   
		   String userid=request.getParameter("userid");
		   
		   
		   
		   //淇敼鏀逛功鐨勯璁綅涓�0
		   BorrowTableDao btd=new BorrowTableDao();
		   
		   BorrowTable b=btd.getBorrowbyId(Integer.parseInt(id));
		   
		   BookSonDao bd=new BookSonDao();
		    BookSon b2=new BookSon();
		   
		   b2.setBooksonid(b.getBookson().getBooksonid());
	
		   
		   bd.UpdateBookOrderStatus(b2);
		   
		   
		   
		   //鐒跺悗灏嗚涔﹀垹闄�
		   btd.DeleteBorrowLan(Integer.parseInt(id));
		   
		   
		   
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
			  	        	 
			  	        	
			  	        	 introduce=bt.getBookson().getBook().getIntroduce().replaceAll("\t", "");
			  	         }
			  	         else
			  	         {
			  	        	 introduce="没有相关内容";
			  	        	 
			  	         }	 
			        	 
		    		 
		    			str.append("{").append("\"id\":\""+bt.getId()+"\"")
						.append(",")
						.append("\"bookid\":\""+bt.getBookson().getBook().getBookid() +"\"")  
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
						.append("\"introduce\":\""+introduce+"\"")
						.append("}").append(",");
		    	
		    	 }
		    	 
		    	 out.write("["+str.substring(0,str.length()-1)+"]");
		    	 
		     }
		        
		   
		   
		   
		   
		   
	
	}

}
