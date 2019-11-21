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
public class ReadHistroy {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(columnDefinition="INT default 0") 
	private int allpage;
	
	@Column(columnDefinition="INT default 0") 
	private int currentpage;
	
	@Column(columnDefinition="INT default 0") 
	private int end;
	
	@Column(columnDefinition="INT default 0") 
	private int start;
	
	@Column(columnDefinition="INT default 0") 
	private int maxpage;
	
	
	private String showtitle;
	
	private String typename;
	
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




	public int getAllpage() {
		return allpage;
	}




	public void setAllpage(int allpage) {
		this.allpage = allpage;
	}



	public int getCurrentpage() {
		return currentpage;
	}




	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}





	public int getEnd() {
		return end;
	}



	public void setEnd(int end) {
		this.end = end;
	}



	public int getStart() {
		return start;
	}



	public void setStart(int start) {
		this.start = start;
	}




	public int getMaxpage() {
		return maxpage;
	}




	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
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




	public String getShowtitle() {
		return showtitle;
	}



	public void setShowtitle(String showtitle) {
		this.showtitle = showtitle;
	}





	public String getTypename() {
		return typename;
	}





	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	
	
	
	
	
	
	
}
