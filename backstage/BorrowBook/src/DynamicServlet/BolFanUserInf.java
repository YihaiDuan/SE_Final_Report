package DynamicServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.FanDao;


@WebServlet("/BolFanUserInf")
public class BolFanUserInf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BolFanUserInf() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		

		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		    
		
		  response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
          PrintWriter out=response.getWriter() ;
          
          
            String userid=request.getParameter("userid");
            String otherid=request.getParameter("otherid");
		
		     FanDao fd=new FanDao();
		    int fanbol=fd.FanBol(userid, otherid);
		     out.write("{"+"\"fanbol\":"+"\""+fanbol+"\""+"}");
		 
		 
	}

}
