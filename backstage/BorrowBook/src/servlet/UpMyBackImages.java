package servlet;

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

import dao.UserDao;
import entity.User;


@WebServlet("/UpMyBackImages")
public class UpMyBackImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpMyBackImages() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		      doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		 
	      PrintWriter out=response.getWriter() ;
	      
	      
	      
	     
	      //C:\webstore\BorrowBook\images
		 String filePath2=getServletContext().getRealPath("/")+"images";
	  
	      String filePath="C://Users//DELL//Desktop//李亚蓉//BorrowBook//WebContent//images";
	   
	      Date date= new Date();
	      SimpleDateFormat sdf = new SimpleDateFormat("ss");//璁剧疆鏃堕棿鏄剧ず鏍煎紡
		  String time= sdf.format(date);
	  		
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
	  			//su.save(filePath);
	  			
	  			
	  		} catch (Exception e) {
	  			//result="上传失败";
	  			e.printStackTrace();
	  			
	  		}
	  	
	  		
	  //com.jspsmart.upload.File tempFile=su.getFiles().getFile(0);
	  		String userid=su.getRequest().getParameter("userid");
	  		
	  String ext = su.getFiles().getFile(0).getFileExt();             //取得上传文件的扩展名
	  //String name = su.getRequest().getParameter("fileup");  //取得自定义的图片名
	  //3.保存上传的文件
	  try {
		su.getFiles().getFile(0).saveAs(filePath+"/"+userid+time+"."+ext);
		su.getFiles().getFile(0).saveAs(filePath2+"/"+userid+time+"."+ext);
	} catch (SmartUploadException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  		
	  	

	   //String filename=tempFile.getFileName();
	  
	  
	 
	        System.out.println(userid+"."+ext);
	        System.out.print(userid);
	        
	       
	        
	        UserDao ud=new UserDao();
	        
	        User u=new User();
	        u.setUserid(userid);

	        u.setBackimg("http://localhost：8080/BorrowBook/images/"+userid+time+"."+ext);
	       ud.UpdatePersonBackImages(u);
	       
	       
	     System.out.println(userid+"."+ext);
		
	}

}
