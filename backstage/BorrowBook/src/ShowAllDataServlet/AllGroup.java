package ShowAllDataServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.GroupBookDao;
import Extenddao2.RduceDao;
import dao.BookDao;
import entity.Book;
import entity.GroupBook;
import util.JsonFormat;


@WebServlet("/AllGroup")
public class AllGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AllGroup() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	      	doPost(request, response);
	}
public static void main(String [] args)
{
	
	 
	   StringBuilder str=new StringBuilder();
	   
	   GroupBookDao td=new GroupBookDao();
	   List<GroupBook>  tlist=td.getAllGroupBook();
	   
	   if(tlist!=null&&tlist.size()>0)
		  {
			  
			  for(int i=tlist.size()-1;i>=0;i--)
			  {
				  
				  
				  
				GroupBook to=tlist.get(i);
				
				  
			     String introduce=null;
				  
				  if(to.getBook().getIntroduce()!=null)
			         {
			        	 
			        	
			        	 introduce=JsonFormat.stringToJson(to.getBook().getIntroduce());
			         }
			         else
			         {
			        	 introduce="没有相关内容";
			        	 
			         }	 
		      	 
				  
					str.append("{").append("\"bookid\":\""+to.getBook().getBookid()+"\"")
					.append(",")
					.append("\"bookimages\":\""+to.getBook().getBookimages()+"\"")
					.append(",")
					.append("\"author\":\""+to.getBook().getAuthor() +"\"")
					.append(",")
					.append("\"typeid\":\""+to.getBook().getCategory().getCategoryid()+"\"")
					.append(",")
					.append("\"booktitle\":\""+to.getBook().getBooktitle()+"\"")
					.append(",")
					.append("\"introduce\":\""+to.getBook().getIntroduce()+"\"")
					.append(",")
					.append("\"borrownum\":\""+to.getBook().getBooknum()+"\"")
					
					.append("}").append(",");
		    		  
				 }
			  
			  
			   System.out.println("["+str.substring(0,str.length()-1)+"]");
			  
		  }
		  


}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		   response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   StringBuilder str=new StringBuilder();
		   
		   GroupBookDao td=new GroupBookDao();
		   List<GroupBook>  tlist=td.getAllGroupBook();
		   
		   if(tlist!=null&&tlist.size()>0)
			  {
				  
				  for(int i=tlist.size()-1;i>=0;i--)
				  {
					  
					  
					  
					  GroupBook to=tlist.get(i);
					
					  
				     String introduce=null;
					  
					  if(to.getBook().getIntroduce()!=null)
				         {
				        	 
				        	
				        	 introduce=JsonFormat.stringToJson(to.getBook().getIntroduce());
				         }
				         else
				         {
				        	 introduce="没有相关内容";
				        	 
				         }	 
			      	 
					  
						str.append("{").append("\"bookid\":\""+to.getBook().getBookid()+"\"")
						.append(",")
						.append("\"bookimages\":\""+to.getBook().getBookimages()+"\"")
						.append(",")
						.append("\"author\":\""+to.getBook().getAuthor() +"\"")
						.append(",")
						.append("\"typeid\":\""+to.getBook().getCategory().getCategoryid()+"\"")
						.append(",")
						.append("\"booktitle\":\""+to.getBook().getBooktitle()+"\"")
						.append(",")
						.append("\"introduce\":\""+to.getBook().getIntroduce()+"\"")
						.append(",")
						.append("\"borrownum\":\""+to.getBook().getBooknum()+"\"")
						
						.append("}").append(",");
			    		  
					 }
				  
				  
				   out.write("["+str.substring(0,str.length()-1)+"]");
				  
			  }
			  
		
		
	}

}
