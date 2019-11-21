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
public class UserBrowse {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	@Column(columnDefinition="INT default 1") 
	private int  num; 
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  // 外键
	@JoinColumn(name = "book_id")
	private Book  book;
	
	
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  // 外键
	@JoinColumn(name = "user_id")
	private User user;




	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}




	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}




	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}




	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}




	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}




	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}




	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
}
