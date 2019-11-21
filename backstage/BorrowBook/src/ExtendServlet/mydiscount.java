package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.DiscountUserDao;
import entity.DiscountUser;


@WebServlet("/mydiscount")
public class mydiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public mydiscount() {
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
	      
	      
	      DiscountUserDao dud=new DiscountUserDao();
	     List<DiscountUser> dlist=dud.getMyDiscount(userid);
	     
	     StringBuilder str=new StringBuilder();
		
		if(dlist!=null&&dlist.size()>0)
		{
			
			for(int i=dlist.size()-1;i>=0;i--)
			{
				
				DiscountUser d=dlist.get(i);
				
				str.append("{").append("\"id\":\""+d.getId()+"\"")
				.append(",")
				.append("\"createdate\":\""+d.getCreatedate()+"\"")
				.append(",")
			   .append("\"deadline\":\""+d.getDeadline()+"\"")
			   .append(",")
			   .append("\"discount\":\""+d.getDiscount().getDiscount()+"\"")
			   .append(",")
			   .append("\"typename\":\""+d.getDiscount().getTypename()+"\"")
			   .append(",")
			   .append("\"typestatus\":\""+d.getDiscount().getTypestatus()+"\"")
			   .append(",")
			   .append("\"status\":\""+d.getStatus()+"\"")
			   .append(",")
			   .append("\"fromstatus\":\""+d.getDiscount().getStatus()+"\"")
			   .append("}").append(",");
	    		   
				
			}
			
		    out.write("["+str.substring(0,str.length()-1)+"]"); 
			
		}
	}

}
