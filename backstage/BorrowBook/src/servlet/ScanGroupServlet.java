package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import dao.ConfirmOrderDao;
import dao.UserDao;
import entity.BorrowTable;
import entity.ConfirmOrder;
import entity.User;


@WebServlet("/ScanGroupServlet")
public class ScanGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ScanGroupServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		           doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		
		   response.setContentType("application/json;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
	       PrintWriter out=response.getWriter() ;
		
		    String userid=request.getParameter("userId");
		    Date date =new Date();
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			  String confirmdate= sdf.format(date);
			  
			  
			 
			  String adminname=request.getParameter("adminId");
		
		    BorrowTableDao btd=new BorrowTableDao();
		    List<BorrowTable> blist= btd.getAllGroupBorrow(userid);
		     StringBuilder  str =new StringBuilder();
		      
		           if(blist!=null&&blist.size()>0)
		           {
		        	   
		        	 
		        	   
		        	   for(int i=0;i<blist.size();i++)
		        	   {
		        		   BorrowTable bt=blist.get(i);	 
		        		   
		        		
		      		       bt.setScanstatus(1);
		      		       btd.UpdateScanStatus(bt);
		        		   str.append(bt.getId()+",");
		        		   
		        	   }
		        	   
		        	   String str3=null;
		        	   if(str.length()>0)
		        	   {
		        		   
		        		   str3=str.substring(0, str.length()-1);
		        		   
		        		   
		        	   }
		        	   //保存确认单
				         
		 		      ConfirmOrderDao cod=new ConfirmOrderDao();
		 			  ConfirmOrder cf=new ConfirmOrder();
		 			  
		 			  UserDao ud=new UserDao();
		 			   User u=ud.getUserbyid(userid);
		 			
		 			  cf.setStatus(0);
		 			  cf.setUsername(u.getNickname());
		 			  cf.setAdminname(adminname);
		 			  cf.setBorrowinf(str3.toString());
		 			  cf.setDate(confirmdate.substring(0,confirmdate.length()-3));
		 			  //cf.setCount("0");
		 			  cod.SaveConfireOrder(cf);
		 		  
		 		  
		 	           out.write("609");
		 		  
		        	   
		        	   
		           
		           }
		           
		           
		           
		           
		           
	}

}
