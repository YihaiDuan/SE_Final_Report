package newServlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.GroupBookDao;
import Extenddao.GroupDao;
import dao.BookDao;
import entity.Book;
import entity.GroupBook;
import entity.GroupMore;
import timer.TimerGroupDead;
import timer.TimerGroupStart;

//添加拼多多下的图书
@WebServlet("/AddGroupServlet")
public class AddGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddGroupServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
				doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		   response.setContentType("text/html;charset=UTF-8");
	       request.setCharacterEncoding("UTF-8");
		 
	       Date date =new Date();
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		   String nowdate= sdf.format(date);
	     
	       
	        String   createdate=request.getParameter("createdate");
	        String   deadline  =request.getParameter("deadline");
	        String   bookid="1";
	        
	      //得到组团开始的毫秒数
	        
	        Date startbegin=null;
	        Date startend =null;
	        
	        try {
				 
				 startbegin = sdf.parse(nowdate);
				 startend = sdf.parse(createdate);
				 
			    } 
			catch (ParseException e) 
			{
				
				e.printStackTrace();
			}
           
			
   long startbetween = (startend.getTime() - startbegin.getTime());// 得到组团开始的毫秒数
	        
	        
	        
	        
	     // 得到组团过期时间的毫秒数
	        Date deadbegin=null;
	        Date deadend=null;
	        
	        
			try {
				 
				 deadbegin = sdf.parse(nowdate);
				   deadend = sdf.parse(deadline);
				 
			    } 
			catch (ParseException e) 
			{
				
				e.printStackTrace();
			}
            
			
    long deadbetween = (deadend.getTime() - deadbegin.getTime());// 得到过期时间的毫秒数
             
             
             GroupMore cm=new GroupMore();
           
             cm.setGroupnum("2");
             cm.setMaxnum("3");
             cm.setCreatedate(createdate);
             cm.setDeadline(deadline);
             
             
             GroupDao cd=new GroupDao();
            
             int id=cd.SaveGroup(cm);
             
             
             GroupBookDao cbd=new GroupBookDao();
             
             GroupBook gb=new GroupBook();
             
             
         
             
             BookDao bd=new BookDao();
             Book b=bd.getBookbyid(bookid);
             
             gb.setBook(b);
             
             GroupMore gm=cd.getGroupMoreById(id);
             gb.setGroupmore(gm);
             
             
             cbd.SaveGroupBook(gb);
             
             
             
             System.out.println("我是要去判断的组团id"+id);
             
             
             
             //组团开始线程
             TimerGroupStart s=new TimerGroupStart((int)startbetween,id);
             s.start();
		    
             
             
               //组团过期线程
             TimerGroupDead t=new TimerGroupDead((int)deadbetween,id);
             t.start();
              
             
		
	}

}
