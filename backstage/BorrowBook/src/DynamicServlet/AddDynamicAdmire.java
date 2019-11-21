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
import IndexPageDao.AllDynamicPageUserDao;
import dao.UserDao;
import entity.Dynamic;
import entity.DynamicAdmire;
import entity.User;


@WebServlet("/AddDynamicAdmire")
public class AddDynamicAdmire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddDynamicAdmire() {
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
           String dynamicid=request.getParameter("dynamicid");
           String action=request.getParameter("action");
           String  pc=request.getParameter("dypagenum");
          
           DynamicAdmire da=new DynamicAdmire();
          
           UserDao ud=new UserDao();
           User u=ud.getUserbyid2(userid);
           
           
          
           DynamicDao dd=new DynamicDao();
           Dynamic d1=dd.getDynamicByid2(Integer.parseInt(dynamicid));
          
           
           if(action.equals("add"))
           {
        	   da.setDynamic(d1);
               da.setUser(u);
               dd.SaveDynamicAdmire(da);
        	   
           }
           else
           {
        	   
        	 dd.DeleteDynamicAdmire(userid,Integer.parseInt(dynamicid));
        	   
           }
           
           
         
          
	}
	
	
	
	
	

}
