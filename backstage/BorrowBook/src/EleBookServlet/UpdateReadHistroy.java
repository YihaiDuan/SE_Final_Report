package EleBookServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EleBookDao.ReadHistroyDao;
import entity.ReadHistroy;


@WebServlet("/UpdateReadHistroy")
public class UpdateReadHistroy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpdateReadHistroy() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		  response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		   String userid= request.getParameter("userid");
		   String bookid=request.getParameter("bookid"); 
		   String allpage=request.getParameter("allpage");
		   String currentpage=request.getParameter("currentpage");
		   String start=request.getParameter("start");
		   String end=request.getParameter("end");
		   String maxpage=request.getParameter("maxpage");
		   String showtitle=request.getParameter("showtitle");
		   
		   ReadHistroyDao rhd=new ReadHistroyDao();
		   ReadHistroy rh=rhd.getReadHistroy(userid, bookid);
		   if(rh!=null)
		   {
			   
			   int oldmaxpage=rh.getMaxpage();
			   if(oldmaxpage<Integer.parseInt(maxpage))
			   {
				   
				   rh.setId(rh.getId());
				   rh.setMaxpage(Integer.parseInt(maxpage));
				   rhd.UpdateMaxPage(rh);
				   
			   }
			   
			   
			   rh.setId(rh.getId());
			   rh.setAllpage(Integer.parseInt(allpage));
			   rh.setCurrentpage(Integer.parseInt(currentpage));
			   rh.setEnd(Integer.parseInt(end));
			   rh.setShowtitle(showtitle);
			   rh.setStart(Integer.parseInt(start));
			   rhd.UpdateAllInf(rh);
			   
		   }
		   
	
	}

}
