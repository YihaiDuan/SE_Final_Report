/*package IndexPageServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import entity.Book;
import entity.Reduce;
import util.JsonFormat;


@WebServlet("/ReducePageServlet")
public class ReducePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReducePageServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		     doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		
		   String pc=request.getParameter("reducepagenum");
		 
		 System.out.println(".............."+pc);
		
		 ReducePageDao tpd=new ReducePageDao();
		  entity.Page<Reduce>  pageBean=tpd.getReduceData(Integer.parseInt(pc));
		  
		
		
		     List<Reduce> blist= pageBean.getBeanlist();
			StringBuilder str=new StringBuilder();
			
			if(blist!=null&&blist.size()>0)
			{
				for(int i=0;i<blist.size();i++)
				{
					Reduce rd=blist.get(i);
					  
					   BookDao bd=new BookDao();
		    		   Book b=bd.getBookbyid(rd.getBookid());
		    		   
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
					   .append(",")
					   .append("\"oldprice\":\""+b.getOldprice()+"\"")
					   .append(",")
					   .append("\"newprice\":\""+b.getNewprice()+"\"")
						.append("}").append(",");
			    		   
					  
					
				}//for
out.write("{"+"\"maxsize\":"+"\""+pageBean.getAll()+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");
			
		  }//if
	}

}
*/