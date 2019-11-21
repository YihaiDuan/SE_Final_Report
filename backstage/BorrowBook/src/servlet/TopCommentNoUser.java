package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import dao.ReplyDao;
import dao.UserDao;
import entity.Comment;
import entity.Reply;
import entity.User;


@WebServlet("/TopCommentNoUser")
public class TopCommentNoUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public TopCommentNoUser() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      
		  PrintWriter out=response.getWriter() ;
		  
		  String bookid=request.getParameter("bookid");
		  
		    CommentDao cd=new CommentDao();
		    UserDao ud=new UserDao();
		    
		    List<Comment>  blist=cd.getTopCommentByid(bookid);
		    StringBuilder str=new StringBuilder();
		    ReplyDao rd=new ReplyDao();
		    
		       if(blist!=null&&blist.size()>0)
		       {
		    	   
		    	   
		    	   for(int i=0;i<blist.size();i++)
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
				
		    		   
		    		   
		    			str.append("{").append("\"commentid\":\""+c.getCommentid()+"\"")
						.append(",")
						.append("\"bookid\":\""+c.getBook().getBookid() +"\"")
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
					   .append("\"admirebol\":\""+false+"\"")
					   .append(",")
					   .append("\"evaluate\":\""+c.getEvaluate()+"\"")
					   .append(",")
					   .append("\"Evaluatestatus\":\""+c.getEvaluatestatus()+"\"")
					   .append(",")
					   .append("\"replylist\":"+"["+replylist+"]"+"")
					   .append("}").append(",");
		    		   
		    		   
		    		   
		    		   
		    		   
		    	   }//for
		    	   
		    	   
		    		 out.write("["+str.subSequence(0,str.length()-1)+"]");
		    	   
		    }//id
		       else
		       {
		    	   
		    	   
		    		 out.write("{"+"\"size\":"+"\""+0+"\""+"}");
		       }
	}

}
