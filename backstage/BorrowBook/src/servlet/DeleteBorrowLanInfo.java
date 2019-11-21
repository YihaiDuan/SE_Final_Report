package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookSonDao;
import dao.BorrowTableDao;
import dao.UserDao;
import entity.BookSon;
import entity.BorrowTable;
import entity.User;


@WebServlet("/DeleteBorrowLanInfo")
public class DeleteBorrowLanInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DeleteBorrowLanInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		       doPost(request,response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
      
        PrintWriter out=response.getWriter();
        
        
        String  id=request.getParameter("id");
		   
		   
		   String userid=request.getParameter("userid");
		   
		   
		   
		   //淇敼鏀逛功鐨勯璁綅涓�0
		   BorrowTableDao btd=new BorrowTableDao();
		   
		   BorrowTable b=btd.getBorrowbyId(Integer.parseInt(id));
		   
		   BookSonDao bd=new BookSonDao();
		    BookSon b2=new BookSon();
		   
		   b2.setBooksonid(b.getBookson().getBooksonid());
//		   b2.setOrderstatus(0);
		   
		   bd.UpdateBookOrderStatus(b2);
		   
		   
		   
		   //鐒跺悗灏嗚涔﹀垹闄�
		   btd.DeleteBorrowLan(Integer.parseInt(id));
		   
		   
		   
		   List<BorrowTable>  blist=btd.getBookNoScanNoDeadLine(userid);
	         StringBuilder str=new StringBuilder();
	         String json=null;
	         
	         float totalcash=0.0f;
	         
	         
	       if(blist!=null&&blist.size()>0)
	       {
	        	 
	        for(int i=blist.size()-1;i>=0;i--)	
	        {
	        	 
	        	 BorrowTable bt=blist.get(i);
	        	
	        	 
	        	  
	        	   totalcash+=bt.getBookson().getBook().getCash();
	        	
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
			   .append("\"borrowdate\":\""+bt.getBorrowdate().substring(0,10)+"\"")
			   .append(",")
			   .append("\"booksonid\":\""+bt.getBookson().getBooksonid()+"\"")
			   .append(",")
			   .append("\"isbn\":\""+bt.getBookson().getBook().getPublishnumber()+"\"")
			   .append(",")
			   .append("\"cash\":\""+bt.getBookson().getBook().getCash()+"\"")
			   
				.append("}").append(",");
	    	   
	            
	         }
	        
	        
	        
	    json="{\"totalcash\":"+"\""+totalcash+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}";
	        
	       out.write(json);
	    
	       }  
	       else
	       {
	    	   
	    	   
	    	   out.write("610");
	       }
	       
			
			
		   
		         
	}

}
