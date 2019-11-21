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
import Extenddao2.ActivityDao;
import entity.Discount;


@WebServlet("/getDiscountUser")
public class getDiscountUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public getDiscountUser() {
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
	    
	      
	      
		   ActivityDao ad=new ActivityDao();
		   
		   List<Discount>  dlist=ad.getAllDiscount();
		   StringBuilder str =new StringBuilder();
		   
		   if(dlist!=null&&dlist.size()>0)
		   {
			   
			  for(int i=dlist.size()-1;i>=0;i--)
			  {
				  
				  Discount d=dlist.get(i);
				  
				  DiscountUserDao  dsu=new DiscountUserDao();
				  
				  boolean discountbol=dsu.BolDiscount(userid,d.getId());
		  	         
		            str.append("{").append("\"id\":\""+d.getId()+"\"")
					.append(",")
					.append("\"num\":\""+d.getNum() +"\"")
					.append(",")
					.append("\"createdate\":\""+d.getCreatedate()+"\"")
					.append(",")
				   .append("\"deadline\":\""+d.getDeadline()+"\"")
				   .append(",")
				   .append("\"discount\":\""+d.getDiscount()+"\"")
				   .append(",")
				   .append("\"showstatus\":\""+d.getShowstatus()+"\"")
				   .append(",")
				   .append("\"typename\":\""+d.getTypename()+"\"")
				   .append(",")
				   .append("\"grade\":\""+d.getGradestatus()+"\"")
				   .append(",")
				   .append("\"discountbol\":\""+discountbol+"\"")
				   .append(",")
				   .append("\"typestatus\":\""+d.getTypestatus()+"\"")
				   
					.append("}").append(",");
		    		   
				  
				  
				  
			  }
			   
			     out.write("["+str.substring(0,str.length()-1)+"]"); 
			     
			     System.out.println(1);
		   }  
		   
		   
		
	}

}
