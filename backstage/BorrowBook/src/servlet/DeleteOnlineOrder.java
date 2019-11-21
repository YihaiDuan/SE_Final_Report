package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.CollectDao;
import dao.OrderLineDao;
import entity.Book;
import entity.OnlineOrder;
import util.JsonFormat;


@WebServlet("/DeleteOnlineOrder")
public class DeleteOnlineOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteOnlineOrder() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
	       
	       String userid=request.getParameter("userid");
	       String bookid=request.getParameter("bookid");
	       OrderLineDao old=new OrderLineDao();
	       
	     OnlineOrder ol=  old.getOrderByid(userid, bookid);
	     
	    
	     
	     
	       
	       if(ol!=null)
	       {
	    	   
	    	   int id=ol.getId();
	    	   
	    	   old.DeleteOrder(id);
	    	   
	    	   BookDao bd=new BookDao();
	    	   Book b=new Book();
	    	   
	    	   
	    	   //鏇存柊涔︾殑棰勮浣�
	    	   b.setBookid(bookid);
	    	  // b.setOrderstatus(0);
	    	 //  bd.UpdateBookOrderStatus(b);
	       }
	       
	       
	       BookDao bd=new BookDao();
           Book b=bd.getBookbyid(bookid);
	       
	       StringBuilder str=new StringBuilder();
           
           if(b!=null)
	        {
               CollectDao cod=new CollectDao();
   	        boolean collectbol=cod.CollectBol(userid,bookid);
   	        
   	        
   	     String list=null;
         String guidreading=null;
         String review=null;
         String introduce=null;
         
       if(b.getList()!=null)
         {
        	 list=JsonFormat.stringToJson(b.getList());
        
         }
         else
         {
        		list="没有相关内容";
        	 
         }
         
         
        	 
         if(b.getGuidreading()!=null)
         {
        	 guidreading=JsonFormat.stringToJson(b.getGuidreading());
        	
         }
         else
         {
        	 guidreading="没有相关内容";
        	 
         }
         
         
         
         if(b.getReview()!=null)
         {
        	 review=JsonFormat.stringToJson(b.getReview());
        	
         }
         else
         {
        	 review="没有相关内容";
        	 
         }
         
         
         
         if(b.getIntroduce()!=null)
         {
        	 
        	
        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
         }
         else
         {
        	 introduce="没有相关内容";
        	 
         }	 
	 
     
   	        
   	        
   	     str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
	        .append(",")
			.append("\"author\":\""+b.getAuthor() +"\"")
			
			.append(",")
			.append("\"bookimages\":\""+b.getBookimages() +"\"")
			
			.append(",")
			.append("\"booktitle\":\""+b.getBooktitle()+"\"")
			
			.append(",")
			.append("\"introduce\":\""+introduce+"\"")
			
			.append(",")
			.append("\"publish\":\""+b.getPublish()+"\"")
			
			.append(",")
			.append("\"publishnumber\":\""+b.getPublishnumber()+"\"")
			
		
			.append(",")
			.append("\"booknum\":\""+b.getBooknum()+"\"")
			
		
			.append(",")
			.append("\"guidreading\":\""+guidreading+"\"")
			.append(",")
			.append("\"cash\":\""+b.getCash()+"\"")
			.append(",")
			.append("\"review\":\""+review+"\"")
			.append(",")
			.append("\"list\":\""+list+"\"")
			
			
			.append(",")
			.append("\"collectbol\":\""+collectbol+"\"")
			.append(",")
 			.append("\"typename\":\""+b.getCategory().getName()+"\"")
 			.append(",")
 			.append("\"cash\":\""+b.getCash()+"\"")
 			.append(",")
 			.append("\"position\":\""+b.getPosition()+"\"")
			.append("}").append(",");
	        
	        out.write(str.toString());
	        
	        }
	       
	       
	       
	       
	}

}
