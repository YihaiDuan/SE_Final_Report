package applyservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplyNearDao;


@WebServlet("/getStatus")
public class getStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public getStatus() {
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
		  
		     boolean readstatus=and.getReadStatus(userid);
		     boolean dostatus=and.getDoStatus(userid);
		     
		     
		    out.write("{\"readstatus\":"+"\""+readstatus+"\","+"\"dostatus\":"+"\""+dostatus+"\"}");
		
		
	}

}
