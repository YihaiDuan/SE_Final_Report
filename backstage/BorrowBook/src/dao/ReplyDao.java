package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entity.Reply;


/**
 * 
 * @author hjm
 * @todo 评论回复数据访问层
 */
public class ReplyDao 
{
	
	//添加回复
	
	public  void addReply(Reply r)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		session.save(r);
		tx.commit();
		  HibernateUtil.closeSession(session);
		
		
	}
	
	//根据用户查看回复情况
	public List<Reply>   getReplyEachByreplyid(String otherid)
	{
		
		
		  Session session=HibernateUtil.getSession();
			 Transaction tx=session.beginTransaction();
				  
	   List<Reply>  
	   
	   list=session.createQuery("from Reply where otherid=?").
setParameter(0,otherid).list();
	   
	  
			
  tx.commit();
  HibernateUtil.closeSession(session);
			 
	         return list;
	
		
	}
	
	
	
	
	   public List<Reply>   getRelybyuserid(String userid)
	   {
		   
		   Session session=HibernateUtil.getSession();
			 Transaction tx=session.beginTransaction();
				  
	   List<Reply>  
	   
	   list=session.createQuery("select r  from Reply  as r inner join r.comment as c  with  c.userid=?").
setParameter(0,userid).list();
	   
	  
			
     tx.commit();
     HibernateUtil.closeSession(session);
			 
	         return list;
		   
	
	   }
	   
	   
	
	   
	   //根据评论id获得用户的回复
	   public List<Reply>   getRelybycommentid(int commentid)
	   {
		   
		   Session session=HibernateUtil.getSession();
			 Transaction tx=session.beginTransaction();
				  
	   List<Reply>  
	   
	   list=session.createQuery("from Reply  as r where r.comment.commentid=? ").
setParameter(0,commentid).list();
	   
	  
			
     tx.commit();
     HibernateUtil.closeSession(session);
			 
	         return list;
		   
	
	   }
	
	   public static void main(String[] args) {
		   
		   ReplyDao rd=new ReplyDao();
		
		   
		  List<Reply> list = rd.getRelybyuserid("123");
		
		/*
		  List<Object> olist = null;
		  Object c=null;
		  Reply r=null;
		  list.add((Reply);
		  olist.add(r);
			  
		  */
		
	}
	
	
	
	
	
	
	
	

}
