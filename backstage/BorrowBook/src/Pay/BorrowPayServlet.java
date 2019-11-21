package Pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookSonDao;
import dao.BorrowTableDao;
import dao.ConfirmOrderDao;
import dao.UserDao;
import entity.BookSon;
import entity.BorrowTable;
import entity.ConfirmOrder;
import entity.User;
import timer.TimerBorrowBookWarn;


@WebServlet("/BorrowPayServlet")
public class BorrowPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BorrowPayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		   doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		 response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   String userid=request.getParameter("userid");
		   String cash=request.getParameter("cash");
		   
		   
		   System.out.println(cash);
		   
		    UserDao ud=new UserDao();
		       User u=ud.getUserbyid(userid);
		       
		       String oldmoney=u.getMoney();
		       
		       Double newmoney=Double.parseDouble(oldmoney)-Double.parseDouble(cash);
		      
		       
		       //更新用户钱包
		        u.setMoney(newmoney.toString());
		        
		        ud.UpdatePersonMoney(u);
		   
		   
		        //更新用户积分
		        int oldscore=u.getScore();
		        int newscore=oldscore+5;
		        
		        u.setScore(newscore);
		        
		        ud.UpdatePersonScore(u);
		        BorrowTableDao bd=new BorrowTableDao();
		          BorrowTable bt=new BorrowTable();
		          String username=null;
		        //更新用借书单表示为
		        List<BorrowTable> btlist=bd.getBookNoScanNoDeadLine(userid);
				  for(BorrowTable i:btlist)
				  {
				 //修改扫描位
				   bt.setId(i.getId());
		           bt.setScanstatus(1);
		           bd.UpdateScanStatus(bt);
				   
				  BorrowTable  bt2=bd.getBorrowTablebyid(i.getId());
				  String booksonid=bt2.getBookson().getBooksonid();
				   
				 
				 //更新预订状态位
				 BookSonDao bsd=new BookSonDao();
				 BookSon bs=new BookSon();

				 
				
				 //更新借书状态位
				
				 bs.setBooksonid(booksonid);
				 bs.setBorrowstatus(1);
				 bsd.UpdateBookBorrowStatus(bs);
				 
				 
				 String userid1 = bt2.getUser().getUserid();
				 String openid=bt2.getUser().getOpenid();
				 String formid=bt2.getFormid();
				 
		         int borrowid=bt2.getId();
				 
		         UserDao ud1=new UserDao();
		         User u1=ud.getUserbyid(userid1);
		         int warn=u1.getBorrowwarnstatus();  
		 		
				 
		 		
		 	
		    if(warn==1)  
		    {
		  
		    	
		   System.out.println("用户的还书提醒标志"+warn);
		   System.out.println("用户的openid"+openid);
		   	
			TimerBorrowBookWarn timer=new TimerBorrowBookWarn(20000,booksonid,userid,borrowid,formid,openid);
		    timer.start();
		   
		    }
				 
				 
			}//for
				 
			 out.write("609");
		   
		   
		   
		
	}

}
