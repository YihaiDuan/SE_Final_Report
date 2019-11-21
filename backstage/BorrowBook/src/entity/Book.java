package entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * @author hjm
 * @todo 图书主书录实体
 */
@Entity
@Table

public class Book 
{
	
	  @Id
	  
	  private String bookid;
	  
		
	   private String booktitle;
	   
	   
	  
	   private String chinesespelling;
	   
	   
	   private String author;
	   
	  
	   private String publish;
	 
	   
	   
	  
	   private String publishnumber;
	   
	   
	   
	    private String bookimages;
	   
	    //目录
	    @Column(length=10000)
	    private String list;
	   
	   
	    @Column(length=10000)
	   
	   private String introduce;
	 
	   
		@Column(columnDefinition="INT default 0") 
	   private int booknum;
	   
	   
		
	   private double cash;
	   
	   
	   private String oldprice;
	   
	   private String newprice;
	   
	   
	  
	
	   @Column(length=10000)
	   private String review;
	   
	   @Column(length=10000)
	   private String guidreading;
	   
	   private String position;
	   
		 @Column(columnDefinition="INT default 0") 
	   private int totlenum;
	   
		 
		 @Column(columnDefinition="INT default 0") 
	   private int borrownum;
	   
		 
		 @Column(columnDefinition="INT default 0") 
	   private int remainnum;
	   
	 
	   
	   private String evaluatescore;
	   
	   
	   //是否有电子书
		 @Column(columnDefinition="INT default 0") 
	   private int elestatus;
	   
	   //是否免费
		 @Column(columnDefinition="INT default 0") 
	   private int freestatus;
        
       //电子书价格
	   private  Double eleprice;
	   
	   
	   //对应电子书textname
	   private  String eletext;
	   
	   //vip免费
		 @Column(columnDefinition="INT default 0") 
	   private int vipfreestatus;
	   
	    //书摘
	   @Column(length=10000)
       private  String bookmain;
	   
	   
	   //图书关键词
	   @Column(length=10000)
	   private  String  bookkey;
	   
	   
	   //价比推荐
	   @Column(length=10000)
	   private  String  compareprice;   
	   
	   
	   
	   

