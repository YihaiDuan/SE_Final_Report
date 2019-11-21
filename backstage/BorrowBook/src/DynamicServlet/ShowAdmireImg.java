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
import entity.DynamicAdmire;


@WebServlet("/ShowAdmireImg")
public class ShowAdmireImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowAdmireImg() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		  response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
          PrintWriter out=response.getWriter() ;
          
          
          String dynamicid=request.getParameter("dynamicid");
          
          
          DynamicDao dd=new DynamicDao();
          List<DynamicAdmire> list=dd.getAllAdmireByid(Integer.parseInt(dynamicid));
          StringBuilder str =new StringBuilder();
          
          if(list!=null&&list.size()>0)
          {
        	  
        	  for(int i=list.size()-1;i>=0;i--)
        	  {
        		  
        		  DynamicAdmire da=list.get(i);
        		  
        		  str.append("{").append("\"userid\":\""+da.getUser().getUserid()+"\"")
					.append(",")
					.append("\"userimages\":\""+da.getUser().getUserimages() +"\"")
					
				   .append("}").append(",");
        		  
        		  
        	  }
        	  
        	  
        	  out.write("["+str.substring(0,str.length()-1)+"]");
        	  
          }
         
		
		
		
	}

}
