package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.Book;
import entity.OnlineOrder;
import hfhdao.Reserve2PageDao;
import hfhdao.Reserve3PageDao;
import hfhdao.ReservePageDao;
@WebServlet(name="reserve3PageServlet",urlPatterns="/reserve3PageServlet")
public class reserve3PageServlet extends HttpServlet {
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
	
	public reserve3PageServlet() {
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

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pc=getPc(request);
		String url=getUrl(request);
		Reserve3PageDao ld=new Reserve3PageDao();
		entity.Page<OnlineOrder> pageBean=ld.findByPage(pc);
		pageBean.setUrl(url);
		request.setAttribute("pb", pageBean);
		
		if(request.getParameter("action")!=null)
		{
			request.getRequestDispatcher("/overdueBook.jsp?action=success").forward(request, response);
			
		}else
		{
			request.getRequestDispatcher("/overdueBook.jsp").forward(request, response);
			
			
		}
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
