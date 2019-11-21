package applyservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplyNearDao;
import entity.ApplyNear;
import util.JsonFormat;


@WebServlet("/MyApply")
public class MyApply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MyApply() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
		  PrintWriter out=response.getWriter();
		  
		  String userid=request.getParameter("userid");
		  
          ApplyNearDao and=new ApplyNearDao();
		  
		  List<ApplyNear>  alist=and.getApplyByUserselfid(userid);
		  StringBuilder str=new StringBuilder();
		  
		  if(alist!=null&&alist.size()>0)
		  {
			  for(int i=alist.size()-1;i>=0;i--)
			  {
				  
				  
				  ApplyNear an=alist.get(i);
				  
				  
				  String introduce =null;
					
					if(an.getBorrowtable().getBookson().getBook().getBookimages()!=null)
					{
						
						
						introduce=JsonFormat.stringToJson(an.getBorrowtable().getBookson().getBook().getIntroduce());
					}
					else
					{
						introduce="内容未知";
						
						
					}
				  
				  
				 str.append("{").append("\"id\":\""+an.getId()+"\"")
					.append(",")
					.append("\"userid\":\""+an.getUser().getUserid() +"\"")
					.append(",")
					.append("\"nickname\":\""+an.getUser().getNickname()+"\"")
					.append(",")
					.append("\"userid\":\""+an.getUser().getUserid()+"\"")
					.append(",")
					.append("\"userimages\":\""+an.getUser().getUserimages()+"\"")
					.append(",")
					.append("\"status\":\""+an.getStatus() +"\"")
					  .append(",")
						.append("\"readstatus\":\""+an.getReadstatus()+"\"")
						.append(",")
						.append("\"dostatus\":\""+an.getDostatus()+"\"")
						.append(",")
						.append("\"borrowid\":\""+an.getBorrowtable().getId()+"\"")
						.append(",")
						.append("\"date\":\""+an.getDate()+"\"")
						.append(",")
						.append("\"bookimages\":\""+an.getBorrowtable().getBookson().getBook().getBookimages()+"\"")
						.append(",")
						.append("\"booktitle\":\""+an.getBorrowtable().getBookson().getBook().getBooktitle()+"\"")
						.append(",")
						.append("\"introduce\":\""+introduce+"\"")
						.append("}").append(",");
				    }
			  
			  
				 out.write("["+str.substring(0,str.length()-1)+"]");
				 System.out.println(str);
			  
			  
		  }
		  else
		  {
			  
			  out.write("0"); 
		
		  }
		  
		  
	}

}
