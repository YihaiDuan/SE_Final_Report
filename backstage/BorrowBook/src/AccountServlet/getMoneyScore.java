package AccountServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;


@WebServlet("/getMoneyScore")
public class getMoneyScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getMoneyScore() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		 response.setContentType("text/html;charset=UTF-8");
	       request.setCharacterEncoding("UTF-8");
	       
	       PrintWriter out=response.getWriter();
	       
	       
	       String userid=request.getParameter("userid");
	       
	       UserDao ud=new UserDao();
	       User u= ud.getUserbyid(userid);
	       
	       out.write("{"+"\"money\":"+"\""+u.getMoney()+"\","+"\"score\":"+"\""+u.getScore()+"\""+"}");
	       
		
	}

}
