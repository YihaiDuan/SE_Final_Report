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
import entity.ShareBook;
import util.MapDistance;


@WebServlet("/ShareBookSearch")
public class ShareBookSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShareBookSearch() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// TODO Auto-generated method stub
				doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter();
	      
	      
	      String key=request.getParameter("key");
	      String longitude=request.getParameter("longitude");
	       String latitude=request.getParameter("latitude");
	     
	      System.out.println(longitude);
	      System.out.println(latitude);
	      
	      
	        ShareBookDao sdd=new ShareBookDao();
	        List<ShareBook>  slist= sdd.getShareBookByKey(key);
		     
	      
	      StringBuilder str=new StringBuilder();
	      
	             if(slist!=null&&slist.size()>0)
	             {
	            	 
	            	 for(int i=slist.size()-1;i>=0;i--)
	            	 {
	            		 
	            		ShareBook s=slist.get(i); 
	            	
	            	  String latitudeother=s.getUser().getLatitude();
	                  String longitudeother=s.getUser().getLongitude();
	             
	               
	                  String distance=null;
	                  if(!longitude.equals("0")&&!latitude.equals("0"))
	       	       {                  
	            		
	                	  if(longitudeother!=null&&latitudeother!=null)
	                	  {
	                		  distance=MapDistance.Distance(Double.valueOf(longitude),Double.valueOf(latitude),Double.valueOf(longitudeother),Double.valueOf(latitudeother));
	                	  }
	                	  else
	                	  {
	                  		  distance=MapDistance.Distance(Double.valueOf(longitude),Double.valueOf(latitude),0.0,0.0);             		  
	                		  
	                	  }

	       	       }
	                  else
	                  {
	        distance="距离未知"; 	  
	                	  
	                  }

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
	            	 
	            	 out.write("{"+"\"size\":"+"\""+slist.size()+"\","+"\"searchlist\":"+"["+str.substring(0,str.length()-1)+"]"+"}");
	              }
	             else
	             {
	            	 
	            	 out.write("["+str.substring(0,str.length()-1)+"]");
	            	 
	             }
	           
	      
	      
	      
	      
	      

	     
	     
	      
	      
	      
	}

}
