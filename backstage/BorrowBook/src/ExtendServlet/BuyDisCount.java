package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.DiscountDao;
import Extenddao.DiscountUserDao;
import dao.UserDao;
import entity.Discount;
import entity.DiscountUser;
import entity.User;


@WebServlet("/BuyDisCount")
public class BuyDisCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BuyDisCount() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		  response.setContentType("text/html;charset=utf-8");
		    request.setCharacterEncoding("utf-8");
		   
		    PrintWriter out=response.getWriter(); 
		    
		    
		    String userid=request.getParameter("userid");
		    String countid=request.getParameter("countid");
		    String needscore=request.getParameter("needscore");
		    
		    
		    
		    //积分扣除
		     UserDao ud=new UserDao();
		     User u=ud.getUserbyid(userid);
		     int oldscore=u.getScore();
		     int newscore=oldscore-Integer.parseInt(needscore);
		     
		     u.setUserid(userid);
		     u.setScore(newscore);
		     ud.UpdatePersonScore(u);
		    
		     
		     //优惠劵过期时间
		      Date date= new Date();
	          SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");//设置时间显示格式
			  String createdate= sdf.format(date);
			  System.out.println("创建时间"+createdate);
			  
			  //生成到期时间
			    Calendar rightNow = Calendar.getInstance();
			    rightNow.setTime(date);
		        rightNow.add(Calendar.MONTH,1);
			    Date dt1=rightNow.getTime();
			    String deadline = sdf.format(dt1);
			    System.out.println(deadline);
			    System.out.println("销毁时间"+deadline);
		     
			    DiscountUserDao dus=new DiscountUserDao();
			    
			    DiscountUser du=new DiscountUser();
			    
			   DiscountDao dd=new DiscountDao();
			   Discount d=dd.getDisCountByid(Integer.parseInt(countid));
			   
			  
			   du.setDiscount(d);
			   du.setUserid(userid);
			   du.setCreatedate(createdate);
			   du.setDeadline(deadline);
			   
			   dus.SaveDiscountUser(du);
			    
	}

}
