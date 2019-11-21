package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReferTableDao;
import entity.ReferTable;


@WebServlet("/ReferMessageBol")
public class ReferMessageBol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ReferMessageBol() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    
		   doPost(request,response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		     
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
	      
	      
	       String userid=request.getParameter("userid");
	      
	      
	      ReferTableDao rtd=new ReferTableDao();
	        
	       List<ReferTable>   rlist=  rtd.getReferTableLook(userid);
		
	       
	       
	       if(rlist!=null)
	       {
	    	   
	    	   
	    	   out.write("0");
	    	   
	       }
	       else
	       {
	    	   
	    	   out.write("1");
	    	   
	       }
		
	}
public static void main(String []  args)
{
	String userid="123";
    
    
    ReferTableDao rtd=new ReferTableDao();
      
     List<ReferTable>   rlist=  rtd.getReferTableLook(userid);

     if(rlist!=null)
     {
    	 
    	 System.out.println("有");
     }
}
}
