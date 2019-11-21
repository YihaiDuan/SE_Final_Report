/**
 * 
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
@Entity
@Table
public class MyEleBook {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	 @Column(columnDefinition="INT default 0") 
	private int addstatus;
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	// 外键
	@JoinColumn(name = "book_id")
	private Book  book;
	
	
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	// 外键
	@JoinColumn(name = "user_id")
	private User  user;




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public Book getBook() {
		return book;
	}




	public void setBook(Book book) {
		this.book = book;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public int getAddstatus() {
		return addstatus;
	}




	public void setAddstatus(int addstatus) {
		this.addstatus = addstatus;
	}
	
	
}
