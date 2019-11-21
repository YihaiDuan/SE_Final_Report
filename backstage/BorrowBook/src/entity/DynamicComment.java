/**
 * 
 */
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


@Entity
@Table
public class DynamicComment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int commentid;
	
	
	
	private String describ;
	
	
	
    private String date;
	
	 @Column(columnDefinition="INT default 0") 
    private int readstatus;
    
	 @Column(columnDefinition="INT default 0") 
    private int admirenum;
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "dynamic_id")
	private Dynamic dynamic;
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
	//一对多关系
		@OneToMany(targetEntity=DynamicReply.class,cascade=CascadeType.ALL)
		@JoinColumn(name="comment_id",updatable=false)
		
		
		private Set<DynamicReply>  reply  =new HashSet<DynamicReply>();

		//一对多关系
		@OneToMany(targetEntity=CommentAdmire.class,cascade=CascadeType.ALL)
		@JoinColumn(name="comment_id",updatable=false)
		private Set<CommentAdmire>  commentadmire  =new HashSet<CommentAdmire>();

	
		public int getCommentid() {
			return commentid;
		}



		
		public void setCommentid(int commentid) {
			this.commentid = commentid;
		}



		
		public String getDescrib() {
			return describ;
		}



	
		public void setDescrib(String describ) {
			this.describ = describ;
		}



		public String getDate() {
			return date;
		}



		public void setDate(String date) {
			this.date = date;
		}



		
		public int getReadstatus() {
			return readstatus;
		}



		public void setReadstatus(int readstatus) {
			this.readstatus = readstatus;
		}



		public int getAdmirenum() {
			return admirenum;
		}



		public void setAdmirenum(int admirenum) {
			this.admirenum = admirenum;
		}



	
		public Dynamic getDynamic() {
			return dynamic;
		}



		public void setDynamic(Dynamic dynamic) {
			this.dynamic = dynamic;
		}



		public User getUser() {
			return user;
		}



		public void setUser(User user) {
			this.user = user;
		}



		
		public Set<DynamicReply> getReply() {
			return reply;
		}



		public void setReply(Set<DynamicReply> reply) {
			this.reply = reply;
		}
		
		
		
		
	
}
