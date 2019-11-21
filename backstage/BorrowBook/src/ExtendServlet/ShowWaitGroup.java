package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.GroupMainDao;
import dao.BookDao;
import entity.Book;
import entity.GroupMain;
import entity.GroupMember;

/**
 * Servlet implementation class ShowWaitGroup
 */
@WebServlet("/ShowWaitGroup")
public class ShowWaitGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ShowWaitGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		  response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
          PrintWriter out=response.getWriter() ;
          
          
          String userid=request.getParameter("userid");
          
          GroupMainDao  gm=new GroupMainDao();
	      List<GroupMain>  gmlist=gm.getGroupMainByuserid(userid); 
	      StringBuilder str=new StringBuilder();
	      StringBuilder str2=new StringBuilder();
	    	
	      String json="0";
	      String json2="0";
	      if(gmlist!=null&&gmlist.size()>0)
	    	{
	    		
	    		for(int i=0;i<gmlist.size();i++)
	    		{
	    			
	    			
	    			GroupMain gma=gmlist.get(i);
	    			
	    			BookDao bd=new BookDao();
	    			Book b=bd.getBookbyid(gma.getBookid());
	    			
	    			   //这本书需要几人团
					  String groupnum=gma.getGroupbook().getGroupmore().getGroupnum();
					   
					   
					   //现在拼团的人数
					   Integer opengroupnum=0;
					   opengroupnum=Integer.parseInt(gm.getNumfromGroupMember(gma.getId()).toString())+1;
					   
					   //剩下多少人
					   int remainnum=Integer.parseInt(groupnum)-opengroupnum;			
	    			
	    			//享受打折
					   String discount="";
					   
					  String num=gma.getGroupbook().getGroupmore().getMaxnum();
					 	if(num.equals("2"))
						{
							discount="9";
							
						}
						else  if(num.equals("3"))
						{
							discount="8";
						}
						else
						{
							
							discount="7";
							
						}
	    			
	    			 str.append("{").append("\"groupmainid\":\""+gma.getId()+"\"")
						.append(",")
						.append("\"bookid\":\""+gma.getBookid() +"\"")
						.append(",")
						.append("\"bookimages\":\""+b.getBookimages() +"\"")
						.append(",")
						.append("\"typeid\":\""+b.getCategory().getCategoryid()+"\"")
						.append(",")
					   .append("\"booktitle\":\""+b.getBooktitle()+"\"")
					   .append(",")
					   .append("\"author\":\""+b.getAuthor()+"\"")
					   .append(",")
					   .append("\"createdate\":\""+gma.getGroupbook().getGroupmore().getCreatedate()+"\"")
					   .append(",")
					   .append("\"deadline\":\""+gma.getGroupbook().getGroupmore().getCreatedate()+"\"")
					   .append(",")
					   .append("\"remainnum\":\""+remainnum+"\"")
					   .append(",")
					   .append("\"status\":\""+gma.getStatus()+"\"")
					   .append(",")
					   .append("\"pay\":\""+gma.getPaymoney()+"\"")
					   .append(",")
					   .append("\"date\":\""+gma.getDate()+"\"")
					   .append(",")
					   .append("\"discount\":\""+discount+"\"")
					   .append("}").append(",");
	    			
	    			
	    		}
	    		json=str.substring(0,str.length()-1);
	    	
	    	}
	      
	    	
	    	List<GroupMember>  gmalist=gm.getGroupMemberByuserid(userid);
	    	
	    	if(gmalist!=null&&gmalist.size()>0)
	    	{
	    		
	    for(int i=0;i<gmalist.size();i++)
	    {
	    	
	    	GroupMember gme=gmalist.get(i);
	    	
	    	
	    	BookDao bd=new BookDao();
			Book b=bd.getBookbyid(gme.getBookid());
			
			   //这本书需要几人团
			  String groupnum=gme.getGroupmain().getGroupbook().getGroupmore().getGroupnum();
					  
			  //现在拼团的人数
			   Integer opengroupnum=0;
			   opengroupnum=Integer.parseInt(gm.getNumfromGroupMember(gme.getGroupmain().getId()).toString())+1;
			   
			   //剩下多少人
			   int remainnum=Integer.parseInt(groupnum)-opengroupnum;			
			
			 //享受打折
			   String discount="";
			   
			  String num=gme.getGroupmain().getGroupbook().getGroupmore().getMaxnum();
			 	if(num.equals("2"))
				{
					discount="9";
					
				}
				else  if(num.equals("3"))
				{
					discount="8";
				}
				else
				{
					
					discount="7";
					
				}
			
			 str2.append("{").append("\"groupmainid\":\""+gme.getGroupmain().getId()+"\"")
			 .append(",")
				.append("\"groupmemberid\":\""+gme.getId() +"\"")
				.append(",")
				.append("\"bookid\":\""+gme.getBookid() +"\"")
				.append(",")
				.append("\"bookimages\":\""+b.getBookimages() +"\"")
				.append(",")
				.append("\"typeid\":\""+b.getCategory().getCategoryid()+"\"")
				.append(",")
			   .append("\"booktitle\":\""+b.getBooktitle()+"\"")
			   .append(",")
			   .append("\"author\":\""+b.getAuthor()+"\"")
			   .append(",")
			   .append("\"createdate\":\""+gme.getGroupmain().getGroupbook().getGroupmore().getCreatedate()+"\"")
			   .append(",")
			   .append("\"deadline\":\""+gme.getGroupmain().getGroupbook().getGroupmore().getCreatedate()+"\"")
			   .append(",")
			   .append("\"remainnum\":\""+remainnum+"\"")
			   .append(",")
			   .append("\"status\":\""+gme.getGroupmain().getStatus()+"\"")
			   .append(",")
			   .append("\"pay\":\""+gme.getPaymoney()+"\"")
			   .append(",")
			   .append("\"date\":\""+gme.getDate()+"\"")
			   .append(",")
			   .append("\"discount\":\""+discount+"\"")
			   .append("}").append(",");
	    	
	    	
	    	
	    }//for
	    
	    json2=str2.substring(0,str2.length()-1);
	    		
	  }//if
          
	    	
	    	
	    	
	    	if(!json.equals("0")&&!json2.equals("0"))
	    	{
	    		out.write("{"+"\"groupmain\":"+"["+json+"],"+"\"groupmember\":"+"["+json2+"]"+"}");	
	    		
	    	}
	    	else if(!json.equals("0")&&json2.equals("0"))
	    	{
	    		
	    		out.write("{"+"\"groupmain\":"+"["+json+"]"+"}");	
	    		
	    	}else if(json.equals("0")&&!json2.equals("0"))
	    	{
	    		
	    		out.write("{"+"\"groupmember\":"+"["+json2+"]"+"}");	
	    	}
	    	else if(json.equals("0")&&json2.equals("0"))
	    	{
	    		//没有信息
	    		
	    	}
	}

}
