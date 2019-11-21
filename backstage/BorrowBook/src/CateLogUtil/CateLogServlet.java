package CateLogUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CateLogServlet")
public class CateLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public CateLogServlet() {
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
              getCateLogDate b=new getCateLogDate();
              List<CateList>  clist=b.getCateList(bookid);
              StringBuilder str=new StringBuilder();
          
              
              if(clist!=null&&clist.size()>0)
              {
            	  for(int i=0;i<clist.size();i++)
            	  {
            		  
            		  
            		  CateList cl=clist.get(i);
            		  
            		  str.append("{").append("\"chapternum\":\""+cl.getTitle()+"\"")
        				.append(",")
        				.append("\"start\":\""+cl.getStart()+"\"")
        				.append(",")
        				.append("\"end\":\""+cl.getEnd()+"\"")
        				.append(",")
        			    .append("\"content\":\""+cl.getContent().replaceAll("\r\n", "\\r\\n")+"\"")
        			    .append("}").append(",");	  
            		  
            		  
            		  
            	  }
            	  
            	  out.write("["+str.substring(0,str.length()-1)+"]");
                 
             }
              
              
              
              
            
            
	}

}
