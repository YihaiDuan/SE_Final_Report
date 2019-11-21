
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
public class GroupMain {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String Userid;
	private String booksonid;
	private String bookid;
	private String Date;
	 @Column(columnDefinition="INT default 0") 
	private int status;
	private String paymoney;
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  // 外键
	@JoinColumn(name = "groupbook_id")
	private GroupBook groupbook;

	
	@OneToMany(targetEntity=GroupMember.class,cascade=CascadeType.ALL)
	 @JoinColumn(name="groupmain_id")
	  private Set<GroupMember>  groupmember  =new HashSet<GroupMember>();

	
	public int getId() {
		return id;
	}


	
	public void setId(int id) {
		this.id = id;
	}


	
	public String getUserid() {
		return Userid;
	}


	
	public void setUserid(String userid) {
		Userid = userid;
	}


	
	public String getBooksonid() {
		return booksonid;
	}


	
	public void setBooksonid(String booksonid) {
		this.booksonid = booksonid;
	}


	
	public String getDate() {
		return Date;
	}


	public void setDate(String date) {
		Date = date;
	}


	public String getBookid() {
		return bookid;
	}


	
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}


	
	public GroupBook getGroupbook() {
		return groupbook;
	}


	
	public void setGroupbook(GroupBook groupbook) {
		this.groupbook = groupbook;
	}


	
	public Set<GroupMember> getGroupmember() {
		return groupmember;
	}


	
	public void setGroupmember(Set<GroupMember> groupmember) {
		this.groupmember = groupmember;
	}



	public int getStatus() {
		return status;
	}


	
	public void setStatus(int status) {
		this.status = status;
	}




	public String getPaymoney() {
		return paymoney;
	}



	
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}




	
	
	
	
}
