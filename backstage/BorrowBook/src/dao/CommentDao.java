package dao;

import java.text.DecimalFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import entity.Comment;
import entity.DynamicComment;
import util.HibernateUtil;


/**
 * 
 * @author hjm
 * @todo 图书分页显示赞数据访问层
 */
public class CommentDao
{
	

	
	public  void addComment(Comment c)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		session.save(c);
		tx.commit();
		  HibernateUtil.closeSession(session);
		
		
	}
	
	//根据评论id获取评论
	public Comment getCommentByid(int commentid)
	{

		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 Comment  c=null;
		 
		 c=(Comment) session.get(Comment.class,commentid);
		 
		 tx.commit();
		  HibernateUtil.closeSession(session);
		 
		
		  return c;
		
		
	}
	
	
	public List<Comment>   getComentbyBookid(String bookid)
	{
		
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
			  
   List<Comment>  list=session.createQuery("from Comment as c where c.book.bookid =?").
						setParameter(0,bookid).list();
		
         tx.commit();
         HibernateUtil.closeSession(session);
		 
         return list;
		
	}
	
	
	

	public List<Comment>   getCommentbyuserid(String userid)
	{
		
		

		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
			  
  List<Comment>  list=session.createQuery("from Comment where userid =?").
						setParameter(0,userid).list();
		
        tx.commit();
        HibernateUtil.closeSession(session);
		 
        return list;
		
		
		
		
		
	}
	
	
	
	

	//根据评论id获取点赞数
	public   Object getAdmirenum(int id)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Object admirenum=null;
		
	  Object c=(Object) session.createQuery("select count(a.comment.commentid) from Admire as a   where a.comment.commentid=?").
				setParameter(0,id).uniqueResult();
		
	    tx.commit();
	    HibernateUtil.closeSession(session);
	    
	    if(c!=null)
	    {
	    return c;

	    }
	    else
	    {
	    	
	    	return 0;
	    }
		
	}
	
	

	
	//获取评论总数
		public Object getCommentTotal(String bookid)
		{
			
			 Session session=HibernateUtil.getSession();
			 Transaction tx=session.beginTransaction();
			 
			 
				Object  commentnum=null;
				
				commentnum=(Object) session.createQuery("select count(c.book.bookid) from Comment as c   where c.book.bookid=?").setParameter(0,bookid)
						.uniqueResult();
			 

				    tx.commit();
				    HibernateUtil.closeSession(session);
				
				    if(commentnum!=null)
				    {
				    
			       return commentnum;
				    }
				    else
				    {
				    	
				    	return 0;
				    }
			
		
		}
		
		
		
		//获取该评论的回复总数
		public Object getReplynum(String bookid)
		{
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
		Object replynum=null;
			
		replynum=(Object)session.createQuery("select  count(bookid) from Reply  as r where bookid=? ").
					setParameter(0,bookid).uniqueResult();
			
		    tx.commit();
		    HibernateUtil.closeSession(session);
		    
		    if(replynum!=null)
		    {
		    
	       return replynum;
		    }
		    else
		    {
		    	
		    	return 0;
		    }

		}
		
		//获取精彩评论
		public List<Comment>  getTopCommentByid(String bookid)
		{
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
	List<Comment>  dlist=session.createQuery("from Comment as c  where c.book.bookid=? order by commentid desc").setParameter(0,bookid).setFirstResult(0).setMaxResults(5).list();
			

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
		
	//计算图书的评分
		
		public String CountEvaluateAvg(String bookid)
		{
			
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			DecimalFormat    df   = new DecimalFormat("######0.0");
List<Comment>  dlist=session.createQuery("from Comment as c  where c.book.bookid=? and c.evaluatestatus=1").setParameter(0,bookid).list();		
			
              tx.commit();
              HibernateUtil.closeSession(session);
              Double allscore=0.0d;

              if(dlist!=null&&dlist.size()>0)
              {
            	  
            	  for(int i=0;i<dlist.size();i++)
            	  {
            		  
            		  Comment c=dlist.get(i);  
            		  
            		  allscore+=Double.valueOf(c.getEvaluate());
            		  
            	  }
            	  
            	  
            	  
              }
              
              Double avg=allscore/dlist.size();
              return df.format(avg);
			
		}
		
		
	

}
