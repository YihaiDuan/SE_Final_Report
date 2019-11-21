
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Slide 
{
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String describ;
	private String type;
	private String img;
	private String url;
	@Column(columnDefinition="INT default 0") 
	private int showstatus;
	private String date;
	
	
	
	
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getDescrib() {
		return describ;
	}
	
	public void setDescrib(String describ) {
		this.describ = describ;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getShowstatus() {
		return showstatus;
	}
	
	public void setShowstatus(int showstatus) {
		this.showstatus = showstatus;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	
	
}
