package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowTableDao;
import dao.UserDao;
import entity.BorrowTable;
import entity.User;


@WebServlet("/getReturnlanInfo")
public class getReturnlanInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public getReturnlanInfo() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	           doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	    
			  response.setContentType("text/html;charset=utf-8");
		         request.setCharacterEncoding("utf-8");
		       
		         PrintWriter out=response.getWriter();
		         
		         
		         Date date= new Date();//创建一个时间对象，获取到当前的时间
		         SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//设置时间显示格式
				 
		          String time= sdf.format(date);//将当前时间格式化为需要的类型

				   System.out.println(time);
		         
		         System.out.println("我来了获取还书二维码的内容");
		         String userid=request.getParameter("userid");
		         
		         System.out.println("要还书的用户"+userid);
		         
		         BorrowTableDao bto=new BorrowTableDao();
		         
		          
		         List<BorrowTable>  blist=bto.getBookOkScan(userid);
		         StringBuilder str=new StringBuilder();
		   
		         
		      
		         java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.0");  
		        
		         
		       if(blist!=null&&blist.size()>0)
		       {
		        	 
		        for(int i=blist.size()-1;i>=0;i--)	
		        {
		        	 
		        	 BorrowTable bt=blist.get(i);
		        	 
		        	 System.out.println("我来了输出获取的对象集合"+bt);
		        	 
		int  day=(int) ((Date.parse(bt.getDeadline())-Date.parse(time))/86400000);
		           
		           
		           System.out.println("输出剩下的天数"+day);
		           
		           String warn=null;
		           float fine=0.0f;
		           boolean  beyondbol=false;
		           
		           if(day>0)
		           {
		        	   
		        	   warn="无逾期";
		        	   fine=0.0f;
		        	   
		           }
		           else
		           {
		        	   warn="逾期"+(-day)+"天";
		        	   fine=(float)((-day)*(0.25));
		        	   beyondbol=true;
		        	   
		           }
		           
		           
		           
		           
		          //计算租金
		           //borrowday借书多少天
int borrowday=(int) ((Date.parse(time)-Date.parse(bt.getBorrowdate()))/86400000);        
float server=Float.valueOf( bt.getBookson().getBook().getNewprice())*borrowday;
		           
		           
		           
		           
		           //是来自团的订单
           boolean   groupbol=false;
           String groupcount=null;
           
           
           
                    //计算打折价
		           if(bt.getTypestatus()==1)
		           {
		        	   
		        groupbol=true;
		        groupcount=df.format((float) (server*0.8));
		        
		        	   
		           }
		           
		         
		           
		         /*  //获取用户是否是vip
		           if(bt.getUser().getGrade()==1)
		           {
		        	   
		        	   
		        	   vipbol=true;
		        	  vipcount=df.format((float) (Integer.parseInt(groupcount)*0.8));
		        	   
		        	   
		           }
		           */
		           
		           
		           /*
		    	    * id:借书id
		    	    * bookid:图书id
		    	    * booktitle:标题
		    	    * bookimages:图书封面
		    	    * author:作者
		    	    * publish:出版社
		    	    * borrowdate:借书时间
		    	    * deadlinedate:到期时间
		    	    * warn提示语
		    	    * fine:逾期产生费用
		    	    
		    	    * */
		           
		         
		     		str.append("{").append("\"id\":\""+bt.getId()+"\"")
					.append(",")
					.append("\"bookid\":\""+bt.getBookson().getBook().getBookid() +"\"")  
					.append(",")
					.append("\"booktitle\":\""+bt.getBookson().getBook().getBooktitle()+"\"")
					.append(",")
					.append("\"bookimages\":\""+bt.getBookson().getBook().getBookimages()+"\"")
					.append(",")
					.append("\"author\":\""+bt.getBookson().getBook().getAuthor()+"\"")
					.append(",")
					.append("\"publish\":\""+bt.getBookson().getBook().getPublish()+"\"")
					.append(",")
				   .append("\"borrowdate\":\""+bt.getBorrowdate().substring(0,10)+"\"")
				   .append(",")
				   .append("\"deadline\":\""+bt.getDeadline().substring(0,10)+"\"")
				    .append(",")
				   .append("\"warn\":\""+warn+"\"")
				   .append(",")
				   .append("\"fine\":\""+String.valueOf(fine)+"\"")
				   .append(",")
				   .append("\"booksonid\":\""+bt.getBookson().getBooksonid()+"\"")
				   .append(",")
				   .append("\"isbn\":\""+bt.getBookson().getBook().getPublishnumber()+"\"")
				   .append(",")
				   .append("\"price\":\""+bt.getBookson().getBook().getCash()+"\"")
				   .append(",")
				   .append("\"borrowday\":\""+borrowday+"\"")
				   .append(",")
				   .append("\"server\":\""+server+"\"")
				   .append(",")
				   .append("\"groupbol\":\""+groupbol+"\"")
				   .append(",")
				   .append("\"groupcount\":\""+groupcount+"\"")
				   .append(",")
				   .append("\"count\":\""+8+"\"")
				   .append(",")
				   .append("\"beyondbol\":\""+beyondbol+"\"")
				   .append(",")
				   .append("\"newprice\":\""+bt.getBookson().getBook().getNewprice()+"\"")
				   .append("}").append(",");
		    	   
	             
		         }
		        
		        
		    
		        
		        
		
		        

		      out.write("["+str.substring(0,str.length()-1)+"]");
		       }
		       else
		       {
		    	   
		    	   
		    	   out.write("610");
		       }
		
		
		
	}

}
