package applyservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplyNearDao;
import dao.UserDao;
import entity.ApplyNear;
import entity.User;


@WebServlet("/AgreeApply")
public class AgreeApply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AgreeApply() {
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
		  
		  
		  String id=request.getParameter("id");
		  String userid=request.getParameter("userid");
		  ApplyNearDao and=new ApplyNearDao();
		  String action=request.getParameter("action");
		  
		  System.out.println(userid);
		  
		  
		  
		  if(action.equals("agree"))
		  {
			  
			  UserDao ud=new UserDao();
			  
			  User u=ud.getUserbyid(userid);
			  
			  String personQR=u.getPersonQR();
			  
			  
			 ApplyNear an=new ApplyNear();
			 
			 
			    //更新同意标志位
			     an.setId(Integer.parseInt(id));
			     an.setStatus(1);
			     
			     and.UpateStatus(an);
			     
			    //更新已经读了的标志位
			     an.setReadstatus(1);
			     and.UpateReadStatus(an);
			     
			     
			     //附上用户的二维码
			     an.setPersonQR(personQR);
			     and.UpateQR(an);
			     
			     //更新操作标志位
			     
			     an.setDostatus(1);
			     and.UpateDoStatus(an);
			     
			     
			     
			  
		  }
		  else
		  {
			  
			  
			  
			  ApplyNear an=new ApplyNear();
			    //更新同意标志位
			     an.setId(Integer.parseInt(id));
			     an.setStatus(2);
			     
			     and.UpateStatus(an);
			     
			    //更新已经读了的标志位
			     an.setReadstatus(1);
			     and.UpateReadStatus(an);
			  
                //更新操作标志位
			     an.setDostatus(1);
			     and.UpateDoStatus(an);
			  
		  }
		  
		 
		  
		  
		  
		  List<ApplyNear>  alist=and.getApplyByOtherid(userid);
		  StringBuilder str=new StringBuilder();
		  
		  if(alist!=null&&alist.size()>0)
		  {
			  for(int i=alist.size()-1;i>=0;i--)
			  {
				  
				  
				  ApplyNear an=alist.get(i);
				  
				 str.append("{").append("\"id\":\""+an.getId()+"\"")
					.append(",")
					.append("\"userid\":\""+an.getUser().getUserid() +"\"")
					.append(",")
					.append("\"nickname\":\""+an.getUser().getNickname()+"\"")
					.append(",")
					.append("\"userid\":\""+an.getUser().getUserid()+"\"")
					.append(",")
					.append("\"userimages\":\""+an.getUser().getUserimages()+"\"")
					.append(",")
					.append("\"status\":\""+an.getStatus() +"\"")
					  .append(",")
						.append("\"readstatus\":\""+an.getReadstatus()+"\"")
						.append(",")
						.append("\"dostatus\":\""+an.getDostatus()+"\"")
						.append(",")
						.append("\"borrowid\":\""+an.getBorrowtable().getId()+"\"")
						.append(",")
						.append("\"booktitle\":\""+an.getBorrowtable().getBookson().getBook().getBooktitle()+"\"")
					    .append("}").append(",");
				    }
			  
			  
				 out.write("["+str.substring(0,str.length()-1)+"]");
				 System.out.println(str);
			  
			  
		  }
		  else
		  {
			  
			  out.write("0"); 
		
		  }
		  
		  
		  
		  
		  
		
	}

}
