/**
 * 
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * @author Administrator
 * @date Jul 31, 2017
 * @todoTODO
 */
@Entity
@Table
public class DynamicReply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int replyid;
	
	

	
    private String otherid;
	

	private String describ;
	
	private String date;
	
	 @Column(columnDefinition="INT default 0") 
	private int  readstatus;
	
	private int dynamicid;

	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "comment_id")
	private DynamicComment comment;
	
	
	//主用户
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "userid_id")
	private User user;


	
	public int getReplyid() {
		return replyid;
	}


	
	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}


	


	
	public String getOtherid() {
		return otherid;
	}


	
	public void setOtherid(String otherid) {
		this.otherid = otherid;
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



	
	public DynamicComment getComment() {
		return comment;
	}


	
	public void setComment(DynamicComment comment) {
		this.comment = comment;
	}


	
	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	
	public int getDynamicid() {
		return dynamicid;
	}



	
	public void setDynamicid(int dynamicid) {
		this.dynamicid = dynamicid;
	}
	
	
	
	
	
	
}
