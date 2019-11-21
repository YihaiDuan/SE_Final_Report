package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.TopicDao;
import entity.Topic;


@WebServlet("/getTopicByidServlet")
public class getTopicByidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public getTopicByidServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		// TODO Auto-generated method stub
				doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		     
		
		     response.setContentType("text/html;charset=utf-8");
		     request.setCharacterEncoding("utf-8");
		     
		     PrintWriter out=response.getWriter();
		     
		     
		     String id=request.getParameter("id");
		     
		     TopicDao tod=new TopicDao();
		     
		     Topic to=tod.getTopicByid(Integer.parseInt(id));
		     StringBuilder str=new StringBuilder();
		     
		     
		     if(to!=null)
		     {
		    		
		    	 
		    	    str.append("{").append("\"id\":\""+to.getId()+"\"")
					.append(",")
					.append("\"topicimg\":\""+to.getTopicimg() +"\"")
					.append(",")
					.append("\"topicname\":\""+to.getTopicname()+"\"")
					.append(",")
				   .append("\"describ\":\""+to.getDescrib()+"\"")
				  
					.append("}");
		    	    
		    	    
		    	    out.println(str);
		    
		       }
		
		
		
	}

}
