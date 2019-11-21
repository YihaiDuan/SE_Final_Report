/**
 * 
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Administrator
 * @date Aug 29, 2017
 * @todoTODO
 */

@Entity
@Table
public class UserToBooks {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String userid;
	 @Column(length=10000)
	 private String books;



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



	public String getBooks() {
		return books;
	}



	public void setBooks(String books) {
		this.books = books;
	}
	
	
	
	
	
	
	
}
