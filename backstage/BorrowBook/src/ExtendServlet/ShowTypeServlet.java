package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.*;

import Extenddao2.TypeDao;
import dao.CategoryDao;


@WebServlet("/ShowTypeServlet")
public class ShowTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		    doPost(request,response);
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
		        	String catelist=null;
	
		       CategoryDao cd=new CategoryDao();
		       List<Category> clist=cd.getCategoryByid(t.getId());
			    StringBuilder str2 =new StringBuilder();
			  
			    
		       
		       if(clist!=null&&clist.size()>0)
		       {
		    	   for(int j=0;j<clist.size();j++)
		    	   {
		    		   
		    		  Category c=clist.get(j) ;
		    		  
		    		 int booknum= cd.getBookNum(c.getCategoryid());
		    		  str2.append("{").append("\"cateid\":\""+c.getCategoryid()+"\"")
						.append(",")
						.append("\"catename\":\""+c.getName()+"\"")
						.append(",")
						.append("\"booknum\":\""+booknum+"\"")
						.append("}").append(",");
		    		  
		    		   
		    		   
		    	   }//for
		    	   
		    	   catelist=str2.substring(0,str2.length()-1).toString();
		    	   
		    	 }//if
		       else
		       {
		    	   
		    	
		    	   catelist="{"+"\"size\":"+"\""+0+"\""+"}";
		    	   
		       }
		       	
		       
		       str.append("{").append("\"typeid\":\""+t.getTypeid()+"\"")
				.append(",")
				.append("\"img\":\""+t.getImg() +"\"")
				.append(",")
				.append("\"typename\":\""+t.getName()+"\"")
				 .append(",")
				   .append("\"list\":"+"["+catelist+"]"+"")
			   
			   .append("}").append(",");
   		  
		       
		        }//for
		        
		        
		        out.write("["+str.substring(0,str.length()-1)+"]");
		    	System.out.println("["+str.substring(0,str.length()-1)+"]");
		        	
		       }//if
		        
	
		
	}

}
