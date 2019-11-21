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
import dao.CategoryDao;
import entity.Book;
import entity.Category;

@WebServlet("/indexServlet")
public class indexServlet extends HttpServlet {

	
	public indexServlet() {
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
		  
		  
		  
		  
			CategoryDao cd=new CategoryDao();
			 BookDao bd=new BookDao();
			
			  List<Category>  clist= cd.getAllCategory();
			  StringBuilder str=new StringBuilder();
			  
			  if(clist!=null&&clist.size()>0)
			  {
				  
				  for(int i=0;i<clist.size();i++)
				  {
					 Category c=clist.get(i);
				
	str.append("{").append("\"type\":"+"\""+c.getName()+"\""+","+"\"typeid\":"+"\""+c.getCategoryid()+"\""+","+"\"img\":"+"\""+c.getTypeclass()+"\""+","+"\"book\":[");  
	   
	                List<Book>  blist=bd.getBookbytypeid(c.getCategoryid());
	  
	                 if(blist!=null&blist.size()>0)
	                 {
	             
	               
	                	 if(blist.size()-1>=7)
	    				 {
	    				 
	    					for(int j=blist.size()-1;j>=blist.size()-1-7;j--)
	    					{
	    						
	    						Book b=blist.get(j);
	    						
	    						
	    						
	    						if(j==blist.size()-1-7)
	    						{
	    					
	    							str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
	    							.append(",")
	    							.append("\"bookimages\":\""+b.getBookimages() +"\"")
	    							.append(",")
	    							.append("\"booktitle\":\""+b.getBooktitle()+"\"")
	    							.append(",")
	    					       .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
	    							.append("}");
	    						}
	    						else
	    						{
	    							str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
	    							.append(",")
	    							.append("\"bookimages\":\""+b.getBookimages() +"\"")
	    							.append(",")
	    							.append("\"booktitle\":\""+b.getBooktitle()+"\"")
	    							.append(",")
	    					       .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
	    							.append("}").append(",");
	    							
	    							
	    						}
	    						
	    						
	    			       	}
	    					
	    					str.append("]},");
	    				
	    				 }
	    				 else
	    				 {
	    					 
	    						for(int j=blist.size()-1;j>=0;j--)
	    						{
	    							
	    							Book b=blist.get(j);
	    							
	    						
	    							if(j==0)
	        						{
	        					
	        							str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
	        							.append(",")
	        							.append("\"bookimages\":\""+b.getBookimages() +"\"")
	        							.append(",")
	        							.append("\"booktitle\":\""+b.getBooktitle()+"\"")
	        							.append(",")
	        					       .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
	        							.append("}");
	        						}
	        						else
	        						{
	        							str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
	        							.append(",")
	        							.append("\"bookimages\":\""+b.getBookimages() +"\"")
	        							.append(",")
	        							.append("\"booktitle\":\""+b.getBooktitle()+"\"")
	        							.append(",")
	        					       .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
	        							.append("}").append(",");
	        							
	        							
	        						}
	    				       	}
	    						
	    						str.append("]},");
	    			
	    					 
	    				 }
	               
	                	 
	                 
	                 
	                 
	    	         }//内层if
	                	 
	               }//外层for
					  
				  String json=str.substring(0,str.length()-1);
				  System.out.println("["+json+"]");
				  out.write("["+json+"]");
					  
				  }//最外层if
		  
		
	
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
