package hfhServlet;

import hfhdao.BorrowTableDao;
import hfhdao.StateTime;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
@WebServlet(name="DataStatisticsServlet",urlPatterns="/DataStatisticsServlet")
public class DataStatisticsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DataStatisticsServlet() {
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
		 int fyear=Integer.parseInt(request.getParameter("fyear"));
		 int fmotch=Integer.parseInt(request.getParameter("fmotch"));
		 int fday=Integer.parseInt(request.getParameter("fday"));
		 int tyear=Integer.parseInt(request.getParameter("tyear"));
		 int tmotch=Integer.parseInt(request.getParameter("tmotch"));
		 int tday=Integer.parseInt(request.getParameter("tday"));
		 StateTime t=new StateTime();
		 int number =t.getNumber(fyear, fmotch, fday, tyear, tmotch, tday);
		 
		 String arr[]=t.getStatetime(number,tyear,tmotch,tday);
		 // System.out.println(arr[0]+"================");
		 List<Integer> list=new ArrayList<Integer>();
		 List<Integer> list1=new ArrayList<Integer>();
		 BorrowTableDao borrow=new BorrowTableDao();
		 for(int i=0;i<arr.length;i++)
		 {
			list.add(borrow.getScanNum(arr[i])); 
		 }
		 for(int i=0;i<arr.length;i++)
		 {
			list1.add(borrow.getOverdueNum(arr[i])); 
		 }
		 StringBuilder str=new StringBuilder();
		 str.append("\"borrow\":[");
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
		  /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
	        for(int i=0;i<=arr.length-1;i++)
	        {    
			    String preMonday = null;
				try {
					preMonday = sdf.format( sdf2.parse(arr[i]));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      arr[i]=preMonday;
	        }*/
		 StringBuilder str2=new StringBuilder();
		 str2.append("\"overdue\":[");
		 for(int i=0;i<arr.length;i++)
		 {
		 if(i==arr.length-1)
		 {
			 str2.append("\""+arr[i]+"\"");
		 }
		 else{
			 str2.append("\""+arr[i]+"\"");
			 str2.append(",");	 
		 }
		
		 }
		 str2.append("],");
		 StringBuilder str3=new StringBuilder();
		 str3.append("\"time\":[");
		 for(int i=0;i<list1.size();i++)
		 {
			 if(i==list1.size()-1)
			 {
				 str3.append(list1.get(i));
			 }else{
				 str3.append(list1.get(i));
				 str3.append(",");
			 }
		
		 }
		 str3.append("]");
/*		 System.out.println(str);
		 System.out.println(str2);
		 System.out.println(str3);*/
		 StringBuilder str4=new StringBuilder();
		 str4.append("{");
		 str4.append(str);
		 str4.append(str2);
		 str4.append(str3);
		 str4.append("}");
		 PrintWriter out = response.getWriter();
		 out.println(str4);
		
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
