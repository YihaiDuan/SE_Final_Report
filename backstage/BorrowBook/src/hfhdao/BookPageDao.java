package hfhdao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;




import entity.Book;
import entity.Page;
import util.HibernateUtil;


public class BookPageDao
{
	
	
	public Page<Book> findByPage(int pc,String categoryid) 
	{
		
		int ps=1;  //��ǰҳ���¼ֵ
		int all=0;
		//select count(*) from news"  �ǻ�ȡall��
		//"select * from news limit"+(pc-1)*ps+" , "+ps;
		//"select * from news limit  a[1 ,3;
		//��ѡȡѡȡ��ҳ��ʾ������  ��ʾ3����¼���ӵڶ�����ʼ
			
		
	     Session session=HibernateUtil.getSession();
		Transaction	tx=session.beginTransaction();
	    List<Book> list=null;	
	    if(categoryid.equals("-1"))
	    {
	    	list=session.createQuery("from Book")
					.list();
	    }else{
	    	list=session.createQuery("from Book as b where b.category.categoryid=:categoryid")
					.setString("categoryid", categoryid)
					.list();	
	    }
		
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
		 if(categoryid.equals("-1"))
		 {
			 list2=session.createQuery("from Book order by bookid asc").
						setFirstResult((pc-1)*ps).
						setMaxResults(ps).list();
		 }else{
			 list2=session.createQuery("from Book as b  where b.category.categoryid=:categoryid order by bookid asc").
		                setString("categoryid", categoryid).
						setFirstResult((pc-1)*ps).
						setMaxResults(ps).list();
			 
		 }

	
             tx2.commit();

			pageBean.setBeanlist(list2);
	
		    HibernateUtil.closeSession(session2);
			
			 return pageBean;
		
	}


}
