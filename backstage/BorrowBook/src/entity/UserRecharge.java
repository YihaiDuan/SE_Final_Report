package entity;

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
public class UserRecharge {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	  private Integer id;
	  private String time;
	  private double price;
	  
	  
	  
	  
	  @ManyToOne(targetEntity=User.class)
	  @Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	  @JoinColumn(name = "user_id")
	  private User user;
	  
	  
	  
	  
	  
	  
	public UserRecharge(Integer id, String time, double price, User user) {
		super();
		this.id = id;
		this.time = time;
		this.price = price;
		this.user = user;
	}
	public UserRecharge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "UserRecharge [id=" + id + ", time=" + time + ", price=" + price
				+ ", user=" + user + "]";
	}
	  
	
}
