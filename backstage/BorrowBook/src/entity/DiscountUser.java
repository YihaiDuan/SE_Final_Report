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

@Entity
@Table
public class DiscountUser 
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	private String userid;
	
	
	 @Column(columnDefinition="INT default 0") 
	private int status;
	
	private String createdate;
	
	private String deadline;
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  // 外键
	@JoinColumn(name = "discount_id")
	private Discount  discount;
	
	
	
	

	
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

	
	public Discount getDiscount() {
		return discount;
	}


	public void setDiscount(Discount discount) {
		this.discount = discount;
	}


	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}


	/**
	 * @return the createdate
	 */
	public String getCreatedate() {
		return createdate;
	}


	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}


	/**
	 * @return the deadline
	 */
	public String getDeadline() {
		return deadline;
	}


	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
  

	 
	
	
}
