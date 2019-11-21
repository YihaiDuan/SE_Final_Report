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


@WebServlet("/DeleteBorrowHistroy")
public class DeleteBorrowHistroy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DeleteBorrowHistroy() {
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
		  
		  
		  String id=request.getParameter("id");
		  String userid=request.getParameter("userid");
		  
		  
		  BorrowTableDao btd=new BorrowTableDao();
		  
		  
		   btd.DeleteBorrowLan(Integer.parseInt(id));
		   
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
				
						.append("}").append(",");
					  
			    }
				  
					 out.write("["+str.substring(0,str.length()-1)+"]");
					 System.out.println(str);
				  
			  }
			  
		
	      
		
		
		
	}

}
