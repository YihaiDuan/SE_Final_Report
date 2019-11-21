package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShareBookDao;
import entity.ShareBook;
import util.JsonFormat;


@WebServlet("/ShowShareBookbyId")
public class ShowShareBookbyId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowShareBookbyId() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter();
     
		String shareid=request.getParameter("shareid");
		
		
		ShareBookDao sbd=new ShareBookDao();
		
		ShareBook sb=sbd.getShareBookByid(Integer.parseInt(shareid));
		StringBuilder str=new StringBuilder();
		
		
		if(sb!=null)
		{
			
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
