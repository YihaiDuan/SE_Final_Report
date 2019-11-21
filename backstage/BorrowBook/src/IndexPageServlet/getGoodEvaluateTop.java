package IndexPageServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import entity.Book;
import util.JsonFormat;


@WebServlet("/getGoodEvaluateTop")
public class getGoodEvaluateTop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public getGoodEvaluateTop() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		        doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      
		  PrintWriter out=response.getWriter() ;
		  
		  String action =request.getParameter("action");
		  
		  BookDao bd=new BookDao();
		  
		  
		  
		  if(action.equals("0"))
		  {
		  
		    List<Book>  blist=bd.getGoodTop();
		    StringBuilder str=new StringBuilder();
		    
		    
		    
		    if(blist!=null&&blist.size()>0)
		    {
		    	
		    	
		    	for(int i=0;i<blist.size();i++)
			    	{
			    		
		    			
			    		Book b=blist.get(i);
			    		
			    		 String introduce=null;
			    		 
			    	      
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
						.append("\"bookimages\":\""+b.getBookimages() +"\"")
						.append(",")
						.append("\"booktitle\":\""+b.getBooktitle()+"\"")
						.append(",")
					   .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
					   .append(",")
					   .append("\"borrownum\":\""+b.getBorrownum()+"\"")
					   .append(",")
					   .append("\"author\":\""+b.getAuthor()+"\"")
					   .append(",")
					   .append("\"typename\":\""+b.getCategory().getName()+"\"")
					   .append(",")
					   .append("\"bookintroduce\":\""+introduce+"\"")
					   .append(",")
					   .append("\"score\":\""+b.getEvaluatescore()+"\"")
						.append("}").append(",");
			    		
			    	
			    		
			    		
			    	}
		    
		
		    	   out.write("["+str.substring(0,str.length()-1)+"]");
		    	
		    }
		  
		  }
		  else
		  {
			  //获取所有
			   List<Book>  blist=bd.getGoodTopAll();
			    StringBuilder str=new StringBuilder();
			    
			    
			    
			    if(blist!=null&&blist.size()>0)
			    {
			    	
			    	
			    	for(int i=0;i<blist.size();i++)
				    	{
				    		
			    			
				    		Book b=blist.get(i);
				    		
				    		 String introduce=null;
				    		 
				    	      
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
							.append("\"bookimages\":\""+b.getBookimages() +"\"")
							.append(",")
							.append("\"booktitle\":\""+b.getBooktitle()+"\"")
							.append(",")
						   .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
						   .append(",")
						   .append("\"borrownum\":\""+b.getBorrownum()+"\"")
						   .append(",")
						   .append("\"author\":\""+b.getAuthor()+"\"")
						   .append(",")
						   .append("\"typename\":\""+b.getCategory().getName()+"\"")
						   .append(",")
						   .append("\"bookintroduce\":\""+introduce+"\"")
						   .append(",")
						   .append("\"score\":\""+b.getEvaluatescore()+"\"")
							.append("}").append(",");
				    		
				    	
				    		
				    		
				    	}
			    
			
			    	   out.write("["+str.substring(0,str.length()-1)+"]");
			    	
			    }
			  
			  
			  
			  
			  
			  
			  
		  }
		
		
		
		
		
		
	}

}
