package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Extenddao2.TypeDao;
import entity.BookType;


@WebServlet("/ShowAllType")
public class ShowAllType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowAllType() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	    response.setContentType("text/html;charset=utf-8");
	    request.setCharacterEncoding("utf-8");
	   
	    PrintWriter out=response.getWriter(); 
	    
	    
	    TypeDao td=new TypeDao();
	    
	    List<BookType> tlist=td.getAllType();
	    StringBuilder str=new StringBuilder();
	    
	        if(tlist!=null&&tlist.size()>0)
	        {
	        	
	        for(int i=0;i<tlist.size();i++)
	        {
	        	
	        	BookType t=tlist.get(i);
str.append("{"+"\"id\":"+"\""+i+"\","+"\"typeid\":"+"\""+t.getId()+"\","+"\"tabName\":"+"\""+t.getName()+"\""+"},");
	        	
	        	
	       }//for
	        
	        
	        out.write("["+str.substring(0,str.length()-1)+"]");
	    	System.out.println("["+str.substring(0,str.length()-1)+"]");
	        	
	       }//if
	        
		
		
		
		
	}

}
