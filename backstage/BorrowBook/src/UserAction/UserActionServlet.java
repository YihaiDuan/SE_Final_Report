package UserAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.UserDao;
import entity.Book;
import entity.User;
import entity.UserBrowse;
import entity.UserSearch;


@WebServlet("/UserActionServlet")
public class UserActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserActionServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		     doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
	      
	      String userid=request.getParameter("userid");
	      String action=request.getParameter("action");
	      String bookid=request.getParameter("bookid");
	      
	      UserDao ud=new UserDao();
    	  User u=ud.getUserbyid(userid);
    	  
    	  
    	  BookDao bd=new BookDao();
    	     Book b=  bd.getBookbyid(bookid);
    	     UserActionDao uad=new UserActionDao();
	    	  
	      
	      if(action.equals("userbrowse"))
	      {
	    	  
	    	
	    	
	    	  if(uad.getUserBrowse(userid, bookid)!=null)
	    	  {
	    		  
	     UserBrowse ub=  uad.getUserBrowse(userid, bookid);
	    		  int oldnum=ub.getNum();
	    		  
	    		  ub.setNum(oldnum+1);
	    		  
	    		  uad.UpdateUserBrowseNum(ub);
	    		  
	    	  }
	    	  else
	    	  {
	    		  
	    		  UserBrowse ub=new UserBrowse();
	    		  
	    		  ub.setUser(u);
	    		  ub.setBook(b);
	    		  uad.SaveUserBrowse(ub);
	    		  
	    	  }
	    	  
	    	  
	    	  
	    	  
	      }else
	      {
	    	  
	    	  
	    	  
	    	  if(uad.getUserSearch(userid, bookid)!=null)
	    	  {
	    		  
	    		  UserSearch ub=  uad.getUserSearch(userid, bookid);
	    		  int oldnum=ub.getNum();
	    		  
	    		  ub.setNum(oldnum+1);
	    		  
	    		  uad.UpdateUserSearchNum(ub);
	    		  
	    	  }
	    	  else
	    	  {
	    		  
	    		  UserSearch ub=new UserSearch();
	    		  
	    		  ub.setUser(u);
	    		  ub.setBook(b);
	    		  uad.SaveUserSearch(ub);
	    		  
	    	  }
	    	  
	    	  
	    	  
	      }
	      
	
	}

}
