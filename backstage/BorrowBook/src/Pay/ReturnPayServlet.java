package Pay;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;


@WebServlet("/ReturnPayServlet")
public class ReturnPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ReturnPayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		   doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		 response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   String userid=request.getParameter("userid");
		   String cash=request.getParameter("cash");
		   
		   
		   System.out.println(cash);
		   
		    UserDao ud=new UserDao();
		       User u=ud.getUserbyid(userid);
		       
		       String oldmoney=u.getMoney();
		       
		       Double newmoney=Double.parseDouble(oldmoney)-Double.parseDouble(cash);
		      
		       
		       //更新用户钱包
		        u.setMoney(newmoney.toString());
		        
		        ud.UpdatePersonMoney(u);
		   
		   
		   
		   
		   
		   
		   
		
	}

}
