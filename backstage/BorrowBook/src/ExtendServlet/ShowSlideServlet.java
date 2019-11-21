package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.SlideDao;

import entity.Slide;


@WebServlet("/ShowSlideServlet")
public class ShowSlideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowSlideServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		         doPost(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
	      
	      SlideDao sd=new SlideDao();
		 
	      List<Slide>  dlist=sd.ShowSlideAll();
		   StringBuilder str =new StringBuilder();
		   
		   if(dlist!=null&&dlist.size()>0)
		   {
			   
			  for(int i=dlist.size()-1;i>=0;i--)
			  {
				  
				    Slide d=dlist.get(i);
				
		  	         
		            str.append("{").append("\"id\":\""+d.getId()+"\"")
					.append(",")
					.append("\"img\":\""+d.getImg() +"\"")
					.append(",")
					.append("\"showstatus\":\""+d.getShowstatus()+"\"")
					.append(",")
				   .append("\"type\":\""+d.getType()+"\"")
				   .append(",")
				   .append("\"url\":\""+d.getUrl()+"\"")
				   
					.append("}").append(",");
		    		   
				  
				  
				  
			  }
			   
			     out.write("["+str.substring(0,str.length()-1)+"]"); 
			     
			   
		   }  
		   
	
		
	}

}
