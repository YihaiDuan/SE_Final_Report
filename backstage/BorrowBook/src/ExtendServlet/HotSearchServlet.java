package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.HotSearchDao;
import dao.BookDao;
import entity.Book;
import entity.HotSearch;


@WebServlet("/HotSearchServlet")
public class HotSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public HotSearchServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		      doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		                
		response.setContentType("text/html;charset=utf-8");
	    request.setCharacterEncoding("utf-8");
	   
	    PrintWriter out=response.getWriter(); 
	    String bookid=request.getParameter("bookid");
	    
	    //判断是否已经存在
	    HotSearchDao  hsd=new HotSearchDao();
	   // 
		if(hsd.getHotSearchBook(bookid)!=null)
		{
			HotSearch hs=hsd.getHotSearchBook(bookid);
		    int oldnum=hs.getNum();
			hs.setId(hs.getId());
			hs.setNum(oldnum+1);
			hsd.UpdateHotnum(hs);
		}
		else
		{
			
			HotSearch hs=new HotSearch();
			
			BookDao bd=new BookDao();
			Book b=bd.getBookbyid(bookid);
			 hs.setNum(1);
			 hs.setBook(b);
			 hsd.SaveHotSearch(hs);
			
		}
	    
	    
		
	}

}
