package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import entity.BorrowTable;
import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class ShowReturnServlet
 */
@WebServlet("/ShowReturnServlet")
public class ShowReturnServlet extends HttpServlet {

	
	public ShowReturnServlet() {
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
			throws ServletException, IOException {
		
		     
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
	      
	      String userid =request.getParameter("userid");
	      
	      
	      BorrowTableDao btd=new BorrowTableDao();
	      
	       List<BorrowTable>  blist= btd.getBookReturnData(userid);
	       
	       StringBuilder str=new StringBuilder();
			  
	    
			  if(blist!=null&&blist.size()>0)
			  {
				  for(int i=blist.size()-1;i>=0;i--)
				  {
						BorrowTable b=blist.get(i);
						
						 
			    		 String introduce=null;
			    		 
			    		   if(b.getBookson().getBook().getIntroduce()!=null)
				  	         {
				  	        	 
				  	        	
				  	        	 introduce=b.getBookson().getBook().getIntroduce().replaceAll("\t", "");
				  	         }
				  	         else
				  	         {
				  	        	 introduce="没有相关内容";
				  	        	 
				  	         }	 
				        	 
						str.append("{").append("\"bookid\":\""+b.getBookson().getBook().getBookid()+"\"")
						.append(",")
						.append("\"id\":\""+b.getId() +"\"")
						.append(",")
						.append("\"userid\":\""+b.getUser().getUserid() +"\"")
						.append(",")
						.append("\"booktitle\":\""+b.getBookson().getBook().getBooktitle()+"\"")
						.append(",")
						.append("\"borrowdate\":\""+b.getBorrowdate()+"\"")
						.append(",")
						.append("\"returndate\":\""+b.getReturndate() +"\"")
						  .append(",")
							.append("\"introduce\":\""+introduce+"\"")
							.append(",")
							.append("\"bookimages\":\""+b.getBookson().getBook().getBookimages()+"\"")
							.append(",")
							.append("\"category_id\":\""+b.getBookson().getBook().getCategory().getCategoryid()+"\"")
							.append(",")
							.append("\"author\":\""+b.getBookson().getBook().getAuthor()+"\"")
							.append(",")
							.append("\"booktitle\":\""+b.getBookson().getBook().getBooktitle()+"\"")
							.append(",")
							.append("\"evaluatestatus\":\""+b.getEvaluatestatus()+"\"")
				
						.append("}").append(",");
					  
			    }
				  
					 out.write("["+str.substring(0,str.length()-1)+"]");
					 System.out.println(str);
				  
			  }
			  
		
	      
		
		

	
	}


	public void init() throws ServletException {
		
	}

}
