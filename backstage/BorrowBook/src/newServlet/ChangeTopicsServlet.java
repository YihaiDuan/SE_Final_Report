package newServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.Topicdao;

import com.jspsmart.upload.SmartUpload;

import entity.Topic;
@WebServlet(name="ChangeTopicsServlet",urlPatterns="/ChangeTopicsServlet")
public class ChangeTopicsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeTopicsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
	     response.setContentType("text/html; charset=UTF-8");  
		 String filePath2=getServletContext().getRealPath("/")+"images";
		 
		 System.out.println(filePath2);
		// String filePath="C://javastore//BorrowBook//WebContent//images";
		 
		// File file=new File(filePath);
		 File file2=new File(filePath2);
		 
		/*	if(!file.exists())
	  		{
	  			file.mkdir();
	  			
	  		}*/
	  		
	  		if(!file2.exists())
	  		{
	  			file2.mkdir();
	  			
	  		}
	  		
	  	
	  		//初始化对象
	  		SmartUpload su=new SmartUpload();
	  	  
	  	  	
	  		su.initialize(getServletConfig(), request, response);
	  		//设置上传文件的大小
	  		su.setMaxFileSize(1024*1024*10);
	  		//设置所有文件大小
	  		su.setTotalMaxFileSize(1024*1024*100);
	  		//上传文件类型
	  		su.setAllowedFilesList("txt,jpg,gif,doc,png");
	      //  String result="上传成功";
	       
	  		//设置禁止上传文件类型
	  		try {
	  			
	  			
	  			su.setDeniedFilesList("rar,jsp,js");
	  			//上传文件
	  			su.upload();
	  			//su.save(filePath);
	  			su.save(filePath2);
	  			
	  		} catch (Exception e) {
	  			//result="上传失败";
	  			e.printStackTrace();
	  			
	  		}
	  	
		 
	  	     
		    /* int id = Integer.parseInt(request.getParameter("id"));
		     String date = request.getParameter("date");
		     String topicname = request.getParameter("topicname");
		     String describ = request.getParameter("describ");
		    */
      int id = Integer.parseInt(su.getRequest().getParameter("id"));
      String date = su.getRequest().getParameter("date");
      String topicname = su.getRequest().getParameter("topicname");
      String describ=su.getRequest().getParameter("describ");
      String[] showstatus=su.getRequest().getParameterValues("showstatus");
      int show;
      if(showstatus==null)
      {
    	  show=0;
    	  
      }else{
    	  show=1;
    	  
      }
      com.jspsmart.upload.File tempFile=su.getFiles().getFile(0);
     // System.out.println("================"+tempFile);
  	  String topicimg=tempFile.getFileName();
  	  
  	  
  	  
  	  Topicdao topicdao = new Topicdao();
  	  Topic topic = topicdao.getTopic(id);
  	  System.out.println(date+"==================");
  	  topic.setDate(date);
  	  topic.setTopicname(topicname);
  	  topic.setDescrib(describ);
  	  if(topicimg!="")
  	  {
  	  topic.setTopicimg(topicimg);
  	  }
  	  topic.setShowstatus(show);
  	  topicdao.changeTopic(topic);
  	     
	  response.sendRedirect("changetopic.jsp?id="+id);
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
