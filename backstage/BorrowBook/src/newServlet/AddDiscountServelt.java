package newServlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.DiscountDao;
import entity.Discount;
import timer.TimerDiscountDead;
import timer.TimerDiscountStart;
import timer.TimerGroupDead;
import timer.TimerGroupStart;

@WebServlet("/AddDiscountServelt")
public class AddDiscountServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddDiscountServelt() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		      doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  
		     response.setContentType("text/html; charset=UTF-8");
		     request.setCharacterEncoding("UTF-8");
		     
     
		      /*
		      * 0为优惠劵
		      * 1为代金券
		      *
		      * */
		       Date date =new Date();
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			   String nowdate= sdf.format(date);
		     
		     
		     
		     
		     String typename=request.getParameter("typename");
		     String num=request.getParameter("num");
		     String deadline=request.getParameter("deadline");
		     String createdate=request.getParameter("createdate");
		     String discount=request.getParameter("discount");
		     
		     System.out.println(createdate);
            System.out.println(deadline);		     
		       //得到优惠劵开始的毫秒数
		        
		        Date startbegin=null;
		        Date startend =null;
		        
		        try {
					 
					 startbegin = sdf.parse(nowdate);
					 startend = sdf.parse(createdate);
					 
				    } 
				catch (ParseException e) 
				{
					
					e.printStackTrace();
				}
	           
				
	   long startbetween = (startend.getTime() - startbegin.getTime());// 得到优惠劵开始的毫秒数
		        
		        
		        
		        
		     // 得到优惠劵过期时间的毫秒数
		        Date deadbegin=null;
		        Date deadend=null;
		        
		        
				try {
					 
					 deadbegin = sdf.parse(nowdate);
					   deadend = sdf.parse(deadline);
					 
				    } 
				catch (ParseException e) 
				{
					
					e.printStackTrace();
				}
	            
				
	    long deadbetween = (deadend.getTime() - deadbegin.getTime());// 得到优惠劵过期时间的毫秒数
	             
		     
		     
		     
		     
		     
		     
		     
		     
		     Discount d=new Discount();
		     DiscountDao dsd=new DiscountDao();
		     
		     d.setDiscount(discount);
		     d.setNum(Integer.parseInt(num));
		     d.setTypename(typename);
		     d.setDeadline(deadline);
		     d.setCreatedate(createdate);
		
		     int id=dsd.SaveDiscount(d);
		       
         System.out.println("我是要去判断该优惠劵是否开启或过期的id"+id);
             
             System.out.println("开始"+startbetween);
             
             //组团开始线程
             TimerDiscountStart s=new TimerDiscountStart((int)startbetween,id);
             s.start();
		    
             System.out.println("结束"+deadbetween);
             
              //组团过期线程
             TimerDiscountDead t=new TimerDiscountDead((int)deadbetween,id);
             t.start();
		      
		
		     
	}

}
