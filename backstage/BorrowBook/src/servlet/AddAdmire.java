package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdmireDao;
import dao.CommentDao;
import dao.ReplyDao;
import dao.UserDao;
import entity.Admire;
import entity.Comment;
import entity.Reply;
import entity.User;
import javax.servlet.annotation.WebServlet;






/**
 * Servlet implementation class AddAdmire
 */
@WebServlet("/AddAdmire")
public class AddAdmire extends HttpServlet {


	public AddAdmire() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		   doPost(request,response);

		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json;charset=utf-8"); 
		  request.setCharacterEncoding("utf-8");
		  
		  PrintWriter  out=response.getWriter();
		  
		  
		  String userid=request.getParameter("userid");
		  String   commentid=request.getParameter("commentid");
		  
		  
		  AdmireDao ad=new AdmireDao();
		  Admire a=new Admire();
		  
		  CommentDao cdd=new CommentDao();
		  Comment comment=cdd.getCommentByid(Integer.parseInt(commentid));
		  
		  a.setUserid(userid);
		  a.setComment(comment);
		  ad.addAdmire(a);
		  
		  String bookid=request.getParameter("bookid");
			
		  
		    CommentDao cd=new CommentDao();
		    UserDao ud=new UserDao();
		    ReplyDao rd=new ReplyDao();
		
		    
		    List<Comment>  blist=cd.getComentbyBookid(bookid);
		    StringBuilder str=new StringBuilder();
		    
		    
		    if(blist!=null&&blist.size()>0)
		       {
		    	   
		    	   
		    	   for(int i=blist.size()-1;i>=0;i--)
		    	   {
		    		   Comment c=blist.get(i);
		    		   
		    		   
		    		   
		    	List<Reply> drlist=rd.getRelybycommentid(c.getCommentid());
		    		    
		    		    StringBuilder str2 =new StringBuilder();
		    		    
		    		          String replylist=null;
		    		    
		    		    		  if(drlist!=null&&drlist.size()>0)
		    		    		  {
		    		    			  
		    		    			 
		    		    			  
		    		    			  for(int j=0;j<drlist.size();j++)
		    		    			  {
		    		    				  
		    		    				  
		    		    				  Reply dr=drlist.get(j);
		    		    				  
		    		    				  User u2= ud.getUserbyid(dr.getUserid());
		    		    				  
		    		    				str2.append("{").append("\"replyid\":\""+dr.getReplyid()+"\"")
		    		  					.append(",")
		    		  					.append("\"replycontent\":\""+dr.getReplycontent()+"\"")
		    		  					.append(",")
		    		  					.append("\"userid\":\""+dr.getUserid()+"\"")
		    		  					.append(",")
		    		  				   .append("\"userimages\":\""+u2.getUserimages()+"\"")
		    		  				   .append(",")
		    		  				   .append("\"nickname\":\""+u2.getNickname()+"\"")
		    		  				  
		    		  				   .append(",")
		    		  				   .append("\"date\":\""+dr.getReplydate()+"\"")
		    		  				 .append(",")
		    		  				   .append("\"commentid\":\""+dr.getComment().getCommentid()+"\"")
		    		  				 .append(",")
		    		  				   .append("\"bookidid\":\""+dr.getBookid()+"\"")
		    		  				   
		    		  				   .append("}").append(",");
		    		    				  
		    		    			  }
		    		    			  
		    		    			  replylist=str2.substring(0,str2.length()-1).toString();
		    		    			  
		    		    		  }
		    		    		  else
		    		    		  {
		    		    			  
		    		    			  replylist="{"+"\"size\":"+"\""+0+"\""+"}";
		    		    		  }	   
		    		   
		    		   
		    		   
		    		   
		    		   
		    		      User u= ud.getUserbyid(c.getUserid());
					      String userimages=u.getUserimages();
					      Object  admirenum=cd.getAdmirenum(c.getCommentid());
		
					       boolean admirebol=ad.getAdmirebyuserid(userid, c.getCommentid());
					      
					  
		    			str.append("{").append("\"commentid\":\""+c.getCommentid()+"\"")
						.append(",")
						.append("\"bookid\":\""+c.getBook().getBookid()+"\"")
						.append(",")
						.append("\"userid\":\""+c.getUserid()+"\"")
						.append(",")
					   .append("\"content\":\""+c.getContent()+"\"")
					   .append(",")
					   .append("\"nickname\":\""+c.getNickname()+"\"")
					   .append(",")
					   .append("\"commentdate\":\""+c.getCommentdate()+"\"")
					   .append(",")
					   .append("\"userimages\":\""+userimages+"\"")
					
					   .append(",")
					   .append("\"admirenum\":\""+admirenum+"\"")
					   .append(",")
					   .append("\"admirebol\":\""+admirebol+"\"")
					   .append(",")
					   .append("\"evaluate\":\""+c.getEvaluate()+"\"")
					   .append(",")
					   .append("\"Evaluatestatus\":\""+c.getEvaluatestatus()+"\"")
					   .append(",")
					   .append("\"replylist\":"+"["+replylist+"]"+"")
					   .append("}").append(",");
		    		   
		    		   
		    		 }
		    	   
		    	   
		    		 out.write("["+str.subSequence(0,str.length()-1)+"]");
		    	   
		    }
		       else
		       {
		    	   
		    	   
		    		 out.write("{"+"\"size\":"+"\""+0+"\""+"}");
		       }
		
		  
		  
		  
		 
	
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
