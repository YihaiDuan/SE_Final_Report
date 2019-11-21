package hfhServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderLineDao;
import entity.OnlineOrder;
import hfhdao.OnlineOrderDao;
import util.Httppost;
import util.access_tokken;


@WebServlet(name="deleteOnlineOrderServlet",urlPatterns="/deleteOnlineOrderServlet")
public class deleteOnlineOrderServlet extends HttpServlet {


	public deleteOnlineOrderServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
             doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		int pc=1;
		if(request.getParameter("pc")!=null)
		{
		pc=Integer.parseInt(request.getParameter("pc"));
		}
		OnlineOrderDao onlineorderdao=new OnlineOrderDao();
		onlineorderdao.uploadOnlineOrderDao(id);
		
		
		OrderLineDao old=new OrderLineDao();
		
		OnlineOrder ol=old.getOrderLineByid(id);
		
		String openid=ol.getUser().getOpenid();
		String formid=ol.getFormid();
		String orderdate=ol.getOrderdate();
		
		
		
		  
		String secret="132de5b5e208efa36294a2516728c477";
		 String appid="wxb31c7fb23e5d11c2";
		String access_token=access_tokken.getAccesstoken(appid,secret);
		String template_id="B5tZLcSspiPuc0dpMokbuEnqqFBazhYIfs2QeWQCZGQ";
		String booktitle=ol.getBookson().getBook().getBooktitle();
		String bookid2=ol.getBookson().getBook().getBookid();
		String typeid=ol.getBookson().getBook().getCategory().getCategoryid();
		
		
		
		/*
		 * 
		 * 书名
		 * 预订单号
		 * 类别：你预订的图书已放到预订区
		 * 取书时间
		 * 取书地点
		 * 
		 * */
		  
		
		String data="\n{\n\"touser\": "+"\""+openid+"\""+", \n \"template_id\": "+"\""+template_id+"\""+",\n\"page\":"+"\""+"pages/particulars/particulars?bookid="+bookid2+"&advice="+typeid+"\""+",\n\"form_id\": "+"\""+formid+"\""+",\n\"data\": "
				+ "{\n\"keyword1\": {\n\"value\":" +"\""+booktitle+"\""+",\n\"color\": \"#173177\"\n},"
						+ "\n\"keyword2\": {\n\"value\":"+"\""+id+"\""+",\n \"color\": \"#173177\"\n},"
								+ "\n\"keyword3\": {\n\"value\": \"你预订的图书已放到预订区\","+"\"color\": \"#173177\" \n},"
										+ "\n\"keyword4\": {\n\"value\":" +"\""+"截止到"+orderdate+"\""+",\n\"color\": \"#173177\"\n},"
												+"\n\"keyword5\": {\n\"value\": \"逸夫图书馆\","+"\"color\": \"#173177\" \n} }}";	
		System.out.println(data);
		String url="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+access_token;
		String success=Httppost.sendPost(url,data);
		System.out.println(success);		
		
	
		
		request.getRequestDispatcher("reservePageServlet?action=success").forward(request, response);
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
