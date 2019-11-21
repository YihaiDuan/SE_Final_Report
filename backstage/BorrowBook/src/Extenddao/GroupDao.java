/**
 * 
 */
package Extenddao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import entity.GroupMore;
import entity.Topic;
import util.HibernateUtil;

/**
 * @author Administrator
 * @date Jul 26, 2017
 * @todoTODO
 */
public class GroupDao 
{
	//添加拼多多
	public GroupMore SaveGroup1(GroupMore  g)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		session.clear();
		session.save(g);
	
		tx.commit();
	    HibernateUtil.closeSession(session);
	    
	    
	    return g;
   }
	public int SaveGroup(GroupMore  g)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		session.clear();
		session.save(g);
	
		tx.commit();
	    HibernateUtil.closeSession(session);
	    
	    
	    return g.getId();
   }
	//根据groupid获取拼多多信息
	public GroupMore  getGroupMoreById(int id)
	{
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		GroupMore g=(GroupMore) session.get(GroupMore.class,id);
		
		tx.commit();
	    HibernateUtil.closeSession(session);
		
		if(g!=null)
		{
			
			return g;
			
			
		}
		else
		{
			return null;
			
		}
	}
	
	
	
	  //更新组团是否开始和过期的标志位
		public void UpdateGroupStatus(GroupMore b)
		{
			
			 Session session=HibernateUtil.getSession();
			 Transaction tx=session.beginTransaction();
			 
			 GroupMore b1=(GroupMore) session.get(GroupMore.class,b.getId());
			
		     b1.setShowstatus(b.getShowstatus());
		     
		      session.update(b1);

			    tx.commit();
			    HibernateUtil.closeSession(session);

		}
		//获得所有组团信息
				public List<GroupMore> getListGroupMore(){
					Session session = HibernateUtil.getSession();
					List<GroupMore> list = null;
				    try {
						session.beginTransaction();
						String hql = "from GroupMore";
						 list = session.createQuery(hql).list();
						 session.getTransaction().commit();	 
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						session.getTransaction().rollback();
					}
					finally{
						HibernateUtil.closeSession(session);
					}
				    return list;
				}
			//删除组团信息
				public void deleteGroupMore(GroupMore groupmore){
					Session session = HibernateUtil.getSession();
				    try {
						session.beginTransaction();
						session.delete(groupmore);
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
		    //删除全部过期信息
				public void deleteOutTimeGroupMore(){
					Session session = HibernateUtil.getSession();
					try {
						session.beginTransaction();
						String hql = "delete from GroupMore where showstatus=2";
						session.createQuery(hql).executeUpdate();
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
				//修改组团信息
				public void updateGroupMore(GroupMore groupmore){
					Session session = HibernateUtil.getSession();
					try {
						session.beginTransaction();
						session.update(groupmore);
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
	
		
}
