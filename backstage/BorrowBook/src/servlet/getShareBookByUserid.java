package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShareBookDao;
import dao.UserDao;
import entity.ShareBook;
import entity.User;

@WebServlet("/getShareBookByUserid")
public class getShareBookByUserid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public getShareBookByUserid() {
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
	      
	      ShareBookDao sbd=new ShareBookDao();
	      
	      
	      List<ShareBook>  slist=sbd.getShareBookByUserid(userid);
	      StringBuilder str=new StringBuilder();
	      
	  
	      
	    
	      if(slist!=null&&slist.size()>0)
	      {
	    	  
	    	  for(int i=slist.size()-1;i>=0;i--)
	    	  {
	    		  
	    		 
	    		  
	    		  ShareBook s=slist.get(i);
	    		  
	    		 String  show=s.getAuthor()+" "+s.getPublish()+" "+s.getCash();
	    		
	    		 
	    			str.append("{").append("\"shareid\":\""+s.getSharebookid()+"\"")
					.append(",")
				   .append("\"show\":\""+show+"\"")
				   .append(",")
				   .append("\"nickname\":\""+s.getUser().getNickname()+"\"")
				   .append(",")
				   .append("\"booktitle\":\""+s.getBooktitle()+"\"")
				   .append(",")
				   .append("\"bookimages\":\""+s.getBookimages()+"\"")
				   
				  .append("}").append(",");
	    		  
	    	 }
	    	  
	    	  
	    	  UserDao ud=new UserDao();
	    	  User u=ud.getUserbyid(userid);
	    	  //"["+str.substring(0,str.length()-1)+"]"
	    	  
 out.write("{"+"\"personQR\":\""+u.getPersonQR()+"\","+"\"userimages\":\""+u.getUserimages()+"\","+"\"userid\":"+"\""+u.getUserid()+"\","+"\"nickname\":"+"\""+u.getNickname()+"\","+"\"sharenum\":"+"\""+slist.size()+"\","+"\"book\":"+"["+str.substring(0,str.length()-1)+"]"+"}");
	    	  
	        }
	      else
	      {
	    	  UserDao ud=new UserDao();
	    	  User u=ud.getUserbyid(userid);
	    	  
	    	  out.write("{"+"\"personQR\":\""+u.getPersonQR()+"\"userimages\":\""+u.getUserimages()+"\","+"\"userid\":"+"\""+u.getUserid()+"\","+"\"nickname\":"+"\""+u.getNickname()+"\","+"\"sharenum\":"+"\""+slist.size()+"\""+"}");	    	  
	    	  
	      }
		
		
	}

}
