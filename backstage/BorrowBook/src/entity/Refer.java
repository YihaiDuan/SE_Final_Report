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
 * @todo 图书推荐---用户点击数记录类
 */


@Entity
@Table
public class Refer 
{
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	private String userid;
	

	
	@ManyToOne
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    @JoinColumn(name ="category_id")
    
	private Category category;
	

	
	
	
	//������ĵ����
	@Column(columnDefinition="INT default 0") 
	private int num;
	

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	

}
