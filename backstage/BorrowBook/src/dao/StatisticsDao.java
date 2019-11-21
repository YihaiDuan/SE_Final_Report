package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import entity.BookTypeNumber;
import entity.BorrowBookTypeNumber;

import util.HibernateUtil;
/*
 * 首页统计操作
 * */
public class StatisticsDao {
   //图书类别比例
   public List<BookTypeNumber> getBookTypenumber(){
	   Session session = HibernateUtil.getSession();
	   List<Object[]> list = null;
	   List<BookTypeNumber> list1 = new ArrayList<BookTypeNumber>();
	   try {
		  session.beginTransaction();
		  String sql = "select booktype.name,count(booktype.id) from  booktype,category,book,bookson where booktype.id = category.type_id and category.categoryid=book.category_id and book.bookid=bookson.book_id GROUP BY booktype.id;";
		  list = session.createSQLQuery(sql).list();	 
	      session.getTransaction().commit();
	   } catch (Exception e) {
		// TODO: handle exception
		   e.printStackTrace();
		   session.getTransaction().rollback();
	}
	   for(int i = 0 ; i< list.size();i++)
		  {
			  BookTypeNumber booktypenumber = new BookTypeNumber();
			  booktypenumber.setBooktypename((String)list.get(i)[0]);
			  booktypenumber.setNumber(Integer.valueOf(list.get(i)[1].toString()));
			  list1.add(booktypenumber);
		  }
	    return list1;
   } 
   //借出图书类别比例
   
   public List<BorrowBookTypeNumber> getBorrowBookTypenumber(){
	   Session session = HibernateUtil.getSession();
	   List<Object[]> list = null;
	   List<BorrowBookTypeNumber> list1 = new ArrayList<BorrowBookTypeNumber>();
	   try {
		  session.beginTransaction();
		  String sql = "select booktype.name,count(booktype.id) from  booktype,category,book,bookson,borrowtable where booktype.id = category.type_id and category.categoryid=book.category_id and book.bookid=bookson.book_id and bookson.booksonid=borrowtable.bookson_id GROUP BY booktype.id ;";
		  list = session.createSQLQuery(sql).list();	 
	      session.getTransaction().commit();
	   } catch (Exception e) {
		// TODO: handle exception
		   e.printStackTrace();
		   session.getTransaction().rollback();
	}
	   for(int i = 0 ; i< list.size();i++)
		  {
		   BorrowBookTypeNumber borrowbooktypenumber = new BorrowBookTypeNumber();
		   borrowbooktypenumber.setBorrowbooktypenumber((String)list.get(i)[0]);
		   borrowbooktypenumber.setNumber(Integer.valueOf(list.get(i)[1].toString()));
		   list1.add(borrowbooktypenumber);
		  }
	    return list1;
   } 
   //本年某月用户借阅币充值收入
     public Double getMonthIncome(String time){
    	 Session session = HibernateUtil.getSession();
    	 Number number = null;
    	 try {
			session.beginTransaction();
			String hql = "select sum(price) from UserRecharge where time like:time";
			number =(Number) session.createQuery(hql).setParameter("time", "%"+time+"%").uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
    	 finally{
    		 HibernateUtil.closeSession(session);
    		 
    	 }
    	 
    	 if(number==null)
    	 {
    		  return 0.0;
    	 }else{
    		 
    		 return (Double) number;
    	 }
    	  
     } 
   
     //本年某月用户会员充值收入
     public Double getMonthVipIncome(String time){
    	 Session session = HibernateUtil.getSession();
    	 Number number = 0;
    	 try {
			session.beginTransaction();
			String hql = "select sum(price) from UserVipRecharge where time like:time";
			number =(Number) session.createQuery(hql).setParameter("time", "%"+time+"%").uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
    	 finally{
    		 HibernateUtil.closeSession(session);
    		 
    	 }
    	 
    	 if(number==null)
    	 {
    		  return 0.0;
    	 }else{
    		 
    		 return (Double) number;
    	 }
     } 
   
   
   
	public static void main(String[] args) {
		StatisticsDao s = new StatisticsDao();
	   System.out.println(s.getMonthIncome("2017/07"));	
	}
	
	
	
	
}
