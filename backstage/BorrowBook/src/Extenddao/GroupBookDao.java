/**
 * 
 */
package Extenddao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.GroupBook;
import entity.GroupMain;
import entity.GroupMember;
import entity.Topic;
import util.HibernateUtil;

/**
 * @author Administrator
 * @date Jul 26, 2017
 * @todoTODO
 */
public class GroupBookDao 
{
	
	//添加拼多多下的书
	public GroupBook SaveGroupBook(GroupBook gb)
	{
	    
		Session session=HibernateUtil.getSession();
		
		Transaction tx=session.beginTransaction();
		session.evict(gb);
		session.save(gb);
		
		tx.commit();
	    HibernateUtil.closeSession(session);
	    return gb;
   }
	
	
	
	//获取该书是否已经在组团中
   public boolean BolBookinGroup(String bookid)
   {
	   
	   Session session =HibernateUtil.getSession();
	   
	   Transaction tx=session.beginTransaction();
	   List<GroupBook> bglist=null;
	 
	   bglist=session.createQuery("from GroupBook as g left join fetch g.groupmore as gm where g.book.bookid=?  and gm.showstatus=1").setParameter(0,bookid).list();
	   
	   tx.commit();
	   HibernateUtil.closeSession(session);
	   
	   
	   if(bglist!=null&&bglist.size()>0)
	   {
		 
		   return true;
		 
	   }
	   else
	   {
		   
		   
		   
		   return false;
	   }
	   
	   
	   
   }
	
	
	 //根据组团号获取组团下的所有书
   public List<GroupBook>  getGroupBookByid(int id)
   {
	   
	   Session session =HibernateUtil.getSession();
	   Transaction tx=session.beginTransaction();
	   
	   List<GroupBook> bglist=null;
	 
	   bglist=session.createQuery("from GroupBook as g where g.groupmore.id=?").setParameter(0,id).list();
	   
	   tx.commit();
	   HibernateUtil.closeSession(session);
	   
	   return bglist;
	   
	
   }
	
   
   
	
	 //根据组团所有书
 public List<GroupBook>  getAllGroupBook()
 {
	   
	   Session session =HibernateUtil.getSession();
	   Transaction tx=session.beginTransaction();
	   //as g inner join g.groupmore as gm where  gm.showstatus=1
	   List<GroupBook> bglist=null;
	 
	   bglist=session.createQuery("from GroupBook as g left join fetch g.groupmore as gm where  gm.showstatus=1").list();
	   
	   tx.commit();
	   HibernateUtil.closeSession(session);
	   
	   if(bglist!=null&&bglist.size()>0)
	   {
		   return bglist;
		   
	   }else
	   {
		   
		   return null;
	   }
	
 }
   
   
   
   
   
   
   
   
   
       public GroupBook getGroupBook(int id)
       {
    	   
    	   Session session =HibernateUtil.getSession();
    	   Transaction tx=session.beginTransaction();
    	   session.clear();
    	   GroupBook gb =null;
    	    gb=(GroupBook)session.get(GroupBook.class,id);
    	   
    	   tx.commit();
    	   HibernateUtil.closeSession(session);
    	   
    	 if(gb!=null)
    	 {
    		 
    		 
    		 return gb;
    	 }
    	 else
    	 {
    		 
    		 return null;
    	 }
    	   
       }
   
	public GroupBook getGroupBookbyBookid(String bookid)
	{
		
		
	   Session session =HibernateUtil.getSession();
 	   Transaction tx=session.beginTransaction();
 	   
GroupBook gb=(GroupBook)session.createQuery("select gb from GroupBook as gb  inner join gb.groupmore as gm where gm.showstatus=1  and gb.book.bookid=?").setParameter(0,bookid).uniqueResult();
 	   
 	   tx.commit();
 	   HibernateUtil.closeSession(session);
 	   
 	 if(gb!=null)
 	 {
 		 
 		 
 		 return gb;
 	 }
 	 else
 	 {
 		 
 		 return null;
 	 }
		
	
	}
	
	
	//计算该书多少人再团
	
	
	public  List<GroupMain> getGroupMainByGroupBook(int id)
	{
		
		Session session=HibernateUtil.getSession();
		
		Transaction tx=session.beginTransaction();
		
		
	
		
List<GroupMain> gmlist=session.createQuery("from GroupMain as gm where gm.groupbook.id=?  and  gm.status=0").setParameter(0,id).list();
		
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
	
	
	
	public  List<GroupMember> getGroupMemberByGroupBook(int id)
	{
		
		Session session=HibernateUtil.getSession();
		
		Transaction tx=session.beginTransaction();
		
		
	
		
List<GroupMember> gmlist=session.createQuery("from GroupMember as gm where gm.groupmain.id=?").setParameter(0,id).list();
		
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
	
		//修改GroupBook
		public void UpdateGroupBook(GroupBook groupbook){
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				session.clear();
				session.update(groupbook);
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			  finally{
				  HibernateUtil.closeSession(session);
			  }
			
		}
		//清空groupbook表中的没有组团信息表的数据
		public void ClearGroupBook(){
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				String sql = "delete from groupbook where group_id is null";
				session.createSQLQuery(sql);
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}		
		   
		}
	
	
	
	public static void main(String []   args)
	{
		
		
	//	System.out.println(a);
		
		GroupBookDao cb=new GroupBookDao();  
	List<GroupBook> gb=cb.getAllGroupBook();
		
		
		if(gb!=null&&gb.size()>0)
		{
			
		
			GroupBook g=gb.get(0);
			
		System.out.println(g.getId());
		}
		else
		{
			
			System.out.println("不在团中");
		}
		
		
	}
	//删除拼团下的图书信息
		public void deleteGroupBook(GroupBook groupbook){
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				session.delete(groupbook);
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			
		}
	
}
