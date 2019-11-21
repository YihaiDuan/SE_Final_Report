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
 * @todo 评论点赞实体
 */
@Entity
@Table
public class Admire 
{
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	private String userid;
	
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "commentid")
	private Comment comment;
	

	
	
	
	
	
	
	
	

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

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}


	
	
	
	
	

}
