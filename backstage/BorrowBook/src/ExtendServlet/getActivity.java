package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.ActivityDao;
import dao.BookDao;
import entity.Book;
import entity.Discount;
import entity.GroupBook;
import entity.Reduce;
import util.JsonFormat;


@WebServlet("/getActivity")
public class getActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getActivity() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
				doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
		
		   String action=request.getParameter("action");
		   
		   ActivityDao ad=new ActivityDao();
		   
		   if("0".equals(action))
		   {
			   
			   
			   List<Reduce>  rlist=ad.getAllReduce();
			   StringBuilder str =new StringBuilder();
			   
			   if(rlist!=null&&rlist.size()>0)
			   {
				   
				  for(int i=rlist.size()-1;i>=0;i--)
				  {
					  
					  Reduce rd=rlist.get(i);
					  
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
			    		   
					  
					  
					  
				  }
				   
				     out.write("["+str.substring(0,str.length()-1)+"]"); 
				     System.out.println(0);
			   }
			   
			   
			   
			   
			   
		   }
		   else if("1".equals(action))
		   {
			   
			   
			   List<Discount>  dlist=ad.getAllDiscount();
			   StringBuilder str =new StringBuilder();
			   
			   if(dlist!=null&&dlist.size()>0)
			   {
				   
				  for(int i=dlist.size()-1;i>=0;i--)
				  {
					  
					  Discount d=dlist.get(i);
					
			  	         
			            str.append("{").append("\"id\":\""+d.getId()+"\"")
						.append(",")
						.append("\"num\":\""+d.getNum() +"\"")
						.append(",")
						.append("\"createdate\":\""+d.getCreatedate()+"\"")
						.append(",")
					   .append("\"deadline\":\""+d.getDeadline()+"\"")
					   .append(",")
					   .append("\"discount\":\""+d.getDiscount()+"\"")
					   .append(",")
					   .append("\"showstatus\":\""+d.getShowstatus()+"\"")
					   .append(",")
					   .append("\"typename\":\""+d.getTypename()+"\"")
					   .append(",")
					   .append("\"grade\":\""+d.getGradestatus()+"\"")
					   .append(",")
					   .append("\"typestatus\":\""+d.getTypestatus() +"\"")
					   
						.append("}").append(",");
			    		   
					  
					  
					  
				  }
				   
				     out.write("["+str.substring(0,str.length()-1)+"]"); 
				     
				     System.out.println(1);
			   }  
			   
			   
		   }
		   else
		   {
			   
  
			   
			   List<GroupBook>  dlist=ad.getAllGroupBook();
			   StringBuilder str =new StringBuilder();
			   
			   if(dlist!=null&&dlist.size()>0)
			   {
				   
				  for(int i=dlist.size()-1;i>=0;i--)
				  {
					  
					 GroupBook d=dlist.get(i);
					
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
			    		   
					  
					  
					  
				  }
				   
				     out.write("["+str.substring(0,str.length()-1)+"]"); 
				     
				     System.out.println(1);
			   
			
			   
			   
		   }
		   
		   
		   
		
		     
	}

	}
}
