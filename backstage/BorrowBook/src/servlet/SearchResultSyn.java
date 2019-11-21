package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookSearchDao;
import dao.BookSonDao;
import entity.Book;


@WebServlet("/SearchResultSyn")
public class SearchResultSyn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SearchResultSyn() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		     doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		     
		  
		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		   String search =request.getParameter("search");
		   
		   BookSearchDao sd=new BookSearchDao();
		   
		   String str = search;
		

			List<Book> blist1 = sd.getBookbyKeyPage(str);
		  StringBuilder str1 = new StringBuilder();

			if (blist1 != null && blist1.size() > 0) {
				for (int i = blist1.size() - 1; i >= 0; i--) {

					Book b = blist1.get(i);

					// 获取藏数量
					Object totalnum = 0;
					BookSonDao bs = new BookSonDao();

					totalnum = bs.getTotalNumByBookid(b.getBookid());

					String introduce = null;

					if (b.getIntroduce() != null) {

						introduce = b.getIntroduce().replaceAll("\t", "");
					} else {
						introduce = "没有相关内容";

					}

					str1.append("{").append("\"bookid\":\"" + b.getBookid() + "\"").append(",")
							.append("\"bookimages\":\"" + b.getBookimages() + "\"").append(",")
							.append("\"booktitle\":\"" + b.getBooktitle() + "\"").append(",")
							.append("\"author\":\"" + b.getAuthor() + "\"").append(",")
							.append("\"category_id\":\"" + b.getCategory().getCategoryid() + "\"").append(",")
							.append("\"introduce\":\"" + introduce + "\"").append(",")
							.append("\"typename\":\"" + b.getCategory().getName() + "\"").append(",")
							.append("\"booknum\":\"" + b.getBooknum() + "\"").append(",")
							.append("\"type\":\"" + 1+ "\"").append(",")
							.append("\"totalnum\":\"" + totalnum + "\"")

							.append("}").append(",");
				}

				out.write("{" + "\"size\":" + "\"" + blist1.size() + "\"," + "\"searchlist\":" + "["
						+ str1.substring(0, str1.length() - 1) + "]}");

			} else {

				out.write("{" + "\"size\":" + "\"" +0+"\"" +"}" );

			}

		} // 最大if结束
		
		


}
