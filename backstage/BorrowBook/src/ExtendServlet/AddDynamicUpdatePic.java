package ExtendServlet;

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
import com.jspsmart.upload.SmartUploadException;

import Extenddao2.DynamicDao;
import entity.Dynamic;


@WebServlet("/AddDynamicUpdatePic")
public class AddDynamicUpdatePic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AddDynamicUpdatePic() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		     doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		 
	      PrintWriter out=response.getWriter() ;
	      
	      
	      
	     
	      //C:\webstore\BorrowBook\images
		 String filePath2=getServletContext().getRealPath("/")+"images";
	  
	      String filePath="C://javastore//BorrowBook//WebContent//images";
	      
	    /*  Date date =new Date();
	      SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	      String nowdate=sdf.format(date);*/
	  		
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
	  		/*	su.save(filePath);
	  			su.save(filePath2);*/
	  			
	  			
	  		} catch (Exception e) {
	  			//result="上传失败";
	  			e.printStackTrace();
	  			
	  		}
	  	long nowdate=System.currentTimeMillis();
	  		/*com.jspsmart.upload.File tempFile=su.getFiles().getFile(0);
	  	   String filename=tempFile.getFileName();
	  	   System.out.println("\\\\\\\\我是更新后的图片"+filename);*/
	  		String userid=su.getRequest().getParameter("userid");
	  		String id=su.getRequest().getParameter("id");
	  		 String ext = su.getFiles().getFile(0).getFileExt();             //取得上传文件的扩展名
	  		System.out.println("更新后id"+id);
	  		
	  	  //3.保存上传的文件
	  	  try {
	  		su.getFiles().getFile(0).saveAs(filePath+"/"+userid+String.valueOf(nowdate)+id+"."+ext);
	  		su.getFiles().getFile(0).saveAs(filePath2+"/"+userid+String.valueOf(nowdate)+id+"."+ext);
	  	} catch (SmartUploadException e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
	  	}
	  	String filename=userid+nowdate+id+"."+ext;
	  	
	  	System.out.println("更新后"+filename);
	  		
	  		String dynamicid=su.getRequest().getParameter("dynamicid");
	  		
	  		DynamicDao dd=new DynamicDao();
	        Dynamic d=dd.getDynamicByid(Integer.parseInt(dynamicid));
	        
	        
	         String oldimg=d.getImages();
	         
	         d.setImages(oldimg+","+filename);
	         
	         
	         dd.UpdateDynamic(d);
	  	
	  
	   
	   
	      out.write("我是更新后的图片");
	  
	}

}
