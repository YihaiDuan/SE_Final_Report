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
import Extenddao2.DynamicDao;
import dao.UserDao;
import entity.CommentAdmire;
import entity.Dynamic;
import entity.DynamicAdmire;
import entity.DynamicComment;
import entity.DynamicReply;
import entity.User;


@WebServlet("/AddCommentAdmire")
public class AddCommentAdmire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddCommentAdmire() {
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
          String commentid=request.getParameter("commentid");
          String action=request.getParameter("action");
          String dynamicid=request.getParameter("dynamicid");
          
         
       CommentAdmire ca=new CommentAdmire();
         
          UserDao ud=new UserDao();
          User u=ud.getUserbyid2(userid);
          
          
         
          DynamicCommentDao dd=new DynamicCommentDao();
          DynamicComment d=dd.getCommentDetailByid2(Integer.parseInt(commentid));
         
          
          if(action.equals("add"))
          {
              ca.setComment(d);
              ca.setUser(u);
              dd.SaveCommentAdmire(ca);
       	   
          }
          else
          {
       	   
       	 dd.DeleteCommentDynamicAdmire(userid,Integer.parseInt(commentid));
       	   
          }
          
          
          //点赞和撤销赞之后去获取最新动态列表
       
	      List<DynamicComment> dlist=dd.getCommentByid(Integer.parseInt(dynamicid));
	      
	      StringBuilder str=new StringBuilder();
	      
          
	      if(dlist!=null&&dlist.size()>0)
	      {
	    	  
	    	  
	    	  for(int i=dlist.size()-1;i>=0;i--)
	    		  
	    	  {
	    		  
	    		  DynamicComment d2=dlist.get(i);
	    		  
	    		  
	    List<DynamicReply> drlist=dd.getReplyByid(d2.getCommentid());
	    
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
	    		  boolean admirebol=dad.CommentAdmire(d2.getCommentid(), userid);
	    		  
	    		  
	    		  str.append("{").append("\"commentid\":\""+d2.getCommentid()+"\"")
					.append(",")
					.append("\"describ\":\""+d2.getDescrib() +"\"")
					.append(",")
					.append("\"userid\":\""+d2.getUser().getUserid()+"\"")
					.append(",")
				   .append("\"userimages\":\""+d2.getUser().getUserimages()+"\"")
				   .append(",")
				   .append("\"nickname\":\""+d2.getUser().getNickname()+"\"")
				  
				   .append(",")
				   .append("\"date\":\""+d2.getDate()+"\"")
				   .append(",")
				   .append("\"admirenum\":\""+d2.getAdmirenum()+"\"")
				 .append(",")
				   .append("\"replylist\":"+"["+replylist+"]"+"")
				   .append(",")
				   .append("\"admirebol\":\""+admirebol+"\"")
				   .append(",")
				   .append("\"dynamicid\":\""+d2.getDynamic().getDynamicid()+"\"")
				   .append(",")
				   .append("\"typeid\":\""+d2.getDynamic().getTypeid()+"\"")
				   .append(",")
				   .append("\"groupmainid\":\""+d2.getDynamic().getGroupmainid()+"\"")
				   .append("}").append(",");
	    		  
	    		  
	    		  
	    	  }
	    	  
	    	  out.write("["+str.substring(0,str.length()-1)+"]");
	    	  
	      }
	      
		           
          
          
          
          
          
          
          
	}

}
