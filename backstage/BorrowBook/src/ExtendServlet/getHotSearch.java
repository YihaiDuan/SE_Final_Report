package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.HotSearchDao;
import entity.HotSearch;


@WebServlet("/getHotSearch")
public class getHotSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getHotSearch() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		        doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html;charset=utf-8");
	    request.setCharacterEncoding("utf-8");
	   
	    PrintWriter out=response.getWriter(); 
	    HotSearchDao hsd=new HotSearchDao();
	    
	    
	    
	      List<HotSearch>  hslist= hsd.getTopSearch();
	    StringBuilder str=new StringBuilder();
	    
	    if(hslist!=null&&hslist.size()>0)
	    {
	    	
	    	for(int i=0;i<hslist.size();i++)
	    	{
	    		
	    	HotSearch hs=hslist.get(i);
	    	
	    	
	    	 str.append("{").append("\"bookid\":\""+hs.getBook().getBookid()+"\"")
				.append(",")
				.append("\"booktitle\":\""+hs.getBook().getBooktitle()+"\"")
				.append(",")
			   .append("\"typeid\":\""+hs.getBook().getCategory().getCategoryid()+"\"")
			   .append("}").append(",");
	    		
	    	}
	    
	    	
	    	   out.write("["+str.substring(0,str.length()-1)+"]"); 
	    	
	    }
	    
	    
	    
		
	}

}
