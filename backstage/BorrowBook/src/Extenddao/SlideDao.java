
package Extenddao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Discount;
import entity.Slide;
import util.HibernateUtil;


//轮播
public class SlideDao 
{
	
	     
	//添加轮播
	public void SaveSlide(Slide  s)
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(s);
	
		tx.commit();
	    HibernateUtil.closeSession(session);
   }
	
	
	//轮播的显示
	public List<Slide>  ShowSlideAll()
	{
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		
		List<Slide>  slist=session.createQuery("from Slide where showstatus=1").list();
		 
		    tx.commit();
		    HibernateUtil.closeSession(session);
		 
		    return slist;
		
		
	}
	//更改轮播信息
		public void changeSlide(Slide slide){
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				session.update(slide);
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
		//根据id查询轮播信息
		public Slide getSlide(int id)
		{
			Session session = HibernateUtil.getSession();
			Slide slide = null;
			try {
				session.beginTransaction();
				slide=(Slide)session.get(Slide.class,id);
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			finally{
				HibernateUtil.closeSession(session);
			}
			return slide;		
		}
		//删除轮播信息
		public void deleteSlide(Slide slide){
			Session session=HibernateUtil.getSession();
			try {
				session.beginTransaction();
				session.delete(slide);
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
		//修改轮播
		public void changesSlide(Slide slide){
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				session.update(slide);
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
	public static void main(String[] args)
	{
		
		SlideDao sd=new SlideDao();
		
		
		List<Slide>  slide=sd.ShowSlideAll();
		
		if(slide!=null)
		{
			
			System.out.println(slide.size());
			
			
		}
		
		
	}
	
	
}
