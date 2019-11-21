package bean;
/**
 * 相似度高的前几个书的id
 * @author Monly_P
 *
 */
public class BookTags {

	private int id;
	private int bookId;
	private String tags;
	
	public BookTags(int bookId, String tags) {
		super();
		this.bookId = bookId;
		this.tags = tags;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
	
}
