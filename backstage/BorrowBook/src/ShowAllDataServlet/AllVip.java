package ShowAllDataServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.RduceDao;
import IndexPageDao.AllVipDao;
import dao.BookDao;
import entity.Book;
import entity.Reduce;
import util.JsonFormat;


@WebServlet("/AllVip")
public class AllVip extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AllVip() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		   StringBuilder str=new StringBuilder();
		   
		   AllVipDao td=new AllVipDao();
		   List<Book>  tlist=td.getAllVipBook();
		   
		   if(tlist!=null&&tlist.size()>0)
			  {
				  
				  for(int i=tlist.size()-1;i>=0;i--)
				  {
					  
					 
					  Book b=tlist.get(i);
					  
					  
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
						.append("\"bookimages\":\""+b.getBookimages()+"\"")
						.append(",")
						.append("\"author\":\""+b.getAuthor() +"\"")
						.append(",")
						.append("\"typeid\":\""+b.getCategory().getCategoryid()+"\"")
						.append(",")
						.append("\"booktitle\":\""+b.getBooktitle()+"\"")
						.append(",")
						.append("\"introduce\":\""+b.getIntroduce()+"\"")
						.append(",")
						.append("\"borrownum\":\""+b.getBooknum()+"\"")
						
						.append("}").append(",");
			    		  
					 }
				  
				  
				   out.write("["+str.substring(0,str.length()-1)+"]");
				  
			  }
			  
		
	
	}

}
