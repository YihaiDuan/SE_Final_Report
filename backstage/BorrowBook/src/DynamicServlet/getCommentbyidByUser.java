package DynamicServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.DynamicAdmireDao;
import Extenddao2.DynamicCommentDao;
import entity.DynamicComment;
import entity.DynamicReply;


@WebServlet("/getCommentbyidByUser")
public class getCommentbyidByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public getCommentbyidByUser() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		       doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		  response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		 
	      PrintWriter out=response.getWriter() ;
	      
	      
	      String dynamicid=request.getParameter("dynamicid");
	      String userid=request.getParameter("userid");
	      
	      DynamicCommentDao dd=new DynamicCommentDao();
	      List<DynamicComment> dlist=dd.getCommentByid(Integer.parseInt(dynamicid));
	      
	      StringBuilder str=new StringBuilder();
	      
	      
	      
	      if(dlist!=null&&dlist.size()>0)
	      {
	    	  
	    	  
	    	  for(int i=dlist.size()-1;i>=0;i--)
	    		  
	    	  {
	    		  
	    		  DynamicComment d=dlist.get(i);
	    		  
	    		  
	    List<DynamicReply> drlist=dd.getReplyByid(d.getCommentid());
	    
	    StringBuilder str2 =new StringBuilder();
	    
	          String replylist=null;
	    
	    		  if(drlist!=null&&drlist.size()>0)
	    		  {
	    			  
	    			 
	    			  
	    			  for(int j=0;j<drlist.size();j++)
	    			  {
	    				  
	    				  
	    				  DynamicReply dr=drlist.get(j);
	    				  
	    				str2.append("{").append("\"replyid\":\""+dr.getReplyid()+"\"")
	  					.append(",")
	  					.append("\"describ\":\""+dr.getDescrib() +"\"")
	  					.append(",")
	  					.append("\"userid\":\""+dr.getUser().getUserid()+"\"")
	  					.append(",")
	  				   .append("\"userimages\":\""+dr.getUser().getUserimages()+"\"")
	  				   .append(",")
	  				   .append("\"nickname\":\""+dr.getUser().getNickname()+"\"")
	  				  
	  				   .append(",")
	  				   .append("\"date\":\""+dr.getDate()+"\"")
	  				 .append(",")
	  				   .append("\"commentid\":\""+dr.getComment().getCommentid()+"\"")
	  				 .append(",")
	  				   .append("\"dynamicid\":\""+dr.getComment().getDynamic().getDynamicid()+"\"")
	  				   
	  				   
	  				   .append("}").append(",");
	    				  
	    			  }
	    			  
	    			  replylist=str2.substring(0,str2.length()-1).toString();
	    			  
	    		  }
	    		  else
	    		  {
	    			  
	    			  replylist="{"+"\"size\":"+"\""+0+"\""+"}";
	    		  }
	    		  
	    		  //是否被点赞
	    		  DynamicAdmireDao dad=new DynamicAdmireDao();
	    		  boolean admirebol=dad.CommentAdmire(d.getCommentid(), userid);
	    		  
	    		  
	    		  str.append("{").append("\"commentid\":\""+d.getCommentid()+"\"")
					.append(",")
					.append("\"describ\":\""+d.getDescrib() +"\"")
					.append(",")
					.append("\"userid\":\""+d.getUser().getUserid()+"\"")
					.append(",")
				   .append("\"userimages\":\""+d.getUser().getUserimages()+"\"")
				   .append(",")
				   .append("\"nickname\":\""+d.getUser().getNickname()+"\"")
				  
				   .append(",")
				   .append("\"date\":\""+d.getDate()+"\"")
				   .append(",")
				   .append("\"admirenum\":\""+d.getAdmirenum()+"\"")
				 .append(",")
				   .append("\"replylist\":"+"["+replylist+"]"+"")
				   .append(",")
				   .append("\"admirebol\":\""+admirebol+"\"")
				   .append(",")
				   .append("\"dynamicid\":\""+d.getDynamic().getDynamicid()+"\"")
				   .append(",")
				   .append("\"typeid\":\""+d.getDynamic().getTypeid()+"\"")
				   .append(",")
				   .append("\"groupmainid\":\""+d.getDynamic().getGroupmainid()+"\"")
				   .append("}").append(",");
	    		  
	    		  
	    		  
	    	  }
	    	  
	    	  out.write("["+str.substring(0,str.length()-1)+"]");
	    	  
	      }
	      else
	      {
	    	  
	    	 out.write("{"+"\"size\":"+"\""+0+"\""+"}");
	    	  
	    	  
	      }
	      
		           
		
		
	}

}
