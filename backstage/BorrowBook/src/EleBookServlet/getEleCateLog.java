package EleBookServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CateLogUtil.CateList;
import CateLogUtil.getCateLogDate;


@WebServlet("/getEleCateLog")
public class getEleCateLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public getEleCateLog() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		      doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		
		   String bookid=request.getParameter("bookid"); 
		   getCateLogDate gcd=new getCateLogDate();
		   List<CateList>  clist=gcd.getCateList(bookid);
		     
		   StringBuilder str=new StringBuilder();
		       if(clist!=null&&clist.size()>0)
		       {
		    	   
		    	   for(int i=0;i<clist.size();i++)
		    	   {
		    		   
		    		   
		    		   CateList c=clist.get(i);
		    		   str.append("{").append("\"catename\":\""+c.getTitle()+"\"")
		    		    .append(",")
		    			.append("\"start\":\""+c.getStart() +"\"")
					   .append("}").append(",");
		    		   
		    		   
		    		   
		    	   }
		    	   
		    	   out.write("["+str.substring(0,str.length()-1)+"]"); 
		    	   
		       }
		     
		   
		   
		        
	}

}
