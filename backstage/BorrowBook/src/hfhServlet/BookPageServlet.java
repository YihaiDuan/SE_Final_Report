package hfhServlet;

import java.io.IOException;





import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import Extenddao.BorrowNumPageDao;
import Extenddao.RemainNumPageDao;
import entity.Book;
import hfhdao.BookPageDao;

@WebServlet(name="BookPageServlet",urlPatterns="/BookPageServlet")
public class BookPageServlet extends HttpServlet {
	
	
	private int getPc(HttpServletRequest request)
	{
		
		int pc=1;  //��ʼ�ǵ�һҳ
		String parm=request.getParameter("pc");//�������ҳ
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
	public BookPageServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		doPost(request,response);

	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int booktypeid = 0;
		if(request.getParameter("booktypeid")!=null)
		{
	    booktypeid = Integer.parseInt(request.getParameter("booktypeid"));
		}
	    String categoryid = "0";
	    if(request.getParameter("categoryid")!=null)
	    {
		categoryid = request.getParameter("categoryid");
	    }
		String type="borrow";
		if(request.getParameter("type")!=null){
		type = request.getParameter("type");
		}
		String mode="bigsmall";
		if(request.getParameter("mode")!=null)
		{
		mode = request.getParameter("mode");
		}
		
		int pc=getPc(request);
		String url=getUrl(request);
		entity.Page<Book> pageBean = null;
		 BorrowNumPageDao bnp = new BorrowNumPageDao();
		 RemainNumPageDao rnp = new RemainNumPageDao();
		if(booktypeid == 0)
		{   
			if(type.equals("borrow"))
			{
			  if(mode.equals("bigsmall"))
			  {
				  pageBean = bnp.BorrowNumPageAsc(pc);//！！！！
				  
			  }
			  if(mode.equals("smallbig")){
				  
				  pageBean = bnp.BorrowNumPageDesc(pc);
				  
			  }
			}
			if(type.equals("read"))
			{
				  if(mode.equals("bigsmall"))
				  {
                     pageBean = rnp.RemainNumPageAsc(pc);
					  
				  }
				  if(mode.equals("smallbig")){
					  
					  pageBean = rnp.RemianNumPageDesc(pc);
					  
				  }
				
			}
		/*	BookPageDao ld=new BookPageDao();
			pageBean=ld.findByPage(pc,categoryid);*/
		}else{
		     if(categoryid.equals("0"))
		     {
		    	 if(type.equals("borrow")){
		    	    if(mode.equals("bigsmall")){
		    	    	pageBean = bnp.BorrowNumPageAscBookType(pc, booktypeid);
		    	    }	 
		    		if(mode.equals("smallbig")){
		    			pageBean = bnp.BorrowNumPageDescBookType(pc, booktypeid);
		    			
		    		}  	 
		    }
		    	if(type.equals("read")){
		    		  if(mode.equals("bigsmall")){
			    	    pageBean = rnp.RemainNumPageAscBookType(pc, booktypeid);
			    	    	
			    	    }	 
			    		if(mode.equals("smallbig")){
			    		pageBean = rnp.RemianNumPageDescBookType(pc, booktypeid);
			    			
			    		} 
		    	}
		    	 
		    	 
		     }else{
		       	 if(type.equals("borrow")){
			    	    if(mode.equals("bigsmall")){
			    	    	pageBean = bnp.BorrowNumPageAscType(pc, categoryid);
			    	    	
			    	    }	 
			    		if(mode.equals("smallbig")){
			    			pageBean = bnp.BorrowNumPageDescType(pc, categoryid);
			    			
			    		}  	 
			    }
			    	if(type.equals("read")){
			    		  if(mode.equals("bigsmall")){
				    	    pageBean = rnp.RemainNumPageAscType(pc, categoryid);
				    	    	
				    	    }	 
				    		if(mode.equals("smallbig")){
				    		pageBean = rnp.RemianNumPageDescType(pc, categoryid);
				    			
				    		} 
			    	}	 
		     }	
		}
		
		pageBean.setUrl(url);
		request.setAttribute("booktypeid", booktypeid);
		request.setAttribute("categoryid", categoryid);
		request.setAttribute("type", type);
		request.setAttribute("mode", mode);
		request.setAttribute("pb", pageBean);
        if(request.getParameter("topic")!=null)
        {
        	request.getRequestDispatcher("/bookmanagement.jsp").forward(request, response);
        	
        }else{
		request.getRequestDispatcher("/bookmanagement.jsp").forward(request, response);
        }
	}

	
	public void init() throws ServletException {
		
	}
	
	

}
