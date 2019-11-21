package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.CollectDao;
import entity.Book;
import entity.Collect;
import util.JsonFormat;


@WebServlet("/ShowMyCollect")
public class ShowMyCollect extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ShowMyCollect() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		    doPost(request,response);
		    
		    
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
	      
	      
	      String userid=request.getParameter("userid");
	      
	      
	      CollectDao cd=new CollectDao();
	      
	      List<Collect>  clist=cd.getCollectByUserid(userid);
	      
	      StringBuilder str=new StringBuilder();
	      
	        if(clist!=null&clist.size()>0)
	        {
	        	
	        	
	        	for(int i=clist.size()-1;i>=0;i--)
	        	{
	        		
	        		Collect c=clist.get(i);
	        		
	        	BookDao bd=new BookDao();
	        	
	        	 Book b=bd.getBookbyid(c.getBook().getBookid());
	        	if(b!=null)
	        	{
	        		String introduce=null;
	        		
	        		if(b.getIntroduce()!=null)
	        		{
	        			
	        			JsonFormat js=new JsonFormat();
	        			introduce=js.stringToJson(b.getIntroduce());
	        			
	        		}
	        		else
	        		{
	        			
	        			introduce=b.getIntroduce();
	        			
	        		}
	        		
	        		
	        		
	        		 str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
	     	        .append(",")
	     			.append("\"author\":\""+b.getAuthor() +"\"")
	     			 .append(",")
		     		.append("\"id\":\""+c.getId()+"\"")
		     		 .append(",")
			     		.append("\"categoryid\":\""+b.getCategory().getCategoryid()+"\"")
	     			
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
	     			.append("\"booknum\":\""+b.getBooknum()+"\"")

	     			
	     			.append(",")
	     			.append("\"author\":\""+b.getAuthor()+"\"")
	     			.append(",")
	     			.append("\"typename\":\""+b.getCategory().getName()+"\"")
	     			
	     			
	     			.append("}").append(",");
	     	        
	        		
	        		
	        	}//if
	        	
	        	
	        		
	        	}//for
	        	
	        	out.write("["+str.substring(0, str.length()-1)+"]");
	        	
	        	
	        }//if
	      
		
		
	}

}
