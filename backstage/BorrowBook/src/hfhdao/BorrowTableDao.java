package hfhdao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Book;
import util.HibernateUtil;

/**
 * 借书表操作类
 * */
public class BorrowTableDao {
   //获得当天借书量
	public Integer getSameNum(String sameday){
	  Session session=HibernateUtil.getSession();
      Number number = null;
      try {
    	  session.beginTransaction();
    	  String hql="select count(*) from BorrowTable where borrowdate like :borrowdate ";
		  number = (Number) session.createQuery(hql).setString("borrowdate",sameday+"%").uniqueResult();
		  session.getTransaction().commit();
	      } catch (Exception e) {
		  e.printStackTrace();
		  session.getTransaction().rollback();
	      }
  	finally{
		HibernateUtil.closeSession(session);
	}
	return number.intValue();		
	}
	//获得当天已借图书的数量
		public Integer getScanNum(String sameday){
		  Session session=HibernateUtil.getSession();
	      Number number = null;
	      try {
	    	  session.beginTransaction();
	    	  String hql="select count(*) from BorrowTable where borrowdate like :borrowdate and scanstatus=1";
			  number = (Number) session.createQuery(hql).setString("borrowdate",sameday+"%").uniqueResult();
			  session.getTransaction().commit();
		      } catch (Exception e) {
			  e.printStackTrace();
			  session.getTransaction().rollback();
		      }
	  	finally{
			HibernateUtil.closeSession(session);
		}
		return number.intValue();		
		}
		//获得当天逾期未还图书的数量
				public Integer getOverdueNum(String sameday){
				  Session session=HibernateUtil.getSession();
			      Number number = null;
			      try {
			    	  session.beginTransaction();
			    	  String hql="select count(*) from BorrowTable where deadline < :sameday and returndate > :sameday";
					  number = (Number) session.createQuery(hql).setString("sameday",sameday ).uniqueResult();
					  session.getTransaction().commit();
				      } catch (Exception e) {
					  e.printStackTrace();
					  session.getTransaction().rollback();
				      }
			  	finally{
					HibernateUtil.closeSession(session);
				}
				return number.intValue();		
				}
    //获得当前booksonid 的借书量
	public  Integer getBookSonNum(String booksonid){
		Session session=HibernateUtil.getSession();
		Number number=null;
		try {
			session.beginTransaction();
			String hql="select count(*) from BorrowTable as b where b.bookson.booksonid=:booksonid ";
			number=(Number) session.createQuery(hql).setString("booksonid", booksonid).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}	
		finally{
			HibernateUtil.closeSession(session);
		}
		return number.intValue();
	  }
	//获得当天类别的借书量
		public  Integer getCategoryNum(String borrowdate,String categoryid){
			Session session=HibernateUtil.getSession();
			Number number=null;
			try {
				session.beginTransaction();
				//select count(bt.bookson.booksonid) from BorrowTable as bt inner join  bt.bookson left join   bt.bookson.book left join   bt.bookson.book.category as ca  with ca.categoryid=:categoryid
	            String sql="select count(bt.bookson_id) from book as b,bookson as bs,borrowtable as bt where b.bookid=bs.book_id and bs.booksonid=bt.bookson_id and b.category_id=:categoryid and bt.borrowdate like :borrowdate  and scanstatus=1";
				number=(Number) session.createSQLQuery(sql).setString("categoryid",categoryid ).setString("borrowdate", borrowdate+"%").uniqueResult();
				session.getTransaction().commit();
				session.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
			}	
			finally{
				HibernateUtil.closeSession(session);
			}
			return number.intValue();
			
		  }
		  //统计用书已借出书总量
	      public Integer borrowTableDeatilNumber(){
	    	  Session session=HibernateUtil.getSession();
	    	  Number number=null;
	    	  try {
				session.beginTransaction();
				String hql="select count(*) from BorrowTable where status=0";
				  number = (Number) session.createQuery(hql).uniqueResult();
				session.getTransaction().commit();
	    		  
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
				// TODO: handle exception
			}
	    		finally{
	    			HibernateUtil.closeSession(session);
	    		}
	    	 return number.intValue();
	      }
	      //统计用书逾期未还总量
	      public Integer borrowTableoverdueNumber(){
	    	  Session session=HibernateUtil.getSession();
	    	  Number number=null;
	    	  try {
				session.beginTransaction();
				String hql="select count(*) from BorrowTable where status=0 and deadstatus=1";
				number = (Number) session.createQuery(hql).uniqueResult();
				session.getTransaction().commit();
	    		  
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
				// TODO: handle exception
			}
	    		finally{
	    			HibernateUtil.closeSession(session);
	    		}
	    	 return number.intValue();
	      }
	      
	      public void saveBook(Book b){
	    	  Session session=HibernateUtil.getSession();
	    	  Transaction tx=null;
	    	  try {
				tx=session.beginTransaction();
				session.save(b);
				
	    		  tx.commit();
	    	} catch (Exception e) {
				// TODO: handle exception
			}
	    	  
	    		finally{
	    			HibernateUtil.closeSession(session);
	    		}
	    	  
	    	  
	      }
	      
	      
	public static void main(String[] args) {
		
		
		BorrowTableDao bd=new BorrowTableDao();
		Book b1=new Book ();
		b1.setBookid("22222222222343434344");
		Book b2=new Book ();
		b2.setBookid("444444444444444444444444");
		bd.saveBook(b1);
		bd.saveBook(b2);
		
	}
}
