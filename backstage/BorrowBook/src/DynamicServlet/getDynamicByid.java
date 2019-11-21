package DynamicServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.DynamicDao;
import entity.Dynamic;


@WebServlet("/getDynamicByid")
public class getDynamicByid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getDynamicByid() {
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
	      
	      
	      DynamicDao dd=new DynamicDao();
	     Dynamic d=dd.getDynamicByid(Integer.parseInt(dynamicid));
	      StringBuilder str=new StringBuilder();
	      
	        if(d!=null)
	         {
	        	
	    	     String images[]=d.getImages().split(",");
	    		  StringBuilder str2 =new StringBuilder();
	    		  
	    		  
	    	  	     //评论总数
	int count=Integer.parseInt(dd.CountComment(d.getDynamicid()).toString())+ Integer.parseInt(dd.CountReply(d.getDynamicid()).toString());  
	    		  	    	
	    		  
	    		  for(int j=0;j<images.length;j++)
	    		  {
	    			  str2.append("{").append("\"img\":\""+images[j]+"\"")
						
					   .append("}").append(",");
		    		  
	    			 
	    		  }
	    		 String img=str2.substring(0,str2.length()-1).toString();
	    		 
	    		 System.out.println(img);
	    		  
	    		  
	    		  str.append("{").append("\"dynamicid\":\""+d.getDynamicid()+"\"")
					.append(",")
					.append("\"describ\":\""+d.getDescrib() +"\"")
					.append(",")
					.append("\"userid\":\""+d.getUser().getUserid()+"\"")
					.append(",")
				   .append("\"userimages\":\""+d.getUser().getUserimages()+"\"")
				   .append(",")
				   .append("\"nickname\":\""+d.getUser().getNickname()+"\"")
				   .append(",")
				   .append("\"bookid\":\""+d.getBook().getBookid()+"\"")
				   .append(",")
				   .append("\"booktitle\":\""+d.getBook().getBooktitle()+"\"")
				   .append(",")
				   .append("\"bookimages\":\""+d.getBook().getBookimages()+"\"")
				   .append(",")
				   .append("\"typeid\":\""+d.getBook().getCategory().getCategoryid()+"\"")
				   .append(",")
				   .append("\"date\":\""+d.getDate()+"\"")
				   .append(",")
				   .append("\"admirenum\":\""+d.getAdmirenum()+"\"")
				   .append(",")
				   .append("\"author\":\""+d.getBook().getAuthor()+"\"")
				   .append(",")
				   .append("\"images\":"+"["+img+"]"+"")
				   .append(",")
				   .append("\"count\":\""+count+"\"")
				   .append(",")
				   .append("\"title\":\""+d.getTitle()+"\"")
				   .append(",")
				   .append("\"typeid\":\""+d.getTypeid()+"\"")
				   .append(",")
				   .append("\"groupmainid\":\""+d.getGroupmainid()+"\"")
				   .append("}");
	    		  
	    		  
	    		        out.write(str.toString());
	    	  }
	    	  
	    	
	    
	      
		
		
	
	}

}
