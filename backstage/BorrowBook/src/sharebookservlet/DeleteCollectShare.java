package sharebookservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShareBookDao;
import entity.ShareBookCollect;


@WebServlet("/DeleteCollectShare")
public class DeleteCollectShare extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteCollectShare() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
	      
	      String id=request.getParameter("id");
	      String userid=request.getParameter("userid");
	      
	      
	      ShareBookDao sbd=new ShareBookDao();
	     
	      sbd.DeleteShareBookCollect(Integer.parseInt(id));
	      
	  
	      
		    List<ShareBookCollect>  clist= sbd.getAllCollect(userid);
		      
		    StringBuilder str=new StringBuilder();
		      
	        if(clist!=null&clist.size()>0)
	        {
	        	
	        	
	        	for(int i=clist.size()-1;i>=0;i--)
	        	{
	        		
	        		ShareBookCollect c=clist.get(i);
	        		
	        		
	        		
	        	
	        	if(c!=null)
	        	{
	        		
	        		 str.append("{").append("\"id\":\""+c.getId()+"\"")
	     	        .append(",")
	     			.append("\"author\":\""+c.getSharebook().getAuthor() +"\"")
	     			
		     	
	     			
	     			
	     			
	     			.append(",")
	     			.append("\"booktitle\":\""+c.getSharebook().getBooktitle()+"\"")
	     			
	     			
	     			.append(",")
	     			.append("\"publish\":\""+c.getSharebook().getPublish()+"\"")
	     			
	     			.append(",")
	     			.append("\"isbn\":\""+c.getSharebook().getIsbn()+"\"")
	     			.append(",")
	                .append("\"bookimages\":\""+c.getSharebook().getBookimages()+"\"")
	     			.append("}").append(",");
	     	        
	        		
	        		
	        	}//if
	        	
	        	
	        		
	        	}//for
	        	
	        	out.write("["+str.substring(0, str.length()-1)+"]");
	        	
	        	
	        }//if
	      
		
	 
	      
	}

}
