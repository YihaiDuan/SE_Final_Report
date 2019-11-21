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
 * 
 * @author hjm
 * @todo 回复评论实体
 */

//回复实体
/*
 * 
 * select * from reply where commentid in (select commentid from commemt where userid=1)
 * 用sql语句查看谁回复了你，你回复了谁
 * 
 * 
 * SELECT r.*  from commemt  c , reply  r  where  c.commentid = r.commentid  and c.userid=1
 * hql语句
 * 
 * 
 * 
 * */

@Entity
@Table


public class Reply 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int replyid;
	
	
	//哪本书下的回复
	private String  bookid;
	
    private String otherid;
	
	
	private String userid;
	
	private String nickname;
	
	
	private String replycontent;
	
	private String replydate;
	
	@Column(columnDefinition="INT default 0") 
	private int readstatus;
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "commentid")
	private Comment comment;
	
	
	

	/**
	 * @return the readstatus
	 */
	public int getReadstatus() {
		return readstatus;
	}

	/**
	 * @param readstatus the readstatus to set
	 */
	public void setReadstatus(int readstatus) {
		this.readstatus = readstatus;
	}

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

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getReplycontent() {
		return replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}

	public String getReplydate() {
		return replydate;
	}

	public void setReplydate(String replydate) {
		this.replydate = replydate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
	
	
	

}
