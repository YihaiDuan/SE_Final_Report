package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.GroupBookDao;
import Extenddao2.GroupMainDao;
import entity.GroupBook;


@WebServlet("/BolGroupMaxNum")
public class BolGroupMaxNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BolGroupMaxNum() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		  response.setContentType("text/html;charset=utf-8");
		     request.setCharacterEncoding("utf-8");
		     
		     PrintWriter out=response.getWriter();
		
		      
		     String bookid=request.getParameter("bookid");
		      
		      
		      GroupBookDao gbd=new GroupBookDao();
		      GroupBook gb=gbd.getGroupBookbyBookid(bookid);
		      
		      
		      //该团的最大数
		      if(gb!=null)
		      {
		      String maxnum=gb.getGroupmore().getMaxnum();
		      GroupMainDao gmd=new GroupMainDao();
		      System.out.println("该团的最大数"+maxnum);
		      
		      //开了多少团
		      int  nownum=Integer.parseInt(gmd.getOpenMainGroupNum(gb.getId()).toString());
		      
		      System.out.println("开了多少团"+nownum);
		      
		      
		      if(nownum==Integer.parseInt(maxnum))
		      {
		    	 
		    	  out.write("1");
		    	  
		      }
		      else
		      {
		    	  
		    	  out.write("0");
		      }
		      }
		      else
		      {
		    	  
		    	  
		    	  System.out.println("不在团中");
		      }
	}

}
