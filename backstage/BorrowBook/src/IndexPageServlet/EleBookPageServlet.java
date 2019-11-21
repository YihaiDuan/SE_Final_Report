/*package IndexPageServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IndexPageDao.EleBookPageDao;
import IndexPageDao.VIPPageDao;
import entity.Book;
import util.JsonFormat;


@WebServlet("/EleBookPageServlet")
public class EleBookPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public EleBookPageServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		    doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		
		   String pc=request.getParameter("elepagenum");
		 
		 System.out.println(".............."+pc);
		
		 EleBookPageDao tpd=new EleBookPageDao();
		  entity.Page<Book>  pageBean=tpd.getEleBookData(Integer.parseInt(pc));
		  
		
		
		     List<Book> blist= pageBean.getBeanlist();
			StringBuilder str=new StringBuilder();
			
			if(blist!=null&&blist.size()>0)
			{
				for(int i=0;i<blist.size();i++)
				{
					Book b=blist.get(i);
					  
					
		    		   
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