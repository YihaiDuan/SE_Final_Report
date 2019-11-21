/*package IndexPageServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.GroupBookDao;

import dao.BookDao;
import entity.Book;
import entity.GroupBook;
import entity.GroupMain;
import entity.GroupMember;
import entity.Topic;


@WebServlet("/GroupPageServlet")
public class GroupPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GroupPageServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		     doPost(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		
		   String pc=request.getParameter("grouppagenum");
		 
		 System.out.println(".............."+pc);
		
		  GroupPageDao tpd=new GroupPageDao();
		  entity.Page<GroupBook>  pageBean=tpd.getGroupData(Integer.parseInt(pc));
		  
		
		
		     List<GroupBook> blist= pageBean.getBeanlist();
			StringBuilder str=new StringBuilder();
			
			
			if(blist!=null&&blist.size()>0)
			{
				for(int i=0;i<blist.size();i++)
				{
					 GroupBook d=blist.get(i);
					 
					
			
				   
						
					 BookDao bd=new BookDao();
					 Book b=bd.getBookbyid(d.getBook().getBookid());
					 
					 
			  	         
			            str.append("{").append("\"id\":\""+d.getId()+"\"")
						.append(",")
						.append("\"bookid\":\""+b.getBookid()+"\"")
						.append(",")
						.append("\"bookimages\":\""+b.getBookimages()+"\"")
						.append(",")
					   .append("\"booktitle\":\""+b.getBooktitle()+"\"")
					   .append(",")
					   .append("\"author\":\""+b.getAuthor()+"\"")
					   .append(",")
					   .append("\"createdate\":\""+d.getGroupmore().getCreatedate()+"\"")
					   .append(",")
					   .append("\"deadline\":\""+d.getGroupmore().getDeadline()+"\"")
					   .append(",")
					   .append("\"maxnum\":\""+d.getGroupmore().getMaxnum()+"\"")
					   .append(",")
					   .append("\"groupnum\":\""+d.getGroupmore().getGroupnum()+"\"")
					   .append(",")
					   .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
					 
					  
					   
						.append("}").append(",");
			    		   
					
					
				}//for
out.write("{"+"\"maxsize\":"+"\""+pageBean.getAll()+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");
			
			
			}//if
		
	}

}
*/