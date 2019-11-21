package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import dao.UserDao;
import entity.BorrowTable;
import entity.User;


@WebServlet("/getBorrowLanInfo")
public class getBorrowLanInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getBorrowLanInfo() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 response.setContentType("text/html;charset=utf-8");
         request.setCharacterEncoding("utf-8");
       
         PrintWriter out=response.getWriter();
         
         
         String userid=request.getParameter("userid");
        
         
         System.out.println("-----------------------"+userid);
         
         BorrowTableDao bto=new BorrowTableDao();
         
          
         List<BorrowTable>  blist=bto.getBookNoScanNoDeadLine(userid);
         StringBuilder str=new StringBuilder();
         String json=null;
         
         float totalcash=0.0f;
         
         
       if(blist!=null&&blist.size()>0)
       {
        	 
        for(int i=blist.size()-1;i>=0;i--)	
        {
        	 
        	 BorrowTable bt=blist.get(i);
        	
        	 
        	  
        	   totalcash+=bt.getBookson().getBook().getCash();
        	
     		str.append("{").append("\"id\":\""+bt.getId()+"\"")
			.append(",")
			.append("\"bookid\":\""+bt.getBookson().getBook().getBookid() +"\"")  
			.append(",")
			.append("\"booktitle\":\""+bt.getBookson().getBook().getBooktitle()+"\"")
			.append(",")
			.append("\"bookimages\":\""+bt.getBookson().getBook().getBookimages()+"\"")
			.append(",")
			.append("\"author\":\""+bt.getBookson().getBook().getAuthor()+"\"")
			.append(",")
			.append("\"publish\":\""+bt.getBookson().getBook().getPublish()+"\"")
			.append(",")
		   .append("\"borrowdate\":\""+bt.getBorrowdate().substring(0,10)+"\"")
		   .append(",")
		   .append("\"booksonid\":\""+bt.getBookson().getBooksonid()+"\"")
		   .append(",")
		   .append("\"isbn\":\""+bt.getBookson().getBook().getPublishnumber()+"\"")
		   .append(",")
		   .append("\"price\":\""+bt.getBookson().getBook().getCash()+"\"")
		   .append(",")
		   .append("\"selected\":\""+false+"\"")
		   .append(",")
		   .append("\"txtStyle\":\""+""+"\"")
		   .append(",")
		   .append("\"deadline\":\""+bt.getDeadline()+"\"")
		   
			.append("}").append(",");
    	   
            
         }
      
        
       UserDao ud=new UserDao();
       User u=ud.getUserbyid(userid);
        
        
    json="["+str.substring(0,str.length()-1)+"]";
        
       out.write(json);
    
       }  
       else
       {
    	   
    	   
    	   out.write("610");
       }
       
		
		
		
		
		
	}

}
