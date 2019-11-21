/**
 * 
 */
package Extenddao2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.UserDao;
import entity.CommentAdmire;
import entity.Dynamic;
import entity.DynamicAdmire;
import entity.DynamicComment;
import entity.DynamicReply;
import entity.User;
import util.HibernateUtil;

public class DynamicCommentDao {
	
	
	
	public void SaveDynamicComment(DynamicComment  d)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(d);
	
		tx.commit();
		HibernateUtil.closeSession(session);
		

     }
	
	
	public void SaveDynamicReply(DynamicReply  d)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(d);
	
		tx.commit();
		HibernateUtil.closeSession(session);
		
     }
	
	//获取最新评论
	public List<DynamicComment>  getCommentByid(int dynamicid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
List<DynamicComment>  dlist=session.createQuery("from DynamicComment as d where d.dynamic.dynamicid=?").setParameter(0,dynamicid).list();
		

        tx.commit();
        HibernateUtil.closeSession(session);
        
		if(dlist!=null&&dlist.size()>0)
		{
			
			return dlist;
			
		}
		else
		{
			return null;
			
		}
	}
	
	
	
	//获取精彩评论
	public List<DynamicComment>  getNBCommentByid(int dynamicid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
List<DynamicComment>  dlist=session.createQuery("from DynamicComment as d where d.dynamic.dynamicid=?  and  d.admirenum>10  order by admirenum desc").setParameter(0,dynamicid).setFirstResult(0).setMaxResults(5).list();
		

        tx.commit();
        HibernateUtil.closeSession(session);
        
		if(dlist!=null&&dlist.size()>0)
		{
			
			return dlist;
			
		}
		else
		{
			return null;
			
		}
	}
	
	
	
	
	public DynamicComment getCommentDetailByid2(int id)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
DynamicComment  dc=(DynamicComment) session.load(DynamicComment.class,id);
		

        tx.commit();
        HibernateUtil.closeSession(session);
        
		if(dc!=null)
		{
			
			return dc;
			
		}
		else
		{
			return null;
			
		}
	}
	
	
	
	
	public DynamicComment getCommentDetailByid(int id)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
DynamicComment  dc=(DynamicComment) session.get(DynamicComment.class,id);
		

        tx.commit();
        HibernateUtil.closeSession(session);
        
		if(dc!=null)
		{
			
			return dc;
			
		}
		else
		{
			return null;
			
		}
	}
	
	
public List<DynamicReply>  getReplyByid(int commentid)
{
		
	    Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
List<DynamicReply>  dlist=session.createQuery("from DynamicReply as d where d.comment.commentid=?").setParameter(0,commentid).setFirstResult(0).
setMaxResults(4).list();
		

        tx.commit();
        HibernateUtil.closeSession(session);
        
		if(dlist!=null&&dlist.size()>0)
		{
			return dlist;
		}
		else
		{
			return null;
			
		}
}
	
	

//获取具体回复

public DynamicReply  getReplyDetailByid(int replyid)
{
		
	    Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
DynamicReply  r=(DynamicReply) session.get(DynamicReply.class, replyid);

		

        tx.commit();
        HibernateUtil.closeSession(session);
        
		if(r!=null)
		{
			return r;
		}
		else
		{
			return null;
			
		}
}
	
	

//添加动态的赞
	public void SaveCommentAdmire(CommentAdmire  dc)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(dc);
	
		tx.commit();
		HibernateUtil.closeSession(session);
		
   }
	
	//撤销动态的赞
	public  void DeleteCommentDynamicAdmire(String userid,int commentid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		CommentAdmire	admire=null;
		
       admire=(CommentAdmire)session.createQuery("from CommentAdmire as ca where  ca.user.userid=? and ca.comment.commentid=?").
					setParameter(0,userid).setParameter(1, commentid).uniqueResult();	
		
		if(admire!=null)
		{
			
		  session.delete(admire);
	
		}
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		
	}
	
	
	
	
	
	
	public static void main(String [] args)
	{
		DynamicCommentDao dcd=new DynamicCommentDao();
		DynamicComment dc=new DynamicComment();
		  UserDao ud=new UserDao();
		  User u=ud.getUserbyid("o50AL0U9sAMhbI3ZrqfTF7nmWduo");
		  dc.setUser(u);
		  
		  
		  DynamicDao dd=new DynamicDao();
		  Dynamic d=dd.getDynamicByid(Integer.parseInt("2"));
		  dc.setDynamic(d);
		
		   dcd.SaveDynamicComment(dc);
		
		
	}
	
	
}
