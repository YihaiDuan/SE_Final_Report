package PublicJson;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import entity.Book;
import entity.ComparePrice;


@WebServlet("/getComparePrice")
public class getComparePrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public getComparePrice() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
	
		    String bookid=request.getParameter("bookid"); 
		    
		    System.out.println(bookid);
		    
		    BookDao bd=new BookDao();
		    Book b=bd.getBookbyid(bookid);
		    double cash=b.getCash();

    		//获取比价推荐json
    	 
    	 
    	 
	       List<ComparePrice>  cplist=new ArrayList<ComparePrice>();
	   		 String str= b.getCompareprice();//"30,京东,链接@20,当当,链接1@40,豆瓣,链接2@50,百度,链接3";
	   		 String str1=null;
	   		 System.out.println(str);
	   		 
	   		if(str!=null)
	   		{
	   		  String a[]=str.split("@@@");
	   		 
	   		 for(int i=0;i<a.length;i++)
	   		 {
	   			 
	   			 String b2[]=a[i].split("609@");
	   			 
	   			 ComparePrice cp=new ComparePrice();
	   			 
	   		System.out.println("length"+b2.length);
	   		
	   		
	   		if(b2.length==4)
	   		{
	   		    cp.setImages(b2[0]);
	   		     cp.setLink(b2[1]);
	   			
	   			 cp.setFrom(b2[2]);
	   			
	   			 
	   			 
	   			Pattern p = Pattern.compile("[0-9]+(\\.[0-9]+){0,1}");
	   			Matcher m = p.matcher(b2[3]);
	   			if(m.find()){
	   				str1 = m.group();
	   			}else{
	   				str1 = "34.5";
	   			}
	   		    cp.setPrice(str1);
	   			 
	   			  cplist.add(cp);
	   			 
	   			 
	   			
	   			
	   		}
	   		else
	   		{
	   			
	   		 cp.setImages("1");
   		     cp.setLink("1");
   			
   			 cp.setFrom("1");
	   			
   			cp.setFrom("1");
	   			
	   		}
	   		
	   			
	   		 }
	   		 
	   		 
	   		StringBuilder str3=new StringBuilder();
	   		 
	   		 if(cplist!=null&&cplist.size()>0)
	   		 {
	   			 
	   			 
	   			 for(int i=0;i<cplist.size();i++)
	   			 {
	   				 
	   				 ComparePrice cp=cplist.get(i);
	   				 System.out.println(cp.getId());
	   				 System.out.println(cp.getPrice());
	   				 
	   		 Double dis=Double.valueOf(cp.getPrice())-Double.valueOf(cash);
	   			int status=0;	 
	   				
	   			if(dis>0)
	   				 {
	   					 
	   					 status=1;
	   				 }
	   				 else
	   				 {
	   					 
	   					 status=0;
	   				 }
	   			DecimalFormat    df   = new DecimalFormat("######0.00");   
	   			
	   			
	   			  str3.append("{").append("\"id\":\""+cp.getId()+"\"")
	  	        .append(",")
	  			.append("\"price\":\""+cp.getPrice()+"\"")
	  			
	  			.append(",")
	  			.append("\"from\":\""+cp.getFrom() +"\"")
	  			
	  			.append(",")
	  			.append("\"link\":\""+cp.getLink()+"\"")
	  			
	  			.append(",")
	  			.append("\"images\":\""+cp.getImages()+"\"")
	  			.append(",")
	  			.append("\"dis\":\""+df.format(dis)+"\"")
	  			.append(",")
	  			.append("\"status\":\""+status+"\"")
	  			
	  			.append("},");
	   				 
	   			 }
	   		  out.write("["+str3.substring(0,str3.length()-1)+"]");
	   		
	   			 
	   		 }
	   		 
	   		}
	   		
    	 
	}

}
