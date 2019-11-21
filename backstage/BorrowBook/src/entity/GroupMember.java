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

/**
 * @author Administrator
 * @date Jul 27, 2017
 * @todoTODO
 */
@Entity
@Table
public class GroupMember {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String userid;
	private String bookid;
	private String booksonid;
	private String date;
	private String paymoney;
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  // 外键
	@JoinColumn(name = "groupmain_id")
	private GroupMain groupmain;

	
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

	
	public String getBookid() {
		return bookid;
	}

	
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	
	public GroupMain getGroupmain() {
		return groupmain;
	}

	
	public void setGroupmain(GroupMain groupmain) {
		this.groupmain = groupmain;
	}


	public String getPaymoney() {
		return paymoney;
	}


	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}


	
	public String getBooksonid() {
		return booksonid;
	}


	
	public void setBooksonid(String booksonid) {
		this.booksonid = booksonid;
	}
	

	
}
