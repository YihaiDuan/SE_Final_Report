package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.OutPayDao;
import dao.BookDao;
import dao.BookSonDao;
import dao.BorrowTableDao;
import dao.ConfirmOrderDao;
import dao.OnlineWarnDao;
import dao.OrderLineDao;
import dao.UserDao;
import entity.Book;
import entity.BookSon;
import entity.BorrowTable;
import entity.ConfirmOrder;
import entity.OnlineOrder;
import entity.OnlineWarn;
import entity.OutPay;
import entity.User;
import util.Httppost;
import util.access_tokken;

import javax.servlet.annotation.WebServlet;



@WebServlet("/ScanReturnServlet")
public class ScanReturnServlet extends HttpServlet {

	
	public ScanReturnServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	    doPost(request,response);

	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
			{
		
		  response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      
		  PrintWriter out=response.getWriter() ;
		  //String adminname=request.getParameter("userId");
		  //保存此次的处理订单下的图书号
		  String borrowinf=request.getParameter("bookid");
		  System.out.println(borrowinf);
		  String count=request.getParameter("count");
	
        String id[]=borrowinf.split(",");
		  
		  for(int i=0;i<id.length;i++)
		  {
		
	      System.out.println("管理员点击了确认还书");
	  
     	  System.out.println("returnid="+id[i]);
	
		  }
		  
		  
		  BorrowTableDao bd=new BorrowTableDao();
		  BorrowTable bt=new BorrowTable();
		  
		  
		  Date date= new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//璁剧疆鏃堕棿鏄剧ず鏍煎紡
		  String returndate= sdf.format(date);
		  
		 
          SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//璁剧疆鏃堕棿鏄剧ず鏍煎紡
		  String date1= sdf1.format(date);
		  
		  System.out.println("iiiiiiiiiiii还书时间"+returndate);
		  
		  
		 String username=null;
		 
		 //为用户进行退款
		 //count>0说明要给用户退款
		 //count<0说明用户给平台支付逾期费用
		 if(Integer.parseInt(count)>0)
		 {
			 
			 BorrowTable btt=bd.getBorrowTablebyid(Integer.parseInt(id[0]));
			
			 
			 
			//退款给的用户
			   UserDao ud=new UserDao();
			   User u=ud.getUserbyid(btt.getUser().getUserid());
		    
		       
		      
		       
		       //退款金额
		       double cash=Double.valueOf(count);
		       
		       
		  
		       
		       
		       //退款时间
		       Date date2=new Date();
			   SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   String nowdate=sdf2.format(date2);
			   
			   
			   //退款描述
			   String fromdes="来自图书馆借书押金退款";
			   
			   
			   //更新用户的钱包
			      String oldmoney=u.getMoney();
			      Double newmoney=Double.parseDouble(oldmoney)+cash;
			      //更新用户钱包
			        u.setMoney(newmoney.toString());
			        ud.UpdatePersonMoney(u);
			   
			        
			        //添加退款记录
			   
			     OutPayDao opd=new OutPayDao();   
			     OutPay op=new OutPay(); 
			     op.setCash(cash);
			     op.setDate(nowdate);
			     op.setFromdes(fromdes);
			     op.setReadstatus(0);
			     op.setUser(u);

		          opd.addOutPay(op);
		          
			 
		 }
		 
		 
		  
		for (int i = 0; i < id.length; i++) 
		{

			System.out.println("还书的id为"+id[i]);

			BorrowTable btt=bd.getBorrowTablebyid(Integer.parseInt(id[i]));
			 
		
			 username=btt.getUser().getNickname();
			 
			
			//更新借书标志位
			bt.setId(Integer.parseInt(id[i]));
			bt.setStatus(1);
            bd.UpdateBorrowStatus(bt);
			
			
            
            //更新还书标志位
		    bt.setId(Integer.parseInt(id[i]));
			bt.setReturndate(returndate);
			bd.UpdateBorrowReturnDate(bt);
			
			
			
      BorrowTableDao btd=new BorrowTableDao();
			
	  BorrowTable bt1=btd.getBorrowTablebyid(Integer.parseInt(id[i]));
			
	 
	  //
	  
	    String booksonid=bt1.getBookson().getBooksonid();
	    
	  
		
		 //修改书的借书位位0
		BookSonDao bsd=new BookSonDao();
		
		BookSon bs=new BookSon();
		bs.setBooksonid(booksonid);
		bs.setBorrowstatus(0);
		bsd.UpdateBookBorrowStatus(bs);
		
		
		//修改预订标志位
//		bs.setOrderstatus(0);
//		bsd.UpdateBookOrderStatus(bs);
	    
	    
	   //进行有书提醒
		
		OnlineWarnDao ow=new OnlineWarnDao();
		
		
		
		
		
		List<OnlineWarn>  olist=ow.getOrderByidWarn(bt1.getBookson().getBook().getBookid());
		
		
		System.out.println("我还的是这本书------------"+bt1.getBookson().getBook().getBookid());
		
		if(olist!=null&&olist.size()>0)
		{
			
			System.out.println("有人设置了提醒");
			
		for(int j=0;j<olist.size();j++)
		{
			
			OnlineWarn  on=olist.get(j);
			
		    int id2=on.getId();
		    OnlineWarn  on1=new OnlineWarn();
		    
		    
		    //修改提醒的标志位
		    on1.setId(id2);
			on1.setStatus(1);
			ow.UpdateOnlinWarnStatus(on1);
			
			
			//获取各用户的openid进行提醒
			
			String openid=on.getUser().getOpenid();
			String formid=on.getFormid();
			
			 System.out.println(openid);
			 
			  String appid="wxb31c7fb23e5d11c2";
				String secret="132de5b5e208efa36294a2516728c477";
					
				String access_token=access_tokken.getAccesstoken(appid,secret);
				String template_id="n9XYD2bFHD_Edsv2ec8aCRylJCIXrdj26oaDufse3dE";
				
			  
				String booktitle=on.getBook().getBooktitle();
				String bookid2=on.getBook().getBookid();
				String typeid=on.getBook().getCategory().getCategoryid();
				
				/*
				 * 
				 * 书名
				 * 
				 * */
				//剩下数量
				String bookid=on.getBook().getBookid();
				
				List<BookSon>  blist=bsd.getNoBorrowBookSon(bookid);
				
				int booknum=blist.size();
				
				
				String data="\n{\n\"touser\": "+"\""+openid+"\""+", \n \"template_id\": "+"\""+template_id+"\""+",\n\"page\":"+"\""+"pages/particulars/particulars?bookid="+bookid2+"&advice="+typeid+"\""+",\n\"form_id\": "+"\""+formid+"\""+",\n\"data\": {\n\"keyword1\": {\n\"value\":" +"\""+booktitle+"\""+",\n\"color\": \"#173177\"\n},\n\"keyword2\": {\n\"value\":"+"\""+booknum+"\""+",\n \"color\": \"#173177\"\n}  }}";	
				System.out.println(data);
				String url="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+access_token;
				String success=Httppost.sendPost(url,data);
				System.out.println(success);
			
			
		}//for鍐呭眰for
		
		
		
	  }//if
		
		
	
    }//澶栧眰for
		  
	out.write("609");
		  
    }

	
	public void init() throws ServletException {
		
	}

}
