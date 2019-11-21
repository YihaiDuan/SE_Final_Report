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

import dao.BorrowTableDao;
import dao.UserDao;
import entity.BorrowTable;
import entity.User;


@WebServlet("/getGroupByAdmin")
public class getGroupByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public getGroupByAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		 
	      PrintWriter out=response.getWriter() ;
	      String userid=request.getParameter("userId");
	      
	      
	      BorrowTableDao btd=new BorrowTableDao();
	      List<BorrowTable> blist= btd.getAllGroupBorrow(userid);
	      StringBuilder str =new StringBuilder();
	      String json=null;
	      
	           if(blist!=null&&blist.size()>0)
	           {
	        	   
	        	 
	        	   
	        	   for(int i=0;i<blist.size();i++)
	        	   {
	        		   BorrowTable bt=blist.get(i);	   
	        		   
	        		   str.append("{").append("\"id\":\""+bt.getId()+"\"")
	     				.append(",")
	     				.append("\"bookid\":\""+bt.getBookson().getBook().getBookid() +"\"")  
	     				.append(",")
	     				.append("\"booktitle\":\""+bt.getBookson().getBook().getBooktitle()+"\"")
	     				.append(",")
	     				.append("\"bookimages\":\""+bt.getBookson().getBook().getBookimages()+"\"")
	     				.append(",")
	     				.append("\"author\":\""+bt.getBookson().getBook().getAuthor()+"\"")
	     				.append(",")
	     				.append("\"publish\":\""+bt.getBookson().getBook().getPublish()+"\"")
	     				.append(",")
	     			   .append("\"borrowdate\":\""+bt.getBorrowdate()+"\"")
	     			   .append(",")
	     			   .append("\"booksonid\":\""+bt.getBookson().getBooksonid()+"\"")
	     			   .append(",")
	     			   .append("\"isbn\":\""+bt.getBookson().getBook().getPublishnumber()+"\"")
	     			  .append(",")
	     			   .append("\"cash\":\""+bt.getBookson().getBook().getCash()+"\"")
	     				.append("}").append(",");
	        		   
	        	   }
	        	   
	        	   UserDao ud=new UserDao();
		   	       User u=ud.getUserbyid(userid);
		   	       if(u!=null)
		   	       {
		   	        System.out.println(u.getUserid());
		   	        
		   	 json="fb_groupp"+"{"+"\"userid\":"+"\""+userid+"\","+"\"nickname\":"+"\""+u.getNickname()+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}";
		   	        
	         System.out.println(json);
		   	      out.write(json); 
		   	       }
	        	   
	        	   
	           }
		
	}

}
