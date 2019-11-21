package Appservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import dao.ConfirmOrderDao;
import entity.BorrowTable;
import entity.ConfirmOrder;


@WebServlet("/getConfirmHistory")
public class getConfirmHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public getConfirmHistory() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		       doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		 response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

		
		String userId= request.getParameter("userId");
		System.out.println(userId);
		
        
        
        ConfirmOrderDao cod=new ConfirmOrderDao();
        List<ConfirmOrder>  clist=cod.getCollectByUserid(userId);
        StringBuilder str=new StringBuilder();
        
        if(clist!=null&&clist.size()>0)
        {
        	
        	for(int i=clist.size()-1;i>=0;i--)
        	{
        		ConfirmOrder c=clist.get(i);
        		
        		StringBuilder str2=new StringBuilder();
        		String str3=null;
        		
        		   String borrowinf[]=c.getBorrowinf().split(",");
        		   
        		   if(borrowinf!=null&&borrowinf.length>0)
        		   {
        			   for(int j=0;j<borrowinf.length;j++)
        			   {
        				   
        			 BorrowTableDao  bto=new BorrowTableDao();
        		     BorrowTable bt=bto.getBorrowTablebyid(Integer.parseInt(borrowinf[j]));
        			str2.append(bt.getBookson().getBook().getBooktitle()).append(",");
        				   
        			 }
        			   str3=str2.substring(0,str2.length()-1);
        			   
        		   }
        		
        		
        		str.append("{")
				
				.append("\"username\":\""+c.getUsername()+"\"")
				.append(",")
				.append("\"booktitles\":"+"["+str3+"]")
				.append(",")
			   .append("\"cash\":\""+c.getCount()+"\"")
			   .append(",")
			   .append("\"date\":\""+c.getDate()+"\"")
			   .append(",")
			   .append("\"orderid\":\""+"594d0c15ac5021ca"+c.getId()+"\"")
			   .append(",")
			   .append("\"status\":\""+c.getStatus()+"\"")
			  .append("}").append(",");
        		
        	}
        	
        	out.write("["+str.substring(0, str.length() - 1) +"]");
        	System.out.println("["+str.substring(0, str.length() - 1) +"]");
        
        }
        else
        {
        	out.write("0");
        	
        }
        
		
	}

}
