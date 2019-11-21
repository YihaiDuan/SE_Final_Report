package ReadStatusUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.Comment;
import entity.Dynamic;
import entity.DynamicComment;
import entity.DynamicReply;
import entity.Reply;
import util.HibernateUtil;

public class ReadStatusDao 
{
	
	//获取评价是否读过
	public List<Reply> bolReviewReply(String userid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
	List<Reply> list=session.createQuery("from Reply  as r where otherid=?  and readstatus=0").setParameter(0,userid).list();
		
		tx.commit();
	  HibernateUtil.closeSession(session);
	   
	if(list!=null&&list.size()>0)
	{
		
		return list;
	}
	else
	{
		
		return null;
		
	}
		
		
	}
	
	public void UpdateReviewReplyStatus(Reply re)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Reply re1=(Reply) session.get(Reply.class,re.getReplyid());
		
		re1.setReadstatus(1);
		session.update(re1);
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
	}
	
	
	
	
	
	//获取动态评论是否读过
	public List<Dynamic> bolDynamicComment(String userid)
	{
		

		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
	List<Dynamic> list=session.createQuery("from Dynamic as d left join fetch d.dynamiccomment as dc where d.user.userid=?  and dc.readstatus=0").setParameter(0,userid).list();
		
		tx.commit();
	  HibernateUtil.closeSession(session);
	   
	if(list!=null&&list.size()>0)
	{
		
		return list;
	}
	else
	{
		
		return null;
		
	}
		
	}
	
	
	
	public void UpdateDynamicCommentStatus(DynamicComment dc)
	{
		
		System.out.println("我来更新标着为");
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
DynamicComment re1=

(DynamicComment) session.get(DynamicComment.class,dc.getCommentid());
		
		re1.setReadstatus(1);
		session.update(re1);
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
	}
	
	
	

	
	
	//获取动态回复是否读过
	public List<DynamicReply> bolDynamicReply(String userid)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
	List<DynamicReply> list=session.createQuery("from DynamicReply as dr where otherid=?  and  readstatus=0").setParameter(0,userid).list();
		
		tx.commit();
	  HibernateUtil.closeSession(session);
	   
	if(list!=null&&list.size()>0)
	{
		
		return list;
	}
	else
	{
		
		return null;
		
	}
		
		
	}
	
	
	public void UpdateDynamicReplyStatus(DynamicReply dr)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		DynamicReply re1=(DynamicReply) session.get(DynamicReply.class,dr.getReplyid());
		
		re1.setReadstatus(1);
		session.update(re1);
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
	}
	

	/*//获取通知是否读过
	public boolean bolNotice()
	{
		
		
		
		
	}*/

	
	public static void main(String [] args)
	{
		
		
		ReadStatusDao rsd=new ReadStatusDao();
		
			/*List<Dynamic> a=rsd.bolDynamicComment("o50AL0U9sAMhbI3ZrqfTF7nmWduo"); 
		if(a!=null)
		System.out.println(a.size());*/

	List<DynamicReply> a=rsd.bolDynamicReply("o50AL0U9sAMhbI3ZrqfTF7nmWduo"); 
		if(a!=null)
			System.out.println(a.size());
	}
}
