package ReadStatusUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.OutPayDao;
import entity.Dynamic;
import entity.DynamicReply;
import entity.OutPay;
import entity.Reply;


@WebServlet("/ReadStatusServlet")
public class ReadStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ReadStatusServlet() {
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
	      
	    
	    
	      ReadStatusDao rsd=new ReadStatusDao();
	    
	    List<Reply>  rlist=rsd.bolReviewReply(userid);
	    boolean bol1=false;
	    if(rlist!=null&&rlist.size()>0)
	    {
	    	bol1=true;
	    }
	    
	    List<Dynamic> dlist=rsd.bolDynamicComment(userid);
	    boolean bol2=false;
	    if(dlist!=null&&dlist.size()>0)
	    {
	    	bol2=true;
	    }
	    
		List<DynamicReply> drlist=rsd.bolDynamicReply(userid);
		 boolean bol3=false;
		    if(drlist!=null&&drlist.size()>0)
		    {
		    	bol3=true;
		    }
	    
		    OutPayDao opd=new OutPayDao();
		    
		    List<OutPay>  oplist=opd.getOutPayNoRead(userid);
	       boolean  bol4=false;
	     
	       if(oplist!=null&&oplist.size()>0)
	       {
	    	   
	    	   
	    	   bol4=true;
	       }
	       
	       
	   out.write("{"+"\"bol1\":"+"\""+bol1+"\","+"\"bol2\":"+"\""+bol2+"\","+"\"bol3\":"+"\""+bol3+"\","+"\"bol4\":"+"\""+bol4+"\""+"}");    
	
	
	}

}
