package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.GroupMainDao;


@WebServlet("/BolInGroupBol")
public class BolInGroupBol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BolInGroupBol() {
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
		   
		    PrintWriter out=response.getWriter(); 
		    
		    String userid=request.getParameter("userid");
		   String groupmainid=request.getParameter("groupmainid");
		   
		   System.out.println(groupmainid);
		  //判断是否该用户已经开过这个团
		    
		        boolean opengroupbol=false;
		        
		        
				GroupMainDao gmd=new GroupMainDao();
				
                String bookid=gmd.getGroupMainByid(Integer.parseInt(groupmainid)).getBookid();
				
				boolean  bol1=gmd.UserOpenMainGroupBol(userid,bookid);
				boolean  bol2=gmd.UserOpenMemberGroupBol(userid,bookid);
				
			      if(bol1||bol2)
				  {
					  
					 opengroupbol=true;
					  
				  }   
		
	            out.write("{"+"\"openbol\":"+"\""+opengroupbol+"\""+"}");
	
	
	}
	
}
