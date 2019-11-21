/**
 * 
 */
package MyTotalUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

/**
 * @author Administrator
 * @date Aug 23, 2017
 * @todoTODO
 */
public class DataDao {
	
	//获取每月借书量
	
	public int getMothBorrow(String yearmonth,String userid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Object num=null;
num=session.createQuery("select count(b.user.userid) "
		+ " from BorrowTable as b where borrowdate like ? and scanstatus=1  and b.user.userid=?").
setParameter(0,yearmonth+"%").
setParameter(1,userid)
.uniqueResult();
		

tx.commit();
HibernateUtil.closeSession(session);


		if(num!=null)
		{
			return Integer.parseInt(num.toString());
		}
		else
		{
			
			return 0;
		}
		
	}
	
	
	//统计各个类型的阅读量
	public int  getReadByTypeNum(String cateid,String userid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Object num=null;
num=session.createQuery("select count(typename) "
+ " from ReadHistroy as rh   where typename=? and rh.user.userid=?").
setParameter(0,cateid).
setParameter(1,userid)
.uniqueResult();
		

tx.commit();
HibernateUtil.closeSession(session);
		
if(num!=null)
{
	return Integer.parseInt(num.toString());
}
else
{
	
	return 0;
}
		
}
	
	
	
	//获取用户的读书记录
	public int getUserBorrowNum(String userid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Object num=null;
num=session.createQuery("select count(b.user.userid) "
		+ " from BorrowTable as b where  scanstatus=1  and b.user.userid=?").setParameter(0,userid)
.uniqueResult();
		

tx.commit();
HibernateUtil.closeSession(session);


		if(num!=null)
		{
			return Integer.parseInt(num.toString());
		}
		else
		{
			
			return 0;
		}
		
		
	}
	
	//获取用户的借书记录
	public int getUserReadNum(String userid)
	{
		

		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Object num=null;
num=session.createQuery("select count(rh.user.userid) "
+ " from ReadHistroy as rh   where rh.user.userid=?").
setParameter(0,userid)
.uniqueResult();
		

tx.commit();
HibernateUtil.closeSession(session);
		
if(num!=null)
{
	return Integer.parseInt(num.toString());
}
else
{
	
	return 0;
}
		
		
		
		
		
	}
	
	public static void main(String [] args)
	{
		
		  Date date=new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("MM");//设置时间显示格式
		  String curmonth= sdf.format(date);
		 List data=new ArrayList();
		//2017/08/21 02:47:47
		 int num=0;
		  List d=new ArrayList();
		  for(int i=1;i<=Integer.parseInt(curmonth);i++)
		  {
			  
			 
			  String time=2017+"/"+"0"+String.valueOf(i);
			  
			  DataDao dd=new DataDao();
			  num=dd.getMothBorrow(time,"123");
		      d.add(num);
		      
		  }
		  
		  StringBuilder str=new StringBuilder();
		  for(int i=0;i<d.size();i++)
		  {
			  
			  str.append(d.get(i)).append(",");
			  
			  
		  }
		  
	    System.out.println(str);
		
		
	}
	
}