	public String getPosition() {
		return position;
	}


	
	public void setPosition(String position) {
		this.position = position;
	}

	
	@OneToMany(targetEntity=MyEleBook.class,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id",updatable=false)
   private Set<MyEleBook>  myEleBook =new HashSet<MyEleBook>();

	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  // 外键
	@JoinColumn(name = "category_id")
	private Category  category;
  

	
	//书目和具体书本的关
@OneToMany(targetEntity=BookSon.class,cascade=CascadeType.ALL)
 @JoinColumn(name="book_id")
  private Set<BookSon>  bookson  =new HashSet<BookSon>();
	
	
	@OneToMany(targetEntity=Collect.class,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id",updatable=false)
   private Set<Collect>  collect  =new HashSet<Collect>();
	
	
	@OneToMany(targetEntity=ReferTable.class,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id",updatable=false)
   private Set<ReferTable>  referTable =new HashSet<ReferTable>();
	
	
	@OneToMany(targetEntity=Comment.class,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id",updatable=false)
   private Set<Comment>  comment =new HashSet<Comment>();

	
	@OneToMany(targetEntity=OnlineWarn.class,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id",updatable=false)
   private Set<OnlineWarn>  onlineWarn =new HashSet<OnlineWarn>();
	
	@OneToMany(targetEntity=GroupBook.class,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id",updatable=false)
   private Set<GroupBook>  groupbook =new HashSet<GroupBook>();
	
	

	@OneToMany(targetEntity=TopicBook.class,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id",updatable=false)
   private Set<TopicBook>  topicbook =new HashSet<TopicBook>();
	
	@OneToMany(targetEntity=Dynamic.class,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id",updatable=false)
   private Set<Dynamic>  dynamic =new HashSet<Dynamic>();
	
	
	@OneToMany(targetEntity=ReadHistroy.class,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id",updatable=false)
	  private Set<ReadHistroy> readhistroy=new HashSet<ReadHistroy>();
	
	
	@OneToMany(targetEntity=UserSearch.class,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id",updatable=false)
	  private Set<UserSearch> userSearch=new HashSet<UserSearch>();
	
	@OneToMany(targetEntity=UserBrowse.class,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id",updatable=false)
	  private Set<UserBrowse> userBorowse=new HashSet<UserBrowse>();
	
	
	
	@OneToOne(mappedBy = "book",cascade=CascadeType.ALL)
	private HotSearch hotsearch;
	
	
	
	
	public String getBookid() {
		return bookid;
	}


	public void setBookid(String bookid) {
		this.bookid = bookid;
	}


	public String getBooktitle() {
		return booktitle;
	}


	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}


	public String getChinesespelling() {
		return chinesespelling;
	}


	public void setChinesespelling(String chinesespelling) {
		this.chinesespelling = chinesespelling;
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


	public String getPublishnumber() {
		return publishnumber;
	}


	public void setPublishnumber(String publishnumber) {
		this.publishnumber = publishnumber;
	}


	public String getBookimages() {
		return bookimages;
	}


	public void setBookimages(String bookimages) {
		this.bookimages = bookimages;
	}


	public String getList() {
		return list;
	}


	public void setList(String list) {
		this.list = list;
	}


	public String getIntroduce() {
		return introduce;
	}


	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}


	public int getBooknum() {
		return booknum;
	}


	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}




	/**
	 * @return the cash
	 */
	public double getCash() {
		return cash;
	}



	/**
	 * @param cash the cash to set
	 */
	public void setCash(double cash) {
		this.cash = cash;
	}



	/**
	 * @return the userSearch
	 */
	public Set<UserSearch> getUserSearch() {
		return userSearch;
	}



	/**
	 * @param userSearch the userSearch to set
	 */
	public void setUserSearch(Set<UserSearch> userSearch) {
		this.userSearch = userSearch;
	}



	/**
	 * @return the userBorowse
	 */
	public Set<UserBrowse> getUserBorowse() {
		return userBorowse;
	}



	/**
	 * @param userBorowse the userBorowse to set
	 */
	public void setUserBorowse(Set<UserBrowse> userBorowse) {
		this.userBorowse = userBorowse;
	}



	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	public String getGuidreading() {
		return guidreading;
	}


	public void setGuidreading(String guidreading) {
		this.guidreading = guidreading;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Set<BookSon> getBookson() {
		return bookson;
	}


	public void setBookson(Set<BookSon> bookson) {
		this.bookson = bookson;
	}


	public Set<Collect> getCollect() {
		return collect;
	}


	public void setCollect(Set<Collect> collect) {
		this.collect = collect;
	}


	public Set<ReferTable> getReferTable() {
		return referTable;
	}


	public void setReferTable(Set<ReferTable> referTable) {
		this.referTable = referTable;
	}


	public Set<Comment> getComment() {
		return comment;
	}


	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}


	


	public Set<OnlineWarn> getOnlineWarn() {
		return onlineWarn;
	}


	public void setOnlineWarn(Set<OnlineWarn> onlineWarn) {
		this.onlineWarn = onlineWarn;
	}




	
	public int getTotlenum() {
		return totlenum;
	}



	public void setTotlenum(int totlenum) {
		this.totlenum = totlenum;
	}


	
	public int getBorrownum() {
		return borrownum;
	}


	
	public void setBorrownum(int borrownum) {
		this.borrownum = borrownum;
	}


	
	public int getRemainnum() {
		return remainnum;
	}



	public void setRemainnum(int remainnum) {
		this.remainnum = remainnum;
	}



	public String getOldprice() {
		return oldprice;
	}



	public void setOldprice(String oldprice) {
		this.oldprice = oldprice;
	}




	public String getNewprice() {
		return newprice;
	}




	public void setNewprice(String newprice) {
		this.newprice = newprice;
	}



	
	public String getEvaluatescore() {
		return evaluatescore;
	}



	
	public void setEvaluatescore(String evaluatescore) {
		this.evaluatescore = evaluatescore;
	}









	public Set<GroupBook> getGroupbook() {
		return groupbook;
	}



	
	public void setGroupbook(Set<GroupBook> groupbook) {
		this.groupbook = groupbook;
	}



	





	public Set<TopicBook> getTopicbook() {
		return topicbook;
	}



	public void setTopicbook(Set<TopicBook> topicbook) {
		this.topicbook = topicbook;
	}



	public int getElestatus() {
		return elestatus;
	}



	public void setElestatus(int elestatus) {
		this.elestatus = elestatus;
	}



	
	public int getFreestatus() {
		return freestatus;
	}



	public void setFreestatus(int freestatus) {
		this.freestatus = freestatus;
	}


	public Double getEleprice() {
		return eleprice;
	}



	
	public void setEleprice(Double eleprice) {
		this.eleprice = eleprice;
	}




	public String getEletext() {
		return eletext;
	}



	public void setEletext(String eletext) {
		this.eletext = eletext;
	}



	
	public int getVipfreestatus() {
		return vipfreestatus;
	}



	
	public void setVipfreestatus(int vipfreestatus) {
		this.vipfreestatus = vipfreestatus;
	}



	

	public Set<Dynamic> getDynamic() {
		return dynamic;
	}



	
	public void setDynamic(Set<Dynamic> dynamic) {
		this.dynamic = dynamic;
	}



	
	public String getBookmain() {
		return bookmain;
	}



	public void setBookmain(String bookmain) {
		this.bookmain = bookmain;
	}



	


	/**
	 * @return the bookkey
	 */
	public String getBookkey() {
		return bookkey;
	}



	/**
	 * @param bookkey the bookkey to set
	 */
	public void setBookkey(String bookkey) {
		this.bookkey = bookkey;
	}



	/**
	 * @return the compareprice
	 */
	public String getCompareprice() {
		return compareprice;
	}



	/**
	 * @param compareprice the compareprice to set
	 */
	public void setCompareprice(String compareprice) {
		this.compareprice = compareprice;
	}



	/**
	 * @return the myEleBook
	 */
	public Set<MyEleBook> getMyEleBook() {
		return myEleBook;
	}



	/**
	 * @param myEleBook the myEleBook to set
	 */
	public void setMyEleBook(Set<MyEleBook> myEleBook) {
		this.myEleBook = myEleBook;
	}



	
	public Set<ReadHistroy> getReadhistroy() {
		return readhistroy;
	}



	
	public void setReadhistroy(Set<ReadHistroy> readhistroy) {
		this.readhistroy = readhistroy;
	}



	
	public HotSearch getHotsearch() {
		return hotsearch;
	}



	
	public void setHotsearch(HotSearch hotsearch) {
		this.hotsearch = hotsearch;
	}
   
	
	
	
   

}