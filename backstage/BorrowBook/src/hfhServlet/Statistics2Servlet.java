package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hfhdao.BorrowTableDao;
import hfhdao.StateTime;


@WebServlet(name="Statistics2Servlet",urlPatterns="/Statistics2Servlet")
public class Statistics2Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Statistics2Servlet() {
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
		
		 int fyear=Integer.parseInt(request.getParameter("fyear"));
		 int fmotch=Integer.parseInt(request.getParameter("fmotch"));
		 int fday=Integer.parseInt(request.getParameter("fday"));
		 int tyear=Integer.parseInt(request.getParameter("tyear"));
		 int tmotch=Integer.parseInt(request.getParameter("tmotch"));
		 int tday=Integer.parseInt(request.getParameter("tday"));
		 StateTime t=new StateTime();
		 int number =t.getNumber(fyear, fmotch, fday, tyear, tmotch, tday);
		 
		 String arr[]=t.getStatetime(number,tyear,tmotch,tday);
		 System.out.println(arr[0]+"================");
		 List<Integer> list=new ArrayList<Integer>();
		 BorrowTableDao borrow=new BorrowTableDao();
		 for(int j=1;j<=15;j++)
		 {
			 int a=0;
		 for(int i=0;i<arr.length;i++)
		 {
			a+=borrow.getCategoryNum(arr[i],Integer.toString(j));
		 }
		   list.add(a);
		 
		 }
		 StringBuilder str=new StringBuilder();
		 str.append("[");
		 for(int i=0;i<list.size();i++)
		 {
		 str.append(list.get(i));
		 str.append(",");
		 }
		 str.append("]");
		 request.setAttribute("news",str);
		 request.getRequestDispatcher("classifition.jsp").forward(request, response);
	/*	 if(request.getParameter("categoryid")==null)
		 {
			 
			 request.getRequestDispatcher("Statistics.jsp?categoryid=1").forward(request, response);
			 
		 }else{
			 System.out.println("回去了");
			 request.getRequestDispatcher("Statistics.jsp?categoryid="+request.getParameter("categoryid")).forward(request, response);		 
		 }*/
		 
		 
		
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
