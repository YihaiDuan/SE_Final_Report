package DynamicServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.FanDao;
import dao.UserDao;
import entity.Fan;
import entity.User;


@WebServlet("/myFan")
public class myFan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public myFan() {
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
		  
		  
		    FanDao fd=new FanDao();
		  
		   List<Fan>  flist=fd.getFanByUserid(userid);
		   StringBuilder str=new StringBuilder();
		   
		   
		   if(flist!=null&&flist.size()>0)
		   {
			   
			   for(int i=flist.size()-1;i>=0;i--)
			   {
				   
				   Fan f=flist.get(i);
				
				   
				   str.append("{").append("\"userid\":\""+f.getUser().getUserid()+"\"")
					.append(",")
					.append("\"userimages\":\""+f.getUser().getUserimages()+"\"")
					.append(",")
					.append("\"nickname\":\""+f.getUser().getNickname()+"\"")
					.append("}").append(",");
				   
			   }
			 out.write("["+str.substring(0, str.length()-1)+"]");
		   }
		 
		
	}

}
