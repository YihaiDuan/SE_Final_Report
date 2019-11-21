
package Extenddao2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import entity.*;
import util.HibernateUtil;

public class DynamicAdmireDao 
{
	
	
	
	//查看动态是否被点赞
	public boolean  DynamicAdmire(int dynamicid,String userid)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
List<DynamicAdmire>  dalist=session.
createQuery("from DynamicAdmire as da where da.user.userid=? and da.dynamic.dynamicid=?").
setParameter(0,userid).setParameter(1,dynamicid).list();
		
		

         tx.commit();
         HibernateUtil.closeSession(session);
         

		if(dalist!=null&&dalist.size()>0)
		{
			
			return true;
			
		}
		else
		{
			
			return false;
		}
	}
	
	
	//查看评论是否被点赞
		public boolean  CommentAdmire(int commentid,String userid)
		{
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			
	List<DynamicAdmire>  dalist=session.
	createQuery("from CommentAdmire as da where da.user.userid=? and da.comment.commentid=?").
	setParameter(0,userid).setParameter(1,commentid).list();
			
			

	         tx.commit();
	         HibernateUtil.closeSession(session);
	         

			if(dalist!=null&&dalist.size()>0)
			{
				
				return true;
				
			}
			else
			{
				
				return false;
			}
		}
	
	
	public  static void main(String [] args)
	{
		
		
		DynamicAdmireDao dad=new DynamicAdmireDao();
		boolean a=dad.CommentAdmire(1,"o50AL0U9sAMhbI3ZrqfTF7nmWduo");
		System.out.println(a);
		
		
	}
	
	
}
