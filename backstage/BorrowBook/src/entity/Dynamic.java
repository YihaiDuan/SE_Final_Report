/**
 * 
 */
package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * @author Administrator
 * @date Jul 31, 2017
 * @todoTODO
 */
@Entity
@Table
public class Dynamic 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int dynamicid;
	
   
	@Column(length=10000)
	private String describ;
	private String images;
	private String date;
	 @Column(columnDefinition="INT default 0") 
	private int admirenum;
	
	 @Column(columnDefinition="INT default 0") 
	private int typeid;
	
	private String title;
	
	//参团id
	 @Column(columnDefinition="INT default 0") 
	private int groupmainid;
	
	
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  // 外键
	@JoinColumn(name = "book_id")
	private Book  book;
	
   
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  // 外键
	@JoinColumn(name = "user_id")
	private User  user;

	
	
	//一对多关系
	@OneToMany(targetEntity=DynamicComment.class,cascade=CascadeType.ALL)
	@JoinColumn(name="dynamic_id",updatable=false)
	
	
	private Set<DynamicComment>  dynamiccomment  =new HashSet<DynamicComment>();

	
	
	//一对多关系
	@OneToMany(targetEntity=DynamicAdmire.class,cascade=CascadeType.ALL)
	@JoinColumn(name="dynamic_id",updatable=false)
	private Set<DynamicAdmire>  dynamicadmire  =new HashSet<DynamicAdmire>();
	
	
	public int getDynamicid() {
		return dynamicid;
	}


	
	public void setDynamicid(int dynamicid) {
		this.dynamicid = dynamicid;
	}


	
	public String getDescrib() {
		return describ;
	}


	
	public void setDescrib(String describ) {
		this.describ = describ;
	}



	public String getImages() {
		return images;
	}


	
	public void setImages(String images) {
		this.images = images;
	}


	
	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}


	public int getGroupmainid() {
		return groupmainid;
	}


	
	public void setGroupmainid(int groupmainid) {
		this.groupmainid = groupmainid;
	}



	public Book getBook() {
		return book;
	}


	
	public void setBook(Book book) {
		this.book = book;
	}


	
	public User getUser() {
		return user;
	}


	
	public void setUser(User user) {
		this.user = user;
	}



	
	public int getAdmirenum() {
		return admirenum;
	}



	
	public void setAdmirenum(int admirenum) {
		this.admirenum = admirenum;
	}



	/**
	 * @return the typeid
	 */
	public int getTypeid() {
		return typeid;
	}



	/**
	 * @param typeid the typeid to set
	 */
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @return the dynamiccomment
	 */
	public Set<DynamicComment> getDynamiccomment() {
		return dynamiccomment;
	}



	/**
	 * @param dynamiccomment the dynamiccomment to set
	 */
	public void setDynamiccomment(Set<DynamicComment> dynamiccomment) {
		this.dynamiccomment = dynamiccomment;
	}



	/**
	 * @return the dynamicadmire
	 */
	public Set<DynamicAdmire> getDynamicadmire() {
		return dynamicadmire;
	}



	/**
	 * @param dynamicadmire the dynamicadmire to set
	 */
	public void setDynamicadmire(Set<DynamicAdmire> dynamicadmire) {
		this.dynamicadmire = dynamicadmire;
	}


     
	
	
	
	
	
}
