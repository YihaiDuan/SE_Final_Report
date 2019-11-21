package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReferTableDao;
import entity.ReferTable;


@WebServlet("/UpdataReferStatus")
public class UpdataReferStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdataReferStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		  doPost(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
	       
	       
	       String referid=request.getParameter("referid");
	       
	       
	       ReferTableDao rtd=new ReferTableDao();
	       
	         ReferTable r=new ReferTable();
	       
	       
	           r.setId(Integer.parseInt(referid));
	           r.setStatus(1);
	       
	         
	           rtd.UpReferStatus(r);
	       
		
	             out.write("success");
		
		
	}

}
