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
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author hjm
 * @todo 图书预订实体
 */


@Entity
@Table


public class OnlineOrder {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/* @Id
	 @GenericGenerator(name="id",strategy="uuid")
	 @GeneratedValue(generator="id")*/
	 
/*	 @GeneratedValue(generator = "id")
	 @GenericGenerator(name = "id", strategy = "uuid")*/
	
	private int id;

  
	//当书被借用了,有人还书自动推送时间可以去借书，当预约时书没有被借，直接生成取书时间
	private String date;
	
	/*
	 * 如果生成了取书时间，必须去取书，
	 * 
	 * 由推送时间data加上1天生成销毁时间
	 * 
	 * 当日期到达销毁时间，预约信息就会被销毁
	 * 
	 * */
	
	
	//指定的时间
	private String orderdate;
	
	
	//状态位
	 @Column(columnDefinition="INT default 0") 
	private int status;

	
	
	
	//表单id
	private String formid;
	
	

	//是否出库标志位
	
     @Column(name="clearstatus",columnDefinition="INT default 0")
	
	private int  clearstatus;

	
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
   @JoinColumn(name="user_id")
    
	
	
	
	private User user ;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClearstatus() {
		return clearstatus;
	}

	public void setClearstatus(int clearstatus) {
		this.clearstatus = clearstatus;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
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

/*	public int getClearstatus() {
		return clearstatus;
	}

	public void setClearstatus(int clearstatus) {
		this.clearstatus = clearstatus;
	}
	*/
	
	
	
	
	
	
	
 
	
	
	
	
	
	
	

}
