
package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Reduce 
{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String bookid;
	private String oldprice;
	private String newprice;
	
	
	
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBookid() {
		return bookid;
	}
	
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	
	public String getOldprice() {
		return oldprice;
	}
	
	public void setOldprice(String oldprice) {
		this.oldprice = oldprice;
	}
	
	public String getNewprice() {
		return newprice;
	}

	public void setNewprice(String newprice) {
		this.newprice = newprice;
	}
	
	
	
	
	
	
}
