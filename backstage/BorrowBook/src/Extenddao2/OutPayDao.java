/**
 * 
 */
package Extenddao2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Admire;
import entity.OutPay;
import util.HibernateUtil;

public class OutPayDao 
{
	
	

	 public void addOutPay(OutPay op)
	{
		
		 
		 
		 
			Session session=HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			session.save(op);
			tx.commit();
		    HibernateUtil.closeSession(session);
		
		
	}
	
	 
	 //获取用户的退款记录
	 public List<OutPay>  getUserOutPay(String userid)
	 {
		 
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 
		 List<OutPay> oplist=session.createQuery("from OutPay as op where op.user.userid=?").setParameter(0,userid).list();
		 
		 
		 
		 tx.commit();
		 HibernateUtil.closeSession(session);
		 if(oplist!=null&&oplist.size()>0)
		 {
			 
			 return oplist;
			 
		 }
		 else
		 {
			 
			 return null;
		 }
		 
	 }
	 
	 
	 
	 //获取退款记录没有读过的集合
	 public List<OutPay>  getOutPayNoRead(String userid)
	 {
		 
		 
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 
		 List<OutPay> oplist=session.createQuery("from OutPay as op where op.user.userid=?  and op.readstatus=0").setParameter(0,userid).list();
		 
		 
		 
		 tx.commit();
		 HibernateUtil.closeSession(session);
		 if(oplist!=null&&oplist.size()>0)
		 {
			 
			 return oplist;
			 
		 }
		 else
		 {
			 
			 return null;
		 }	 
		 
	
	 }
	 
	 
	//更新读过得退款记录
	 
	 public void UpdateReadStatus(OutPay op)
	 {
		 
		 Session session=HibernateUtil.getSession();
		 Transaction tx=session.beginTransaction();
		 
		 OutPay op1=(OutPay) session.get(OutPay.class,op.getId());
		 if(op1!=null)
		 {
			 
			 op1.setReadstatus(1);
			 session.update(op1);
			 
		 }
		 tx.commit();
		 HibernateUtil.closeSession(session);
		 
	 }
	 
	
	
	
}
