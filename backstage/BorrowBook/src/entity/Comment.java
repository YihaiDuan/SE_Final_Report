package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * @author hjm
 * @todo 图书评论实体
 */

@Entity
@Table
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int commentid;
	
	private String  userid;
	
	private String nickname;
	
	
	
	private String content;
	
	
	private String  evaluate;
	
	
	 @Column(columnDefinition="INT default 0") 
	private int evaluatestatus;
	
	
    private String commentdate;
	
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "book_id")
	private Book book;
	
	
	
	
	
	//一对多关系
		@OneToMany(targetEntity=Reply.class,cascade=CascadeType.ALL)
		@JoinColumn(name="commentid",updatable=false)
		
		
		private Set<Reply>  reply  =new HashSet<Reply>();
	
	
	
		//一对多关系
		@OneToMany(targetEntity=Admire.class,cascade=CascadeType.ALL)
		@JoinColumn(name="commentid",updatable=false)
				
				
		private Set<Admire>  admire =new HashSet<Admire>();
	
	

	public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}

		public Set<Reply> getReply() {
			return reply;
		}

		public void setReply(Set<Reply> reply) {
			this.reply = reply;
		}

		public Set<Admire> getAdmire() {
			return admire;
		}

		public void setAdmire(Set<Admire> admire) {
			this.admire = admire;
		}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	

	public String getNickname() {
		return nickname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCommentdate() {
		return commentdate;
	}

	public void setCommentdate(String commentdate) {
		this.commentdate = commentdate;
	}

	/**
	 * @return the evaluate
	 */
	public String getEvaluate() {
		return evaluate;
	}

	/**
	 * @param evaluate the evaluate to set
	 */
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	/**
	 * @return the evaluatestatus
	 */
	public int getEvaluatestatus() {
		return evaluatestatus;
	}

	/**
	 * @param evaluatestatus the evaluatestatus to set
	 */
	public void setEvaluatestatus(int evaluatestatus) {
		this.evaluatestatus = evaluatestatus;
	}


}
