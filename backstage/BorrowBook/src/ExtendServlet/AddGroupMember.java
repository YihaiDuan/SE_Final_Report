package ExtendServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao.GroupDao;
import Extenddao2.GroupMainDao;
import dao.BookSonDao;
import dao.BorrowTableDao;
import dao.UserDao;
import entity.BookSon;
import entity.BorrowTable;
import entity.GroupMain;
import entity.GroupMember;
import entity.GroupMore;
import entity.User;


@WebServlet("/AddGroupMember")
public class AddGroupMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddGroupMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		        doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		 response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   
		   PrintWriter out=response.getWriter();
		   
		   
		   Date date=new Date();
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   String nowdate = sdf.format(date);
		   
		   
		   
		   System.out.println("支付成功");
		   
		   String userid=request.getParameter("userid");
		   String bookid=request.getParameter("bookid");
		   String cash=request.getParameter("cash");
		   
		   
		   String groupmoreid=request.getParameter("groupmoreid");
		   String groupmainid=request.getParameter("groupmainid");
		   
		   
		   GroupDao gmd=new GroupDao();
		   GroupMore gm=gmd.getGroupMoreById(Integer.parseInt( groupmoreid));
		   
		   
		   
		   
		   //扣除支付金额
			  UserDao ud2=new UserDao();
			  User u2=ud2.getUserbyid(userid);
			  
			  String oldmoney=u2.getMoney();
			  Double newmoney=Double.valueOf(oldmoney)-Double.valueOf(cash);
			  
			  //更新钱包
			    u2.setUserid(userid);
			    u2.setMoney(String.valueOf(newmoney));
			    ud2.UpdatePersonMoney(u2);
		   
		   
          //根据书目录id获取下面的所有可借书籍
           
           BookSonDao bsd=new BookSonDao();
	       BookSon bs=bsd.getNoBorrowNoOrderBookSon(bookid);
	       
	       String booksonid=null;
	       
	       if(bs!=null)
	       {
	    	   
	    	 booksonid=bs.getBooksonid();
	     
	      
		   
	       System.out.println(booksonid);
	       
	       
	       //更新图书被借标志位
	       BookSon bs1=bsd.getBookSon(booksonid);
	       bs1.setBorrowstatus(1);
	       bsd.UpdateBookBorrowStatus(bs1);
		   
	       }
		   //最大组团数
		    String groupnum=gm.getGroupnum();
		   
		    
		   
		         
		   System.out.println("最大组团数"+groupnum);
		   
		   
		   //添加到成员中
		   GroupMainDao gmad=new GroupMainDao();
		   GroupMember gmb=new GroupMember();
		   
		   GroupMain gma=gmad.getGroupMainByid(Integer.parseInt(groupmainid));
		   
		   
		   gmb.setGroupmain(gma);
		   gmb.setBookid(bookid);
		   gmb.setUserid(userid);
		   gmb.setPaymoney(cash);
		   gmb.setDate(nowdate);
		   gmb.setBooksonid(booksonid);
		   
		   
		   gmad.SaveGroupMember(gmb);
		   
		   
		 
		   
		   
		   //当前成员数
		 
		   int membernum=Integer.parseInt(gmad.getNumfromGroupMember(Integer.parseInt(groupmainid)).toString());
		   System.out.println("当前成员数"+membernum);
		   
		   
		   //团已经满了可以成立，同时修改团的标志位为1，将这些书添加到借书表中      同时这个借书单的类型是拼多多类型
		   if((membernum+1)==Integer.parseInt(groupnum))
		   {
			   
			   //修改团满标志位为1
			   //status=2表示该团完成
			  gma.setStatus(1);
			  gmad.UpdateGroupmainFull(gma);
			   
			  System.out.println("修改成功");
			  
			  String createdate=null;
			  createdate = sdf.format(date);
			  
			  
			  //生成到期时间
			    Calendar rightNow = Calendar.getInstance();
			    rightNow.setTime(date);
		        rightNow.add(Calendar.MONTH,1);
			    Date dt1=rightNow.getTime();
			    String deadline = sdf.format(dt1);
			    System.out.println("到期时间:"+deadline);
			  
			   
			  String mainuserid=gma.getUserid();
			  String mainbooksonid=gma.getBooksonid();
			  String mainpaymemoney=gma.getPaymoney();

			   BorrowTableDao  btd=new BorrowTableDao(); 
			   BorrowTable bt=new BorrowTable();
			 
			   
			   
			   bt.setDeadline(deadline);
			   bt.setBorrowdate(createdate);
			   bt.setTypestatus(1);
			   bt.setPaymoney(mainpaymemoney);
			   
			   
			    BookSonDao bd=new BookSonDao();
			    BookSon b=bd.getBookSon(mainbooksonid);
			    bt.setBookson(b);
			    
			    
			    UserDao ud=new UserDao();
			    User u=ud.getUserbyid(mainuserid);
			    bt.setUser(u);
			  
			    
			    
			   //添加团长到借书单
			    btd.SaveBorrowTableData(bt);
			  
			   
			   //添加成员到借书单
			List<GroupMember>  gmlist=gmad.getAllMemberbyid(Integer.parseInt(groupmainid));
			  
			  if(gmlist!=null&&gmlist.size()>0)
			  {
				 
				 System.out.println("添加成员到借书单");
				  for(int i=0;i<gmlist.size();i++)
				  {
				String memberuserid=null;
			    String memberbooksonid=null;
				String memberpaymoney=null;
			    
			     GroupMember gmem=gmlist.get(i);
				 memberuserid=gmem.getUserid();
				 memberbooksonid=gmem.getBooksonid();
				 memberpaymoney=gmem.getPaymoney();
				 
				   BorrowTableDao  btd1=new BorrowTableDao(); 
				   BorrowTable bt1=new BorrowTable();
				 
				   
				   
				   bt1.setDeadline(deadline);
				   bt1.setBorrowdate(createdate);
				   bt1.setTypestatus(1);
				   bt1.setPaymoney(memberpaymoney);
				   
				   
				    BookSonDao bd1=new BookSonDao();
				    BookSon b1=bd1.getBookSon(memberbooksonid);
				    bt1.setBookson(b1);
				    
				    
				    UserDao ud1=new UserDao();
				    User u1=ud1.getUserbyid(memberuserid);
				    bt1.setUser(u1);
				  
				    
				    
				   //添加到借书单
				    btd1.SaveBorrowTableData(bt1);
				
				}
				
			  }
			  
			  
			  
			  
			   
		   }
		   
		   
	}

}
