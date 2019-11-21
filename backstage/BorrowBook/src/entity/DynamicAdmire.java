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

@Entity
@Table
public class DynamicAdmire 
{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
   // 外键
	@JoinColumn(name = "dynamic_id")
	private Dynamic dynamic;




	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
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




	/**
	 * @return the dynamic
	 */
	public Dynamic getDynamic() {
		return dynamic;
	}




	/**
	 * @param dynamic the dynamic to set
	 */
	public void setDynamic(Dynamic dynamic) {
		this.dynamic = dynamic;
	}
	
	
	
	
	
	
	
	
	
}
