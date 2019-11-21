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
public class ConfirmOrder 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String adminname;
	private String date;
	//borroworder or returnorder
	 @Column(columnDefinition="INT default 0") 
	private int status;
	
	private String username;
	

	
	private String borrowinf;
	


    private String count;

	
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
	 * @return the borrowinf
	 */
	public String getBorrowinf() {
		return borrowinf;
	}




	/**
	 * @param borrowinf the borrowinf to set
	 */
	public void setBorrowinf(String borrowinf) {
		this.borrowinf = borrowinf;
	}




	public int getId() {
		return id;
	}



	
	public void setId(int id) {
		this.id = id;
	}



	
	public String getAdminname() {
		return adminname;
	}



	
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}



	
	public String getDate() {
		return date;
	}





	public void setDate(String date) {
		this.date = date;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}




	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}





	
	
}
