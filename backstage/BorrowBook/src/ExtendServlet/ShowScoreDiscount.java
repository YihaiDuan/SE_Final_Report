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


@WebServlet("/ShowScoreDiscount")
public class ShowScoreDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowScoreDiscount() {
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
		
		List<Discount>  dlist=dcd.getScoreDiscount();
		
		StringBuilder str=new StringBuilder();
		
		if(dlist!=null&&dlist.size()>0)
		{
			
			for(int i=0;i<dlist.size();i++)
			{
				
				Discount dis=dlist.get(i);
				
				str.append("{").append("\"id\":\""+dis.getId()+"\"")
					.append(",")
					.append("\"typename\":\""+dis.getTypename() +"\"")
					.append(",")
					.append("\"discount\":\""+dis.getDiscount()+"\"")
					.append("},");		
				
			}
			
			out.write("["+str.substring(0,str.length()-1)+"]");
			
		}
		
	}

}
