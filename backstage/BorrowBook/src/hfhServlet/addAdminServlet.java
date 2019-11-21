package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.Admin;
import hfhdao.AdminDao;
import util.MD5;
import util.SHA1;
@WebServlet(name="addAdminServlet",urlPatterns="/addAdminServlet")
public class addAdminServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public addAdminServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	    {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int pc=Integer.parseInt(request.getParameter("pc"));
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		int age;
		if(request.getParameter("age").equals("男"))
		{
              age=0;
		}else{
              age=1;
		}
		
		String phonenumber=request.getParameter("phonenumber");
		Admin admin=new Admin();
		 MD5 md=new MD5(); 
		
		 String newpassword=md.encrypt_30(password);
		   
		 System.out.println(newpassword);
		   
		admin.setUsername(username);
		admin.setPassword(newpassword);
	    admin.setAge(age);
		admin.setPhonenumber(phonenumber);
		AdminDao admindao=new AdminDao();
		admindao.addAdmin(admin);
		
	    request.getRequestDispatcher("AdminPageServlet?pc="+pc).forward(request, response);
	}

	
	
	public void init() throws ServletException {
		
	}

}
