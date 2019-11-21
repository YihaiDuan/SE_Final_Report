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

import dao.ApplyNearDao;
import dao.BorrowTableDao;
import entity.BorrowTable;


@WebServlet("/ShowNearBorrowDetail")
public class ShowNearBorrowDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ShowNearBorrowDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		         doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		         
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      Date date= new Date();//创建一个时间对象，获取到当前的时间
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//设置时间显示格式
			 
	          String time= sdf.format(date);//将当前时间格式化为需要的类型

			   System.out.println(time);
	      
	      PrintWriter out=response.getWriter() ;
	      
	        String borrowid=request.getParameter("borrowid");
	        String userid=request.getParameter("userid");
	        
	        
	        System.out.println(userid);
	        BorrowTableDao btd=new BorrowTableDao();
		  
		     BorrowTable b=btd.getBorrowbyId(Integer.parseInt(borrowid));
		     StringBuilder str=new StringBuilder();
		     
		     if(b!=null)
		     {
		    	 
		    	 int  day=(int) ((Date.parse(b.getDeadline())-Date.parse(time))/86400000);
		         System.out.println("输出剩下的天数"+day);
		    	 
		         ApplyNearDao and=new ApplyNearDao();
		         
		      boolean applybol= and.ApplyBoolean(userid, Integer.parseInt(borrowid));
		         
		    	 str.append("{").append("\"borrowid\":\""+b.getId()+"\"")

		    	 .append(",")
		    	 .append("\"booktitle\":\""+b.getBookson().getBook().getBooktitle()+"\"")
		    	 .append(",")
		    	 .append("\"bookimages\":\""+b.getBookson().getBook().getBookimages()+"\"")
		    	 .append(",")
		    	 .append("\"bookid\":\""+b.getBookson().getBook().getBookid()+"\"")
		    	 .append(",")
		    	 .append("\"booksonid\":\""+b.getBookson().getBooksonid()+"\"")
		    	 .append(",")
		    	 .append("\"author\":\""+b.getBookson().getBook().getAuthor()+"\"")
		    	 .append(",")
		    	 .append("\"deadline\":\""+b.getDeadline()+"\"")
		    	 .append(",")
		    	 .append("\"userid\":\""+b.getUser().getUserid()+"\"")
		    	 .append(",")
		    	 .append("\"userimages\":\""+b.getUser().getUserimages()+"\"")
		    	 .append(",")
		    	 .append("\"nickname\":\""+b.getUser().getNickname()+"\"")
		    	 .append(",")
		    	 .append("\"sex\":\""+b.getUser().getSex()+"\"")
		    	 .append(",")
		    	 .append("\"personQR\":\""+b.getUser().getPersonQR()+"\"")
		    	 .append(",")
		    	 .append("\"type\":\""+b.getBookson().getBook().getCategory().getCategoryid()+"\"")
		    	 .append(",")
		    	 .append("\"day\":\""+day+"\"")
		    	 .append(",")
		    	 .append("\"applybol\":\""+applybol+"\"")
		    	 
		    	 .append("}");  
		    	 
		    	 
		    	 out.write(str.toString());
		    	 
		     }
	}

}
