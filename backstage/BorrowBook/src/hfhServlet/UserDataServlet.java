package hfhServlet;

import hfhdao.BorrowTableDao;
import hfhdao.StateTime;
import hfhdao.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="UserDataServlet",urlPatterns="/UserDataServlet")
public class UserDataServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserDataServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		 int fyear=Integer.parseInt(request.getParameter("fyear"));
		 int fmotch=Integer.parseInt(request.getParameter("fmotch"));
		 int fday=Integer.parseInt(request.getParameter("fday"));
		 int tyear=Integer.parseInt(request.getParameter("tyear"));
		 int tmotch=Integer.parseInt(request.getParameter("tmotch"));
		 int tday=Integer.parseInt(request.getParameter("tday"));
		 StateTime t=new StateTime();
		 int number =t.getNumber(fyear, fmotch, fday, tyear, tmotch, tday);
		 String arr[]=t.getStatetime(number,tyear,tmotch,tday);
		 List<Integer> list=new ArrayList<Integer>();
		 UserDao userdao=new UserDao();
		 for(int i=0;i<arr.length;i++)
		 {
			list.add(userdao.getADDTodayUser(arr[i])); 
		 }
		 StringBuilder str=new StringBuilder();
		 str.append("\"number\":[");
		 for(int i=0;i<list.size();i++)
		 {
	    if(i==list.size()-1)
	    {
	    	 str.append(list.get(i));
	    }else{
	    	 str.append(list.get(i));
			 str.append(",");
	    }
		
		 }
		 str.append("],");
		 StringBuilder str1=new StringBuilder();
		 str1.append("\"time\":[");
		 for(int i=0;i<arr.length;i++)
		 {
		 if(i==arr.length-1)
		 {
			 str1.append("\""+arr[i]+"\"");
		 }
		 else{
			 str1.append("\""+arr[i]+"\"");
			 str1.append(",");	 
		 }
		
		 }
		 str1.append("],");
		 StringBuilder str2=new StringBuilder();
		 str2.append("{");
		 str2.append(str);
		 str2.append(str1);
		 str2.append("}");
		PrintWriter out = response.getWriter();
		out.println(str2);
		
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
