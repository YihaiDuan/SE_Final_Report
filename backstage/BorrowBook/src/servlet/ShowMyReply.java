package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BookDao;
import dao.CommentDao;
import dao.ReplyDao;
import dao.UserDao;
import entity.Book;
import entity.Comment;
import entity.Reply;
import entity.User;
import javax.servlet.annotation.WebServlet;



@WebServlet("/ShowMyReply")
public class ShowMyReply extends HttpServlet {


	public ShowMyReply() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		         doPost(request,response);

		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
			{

		
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
	       
	       
	       String userid=request.getParameter("userid");
	       ReplyDao rd=new ReplyDao();
	       UserDao ud=new UserDao();
	       CommentDao cd=new CommentDao();
	       
	         
	       List<Reply>  rlist=rd.getReplyEachByreplyid(userid);
	       StringBuilder str=new StringBuilder();
		      
	       
	       if(rlist!=null&&rlist.size()>0)
	       {
	    	   
	    	   for(int i=rlist.size()-1;i>=0;i--)
	    	   {
	    		   
	    		   Reply r=rlist.get(i);
	    		   
	    		   User u= ud.getUserbyid(r.getUserid());
				   String userimages=u.getUserimages();
				  
				   
			Comment c=cd.getCommentByid(r.getComment().getCommentid());
				   String maincontent=c.getContent();
				  
	        		  
	        			str.append("{").append("\"replyid\":\""+r.getReplyid()+"\"")
						.append(",")
						.append("\"bookid\":\""+r.getBookid() +"\"")
						.append(",")
						.append("\"userid\":\""+r.getUserid()+"\"")
						.append(",")
					   .append("\"content\":\""+r.getReplycontent()+"\"")
					   .append(",")
					   .append("\"nickname\":\""+r.getNickname()+"\"")
					   .append(",")
					   .append("\"date\":\""+r.getReplydate()+"\"")
					   .append(",")
					   .append("\"userimages\":\""+userimages+"\"")
					   .append(",")
					   .append("\"maincontent\":\""+maincontent+"\"")
					   .append(",")
					   .append("\"commentid\":\""+r.getComment().getCommentid()+"\"")
					    .append("}").append(",");
	    		 
	    		   
	    	   }
	    	   
	    	   
	    	   out.write("["+str.subSequence(0,str.length()-1)+"]");
	    	   
	
	       }
	    
	       
	   
	        }


	public void init() throws ServletException {
		
	}

}
