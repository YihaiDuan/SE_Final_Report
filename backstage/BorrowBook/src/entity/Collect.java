package entity;

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
 * @todo 图书收藏实体
 */

@Entity
@Table
public class Collect {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	@ManyToOne
    @Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    @JoinColumn(name ="book_id")
    
	private Book book;
	
    private String userid;

	
	
	
	
	
	
	

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


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
	
	

}
