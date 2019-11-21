package servlet;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/getOpenid")
public class getOpenid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getOpenid() {
        super();
        
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
            //异常记录
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
                //异常记录
            }
        }
        return result;
    }
    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			
		 PrintWriter  out=response.getWriter();
		  String codeid=request.getParameter("code");
		  String appid="wx91dbc8c07e056869";
		  String secret="7d670e3ecda557196e9684d5e03aee59";
		  String api="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+codeid+"&grant_type=authorization_code";
		   String str=GET(api);
		 
		    System.out.println(str);
		    
		    out.write(str);
		  
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
		        
		
		  out.write("lianjiechengogn");
		
		
	}

}
