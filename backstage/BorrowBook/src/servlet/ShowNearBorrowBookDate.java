package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import NearPeopleEntity.NearPeopleEn;
import dao.BorrowTableDao;
import dao.UserDao;
import entity.BorrowTable;
import entity.User;
import util.JsonFormat;
import util.MapDistance;


@WebServlet("/ShowNearBorrowBookDate")
public class ShowNearBorrowBookDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowNearBorrowBookDate() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		           doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		

		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter();
	      
	      
	       String longitude=request.getParameter("longitude");
	       String latitude=request.getParameter("latitude");
	       String userid=request.getParameter("userid");
	       
	       
	       Date date= new Date();//创建一个时间对象，获取到当前的时间
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//设置时间显示格式
			 
	          String time= sdf.format(date);//将当前时间格式化为需要的类型

			   System.out.println(time);
	       
			      UserDao ud=new UserDao();
			      User u=ud.getUserbyid(userid);
			      
			      u.setUserid(userid);
			      u.setLatitude(latitude);
			      u.setLongitude(longitude);
			      
			      
			     ud.UpdatePosition(u);
			     
			     User u2=ud.getUserbyid(userid);
			      
			     System.out.println("未更新的经度"+longitude);
			      System.out.println("未更新的纬度"+latitude);
			     System.out.println("我是该用户更新后的经度"+u2.getLongitude());
			      System.out.println("我是该用户更新后的纬度"+u2.getLatitude());
			      System.out.println("我是该用户"+userid);
	      
	     List<NearPeopleEn>  nplist=new ArrayList<NearPeopleEn>(0);
	     
	     
	     
	     
	     BorrowTableDao btd=new BorrowTableDao();
	      
	      List<BorrowTable> blist=btd.getAllBorrow();
	      if(blist!=null&&blist.size()>0)
	      {
	      
	        for(int i=0;i<blist.size();i++)
	        {
	        	
	        	 BorrowTable b=blist.get(i);
	              
	             String latitudeother=b.getUser().getLatitude();
                 String longitudeother=b.getUser().getLongitude();
                 

	    		int  day=(int) ((Date.parse(b.getDeadline())-Date.parse(time))/86400000);
			         System.out.println("输出剩下的天数"+day);
            
                 String distance="0";
	        	
                 if(u2.getLongitude()!=null&&u2.getLatitude()!=null)
         	       {                  
              		
                  	  if(longitudeother!=null&&latitudeother!=null)
                  	  {
                  		  distance=MapDistance.Distance(Double.valueOf(u2.getLongitude()),Double.valueOf(u2.getLatitude()),Double.valueOf(longitudeother),Double.valueOf(latitudeother));
                  	  }
                  	

         	       }
                
  	              System.out.println(distance);
                 
                 
  	           	 if(!userid.equals(b.getUser().getUserid()))
  	           	 {
  	           	
  	           	NearPeopleEn npe=new NearPeopleEn();
  	           	
  	           	
  	           	
  	          
  	           	npe.setDistance(distance);
  	           	npe.setDate(String.valueOf(day));
  	           	npe.setId(b.getId());
  	               nplist.add(npe);
  	           	 
  	           	 }
  	           	
  	           
	        
	        }//for
	        
	        
	        
	      //按照距离排序
	         // 从高到低
	        
	        
	        Collections.sort(nplist, new Comparator<NearPeopleEn>(){  
	        	  
	            /*  
	             * int compare(Student o1, Student o2) 返回一个基本类型的整型，  
	             * 返回负数表示：o1 小于o2，  
	             * 返回0 表示：o1和o2相等，  
	             * 返回正数表示：o1大于o2。  
	             */  
	            public int compare(NearPeopleEn o1, NearPeopleEn o2) {  
	              
	                //按照学生的年龄进行升序排列  
	                if(Double.valueOf(o1.getDate())  >Double.valueOf(o2.getDate()) ){  
	                    return 1;  
	                }  
	                if(Double.valueOf(o1.getDate())==Double.valueOf(o2.getDate()) ){  
	                    return 0;  
	                }  
	                return -1;  
	            }  
	        });   
	        
	        
	        
	        System.out.println(nplist);
	    	java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");      
	            
	        
	        
	        StringBuilder str3=new StringBuilder();
	        
	        for(int i=0;i<nplist.size();i++)
	        {
	        	
	        	
	        	NearPeopleEn  npe=nplist.get(i);
	        	
	        
	        	BorrowTableDao bd=new BorrowTableDao();
	        	BorrowTable b= bd.getBorrowbyId(npe.getId());
	        	
    	
	        	
	        	int day=Integer.parseInt(npe.getDate());
    			
	 if(day>0)
	{
		
		String introduce =null;
		
		if(b.getBookson().getBook().getBookimages()!=null)
		{
			
			
			introduce=JsonFormat.stringToJson(b.getBookson().getBook().getIntroduce());
		}
		else
		{
			introduce="内容未知";
			
			
		}
		 double dis=Double.valueOf(df.format(Double.valueOf(npe.getDistance())/1000));
	
str3.append("{").append("\"borrowid\":\""+b.getId()+"\"")

.append(",")
.append("\"booktitle\":\""+b.getBookson().getBook().getBooktitle()+"\"")
.append(",")
.append("\"bookimages\":\""+b.getBookson().getBook().getBookimages()+"\"")
.append(",")
.append("\"distance\":\""+dis+"\"")
.append(",")
.append("\"bookid\":\""+b.getBookson().getBook().getBookid()+"\"")
.append(",")
.append("\"author\":\""+b.getBookson().getBook().getAuthor()+"\"")
.append(",")
.append("\"deadline\":\""+b.getDeadline()+"\"")
.append(",")
.append("\"userid\":\""+b.getUser().getUserid()+"\"")
.append(",")
.append("\"userimages\":\""+b.getUser().getUserimages()+"\"")
.append(",")
.append("\"nickname\":\""+b.getUser().getNickname()+"\"")
.append(",")
.append("\"sex\":\""+b.getUser().getSex()+"\"")
.append(",")
.append("\"introduce\":\""+introduce+"\"")
.append(",")
.append("\"day\":\""+day+"\"")

.append("}").append(","); 

	}//day
	            		 
  }//for
	    	
	        if(str3.length()>0)
		    {
		    		
		    out.write("["+str3.substring(0,str3.length()-1)+"]");
		    }
		    else
		    {
		    	out.write("0");
		    	
		    }
		    	    	
	        	
	        	
	        	
	        }//if
	        
	        
	      }
	      
	   

	
	

}
