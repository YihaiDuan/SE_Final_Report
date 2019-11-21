package sharebookservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.ShareBookDao;
import dao.UserDao;
import entity.Book;
import entity.Collect;
import entity.ShareBookCollect;
import entity.User;
import util.MapDistance;


@WebServlet("/getCollectByUser")
public class getCollectByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getCollectByUser() {
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
	      
	      UserDao ud=new UserDao();
	      User u=ud.getUserbyid(userid);
	      String latitude=u.getLatitude();
	      String longitude=u.getLongitude();
	      
	      
	      
	      ShareBookDao sbd=new ShareBookDao();
	      
	    List<ShareBookCollect>  clist= sbd.getAllCollect(userid);
	      
	    StringBuilder str=new StringBuilder();
	      
        if(clist!=null&clist.size()>0)
        {
        	
        	
        	for(int i=clist.size()-1;i>=0;i--)
        	{
        		
        		ShareBookCollect c=clist.get(i);
        		
        		
        		  String latitudeother=c.getUser().getLatitude();
                  String longitudeother=c.getUser().getLongitude();	
        		
        		
        	
        	if(c!=null)
        	{
        		
        		
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
        		
        		
               String  show=c.getSharebook().getAuthor();	                  
                
                str.append("{").append("\"shareid\":\""+c.getSharebook().getSharebookid()+"\"")
                .append(",")
                .append("\"show\":\""+show+"\"")
                .append(",")
                .append("\"nickname\":\""+c.getUser().getNickname()+"\"")
                .append(",")
                .append("\"booktitle\":\""+c.getSharebook().getBooktitle()+"\"")
                .append(",")
                .append("\"distance\":\""+distance+"\"")
                .append(",")
                .append("\"id\":\""+c.getId()+"\"")
                .append(",")
                .append("\"bookimages\":\""+c.getSharebook().getBookimages()+"\"")
                .append("}").append(",");  
                	            		 
     	        
        		
        		
        	}//if
        	
        	
        		
        	}//for
        	
        	out.write("["+str.substring(0, str.length()-1)+"]");
        	
        	
        }//if
      
	
		
	}

}
