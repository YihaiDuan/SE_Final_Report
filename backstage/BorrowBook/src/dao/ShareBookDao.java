package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import entity.ShareBook;
import entity.ShareBookCollect;
import entity.User;
import util.HibernateUtil;


/**
 * 
 * @author hjm
 * @todo 图书分享数据访问层
 */
public class ShareBookDao 
{
	

	public void SaveShareBookData(ShareBook  b)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(b);
	
		tx.commit();
	    HibernateUtil.closeSession(session);

     }
	
	
	
	
	public List<ShareBook>   getAllShareBook()
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<ShareBook> list=session.createQuery("from ShareBook").list();
		
		tx.commit();
	  HibernateUtil.closeSession(session);
	   
	    return list;
		
	
	}
	
	
	
	
	public boolean getShareBookBol(String userid,String isbn)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		ShareBook s=null;
		
	  s=(ShareBook) session.createQuery("from ShareBook  where user_id=?  and isbn=?").setParameter(0,userid).setParameter(1,isbn).uniqueResult();
		
		tx.commit();
	    HibernateUtil.closeSession(session);
	   
	  if(s!=null)
	  {
		return true;  
		  
	  }
	  else
	  {
		  
		  return false;
		  
	  }

	}
	
	
	
	
	
	
	public List<ShareBook>  getShareBookByUserid(String userid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<ShareBook> list=session.createQuery("from ShareBook  where user_id=?")
				.setParameter(0, userid).list();
		
		
		tx.commit();
	  HibernateUtil.closeSession(session);
	   
	    return list;
	
	}
	
	

	
	public ShareBook getShareBookByid(int shareid)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		ShareBook sb=null;
		sb=(ShareBook) session.get(ShareBook.class,shareid);
		
		tx.commit();
		  HibernateUtil.closeSession(session);
		
		if(sb!=null)
		{
			
			return sb;
			
		}
		else
		{
			
			return null;
		}
		
		
	}
	
	//获取用户分享的书的数量
	public Object getShareBookNumByUserid(String userid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		Object	s=0;
		
	s=session.createQuery("select count(s.user.userid) from ShareBook  as s  where s.user.userid=?").setParameter(0,userid).uniqueResult();	
		
	  tx.commit();
	  HibernateUtil.closeSession(session);
		 return s;
		
	}
	
	//删除分享的书籍
	
	public void DeleteShareBook(int shareid)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		ShareBook sb=(ShareBook) session.get(ShareBook.class,shareid);
		
		
		if(sb!=null)
		{
			
			session.delete(sb);
		}
	
		
		  tx.commit();
		  HibernateUtil.closeSession(session);
		
		
	}
	
	//判断用户是否已经收藏了该图书
	
	public ShareBookCollect getBookCollectBoolean(String userid,int  shareid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		ShareBookCollect s=null;
		s=(ShareBookCollect) session.createQuery("from ShareBookCollect as s  where s.user.userid=?  and s.sharebook.sharebookid=? ")
				.setParameter(0, userid).setParameter(1,shareid).uniqueResult();
		
		
		
		  tx.commit();
		  HibernateUtil.closeSession(session);
		  
		if(s!=null)
		{
			return s;
			
		}
		else
		{
			
			return null;
		}
	
	}
	
	
	//删除用户收藏的图书
	
	public void DeleteShareBookCollect(int id)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		ShareBookCollect sb=(ShareBookCollect) session.get(ShareBookCollect.class,id);
		
		
		if(sb!=null)
		{
			
			session.delete(sb);
		}
	
		
		  tx.commit();
		  HibernateUtil.closeSession(session);
		
		
	}
	
	
	//添加用户收藏的图书
	public void SavaShareBookCollect(ShareBookCollect sbc)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(sbc);
		
		 tx.commit();
		 HibernateUtil.closeSession(session);
	}
	
	//用户id获取收藏数
	public List<ShareBookCollect>  getAllCollect(String userid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		List<ShareBookCollect> list=session.createQuery("from ShareBookCollect as s where s.user.userid=?")
				.setParameter(0, userid).list();
		
		
		tx.commit();
	  HibernateUtil.closeSession(session);
	   
	    return list;
		
		
		
	}
	

	
	
	//根据书名搜索和isbn搜索
	
	public List<ShareBook>  getShareBookByKey(String key)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		List<ShareBook> list=session.createQuery("from ShareBook where booktitle like ?  or isbn=?")
				.setParameter(0,"%"+key+"%").setParameter(1,key).list();
		
		
		tx.commit();
	  HibernateUtil.closeSession(session);
	   
	    return list;
		
		
		
	}
	
	
	



}
