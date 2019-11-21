package EleBookServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EleBookDao.EleBookDao;


@WebServlet("/getEleBookOwnBol")
public class getEleBookOwnBol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getEleBookOwnBol() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	          	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		   String userid= request.getParameter("userid");
		   String bookid=request.getParameter("bookid"); 
		   
		   
		   
           //判断是否已经购买   已经购买可以直接阅读
	       boolean readbol=false;
	       EleBookDao ebd=new EleBookDao();
	       
	       if(ebd.BolOwnEleBook(userid,bookid))
	       {
	    	   
	    	   
	    	   readbol=true;
	       }
	       
	       
	       
	       out.write("{"+"\"readbol\":"+"\""+readbol+"\""+"}");
	       
				
	
	}

}
