package EleBookServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EleBookDao.EleBookDao;
import EleBookDao.ReadHistroyDao;
import entity.MyEleBook;
import entity.ReadHistroy;


@WebServlet("/ShowMyEleBook")
public class ShowMyEleBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowMyEleBook() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		      doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		

		   response.setContentType("text/html;charset=utf-8");
	       request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   String userid=request.getParameter("userid");
		   
		   
		     StringBuilder str=new StringBuilder();
		     EleBookDao   ebd=new EleBookDao();
		     ReadHistroyDao  rhd=new ReadHistroyDao(); 
		     List<MyEleBook>  mlist=ebd.getAllMyEleBook(userid);
		     
		     
		     
		     /*
		      * 
		      * readstatus
		      * 0收费试读
		      * 1已经购买
		      * 2免费
		      * 3vip免费
		      * 
		      * 
		      * */
		     DecimalFormat    df   = new DecimalFormat("######0.00");  
		     if(mlist!=null&&mlist.size()>0)
		     {
		    	 
		    	 for(int i=mlist.size()-1;i>=0;i--)
		    	 {
		    		 
		    	MyEleBook m=mlist.get(i);
		    	//获取是否已经读的状态
		    	//未读显示未读   已读显示进度
		    	
		         
		    
		       
		       
		       
		     boolean process=false;
		    Double speed=0.0d;
		    	 if(rhd.getReadHistroy(userid, m.getBook().getBookid())!=null)
		    	 {
		    		 
		        ReadHistroy  rh= rhd.getReadHistroy(userid, m.getBook().getBookid());
		    	speed=(double)rh.getMaxpage()/(double)rh.getAllpage();
		    	
		    	process=true;
		    	 }
		    	
		    	
		    	
		    	  //判断是否已经购买   已经购买可以直接阅读
			       boolean readbol=false;
			       int readstatus=0;
			       
			       if(ebd.BolOwnEleBook(userid,m.getBook().getBookid()))
			       {
			    	   
			    	   
			    	   readbol=true;
			       }
			       
		    	  
			      int vipfreestatus=m.getBook().getVipfreestatus();
		    	  int  freestatus=m.getBook().getFreestatus();
		    	  
		    	  //免费
		    	  if(freestatus==0)
		    	  {
		    		  readstatus=2;
		    	  }
		    	  //收费
		    	  else
		    	  {
		    		  
		    		  
		    		  //vi免费读
		    		  if(vipfreestatus==1)
		    		  {
		    			  
		    			  readstatus=3;
		    			  
		    		  }
		    		  //收费
		    		  if(vipfreestatus==0)
		    		  {
		    			  
		    			  if(ebd.BolOwnEleBook(userid,m.getBook().getBookid()))
					       {
					    	   
					    	   //已经购买
		    				  readstatus=1;
					       }
		    			  else
		    			  {
		    				  //试读
		    				  readstatus=0;
		    				  
		    			  }
		    			  
		    		  }
		    		 
		    	  }
		    	
		    		 
		    	str.append("{").append("\"id\":\""+m.getId()+"\"")
					.append(",")
					.append("\"bookid\":\""+m.getBook().getBookid() +"\"")
					.append(",")
					.append("\"bookimages\":\""+m.getBook().getBookimages()+"\"")
					.append(",")
				    .append("\"booktitle\":\""+m.getBook().getBooktitle()+"\"")
					.append(",")
				    .append("\"readstatus\":\""+readstatus+"\"")
				    .append(",")
				    .append("\"process\":\""+process+"\"")
				    .append(",")
				    .append("\"speed\":\""+df.format(speed)+"\"")
				    .append("}").append(",");
		    		 
		    		}
		    	 
		     	  out.write("["+str.substring(0,str.length()-1)+"]");
		    	 
		     }
		
		        
	}

}
