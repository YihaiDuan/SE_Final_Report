package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.BookSonDao;
import entity.Book;
import util.JsonFormat;


@WebServlet("/BookDetailServlet")
public class BookDetailServlet extends HttpServlet {

	
	public BookDetailServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
		
	}

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  
		
		 
		    doPost(request,response);
		 
		    
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		  response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
		  
	      
	        String bookid=request.getParameter("bookid");
	        
	        BookDao bd=new BookDao();
	        Book  b=bd.getBookbyid(bookid);
	       
	        StringBuilder str=new StringBuilder();
	        if(b!=null)
	        {
	        	
	        	 //获取藏数量
		        Object totalnum=0;
		        BookSonDao bs=new BookSonDao();
		        //当前可借数量
		        Object borrownum=0;
		        borrownum=bs.getBookSnoBorrowStatusByBookid(bookid);
		        
		        totalnum=bs.getTotalNumByBookid(bookid);
		        
		 	   String list=null;
	  	         String guidreading=null;
	  	         String review=null;
	  	         String introduce=null;
	  	       String list3[]=null;
		        StringBuilder strlist=new StringBuilder();
		         
		         if(b.getList()!=null)
		         {
		        	 list=JsonFormat.stringToJson(b.getList()).replaceAll("@+","@");
		        	list3=list.split("@");
		        	
		        	if(list3.length>=3)
		        	{
		        		
		        		
		        		
		        		for(int i=0;i<3;i++)
		        		{
		        			 strlist.append("{").append("\"list\":\""+list3[i]+"\"")
			        		 
			      	         .append("},");
		        		}
		        		
		        	
		        	}
		        	else
		        	{
		        		for(int i=0;i<list3.length;i++)
		        		{
		        			
	                         strlist.append("{").append("\"list\":\""+list3[i]+"\"")
			        		 
			      	         .append("},");
		        			
		        			
		        		}
		        		
		        		
		        	}
		        	
		        	
		        	
		        
		         }
		         else
		         {
		        		list="没有相关内容";
		        	 
		         }
	  	         
	  	         
	  	        	 
	  	         if(b.getGuidreading()!=null)
	  	         {
	  	        	 guidreading=JsonFormat.stringToJson(b.getGuidreading());
	  	        	
	  	         }
	  	         else
	  	         {
	  	        	 guidreading="没有相关内容";
	  	        	 
	  	         }
	  	         
	  	         
	  	         
	  	         if(b.getReview()!=null)
	  	         {
	  	        	 review=JsonFormat.stringToJson(b.getReview());
	  	        	
	  	         }
	  	         else
	  	         {
	  	        	 review="没有相关内容";
	  	        	 
	  	         }
	  	         
	  	         
	  	         
	  	         if(b.getIntroduce()!=null)
	  	         {
	  	        	 
	  	        	
	  	        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
	  	         }
	  	         else
	  	         {
	  	        	 introduce="没有相关内容";
	  	        	 
	  	         }	 
	        	 
	  	       //是否降价
	  	       String oldprice =b.getOldprice();
	  	       String newprice=b.getNewprice();
	  	       boolean cutbol=false;
	  	       
	  	       if(!oldprice.equals(newprice))
	  	       {
	  	    	   
	  	    	   cutbol=true;
	  	    	   
	  	       }
	  	       
		        
		        
		   
	        str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
	        .append(",")
			.append("\"author\":\""+b.getAuthor() +"\"")
			
			.append(",")
			.append("\"bookimages\":\""+b.getBookimages() +"\"")
			
			.append(",")
			.append("\"booktitle\":\""+b.getBooktitle()+"\"")
			
			.append(",")
			.append("\"introduce\":\""+introduce+"\"")
			
			.append(",")
			.append("\"publish\":\""+b.getPublish()+"\"")
			
			.append(",")
			.append("\"publishnumber\":\""+b.getPublishnumber()+"\"")
			
			.append(",")
			.append("\"totalnum\":\""+totalnum+"\"")
			
			.append(",")
			.append("\"booknum\":\""+b.getBooknum()+"\"")
			
			.append(",")
			.append("\"guidreading\":\""+guidreading+"\"")
			.append(",")
			.append("\"cash\":\""+b.getCash()+"\"")
			.append(",")
			.append("\"review\":\""+review+"\"")
			.append(",")
			.append("\"list\":\""+list+"\"")
			.append(",")
 			.append("\"cash\":\""+b.getCash()+"\"")
 			.append(",")
 			.append("\"borrownum\":\""+borrownum+"\"")
 			.append(",")
 			.append("\"typename\":\""+b.getCategory().getName()+"\"")
 			.append(",")
			.append("\"typeid\":\""+b.getCategory().getCategoryid()+"\"")
 			.append(",")
 			.append("\"eleprice\":\""+b.getEleprice()+"\"")
 			.append(",")
 			.append("\"position\":\""+b.getPosition()+"\"")
 			.append(",")
			.append("\"score\":\""+b.getEvaluatescore()+"\"")
			.append(",")
			.append("\"oldprice\":\""+b.getOldprice()+"\"")
			.append(",")
			.append("\"newprice\":\""+b.getNewprice()+"\"")
			.append(",")
			.append("\"cutbol\":\""+cutbol+"\"")
			.append(",")
			.append("\"strlist\":"+"["+strlist.substring(0,strlist.length()-1)+"]")
 			
			.append("}").append(",");
	        
		  
	         out.write(str.substring(0, str.length()-1));
	         
		  
	        }
		  
		
	
	}

	
	public void init() throws ServletException {
		
	}

}
