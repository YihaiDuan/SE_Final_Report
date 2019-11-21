package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.DiscountDao;
import entity.Discount;


@WebServlet("/getDiscountScoreByid")
public class getDiscountScoreByid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getDiscountScoreByid() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		
		   doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		 
	      PrintWriter out=response.getWriter() ;
		DiscountDao dcd=new DiscountDao();
		
		String countid=request.getParameter("countid");
		
		Discount  dis=dcd.getDisCountByid(Integer.parseInt(countid));
		
		StringBuilder str=new StringBuilder();
		
		if(dis!=null)
		{
			
				
				str.append("{").append("\"id\":\""+dis.getId()+"\"")
					.append(",")
					.append("\"typename\":\""+dis.getTypename() +"\"")
					.append(",")
					.append("\"discount\":\""+dis.getDiscount()+"\"")
					.append("}");		
				
	         out.write(str.toString());
			
		}
		
	

	}

}
