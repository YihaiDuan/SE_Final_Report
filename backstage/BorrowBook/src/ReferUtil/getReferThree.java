package ReferUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import entity.Book;
import entity.BorrowTable;


@WebServlet("/getReferThree")
public class getReferThree extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getReferThree() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		 response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   String id=request.getParameter("ids");
		   ReferDao rd=new ReferDao();
			
		   StringBuilder str=new StringBuilder(); 
		   List<Book>  newblist=new ArrayList<Book>();
		   
		   String ids[]=id.split(",");
		   
		   
		   for(int i=0;i<ids.length;i++)
		   {
			   BorrowTableDao btd=new BorrowTableDao();
			   BorrowTable bt=btd.getBorrowTablebyid(Integer.parseInt(ids[i]));
			   String cateid=bt.getBookson().getBook().getCategory().getCategoryid();
			   
		   
				  
				 List<Book>  blist=rd.getBookByCateThree(cateid);
				 if(blist!=null&&blist.size()>0)
				 {
				 for(int j=0;j<blist.size();j++)
				 {
					 
					 Book b=blist.get(i);
					 
					 newblist.add(b);
					 
					 
				 }
				 }
			   
		   
		   
		   
		   }
		   
		 
		   
		   if(newblist!=null&&newblist.size()>0)
		   {
			   
			   for(int i=0;i<newblist.size();i++)
			   {
				   
				   Book b=newblist.get(i);
				   
					str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
					.append(",")
					.append("\"typeid\":\""+b.getCategory().getCategoryid() +"\"")
					.append(",")
					.append("\"booktitle\":\""+b.getBooktitle()+"\"")
					.append(",")
					.append("\"bookimages\":\""+b.getBookimages()+"\"")
					.append(",")
					.append("\"cash\":\""+b.getCash()+"\"")
					.append("},");
				   
				   
				   
				   
			   }
			   
			   out.write("["+str.substring(0,str.length()-1)+"]");
		   }
		   
		   
		   

	}

}
