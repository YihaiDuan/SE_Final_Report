/**
 * 
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table
public class Notice 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	private String content;
	
	
	//0表示用户     //1表示管理员
	 @Column(columnDefinition="INT default 0") 
    private  int typestatus;
    
    
    private String date;
	 @Column(columnDefinition="INT default 0") 
    private int readstatus;
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}


	public int getTypestatus() {
		return typestatus;
	}


	
	public void setTypestatus(int typestatus) {
		this.typestatus = typestatus;
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
	
	
}
