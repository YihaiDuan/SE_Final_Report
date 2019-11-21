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
import dao.BookDao;
import dao.UserDao;
import entity.Book;
import entity.Dynamic;
import entity.User;


@WebServlet("/AddDynamicServlet")
public class AddDynamicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddDynamicServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");

		 
	      PrintWriter out=response.getWriter() ;
	      Date date =new Date();
	      SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
	      String nowdate=sdf.format(date);
	      
	      Date date2= new Date();
	      SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");//璁剧疆鏃堕棿鏄剧ず鏍煎紡
		  String time= sdf2.format(date2);
	     
	      //C:\webstore\BorrowBook\images
		 String filePath2=getServletContext().getRealPath("/")+"images";
	  
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
	  			/*su.save(filePath);
	  			su.save(filePath2);
*/	  			
	  		} catch (Exception e) {
	  			//result="上传失败";
	  			e.printStackTrace();
	  			
	  		}
	  		String userid=su.getRequest().getParameter("userid");
	  		
	  	  String ext = su.getFiles().getFile(0).getFileExt();             //取得上传文件的扩展名
	   	long time1=System.currentTimeMillis();
	  	try {
			su.getFiles().getFile(0).saveAs(filePath+"/"+userid+time1+"."+ext);
			su.getFiles().getFile(0).saveAs(filePath2+"/"+userid+time1+"."+ext);
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	  	
	  		String bookid=su.getRequest().getParameter("bookid");
	  		String describ=su.getRequest().getParameter("describ");
	  		
	  		
	  		  System.out.println(userid);
		      System.out.println(bookid);
		      System.out.println(describ);
	  		
	 
	  	
	         //com.jspsmart.upload.File tempFile=su.getFiles().getFile(0);
	        // String filename=tempFile.getFileName();
	         
	         
	         
	        // System.out.println("\\\\\\\\我是第一张图片"+filename);
	   
	         
	         DynamicDao dd=new DynamicDao();
		      Dynamic d=new Dynamic();
		      
		      
		      UserDao ud=new UserDao();
		      User u=ud.getUserbyid(userid);
		      d.setUser(u);
		      
		      BookDao bd=new BookDao();
		      Book b=bd.getBookbyid(bookid);
		      d.setBook(b);
		      
		      
		      d.setImages(userid+time1+"."+ext);
		      d.setDescrib(describ);
		      d.setGroupmainid(0);
		      d.setTypeid(0);
		      d.setDate(nowdate);
		      
		     
		      int dynamicid=dd.SaveDynamic(d);
	   
	         out.write(String.valueOf(dynamicid).toString());
	  
	
	}

}
