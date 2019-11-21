package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.GroupMainDao;
import dao.UserDao;
import entity.GroupMain;
import entity.User;


@WebServlet("/ShowGroupMainByBookid")
public class ShowGroupMainByBookid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowGroupMainByBookid() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		  response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   
		   PrintWriter out=response.getWriter();
		   
		   
		   String bookid=request.getParameter("bookid");
		   
		   GroupMainDao gmd=new GroupMainDao();
		   
		   List<GroupMain>  gmlist=gmd.getGroupMainByBookid(bookid);
		   StringBuilder str=new StringBuilder();
		   
		   if(gmlist!=null&&gmlist.size()>0)
		   {
			   for(int i=gmlist.size()-1;i>=0;i--)
			   {
				   GroupMain gm=gmlist.get(i);
				   
				   
				   UserDao ud=new UserDao();
				   User u=ud.getUserbyid(gm.getUserid());
				   
				   
				   //这本书需要几人团
				  String groupnum=gm.getGroupbook().getGroupmore().getGroupnum();
				   
				   
				   //现在拼团的人数
				   Integer opengroupnum=0;
				   opengroupnum=Integer.parseInt(gmd.getNumfromGroupMember(gm.getId()).toString())+1;
				   
				   //剩下多少人
				   int remainnum=Integer.parseInt(groupnum)-opengroupnum;
				   
				   str.append("{").append("\"id\":\""+gm.getId()+"\"")
					.append(",")
					.append("\"userid\":\""+gm.getUserid()+"\"")
					.append(",")
					.append("\"userimages\":\""+u.getUserimages()+"\"")
					.append(",")
				   .append("\"nickname\":\""+u.getNickname()+"\"")
				   .append(",")
				   .append("\"remainnum\":\""+remainnum+"\"")
					.append("}").append(",");
				   
				}
			    
			     out.write("["+str.substring(0,str.length()-1)+"]"); 
			  
		   }
		   else
		   {
			   
			      out.write("0"); 
			   
		   }
		   
		   
		   
		   
		   
		   
		   
		   
		
	}

}
