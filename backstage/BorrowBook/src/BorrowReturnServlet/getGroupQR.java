package BorrowReturnServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import dao.BorrowTableDao;
import entity.BorrowTable;
import util.MD5;


@WebServlet("/getGroupQR")
public class getGroupQR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public getGroupQR() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		
		 response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      PrintWriter out = response.getWriter();
		
	      String userid=request.getParameter("userid");
		
		
	     BorrowTableDao btd=new BorrowTableDao();
	     List<BorrowTable> btlist=btd.getAllGroupBorrow(userid);
	      
	      
	      if(btlist!=null&&btlist.size()>0)
	      {
	    	   Date date= new Date();
	   	       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");//设置时间显示格式
	   		   String time= sdf.format(date);//将当前时间格式化为需要的类型
	           System.out.println(time);
	    	  
	    	  
	    	  String time2=MD5.encrypt_5(time);
	          String fb=MD5.encrypt_10("fb_groupp");
	           
	           
	 String  json=fb+time2+"@"+userid;           
	    	  
	    	  
	    	  
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
		
		
 
		
Path file2=new File(filePath+"/"+userid+".png").toPath();
	
	try {
	     
	
		MatrixToImageWriter.writeToPath(bitMatrix,format,file2);

	} catch (IOException e) {
		
		e.printStackTrace();
	}



} 
catch (WriterException e)
{

	e.printStackTrace();
}


String images=userid+".png";

out.write("{"+"\"BorrowQR\":"+"\""+images+"\""+"}");
System.out.println("来了");
	    	  
	      
	}//if
	      else
	      {
	    	  
	    	  
	    	  out.write("0");
	      }
	    	  
	}//function
	
}