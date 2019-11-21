package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import dao.UserDao;
import entity.Comment;
import entity.User;

import javax.servlet.annotation.WebServlet;



@WebServlet("/ShowCommentOne")
public class ShowCommentOne extends HttpServlet {

	
	public ShowCommentOne() {
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
		  
		  String bookid=request.getParameter("bookid");
		  
		  CommentDao cd=new CommentDao();
		   
		  
		  //获取评论总数
		  
		      int num=Integer.parseInt(cd.getCommentTotal(bookid).toString());
	          int num2=Integer.parseInt(cd.getReplynum(bookid).toString());
	         int  num3=num+num2;
	      
	      out.write("{"+"\"num\":"+"\""+num3+"\""+"}");
	      
	   
	      

	
	}


	public void init() throws ServletException {
		
	}

}
