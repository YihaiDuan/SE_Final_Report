package BorrowReturnServlet;

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


@WebServlet("/ScanGroupInf")
public class ScanGroupInf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ScanGroupInf() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      PrintWriter out = response.getWriter();
		
	      String userid=request.getParameter("userId");
		
		
	      
	      BorrowTableDao btd=new BorrowTableDao();
		     List<BorrowTable> btlist=btd.getAllGroupBorrow(userid);
		      StringBuilder str=new StringBuilder();
		      String json=null;
		      
		      if(btlist!=null&&btlist.size()>0)
		      {
		    	  for(int i=0;i<btlist.size();i++)
		    	  {
		    		  
		    	   BorrowTable bt=btlist.get(i);	  
		    		  
		    		str.append("{").append("\"id\":\""+bt.getId()+"\"")
					.append(",")
					.append("\"bookid\":\""+bt.getBookson().getBook().getBookid() +"\"")  
					.append(",")
					.append("\"booktitle\":\""+bt.getBookson().getBook().getBooktitle()+"\"")
					.append(",")
					.append("\"bookimages\":\""+bt.getBookson().getBook().getBookimages()+"\"")
					.append(",")
					.append("\"author\":\""+bt.getBookson().getBook().getAuthor()+"\"")
					.append(",")
					.append("\"publish\":\""+bt.getBookson().getBook().getPublish()+"\"")
					.append(",")
				   .append("\"borrowdate\":\""+bt.getBorrowdate()+"\"")
				   .append(",")
				   .append("\"deadline\":\""+bt.getDeadline()+"\"")
				   .append(",")
				   .append("\"booksonid\":\""+bt.getBookson().getBooksonid()+"\"")
				   .append(",")
				   .append("\"isbn\":\""+bt.getBookson().getBook().getPublishnumber()+"\"")
				   .append(",")
				   .append("\"cash\":\""+bt.getBookson().getBook().getCash()+"\"")
				   .append("}").append(",");		
		    	   
		    		  
		    	  }
		    	  
		    	  UserDao ud=new UserDao();
			       User u=ud.getUserbyid(userid);
			        
			        
json="fb_groupp"+"{"+"\"userid\":"+"\""+userid+"\","+"\"nickname\":"+"\""+u.getNickname()+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}";   	  
		    	 
		      }
	}

}
