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
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author hjm
 * @todo 借阅图书实体
 */


@Entity
@Table
public class BorrowTable 
{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	
   private int id;
	
 
  
    private String warndate;
    
  
 
    private String deadline;
    
    
    
 
    private  String borrowdate;
    
    
    
    private String  returndate;
    
    
    
	 @Column(columnDefinition="INT default 0") 
    private int status;
    
    
	 @Column(columnDefinition="INT default 0") 
    private int scanstatus;
    
    
	 @Column(columnDefinition="INT default 0") 
    private int returnstatus;
    
     
    
     private String formid;
    
  
     
    private String deadborrow;
    
	 @Column(columnDefinition="INT default 0") 
    private int deadstatus;
    
	 @Column(columnDefinition="INT default 0") 
    private int evaluatestatus;
    
	 @Column(columnDefinition="INT default 0") 
    private int typestatus;
    
    private String paymoney;
	
    private String count;
    
	 @Column(columnDefinition="INT default 0") 
    private int renewstatus;
    
    /*
     * 多对一
     * 借阅表和图书关系
     * 
     * */
	
	@ManyToOne
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    // 外键
	
	@JoinColumn(name ="bookson_id")
    
	private BookSon bookson;
 
    /*
     * 
     * 多对一关系
     * 借阅表和图书关系
     * 
     * 
     * */
	
	@ManyToOne     
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    // 外键
	@JoinColumn(name ="user_id")
	
	private User user ;
	

	@OneToMany(targetEntity=ApplyNear.class,cascade=CascadeType.ALL)
	@JoinColumn(name="borrowid",updatable=false)
    
	private Set<ApplyNear>  applynear =new HashSet<ApplyNear>();

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(int returnstatus) {
		this.returnstatus = returnstatus;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public int getDeadstatus() {
		return deadstatus;
	}

	public void setDeadstatus(int deadstatus) {
		this.deadstatus = deadstatus;
	}

	public String getWarndate() {
		return warndate;
	}

	public void setWarndate(String warndate) {
		this.warndate = warndate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getBorrowdate() {
		return borrowdate;
	}

	public void setBorrowdate(String borrowdate) {
		this.borrowdate = borrowdate;
	}

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}



	public BookSon getBookson() {
		return bookson;
	}

	public void setBookson(BookSon bookson) {
		this.bookson = bookson;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public int getScanstatus() {
		return scanstatus;
	}

	public void setScanstatus(int scanstatus) {
		this.scanstatus = scanstatus;
	}

public String getDeadborrow() {
		return deadborrow;
	}

	public void setDeadborrow(String deadborrow) {
		this.deadborrow = deadborrow;
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

	/**
	 * @return the typestatus
	 */
	public int getTypestatus() {
		return typestatus;
	}

	/**
	 * @param typestatus the typestatus to set
	 */
	public void setTypestatus(int typestatus) {
		this.typestatus = typestatus;
	}

	/**
	 * @return the paymoney
	 */
	public String getPaymoney() {
		return paymoney;
	}

	/**
	 * @param paymoney the paymoney to set
	 */
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}

	/**
	 * @return the applynear
	 */
	public Set<ApplyNear> getApplynear() {
		return applynear;
	}

	/**
	 * @param applynear the applynear to set
	 */
	public void setApplynear(Set<ApplyNear> applynear) {
		this.applynear = applynear;
	}

	/**
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * @return the renewstatus
	 */
	public int getRenewstatus() {
		return renewstatus;
	}

	/**
	 * @param renewstatus the renewstatus to set
	 */
	public void setRenewstatus(int renewstatus) {
		this.renewstatus = renewstatus;
	}
 
	
    

}
