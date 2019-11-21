package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;





import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import dao.BorrowTableDao;
import entity.BorrowTable;


@WebServlet("/TransmitBorrowQR")
public class TransmitBorrowQR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public TransmitBorrowQR() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
				doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		 response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   
	       PrintWriter out=response.getWriter() ;
		   
		   
		   String id=request.getParameter("id");
		   
		   
		   
		   BorrowTableDao btd=new BorrowTableDao();
		   
		     BorrowTable bt=btd.getBorrowbyId(Integer.parseInt(id));
		     
		     if(bt.getStatus()==1)
		     {
		    	 
		 out.write("0"); 	 
		    	
		     }
		     else
		     {
		   
		    int width=300;
			int height=300;
			String format="png";
	String content="transmitborrow"+id;
			



			HashMap hints=new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET,"utf-8");  //编码方式
			hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M); //大小等级
			hints.put(EncodeHintType.MARGIN,new Integer(2));   //调节空白边宽
			
	BitMatrix bitMatrix;
	try {
		
		
		bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height,hints);
		
//		
//	String filePath5=getServletContext().getRealPath("/")+"images";
//		
//		String filePath="C://javastore//BorrowBook//WebContent//images";
		
  Path file=new File("C://Users//DELL//Desktop//李亚蓉//BorrowBook//WebContent//images/"+"transmitborrow"+id+".png").toPath();
  Path file2=new File(getServletContext().getRealPath("/")+"images/"+"transmitborrow"+id+".png").toPath();	
		
		
		try {
		
		
			MatrixToImageWriter.writeToPath(bitMatrix,format,file);
			MatrixToImageWriter.writeToPath(bitMatrix,format,file2);

		} catch (IOException e) {
			
			e.printStackTrace();
		}



	} catch (WriterException e) {

		e.printStackTrace();
	}
			

	
	   out.write("{\"imagesQR\":"+"\""+"transmitborrow"+id+".png"+"\"}");
	   
		     }

	}

}
