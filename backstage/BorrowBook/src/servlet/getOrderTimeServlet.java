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
import java.util.Calendar;



@WebServlet("/getOrderTimeServlet")
public class getOrderTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public getOrderTimeServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		          doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
	      
	      
	        Date date= new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置时间显示格式
			 
	        String current= sdf.format(date);
		      
	        System.out.println(current);
	        
	        
	        Calendar rightNow = Calendar.getInstance();
		    rightNow.add(Calendar.HOUR_OF_DAY,48);
		    Date dt=rightNow.getTime();
		    String endtime=sdf.format(dt);
		    System.out.println(endtime);
		    
 out.write("{"+"\"current\":"+"\""+current+"\","+"\"endtime\":"+"\""+endtime+"\""+"}");    
		    
		    
		 /*   SimpleDateFormat sdf1 = new SimpleDateFormat("HH");//设置时间显示格式
			 
	        String currenthour= sdf1.format(date);
			
	           System.out.println(currenthour);
	           
	           String time1="";
	           String time2="";
	           String time3="";
	           String time4="";
			
		      if(Integer.parseInt(currenthour)>=13&&Integer.parseInt(currenthour)<=23)
		      {
		    
		    	  time1=orderdate1+" 13:00-18:00";
		    	  
		    	  System.out.println("time1:"+time1);
		    	  
		    	    Calendar rightNow2 = Calendar.getInstance();
		    	    rightNow2.add(Calendar.HOUR_OF_DAY,48);
		    	    Date dt2=rightNow2.getTime();
		    	    String orderdate2=sdf.format(dt2);
		    	   
		    	    
		    	    time2=orderdate2+" 06:00-12:00";
		    	    
		    	    System.out.println("time2:"+time2);
		    	    
		    	    
		    	    time3=orderdate2+" 13:00-18:00";
		    	    
		    	    System.out.println("time3:"+time3);
		    	    
		    	    Calendar rightNow3 = Calendar.getInstance();
		    	    rightNow3.add(Calendar.HOUR_OF_DAY,72);
		    	    Date dt3=rightNow3.getTime();
		    	    String orderdate3=sdf.format(dt3);
		    	    
	               time4=orderdate3+" 06:00-12:00";
		    	    
		    	    System.out.println("time4:"+time4);
		         
		     
		      }
		      if(Integer.parseInt(currenthour)>=00&&Integer.parseInt(currenthour)<=05)
		      {
		    	  
		    	 time1=orderdate1+" 13:00-18:00";
		    	 
		    	 System.out.println("time1:"+time1);
		    	  
		    	    Calendar rightNow2 = Calendar.getInstance();
		    	    rightNow2.add(Calendar.HOUR_OF_DAY,48);
		    	    Date dt2=rightNow2.getTime();
		    	    String orderdate2=sdf.format(dt2);
		    	   
		    	    
		    	    time2=orderdate2+" 06:00-12:00";
		    	    
		    	    System.out.println("time2:"+time2);
		    	    
		    	    
		    	    time3=orderdate2+" 13:00-18:00";
		    	    
		    	    System.out.println("time3:"+time3);
		    	    
		    	    Calendar rightNow3 = Calendar.getInstance();
		    	    rightNow3.add(Calendar.HOUR_OF_DAY,72);
		    	    Date dt3=rightNow3.getTime();
		    	    String orderdate3=sdf.format(dt3);
		    	    
	            time4=orderdate3+" 06:00-12:00";
		    	    
		    	    System.out.println("time4:"+time4);
		    	
		      }
		      if(Integer.parseInt(currenthour)>=06&&Integer.parseInt(currenthour)<=12)
		      {
		    	  
		    	 time1=orderdate1+" 06:00-12:00";
		    	 System.out.println(time1);
		    	 time2=orderdate1+" 13:00-18:00";
		    	 System.out.println(time2);
		    	 
		    	 
		    	  Calendar rightNow2 = Calendar.getInstance();
		    	  rightNow2.add(Calendar.HOUR_OF_DAY,48);
		    	  Date dt2=rightNow2.getTime();
		    	  String orderdate2=sdf.format(dt2);
		    	  
		    	  
		    		 time3=orderdate2+" 06:00-12:00";
			    	 System.out.println(time3);
			    	 time4=orderdate2+" 13:00-18:00";
			    	 System.out.println(time4);
		    	 
		    	 
		      }
		      

  out.write("{"+"\"time1\":"+"\""+time1+"\","+"\"time2\":"+"\""+time2+"\","+"\"time3\":"+"\""+time3+"\","+"\"time4\":"+"\""+time4+"\""+"}");*/
		      
	
	}

}
