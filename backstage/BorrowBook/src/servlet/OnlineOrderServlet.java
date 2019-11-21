package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timer.TimerOnlinDead;
import util.JsonFormat;
import dao.BookDao;
import dao.BookSonDao;
import dao.CollectDao;
import dao.OnlineWarnDao;
import dao.OrderLineDao;
import dao.UserDao;
import entity.Book;
import entity.BookSon;
import entity.OnlineOrder;
import entity.OnlineWarn;
import entity.User;
import javax.servlet.annotation.WebServlet;



@WebServlet("/OnlineOrderServlet")
public class OnlineOrderServlet extends HttpServlet {

	
	public OnlineOrderServlet() {
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
	       
	       
	       Date date= new Date();
			
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//璁剧疆鏃堕棿鏄剧ず鏍煎紡
		 
           String time= sdf.format(date);
           
           
           
           Date dt=new Date();
		try {
			dt = sdf.parse(time);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	       
	       String status=request.getParameter("status");
	       String userid=request.getParameter("userid");
	       String bookid=request.getParameter("bookid");
	       String action=request.getParameter("action");
	       String formId=request.getParameter("formId");
	       String deadtime=request.getParameter("deadtime");
	       String cash=request.getParameter("cash");
	       
	       
	       System.out.println(cash);
	       //String orderdate=request.getParameter("orderdate");
	       System.out.println("lalalalalalalal"+deadtime);
	      
	       
	       OnlineOrder o=new OnlineOrder();
	       OrderLineDao od=new OrderLineDao();
	       UserDao ud=new UserDao();
           User u=ud.getUserbyid(userid);
           BookDao bd=new BookDao();
          
           
           //根据书目录id获取下面的所有可预订书籍
           
           BookSonDao bsd=new BookSonDao();
	       BookSon bs=bsd.getNoBorrowNoOrderBookSon(bookid);
	       
	       String booksonid=null;
	       if(bs!=null)
	       {
	    	   
	    	 booksonid=bs.getBooksonid();
	       }
	       
	       if(action.equals("order"))
	       {
	    	   
	    	   
	    	   String orderdate=request.getParameter("orderdate");
	    	   
	    	  
	    	   //判断用户是否已经预订了两本书
	    	  
	    	   int num=0;
	    	   
	    	    num=od.getOnlineOrderNum(userid);
	    	    
	    	   //如果已经预订了两本就无法预订
	    	   if(num==2)
	    	   {
	    		   
	    		   out.write("beyond");
	    	   }
	    	   else
	    	   {
	    	   
	    		   //扣除支付金额
	    			
	    			  
	    			  String oldmoney=u.getMoney();
	    			  Double newmoney=Double.valueOf(oldmoney)-Double.valueOf(cash);
	    			  
	    			  System.out.println("\\\\\\\\\\newmoney"+newmoney);
	    			  
	    			  //更新钱包
	    			 
	    			  u.setMoney(newmoney.toString());
	    			  ud.UpdatePersonMoney(u);
	    		
	    			 /* if(newmoney!=-1)
	    			  {UserDao uuud=new UserDao();
	    			  
	    			  User u3=uuud.getUserbyid(userid);
	    			    u3.setUserid(userid);
	    			    u3.setMoney(String.valueOf(newmoney));
	    			    uuud.UpdatePersonMoney(u3);
	    			    
	    			    
	    			  }*/
	    	    
	    
	             o.setFormid(formId);
	             o.setOrderdate(orderdate);
	            
	             o.setStatus(Integer.parseInt(status));
	             o.setUser(u);
	             
	             
	             o.setBookson(bs);
	             
	             o.setDate(time);
	             od.SavaOrderLine(o);
	             
	             //更新书的预订标志位
//	             bs.setBooksonid(booksonid);
//	             bs.setOrderstatus(1);
	           
	             bsd.UpdateBookOrderStatus(bs);
	             
	        Book b=bd.getBookbyid(bookid);
	        StringBuilder str=new StringBuilder();
	             
	        if(b!=null)
	         {
	        	 
	        	   String list=null;
	  	         String guidreading=null;
	  	         String review=null;
	  	         String introduce=null;
	  	         
	  	       if(b.getList()!=null)
	  	         {
	  	        	 list=JsonFormat.stringToJson(b.getList());
	  	        
	  	         }
	  	         else
	  	         {
	  	        		list="没有相关内容";
	  	        	 
	  	         }
	  	         
	  	         
	  	        	 
	  	         if(b.getGuidreading()!=null)
	  	         {
	  	        	 guidreading=JsonFormat.stringToJson(b.getGuidreading());
	  	        	
	  	         }
	  	         else
	  	         {
	  	        	 guidreading="没有相关内容";
	  	        	 
	  	         }
	  	         
	  	         
	  	         
	  	         if(b.getReview()!=null)
	  	         {
	  	        	 review=JsonFormat.stringToJson(b.getReview());
	  	        	
	  	         }
	  	         else
	  	         {
	  	        	 review="没有相关内容";
	  	        	 
	  	         }
	  	         
	  	         
	  	         
	  	         if(b.getIntroduce()!=null)
	  	         {
	  	        	 
	  	        	
	  	        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
	  	         }
	  	         else
	  	         {
	  	        	 introduce="没有相关内容";
	  	        	 
	  	         }	 
	        	 
	        	 
	        	 
	        CollectDao cod=new CollectDao();
	        boolean collectbol=cod.CollectBol(userid,bookid);
	        
	       //获取藏数量
	        Object totalnum=0;
	       
	        
	        totalnum=bsd.getTotalNumByBookid(bookid);
	        
	        
	        //当前可借数量
	        Object borrownum=0;
	        borrownum=bsd.getBookSnoBorrowStatusByBookid(bookid);
	        
	        
	         OnlineWarnDao old=new OnlineWarnDao();
	        
	        boolean warnboolean=old.OrderWarn(userid, bookid);
	        	 
	        	 str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
	 	        .append(",")
	 			.append("\"author\":\""+b.getAuthor() +"\"")
	 			
	 			.append(",")
	 			.append("\"bookimages\":\""+b.getBookimages() +"\"")
	 			
	 			.append(",")
	 			.append("\"booktitle\":\""+b.getBooktitle()+"\"")
	 			
	 			.append(",")
	 			.append("\"introduce\":\""+introduce+"\"")
	 			
	 			.append(",")
	 			.append("\"publish\":\""+b.getPublish()+"\"")
	 			
	 			.append(",")
	 			.append("\"publishnumber\":\""+b.getPublishnumber()+"\"")
	 			
			    .append(",")
			    .append("\"totalnum\":\""+totalnum+"\"")
	 			
	 			.append(",")
	 			.append("\"booknum\":\""+b.getBooknum()+"\"")
               .append(",")
	 			.append("\"review\":\""+review+"\"")
	 			
	 			.append(",")
	 			.append("\"list\":\""+list+"\"")
	 			
	 			.append(",")
	 			.append("\"guidreading\":\""+guidreading+"\"")
	 			
	 			
	 			//判断用户是否已经收藏
	 			.append(",")
	 			.append("\"collectbol\":\""+collectbol+"\"")
	 			
	 			.append(",")
	 			.append("\"borrownum\":\""+borrownum+"\"")

 	 			.append(",")
 	 			.append("\"warnboolean\":\""+warnboolean+"\"")
 	 			.append(",")
	 			.append("\"typename\":\""+b.getCategory().getName()+"\"")
	 			.append(",")
	 			.append("\"cash\":\""+b.getCash()+"\"")
	 			.append(",")
	 			.append("\"position\":\""+b.getPosition()+"\"")
	 			
	 			.append("}");
	 	        
	 		  
	 	         out.write(str.toString());
	 	         
	        	 
	        }//获取的更新对象if
	
	        
	        
	        TimerOnlinDead tod=new TimerOnlinDead(86400000,booksonid,userid);
 	        
 	        tod.start();
 	        
	    	  }
	 	      
	     }
	      
	     else
	       {
	    	   
	    	   
	    	  //一本书只能设置一次提醒
	    	   OnlineWarnDao old=new OnlineWarnDao();
	    	   
	    	 boolean bol=old.OrderWarn(userid, bookid);
	    	 
	    	   
	    	if(!bol)
	    	{
	    		
	    		Book b=bd.getBookbyid(bookid);
	    		
	    		
	    		OnlineWarn ow=new OnlineWarn();
	    		
	    	     ow.setFormid(formId);
	    	     ow.setStatus(0);
	             ow.setUser(u);
	             ow.setBook(b);
	             
	             old.SavaOrderWarn(ow);
	            
	             StringBuilder str=new StringBuilder();
	             
	             
	             
	 	        if(b!=null)
	 	         {
	 	        	 
	 	        	 String list=null;
	 	  	         String guidreading=null;
	 	  	         String review=null;
	 	  	         String introduce=null;
	 	  	         
	 	  	         if(b.getList()!=null)
	 	  	         {
	 	  	        	 list=b.getList().replaceAll("\t", "");
	 	  	        
	 	  	         }
	 	  	         else
	 	  	         {
	 	  	        		list="没有相关内容";
	 	  	        	 
	 	  	         }
	 	  	         
	 	  	         
	 	  	        	 
	 	  	         if(b.getGuidreading()!=null)
	 	  	         {
	 	  	        	 guidreading=b.getGuidreading().replaceAll("\t", "");
	 	  	        	
	 	  	         }
	 	  	         else
	 	  	         {
	 	  	        	 guidreading="没有相关内容";
	 	  	        	 
	 	  	         }
	 	  	         
	 	  	         
	 	  	         
	 	  	         if(b.getReview()!=null)
	 	  	         {
	 	  	        	 review=b.getReview().replaceAll("\t", "");
	 	  	        	
	 	  	         }
	 	  	         else
	 	  	         {
	 	  	        	 review="没有相关内容";
	 	  	        	 
	 	  	         }
	 	  	         
	 	  	         
	 	  	         
	 	  	         if(b.getIntroduce()!=null)
	 	  	         {
	 	  	        	 
	 	  	        	
	 	  	        	 introduce=b.getIntroduce().replaceAll("\t", "");
	 	  	         }
	 	  	         else
	 	  	         {
	 	  	        	 introduce="没有相关内容";
	 	  	        	 
	 	  	         }	 
	 	        	 
	 	        	 
	 	        	 
	 	        CollectDao cod=new CollectDao();
	 	        boolean collectbol=cod.CollectBol(userid,bookid);
	 	        
	 	       //获取藏数量
	 	        Object totalnum=0;
	 	       
	 	        
	 	        totalnum=bsd.getTotalNumByBookid(bookid);
	 	        
	 	        
	 	        //当前可借数量
	 	        Object borrownum=0;
	 	        borrownum=bsd.getBookSnoBorrowStatusByBookid(bookid);
	 	        
	 	        boolean warnboolean=old.OrderWarn(userid, bookid);
	 	        	 
	 	        	 str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
	 	 	        .append(",")
	 	 			.append("\"author\":\""+b.getAuthor() +"\"")
	 	 			
	 	 			.append(",")
	 	 			.append("\"bookimages\":\""+b.getBookimages() +"\"")
	 	 			
	 	 			.append(",")
	 	 			.append("\"booktitle\":\""+b.getBooktitle()+"\"")
	 	 			
	 	 			.append(",")
	 	 			.append("\"introduce\":\""+introduce+"\"")
	 	 			
	 	 			.append(",")
	 	 			.append("\"publish\":\""+b.getPublish()+"\"")
	 	 			
	 	 			.append(",")
	 	 			.append("\"publishnumber\":\""+b.getPublishnumber()+"\"")
	 	 			
	 			    .append(",")
	 			    .append("\"totalnum\":\""+totalnum+"\"")
	 	 			
	 	 			.append(",")
	 	 			.append("\"booknum\":\""+b.getBooknum()+"\"")
	                .append(",")
	 	 			.append("\"review\":\""+review+"\"")
	 	 			
	 	 			.append(",")
	 	 			.append("\"list\":\""+list+"\"")
	 	 			
	 	 			.append(",")
	 	 			.append("\"guidreading\":\""+guidreading+"\"")
	 	 			
	 	 			
	 	 			//判断用户是否已经收藏
	 	 			.append(",")
	 	 			.append("\"collectbol\":\""+collectbol+"\"")
	 	 			
	 	 			.append(",")
	 	 			.append("\"borrownum\":\""+borrownum+"\"")
	 	 			
	 	 			.append(",")
	 	 			.append("\"warnboolean\":\""+warnboolean+"\"")
	 	 			.append(",")
		 			.append("\"typename\":\""+b.getCategory().getName()+"\"")
		 			.append(",")
		 			.append("\"position\":\""+b.getPosition()+"\"")
	 	 			
	 	 			.append("}");
	 	 	        
	 	 		  
	 	 	         out.write(str.toString());
	 	 	         
	 	        	 
	 	        }//获取的更新对象if
	 	
	             
	             
	    	  
	    	
	    	}
	    	else
	    	{
	    		
	    		
	    		out.write("fail");
	    	}
	    
	    	
	       }
	
		
	}

	
	public void init() throws ServletException {
		
	}

}
