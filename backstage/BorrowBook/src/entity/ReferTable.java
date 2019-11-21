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
 * @todo 图书推荐实体
 */

@Entity
@Table
public class ReferTable 
{
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String userid;
	
	
	 private String date;

		
	//是否已经读了,读了为1，没有读为0
	@Column(columnDefinition="INT default 0") 
    private int status;
	
	@ManyToOne
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinColumn(name ="book_id")
    
	private Book book;
	
   

	
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
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


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}



	
	
	

}
