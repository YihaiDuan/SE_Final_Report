package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.CategoryDao;
import entity.Category;
import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class indexCategoryServlet
 */
@WebServlet("/indexCategoryServlet")
public class indexCategoryServlet extends HttpServlet {

	
	public indexCategoryServlet() {
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
	       List<Category>  list=cd.getAllCategory();

	        StringBuilder str=new StringBuilder();
	       
	      
	       if(list!=null&list.size()>0)
	       {
	    	   
	    	  if(list.size()-1>=7)
	    	  {
	    		  
	    		  
	    		  for(int i=0;i<=7;i++)
	    		  {
	    			  
	    			Category c=list.get(i);
	    			  
	    		    str.append("{").append("\"categoryid\":\""+c.getCategoryid()+"\"")
	    	        .append(",")
	    			.append("\"name\":\""+c.getName() +"\"")
	    			
	    			.append(",")
	    			.append("\"img\":\""+c.getTypeclass()+"\"")
	    		     .append("}").append(",");
	    			  
	    			  
	    			  
	    		  }
	    		  
	    		  
	    		 
	    	  }
	    	  else
	    	  {
	    		  for(int i=0;i<list.size();i++)
	    		  {
	    			  
	    			Category c=list.get(i);
	    			 str.append("{").append("\"categoryid\":\""+c.getCategoryid()+"\"")
		    	        .append(",")
		    			.append("\"name\":\""+c.getName() +"\"")
		    			
		    			.append(",")
		    			.append("\"img\":\""+"图片"+"\"")
		    		     .append("}").append(",");
	    			  
	    			  
	    			  
	    		  }
	    		  
	    		  
	    	  }
	    	   
	     
	    	  
	    	       out.write("["+str.substring(0,str.length()-1)+"]");
	    	   
	       }
	      

	
	       
	       
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
