package sharebookservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShareBookDao;
import dao.UserDao;
import entity.ShareBook;
import entity.User;


@WebServlet("/AddShareBook")
public class AddShareBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddShareBook() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		         doPost(request,response);
		
		   
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter();
	      
	      Date date= new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//设置时间显示格式
		  String time= sdf.format(date);
	      
	      String isbn=request.getParameter("isbn");
	      String booktitle=request.getParameter("booktitle");
	      String author=request.getParameter("author");
	      String summary=request.getParameter("summary");
	      String pagenum=request.getParameter("pagenum");
	      String cash=request.getParameter("cash");
	      String userid=request.getParameter("userid");
	      String publish=request.getParameter("publish");
	      String publishdate=request.getParameter("publishdate");
	      String bookimages=request.getParameter("bookimages");
	      
	
	      
	      System.out.println(isbn);
	      System.out.println(booktitle);
	      System.out.println(author);
	      System.out.println(summary);
	      System.out.println(pagenum);
	      System.out.println(cash);
	      System.out.println(publish);
	      System.out.println(time);
	      System.out.println(userid);
	      
	      ShareBookDao sbd=new ShareBookDao();
	      boolean bol=sbd.getShareBookBol(userid,isbn);
	      
	      if(bol)
	      {
	    	  
	    	 out.write("failure");
	    	 System.out.println("我已经分享过了");
	    	  
	      }
	      else
	      {
	    	
      ShareBook s=new ShareBook();
	    	  
	    	
	   UserDao ud=new UserDao(); 	
	    
	   
	   User u=ud.getUserbyid(userid);
	   
	   
	   if(u!=null)
	   {
	    s.setUser(u);
	    s.setIsbn(isbn);
	    s.setBooktitle(booktitle);
	    s.setPublish(publish);
	    s.setPagenum(pagenum);
	    s.setAuthor(author);
	    s.setSummary(summary);
	    s.setSharedate(time);
	    s.setCash(cash);
	    s.setPagenum(pagenum);
	    s.setPublishdate(publishdate);
	    s.setBookimages(bookimages);
	   
	    
	    sbd.SaveShareBookData(s);
	    
	    
	    out.write("success");
	    
	   }
	  
	    	  
	 }

		
		
	}

}
