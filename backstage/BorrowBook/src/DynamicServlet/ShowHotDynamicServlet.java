package DynamicServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.DynamicDao;
import entity.Dynamic;


@WebServlet("/ShowHotDynamicServlet")
public class ShowHotDynamicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowHotDynamicServlet() {
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
	      
	      
	      DynamicDao dd=new DynamicDao();
	      
	      List<Dynamic>  dlist=dd.getHotDynamic();
	      StringBuilder str=new StringBuilder();
	      
	      if(dlist!=null&&dlist.size()>0)
	      {
	    	  
	    	  for(int i=0;i<dlist.size();i++)
	    	  {
	    		  
	    		  
	    		  
	    		  Dynamic dr=dlist.get(i);
	    		  
	    		 String images[]=dr.getImages().split(",");
	    		 
	    		 if(images[0]!=null&&!images[0].isEmpty()&&!images[0].equals("0"))
	    		  
  				str.append("{").append("\"dynamicid\":\""+dr.getDynamicid()+"\"")
					.append(",")
					.append("\"images\":\""+images[0]+"\"")
					.append("}").append(",");  
	    		  
	    		  
	    	  }
	    	  
	    	  out.write("["+str.substring(0,str.length()-1)+"]");
	    	  
	    	  System.out.println("服务端");
	      }
	    
	      
	      
	      
		
		
		
	}

}
