
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
public class OutPay 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private double cash;
	
	   @Column(columnDefinition="INT default 0")
	private int readstatus;
	
	
	private String fromdes;
	
	
	private String date;
	
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	// 外键
	@JoinColumn(name = "user_id")
	private User  user;



	
	public int getId() {
		return id;
	}



	
	public void setId(int id) {
		this.id = id;
	}



	


	/**
	 * @return the cash
	 */
	public double getCash() {
		return cash;
	}




	/**
	 * @param cash the cash to set
	 */
	public void setCash(double cash) {
		this.cash = cash;
	}




	public int getReadstatus() {
		return readstatus;
	}



	public void setReadstatus(int readstatus) {
		this.readstatus = readstatus;
	}



	
	public String getFromdes() {
		return fromdes;
	}



	public void setFromdes(String fromdes) {
		this.fromdes = fromdes;
	}



	
	public String getDate() {
		return date;
	}



	
	public void setDate(String date) {
		this.date = date;
	}



	
	public User getUser() {
		return user;
	}



	
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
}
