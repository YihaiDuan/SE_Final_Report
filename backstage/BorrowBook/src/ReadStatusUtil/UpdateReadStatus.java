package ReadStatusUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.DynamicCommentDao;
import Extenddao2.OutPayDao;
import entity.Dynamic;
import entity.DynamicComment;
import entity.DynamicReply;
import entity.OutPay;
import entity.Reply;


@WebServlet("/UpdateReadStatus")
public class UpdateReadStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UpdateReadStatus() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		 response.setContentType("text/html;charset=utf-8");
	     request.setCharacterEncoding("utf-8");
	   
	      PrintWriter out=response.getWriter(); 
	      String userid=request.getParameter("userid");
	      
	      String action=request.getParameter("action");
	      ReadStatusDao rsd=new ReadStatusDao();
	      System.out.println("''';;;;;;;;;;"+action);
	      System.out.println("''';;;;;;;;;;"+action);
	          if(action.equals("dynamic"))
	          {
	        	 
	        	
	        List<Dynamic>  dlist=rsd.bolDynamicComment(userid);
	        	  
	        	  
	        	  if(dlist!=null&&dlist.size()>0)
	        	  {
	        		  
	        		  for(int i=0;i<dlist.size();i++)
	        		  {
	        			  
	        			  
	        			Dynamic d=dlist.get(i);
	        			DynamicCommentDao dcd=new DynamicCommentDao();
	        		
	        List<DynamicComment>  dclist=dcd.getCommentByid(d.getDynamicid());
	        			if(dclist!=null&&dclist.size()>0)
	        			{
	        				for(int j=0;j<dclist.size();j++)
	        				{
	        					
	        				DynamicComment dc=dclist.get(j);
	        				rsd.UpdateDynamicCommentStatus(dc);
	        					
	        					
	        				}
	        			}
	        			  
	        			  
	        		  }
	        	  }
	        	List<DynamicReply>   drlist=rsd.bolDynamicReply(userid);
	        	if(drlist!=null&&drlist.size()>0)
	        	{
	        		
	        		 System.out.println(",,,,,,,,,,,我来自动态消息");
	        	     for(int i=0;i<drlist.size();i++)
	        		{
	        			
	        			DynamicReply dr=drlist.get(i);
	        			rsd.UpdateDynamicReplyStatus(dr);
	        			
	        		}
	        	}
	        		  
	        	
	        	  
	        	 }
	          else   if(action.equals("review"))
	          {
	        	  
	        	  List<Reply>  rlist=rsd.bolReviewReply(userid);
	        	  
	        	  
	        	  if(rlist!=null&&rlist.size()>0)
		        	{
		        	     for(int i=0;i<rlist.size();i++)
		        		{
		        			
		        			Reply r=rlist.get(i);
		        			rsd.UpdateReviewReplyStatus(r);
		        			
		        		}
		        	}
		        		  
	        	  
	        	  
	          }
	          
	          else if(action.equals("outpay"))
	          {
	        	  
	        	
	     	    
	     		    OutPayDao opd=new OutPayDao();
	     		    
	     		    List<OutPay>  oplist=opd.getOutPayNoRead(userid);
	     	    
	     	     
	     	      
	    	       if(oplist!=null&&oplist.size()>0)
	    	       {
	    	    	   
	    	    	   for(int i=0;i<oplist.size();i++)
	    	    	   {
	    	    		   
	    	    		   OutPay op=oplist.get(i);
	    	    		   op.setReadstatus(1);
	    	    		   opd.UpdateReadStatus(op);
	    	    		   
	    	    	   }
	    	    	 
	    	       }
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	          }
	        	   
	      
	      
	}

}
