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
import dao.CollectDao;
import dao.OrderLineDao;
import entity.Book;
import entity.BookSon;
import entity.OnlineOrder;


@WebServlet("/DeleteOrderLine")
public class DeleteOrderLine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteOrderLine() {
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
		  
		  
		  String userid=request.getParameter("userid");
	      
		  String id=request.getParameter("id");
	       
	       OrderLineDao old=new OrderLineDao();
	       
	       
	       //修改预定状态为0
	       BookSonDao bd=new BookSonDao();
    	   BookSon b=new BookSon();
    	 
    	   OnlineOrder o=new OnlineOrder();
    	   o=old.getOrderLineByid(Integer.parseInt(id));
    	   b.setBooksonid(o.getBookson().getBooksonid());
//    	   b.setOrderstatus(0);
    	   bd.UpdateBookOrderStatus(b);
     
	  
	    	 old.DeleteOrder(Integer.parseInt(id));
	    	   
	    	 
	    	   
	    	  
	       
	    	   OrderLineDao od=new OrderLineDao();
			     
		         
			     List<OnlineOrder> olist=od.getOrderByuserid(userid);
			     StringBuilder str=new StringBuilder();
			
			     
			      if(olist!=null&&olist.size()>0)
			      {
			    	  
			    	  
			    	  for(int i=olist.size()-1;i>=0;i--)
			    	  {
			    		  
			    		  OnlineOrder o1=olist.get(i);
			    		  
			    			 
				    		 String introduce=null;
				    		 
				    		   if(o1.getBookson().getBook().getIntroduce()!=null)
					  	         {
					  	        	 
					  	        	
					  	        	 introduce=o.getBookson().getBook().getIntroduce().replaceAll("\t", "");
					  	         }
					  	         else
					  	         {
					  	        	 introduce="没有相关内容";
					  	        	 
					  	         }	 
					        	 
			    		  
			    			str.append("{").append("\"id\":\""+o1.getId()+"\"")
							.append(",")
							.append("\"bookid\":\""+o1.getBookson().getBook().getBookid() +"\"")
							.append(",")
							.append("\"userid\":\""+o1.getUser().getUserid()+"\"")
							.append(",")
						   .append("\"orderdate\":\""+o1.getOrderdate()+"\"")
						   .append(",")
						   .append("\"status\":\""+o1.getStatus()+"\"")
						   .append(",")
						   .append("\"date\":\""+o1.getDate()+"\"")
						   .append(",")
							.append("\"introduce\":\""+introduce+"\"")
							.append(",")
							.append("\"bookimages\":\""+o1.getBookson().getBook().getBookimages()+"\"")
							.append(",")
							.append("\"category_id\":\""+o1.getBookson().getBook().getCategory().getCategoryid()+"\"")
							.append(",")
							.append("\"author\":\""+o1.getBookson().getBook().getAuthor()+"\"")
							.append(",")
							.append("\"booktitle\":\""+o.getBookson().getBook().getBooktitle()+"\"")
						    .append("}").append(",");
			    		  
			     	  }
			    	  
			    	  
			    	  out.write("["+str.substring(0,str.length()-1) +"]");
			    	  
			 	  
			      }
			    
			
		  
		  
		  
	}

}
