/*package IndexPageServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IndexPageDao.TopicPageDao;
import dao.BookPageDao;
import entity.Book;
import entity.Topic;
import util.JsonFormat;


@WebServlet("/TopicPageServlet")
public class TopicPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public TopicPageServlet() {
        super();
       
    }

    
  
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		        doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		
		   String pc=request.getParameter("topicpagenum");
		 
		 System.out.println(".............."+pc);
		
		  TopicPageDao tpd=new TopicPageDao();
		  entity.Page<Topic>  pageBean=tpd.getTopicData(Integer.parseInt(pc));
		  
		
		
		     List<Topic> blist= pageBean.getBeanlist();
			StringBuilder str=new StringBuilder();
			
			if(blist!=null&&blist.size()>0)
			{
				for(int i=0;i<blist.size();i++)
				{
					Topic b=blist.get(i);
					JsonFormat jf=new JsonFormat();
					
					String topicname=null;
					
					if(b.getTopicname()!=null)
					{
						
						topicname=jf.stringToJson(b.getTopicname());
					}
					else
					{
						
						topicname="未填";
						
					}
					
				System.out.println(b.getDescrib());
			   
				str.append("{").append("\"topicimg\":\""+b.getTopicimg()+"\"")
				.append(",")
				.append("\"topicid\":\""+b.getId()+"\"")
				.append(",")
				.append("\"topicname\":\""+topicname+"\"")
				.append(",")
				.append("\"id\":\""+b.getId()+"\"")
				.append("}").append(",");
					
					
				}//for
out.write("{"+"\"maxsize\":"+"\""+pageBean.getAll()+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");
			
			
			}//if
		
			
}
	

}
*/