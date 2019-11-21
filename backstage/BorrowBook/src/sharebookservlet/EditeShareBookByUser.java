package sharebookservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShareBookDao;
import dao.UserDao;
import entity.ShareBook;
import entity.ShareBookCollect;
import entity.User;
import util.JsonFormat;


@WebServlet("/EditeShareBookByUser")
public class EditeShareBookByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public EditeShareBookByUser() {
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
		   String shareid=request.getParameter("shareid");
		   String action=request.getParameter("action");
		   
		   ShareBookDao sbd=new ShareBookDao();
		   
		   
		   
		
		
			   if(action.equals("delete"))
			   {
				   ShareBookCollect sbc=sbd.getBookCollectBoolean(userid,Integer.parseInt(shareid));
				   
				   if(sbc!=null)
				   {  
				  sbd.DeleteShareBookCollect(sbc.getId()); 
				   }
				  
			   }
			   else
			   {
				   System.out.println("我来了");
				   UserDao ud=new UserDao();
				   User u=ud.getUserbyid(userid);
				   System.out.println(u.getUserid());
				   
				ShareBook sb2= sbd.getShareBookByid(Integer.parseInt(shareid));
				   System.out.println(sb2.getSharebookid());
				
				
				   ShareBookCollect sbc2=new ShareBookCollect();
				   
				   
				   sbc2.setUser(u);
				   sbc2.setSharebook(sb2);
				   
				   sbd.SavaShareBookCollect(sbc2);
			   }
			   
			   ShareBook sb=sbd.getShareBookByid(Integer.parseInt(shareid));
				StringBuilder str=new StringBuilder();
				
				
				if(sb!=null)
				{
					boolean collectbol=false;
					
				ShareBookCollect sbc2=sbd.getBookCollectBoolean(userid,Integer.parseInt(shareid));
					if(sbc2!=null)
					{
						
						collectbol=true;
					}
					else
					{
						collectbol=false;
						
					}
				
					Object s=sbd.getShareBookNumByUserid(sb.getUser().getUserid());
					
					  String summary=null;
					  JsonFormat jf=new JsonFormat();
					  if(sb.getSummary()!=null)
					  {
						  summary=jf.stringToJson(sb.getSummary());
					  }
					  else
					  {
						  summary=sb.getSummary();
						  
					  }
					
					str.append("{").append("\"shareid\":\""+sb.getSharebookid()+"\"")
					.append(",")
					.append("\"author\":\""+sb.getAuthor()+"\"")
					.append(",")
					.append("\"nickname\":\""+sb.getUser().getNickname()+"\"")
					.append(",")
					.append("\"booktitle\":\""+sb.getBooktitle()+"\"")
					.append(",")
					.append("\"publish\":\""+sb.getPublish()+"\"")
					.append(",")
					.append("\"isbn\":\""+sb.getIsbn()+"\"")
					.append(",")
					.append("\"pagenum\":\""+sb.getPagenum()+"\"")
					.append(",")
					.append("\"cash\":\""+sb.getCash()+"\"")
					.append(",")
					.append("\"publishdate\":\""+sb.getPublishdate()+"\"")
					.append(",")
					.append("\"summary\":\""+summary+"\"")
					.append(",")
					.append("\"sharenum\":\""+s+"\"")
					.append(",")
					.append("\"personqr\":\""+sb.getUser().getPersonQR()+"\"")
					.append(",")
					.append("\"userid\":\""+sb.getUser().getUserid()+"\"")
					.append(",")
					.append("\"collectbol\":\""+collectbol+"\"")
				
					.append(",")
					.append("\"bookimages\":\""+sb.getBookimages()+"\"")
					.append(",")
					.append("\"userimages\":\""+sb.getUser().getUserimages()+"\"")
					
					.append("}");  
					
					out.write(str.toString());
					
					System.out.println(str);
				}
				else
				{
					
					out.write("fail");
					
					
				}
				
		
		   
		   }
	     
	      
	      
	

}
