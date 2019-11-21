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




/**
 * 
 * @author hjm
 * @todo 图书预订提醒实体
 */

@Entity
@Table
public class OnlineWarn 
{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String formid;
	
	
	   @Column(columnDefinition="INT default 0")
	private int status;
	
	 /*
     * 多对一
     * 借阅表和图书关系
     * 
     * */
	
	@ManyToOne
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    // 外键
	@JoinColumn(name ="book_id")
    
	private Book book;
 
    /*
     * 
     * 多对一关系
     * 借阅表和图书关系
     * 
     * 
     * */

	@ManyToOne
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    // 外键
   @JoinColumn(name="user_id")
    
	private User user ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

}
