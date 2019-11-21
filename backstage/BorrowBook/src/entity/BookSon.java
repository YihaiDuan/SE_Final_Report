package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * 
 * @author hjm
 * @todo 具体图书实体
 */


@Entity
@Table


public class BookSon 
{
	
	 @Id
	private String booksonid;
	

	 //被借标志位
	 @Column(columnDefinition="INT default 0") 
    private int borrowstatus;
	   
    
	 //被预订标志位
//	 @Column(columnDefinition="INT default 0") 
//	private int orderstatus;
	
	
	


	@ManyToOne(fetch=FetchType.EAGER)
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  
	@JoinColumn(name = "book_id")
	private Book book;
	
	
	 
	
	
		@OneToMany(targetEntity=BorrowTable.class,cascade=CascadeType.ALL)
		@JoinColumn(name="bookson_id",updatable=false)
	   private Set<BorrowTable>  borrowtable  =new HashSet<BorrowTable>();
		
		
		
		
		@OneToMany(targetEntity=OnlineOrder.class,cascade=CascadeType.ALL)
		@JoinColumn(name="bookson_id",updatable=false)
//	   private Set<OnlineOrder>  onlineOrder  =new HashSet<OnlineOrder>();
		
		


		public String getBooksonid() {
			return booksonid;
		}


		public void setBooksonid(String booksonid) {
			this.booksonid = booksonid;
		}


		public int getBorrowstatus() {
			return borrowstatus;
		}


		public void setBorrowstatus(int borrowstatus) {
			this.borrowstatus = borrowstatus;
		}

//
//		public int getOrderstatus() {
//			return orderstatus;
//		}
//
//
//		public void setOrderstatus(int orderstatus) {
//			this.orderstatus = orderstatus;
//		}


		public Book getBook() {
			return book;
		}


		public void setBook(Book book) {
			this.book = book;
		}


		public Set<BorrowTable> getBorrowtable() {
			return borrowtable;
		}


		public void setBorrowtable(Set<BorrowTable> borrowtable) {
			this.borrowtable = borrowtable;
		}


//		public Set<OnlineOrder> getOnlineOrder() {
//			return onlineOrder;
//		}
//
//
//		public void setOnlineOrder(Set<OnlineOrder> onlineOrder) {
//			this.onlineOrder = onlineOrder;
//		}
		
		

	
		
		
		
	
		
		
	

	
	
	
	

}
