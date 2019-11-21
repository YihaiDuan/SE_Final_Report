package hfhdao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;






import entity.Admin;
import entity.Book;
import entity.Page;
import entity.User;
import util.HibernateUtil;


public class AdminPageDao
{
	
	
	public Page<Admin> findByPage(int pc) 
	{
		
		int ps=15;  //��ǰҳ���¼ֵ
		int all=0;
		
		//select count(*) from news"  �ǻ�ȡall��
		//"select * from news limit"+(pc-1)*ps+" , "+ps;
		//"select * from news limit  a[1 ,3;
		//��ѡȡѡȡ��ҳ��ʾ������  ��ʾ3����¼���ӵڶ�����ʼ
			
		
	     Session session=HibernateUtil.getSession();
		Transaction	tx=session.beginTransaction();
	    List<Admin> list=null;	
		list=session.createQuery("from Admin ").list();
		if(list!=null)
		{
			
			all=list.size();
			
			
		}
		
		tx.commit();
	   
		
		
		Session session2=HibernateUtil.getSession();
		Transaction	tx2=session2.beginTransaction();

		
	
		Page<Admin> pageBean=new Page<Admin>();

		pageBean.setAll(all);
		pageBean.setPc(pc);        //��jsp������
		pageBean.setPs(ps);
	
		List<Admin> list2=new ArrayList<Admin>(); 
		
       list2=session.createQuery("from Admin order by id asc").
				setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
	
             tx2.commit();

			pageBean.setBeanlist(list2);
	
		    HibernateUtil.closeSession(session2);
			
			 return pageBean;
		
	}


}
