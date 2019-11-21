package newServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.BorrowNumPageDao;
import entity.Book;
import entity.Page;

/*
 * 
 * 借阅量的升序降序排序
 * */

@WebServlet("/BorrowNumPageServlet")
public class BorrowNumPageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    
    public BorrowNumPageServlet() {
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
		
		
		
				doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	       request.setCharacterEncoding("UTF-8");
	       response.setContentType("text/html; charset=UTF-8");  
	       
	   	    int pc=getPc(request);
		    String url=getUrl(request);
		    /*
		     * action=0 asc 升序
		     * action=1 desc 降序
		     * 
		     * */
		    
		    String action=request.getParameter("action");
		    
		    
		    BorrowNumPageDao bnd=new BorrowNumPageDao();
		    
		    
		    if(action.equals("0"))
		    {
		        
		    	
		    	Page<Book> pageBean=bnd.BorrowNumPageAsc(pc);
		    	
		    	
		    	pageBean.setUrl(url);
		    	
		    	request.setAttribute("pb", pageBean);
		    	
		    	
		    	List<Book> blist=pageBean.getBeanlist();
		    	
		    	
		    	
		    	if(blist.size()>0)
		    	{
		    		
		    		System.out.println("正确"+blist.size());
		    	}
		    	else
		    	{
		    		System.out.println("错误");
		    		
		    	}
		    	
		    	
		    	request.getRequestDispatcher("/xxx.jsp").forward(request, response);  
		   
		    }
		    else
		    {
		    	
	            Page<Book> pageBean=bnd.BorrowNumPageAsc(pc);
	            
	            pageBean.setUrl(url);
		    	
		    	request.setAttribute("pb", pageBean);
		    	
		    	
	            List<Book> blist=pageBean.getBeanlist();
		    	
		    	if(blist.size()>0)
		    	{
		    		
		    		System.out.println("正确"+blist.size());
		    	}
		    	else
		    	{
		    		System.out.println("错误");
		    		
		    	}
		    	
		    	request.getRequestDispatcher("/xxx.jsp").forward(request, response);  
		    	
		   
		    }
		    
		  
	        
	       
		
		
	}

}
