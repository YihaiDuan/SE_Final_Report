package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import dao.UserDao;
import entity.Comment;
import entity.User;

/**
 * Servlet implementation class GetComment
 */
@WebServlet("/GetComment")
public class GetComment extends HttpServlet {

	public GetComment() {
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
	      
	      
		  PrintWriter out=response.getWriter();
		  
		  String commentid=request.getParameter("commentid");
		  
		  CommentDao cd=new CommentDao();
		  UserDao ud=new UserDao();
		  
		
		  
		 Comment c=cd.getCommentByid(Integer.parseInt(commentid));
		 
		    StringBuilder str=new StringBuilder();
		    
		    
		       if(c!=null)
		       {
		    	   
		    	   
		    	   User u= ud.getUserbyid(c.getUserid());
				      String userimages=u.getUserimages();
				      
				      
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
					    .append("}");
		    		   
		    	
		    	   
		    		 out.write(str.subSequence(0,str.length()).toString());
		    	   
		       }
		
		
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      

	
	}


	public void init() throws ServletException {
		
	}

}
