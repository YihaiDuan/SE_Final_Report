package MyTotalUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getMonthBorrowServlet")
public class getMonthBorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getMonthBorrowServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		              doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		

		response.setContentType("application/json;charset=utf-8"); 
		  request.setCharacterEncoding("utf-8");
		  
		  PrintWriter  out=response.getWriter();
		  
		  
		  String year=request.getParameter("year");
		  String userid=request.getParameter("userid");
		  
		  Date date=new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("MM");//设置时间显示格式
		  String curmonth= sdf.format(date);
		  
		  
		  Date date1=new Date();
          SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");//设置时间显示格式
		  String curyear= sdf1.format(date1);
		  
		  
		  System.out.println(curyear);
		  if(curyear.equals(year))
		  {
		  
		
		//2017/08/21 02:47:47
		 int num=0;
		  List d=new ArrayList();
		  for(int i=1;i<=Integer.parseInt(curmonth);i++)
		  {
			  
			 
			  String time=year+"/"+"0"+String.valueOf(i);
			  
			  DataDao dd=new DataDao();
			  num=dd.getMothBorrow(time,userid);
		      d.add(num);
		      
		  }
		  
		  StringBuilder str=new StringBuilder();
		  StringBuilder str2=new StringBuilder();
		  if(d!=null&&d.size()>0)
		  {
		  for(int i=0;i<d.size();i++)
		  {
			  
			  str.append(d.get(i)).append(",");
			  str2.append(i+1).append(",");
			  
		  }
		/*  {
		      canvasId: 'lineGraph',
		      type: 'line',
		      categories: [1,2,3],
		      series: [{
		        name: '借书量',
		        data: [1,2,3],
		      
		      }],
		     
		      width: 320,
		      height: 200
		    }*/
		  
		  String a="["+str2.substring(0,str2.length()-1)+"]";
		  String b="["+str.substring(0,str.length()-1)+"]";
		  String c="[{"+"\"name\":"+"\"借书量\","+"\"data\":"+b+"}]";
out.write("{"+"\"canvasId\":"+"\"lineGraph\","+"\"type\":"+"\"line\","+"\"width\":"+"\"320\","+"\"height\":"+"\"200\","+"\"categories\":"+a+","+"\"series\":"+c+"}");
		
		
		  }
		  }
		  else
		  {
			  
			  
				//2017/08/21 02:47:47
				 int num=0;
				  List d=new ArrayList();
				  for(int i=1;i<=12;i++)
				  {
					  
					 
					  String time=year+"/"+"0"+String.valueOf(i);
					  
					  DataDao dd=new DataDao();
					  num=dd.getMothBorrow(time,userid);
				      d.add(num);
				      
				  }
				  
				  StringBuilder str=new StringBuilder();
				  StringBuilder str2=new StringBuilder();
				  if(d!=null&&d.size()>0)
				  {
				  for(int i=0;i<d.size();i++)
				  {
					  
					  str.append(d.get(i)).append(",");
					  str2.append(i+1).append(",");
					  
				  }
				/*  {
				      canvasId: 'lineGraph',
				      type: 'line',
				      categories: [1,2,3],
				      series: [{
				        name: '借书量',
				        data: [1,2,3],
				      
				      }],
				     
				      width: 320,
				      height: 200
				    }*/
				  
				  String a="["+str2.substring(0,str2.length()-1)+"]";
				  String b="["+str.substring(0,str.length()-1)+"]";
				  String c="[{"+"\"name\":"+"\"借书量\","+"\"data\":"+b+"}]";
		out.write("{"+"\"canvasId\":"+"\"lineGraph\","+"\"type\":"+"\"line\","+"\"width\":"+"\"320\","+"\"height\":"+"\"200\","+"\"categories\":"+a+","+"\"series\":"+c+"}");
				
				
				  }	  
			  
			  
			  
			  
			  
			  
			  
			  
		  }
	
	}

}
