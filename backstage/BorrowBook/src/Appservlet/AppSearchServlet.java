package Appservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookSearchDao;
import dao.BookSonDao;
import entity.Book;
import util.JsonFormat;


@WebServlet("/AppSearchServlet")
public class AppSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AppSearchServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();

		String search = request.getParameter("search");
		
        search = URLDecoder.decode(search,"utf-8");
        
		/* Escape.unescape(request.getParameter("search").toString().trim())*/;
		
		String action = request.getParameter("action");

		System.out.println("search"+search);
	
		System.out.println(action);

		BookSearchDao bsd = new BookSearchDao();
		
		
		// 根据搜索记录搜索
				if (action.equals("4")) 
				{
                   System.out.println("lalalalalalalal");
					List<Book> blist1 = bsd.getBookbyAll(search);
					StringBuilder str1 = new StringBuilder();

					if (blist1 != null && blist1.size() > 0) {
						for (int i = blist1.size() - 1; i >= 0; i--) {

							Book b = blist1.get(i);
							String introduce = null;

						    if(b.getIntroduce()!=null)
				  	         {
				  	        	 
				  	        	
				  	        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
				  	         }
				  	         else
				  	         {
				  	        	 introduce="没有相关内容";
				  	        	 
				  	         }	 
				        	 
							// 获取藏数量
							Object totalnum = 0;
							BookSonDao bs = new BookSonDao();

							totalnum = bs.getTotalNumByBookid(b.getBookid());

							str1.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
							.append(",")
							.append("\"bookimages\":\""+b.getBookimages() +"\"")
							.append(",")
							.append("\"booktitle\":\""+b.getBooktitle()+"\"")
							.append(",")
						   .append("\"category_id\":\""+b.getCategory().getCategoryid()+"\"")
						   .append(",")
						   .append("\"booknum\":\""+b.getBooknum()+"\"")
						   
						   .append(",")
						   .append("\"author\":\""+b.getAuthor()+"\"")
						   .append(",")
						   .append("\"typename\":\""+b.getCategory().getName()+"\"")
						   .append(",")
						   .append("\"bookintroduce\":\""+introduce+"\"")
							
							.append("}").append(",");
						}

		out.write("["+str1.substring(0, str1.length() - 1) +"]");

					} else {
                          System.out.println("hello");
						out.write("0");

					}
				}

		
		
	}

}
