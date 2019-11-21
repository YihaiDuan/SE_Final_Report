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


@WebServlet("/AddDynamicNoPic")
public class AddDynamicNoPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddDynamicNoPic() {
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
	      
	      System.out.println(nowdate);
	      
	      String userid=request.getParameter("userid");
	      String bookid=request.getParameter("bookid");
	      String describ=request.getParameter("describ");
	      
	      
	      System.out.println(userid);
	      System.out.println(bookid);
	      
	      
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
	      d.setGroupmainid(0);
	      d.setDate(nowdate);
	      d.setTypeid(0);
	      
	     
	      
	      
	      dd.SaveDynamic(d);
	   
	      
	
	}

}
