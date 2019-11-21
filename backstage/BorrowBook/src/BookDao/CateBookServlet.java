package BookDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
import util.JsonFormat;


@WebServlet("/CateBookServlet")
public class CateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CateBookServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		          doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		   
		
		  response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
           PrintWriter out=response.getWriter() ;
		
		        
           String cateid=request.getParameter("search");
           String action=request.getParameter("action");
           String pc=request.getParameter("pc");
           
           
           //获取热门图书
           if(action.equals("0"))
           {
        	  
        	   
        	CategoryBookDao cbd=new CategoryBookDao();
        	
        entity.Page<Book> pagebean=cbd.getBookHotPage(Integer.parseInt(pc), cateid);
        	
        	List<Book>  blist=  pagebean.getBeanlist();
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
				   .append("\"introduce\":\""+introduce+"\"")
				  .append(",")
				   /*.append("\"cash\":\""+b.getCash()+"\"")
				   .append(",")*/
				   .append("\"evaluate\":\""+b.getEvaluatescore()+"\"")
					
					.append("}").append(",");
		    		
        		}//for
        		
       out.write("{"+"\"size\":"+"\""+pagebean.getAll()+"\","+"\"curnum\":"+"\""+blist.size()+"\","+"\"curpage\":"+"\""+pc+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");		
        		   
        		}//if
        	
        	   else
        	   {
        		   
      out.write("{" + "\"size\":" + "\"" +0+"\","+"\"curnum\":"+"\""+0+"\""+"}" ); 		   
        	   }
           
           
           
           }//外if
           
           
           //获取好评图书
           else if(action.equals("1"))
           {
        	   
        	   
           	CategoryBookDao cbd=new CategoryBookDao();
           	
           entity.Page<Book> pagebean=cbd.getGoodBookPage(Integer.parseInt(pc), cateid);
           	
           	List<Book>  blist=  pagebean.getBeanlist();
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
   				   .append("\"introduce\":\""+introduce+"\"")
   				  .append(",")
   				  /* .append("\"cash\":\""+b.getCash()+"\"")
   				   .append(",")*/
				   .append("\"evaluate\":\""+b.getEvaluatescore()+"\"")
   					
   					.append("}").append(",");
   		    		
           		}//for
           		
          out.write("{"+"\"size\":"+"\""+pagebean.getAll()+"\","+"\"curnum\":"+"\""+blist.size()+"\","+"\"curpage\":"+"\""+pc+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");		
           		   
           		}//if
           	
           	   else
           	   {
           		   
         out.write("{" + "\"size\":" + "\"" +0+"\","+"\"curnum\":"+"\""+0+"\""+"}" ); 		   
           	   }
              
        	   
        	   
           }//外if
           
       
	}

}
