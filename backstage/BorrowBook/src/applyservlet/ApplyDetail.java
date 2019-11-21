package applyservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplyNearDao;
import entity.ApplyNear;


@WebServlet("/ApplyDetail")
public class ApplyDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ApplyDetail() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
		  PrintWriter out=response.getWriter();
		  
		  
		  String id=request.getParameter("id");
		  System.out.println(id);
		  
		  ApplyNearDao and=new ApplyNearDao();
		  
		  
		  
		  
		  
		  ApplyNear  an=and.getApplynearbyid(Integer.parseInt(id));
		  
		  an.setId(Integer.parseInt(id));
		  an.setDostatus(2);;
		  and.UpateDoStatus(an);
		  
		  
		  
		  StringBuilder str=new StringBuilder();
		  
		  if(an!=null)
		  {
			
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
						.append("\"booktitle\":\""+an.getBorrowtable().getBookson().getBook().getBooktitle()+"\"")
						.append(",")
						.append("\"personQR\":\""+an.getPersonQR()+"\"")
					    .append("}");
			
			  
				 out.write(str.toString());
				 System.out.println(str);
			  
			  
		  }
		  else
		  {
			  
			  out.write("0"); 
		
		  }
		  
		
		       
	}

}
