package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.DiscountDao;
import Extenddao.DiscountUserDao;
import entity.Discount;
import entity.DiscountUser;


@WebServlet("/UpdateDiscount")
public class UpdateDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UpdateDiscount() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		          doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		 
	      PrintWriter out=response.getWriter() ;
	      
	        String id =request.getParameter("id");
	        
	        DiscountUserDao disd=new DiscountUserDao();
	        
	          DiscountUser dis=disd.getDiscountUserByid(Integer.parseInt(id));
	      
	          
	          //更新已经使用标志位
	          if(dis!=null)
	         {
	    	       dis.setStatus(2);
	    	       disd.UpdateDiscountUser(dis);
	          }
	      
	}

}
