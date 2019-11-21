package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import entity.BorrowTable;
import javax.servlet.annotation.WebServlet;


@WebServlet("/ShowBorrowServlet")
public class ShowBorrowServlet extends HttpServlet {


	public ShowBorrowServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
			{
		
		
		          doPost(request,response);

	
	       }


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
	      
	      Date date= new Date();//创建一个时间对象，获取到当前的时间
			
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//设置时间显示格式
		 
          String time= sdf.format(date);//将当前时间格式化为需要的类型

		   System.out.println(time);
	      
	      String userid =request.getParameter("userid");
	      
	      
	      
	      BorrowTableDao btd=new BorrowTableDao();
	      
	       List<BorrowTable>  blist = btd.getBookBorrowData(userid);
	       StringBuilder str=new StringBuilder();
			  
	          System.out.println(blist.size());
			  
	          
	          
			  if(blist!=null&&blist.size()>0)
			  {
				  for(int i=blist.size()-1;i>=0;i--)
				  {
						BorrowTable b=blist.get(i);
						
						
				        	 
						
						      
						str.append("{").append("\"bookid\":\""+b.getBookson().getBook().getBookid()+"\"")
						.append(",")
						.append("\"id\":\""+b.getId() +"\"")
						.append(",")
						.append("\"userid\":\""+b.getUser().getUserid() +"\"")
						.append(",")
						.append("\"booktitle\":\""+b.getBookson().getBook().getBooktitle()+"\"")
						.append(",")
						.append("\"day\":\""+String.valueOf((Date.parse(b.getDeadline())-Date.parse(time))/86400000)+"\"")
						.append(",")
						.append("\"deadline\":\""+b.getDeadline().substring(0,10)+"\"")
						.append(",")
						.append("\"borrowdate\":\""+b.getBorrowdate().substring(0, 10)+"\"")
						.append(",")
						.append("\"status\":\""+b.getStatus()+"\"")
						.append(",")
					.append("\"returnstatus\":\""+b.getReturnstatus()+"\"")
			
					.append(",")
					.append("\"bookimages\":\""+b.getBookson().getBook().getBookimages()+"\"")
					.append(",")
					.append("\"category_id\":\""+b.getBookson().getBook().getCategory().getCategoryid()+"\"")
						.append("}").append(",");
					  
			    
				  }
				  
				  
					 out.write("["+str.substring(0,str.length()-1)+"]");
				  
			  }
			  
		
	      
		

	
	}

	
	public void init() throws ServletException {
		
	}
	


}
