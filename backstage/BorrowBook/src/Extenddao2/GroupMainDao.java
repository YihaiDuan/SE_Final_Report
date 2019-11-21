/**
 * 
 */
package Extenddao2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.GroupMain;
import entity.GroupMember;
import util.HibernateUtil;


public class GroupMainDao {
	
	
	
	public int SaveGroupMain(GroupMain  to)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(to);
	
		
	
		tx.commit();
	    HibernateUtil.closeSession(session);
	    
		return to.getId();
   }
	
	
	public void SaveGroupMember(GroupMember  to)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(to);
	
		tx.commit();
	    HibernateUtil.closeSession(session);
   }
	
	
	
	
	public List<GroupMain>  getGroupMainByBookid(String bookid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
List<GroupMain> tlist=session.createQuery("from GroupMain where bookid=? and status=0").setParameter(0,bookid).list();
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		if(tlist!=null)
		{
		
		return tlist;
		
		}
		else
		{
			return null;
		}
	
	}
	
	
	
	public Object  getNumfromGroupMember(int id)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
      Object num=null;
		
      num=(Object) session.createQuery("select count(gmb.groupmain.id) from GroupMember as gmb where gmb.groupmain.id=?").setParameter(0,id).uniqueResult();
		
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
	
	
	
	public Object getOpenMainGroupNum(int groupbookid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		Object num=null;
		
		num=session.createQuery("select count(gm.groupbook.id) from GroupMain as gm where gm.groupbook.id=?").setParameter(0,groupbookid).uniqueResult();
		
		
		
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
	
	
	public GroupMain getGroupMainByid(int id)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		
		GroupMain gm=(GroupMain) session.get(GroupMain.class,id);
		
		
		
		tx.commit(); 
	    HibernateUtil.closeSession(session);
	    
	    
		if(gm!=null)
		{
			
			return gm;
			
		}
		else
		{
			
			return null;
			
		}
	
	}
	
	
	public void UpdateGroupmainFull(GroupMain gm)
	{
		
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		GroupMain gm1=(GroupMain) session.get(GroupMain.class,gm.getId());
		
		gm1.setStatus(gm.getStatus());
		session.update(gm1);
		
		tx.commit();
		
		HibernateUtil.closeSession(session);
		
		
	}
	
	
	public List<GroupMember>  getAllMemberbyid(int id)
	{
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
	List<GroupMember> gmlist=session.createQuery("from GroupMember as gm where gm.groupmain.id=?").setParameter(0,id).list();
		
	
	tx.commit();
	
	HibernateUtil.closeSession(session);
	
	
		if(gmlist!=null)
		{
			
			return gmlist;
			
		}
		else
		{
			
			return null;
		}
		
	}
	
	
	
	public boolean  UserOpenMainGroupBol(String userid,String bookid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
			
List<GroupMain> gmlist=session.createQuery("from GroupMain as gm where status=0 and userid=? and bookid=?").
setParameter(0,userid).
setParameter(1,bookid).list();
		


        tx.commit();
        HibernateUtil.closeSession(session);
		
		if(gmlist!=null&&gmlist.size()>0)
		{
			
			return true;
		}
		else
		{
			
			return false;
		}
		
	}
	
	
	public boolean  UserOpenMemberGroupBol(String userid,String bookid)
	{
		
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
List<GroupMain> gmlist=session.createQuery("select gmem from GroupMember as gmem  inner join gmem.groupmain as gm where gm.status=0 and gmem.userid=? and gmem.bookid=?").
setParameter(0,userid).
setParameter(1,bookid).list();
		


        tx.commit();
        HibernateUtil.closeSession(session);
		
		if(gmlist!=null&&gmlist.size()>0)
		{
			
			return true;
		}
		else
		{
			
			return false;
		}
		
	}
	//获取用户开主团
	public List<GroupMain>   getGroupMainByuserid(String userid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
List<GroupMain> gmlist=session.createQuery("from GroupMain as gm where userid=?  and status=0").
setParameter(0,userid).list();
		
		
        tx.commit();
        HibernateUtil.closeSession(session);
		if(gmlist!=null&&gmlist.size()>0)
		{
			
			return gmlist;
			
			
			
		}
		return null;
		
		
	}
	
	
	//获取用户参团
	public List<GroupMember>   getGroupMemberByuserid(String userid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
List<GroupMember> gmlist=session.createQuery("select gm from GroupMember as gm inner join gm.groupmain as gma where gm.userid=?  and gma.status=0").
setParameter(0,userid).list();
		
		
        tx.commit();
        HibernateUtil.closeSession(session);
		if(gmlist!=null&&gmlist.size()>0)
		{
			
			return gmlist;
			
			
			
		}
		return null;
		
		
	}
	
	
	//获取团已经过期了还没有成团的用户进行退款
	public List<GroupMain>  getGroupMainDead(int groupid)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
List<GroupMain>  gmlist=
session.createQuery("select gm from GroupMain as gm left join fetch gm.groupbook as gb left join fetch gb.groupmore as gmr where gm.status=0 and gmr.showstatus=2  and gmr.id=?")
.setParameter(0,groupid).list();
		

tx.commit();
HibernateUtil.closeSession(session);


if(gmlist!=null&&gmlist.size()>0)
{
	
return gmlist;


}
else
{
	
return null;

}
		
}
	
	
	
	
	public static void main(String []  args)
	{
		
		
		  GroupMainDao  gm=new GroupMainDao();
	      List<GroupMain>  gmlist=gm.getGroupMainDead(1);   
	    	if(gmlist!=null&&gmlist.size()>0)
	    	{
	    		
	    	System.out.println(gmlist.size());
	    		
	    		
	    	}
	    	
	    
	}

	
}
