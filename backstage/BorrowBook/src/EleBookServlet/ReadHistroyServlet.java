package EleBookServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EleBookDao.ReadHistroyDao;
import dao.BookDao;
import dao.UserDao;
import entity.Book;
import entity.ReadHistroy;
import entity.User;

@WebServlet("/ReadHistroy")
public class ReadHistroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReadHistroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		       doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	       response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		   String userid= request.getParameter("userid");
		   String bookid=request.getParameter("bookid"); 
		   String allpage=request.getParameter("allpage");
		   String currentpage=request.getParameter("currentpage");
		   String start=request.getParameter("start");
		   String end=request.getParameter("end");
		   String maxpage=request.getParameter("maxpage");
		   String showtitle=request.getParameter("showtitle");
		  
		   
		   
		   System.out.println(userid);
		   System.out.println(bookid);
		   System.out.println(allpage);
		   System.out.println(currentpage);
		   System.out.println(start);
		   System.out.println(end);
		   System.out.println(maxpage);
		   System.out.println(showtitle);
		   
		   
		   //判断是否已经在历史记录中
		  StringBuilder str =new StringBuilder();
		   ReadHistroyDao  rhd=new ReadHistroyDao(); 
		   
		   //存在获取历史记录-
		   if(rhd.getReadHistroy(userid, bookid)!=null)
		   {
			   
			   System.out.println("我来了");
			   ReadHistroy rh=rhd.getReadHistroy(userid, bookid);
			   
				str.append("{").append("\"pc\":\""+rh.getCurrentpage()+"\"")
				.append(",")
				.append("\"end\":\""+rh.getEnd() +"\"")
				.append(",")
				.append("\"start\":\""+rh.getStart()+"\"")
				.append(",")
			    .append("\"showtitle\":\""+rh.getShowtitle()+"\"")
			    .append(",")
			    .append("\"allpage\":\""+rh.getAllpage()+"\"")
				
			    .append("}");
			   
			   
			   //out.write(str.toString());
			   out.write("{"+"\"bol\":\"true\","+"\"histroy\":"+str+"}");
			   
		   }
		   //不存在则更新
		   else
		   {
			   
			   
			   ReadHistroy re=new ReadHistroy();
			   UserDao ud=new UserDao();
			    BookDao bd=new BookDao();
			   User u=ud.getUserbyid(userid);
			   Book  b=bd.getBookbyid(bookid);
			   
			   re.setAllpage(Integer.parseInt(allpage) );
			   re.setEnd(Integer.parseInt(end));
			   re.setShowtitle(showtitle);
			   re.setCurrentpage(Integer.parseInt(currentpage));
			   re.setMaxpage(Integer.parseInt(maxpage));
			   re.setBook(b);
			   re.setUser(u);
			   re.setTypename(b.getCategory().getType().getName());
			   
			  rhd.SaveReadHistroy(re);
			  out.write("{"+"\"bol\":\"false\""+"}");
		   }
		   
		   
		   
	
	}

}
