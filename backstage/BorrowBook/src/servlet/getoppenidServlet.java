package servlet;

import javax.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.access_tokken;
import util.Httppost;
/**
 * Servlet implementation class getoppenidServlet
 */
@WebServlet("/getoppenidServlet")
public class getoppenidServlet extends HttpServlet {

	public getoppenidServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}
	
	
	
	  public static String GET(String url){
	        String result = "";
	        BufferedReader in = null;
	        InputStream is = null;
	        InputStreamReader isr = null;
	        try {
	            URL realUrl = new URL(url);
	            URLConnection conn = realUrl.openConnection();
	            conn.connect();
	            Map<String, List<String>> map = conn.getHeaderFields();
	            is = conn.getInputStream();
	            isr = new InputStreamReader(is);
	            in = new BufferedReader(isr);
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            //寮傚父璁板綍
	        }finally{
	            try {
	                if(in!=null){
	                    in.close();
	                }
	                if(is!=null){
	                    is.close();
	                }
	                if(isr!=null){
	                    isr.close();
	                }
	            } catch (Exception e2) {
	                //寮傚父璁板綍
	            }
	        }
	        return result;
	    }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			
		PrintWriter  out=response.getWriter();
		  String codeid=request.getParameter("code");
		  String appid="wxb31c7fb23e5d11c2";
		  String secret="132de5b5e208efa36294a2516728c477";
		  String api="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+codeid+"&grant_type=authorization_code";
		  String oppenid="o50AL0V1MZtz6imbAVcCI0Kw-yts";
		  // String str=GET(api);
		  String access_token=access_tokken.getAccesstoken(appid,secret);
		  String template_id="9CPBqUlmWuYPiMasDfhPFF3Ose31O9IdCsll1-ckDBM";
		  String formid="1493110632568";
		  // System.out.print(access_token);
		 // out.write(str);
		//  System.out.println(str);
		  //String data="\n{\n\"touser\": "+"\""+oppenid+"\""+", \n \"template_id\": "+"\""+template_id+"\""+",\n\"page\": \"index\",\n\"form_id\": "+"\""+formid+"\""+",\n\"data\": {\n\"keyword1\": {\n\"value\": \"鎵炬垜鍊熶功 \",\n\"color\": \"#173177\"\n},\n\"keyword2\": {\n\"value\":\"2017.06.23\",\n \"color\": \"#173177\"\n},\n\"keyword3\": {\n \"value\": \"2017.06.23\",\n\"color\": \"#173177\"\n},\n\"keyword4\": {\n\"value\": \"骞垮窞甯傚ぉ娌冲尯澶╂渤璺�208鍙穃",\n\"color\": \"#173177\" \n}\n},\n\"emphasis_keyword\": \"keyword1.DATA\"\n}";
		 // System.out.println(data);
		  String url="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+access_token;
		//String success=Httppost.sendPost(url,data);
		//System.out.println(success);
		

		  
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
