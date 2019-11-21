package DynamicServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.DynamicAdmireDao;
import Extenddao2.DynamicDao;
import Extenddao2.FanDao;
import dao.UserDao;
import entity.Dynamic;
import entity.DynamicAdmire;
import entity.User;


@WebServlet("/AddDynamicAdmireDetail")
public class AddDynamicAdmireDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddDynamicAdmireDetail() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	     
		  response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
          PrintWriter out=response.getWriter() ;
          
          
           String userid=request.getParameter("userid");
           String dynamicid=request.getParameter("dynamicid");
           String action=request.getParameter("action");
          
           DynamicAdmire da=new DynamicAdmire();
          
           UserDao ud=new UserDao();
           User u=ud.getUserbyid2(userid);
           
           
          
           DynamicDao dd=new DynamicDao();
           Dynamic d=dd.getDynamicByid2(Integer.parseInt(dynamicid));
          
           
           if(action.equals("add"))
           {
        	   da.setDynamic(d);
               da.setUser(u);
               dd.SaveDynamicAdmire(da);
        	   
           }
           else
           {
        	   
        	 dd.DeleteDynamicAdmire(userid,Integer.parseInt(dynamicid));
        	   
           }
           
           
           //点赞和撤销赞之后去获取最新动态列表
           
    	     Dynamic d2=dd.getDynamicByid(Integer.parseInt(dynamicid));
    	      StringBuilder str=new StringBuilder();
    	      
    	        if(d2!=null)
    	         {
    	        	
    	    	     String images[]=d2.getImages().split(",");
    	    		  StringBuilder str2 =new StringBuilder();
    	    		  
    	    		  
    	    	  	     //评论总数
    	int count=Integer.parseInt(dd.CountComment(d2.getDynamicid()).toString())+ Integer.parseInt(dd.CountReply(d2.getDynamicid()).toString());  
    	    		  	    	
    	  //是否被点过赞
    	  DynamicAdmireDao dad=new DynamicAdmireDao();
    	  boolean admirebol=dad.DynamicAdmire(d2.getDynamicid(), userid);
    	  
    	  
    	  int fanbol=0;
    	  
    	  //是否被关注
    	  if(!userid.equals(d2.getUser().getUserid()))
    	  {
    		  
    	  FanDao fd=new FanDao();
    	  fanbol=fd.FanBol(userid,d2.getUser().getUserid());
    	  
    	  }
    	  else
    	  {
    		  fanbol=2;
    		  
    	  }
    	  
    	  System.out.println("////////////"+userid);
    	  System.out.println("///////////"+d2.getUser().getUserid());
    	
    	
    	
    	    		  for(int j=0;j<images.length;j++)
    	    		  {
    	    			  str2.append("{").append("\"img\":\""+images[j]+"\"")
    						
    					   .append("}").append(",");
    		    		  
    	    			 
    	    		  }
    	    		 String img=str2.substring(0,str2.length()-1).toString();
    	    		 
    	    		 System.out.println(img);
    	    		  
    	    		  
    	    		  str.append("{").append("\"dynamicid\":\""+d2.getDynamicid()+"\"")
    					.append(",")
    					.append("\"describ\":\""+d2.getDescrib() +"\"")
    					.append(",")
    					.append("\"userid\":\""+d2.getUser().getUserid()+"\"")
    					.append(",")
    				   .append("\"userimages\":\""+d2.getUser().getUserimages()+"\"")
    				   .append(",")
    				   .append("\"nickname\":\""+d2.getUser().getNickname()+"\"")
    				   .append(",")
    				   .append("\"bookid\":\""+d2.getBook().getBookid()+"\"")
    				   .append(",")
    				   .append("\"booktitle\":\""+d2.getBook().getBooktitle()+"\"")
    				   .append(",")
    				   .append("\"bookimages\":\""+d2.getBook().getBookimages()+"\"")
    				   .append(",")
    				   .append("\"typeid\":\""+d2.getBook().getCategory().getCategoryid()+"\"")
    				   .append(",")
    				   .append("\"date\":\""+d2.getDate()+"\"")
    				   .append(",")
    				   .append("\"admirenum\":\""+d2.getAdmirenum()+"\"")
    				   .append(",")
    				   .append("\"author\":\""+d2.getBook().getAuthor()+"\"")
    				   .append(",")
    				   .append("\"images\":"+"["+img+"]"+"")
    				   .append(",")
    				   .append("\"count\":\""+count+"\"")
    				   .append(",")
    				   .append("\"admirebol\":\""+admirebol+"\"")
    				   .append(",")
    				   .append("\"fanbol\":\""+fanbol+"\"")
    				   .append(",")
    				   .append("\"type\":\""+d2.getTypeid()+"\"")
    				   .append(",")
    				   .append("\"title\":\""+d2.getTitle()+"\"")
    				   .append(",")
    				   .append("\"typeid\":\""+d2.getTypeid()+"\"")
    				   .append(",")
    				   .append("\"groupmainid\":\""+d2.getGroupmainid()+"\"")
    				   .append("}");
    	    		  
    	    		  
    	    		        out.write(str.toString());
    	    	  }
    	    	  
		    
	}

}
