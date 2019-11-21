package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderLineDao;
import entity.OnlineOrder;


@WebServlet("/ShowOrderByid")
public class ShowOrderByid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowOrderByid() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("application/json;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
	       
	       
	       String orderid=request.getParameter("orderid");
	       
	       
	       OrderLineDao old=new OrderLineDao();
	       
	     OnlineOrder o=old.getOrderLineByid(Integer.parseInt(orderid));
	     
	     StringBuilder str=new StringBuilder();
	       
	       if(o!=null)
	       {
	    	   
	    	   
   			str.append("{").append("\"id\":\""+o.getId()+"\"")
				.append(",")
				.append("\"booksonid\":\""+o.getBookson().getBooksonid() +"\"")
				.append(",")
				.append("\"bookid\":\""+o.getBookson().getBook().getBookid() +"\"")
				.append(",")
				.append("\"userid\":\""+o.getUser().getUserid()+"\"")
				.append(",")
			   .append("\"orderdate\":\""+o.getOrderdate()+"\"")
			   .append(",")
			   .append("\"status\":\""+o.getStatus()+"\"")
			   .append(",")
			   .append("\"date\":\""+o.getDate()+"\"")
			
				.append(",")
				.append("\"bookimages\":\""+o.getBookson().getBook().getBookimages()+"\"")
				.append(",")
				.append("\"category_id\":\""+o.getBookson().getBook().getCategory().getCategoryid()+"\"")
				.append(",")
				.append("\"author\":\""+o.getBookson().getBook().getAuthor()+"\"")
				.append(",")
				.append("\"booktitle\":\""+o.getBookson().getBook().getBooktitle()+"\"")
				.append(",")
				.append("\"typename\":\""+o.getBookson().getBook().getCategory().getName()+"\"")
				.append(",")
				   .append("\"isbn\":\""+o.getBookson().getBook().getPublishnumber()+"\"")
				   .append(",")
				   .append("\"cash\":\""+o.getBookson().getBook().getCash()+"\"")
			    .append("}");
   		  
	    	   
	    	   
	    	   out.write(str.toString());
	    	   
	       }
		  
		   
		          
	}

}
