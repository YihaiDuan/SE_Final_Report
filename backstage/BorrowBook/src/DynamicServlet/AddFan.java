package DynamicServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.DynamicAdmireDao;
import Extenddao2.DynamicDao;
import Extenddao2.FanDao;
import dao.UserDao;
import entity.Dynamic;
import entity.Fan;
import entity.User;


@WebServlet("/AddFan")
public class AddFan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddFan() {
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
           String otherid=request.getParameter("otherid");
           String action=request.getParameter("action");
           
           System.out.println(otherid);
           
           
           DynamicDao dd=new DynamicDao();
           
           Fan f=new Fan();
           UserDao ud=new UserDao();
           User u=ud.getUserbyid2(userid);
           
           
       
          
           
           if(action.equals("add"))
           {
        	   
        	    f.setUser(u);
                f.setOtherid(otherid);
                dd.SaveFan(f);
        	   
        	   
           }
           else
           {
        	   
        	   dd.DeleteFan(userid, otherid);
        	   
        	   
           }
           
           
      
       
		
	}

}
