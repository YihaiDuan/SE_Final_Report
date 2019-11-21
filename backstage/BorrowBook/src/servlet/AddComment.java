package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdmireDao;
import dao.BookDao;
import dao.CommentDao;
import dao.ReplyDao;
import dao.UserDao;
import entity.Book;
import entity.Comment;
import entity.Reply;
import entity.User;
import util.Escape;
import javax.servlet.annotation.WebServlet;



@WebServlet("/AddComment")
public class AddComment extends HttpServlet {


	public AddComment() {
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
			throws ServletException, IOException {
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      
		  PrintWriter out=response.getWriter() ;
		  
		  Date date= new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
		  String time= sdf.format(date);
		  
		  
		  String userid=request.getParameter("userid");
		  String nickname=request.getParameter("nickname");
		  String bookid=request.getParameter("bookid");
		  
		  String content=request.getParameter("content");
		  
		  
		  System.out.println(content);
		  System.out.println(nickname);
		  System.out.println(userid);
		  if(userid.equals("undefined"))
		  {
			  
			  out.write("["+"{"+"\"nickname\":"+"\""+"未登录"+"\""+"}"+"]");
		  }	
		  else
		  {
		  
		  CommentDao cd=new CommentDao();
		  Comment c=new Comment();
		  
		  BookDao bd=new BookDao();
	         Book b=bd.getBookbyid(bookid);
		  
		  c.setBook(b);
		  c.setNickname(nickname);
		  c.setUserid(userid);
		  c.setCommentdate(time);
		  c.setContent(content);
		  
		  
		  cd.addComment(c);
		  

		    UserDao ud=new UserDao();
		    AdmireDao ad=new AdmireDao();
		    ReplyDao rd=new ReplyDao();
		    
		    List<Comment>  blist=cd.getComentbyBookid(bookid);
		    StringBuilder str=new StringBuilder();
		    
		    
		       if(blist!=null&&blist.size()>0)
		       {
		    	   
		    	   
		    	   for(int i=blist.size()-1;i>=0;i--)
		    	   {
		    		   Comment c2=blist.get(i);
		    		   
		    		   
		    		   
		    	List<Reply> drlist=rd.getRelybycommentid(c2.getCommentid());
		    		    
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
		    		   
		    		   
		    		   
		    		   
		    		   
		    		      User u= ud.getUserbyid(c2.getUserid());
					      String userimages=u.getUserimages();
					      Object  admirenum=cd.getAdmirenum(c2.getCommentid());
					     
					       boolean admirebol=ad.getAdmirebyuserid(userid, c2.getCommentid());
					      
					  
		    			str.append("{").append("\"commentid\":\""+c2.getCommentid()+"\"")
						.append(",")
						.append("\"bookid\":\""+c2.getBook().getBookid()+"\"")
						.append(",")
						.append("\"userid\":\""+c2.getUserid()+"\"")
						.append(",")
					   .append("\"content\":\""+c2.getContent()+"\"")
					   .append(",")
					   .append("\"nickname\":\""+c2.getNickname()+"\"")
					   .append(",")
					   .append("\"commentdate\":\""+c2.getCommentdate()+"\"")
					   .append(",")
					   .append("\"userimages\":\""+userimages+"\"")
					   
					   .append(",")
					   .append("\"admirenum\":\""+admirenum+"\"")
					   .append(",")
					   .append("\"admirebol\":\""+admirebol+"\"")
					   .append(",")
					   .append("\"evaluate\":\""+c2.getEvaluate()+"\"")
					   .append(",")
					   .append("\"Evaluatestatus\":\""+c2.getEvaluatestatus()+"\"")
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
		  
		  
		  

		
	}

	
	public void init() throws ServletException {
		
	}

}
