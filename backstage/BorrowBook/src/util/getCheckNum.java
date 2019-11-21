package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


@WebServlet("/getCheckNum")
public class getCheckNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public getCheckNum() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		   doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
	   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   String userid=request.getParameter("userid");
		   
		   System.out.println("=========="+userid);
		    // out.write("123456");
		      int n = 0 ;
		       n=(int)(Math.random()*1000000);
		       System.out.println(n);
		     
		       
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
			post.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=gbk");
			NameValuePair[] data = { new NameValuePair("Uid", "hfh1054184655"), 
					new NameValuePair("Key", "837882102d9bfeba4234"),
					new NameValuePair("smsMob",userid), 
					new NameValuePair("smsText","你的验证是"+String.valueOf(n))};		
			
			post.setRequestBody(data);

			client.executeMethod(post);
			
			Header[] headers = post.getResponseHeaders();
			
			int statusCode = post.getStatusCode();
			
			System.out.println("statusCode:" + statusCode);
			
			for (Header h : headers) {
				System.out.println(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes(
					"gbk"));
			System.out.println(result);
			post.releaseConnection();
			out.write("{"+"\"checknum\":"+"\""+n+"\""+"}");
		   
		
	}

}
