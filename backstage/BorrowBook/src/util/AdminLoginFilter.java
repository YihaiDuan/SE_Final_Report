/**
 * 
 */
package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * @date Jun 24, 2017
 * @todoTODO
 */
public class AdminLoginFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	 public void doFilter(ServletRequest arg0, ServletResponse arg1,  
             FilterChain arg2) throws IOException, ServletException {  
		
          HttpServletResponse response = (HttpServletResponse) arg1;    
        HttpServletRequest request=(HttpServletRequest)arg0;  
        HttpSession session = request.getSession(true);    
               String username = (String) session.getAttribute("admin");  
               String url=request.getRequestURI();  
              
               if(!url.equals("/BorrowBook/loginServlet")||username!=null)
               {
                if(username==null || username.equals(""))  
              {  
                    //判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转  
                  if(url!=null && !url.equals("") && ( url.indexOf("register")<0 && url.indexOf("Register")<0 ))  
                    {  
                	
                      response.sendRedirect("register.jsp");  
                       return ;  
                  }             
             }  
               }
                 //已通过验证，用户访问继续  
                arg2.doFilter(arg0, arg1);  
}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
