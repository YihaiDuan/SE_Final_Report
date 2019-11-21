package ShowAllDataServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.TopicDao;
import entity.Topic;
import util.JsonFormat;


@WebServlet("/AllTopic")
public class AllTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AllTopic() {
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
		   
		   
		   StringBuilder str=new StringBuilder();
		   
		   TopicDao td=new TopicDao();
		   List<Topic>  tlist=td.getAllTopic();
		   
		   if(tlist!=null&&tlist.size()>0)
			  {
				  
				  for(int i=0;i<tlist.size();i++)
				  {
					  
					  
					  
					    Topic to=tlist.get(i);
					    JsonFormat jf=new JsonFormat();
						String topicname=null;
						
						if(to.getTopicname()!=null)
						{
							
							topicname=jf.stringToJson(to.getTopicname());
						}
						else
						{
							
							topicname="未填";
							
						}
						
					  
						str.append("{").append("\"id\":\""+to.getId()+"\"")
						.append(",")
						.append("\"topicimg\":\""+to.getTopicimg() +"\"")
						.append(",")
						.append("\"topicname\":\""+topicname+"\"")
						
						.append("}").append(",");
			    		  
					 }
				  
				  
				   out.write("["+str.substring(0,str.length()-1)+"]");
				  
			  }
			  
		

		   
	
	}

}
