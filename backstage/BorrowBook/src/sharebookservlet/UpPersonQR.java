package sharebookservlet;

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

import dao.UserDao;
import entity.User;


@WebServlet("/UpPersonQR")
public class UpPersonQR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpPersonQR() {
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
	       
	       Date date= new Date();
	       SimpleDateFormat sdf = new SimpleDateFormat("ss");//璁剧疆鏃堕棿鏄剧ず鏍煎紡
	 	  String time= sdf.format(date);
	       
	       String userid=request.getParameter("userid");
	       String url=request.getParameter("url");
	       
	       
	       //生成二维码
		    
			int width=300;
			int height=300;
			String format="png";
            String content=url;
			
			HashMap hints=new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET,"utf-8");  //编码方式
			hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M); //大小等级
			hints.put(EncodeHintType.MARGIN,new Integer(2));   //调节空白边宽
			
			 
			
	BitMatrix bitMatrix;
	try {
		
		
		bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height,hints);
		
		//获取服务器的地址
		String filePath5=getServletContext().getRealPath("/")+"images";
		
		String filePath="C://javastore//BorrowBook//WebContent//images";
			
		     File file=new File(filePath);
		     File file5=new File(filePath5);
		     
			if(!file.exists())
			{
				file.mkdir();
				
			}
			
			if(!file5.exists())
			{
				file5.mkdir();
				
			}
			
	    
			
	Path file2=new File(filePath+"/"+userid+"personQR"+time+".png").toPath();
	
	Path file3=new File(filePath5+"/"+userid+"personQR"+time+".png").toPath();
		
		try {
		     
		
			MatrixToImageWriter.writeToPath(bitMatrix,format,file2);
			MatrixToImageWriter.writeToPath(bitMatrix,format,file3);

		} catch (IOException e) {
			
			e.printStackTrace();
		}
  


	} catch (WriterException e) {

		e.printStackTrace();
	}
	
	 //以上已经把服务器的二维码重新生成了一张
	 String images=userid+"personQR"+time+".png";

     
	   UserDao ud=new UserDao();
	 
	    User u=ud.getUserbyid(userid);
		
	    u.setUserid(userid);
	    u.setPersonQR(images);
	    
	    ud.UpdatePersonQR(u);
	    
	    
	   User u2=ud.getUserbyid(userid);
	    
        StringBuilder str=new StringBuilder();
        
        if(u2!=null)
        {
		   
	        str.append("{").append("\"userid\":\""+u2.getUserid()+"\"")
	        .append(",")
			.append("\"nickname\":\""+u2.getNickname() +"\"")
			
			.append(",")
			.append("\"userimages\":\""+u2.getUserimages() +"\"")
			
			.append(",")
			.append("\"sex\":\""+u2.getSex()+"\"")
			
			
			
			.append(",")
			.append("\"e_mail\":\""+u2.getE_mail()+"\"")
			
			.append(",")
			.append("\"phone\":\""+u2.getPhone()+"\"")
			
			.append(",")
			.append("\"p\":\""+u2.getP()+"\"")
			
			.append(",")
			.append("\"referstatus\":\""+u2.getReferstatus()+"\"")
			
			.append(",")
			.append("\"borrowwarnstatus\":\""+u2.getBorrowwarnstatus()+"\"")
			.append(",")
			.append("\"personQR\":\""+u2.getPersonQR()+"\"")
			.append(",")
			.append("\"latitude\":\""+u2.getLatitude()+"\"")
			.append(",")
			.append("\"longitude\":\""+u2.getLongitude()+"\"")
			
			 .append("}");
	        
    	
     	out.write(str.toString());
        }
	    
		
	}

}
