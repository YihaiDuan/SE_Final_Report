package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.GroupBookDao;
import entity.GroupBook;


@WebServlet("/getBookinGroup")
public class getBookinGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getBookinGroup() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		     doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		   response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   
		   PrintWriter out=response.getWriter();
		   
		   String bookid=request.getParameter("bookid");
		  
		    GroupBookDao gbd=new GroupBookDao();
	        GroupBook gb=gbd.getGroupBookbyBookid(bookid);
	        StringBuilder str =new StringBuilder();
			 
			 if(gb!=null)
			 {
				 
				 //2 3 5
				 //9 8 7
				 
				/* String discount=null;
				 String num=null;
				 num=gb.getGroupmore().getGroupnum();
						 
					if(num.equals("2"))
					{
						discount="9";
						
					}
					else  if(num.equals("3"))
					{
						discount="8";
					}
					else
					{
						
						discount="7";
						
					}*/
						 
						 
				str.append("{").append("\"id\":\""+gb.getId()+"\"")
					.append(",")
					.append("\"groupid\":\""+gb.getGroupmore().getId() +"\"")
					.append(",")
					.append("\"bookid\":\""+gb.getBook().getBookid()+"\"")
					.append(",")
				   .append("\"bookimages\":\""+gb.getBook().getBookimages()+"\"")
				   .append(",")
				   .append("\"booktitle\":\""+gb.getBook().getBooktitle()+"\"")
				   .append(",")
				   .append("\"author\":\""+gb.getBook().getAuthor()+"\"")
				   .append(",")
				   .append("\"cash\":\""+gb.getBook().getCash()+"\"")
					.append("}"); 
				 
				     out.write(str.toString());
			 }
	
		
	}

}
