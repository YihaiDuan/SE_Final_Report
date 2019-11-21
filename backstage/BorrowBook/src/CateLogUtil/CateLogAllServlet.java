package CateLogUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CateLogAllServlet")
public class CateLogAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public CateLogAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		  response.setContentType("text/html;charset=utf-8");
          request.setCharacterEncoding("utf-8");
          PrintWriter out=response.getWriter() ;
          String bookid=request.getParameter("bookid");
   
          getCateLogDate b=new getCateLogDate();

          
     //out.write("{"+"\"content\":"+"\""+b.getCateLogData(bookid).replaceAll("\r\n","\\\\r\\\\n").replaceAll("\"","")+"\""+"}");
          out.write(b.getCateLogData(bookid));	
	}

}
