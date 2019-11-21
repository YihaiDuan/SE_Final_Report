package EleBookServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EleBookDao.EleBookDao;
import dao.BookDao;
import dao.UserDao;
import entity.Book;
import entity.MyEleBook;
import entity.User;


@WebServlet("/PayEleBook")
public class PayEleBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PayEleBook() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		         doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		  response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		 
	      PrintWriter out=response.getWriter() ;
		
		         String userid=request.getParameter("userid");
		         String bookid=request.getParameter("bookid");
		         String paymoney=request.getParameter("realmoney");
		         
		         
		         //扣除支付金额
   			  UserDao ud2=new UserDao();
   			  User u2=ud2.getUserbyid(userid);
   			  
   			  String oldmoney=u2.getMoney();
   			  Double newmoney=Double.valueOf(oldmoney)-Double.valueOf(paymoney);
   			  
   			  //更新钱包
   			    u2.setUserid(userid);
   			    u2.setMoney(String.valueOf(newmoney));
   			    ud2.UpdatePersonMoney(u2);
   			    
   			    
   			    
   			    //判断该书是否已经在书架中
   			    EleBookDao ebd=new EleBookDao();
   			    MyEleBook eb=ebd.getMyEleBook(userid, bookid);
   			    
   			    
   			    //已经在书架中更新标志位
		         if(eb!=null)
		         {
		        	 
		        	 MyEleBook m=new MyEleBook();
		        	 m.setId(eb.getId());
		        	 m.setAddstatus(1);
		        	 ebd.UpdateMyEleBookAddStatus(m);
		        	 
		         }
		          //还没有在书架中添加到书架中
		         else
		         {
		        	 
		        	 MyEleBook mm=new MyEleBook();
		        	 UserDao ud=new UserDao();
		        	 User u=ud.getUserbyid(userid);
		        	 BookDao bd=new BookDao();
		        	 Book b=bd.getBookbyid(bookid);
		        	 
		        	 
		        	 mm.setAddstatus(1);
		        	 mm.setBook(b);
		        	 mm.setUser(u);
		        	 
		        	 ebd.SaveMyEleBook(mm);
		        	 
		         }
		
		
		
	}

}
