package MapServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;


@WebServlet("/getLocation")
public class getLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getLocation() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
				doPost(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	     PrintWriter out = response.getWriter();
	     
	     
	     String userid=request.getParameter("userid");
      
         
          UserDao d=new UserDao();
		
          User u=d.getUserbyid(userid);
          
          
          out.write("{"+"\"longitude\":"+"\""+u.getLongitude()+"\","+"\"latitude\":"+"\""+u.getLatitude()+"\""+"}");
          
		
	}

}
