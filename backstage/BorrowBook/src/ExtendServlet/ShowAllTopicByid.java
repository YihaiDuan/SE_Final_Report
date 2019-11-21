package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.TopicDao;
import dao.BookDao;
import entity.Book;
import entity.TopicBook;
import util.JsonFormat;


@WebServlet("/ShowAllTopicByid")
public class ShowAllTopicByid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowAllTopicByid() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
				doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		       response.setContentType("text/html;charset=utf-8");
		       request.setCharacterEncoding("utf-8");
		       
		       PrintWriter out=response.getWriter();
		       
		       String id=request.getParameter("id");
		       
		       
		       TopicDao tod=new TopicDao();
		       List<TopicBook>  tblist=tod.getTopicBookByid(Integer.parseInt(id));
		       
		       StringBuilder str=new StringBuilder();
		       
		       if(tblist!=null&&tblist.size()>0)
		       {
		    	   
		    	   for(int i=0;i<tblist.size();i++)
		    	   {
		    		   
		    		   
		    		   
		    		   TopicBook tb=tblist.get(i);
		    		   
		    		   BookDao bd=new BookDao();
		    		   Book b=bd.getBookbyid(tb.getBook().getBookid());
		    		   
		    		   String introduce=null;
		    		   
		    		   
		    		   
			  	         
			  	         if(b.getIntroduce()!=null)
			  	         {
			  	        	 
			  	        	
			  	        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
			  	         }
			  	         else
			  	         {
			  	        	 introduce="没有相关内容";
			  	        	 
			  	         }	 
			  	         
			            str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
						.append(",")
						.append("\"bookimages\":\""+b.getBookimages() +"\"")
						.append(",")
						.append("\"booktitle\":\""+b.getBooktitle()+"\"")
						.append(",")
					   .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
					   .append(",")
					   .append("\"borrownum\":\""+b.getBorrownum()+"\"")
					   .append(",")
					   .append("\"author\":\""+b.getAuthor()+"\"")
					   .append(",")
					   .append("\"typename\":\""+b.getCategory().getName()+"\"")
					   .append(",")
					   .append("\"bookintroduce\":\""+introduce+"\"")
						.append("}").append(",");
			    		   
		    		   
		    		   
		    		   
		    		   
		    	   }
		    	   
		    	   out.write("["+str.substring(0,str.length()-1)+"]"); 
		    	   
		       }
		       
		       
		       
		       
		
		
	}

}
