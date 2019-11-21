package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderLineDao;
import entity.OnlineOrder;
import util.JsonFormat;

import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class GetOrderServlet
 */
@WebServlet("/GetOrderServlet")
public class GetOrderServlet extends HttpServlet {

	
	public GetOrderServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		     doPost(request,response);

		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
			{
		
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
		  PrintWriter out=response.getWriter() ;
		
		  
		     String userid=request.getParameter("userid");
		     
		      OrderLineDao od=new OrderLineDao();
		     
		         
		     List<OnlineOrder> olist=od.getOrderByuserid(userid);
		     StringBuilder str=new StringBuilder();
		
		     
		      if(olist!=null&&olist.size()>0)
		      {
		    	  
		    	  
		    	  for(int i=olist.size()-1;i>=0;i--)
		    	  {
		    		  
		    		  OnlineOrder o=olist.get(i);
		    		  
		    			 
			    		 String introduce=null;
			    		 JsonFormat js=new	 JsonFormat();
			    		 
			    		   if(o.getBookson().getBook().getIntroduce()!=null)
				  	         {
				  	        	 
				  	        	
              introduce= js.stringToJson( o.getBookson().getBook().getIntroduce());
				  	         }
				  	         else
				  	         {
				  	        	 introduce="没有相关内容";
				  	        	 
				  	         }	 
				        	 
		    		  
		    			str.append("{").append("\"id\":\""+o.getId()+"\"")
						.append(",")
						.append("\"bookid\":\""+o.getBookson().getBook().getBookid() +"\"")
						.append(",")
						.append("\"userid\":\""+o.getUser().getUserid()+"\"")
						.append(",")
					   .append("\"orderdate\":\""+o.getOrderdate().substring(5,o.getOrderdate().length())+"\"")
					   .append(",")
					   .append("\"status\":\""+o.getStatus()+"\"")
					   .append(",")
					   .append("\"date\":\""+o.getDate()+"\"")
					   .append(",")
						.append("\"introduce\":\""+introduce+"\"")
						.append(",")
						.append("\"bookimages\":\""+o.getBookson().getBook().getBookimages()+"\"")
						.append(",")
						.append("\"category_id\":\""+o.getBookson().getBook().getCategory().getCategoryid()+"\"")
						.append(",")
						.append("\"author\":\""+o.getBookson().getBook().getAuthor()+"\"")
						.append(",")
						.append("\"booktitle\":\""+o.getBookson().getBook().getBooktitle()+"\"")
					    .append("}").append(",");
		    		  
		     	  }
		    	  
		    	  
		    	  out.write("["+str.substring(0,str.length()-1) +"]");
		    	  
		 	  
		      }
		    
		

		
	}

	
	public void init() throws ServletException {
		
	}

}
