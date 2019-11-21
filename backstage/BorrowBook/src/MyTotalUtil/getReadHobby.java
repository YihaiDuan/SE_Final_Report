package MyTotalUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EleBookDao.ReadHistroyDao;
import entity.ReadHistroy;

@WebServlet("/getReadHobby")
public class getReadHobby extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getReadHobby() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		  response.setContentType("application/json;charset=utf-8"); 
		  request.setCharacterEncoding("utf-8");
		  PrintWriter  out=response.getWriter();
		
		 
		  String userid=request.getParameter("userid");
		  
		  System.out.println("=================================="+userid);
		  
		  ReadHistroyDao rhd=new ReadHistroyDao();
		  
		  
		  
		  List <ReadHistroy>  rhlist=rhd.getReadByUser(userid);
		  Set<String>  rhset=new HashSet<String>();
		  
		  if(rhlist!=null&&rhlist.size()>0)
		  {
			  
			  for(int i=0;i<rhlist.size();i++)
			  {
				  
				  
				  ReadHistroy rh=rhlist.get(i);
				 
				  rhset.add(rh.getBook().getCategory().getType().getName());
				  
				  
			  }
			  
			}
		  
		  
		
		  
		  StringBuilder str=new StringBuilder();
		  DataDao dd=new DataDao();
		   for(String i :rhset )
		   {
			 System.out.println(i);
			   
			 int num=dd.getReadByTypeNum(i,userid);
				str.append("{").append("\"name\":\""+i+"\"")
				.append(",")
				.append("\"data\":"+num)
				
				
				.append("}").append(",");
			   
		   }	  
		   
		   String a=null;
		   
		   if(rhset.size()>0)
		   {
			   a="["+str.substring(0,str.length()-1)+"]";
			   
		   }
		   
		 
out.write("{"+"\"canvasId\":"+"\"pieGraph\","+"\"dataLabel\":"+"\"true\","+"\"type\":"+"\"pie\","+"\"width\":"+"\"320\","+"\"height\":"+"\"200\","+"\"series\":"+a+"}");   
		   
		
	}

}
