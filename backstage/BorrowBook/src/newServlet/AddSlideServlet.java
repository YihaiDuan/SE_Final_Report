package newServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

import Extenddao.SlideDao;
import entity.Slide;




@WebServlet("/AddSlideServlet")
public class AddSlideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddSlideServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	    	doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

          request.setCharacterEncoding("UTF-8");
	      response.setContentType("text/html; charset=UTF-8");  
	      
	   
	      PrintWriter out=response.getWriter() ;
		  
		  Date date =new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  String createdate= sdf.format(date);

			 String filePath2=getServletContext().getRealPath("/")+"images";
			 
			 System.out.println(filePath2);
			 String filePath="C://javastore//BorrowBook//WebContent//images";
			 
			 File file=new File(filePath);
			 File file2=new File(filePath2);
			 
				if(!file.exists())
		  		{
		  			file.mkdir();
		  			
		  		}
		  		
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
		  			su.save(filePath);
		  			su.save(filePath2);
		  			
		  		} catch (Exception e) {
		  			//result="上传失败";
		  			e.printStackTrace();
		  			
		  		}
		  	
			 
		 
	     
	      String describ=su.getRequest().getParameter("describ");
	   /*   String showstatus=su.getRequest().getParameter("showstatus");*/
	      String[] showstatus = null;
	      if(su.getRequest().getParameterValues("showstatus")!=null)
	      {
	      showstatus=su.getRequest().getParameterValues("showstatus");
	      }
	      int show;
	      if(showstatus==null)
	      {
	    	  show=0;
	    	  
	      }else{
	    	  show=1;
	    	  
	      }
	      String url=su.getRequest().getParameter("url");
	      String type=su.getRequest().getParameter("type");
	  
	      
	      com.jspsmart.upload.File tempFile=su.getFiles().getFile(0);
	      System.out.println("================"+tempFile);
	  	  String img="https://www.xunjia.net.cn/BorrowBook/images/"+tempFile.getFileName();
	  	  
	  	  //测试输出
	
//	      System.out.println(show);
//	      System.out.println(img);
//	      System.out.println(createdate);
//	      System.out.println(describ);
	      System.out.println(url);
	  	  System.out.println(type);
	  	  
	  	
	      
	      Slide to=new Slide();
	      SlideDao tod=new SlideDao();
	      
	   
	      to.setShowstatus(show);
	      to.setImg(img);
	      to.setDescrib(describ);
	      to.setDate(createdate);
	      to.setType(type);
	      to.setUrl(url);
	      
	      tod.SaveSlide(to);
	      
	      request.getRequestDispatcher("slidemanage.jsp").forward(request, response);
	
	}

}
