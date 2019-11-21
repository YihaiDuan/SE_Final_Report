package hfhdao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;




import entity.Book;
import entity.Page;
import util.HibernateUtil;


public class authorPageDao
{
	
	
	public Page<Book> findByPage(int pc,String author) 
	{
		
		int ps=15;  //��ǰҳ���¼ֵ
		int all=0;
		
		//select count(*) from news"  �ǻ�ȡall��
		//"select * from news limit"+(pc-1)*ps+" , "+ps;
		//"select * from news limit  a[1 ,3;
		//��ѡȡѡȡ��ҳ��ʾ������  ��ʾ3����¼���ӵڶ�����ʼ
			
		
	     Session session=HibernateUtil.getSession();
		Transaction	tx=session.beginTransaction();
	    List<Book> list=null;	
		list=session.createQuery("from Book where author like :author")
				.setString("author", "%"+author+"%")
				.list();
		if(list!=null)
		{
			
			all=list.size();
			
			
		}
		
		tx.commit();
	   
		
		
		Session session2=HibernateUtil.getSession();
		Transaction	tx2=session2.beginTransaction();

		
	
		Page<Book> pageBean=new Page<Book>();

		pageBean.setAll(all);
		pageBean.setPc(pc);        //��jsp������
		pageBean.setPs(ps);
	
		List<Book> list2=new ArrayList<Book>(); 
		
list2=session.createQuery("from Book where author like :author order by bookid asc").
                setString("author", "%"+author+"%").
				setFirstResult((pc-1)*ps).
				setMaxResults(ps).list();
	
             tx2.commit();

			pageBean.setBeanlist(list2);
	
		    HibernateUtil.closeSession(session2);
			
			 return pageBean;
		
	}


}
