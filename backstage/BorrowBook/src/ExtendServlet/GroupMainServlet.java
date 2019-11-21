package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.GroupBookDao;
import Extenddao2.GroupMainDao;
import dao.BookSonDao;
import dao.UserDao;
import entity.Book;
import entity.BookSon;
import entity.GroupBook;
import entity.GroupMain;
import entity.User;
import hfhdao.BookDao;


@WebServlet("/GroupMainServlet")
public class GroupMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GroupMainServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		   response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   
		   PrintWriter out=response.getWriter();
		   
		   
		   Date date=new Date();
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   String nowdate = sdf.format(date);
		   
		   
		   
		   System.out.println("支付成功");
		   
		   String userid=request.getParameter("userid");
		   String bookid=request.getParameter("bookid");
		   String cash=request.getParameter("cash");
		   String id=request.getParameter("id");
		   
		   //扣除支付金额
		  UserDao ud=new UserDao();
		  User u=ud.getUserbyid(userid);
		  
		  String oldmoney=u.getMoney();
		  Double newmoney=Double.valueOf(oldmoney)-Double.valueOf(cash);
		  
		  //更新钱包
		    u.setUserid(userid);
		    u.setMoney(String.valueOf(newmoney));
		    ud.UpdatePersonMoney(u);
		   
         //根据书目录id获取下面的所有可借书籍
           
           BookSonDao bsd=new BookSonDao();
	       BookSon bs=bsd.getNoBorrowNoOrderBookSon(bookid);
	       
	       String booksonid=null;
	       
	       if(bs!=null)
	       {
	    	   
	    	 booksonid=bs.getBooksonid();
	     
	       }
		   
	       System.out.println(booksonid);
	       
	       
	       //更新图书被借标志位
	       BookSon bs1=bsd.getBookSon(booksonid);
	       bs1.setBorrowstatus(1);
	       bsd.UpdateBookBorrowStatus(bs1);
	       
	       
	       
	       GroupMainDao gmd=new GroupMainDao();
	       GroupMain gm=new GroupMain();
	       
	      
	       
	       GroupBookDao gbd=new GroupBookDao();
	       GroupBook gb=gbd.getGroupBook(Integer.parseInt(id));
	       
	     
	       
	       gm.setDate(nowdate);
	       gm.setBookid(bookid);
	       gm.setGroupbook(gb);
	       gm.setBooksonid(booksonid);
	       gm.setUserid(userid);
	       gm.setPaymoney(cash);
	       
	     
	       
	       int groupmainid=gmd.SaveGroupMain(gm);
	       
	   
	       out.write("{"+"\"id\":"+"\""+groupmainid+"\""+"}");
	  
		
	}

}
