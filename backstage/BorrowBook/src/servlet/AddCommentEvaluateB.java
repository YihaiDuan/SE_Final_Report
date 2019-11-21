package servlet;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.BorrowTableDao;
import dao.CommentDao;
import entity.Book;
import entity.BorrowTable;
import entity.Comment;

@WebServlet("/AddCommentEvaluateB")
public class AddCommentEvaluateB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddCommentEvaluateB() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		  System.out.println("/////////////////////////"+"我来了1");
	   /*   
	      
		//  PrintWriter out=response.getWriter() ;
		  
		  Date date= new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
		  String time= sdf.format(date);
		  

		  System.out.println("/////////////////////////"+"我来了1");
		  String userid=request.getParameter("userid");
		  String nickname=request.getParameter("nickname");
		  String bookid=request.getParameter("bookid");
		  
		  String content=request.getParameter("content");
		  String evaluate=request.getParameter("evaluate");
		  String borrowtableid=request.getParameter("borrowtableid");
		  
		  System.out.println("/////////////////////////"+evaluate);
		  System.out.println("/////////////////////////"+borrowtableid);
		  
		  System.out.println(content);
		  System.out.println(nickname);
		  System.out.println(userid);
		
		  BorrowTableDao btd=new BorrowTableDao();
		  
		  BorrowTable bt=new BorrowTable();
		  bt.setId(Integer.parseInt(borrowtableid));
		  bt.setEvaluatestatus(1);
		  btd.UpdateEvaluateStatus(bt);
		  System.out.println("我更新了");
		  
		  CommentDao cd=new CommentDao();
		  Comment c=new Comment();
		  
		  BookDao bd=new BookDao();
	         Book b=bd.getBookbyid(bookid);
		  
		  c.setBook(b);
		 c.setEvaluate(evaluate);
		 c.setEvaluatestatus(1);
		  c.setNickname(nickname);
		  c.setUserid(userid);
		  c.setCommentdate(time);
		  c.setContent(content);
		
		  
		  
		  cd.addComment(c);*/
		
	}

}
