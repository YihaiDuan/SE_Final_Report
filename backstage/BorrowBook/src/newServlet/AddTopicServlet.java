package newServlet;

import java.io.File;
/*
 * 
 * 添加主题
 * 
 * */




import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import Extenddao.TopicBookDao;
import Extenddao.Topicdao;

import com.jspsmart.upload.SmartUpload;

import entity.Topic;
import entity.TopicBook;
@WebServlet("/AddTopicServlet")
public class AddTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddTopicServlet() 
    {
      
    }

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
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
			  	
				 
			 
		      String topicname=su.getRequest().getParameter("topicname");
		      String describ=su.getRequest().getParameter("describ");
		      String[] showstatus =null;
		      int show = 0;
		      if(su.getRequest().getParameterValues("showstatus")!=null){
		    	  
		    	  showstatus=su.getRequest().getParameterValues("showstatus");
		      }
		 
		      com.jspsmart.upload.File tempFile=su.getFiles().getFile(0);
		  	  String topicimg="https://www.xunjia.net.cn/BorrowBook/images/"+tempFile.getFileName();
		  	     Topic to=new Topic();
			      Topicdao tod=new Topicdao();
			      
			      to.setTopicname(topicname);
			      to.setShowstatus(show);
			      to.setTopicimg(topicimg);
			      to.setDescrib(describ);
			      to.setDate(createdate);
			      TopicBook tb = null;
			    
			      Set<TopicBook> list =new HashSet<TopicBook>();
			      if(su.getRequest().getParameter("topicbook")!=""){
			     String topicbook = su.getRequest().getParameter("topicbook");
		         String []topicbooks = topicbook.split(",");
		  	     for(int i=0;i<topicbooks.length;i++){
		  	    	 System.out.println(topicbooks[i]);
		  	      TopicBookDao tbd = new TopicBookDao();
		  		  tb=tbd.getIdTopicBook(Integer.parseInt(topicbooks[i]));
		  		  list.add(tb);
		  		  
		  	      }
		  	  
		  	
		          }
			   to=tod.SaveTopic(to);
			    for (TopicBook topicBook : list) {
			    	 TopicBookDao tbd = new TopicBookDao();
			    	 topicBook.setTopic(to);
			    	 tbd.updateTopicBook(topicBook);
			    	 
				}
			      response.sendRedirect("specialmanage.jsp");
		
		 
		      
		           
		
	}

}
