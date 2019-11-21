package pageservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.BookPageDao;
import entity.Book;
import util.JsonFormat;


@WebServlet("/bookpageservlet")
public class bookpageservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public bookpageservlet() {
        super();
    }
    

	private int getPc(HttpServletRequest request)
	{
		
		int pc=1;  
		String parm=request.getParameter("pc");
		if(parm!=null&&!parm.trim().isEmpty())
		{
			
			pc=Integer.parseInt(parm);
			
		}
				return pc;
	
	}
	
private String getUrl(HttpServletRequest request)
	{
		
		String url=request.getRequestURI()+"?"+request.getQueryString();
		
		int index=url.lastIndexOf("&pc=");
		if(index!=-1)
		{
			url=url.substring(0,index);
		}
		
				return url;
	
	}
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	response.setContentType("text/html;charset=utf-8");
	   request.setCharacterEncoding("utf-8");
	   PrintWriter out=response.getWriter() ;
		
		
	   String pc=request.getParameter("p");
	    
	   String ps=request.getParameter("n");
	   
	  
	   String typeid=request.getParameter("typeid");
	   
	 
	  
	
	   
	  System.out.println(".............."+pc);
	  System.out.println(".............."+ps);
	  System.out.println(".............."+typeid);
	  
	  
	  BookPageDao ld=new BookPageDao();
	  entity.Page<Book> pageBean=ld.findByPage(Integer.parseInt(pc),Integer.parseInt(typeid));
	
	     List<Book> blist= pageBean.getBeanlist();
		StringBuilder str=new StringBuilder();
		
		if(blist!=null&&blist.size()>0)
		{
			for(int i=0;i<blist.size();i++)
			{
				Book b=blist.get(i);
				
				String introduce=null;
				
				if(b.getIntroduce()!=null)
				{
					
					introduce=JsonFormat.stringToJson(b.getIntroduce());
				}
				else
				{
					
					introduce=b.getIntroduce();
					
				}
				
		   System.out.println(b.getBookid());
		   
			str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
			.append(",")
			.append("\"bookimages\":\""+b.getBookimages() +"\"")
			.append(",")
			.append("\"booktitle\":\""+b.getBooktitle()+"\"")
			.append(",")
		   .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
		   .append(",")
		   .append("\"booknum\":\""+b.getBooknum()+"\"")
		   .append(",")
		   .append("\"bookintroduce\":\""+introduce+"\"")
		   .append(",")
		   .append("\"author\":\""+b.getAuthor()+"\"")
			.append("}").append(",");
				
				
			}
			out.write("{"+"\"curnum\":"+"\""+blist.size()+"\","+"\"curpage\":"+"\""+pc+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");
		 }
		else
		{
			
			out.write("{"+"\"curnum\":"+"\"0\"}");		
			System.out.println("{"+"\"curnum\":"+"\"0\"}");
		}
	 
		



		
		
	}
	public static void main(String[] args)
	{
		
		 BookPageDao ld=new BookPageDao();
		  entity.Page<Book> pageBean=ld.findByPage(3,1);
			//pageBean.setUrl(url);
			//request.setAttribute("categoryid", categoryid);
			//request.setAttribute("pb", pageBean);
		  
		     List<Book> blist= pageBean.getBeanlist();
			StringBuilder str=new StringBuilder();
			
			if(blist!=null&&blist.size()>0)
			{
				for(int i=0;i<blist.size();i++)
				{
					Book b=blist.get(i);
					/*
					 * 
					 *  '\b' : '\\b',  
    '\t' : '\\t',  
    '\n' : '\\n',  
    '\f' : '\\f',  
    '\r' : '\\r',  
    '"' : '\\"',  
    '\\' : '\\\\'
					 * */
			   System.out.println(b.getBookid());
			   
				str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
				.append(",")
				.append("\"bookimages\":\""+b.getBookimages() +"\"")
				.append(",")
				.append("\"booktitle\":\""+b.getBooktitle()+"\"")
				.append(",")
			   .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
			   .append(",")
			   .append("\"booknum\":\""+b.getBooknum()+"\"")
			 /*  .append(",")
			   .append("\"bookintroduce\":\""+b.getIntroduce().replaceAll("\t", "").replaceAll("\n","").replaceAll("\f","").replaceAll("\r","").replaceAll("\\","")+"\"")*/
				.append("}").append(",");
					
					
				}
				System.out.println("{"+"\"curnum\":"+"\""+blist.size()+"\","+"\"curpage\":"+"\""+1+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");
			 }
			else
			{
				
				System.out.println("{"+"\"curnum\":"+"\"0\"}");		
				System.out.println("{"+"\"curnum\":"+"\"0\"}");
			}
		  
	
		
	}

}
