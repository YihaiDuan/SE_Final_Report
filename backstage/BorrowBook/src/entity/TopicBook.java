/**
 * 
 */
package entity;

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
public class TopicBook 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  // 外键
	@JoinColumn(name = "book_id")
	private Book book;
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  // 外键
	@JoinColumn(name = "topic_id")
	private Topic  topic;

	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	

	
	public Topic getTopic() {
		return topic;
	}


	public void setTopic(Topic topic) {
		this.topic = topic;
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
  
	
	
	
	
	
	
}
