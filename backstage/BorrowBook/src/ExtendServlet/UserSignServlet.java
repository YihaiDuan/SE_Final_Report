package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;
import timer.SignTimer;


@WebServlet("/UserSignServlet")
public class UserSignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UserSignServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		      doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		
		
		   
		   String userid=request.getParameter("userid");
		   
		   
		   UserDao ud=new UserDao();
		   User u=ud.getUserbyid(userid);
		   
		   int oldscore=u.getScore();
		   u.setScore(oldscore+5);
		   
		   ud.UpdatePersonScore(u);
		   
		   
		   
		   u.setSign(1);
		   
		   ud.UpdateSignStatus(u);
		   
		   //00点后可以重新签到
		   
		    
		    SignTimer st=new SignTimer(userid);
		    st.start();
		
	}

}
