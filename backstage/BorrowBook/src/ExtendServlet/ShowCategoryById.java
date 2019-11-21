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
import dao.CategoryDao;
import entity.BookType;
import entity.Category;


@WebServlet("/ShowCategoryById")
public class ShowCategoryById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowCategoryById() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	    response.setContentType("text/html;charset=utf-8");
	    request.setCharacterEncoding("utf-8");
	   
	    PrintWriter out=response.getWriter(); 
	    
	    String typeid=request.getParameter("typeid");
	    
	    TypeDao td=new TypeDao();
	    BookType t=td.getBookTypeByid(Integer.parseInt(typeid));
	    
	    CategoryDao cd=new CategoryDao();
	    List<Category> clist=cd.getCategoryByid(Integer.parseInt(typeid));
	  StringBuilder str=new StringBuilder();
	    
	          if(clist!=null&&clist.size()>0)
	          {
	        	  
	        	  
	        	  for(int i=0;i<clist.size();i++)
	        	  {
	        		  
	        		Category c=clist.get(i);
	        		
	        		 int booknum= cd.getBookNum(c.getCategoryid());
	        		 
	        		  str.append("{").append("\"cateid\":\""+c.getCategoryid()+"\"")
						.append(",")
						.append("\"name\":\""+c.getName()+"\"")
						.append(",")
						.append("\"num\":\""+booknum+"\"")
						.append(",")
						.append("\"img\":\""+c.getTypeclass()+"\"")
						.append("}").append(",");
	        		  
	        		  
	        		  
	        		  
	        	  }
out.write("{"+"\"typeid\":"+"\""+t.getId()+"\","+"\"img\":"+"\""+t.getImg()+"\","+"\"typename\":"+"\""+t.getName()+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]"+"}");
	          
	        	  
	          }
	    
		
		
	}

}
