
package PublicJson;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EleBookDao.EleBookDao;
import Extenddao.GroupBookDao;
import Extenddao2.GroupMainDao;
import dao.BookSonDao;
import dao.CollectDao;
import dao.OnlineWarnDao;
import entity.Book;
import entity.GroupBook;
import util.JsonFormat;

public class EleBookDetailJson {
	
	
	public  String  EleBookDetailJson(HttpServletRequest request,HttpServletResponse response,Book b,String userid)
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
		         
		         if(b.getList()!=null)
		         {
		        	 list=JsonFormat.stringToJson(b.getList());
		        
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
	      	 
	      	 
	      	 
		         
		        //是否已经收藏
	           CollectDao cod=new CollectDao();
	           boolean collectbol=cod.CollectBol(userid,b.getBookid());
	      
	           //获取藏数量
	           Object totalnum=0;
	           BookSonDao bs=new BookSonDao();
	      
	          totalnum=bs.getTotalNumByBookid(b.getBookid());
	      
	      
	             //是否免费  显示免费阅读
	          
	          
	          
	       //是否是vip免费   显示vip免费读
	     
	               
	         //判断是否已经购买   已经购买可以直接阅读
	      
	          
	       boolean readbol=false;
	       EleBookDao ebd=new EleBookDao();
	       
	       if(ebd.BolOwnEleBook(userid,b.getBookid()))
	       {
	    	   
	    	   
	    	   readbol=true;
	       }
				
			  //判断是否已经加入到书架
	       boolean myelebol=false;
	   
	       
	        if(ebd.BolMyEleBook(userid,b.getBookid()))
	        {
	        	
	        	myelebol=true;
	        	
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
				.append("\"getborrownum\":\""+b.getBorrownum()+"\"")
				.append(",")
				.append("\"elestatus\":\""+b.getElestatus()+"\"")
				.append(",")
				.append("\"eleprice\":\""+b.getEleprice()+"\"")
				.append(",")
				.append("\"freestatus\":\""+b.getFreestatus()+"\"")
				.append(",")
				.append("\"vipfreestatus\":\""+b.getVipfreestatus()+"\"")
				.append(",")
				.append("\"readbol\":\""+readbol+"\"")
				.append(",")
				.append("\"myelebol\":\""+myelebol+"\"")
				.append(",")
				.append("\"score\":\""+b.getEvaluatescore()+"\"")
				.append(",")
				.append("\"typeid\":\""+b.getCategory().getCategoryid()+"\"")
				.append("}");
		        
			  
		      	return str.toString();
		    	
		    	
		    	
		    	
		    }
		   
		
		
		
	
	
	
}
