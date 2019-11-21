package DynamicServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import entity.Dynamic;
import entity.DynamicComment;
import entity.DynamicReply;
import entity.User;


@WebServlet("/AddDynamicComment")
public class AddDynamicComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AddDynamicComment() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		 
	      PrintWriter out=response.getWriter() ;
	      
	      Date date =new Date();
	      SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
	      String nowdate=sdf.format(date);
		
		  String userid=request.getParameter("userid");
		  String dynamicid=request.getParameter("dynamicid");
		  String describ=request.getParameter("describ");
		  
		  
		  System.out.println(userid);
		  System.out.println(dynamicid);
		  System.out.println(describ);
		  
		  DynamicCommentDao dcd=new DynamicCommentDao();
		  DynamicComment dc=new DynamicComment();
		  
		  
		  UserDao ud=new UserDao();
		  User u=ud.getUserbyid2(userid);
		  dc.setUser(u);
		  
		  
		  DynamicDao dd=new DynamicDao();
		  Dynamic d=dd.getDynamicByid2(Integer.parseInt(dynamicid));
		  dc.setDynamic(d);
		  
		  
		  dc.setDescrib(describ);
		  dc.setDate(nowdate);
		  
		  dcd.SaveDynamicComment(dc);
		  
		  
		  
		  
		  
		  //发送评论后更新评论列表
		 // DynamicCommentDao dd=new DynamicCommentDao();
	      List<DynamicComment> dlist=dcd.getCommentByid(Integer.parseInt(dynamicid));
	      
	      StringBuilder str=new StringBuilder();
	      
	      
	      
	      if(dlist!=null&&dlist.size()>0)
	      {
	    	  
	    	  
	    	  for(int i=dlist.size()-1;i>=0;i--)
	    		  
	    	  {
	    		  
	    		  DynamicComment dc2=dlist.get(i);
	    		  
	    		  
	    List<DynamicReply> drlist=dcd.getReplyByid(dc2.getCommentid());
	    
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
	    		  boolean admirebol=dad.CommentAdmire(dc2.getCommentid(), userid);
	    		  
	    		  
	    		  str.append("{").append("\"commentid\":\""+dc2.getCommentid()+"\"")
					.append(",")
					.append("\"describ\":\""+dc2.getDescrib() +"\"")
					.append(",")
					.append("\"userid\":\""+dc2.getUser().getUserid()+"\"")
					.append(",")
				   .append("\"userimages\":\""+dc2.getUser().getUserimages()+"\"")
				   .append(",")
				   .append("\"nickname\":\""+dc2.getUser().getNickname()+"\"")
				  
				   .append(",")
				   .append("\"date\":\""+dc2.getDate()+"\"")
				   .append(",")
				   .append("\"admirenum\":\""+dc2.getAdmirenum()+"\"")
				 .append(",")
				   .append("\"replylist\":"+"["+replylist+"]"+"")
				   .append(",")
				   .append("\"admirebol\":\""+admirebol+"\"")
				   .append(",")
				   .append("\"dynamicid\":\""+dc2.getDynamic().getDynamicid()+"\"")
				   .append(",")
				   .append("\"typeid\":\""+dc2.getDynamic().getTypeid()+"\"")
				   .append(",")
				   .append("\"groupmainid\":\""+dc2.getDynamic().getGroupmainid()+"\"")
				   .append("}").append(",");
	    		  
	    		  
	    		  
	    	  }
	    	  
	    	  out.write("["+str.substring(0,str.length()-1)+"]");
	    	  
	      }
	      
		           
		
		  
		  
		  
		
	}

}
