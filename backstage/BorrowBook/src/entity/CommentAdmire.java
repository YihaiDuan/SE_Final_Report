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
public class CommentAdmire 
{

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "comment_id")
	private DynamicComment comment;




	
	public int getId() {
		return id;
	}




	
	public void setId(int id) {
		this.id = id;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public DynamicComment getComment() {
		return comment;
	}




	
	public void setComment(DynamicComment comment) {
		this.comment = comment;
	}

	
	
}
