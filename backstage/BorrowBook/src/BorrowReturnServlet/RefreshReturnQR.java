package BorrowReturnServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import util.MD5;


@WebServlet("/RefreshReturnQR")
public class RefreshReturnQR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RefreshReturnQR() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter() ;
	      
	     
	      Date date= new Date();
	      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");//设置时间显示格式
			 
         String time= sdf.format(date);//将当前时间格式化为需要的类型

		   System.out.println(time);
		   
		   
		      Date date2= new Date();
		      SimpleDateFormat sdf2 = new SimpleDateFormat("ss");//设置时间显示格式
				 
	          String time2= sdf2.format(date2);//将当前时间格式化为需要的类型

			   System.out.println(time2);
			   
			   
			   String userid=request.getParameter("userid");
		       String count=request.getParameter("count");
		       String id=request.getParameter("id");
		      
		     
		    	   String time3=MD5.encrypt_5(time);
		           String fb=MD5.encrypt_10("fb_return");
		    	   
		    	   
		    	   
		String  json=fb+time3+userid+"@"+id+"@"+count;         
		        
		
		        

	      //生成二维码
		    
			int width=300;
			int height=300;
			String format="png";
              String content=json;
			
			HashMap hints=new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET,"utf-8");  //编码方式
			hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M); //大小等级
			hints.put(EncodeHintType.MARGIN,new Integer(2));   //调节空白边宽
			
			 
			
	BitMatrix bitMatrix;
	try {
		
		
		bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height,hints);
		
		//获取服务器的地址
		String filePath=getServletContext().getRealPath("/")+"images";
			
		     File file=new File(filePath);
			if(!file.exists())
			{
				file.mkdir();
				
			}
			
			
	    
			
	Path file2=new File(filePath+"/"+userid+time2+".png").toPath();
		
		try {
		     
		
			MatrixToImageWriter.writeToPath(bitMatrix,format,file2);

		} catch (IOException e) {
			
			e.printStackTrace();
		}



	} catch (WriterException e) {

		e.printStackTrace();
	}
	
	 //以上已经把服务器的二维码重新生成了一张
	 String images=userid+time2+".png";

out.write("{"+"\"BorrowQR\":"+"\""+images+"\","+"\"count\":"+"\""+count+"\""+"}");
   System.out.println("来了");
		
	}

}
