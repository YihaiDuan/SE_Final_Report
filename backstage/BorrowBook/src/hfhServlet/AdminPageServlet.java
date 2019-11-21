package hfhServlet;

import java.io.IOException;





import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Admin;
import entity.Book;
import entity.User;
import hfhdao.AdminPageDao;

@WebServlet(name="AdminPageServlet",urlPatterns="/AdminPageServlet")
public class AdminPageServlet extends HttpServlet {
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
	
	
	public AdminPageServlet() {
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
		int pc=getPc(request);
		String url=getUrl(request);
		AdminPageDao ld=new AdminPageDao();
		entity.Page<Admin> pageBean=ld.findByPage(pc);
		pageBean.setUrl(url);
		request.setAttribute("pb", pageBean);
		request.getRequestDispatcher("/admin.jsp").forward(request, response);

	}

	
	public void init() throws ServletException {
		
	}

}
