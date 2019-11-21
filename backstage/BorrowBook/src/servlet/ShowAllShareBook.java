package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShareBookDao;
import dao.UserDao;
import entity.ShareBook;
import entity.User;
import util.MapDistance;


@WebServlet("/ShowAllShareBook")
public class ShowAllShareBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ShowAllShareBook() 
    {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter();
	      
	      
	       String longitude=request.getParameter("longitude");
	       String latitude=request.getParameter("latitude");
	       String userid=request.getParameter("userid");
	       
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
	      
	      
	      
	      
	        ShareBookDao sdd=new ShareBookDao();
	      
	      
	      List<ShareBook>  slist= sdd.getAllShareBook();
	      StringBuilder str=new StringBuilder();
	      
	             if(slist!=null&&slist.size()>0)
	             {
	            	 
	            	 for(int i=slist.size()-1;i>=0;i--)
	            	 {
	            		 
	            		ShareBook s=slist.get(i); 
	            	
	            	  String latitudeother=s.getUser().getLatitude();
	                  String longitudeother=s.getUser().getLongitude();
	             
	                  System.out.println("对面用户纬度"+i+"///////////////"+latitudeother);
	                  System.out.println("对面用户经度"+i+"///////////////"+longitudeother);
	                
	                  String distance=null;
	                  if(!u2.getLongitude().equals("0")&&!u2.getLatitude().equals("0"))
	       	       {                  
	            		
	                	  if(longitudeother!=null&&latitudeother!=null)
	                	  {
	                		  
           distance=MapDistance.Distance(Double.valueOf(u2.getLongitude()),Double.valueOf(u2.getLatitude()),Double.valueOf(longitudeother),Double.valueOf(latitudeother));
         
	                	  }
	                	  else
	                	  {
	              distance=MapDistance.Distance(Double.valueOf(u2.getLongitude()),Double.valueOf(u2.getLatitude()),0,0);            		  
	                		  
	                	  }

	       	       }
	                  else
	                  {
	                	  
	                 distance="0"; 	  
	                	  
	                  }
	                  System.out.println("距离"+i+"///////////////"+distance);
	                  
	                
       
String  show=s.getAuthor()+" "+s.getPublish()+" "+s.getCash();	                  
	                  
str.append("{").append("\"shareid\":\""+s.getSharebookid()+"\"")
.append(",")
.append("\"show\":\""+show+"\"")
.append(",")
.append("\"nickname\":\""+s.getUser().getNickname()+"\"")
.append(",")
.append("\"booktitle\":\""+s.getBooktitle()+"\"")
.append(",")
.append("\"distance\":\""+distance+"\"")
.append(",")
.append("\"bookimages\":\""+s.getBookimages()+"\"")
.append("}").append(",");  
	            		 
	            		 
	                
	            	 }
	            	 
	            	 out.write("["+str.substring(0,str.length()-1)+"]");
	            	 System.out.println(str.substring(0,str.length()-1));
	              }
	           
	      
	      
	      
	      

		   
	}

}
