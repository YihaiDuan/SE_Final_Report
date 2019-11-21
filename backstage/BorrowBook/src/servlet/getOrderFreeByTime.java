package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getOrderFreeByTime")
public class getOrderFreeByTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getOrderFreeByTime() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
	      
	      
	      String date=request.getParameter("date");
		
	        Date date1= new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//设置时间显示格式
			 
	        String current= sdf.format(date1);
	      
	        System.out.println(date);
	        System.out.println(current);
	        
	        int day=(int) ((Date.parse(date.replace("-","/"))-Date.parse(current))/86400000);
	        System.out.println(day+1);
	        
	        out.write("{"+"\"day\":"+"\""+(day+1)+"\""+"}");
	}

}
