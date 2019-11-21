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


@WebServlet("/getTransmitBorrowPay")
public class getTransmitBorrowPay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public getTransmitBorrowPay() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		          doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		

		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
      
        PrintWriter out=response.getWriter();
        
        
        String id=request.getParameter("id");
       
        
       
       
           BorrowTableDao btd=new BorrowTableDao();
        
             BorrowTable bt=btd.getBorrowbyId(Integer.parseInt(id));
        
                
                StringBuilder str=new StringBuilder();
       
                if(bt!=null)
               {
       	 
    
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
		   .append("\"cash\":\""+bt.getBookson().getBook().getCash()+"\"")
		   
			.append("}");
  
         out.write(str.toString());
   
      }  
      else
      {
   	   
   	   
   	   out.write("610");
      }
      
	
		
	}



}
