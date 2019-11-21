package AccountServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;
import entity.UserRecharge;


@WebServlet("/ReChargeServlet")
public class ReChargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReChargeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		         doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		 response.setContentType("text/html;charset=UTF-8");
	       request.setCharacterEncoding("UTF-8");
	       
	       PrintWriter out=response.getWriter();
	       
	       String userid=request.getParameter("userid");
	       String recharge=request.getParameter("recharge");
	       String score=request.getParameter("score");
	       
	       System.out.println("sdfsdfdsfsdfd"+score);
	       
	       //添加充值记录
	       
	       Date date=new Date();
	       SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	       String nowdate=sdf.format(date);
	       
	       
	       
	        UserDao ud=new UserDao();
	       User u=ud.getUserbyid(userid);
	       
	       ReChargeDao rcd=new ReChargeDao();
	       
	       
	         UserRecharge  ur=new UserRecharge();
	         ur.setTime(nowdate);
	         ur.setPrice(Double.valueOf(recharge));
	         ur.setUser(u);
	         
	         rcd.SaveRecharge(ur);
	       
	       String oldmoney=u.getMoney();
	       
	       Double newmoney=Double.parseDouble(oldmoney)+Double.parseDouble(recharge);
	      
	       
	       //更新用户钱包
	        u.setMoney(newmoney.toString());
	        
	        ud.UpdatePersonMoney(u);
	        
	        
	        //更新用户积分
	        int oldscore=u.getScore();
	        int newscore=oldscore+Integer.parseInt(score);
	        
	        u.setScore(newscore);
	        
	        ud.UpdatePersonScore(u);
	        
	        
	        User u2=ud.getUserbyid(userid);
		       
		       if(u2!=null)
		       {
		    	   
		    	   out.write(u.getMoney().toString());
		    	   
		    	   
		       }
	       
	       
	       
	}

}
