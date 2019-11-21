
package PublicJson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.GroupBookDao;
import Extenddao2.GroupMainDao;
import dao.BookSonDao;
import dao.BorrowTableDao;
import dao.CollectDao;
import dao.OnlineWarnDao;
import dao.OrderLineDao;
import entity.Book;
import entity.BorrowTable;
import entity.ComparePrice;
import entity.GroupBook;
import util.JsonFormat;


public class BookDetailJson {
	
	
public  String  BookDetailJson(HttpServletRequest request,HttpServletResponse response,Book b,String userid)
{
	    	 
	    	 PrintWriter out=null;
	    	 
	    	 
	    
	    	 
	    	 
	    	 
			try {
				
				out = response.getWriter();
				
			}
			catch (IOException e)
			{
				
				e.printStackTrace();
			}
			
	    	 StringBuilder str=new StringBuilder();
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 

      	     String list=null;
	         String guidreading=null;
	         String review=null;
	         String introduce=null;
	        String list3[]=null;
	        StringBuilder strlist=new StringBuilder();
	         
	         if(b.getList()!=null)
	         {
	        	 list=JsonFormat.stringToJson(b.getList()).replaceAll("@+","@");
	        	list3=list.split("@");
	        	
	        	if(list3.length>=3)
	        	{
	        		
	        		
	        		
	        		for(int i=0;i<3;i++)
	        		{
	        			 strlist.append("{").append("\"list\":\""+list3[i]+"\"")
		        		 
		      	         .append("},");
	        		}
	        		
	        	
	        	}
	        	else
	        	{
	        		for(int i=0;i<list3.length;i++)
	        		{
	        			
                         strlist.append("{").append("\"list\":\""+list3[i]+"\"")
		        		 
		      	         .append("},");
	        			
	        			
	        		}
	        		
	        		
	        	}
	        	
	        	
	        	
	        
	         }
	         else
	         {
	        		list="没有相关内容";
	        	 
	         }
	         
	         
	        	 
	         if(b.getGuidreading()!=null)
	         {
	        	 guidreading=JsonFormat.stringToJson(b.getGuidreading());
	        	
	         }
	         else
	         {
	        	 guidreading="没有相关内容";
	        	 
	         }
	         
	         
	         
	         if(b.getReview()!=null)
	         {
	        	 review=JsonFormat.stringToJson(b.getReview());
	        	
	         }
	         else
	         {
	        	 review="没有相关内容";
	        	 
	         }
	         
	         
	         
	         if(b.getIntroduce()!=null)
	         {
	        	 
	        	
	        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
	         }
	         else
	         {
	        	 introduce="没有相关内容";
	        	 
	         }	 
	         
	         //是否已经借了两本
	        boolean outborrow=false;
	       
	        BorrowTableDao btd=new BorrowTableDao();
	         if(btd.getBorrowLanNum(userid)>=2)
	         {
	        	 
	        	 outborrow=true;
	        	 
	        	 
	         }
      	 
	         
	         //是否已经预订两本
		        boolean outorder=false;
		       
		        OrderLineDao ood=new OrderLineDao ();
		         if(ood.getOnlineOrderNum(userid)>=2)
		         {
		        	 
		        	 outorder=true;
		        	 
		        	 
		         }
      	 
      	 
	         
	        //是否已经收藏
           CollectDao cod=new CollectDao();
           boolean collectbol=cod.CollectBol(userid,b.getBookid());
      
           //获取藏数量
           Object totalnum=0;
           BookSonDao bs=new BookSonDao();
      
          totalnum=bs.getTotalNumByBookid(b.getBookid());
      
      
      //当前可借数量
      Object borrownum=0;
      borrownum=bs.getBookSnoBorrowStatusByBookid(b.getBookid());
      
      //是否提醒
      OnlineWarnDao old=new OnlineWarnDao();
      boolean warnboolean=old.OrderWarn(userid, b.getBookid());
      
      
      
       //是否降价
//       String oldprice =b.getOldprice();
       String newprice=b.getNewprice();
       boolean cutbol=false;
       
//       if(!oldprice.equals(newprice))
//       {
//    	   
//    	   cutbol=true;
//    	   
//       }
       
       
       //是否在开团列表
       GroupBookDao gbd=new GroupBookDao();
       boolean groupbol=gbd.BolBookinGroup(b.getBookid());
       
         String discount=null;
		 String num=null;
		 String createdate=null;
		 String deadline=null;
		 String groupnum="0";
		 
	    boolean opengroupbol=false;
				 
		  if(groupbol)
	       {
	    	   
	    	GroupBook gb=gbd.getGroupBookbyBookid(b.getBookid());
	    	num=gb.getGroupmore().getGroupnum();
	    	createdate=gb.getGroupmore().getCreatedate();
	    	deadline=gb.getGroupmore().getDeadline();
	    	groupnum=gb.getGroupmore().getGroupnum();
	    	   
	    	if(num.equals("2"))
			{
				discount="9";
				
			}
			else  if(num.equals("3"))
			{
				discount="8";
			}
			else
			{
				
				discount="7";
				
			}
		  
		  //判断是否该用户已经开过这个团
		GroupMainDao gmd=new GroupMainDao();
		boolean  bol1=gmd.UserOpenMainGroupBol(userid,b.getBookid());
		boolean  bol2=gmd.UserOpenMemberGroupBol(userid, b.getBookid());
		
	
		  
		  if(bol1||bol2)
		  {
			  
			 opengroupbol=true;
			  
		  }   
	       }
		  
       
			
		  
             str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
	        .append(",")
			.append("\"author\":\""+b.getAuthor() +"\"")
			
			.append(",")
			.append("\"bookimages\":\""+b.getBookimages() +"\"")
			
			.append(",")
			.append("\"booktitle\":\""+b.getBooktitle()+"\"")
			
			.append(",")
			.append("\"introduce\":\""+introduce+"\"")
			
			.append(",")
			.append("\"publish\":\""+b.getPublish()+"\"")
			
			.append(",")
			.append("\"publishnumber\":\""+b.getPublishnumber()+"\"")
			
		    .append(",")
		    .append("\"totalnum\":\""+totalnum+"\"")
			
			.append(",")
			.append("\"booknum\":\""+b.getBooknum()+"\"")
          .append(",")
			.append("\"review\":\""+review+"\"")
			
			.append(",")
			.append("\"list\":\""+list+"\"")
			
			.append(",")
			.append("\"guidreading\":\""+guidreading+"\"")
			
			
			.append(",")
			.append("\"collectbol\":\""+collectbol+"\"")
			
			.append(",")
			.append("\"borrownum\":\""+borrownum+"\"")
			
			.append(",")
			.append("\"warnboolean\":\""+warnboolean+"\"")
			.append(",")
			.append("\"cash\":\""+b.getCash()+"\"")
			.append(",")
			.append("\"typename\":\""+b.getCategory().getName()+"\"")
			.append(",")
			.append("\"position\":\""+b.getPosition()+"\"")
			.append(",")
			.append("\"oldprice\":\""+b.getOldprice()+"\"")
			.append(",")
			.append("\"newprice\":\""+b.getNewprice()+"\"")
			.append(",")
			.append("\"cutbol\":\""+cutbol+"\"")
			.append(",")
			.append("\"groupbol\":\""+groupbol+"\"")
			.append(",")
			.append("\"discount\":\""+discount+"\"")
			.append(",")
			.append("\"deadline\":\""+deadline+"\"")
			.append(",")
			.append("\"createdate\":\""+createdate+"\"")
			.append(",")
			.append("\"opengroupbol\":\""+opengroupbol+"\"")
			.append(",")
			.append("\"groupnum\":\""+groupnum+"\"")
			.append(",")
			.append("\"getborrownum\":\""+b.getBorrownum()+"\"")
			.append(",")
			.append("\"elestatus\":\""+b.getElestatus()+"\"")
			.append(",")
			.append("\"eleprice\":\""+b.getEleprice()+"\"")
			.append(",")
			.append("\"freestatus\":\""+b.getFreestatus()+"\"")
			.append(",")
			.append("\"score\":\""+b.getEvaluatescore()+"\"")
			.append(",")
			.append("\"strlist\":"+"["+strlist.substring(0,strlist.length()-1)+"]")
			.append(",")
			.append("\"outorder\":\""+outorder+"\"")
			.append(",")
			.append("\"outborrow\":\""+outborrow+"\"")
			.append("}");
	        
		  
	      	return str.toString();
	    	
	    	
	    	
	    	
	    }
	   
	
	
	
	
	
	
}
