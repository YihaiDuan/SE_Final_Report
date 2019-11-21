package newServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.DiscountDao;
import entity.Discount;
@WebServlet(name="AddDiscountServlet",urlPatterns="/AddDiscountServlet")
public class AddDiscountServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddDiscountServlet() {
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
		String createdate = request.getParameter("createdate");
		String deadline = request.getParameter("deadline");
		int typestatus = Integer.parseInt(request.getParameter("typestatus"));
		int num = Integer.parseInt(request.getParameter("num"));
		String typename = request.getParameter("typename");
		int gradestatus = Integer.parseInt(request.getParameter("gradestatus"));
		
		Discount discount = new Discount();
		discount.setCreatedate(createdate);
		discount.setDeadline(deadline);
		discount.setTypestatus(typestatus);
		discount.setNum(num);
		discount.setTypename(typename);
		discount.setGradestatus(gradestatus);
		discount.setDiscount("0");
		discount.setShowstatus(1);
		
		DiscountDao discountdao = new DiscountDao();
		discountdao.SaveDiscount(discount);
		
		request.getRequestDispatcher("addactive.jsp").forward(request, response);
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
