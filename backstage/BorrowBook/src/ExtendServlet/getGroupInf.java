package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.GroupDao;
import Extenddao2.GroupMainDao;
import dao.BookDao;
import dao.UserDao;
import entity.Book;
import entity.GroupMain;
import entity.GroupMember;
import entity.User;


@WebServlet("/getGroupInf")
public class getGroupInf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public getGroupInf() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		 
	      PrintWriter out=response.getWriter() ;
	      
	        String groupmainid=request.getParameter("groupmainid");
	      
	      GroupMainDao gd=new GroupMainDao();
	        
	      GroupMain gm=gd.getGroupMainByid(Integer.parseInt(groupmainid));
	      
	      
	      if(gm!=null)
	      {
	    	  if(gm.getStatus()==2)
	    	  {
	    		  
	    		out.write("2");  
	    		  
	    	  }
	    	  else if(gm.getStatus()==1)
	    	  {
	    		  
	    		  
	    		out.write("1");  
	    		  
	    		  
	    	  }
	    	  else
	    	  {
	    		  
	    		  
	    		StringBuilder str=new StringBuilder();  
	    		  
List<GroupMember>  gmlist=gd.getAllMemberbyid(Integer.parseInt(groupmainid));  
	    		
                  UserDao ud2=new UserDao();
                   User u2=ud2.getUserbyid(gm.getUserid());

              String mainuser=u2.getUserimages();
              System.out.println("主团成员:"+mainuser);
                      String userimages="";
	    		if(gmlist!=null&&gmlist.size()>0)
	    		{
	    			for(int i=0;i<gmlist.size();i++)
	    			{
	    				
	    				
	    				GroupMember gmb=gmlist.get(i);
	    				UserDao ud=new UserDao();
	    				User u=ud.getUserbyid(gmb.getUserid());

                   userimages=userimages+u.getUserimages()+",";
	    				
	    				
	    			}
	    			
	    			userimages=userimages.substring(0,userimages.length()-1);
	    			System.out.println("团成员:"+userimages);
	    		}
	    		
	    		
	    		if(userimages!=null&&!userimages.isEmpty()&&userimages!="")
	    		{
	    			mainuser=mainuser+","+userimages;
	    			System.out.println("我进来拼接了吗");
	    			
	    		}
	    		
	    		String userarray[]=mainuser.split(",");
	    		StringBuilder str2=new StringBuilder();
	    	    
	    		
	    		for(int i=0;i<userarray.length;i++)
	    	     {
	    	    		str2.append("{").append("\"userimages\":\""+userarray[i]+"\"")
	  				    .append("},");	  	 
	    	    	 
	    	    	 
	    	     }
	    	     System.out.println("拼接后="+str2);
	    	     
	    	     String str3=str2.substring(0, str2.length()-1).toString();
	    	   
	    	     
	    	     BookDao bd=new BookDao();
	    	    Book b=bd.getBookbyid(gm.getBookid());
	    	    
	    	    
	    	    
	    	    
	    	    
	    	    
	    	    
	    	    if(b!=null)
	    	    {
	    	    	
	    	    	
	    	    	
	    	    	    int discount=0;
	    	    	    if(gm.getGroupbook().getGroupmore().getGroupnum().equals("2"))
	    	    	    {
	    	    	    	
	    	    	    	discount=9;
	    	    	    }
	    	    	    else if(gm.getGroupbook().getGroupmore().getGroupnum().equals("3"))
	    	    	    {
	    	    	    	discount=8;
	    	    	    }
	    	    	    else
	    	    	    {
	    	    	    	
	    	    	    	discount=7;
	    	    	    }
	    	    	    	
	    	    	
	    	    	  int remainnum=Integer.parseInt(gm.getGroupbook().getGroupmore().getGroupnum())-userarray.length;
	    	    	str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
  					.append(",")
  					.append("\"typeid\":\""+b.getCategory().getCategoryid() +"\"")
  					.append(",")
  					.append("\"bookimages\":\""+b.getBookimages()+"\"")
  					.append(",")
  				   .append("\"booktitle\":\""+b.getBooktitle()+"\"")
  				   .append(",")
  				   .append("\"author\":\""+b.getAuthor()+"\"")
  				   .append(",")
  				   .append("\"cash\":\""+b.getCash()+"\"")
  				 .append(",")
  				   .append("\"createdate\":\""+gm.getGroupbook().getGroupmore().getCreatedate()+"\"")
  				 .append(",")
  				   .append("\"deadline\":\""+gm.getGroupbook().getGroupmore().getDeadline()+"\"")
  				 .append(",")
				   .append("\"groupmainid\":\""+gm.getId()+"\"")
				   .append(",")
				   .append("\"groupnum\":\""+gm.getGroupbook().getGroupmore().getGroupnum()+"\"")
				   .append(",")
				   .append("\"remainnum\":\""+remainnum+"\"")
				   .append(",")
				   .append("\"discount\":\""+discount+"\"")
				   .append(",")
				   .append("\"userlist\":"+"["+str3+"]")
  				   
  				   .append("}");	
	    	    	
	    	
	    	    	out.write(str.toString());
	    	    }
	    	     
	    	     
	    		  
	    	  }
	    	  
	    	  
	    	  
	      }
	      
	      
	}

}
