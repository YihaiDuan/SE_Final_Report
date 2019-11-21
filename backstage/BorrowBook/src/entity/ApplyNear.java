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
 * @todo 附近的人申请转借实体
 */
@Entity
@Table
public class ApplyNear 
{
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private int id;
	  
	  
	    @ManyToOne     
		@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	    // 外键
		@JoinColumn(name ="user_id")
	    private User user;
	  
	  
	    
	  
	   private String otherid;
	  
	  
	    @ManyToOne     
		@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	    // 外键
		@JoinColumn(name ="borrowid")
	  
	  
	  private BorrowTable borrowtable;
	  
	  
	  
	    //申请状态位
	  private int status;
	  
	  //未读状态位
	  /*
	   * 0表示还没有读取
	   * 1表示已经读取
	   * 
	   * */
	  private int readstatus;
	  
	  
	  /*
	   * 0表示初始状态
	   * 1表示还未读
	   * 2表示已经读了
	   * */
	  //操作未读状态位
	  
	  
	@Column(columnDefinition="INT default 0") 
	  private int dostatus;
	  
	  
	  private String personQR;
	  
      private String date;
      
      
      
      
      
      

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOtherid() {
		return otherid;
	}

	public void setOtherid(String otherid) {
		this.otherid = otherid;
	}

	public BorrowTable getBorrowtable() {
		return borrowtable;
	}

	public void setBorrowtable(BorrowTable borrowtable) {
		this.borrowtable = borrowtable;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPersonQR() {
		return personQR;
	}

	public void setPersonQR(String personQR) {
		this.personQR = personQR;
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

	public int getDostatus() {
		return dostatus;
	}

	public void setDostatus(int dostatus) {
		this.dostatus = dostatus;
	}

  
}
