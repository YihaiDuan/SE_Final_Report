package timer;

import dao.BookDao;
import dao.BorrowTableDao;
import dao.UserDao;
import entity.Book;
import entity.BorrowTable;
import entity.User;
import util.Httppost;
import util.access_tokken;

public class TimerBorrowBookWarn   extends Thread
{
	
	
	private int time;
	private String booksonid;
	private String userid;
	private int borrowid;
	private String formid;
	private String openid;
	
	
	

	
	
	public TimerBorrowBookWarn(int time, String booksonid, String userid, int borrowid, String formid, String openid) {
		super();
		this.time = time;
		this.booksonid = booksonid;
		this.userid = userid;
		this.borrowid = borrowid;
		this.formid = formid;
		this.openid = openid;
	}






	public void run()
	{
		
	try {
		
	
		Thread.sleep(time);
		
		 System.out.println("线程启动");
		//鍒ゆ柇璇ョ敤鎴锋槸鍚﹁繘琛岃繕涔︽彁閱�
		
		
		UserDao ud=new UserDao();
		
		User u=ud.getUserbyid(userid);
		
		int warn=u.getBorrowwarnstatus();
		
		if(warn==1)
		{
			 System.out.println("用户设置了还书提醒");
			
			
	 //杩涜杩樹功鎻愰啋
		
	 //鏌ョ湅鐢ㄦ埛鏄惁宸茬粡鎶婅鍥句功褰掕繕
			
		  BorrowTableDao bto=new BorrowTableDao();
		  BorrowTable bt=bto.getBorrowTablebyid(borrowid);
		  
		if(bt!=null)
		{
		  
		  
		  int returnstatus=bt.getReturnstatus();
		
		  
		  if(returnstatus==0)
		  {
			  
			  System.out.println("进行还书提醒");
			  
			  
			  System.out.println("--------------------------"+openid);
			  
		String appid="wxb31c7fb23e5d11c2";
		String secret="132de5b5e208efa36294a2516728c477";
					
		String access_token=access_tokken.getAccesstoken(appid,secret);
		String template_id="9CPBqUlmWuYPiMasDfhPFF3Ose31O9IdCsll1-ckDBM";	
		
		
		/*
		 * keyword1涔﹀悕
		 * keyword2 鍊熼槄鏃堕棿
		 * keyword3搴旇繕鏃堕棿
		 * keyword4杩樹功鍦扮偣
		 * 
		 * 
		 * */
		
		String booktitle=bt.getBookson().getBook().getBooktitle();
		String borrowdate=bt.getBorrowdate();
		String deadline=bt.getDeadline();
	    String urlpath="pages/operation/operation";
		
		//gbk  String data="\n{\n\"touser\": "+"\""+openid+"\""+", \n \"template_id\": "+"\""+template_id+"\""+",\n\"page\":"+"\""+urlpath+"\""+",\n\"form_id\": "+"\""+formid+"\""+",\n\"data\": {\n\"keyword1\": {\n\"value\":"+"\""+booktitle+"\""+",\n\"color\": \"#173177\"\n},\n\"keyword2\": {\n\"value\":"+"\""+borrowdate+"\""+",\n \"color\": \"#173177\"\n},\n\"keyword3\": {\n \"value\": "+"\""+deadline+"\""+",\n\"color\": \"#173177\"\n},\n\"keyword4\": {\n\"value\": \"逸夫图书馆\","+"\"color\": \"#173177\" \n}\n},}";
		
	    String data="\n{\n\"touser\": "+"\""+openid+"\""+", \n \"template_id\": "+"\""+template_id+"\""+",\n\"page\":"+"\""+urlpath+"\""+",\n\"form_id\": "+"\""+formid+"\""+",\n\"data\": {\n\"keyword1\": {\n\"value\":"+"\""+booktitle+"\""+",\n\"color\": \"#173177\"\n},\n\"keyword2\": {\n\"value\":"+"\""+borrowdate+"\""+",\n \"color\": \"#173177\"\n},\n\"keyword3\": {\n \"value\": "+"\""+deadline+"\""+",\n\"color\": \"#173177\"\n},\n\"keyword4\": {\n\"value\": \"逸夫图书馆\","+"\"color\": \"#173177\" \n}\n}}";	    
	    System.out.println(data);
		String url="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+access_token;
		String success=Httppost.sendPost(url,data);
		System.out.println(success);
			
			
		  }
		  else
		  {
			  
			  System.out.println("涔︽湰宸茬粡褰掕繕涓嶇敤鎻愰啋");
		  }
		  
		}
		else
		{
			
			//涓嶈繘琛岃繕涔︽彁閱�
			System.out.println("鐢ㄦ埛鍙栨秷浜嗚繕涔︽彁閱�");
		
			
		}
			
		}//if鍒ゆ柇璇ュ�熶功鏄惁杩樺瓨鍦�
	
		}
		catch (Exception e) 
		{
			
			System.out.println("绾跨▼閿欒"+e);
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	

}
