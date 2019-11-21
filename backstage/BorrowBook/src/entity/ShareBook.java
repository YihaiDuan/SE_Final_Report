package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 
 * @author hjm
 * @todo 个人图书分享实体
 */

@Entity
@Table
public class ShareBook 
{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int sharebookid;
	
    
    //图书的isbn
	private String isbn;
	
	private  String booktitle;
	
	private String author;
	
	
	private String  publish;
	
	
	
	//出版日期
	private String publishdate;
	

	
	//页数
	private String pagenum;
	
	
	//押金
	private String cash;
	
	

	
	//图书摘要
	@Column(length=10000)
	private String summary;
	
	
	
	
	//分享日期
	private String sharedate;
	
	private String bookimages;
	
	@ManyToOne
	//@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    // ���
	@JoinColumn(name ="user_id")
    
	private User user ;


	@OneToMany(targetEntity=ShareBookCollect.class)
	@JoinColumn(name="sharebookid")
   private Set<ShareBookCollect>  onlineOrder  =new HashSet<ShareBookCollect>();
	
	
	
	public int getSharebookid() {
		return sharebookid;
	}



	public void setSharebookid(int sharebookid) {
		this.sharebookid = sharebookid;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public String getBooktitle() {
		return booktitle;
	}



	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getPublish() {
		return publish;
	}



	public void setPublish(String publish) {
		this.publish = publish;
	}



	public String getPublishdate() {
		return publishdate;
	}



	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}



	public String getPagenum() {
		return pagenum;
	}



	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}



	public String getCash() {
		return cash;
	}



	public void setCash(String cash) {
		this.cash = cash;
	}



	public String getSummary() {
		return summary;
	}



	public void setSummary(String summary) {
		this.summary = summary;
	}



	public String getSharedate() {
		return sharedate;
	}



	public void setSharedate(String sharedate) {
		this.sharedate = sharedate;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getBookimages() {
		return bookimages;
	}



	public void setBookimages(String bookimages) {
		this.bookimages = bookimages;
	}
	
	
	
	
}
