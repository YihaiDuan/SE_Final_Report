
package entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BookToBooks {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String bookid;
	  @Column(length=10000)
	 private String books;



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




	public String getBooks() {
		return books;
	}




	
	public void setBooks(String books) {
		this.books = books;
	}






	
	
}
