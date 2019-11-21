/**
 * 
 */
package Extenddao2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Admire;
import entity.Dynamic;
import entity.DynamicAdmire;
import entity.DynamicComment;
import entity.DynamicMessage;
import entity.DynamicReply;
import entity.Fan;
import entity.Reply;
import entity.User;
import util.HibernateUtil;

public class DynamicDao {
	
	
	
	//获取热门动态
	public List<Dynamic>  getHotDynamic()
	{
		
		
		Session  session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
List<Dynamic>  dlist=session.createQuery("from Dynamic where admirenum>10 and typeid=0  order by admirenum desc").setFirstResult(0).setMaxResults(10).list();
		

tx.commit();
HibernateUtil.closeSession(session);
		if(dlist!=null&&dlist.size()>0)
		{
			
			return dlist;
			
			
			
		}else
		{
			
			
			return null;
			
			
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	//添加动态
	public int SaveDynamic(Dynamic  d)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(d);
	
		tx.commit();
		HibernateUtil.closeSession(session);
		
		return d.getDynamicid();

     }
	
	
	//添加关注
	public void SaveFan(Fan  fa)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(fa);
	
		tx.commit();
		HibernateUtil.closeSession(session);
		
     }
	
	
	//取消关注
	public  void DeleteFan(String userid,String otherid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		 Fan f=null;
		
         f=(Fan)session.createQuery("from Fan as f where  f.user.userid=? and otherid=?").
					setParameter(0,userid).setParameter(1,otherid).uniqueResult();	
		
		if(f!=null)
		{
			
		  session.delete(f);
	
		}
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		
	}
	
	
	//添加动态的赞
	public void SaveDynamicAdmire(DynamicAdmire  da)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(da);
	
