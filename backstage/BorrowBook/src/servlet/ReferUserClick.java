package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import dao.ReferDao;
import entity.Category;
import entity.Refer;


@WebServlet("/ReferUserClick")
public class ReferUserClick extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReferUserClick() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
		
       	      String userid=request.getParameter("userid");
       	      String categoryid=request.getParameter("categoryid");

			ReferDao rd=new ReferDao();
			
			if(rd.getBolUserCategoryid(userid,categoryid)!=null)
			{
				
				Refer r=rd.getBolUserCategoryid(userid,categoryid);
				
				int id=r.getId();
				int num=r.getNum();
				
				r.setId(id);
				r.setNum(num+1);
				
				
				rd.UpDateNum(r);
			   System.out.println("更新成功");
			
			}
			else
			{
				
				Refer r=new Refer();
				
				CategoryDao cd=new CategoryDao();
				Category c=cd.getCategoryByid(categoryid);
				
				r.setCategory(c);;
				
				
				r.setUserid(userid);
				rd.SavaRefer(r);
				
				
				  System.out.println("添加成功");
				
				
			}
			
		
		
	}

}
