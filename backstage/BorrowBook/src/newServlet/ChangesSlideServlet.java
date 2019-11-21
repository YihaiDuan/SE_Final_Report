package newServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

import entity.Slide;
import Extenddao.SlideDao;
@WebServlet(name="ChangesSlideServlet",urlPatterns="/ChangesSlideServlet")
public class ChangesSlideServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangesSlideServlet() {
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

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		/*int id=Integer.parseInt(request.getParameter("id"));
		String describ=request.getParameter("describ");
		String type=request.getParameter("type");
		
		
		SlideDao slidedao=new SlideDao();
		Slide slide = slidedao.getSlide(id);*/
		String filePath2=getServletContext().getRealPath("/")+"images";
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
	  	
		 
	 
     
      String describ=su.getRequest().getParameter("describ");
      String[] showstatus=su.getRequest().getParameterValues("showstatus");
      int show;
      if(showstatus==null)
      {
    	  show=0;
    	  
      }else{
    	  show=1;
    	  
      }
      int id= Integer.parseInt(su.getRequest().getParameter("id"));
     
      String url=su.getRequest().getParameter("url");
      String type=su.getRequest().getParameter("type"); 
      com.jspsmart.upload.File tempFile=su.getFiles().getFile(0);
  	  String img=tempFile.getFileName();
  	  SlideDao slidedao=new SlideDao();
  	  Slide slide=slidedao.getSlide(id);
  	  slide.setDescrib(describ);
  	  slide.setType(type);
  	  slide.setShowstatus(show);
  	  slide.setUrl(url);
  	  if(!img.equals(""))
  	  {
  	  slide.setImg(img);
  	  }
  	  slidedao.changesSlide(slide);
  	  response.sendRedirect("slidemanage.jsp");
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