		tx.commit();
		HibernateUtil.closeSession(session);
		
     }
	
	//撤销动态的赞
	public  void DeleteDynamicAdmire(String userid,int dynamicid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		DynamicAdmire	admire=null;
		
         admire=(DynamicAdmire)session.createQuery("from DynamicAdmire as da where  da.user.userid=? and da.dynamic.dynamicid=?").
					setParameter(0,userid).setParameter(1, dynamicid).uniqueResult();	
		
		if(admire!=null)
		{
			
		  session.delete(admire);
	
		}
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		
	}
	
	
	
	public Dynamic getDynamicByid(int id)
	{
		
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		Dynamic d=(Dynamic) session.get(Dynamic.class,id);
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
		if(d!=null)
		
		{
			
			return d;
		}
		else
		{
			
			return null;
		}
		
	
	}
	
	
	public Dynamic getDynamicByid2(int id)
	{
		
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		Dynamic d=(Dynamic) session.load(Dynamic.class,id);
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
		if(d!=null)
		
		{
			
			return d;
		}
		else
		{
			
			return null;
		}
		
	
	}

	public void  UpdateDynamic(Dynamic d)
	{
		
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		Dynamic d2=(Dynamic) session.get(Dynamic.class,d.getDynamicid());
		
		if(d2!=null)
		
		{
			d2.setImages(d.getImages());
			session.update(d2);
		}
		
		
		tx.commit();
		HibernateUtil.closeSession(session);
	
	}
	
	
	
	//显示所有动态
	public List<Dynamic>  showAllDynamic()
	{
		
		
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<Dynamic> dlist=session.createQuery("from Dynamic").list();
		
		
		tx.commit();
		HibernateUtil.closeSession(session);
		
	if(dlist!=null&&dlist.size()>0)
	{
		
		return dlist;
		
	}
	
	return null;

	}
	
	
	
	//计算总的评论数
	public Object CountReply(int dynamicid)
	{
		
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Object num=0;
		
        num=session.createQuery("select count(dynamicid)  from DynamicReply where dynamicid=?").
setParameter(0,dynamicid).uniqueResult();
		

       tx.commit();
       HibernateUtil.closeSession(session);

       return num;
		
	}
	
	
	
	//计算总的评论数
		public Object CountComment(int dynamicid)
		{
			
			Session session =HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			Object num=0;
			
	        num=session.createQuery("select count(dc.dynamic.dynamicid)  from DynamicComment as dc where dc.dynamic.dynamicid=?").
	setParameter(0,dynamicid).uniqueResult();
			

	       tx.commit();
	       HibernateUtil.closeSession(session);

	       return num;
			
		}
		
		
		
		//获取点赞用户头像
		public List<DynamicAdmire>  getAllAdmireByid(int dynamicid)
		{
			
			
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			
List<DynamicAdmire>  list=session.createQuery("from DynamicAdmire as ca where ca.dynamic.dynamicid=?").setParameter(0,dynamicid).
setFirstResult(0).
setMaxResults(8).list();
			
			
			
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
		
		
		//获取用户关注人数
		public Object getFanNum(String userid)
		{
			Session session =HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			Object num=0;
			
			num=session.createQuery("select count(f.user.userid) from Fan as f where f.user.userid=?").setParameter(0, userid).uniqueResult();
			
			
			tx.commit();
			HibernateUtil.closeSession(session);
			if(num!=null)
			{
				
				return num;
			}
			else
			{
				
				return 0;
			}
		}
		
		//获取用户粉丝人数
		public Object getFansNum(String userid)
		{
			Session session =HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
			Object num=0;
			
			num=session.createQuery("select count(otherid) from Fan as f where otherid=?").setParameter(0, userid).uniqueResult();
			
			
			tx.commit();
			HibernateUtil.closeSession(session);
			if(num!=null)
			{
				
				return num;
			}
			else
			{
				
				return 0;
			}
		}
		
		//获取用户动态数量
		public List<Dynamic> getDynamicByUserid(String userid)
		{
			Session session =HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			
		
			
	List<Dynamic> dlist=session.createQuery("from Dynamic as d where d.user.userid=?").setParameter(0, userid).list();
			
			
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
		
		
		//我发的动态有谁评论了我
public List<DynamicComment>   getCommentuserid(String userid)
     {
			   
			   Session session=HibernateUtil.getSession();
			   Transaction tx=session.beginTransaction();
					  
List<DynamicComment>  
list=session.createQuery("select c  from DynamicComment  as c inner join c.dynamic as d  with  d.user.userid=?").
	setParameter(0,userid).list();
		   
		  
				
	     tx.commit();
	     HibernateUtil.closeSession(session);
				 
	     if(list!=null&&list.size()>0)
	 	{
	 		
	 		
	 		return list;
	 	}
	 		return null; 
			   
     }
//我发的评论有谁回复了我
public List<DynamicReply>   getReplyByuserid(String userid)
{
		   
		   Session session=HibernateUtil.getSession();
		   Transaction tx=session.beginTransaction();
				  
List<DynamicReply>  
list=session.createQuery("from DynamicReply  as dr where  otherid=?").
setParameter(0,userid).list();
	   
	  
			
    tx.commit();
    HibernateUtil.closeSession(session);
			 
	if(list!=null&&list.size()>0)
	{
		
		
		return list;
	}
		return null;   
	}
		//获取用户的的动态
      public List<Dynamic>   getDynamicByUser(String userid)
      {
    	  
    	  Session session=HibernateUtil.getSession();
		   Transaction tx=session.beginTransaction();
				  
List<Dynamic>  list=session.createQuery("from Dynamic  as d where  d.user.userid=?").
setParameter(0,userid).list();
	   
	  
			
   tx.commit();
   HibernateUtil.closeSession(session);
			 
   if(list!=null&&list.size()>0)
	{
		
		
		return list;
	}
		return null;  
    	  
    	
      }
		
	public static void main(String [] args)
	{
		
		DynamicDao dd=new DynamicDao();
		
		List<DynamicMessage>  dmlist=new ArrayList<DynamicMessage>();
		
		
	List<DynamicComment> dlist=dd.getCommentuserid("o50AL0U9sAMhbI3ZrqfTF7nmWduo");
		
		
	if(dlist!=null&&dlist.size()>0)
		{
			for(int i=0;i<dlist.size();i++)
			{
				
			DynamicComment d=dlist.get(i);
			DynamicMessage dm=new DynamicMessage();	
			dm.setId(d.getCommentid());
			dm.setTypestatus(0);
			dm.setUserid(d.getUser().getUserid());
		    dm.setDate(d.getDate());
		    dm.setDescrib(d.getDescrib());
		    dm.setDynamicid(d.getDynamic().getDynamicid());
		    
		    System.out.println(dm.getTypestatus());
		    dmlist.add(dm);
			}
			
			
		}
		List<DynamicReply>  drlist=dd.getReplyByuserid("o50AL0U9sAMhbI3ZrqfTF7nmWduo");
		
		if(drlist!=null&&drlist.size()>0)
		{
			for(int i=0;i<drlist.size();i++)
			{
				DynamicReply dr=drlist.get(i);
				DynamicMessage dm=new DynamicMessage();
				dm.setId(dr.getReplyid());
				dm.setTypestatus(1);
				dm.setUserid(dr.getUser().getUserid());
			    dm.setDate(dr.getDate());
			    dm.setDescrib(dr.getDescrib());
			    dm.setDynamicid(dr.getDynamicid());
				
			    dmlist.add(dm);
				
			}
			
			
		}
		
		
		  if(dmlist!=null&&dmlist.size()>0)
		  {
			 for(int i=0;i<dmlist.size();i++)
			  {
				  
				 DynamicMessage dm2=dmlist.get(i);
				 
				 System.out.println("我是新集合"+dm2.getTypestatus());
				  
			  }
		   }
		
		  Collections.sort(dmlist, new Comparator<DynamicMessage>(){  
			  
	            /*  
	             * int compare(Student o1, Student o2) 返回一个基本类型的整型，  
	             * 返回负数表示：o1 小于o2，  
	             * 返回0 表示：o1和o2相等，  
	             * 返回正数表示：o1大于o2。  
	             */  
	            public int compare(DynamicMessage o1, DynamicMessage o2) {  
	              
	                //按照学生的年龄进行降序排列  
	                if(o1.getDate().compareTo(o2.getDate())==-1 ){  
	                    return 1;  
	                }  
	                if(o1.getDate().compareTo(o2.getDate())==0 ){  
	                    return 0;  
	                }  
	                return -1;  
	            }  
	        });   
		  
	       if(dmlist!=null&&dmlist.size()>0)
                {
                	
	    	   for(int i=0;i<dmlist.size();i++)
	    	   {
	    		   
	    		   DynamicMessage dm=dmlist.get(i);
	    		   
	    		   
	    		   System.out.println(dm.getDate());
                	
                }
	    	   
                }
		
		
	}
	
	
}
