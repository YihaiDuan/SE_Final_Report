package DynamicServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.DynamicDao;
import dao.BookDao;
import dao.UserDao;
import entity.Book;
import entity.Dynamic;
import entity.User;


@WebServlet("/AddGroupShare")
public class AddGroupShare extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddGroupShare() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		 
	      PrintWriter out=response.getWriter() ;
	      Date date =new Date();
	      SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
	      String nowdate=sdf.format(date);
	      
	      String userid=request.getParameter("userid");
	      String describ=request.getParameter("describ");
	      String bookid=request.getParameter("bookid");
	      String groupmainid=request.getParameter("groupmainid");
	      
	      

	      DynamicDao dd=new DynamicDao();
	      Dynamic d=new Dynamic();
	      
	      
	      UserDao ud=new UserDao();
	      User u=ud.getUserbyid(userid);
	      d.setUser(u);
	      
	      BookDao bd=new BookDao();
	      Book b=bd.getBookbyid(bookid);
	      d.setBook(b);
	      
	      
	      d.setImages("0");
	      d.setDescrib(describ);
	      d.setTypeid(2);
	      d.setGroupmainid(Integer.parseInt(groupmainid));
	      d.setDate(nowdate);
	    
	      
	      dd.SaveDynamic(d);

		
	}

}
