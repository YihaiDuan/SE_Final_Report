package hfhdao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entity.Admin;
import entity.OnlineOrder;
import entity.Page;

public class ReservePageDao {
	public Page<OnlineOrder> findByPage(int pc) 
	{
		
		int ps=12;  //��ǰҳ���¼ֵ
		int all=0;
		
		//select count(*) from news"  �ǻ�ȡall��
		//"select * from news limit"+(pc-1)*ps+" , "+ps;
		//"select * from news limit  a[1 ,3;
		//��ѡȡѡȡ��ҳ��ʾ������  ��ʾ3����¼���ӵڶ�����ʼ
			
		
	     Session session=HibernateUtil.getSession();
		Transaction	tx=session.beginTransaction();
	    List<OnlineOrder> list=null;	
		list=session.createQuery("from OnlineOrder where status=0 and clearstatus=0 order by orderdate asc ").list();
		if(list!=null)
		{		
			all=list.size();
		}

		tx.commit();
	   
		
		
		Session session2=HibernateUtil.getSession();
		Transaction	tx2=session2.beginTransaction();

		
	
		Page<OnlineOrder> pageBean=new Page<OnlineOrder>();

		pageBean.setAll(all);
		pageBean.setPc(pc);        //��jsp������
		pageBean.setPs(ps);
	
		List<OnlineOrder> list2=new ArrayList<OnlineOrder>(); 
		
       list2=session.createQuery("from OnlineOrder  where status=0 and clearstatus=0 order by orderdate asc").
				setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
	
             tx2.commit();

			pageBean.setBeanlist(list2);
	
		    HibernateUtil.closeSession(session2);
			
			 return pageBean;
		
	}

}
