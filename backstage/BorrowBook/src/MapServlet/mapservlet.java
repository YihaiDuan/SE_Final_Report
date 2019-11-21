package MapServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import dao.UserDao;
import entity.BorrowTable;
import entity.User;


@WebServlet("/mapservlet")
public class mapservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public mapservlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		     doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	     PrintWriter out = response.getWriter();
	     
        
        StringBuilder str=new StringBuilder();
        UserDao d=new UserDao();
		List<User> ulist=d.getAllUser();
		
		if(ulist!=null&&ulist.size()>0)
		{
		for(int i=0;i<ulist.size();i++)
		{
			User u=ulist.get(i);
			String userid=u.getUserid();
		BorrowTableDao btd=new BorrowTableDao();	
		List<BorrowTable> btlist=btd.getBookBorrowData(userid);
		int borrownum=btlist.size();
		
		if(borrownum>0)
		{
			
			str.append("{").append("\"id\":\""+userid+"\"")
			.append(",")
			.append("\"latitude\":\""+u.getLatitude()+"\"")
			.append(",")
			.append("\"longitude\":\""+u.getLongitude()+"\"")
			.append(",")
		   .append("\"width\":\""+50+"\"")
		   .append(",")
		   .append("\"height\":\""+50+"\"")
		   .append(",")
		   .append("\"title\":\""+borrownum+"本可借"+"\"")
		  .append("},");
			/* .append(",")
			 .append("\"callout\":{"+"\"content\":"+"\""+borrownum+"本可借"+"\","+"\"display\":"+"\"ALWAYS\",")
			 .append("\"color\":"+"\"#fff\","+"\"borderRadius\":"+"\"20rpx\","+"\"bgColor\":"+"\"#fe5701\",")
			 .append("\"fontSize\":"+"\"13rpx\"}")*/
			
		}
		}
		out.write("["+str.substring(0,str.length()-1)+"]");
	}
		
	
	
	}

}
