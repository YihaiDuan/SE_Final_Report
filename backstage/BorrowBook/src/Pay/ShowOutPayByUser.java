package Pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.OutPayDao;
import entity.OutPay;


@WebServlet("/ShowOutPayByUser")
public class ShowOutPayByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowOutPayByUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request,response);
		   
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   String userid=request.getParameter("userid");
		   
		    OutPayDao opd=new OutPayDao();
		   
		   
		   List<OutPay> oplist=opd.getUserOutPay(userid);
		   StringBuilder str=new StringBuilder();
		   
		   if(oplist!=null&&oplist.size()>0)
		   {
			   
			   
			   
			   for(int i=oplist.size()-1;i>=0;i--)
			   {
				   
				   
				   OutPay op=oplist.get(i);
				   
				   str.append("{").append("\"id\":\""+op.getId()+"\"")
					.append(",")
					.append("\"fromdes\":\""+op.getFromdes()+"\"")
					.append(",")
					.append("\"date\":\""+op.getDate()+"\"")
					.append(",")
					.append("\"cash\":\""+op.getCash()+"\"")
					.append("}").append(",");
				   
				   
				   
			   }
			   
			   out.write("["+str.substring(0, str.length()-1)+"]");
			   
		   }
		   
	}

}
