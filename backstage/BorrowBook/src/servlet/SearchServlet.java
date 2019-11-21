package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.BookSearchDao;
import dao.BookSonDao;
import entity.Book;
import entity.BookSon;
import util.Escape;
import util.JsonFormat;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

	public SearchServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();

		String search = request.getParameter("search");
		
       /* search = URLDecoder.decode(search,"utf-8");*/
        
		
		
		String action = request.getParameter("action");

		System.out.println("search"+search);
	
		System.out.println(action);

		BookSearchDao bsd = new BookSearchDao();

		// 根据关键词0搜索和图书标题1搜索
		if (action.equals("0") || action.equals("1")) {

			String str = search;
			char[] ch = str.toCharArray();

			for (int i = 0; i < ch.length; i++) {
				if ((int) ch[i] >= 65 && (int) ch[i] <= 90) {
					ch[i] += 32;
				}
			}

			str = new String(ch);

			List<Book> blist1 = bsd.getBookbyKey(str);
			StringBuilder str1 = new StringBuilder();

			if (blist1 != null && blist1.size() > 0) {
				for (int i = blist1.size() - 1; i >= 0; i--) {

					Book b = blist1.get(i);

					// 获取藏数量
					Object totalnum = 0;
					BookSonDao bs = new BookSonDao();

					totalnum = bs.getTotalNumByBookid(b.getBookid());

					String introduce = null;

				    if(b.getIntroduce()!=null)
		  	         {
		  	        	 
		  	        	
		  	        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
		  	         }
		  	         else
		  	         {
		  	        	 introduce="没有相关内容";
		  	        	 
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

		// 根据图书编号搜索

		if (action.equals("2")) {

			BookSonDao bsdd = new BookSonDao();

			BookSon bs = bsdd.getBookSon(search);

			StringBuilder str = new StringBuilder();

			if (bs != null) {
				
				
				

				BookDao bd = new BookDao();

				Book b = bd.getBookbyid(bs.getBook().getBookid());
				
			// 获取藏数量
				Object totalnum = 0;

				totalnum = bsdd.getTotalNumByBookid(bs.getBook().getBookid());

				String introduce = null;

			    if(b.getIntroduce()!=null)
	  	         {
	  	        	 
	  	        	
	  	        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
	  	         }
	  	         else
	  	         {
	  	        	 introduce="没有相关内容";
	  	        	 
	  	         }	 
	        	 

				str.append("{").append("\"bookid\":\"" + b.getBookid() + "\"").append(",")
				        .append("\"booksonid\":\"" + bs.getBooksonid() + "\"").append(",")
				        .append("\"borrowstatus\":\"" + bs.getBorrowstatus() + "\"").append(",")
//				        .append("\"orderstatus\":\"" + bs.getOrderstatus()+ "\"").append(",")
						.append("\"bookimages\":\"" + b.getBookimages() + "\"").append(",")
						.append("\"booktitle\":\"" + b.getBooktitle() + "\"").append(",")
						.append("\"author\":\"" + b.getAuthor() + "\"").append(",")
						.append("\"category_id\":\"" + b.getCategory().getCategoryid() + "\"").append(",")
						.append("\"introduce\":\"" + introduce + "\"").append(",")
						.append("\"typename\":\"" + b.getCategory().getName() + "\"").append(",")
						.append("\"booknum\":\"" + b.getBooknum() + "\"").append(",")
						.append("\"type\":\"" + 0 + "\"").append(",")
						.append("\"totalnum\":\"" + totalnum + "\"").append("}");
				

			      out.write("{" + "\"size\":" + "\"" +1+ "\"," + "\"searchlist\":" + "["
							+ str+ "]}");
			}

			else {
				
				out.write("{" + "\"size\":" + "\"" +0+"\"" +"}" );

			}

		} // 最大if结束

		// 根据作者搜索
		if (action.equals("3")) {

			List<Book> blist1 = bsd.getBookbyAuthor(search);
			StringBuilder str1 = new StringBuilder();

			if (blist1 != null && blist1.size() > 0) {
				for (int i = blist1.size() - 1; i >= 0; i--) {

					Book b = blist1.get(i);
					// 获取藏数量
					Object totalnum = 0;
					BookSonDao bs = new BookSonDao();

					totalnum = bs.getTotalNumByBookid(b.getBookid());

					String introduce = null;

				    if(b.getIntroduce()!=null)
		  	         {
		  	        	 
		  	        	
		  	        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
		  	         }
		  	         else
		  	         {
		  	        	 introduce="没有相关内容";
		  	        	 
		  	         }	 
		        	 

					str1.append("{").append("\"bookid\":\"" + b.getBookid() + "\"").append(",")
							.append("\"bookimages\":\"" + b.getBookimages() + "\"").append(",")
							.append("\"booktitle\":\"" + b.getBooktitle() + "\"").append(",")
							.append("\"author\":\"" + b.getAuthor() + "\"").append(",")
							.append("\"category_id\":\"" + b.getCategory().getCategoryid() + "\"").append(",")
							.append("\"introduce\":\"" + introduce + "\"").append(",")
							.append("\"typename\":\"" + b.getCategory().getName() + "\"").append(",")
							.append("\"booknum\":\"" + b.getBooknum() + "\"").append(",")
							.append("\"totalnum\":\"" + totalnum + "\"").append(",")
							.append("\"type\":\"" + 1 + "\"")

							.append("}").append(",");
				}

				out.write("{" + "\"size\":" + "\"" + blist1.size() + "\"," + "\"searchlist\":" + "["
						+ str1.substring(0, str1.length() - 1) + "]}");

			} else {

				out.write("{" + "\"size\":" + "\"" +0+"\"" +"}" );

			}

		} // 最大if结束

		// 根据搜索记录搜索
		if (action.equals("4")) {

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

					str1.append("{").append("\"bookid\":\"" + b.getBookid() + "\"").append(",")
							.append("\"bookimages\":\"" + b.getBookimages() + "\"").append(",")
							.append("\"booktitle\":\"" + b.getBooktitle() + "\"").append(",")
							.append("\"author\":\"" + b.getAuthor() + "\"").append(",")
							.append("\"category_id\":\"" + b.getCategory().getCategoryid() + "\"").append(",")
							.append("\"introduce\":\"" + introduce + "\"").append(",")
							.append("\"typename\":\"" + b.getCategory().getName() + "\"").append(",")
							.append("\"booknum\":\"" + b.getBooknum() + "\"").append(",")
							.append("\"totalnum\":\"" + totalnum + "\"").append(",")
							.append("\"type\":\"" + 1+ "\"")

							.append("}").append(",");
				}

				out.write("{" + "\"size\":" + "\"" + blist1.size() + "\"," + "\"searchlist\":" + "["
						+ str1.substring(0, str1.length() - 1) + "]}");

			} else {

				out.write("{" + "\"size\":" + "\"" +0+"\"" +"}" );

			}

		} // 最大if结束

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	}

	public void init() throws ServletException {

	}

}
