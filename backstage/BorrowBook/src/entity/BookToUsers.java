
package entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BookToUsers {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String bookid;
	  @Column(length=10000)
	 private String users;



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




	public String getUsers() {
		return users;
	}





	public void setUsers(String users) {
		this.users = users;
	}



	
	
}
