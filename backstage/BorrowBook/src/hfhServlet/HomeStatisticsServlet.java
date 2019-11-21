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

import xml.BorrowXmlDao;
import xml.InComeDao;
import xml.XmlDao;
import entity.BookTypeNumber;
import entity.BorrowBookTypeNumber;
import entity.IncomeMonth;
@WebServlet(name="HomeStatisticsServlet",urlPatterns="/HomeStatisticsServlet")
public class HomeStatisticsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HomeStatisticsServlet() {
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
		List<BookTypeNumber> list1 = XmlDao.getFamilyMemebers();
		
		List<BorrowBookTypeNumber> list2 = BorrowXmlDao.getFamilyMemebers();
		List<IncomeMonth> list3 = InComeDao.getFamilyMemebers();
		
		//System.out.println(list3+"================");
		
		StringBuilder str1 = new StringBuilder();
		str1.append("[");
		StringBuilder str2 = new StringBuilder();
		str2.append("[");
		for(int i = 0 ; i <list3.size(); i++)
		{
			IncomeMonth incomemonth = list3.get(i);
		    String time = incomemonth.getTime();
		    double price = incomemonth.getPrice();
		    if(i<list3.size()-1)
		    {
		    str1.append("\""+time+"\",");
		    str2.append(price+",");
		    }else{
		    	
		    	 str1.append("\""+time+"\"");
				 str2.append(price+"");
		    }
		}
		str1.append("]");
		str2.append("]");
		
		
		StringBuilder str3 = new StringBuilder();
		str3.append("[");
		StringBuilder str4 = new StringBuilder();
		str4.append("[");
		for(int i = 0 ; i < list1.size() ; i++ )
		{
			BookTypeNumber booktypenumber = list1.get(i);
			String name =  booktypenumber.getBooktypename();
			int number = booktypenumber.getNumber();
			  if(i<list1.size()-1)
			    {
			    str3.append("\""+name+"\",");
			    str4.append(number+",");
			    }else{
			    	
			    	 str3.append("\""+name+"\"");
					 str4.append(number+"");
			    }
			
		}
		str3.append("]");
		str4.append("]");
		StringBuilder str5 = new StringBuilder();
		str5.append("[");
		StringBuilder str6 = new StringBuilder();
		str6.append("[");
		for(int i = 0 ; i < list2.size() ; i++ )
		{
			BorrowBookTypeNumber borrowbooktypenumber = list2.get(i);
			String name =  borrowbooktypenumber.getBorrowbooktypenumber();
			int number = borrowbooktypenumber.getNumber();
			  if(i<list2.size()-1)
			    {
			    str5.append("\""+name+"\",");
			    str6.append(number+",");
			    }else{
			    	
			    	 str5.append("\""+name+"\"");
					 str6.append(number+"");
			    }
			
		}
		str5.append("]");
		str6.append("]");
		
		System.out.println(str1+"======");
		StringBuilder str7 = new StringBuilder();
		str7.append("{");
		str7.append("\"time\":");
		str7.append(str1);
		str7.append(",");
		str7.append("\"price\":");
		str7.append(str2);
		str7.append("}");
		System.out.println(str7);
		
		StringBuilder str8 = new StringBuilder();
		str8.append("{");
		str8.append("\"name\":");
		str8.append(str3);
		str8.append(",");
		str8.append("\"number\":");
		str8.append(str4);
		str8.append("}");
		System.out.println(str8);
		
		StringBuilder str9 = new StringBuilder();
		str9.append("{");
		str9.append("\"name\":");
		str9.append(str5);
		str9.append(",");
		str9.append("\"number\":");
		str9.append(str6);
		str9.append("}");
		System.out.println(str9);
		
		request.setAttribute("str1",str7);
		request.setAttribute("str2",str8);
		request.setAttribute("str3",str9);
		
		request.getRequestDispatcher("index2.jsp").forward(request,response);
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
