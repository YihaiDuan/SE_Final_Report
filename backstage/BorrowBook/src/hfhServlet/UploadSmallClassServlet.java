package hfhServlet;

import hfhdao.CategoryDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Category;
@WebServlet(name="UploadSmallClassServlet",urlPatterns="/UploadSmallClassServlet")
public class UploadSmallClassServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadSmallClassServlet() {
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
		int id=Integer.parseInt(request.getParameter("id"));
		CategoryDao categorydao = new CategoryDao();
		List<Category> list = categorydao.getListCategory(id);
		StringBuilder str1 = new StringBuilder();
		StringBuilder str2 = new StringBuilder();
		StringBuilder str3 = new StringBuilder();
	    str1.append("[");
	    str2.append("[");
	    for(int i=0;i<list.size();i++)
	    {
	    	Category category = list.get(i);
	    	if(i<list.size()-1)
	    	{
	    		str1.append("\""+category.getCategoryid()+"\",");
	    		str2.append("\""+category.getName()+"\",");
	    	}else{
	    		str1.append("\""+category.getCategoryid()+"\"");
	    		str2.append("\""+category.getName()+"\"");
	    	}
	    	
	    	
	    }
		str1.append("]");
		str2.append("]");
        str3.append("{\"id\":");
        str3.append(str1+",\"name\":");
        str3.append(str2+"}");
      //  System.out.println(str3);
        PrintWriter out = response.getWriter();
	      out.println(str3);
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
