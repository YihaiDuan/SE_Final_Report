package hfhServlet;

import hfhdao.AdminDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MD5;
import entity.Admin;
import hfhdao.AdminDao;
@WebServlet(name="UpdateServlet",urlPatterns="/UpdateServlet")
public class UpdateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateServlet() {
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");    
		int id=Integer.parseInt(request.getParameter("id"));
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		MD5 md=new MD5(); 
		 String newpassword=md.encrypt_30(password);
		String age=request.getParameter("age");
		String phonenumber=request.getParameter("phonenumber");
		AdminDao admindao=new AdminDao();
		Admin admin=admindao.getAdmin(id);
		admin.setUsername(username);
		admin.setPassword(newpassword);
		if(age.equals("男"))
		{
			admin.setAge(0);
			
		}else{
			admin.setAge(1);
			
		}
		admin.setPhonenumber(phonenumber);
		admindao.updateAdmin(admin);
		int pc=Integer.parseInt(request.getParameter("pc"));
		request.getRequestDispatcher("AdminPageServlet?pc="+pc).forward(request, response);
		/*StringBuilder str=new StringBuilder();
		str.append("{");
		str.append("\"id\":"+admin.getId()+",");
		str.append("\"username\":"+admin.getUsername()+",");
		if(admin.getAge()==0)
		{
		str.append("\"age\":\"男\",");
		}else{
		str.append("\"age\":\"女\",");	
		}
		str.append("\"phonenumber\":"+admin.getPhonenumber());
		str.append("}");
		PrintWriter out = response.getWriter();
		out.println(str);*/

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
