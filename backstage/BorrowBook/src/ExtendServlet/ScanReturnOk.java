package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import entity.BorrowTable;


@WebServlet("/ScanReturnOk")
public class ScanReturnOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ScanReturnOk() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		       doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
		 response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		   
		   String id=request.getParameter("id");
		   
		   
		   String ids[]=id.split(",");
		   
		   
		   System.out.println(ids[0]);
		   
		   
		   
		   
		   BorrowTableDao btd=new BorrowTableDao();
		   
		   
		  BorrowTable bt= btd.getBorrowbyId(Integer.parseInt(ids[0]));
		   
		       if(bt!=null)
		       {
		    	   
		    	   
		    	   //管理员点击了归还
		    	   if(bt.getStatus()==1)
		    	   {
		    		   
		    		   
		    		   out.write("{"+"\"bol\":"+"\""+true+"\""+"}");
		    		   
		    		   
		    	   }
		    	   else
		    	   {
		    		   
		    		   out.write("{"+"\"bol\":"+"\""+false+"\""+"}");
		    	   }
		    	   
		    	 }
		   
	}

}
