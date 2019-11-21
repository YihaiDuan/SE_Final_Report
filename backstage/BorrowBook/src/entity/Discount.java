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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class Discount
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	//打折金额   代金金额
    private String discount;
	 @Column(columnDefinition="INT default 0") 
    private int num;
    //0表示活动优惠劵    1表示积分优惠劵
	 @Column(columnDefinition="INT default 0") 
    private int status;
      //0 表示不显示   1表示活动开启显示  2表示活动结束显示
	 @Column(columnDefinition="INT default 0") 
    private int showstatus;
      //优惠劵描述
    private String typename;
	private String createdate;
	private String deadline;
	
	//0普通用户可以领取    1领取  为vip可以领取
	 @Column(columnDefinition="INT default 0") 
	private int gradestatus;
	
	
	//0表示打折  1表示代金券1
	 @Column(columnDefinition="INT default 0") 
	private int typestatus;

	
	
	
	@OneToMany(targetEntity=DiscountUser.class,cascade=CascadeType.ALL)
	@JoinColumn(name="discount_id",updatable=false)
	
	private Set<DiscountUser>  discountuser =new HashSet<DiscountUser>();
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public String getCreatedate() {
		return createdate;
	}
	
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	public String getDeadline() {
		return deadline;
	}
	
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	

	


	
	/**
	 * @return the showstatus
	 */
	public int getShowstatus() {
		return showstatus;
	}

	/**
	 * @param showstatus the showstatus to set
	 */
	public void setShowstatus(int showstatus) {
		this.showstatus = showstatus;
	}

	public Set<DiscountUser> getDiscountuser() {
		return discountuser;
	}

	
	public void setDiscountuser(Set<DiscountUser> discountuser) {
		this.discountuser = discountuser;
	}

	
	public String getDiscount() {
		return discount;
	}


	public void setDiscount(String discount) {
		this.discount = discount;
	}

	
	public int getNum() {
		return num;
	}

	
	public void setNum(int num) {
		this.num = num;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}


	public int getGradestatus() {
		return gradestatus;
	}

	
	public void setGradestatus(int gradestatus) {
		this.gradestatus = gradestatus;
	}

	public int getTypestatus() {
		return typestatus;
	}


	public void setTypestatus(int typestatus) {
		this.typestatus = typestatus;
	}


	
}
