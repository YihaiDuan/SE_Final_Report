package siggyfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;


import util.HibernateUtil;

public class OpenSession implements Filter{

	public void destroy()
	{}
	
	
	public void doFilter(ServletRequest request,ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		 
		
		System.out.println("我来了");
		Session session=null;
		Transaction tx=null;
		try
		{
		 session=HibernateUtil.getSession();
		 tx=session.beginTransaction();
		 chain.doFilter(request, response);
		 //servlet->service->dao->getssesion->servlet->jsp->��������
		 System.out.println("我提交了");
		 tx.commit();
		 
		
		}catch(Exception e)
		{
			if(tx!=null) 
		tx.rollback();
				
		
		}
		finally
		{
			HibernateUtil.closeSession(session);
			
		}
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
