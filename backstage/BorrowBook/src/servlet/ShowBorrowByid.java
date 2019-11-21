package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import dao.UserDao;
import entity.BorrowTable;
import entity.User;


@WebServlet("/ShowBorrowByid")
public class ShowBorrowByid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ShowBorrowByid() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	           doPost(request,response);
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		    response.setContentType("application/json;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
	       
	       Date date= new Date();//创建一个时间对象，获取到当前的时间
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//设置时间显示格式
			 
	          String time= sdf.format(date);//将当前时间格式化为需要的类型
		
	       
	         String borrowid=request.getParameter("borrowid");
	         
	         BorrowTableDao btd=new BorrowTableDao();
	         
	         BorrowTable bt=btd.getBorrowTablebyid(Integer.parseInt(borrowid));
	         
	         StringBuilder str=new StringBuilder();
	         if(bt!=null)
	         {
	        	 
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
		        	
		     		str.append("{").append("\"id\":\""+bt.getId()+"\"")
					.append(",")
					.append("\"booksonid\":\""+bt.getBookson().getBooksonid()+"\"")  
					.append(",")
					.append("\"booktitle\":\""+bt.getBookson().getBook().getBooktitle()+"\"")
					.append(",")
					.append("\"bookimages\":\""+bt.getBookson().getBook().getBookimages()+"\"")
					.append(",")
					.append("\"author\":\""+bt.getBookson().getBook().getAuthor()+"\"")
					.append(",")
					.append("\"publish\":\""+bt.getBookson().getBook().getPublish()+"\"")
					.append(",")
				   .append("\"borrowdate\":\""+bt.getBorrowdate()+"\"")
				   .append(",")
				   .append("\"deadline\":\""+bt.getDeadline()+"\"")
				    .append(",")
				   .append("\"warn\":\""+warn+"\"")
				   .append(",")
				   .append("\"fine\":\""+String.valueOf(fine)+"\"")
				   .append(",")
				   .append("\"userimages\":\""+bt.getUser().getUserimages()+"\"")
				   .append(",")
				   .append("\"userid\":\""+bt.getUser().getUserid()+"\"")
				   .append(",")
				   .append("\"nickname\":\""+bt.getUser().getNickname()+"\"")
				   .append(",")
				   .append("\"isbn\":\""+bt.getBookson().getBook().getPublishnumber()+"\"")
				   .append(",")
					.append("\"bookid\":\""+bt.getBookson().getBook().getBookid() +"\"")
					.append(",")
					.append("\"category_id\":\""+bt.getBookson().getBook().getCategory().getCategoryid()+"\"")
					.append(",")
					.append("\"typename\":\""+bt.getBookson().getBook().getCategory().getName()+"\"")
					.append(",")
					.append("\"renewstatus\":\""+bt.getRenewstatus()+"\"")
					.append(",")
					.append("\"cash\":\""+bt.getBookson().getBook().getCash()+"\"")
				   .append("}");
		    	   
		             out.write(str.toString());

	         }
	         
	         
		
	}

}
