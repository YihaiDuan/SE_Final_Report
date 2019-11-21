package DynamicServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.DynamicCommentDao;
import Extenddao2.DynamicDao;
import dao.UserDao;
import entity.Dynamic;
import entity.DynamicComment;
import entity.DynamicMessage;
import entity.DynamicReply;
import entity.User;


@WebServlet("/getDynamicMessage")
public class getDynamicMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public getDynamicMessage() {
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
	      
	      

			DynamicDao dd=new DynamicDao();
			
			List<DynamicMessage>  dmlist=new ArrayList<DynamicMessage>();
			
			
		List<DynamicComment> dlist=dd.getCommentuserid(userid);
			
			
		if(dlist!=null&&dlist.size()>0)
			{
				for(int i=0;i<dlist.size();i++)
				{
					
				DynamicComment d=dlist.get(i);
				DynamicMessage dm=new DynamicMessage();	
				dm.setId(d.getCommentid());
				dm.setTypestatus(0);
				dm.setUserid(d.getUser().getUserid());
			    dm.setDate(d.getDate());
			    dm.setDescrib(d.getDescrib());
			    dm.setDynamicid(d.getDynamic().getDynamicid());
			    dm.setCommentid(d.getCommentid());
			    
			    System.out.println(dm.getTypestatus());
			    dmlist.add(dm);
				}
				
				
			}
			List<DynamicReply>  drlist=dd.getReplyByuserid(userid);
			
			if(drlist!=null&&drlist.size()>0)
			{
				for(int i=0;i<drlist.size();i++)
				{
					DynamicReply dr=drlist.get(i);
					DynamicMessage dm=new DynamicMessage();
					dm.setId(dr.getReplyid());
					dm.setTypestatus(1);
					dm.setUserid(dr.getUser().getUserid());
				    dm.setDate(dr.getDate());
				    dm.setDescrib(dr.getDescrib());
				    dm.setDynamicid(dr.getDynamicid());
				    dm.setCommentid(dr.getComment().getCommentid());
					
				    dmlist.add(dm);
					
				}
				
				
			}
			
			
			
			
			//根据时间进行降序排序
			
			  Collections.sort(dmlist, new Comparator<DynamicMessage>(){  
				  
		            /*  
		             * int compare(Student o1, Student o2) 返回一个基本类型的整型，  
		             * 返回负数表示：o1 小于o2，  
		             * 返回0 表示：o1和o2相等，  
		             * 返回正数表示：o1大于o2。  
		             */  
		            public int compare(DynamicMessage o1, DynamicMessage o2) {  
		              
		                //按照学生的年龄进行降序排列  
		                if(o1.getDate().compareTo(o2.getDate())==-1 ){  
		                    return 1;  
		                }  
		                if(o1.getDate().compareTo(o2.getDate())==0 ){  
		                    return 0;  
		                }  
		                return -1;  
		            }  
		        });   
			  
			  
			  
			  
			  StringBuilder str =new StringBuilder();
			  
		       if(dmlist!=null&&dmlist.size()>0)
	                {
	                	
		    	   for(int i=0;i<dmlist.size();i++)
		    	   {
		    		   
		    		   DynamicMessage dm=dmlist.get(i);
		    		   System.out.println(dm.getDate());
		    		   
		    		   UserDao ud=new UserDao();
		    		   User u=ud.getUserbyid(dm.getUserid());
		    		   
		    		   String nickname=u.getNickname();
		    		   String userimages=u.getUserimages();
		    		   String booktitle=null;
		    		   String replydescrib=null;
		    		   if(dm.getTypestatus()==0)
		    		   {
		    		   DynamicDao ddd=new DynamicDao();
		    		   Dynamic d=ddd.getDynamicByid(dm.getId());
		    		   
		    		   booktitle=d.getBook().getBooktitle();
		    		   
		    		   }
		    		   else
		    		   {
		    			DynamicCommentDao dc=new DynamicCommentDao();
		    			//replydescrib=dc.getReplyDetailByid(dm.getId()).getDescrib();
		    			
		    			
		    			replydescrib=dc.getCommentDetailByid(dm.getId()).getDescrib();
		    		
		    			}
		    		   
		    		   str.append("{").append("\"id\":\""+dm.getId()+"\"")
	  					.append(",")
	  					.append("\"describ\":\""+dm.getDescrib() +"\"")
	  					.append(",")
	  					.append("\"userid\":\""+dm.getUserid()+"\"")
	  					.append(",")
	  				   .append("\"date\":\""+dm.getDate()+"\"")
	  				   .append(",")
	  				   .append("\"nickname\":\""+nickname+"\"")
	  				  
	  				   .append(",")
	  				   .append("\"typestatus\":\""+dm.getTypestatus()+"\"")
	  				 .append(",")
	  				   .append("\"booktitle\":\""+booktitle+"\"")
	  				 .append(",")
	  				   .append("\"replydescrib\":\""+replydescrib+"\"")
	  				 .append(",")
	  				   .append("\"dynamicid\":\""+dm.getDynamicid()+"\"")
	  				 .append(",")
	  				   .append("\"userimages\":\""+userimages+"\"")
	  				 .append(",")
	  				   .append("\"commentid\":\""+dm.getCommentid()+"\"")
	  				   
	  				   .append("}").append(",");
	             
		    		   
		    		   
		    		   
	                }
		    	   
		    	   
		    		  out.write("["+str.substring(0,str.length()-1)+"]");
		    	 }//if
		      
			
			
		
		
	}

}
