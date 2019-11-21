package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.NoticeDao;
import entity.Notice;


@WebServlet("/getNoticeServlet")
public class getNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getNoticeServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		     doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
	      
	      
	      NoticeDao nod=new NoticeDao();
	      
	      
	      List<Notice>  nolist=nod.getSystemNotice();
	      StringBuilder str=new StringBuilder();
	      
	      if(nolist!=null&&nolist.size()>0)
	      {
	    	  
	    	  for(int i=nolist.size()-1;i>=0;i--)
	    	  {
	    		  Notice no=nolist.get(i);
	    		  
	 str.append("{"+"\"date\":"+"\""+no.getDate()+"\","+"\"content\":"+"\""+no.getContent()+"\""+"},");
	    		  
	    		  
	    		  
	    	  }
	    	  
	    	  
	    	  out.write("["+str.substring(0,str.length()-1)+"]");
	    	  
	      }
	      
	      
	
	}

}
