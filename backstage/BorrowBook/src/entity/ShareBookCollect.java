package entity;

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
 * @todo 个人图书收藏实体
 */

@Entity
@Table
public class ShareBookCollect 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private int id;
	
	
	
	@ManyToOne
    @Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })

	@JoinColumn(name ="sharebookid")
    
	private ShareBook sharebook ;
	
	
	
	
	@ManyToOne
    //@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    // ���
	@JoinColumn(name ="user_id")
    
	private User user ;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}






	public ShareBook getSharebook() {
		return sharebook;
	}



	public void setSharebook(ShareBook sharebook) {
		this.sharebook = sharebook;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
