package DynamicServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.DynamicDao;
import dao.UserDao;
import entity.Dynamic;
import entity.User;


@WebServlet("/getUserInf")
public class getUserInf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public getUserInf() {
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
         
          
          
          UserDao ud=new UserDao();
          User u=ud.getUserbyid(userid);
          StringBuilder str=new StringBuilder();
          
          DynamicDao dd=new DynamicDao();
          
          if(u!=null)
          {
        	  
        	  //关注数
        	  int fannum=Integer.parseInt(dd.getFanNum(userid).toString());
        	  
        	  //粉丝数
        	  int fansnum=Integer.parseInt(dd.getFansNum(userid).toString());
        	  
        	  //动态数
        	  List<Dynamic> dlist=dd.getDynamicByUserid(userid);
        	  
        	  int dynamicnum=0;
        	  
        	  if(dlist!=null&&dlist.size()>0)
        	  {
        		  dynamicnum=dlist.size();
        		  
        	  }
        	  
        	 str.append("{").append("\"userid\":\""+u.getUserid()+"\"")
				.append(",")
				.append("\"userimages\":\""+u.getUserimages()+"\"")
				.append(",")
				.append("\"fannum\":\""+fannum+"\"")
				.append(",")
				.append("\"fansnum\":\""+fansnum+"\"")
				.append(",")
				.append("\"dynamicnum\":\""+dynamicnum+"\"")
				.append(",")
				.append("\"nickname\":\""+u.getNickname()+"\"")
				.append(",")
				.append("\"sex\":\""+u.getSex()+"\"")
				.append(",")
				.append("\"phone\":\""+u.getPhone()+"\"")
				.append(",")
				.append("\"e_mail\":\""+u.getE_mail()+"\"")
				
			   .append("}");
        	  
        	    out.write(str.toString());
          }
          
          
          
	}

}
