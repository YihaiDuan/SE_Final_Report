package newServlet;

import hfhdao.BookDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
import entity.GroupBook;
import entity.GroupMore;
import Extenddao.GroupBookDao;
import Extenddao.GroupDao;
@WebServlet(name="AddGroupMoreServlet",urlPatterns="/AddGroupMoreServlet")
public class AddGroupMoreServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddGroupMoreServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
	    String createdate = request.getParameter("createdate");
	    String deadline = request.getParameter("deadline");
	    String groupnum = request.getParameter("groupnum");
	    String maxnum = request.getParameter("maxnum");
	    String groupbookid = request.getParameter("groupbookid");
	    
	    GroupDao grounpdao = new GroupDao();
	    GroupBookDao groupbookdao = new GroupBookDao();
	    GroupMore groupmore = new GroupMore();
	    
	    groupmore.setCreatedate(createdate);
	    groupmore.setDeadline(deadline);
	    groupmore.setMaxnum(maxnum);
	    groupmore.setGroupnum(groupnum);
	    
	    String []groupbookids = groupbookid.split(",");
	    Set<GroupBook> groupbooks =new HashSet<GroupBook>();
	    groupmore = grounpdao.SaveGroup1(groupmore);
	    for(int i =0;i<groupbookids.length;i++)
	    {
	     GroupBook groupbook = groupbookdao.getGroupBook(Integer.parseInt(groupbookids[i]));
	    // groupbooks.add(groupbook);	
	     groupbook.setGroupmore(groupmore);
	     groupbookdao.UpdateGroupBook(groupbook);
	    }
	    groupbookdao.ClearGroupBook();
	   // groupmore.setGroupbook(groupbooks);
	    
	    request.getRequestDispatcher("addactive.jsp").forward(request,response);
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
