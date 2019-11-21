package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import dao.UserDao;
import entity.BorrowTable;
import entity.User;


@WebServlet("/DeleteReturnLan")
public class DeleteReturnLan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public DeleteReturnLan() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    doPost(request,response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
      
        PrintWriter out=response.getWriter();
        
        
          String id=request.getParameter("id");
          
          
          BorrowTableDao btd=new BorrowTableDao();
          
          BorrowTable btt=new BorrowTable();
          
          btt.setId(Integer.parseInt(id));
          btt.setReturnstatus(0);
          
          
          
          btd.UpdateBorrowReturnStatus(btt);
          
          
          
          
          Date date= new Date();//创建一个时间对象，获取到当前的时间
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//设置时间显示格式
			 
	          String time= sdf.format(date);//将当前时间格式化为需要的类型

			   System.out.println(time);
	         
	         System.out.println("我来了获取还书二维码的内容");
	         String userid=request.getParameter("userid");
	         
	         System.out.println("要还书的用户"+userid);
	         
	         BorrowTableDao bto=new BorrowTableDao();
	         
	          
	         List<BorrowTable>  blist=bto.getBookOkScan(userid);
	         StringBuilder str=new StringBuilder();
	         String json=null;
	         
	         float totalfine=0.0f;
	         
	       if(blist!=null&&blist.size()>0)
	       {
	        	 
	        for(int i=blist.size()-1;i>=0;i--)	
	        {
	        	 
	        	 BorrowTable bt=blist.get(i);
	        	 
	        	 System.out.println("我来了输出获取的对象集合"+bt);
	        	 
	           int  day=(int) ((Date.parse(bt.getDeadline())-Date.parse(time))/86400000);
	           
	           
	           System.out.println("输出剩下的天数"+day);
	           
	           String warn=null;
	           float fine=0.0f;
	           
	           if(day>0)
	           {
	        	   
	        	   warn="无逾期";
	        	   fine=0.0f;
	        	   
	           }
	           else
	           {
	        	   warn="逾期"+(-day)+"天";
	        	   fine=(float)((-day)*(0.25));
	        	   
	           }
	           /*
	    	    * id:借书id
	    	    * bookid:图书id
	    	    * booktitle:标题
	    	    * bookimages:图书封面
	    	    * author:作者
	    	    * publish:出版社
	    	    * borrowdate:借书时间
	    	    * deadlinedate:到期时间
	    	    * warn提示语
	    	    * fine:逾期产生费用
	    	    
	    	    * */
	           
	           totalfine+=fine;
	        	
	     		str.append("{").append("\"id\":\""+bt.getId()+"\"")
				.append(",")
				.append("\"bookid\":\""+bt.getBookson().getBook().getBookid() +"\"")  
				.append(",")
				.append("\"booktitle\":\""+bt.getBookson().getBook().getBooktitle()+"\"")
				.append(",")
				.append("\"bookimages\":\""+bt.getBookson().getBook().getBookimages()+"\"")
				.append(",")
				.append("\"author\":\""+bt.getBookson().getBook().getAuthor()+"\"")
				.append(",")
				.append("\"publish\":\""+bt.getBookson().getBook().getPublish()+"\"")
				.append(",")
			   .append("\"borrowdate\":\""+bt.getBorrowdate().substring(0,10)+"\"")
			   .append(",")
			   .append("\"deadline\":\""+bt.getDeadline().substring(0,10)+"\"")
			    .append(",")
			   .append("\"warn\":\""+warn+"\"")
			   .append(",")
			   .append("\"fine\":\""+String.valueOf(fine)+"\"")
			   .append(",")
			   .append("\"booksonid\":\""+bt.getBookson().getBooksonid()+"\"")
			   .append(",")
			   .append("\"isbn\":\""+bt.getBookson().getBook().getPublishnumber()+"\"")
			   .append(",")
			   .append("\"cash\":\""+bt.getBookson().getBook().getCash()+"\"")
			   .append("}").append(",");
	    	   
          
	         }
	        
	        
	       UserDao ud=new UserDao();
	       User u=ud.getUserbyid(userid);
	        
	        
	
	        

	      out.write("{\"totalfine\":"+"\""+totalfine+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");
	       }
	       else
	       {
	    	   
	    	   
	    	   out.write("610");
	       }
	
	
		  
		
		      
	}

}
