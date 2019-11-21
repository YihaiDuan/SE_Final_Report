
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
public class Fan {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "user_id")
	private User user;
	
	
	private String otherid;


	public int getId() {
		return id;
	}


	
	public void setId(int id) {
		this.id = id;
	}


	

	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}



	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}



	public String getOtherid() {
		return otherid;
	}


	
	public void setOtherid(String otherid) {
		this.otherid = otherid;
	}
	
	
	
}
