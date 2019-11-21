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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * 
 * @author hjm
 * @todo 用户实体
 */

@Entity
@Table

public class User {
	
	
/*
 * 
 * 用户账号
 * 用户昵称
 * 用户密码
 * 用户性别
 * 用户爱好
 * 用户e_mail
 * 用户手机号
 * 
 * */

	@Id
	private String userid;
	
	
	
	
	private String nickname;
	
	private String password;
	
	
	private String userimages;
	
	private String sex;
	
	@Column(columnDefinition="INT default 0") 
	private int maxnumber;

	
	private String e_mail;
	private String phone;
	private String openid;
	
	//是否进行还书提醒
	@Column(columnDefinition="INT default 0") 
	private int  borrowwarnstatus;
	
	//是否进行推荐提醒
	@Column(columnDefinition="INT default 0") 
	private int referstatus;
	
	
	//用户设置的提醒频率值
	@Column(columnDefinition="INT default 0") 
	private  int p;
	

	//纬度
	private  String latitude;
	
	
	//经度
	private String longitude;
	
	
	//个人二维码
	private String personQR;
	
	
	//用户全权
	@Column(columnDefinition="INT default 0") 
	private int jurisdiction;
	
	
	
	//用户积分
	@Column(columnDefinition="INT default 0") 
	   private int score;
	   
	   
	   
	   //用户借阅钱币
	   private String money;
	   
	   
	   //签到是否过期
	   @Column(columnDefinition="INT default 0") 
	   private int sign;
	   
	  
	   
	   //充值是否过期
	   @Column(columnDefinition="INT default 0") 
	   private int paystatus;
	   
	   //等级
	   @Column(columnDefinition="INT default 0") 
	   private int grade;
	   
	   //vip过期时间
	   private String deadline;
	   
	   //用户注册时间
	   private String starttime;
	   
	   
	   //用户背景强
	   private String backimg;
	   
	   
	   //用户个性签名
	   private  String nicesign;
	   
	
	@OneToMany(targetEntity=BorrowTable.class)
	@JoinColumn(name="user_id")
   private Set<BorrowTable>  borrowtable  =new HashSet<BorrowTable>();
	
	
	
	@OneToMany(targetEntity=OnlineOrder.class)
	@JoinColumn(name="user_id")
   private Set<OnlineOrder>  onlineOrder  =new HashSet<OnlineOrder>();


	@OneToMany(targetEntity=OnlineWarn.class,cascade=CascadeType.ALL)
	@JoinColumn(name="user_id",updatable=false)
   private Set<OnlineWarn>  onlineWarn =new HashSet<OnlineWarn>();
	
	
	@OneToMany(targetEntity=Dynamic.class,cascade=CascadeType.ALL)
	@JoinColumn(name="user_id",updatable=false)
   private Set<Dynamic>  dynamic =new HashSet<Dynamic>();
	
	//一对多关系
	@OneToMany(targetEntity=DynamicComment.class,cascade=CascadeType.ALL)
	@JoinColumn(name="user_id",updatable=false)
	
	
	private Set<DynamicComment>  dynamiccomment  =new HashSet<DynamicComment>();
	
	
	//一对多关系
		@OneToMany(targetEntity=DynamicAdmire.class,cascade=CascadeType.ALL)
		@JoinColumn(name="user_id",updatable=false)
		private Set<DynamicAdmire>  dynamicadmire  =new HashSet<DynamicAdmire>();
		
		
		//一对多关系
				@OneToMany(targetEntity=CommentAdmire.class,cascade=CascadeType.ALL)
				@JoinColumn(name="user_id",updatable=false)
				private Set<CommentAdmire>  commentadmire  =new HashSet<CommentAdmire>();
				
				
		//一对多关系
		@OneToMany(targetEntity=Fan.class,cascade=CascadeType.ALL)
		@JoinColumn(name="user_id",updatable=false)
		private Set<Fan>  fan  =new HashSet<Fan>();
		
		
		
//		@OneToMany(cascade=CascadeType.ALL)
//		@JoinColumn(name="user_id",updatable=false)
//		
//          private Set<MyEleBook> myelebook=new HashSet<MyEleBook>();
//		
		@OneToMany(cascade=CascadeType.ALL)
		@JoinColumn(name="user_id",updatable=false)
		
          private Set<ReadHistroy> readhistroy=new HashSet<ReadHistroy>();
		
		
		@OneToMany(cascade=CascadeType.ALL)
		@JoinColumn(name="user_id",updatable=false)
		
          private Set<OutPay> outpay=new HashSet<OutPay>();
		
		
		
		@OneToMany(cascade=CascadeType.ALL)
		@JoinColumn(name="user_id",updatable=false)
		
          private Set<UserRecharge> userrecharge=new HashSet<UserRecharge>();
		
		
		@OneToMany(cascade=CascadeType.ALL)
		@JoinColumn(name="user_id",updatable=false)
		
          private Set<UserVipRecharge> userviprecharge=new HashSet<UserVipRecharge>();
		
		

		@OneToMany(targetEntity=UserSearch.class,cascade=CascadeType.ALL)
		@JoinColumn(name="user_id",updatable=false)
		  private Set<UserSearch> userSearch=new HashSet<UserSearch>();
		
		@OneToMany(targetEntity=UserBrowse.class,cascade=CascadeType.ALL)
		@JoinColumn(name="user_id",updatable=false)
		  private Set<UserBrowse> userBorowse=new HashSet<UserBrowse>();


	public int getBorrowwarnstatus() {
		return borrowwarnstatus;
	}



	public void setBorrowwarnstatus(int borrowwarnstatus) {
		this.borrowwarnstatus = borrowwarnstatus;
	}



	public int getReferstatus() {
		return referstatus;
	}



	public void setReferstatus(int referstatus) {
		this.referstatus = referstatus;
	}



	public int getP() {
		return p;
	}



	public void setP(int p) {
		this.p = p;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getUserimages() {
		return userimages;
	}



	public void setUserimages(String userimages) {
		this.userimages = userimages;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public int getMaxnumber() {
		return maxnumber;
	}



	public void setMaxnumber(int maxnumber) {
		this.maxnumber = maxnumber;
	}



	public String getE_mail() {
		return e_mail;
	}



	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Set<BorrowTable> getBorrowtable() {
		return borrowtable;
	}



	public void setBorrowtable(Set<BorrowTable> borrowtable) {
		this.borrowtable = borrowtable;
	}



	public Set<OnlineOrder> getOnlineOrder() {
		return onlineOrder;
	}



	public void setOnlineOrder(Set<OnlineOrder> onlineOrder) {
		this.onlineOrder = onlineOrder;
	}



	public String getOpenid() {
		return openid;
	}



	public void setOpenid(String openid) {
		this.openid = openid;
	}



	public String getLatitude() {
		return latitude;
	}



	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}



	public String getLongitude() {
		return longitude;
	}



	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}



	public String getPersonQR() {
		return personQR;
	}



	public void setPersonQR(String personQR) {
		this.personQR = personQR;
	}



	public int getJurisdiction() {
		return jurisdiction;
	}



	public void setJurisdiction(int jurisdiction) {
		this.jurisdiction = jurisdiction;
	}



	public Set<OnlineWarn> getOnlineWarn() {
		return onlineWarn;
	}



	public void setOnlineWarn(Set<OnlineWarn> onlineWarn) {
		this.onlineWarn = onlineWarn;
	}




	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}



	public String getMoney() {
		return money;
	}




	public void setMoney(String money) {
		this.money = money;
	}




	public int getSign() {
		return sign;
	}



	public void setSign(int sign) {
		this.sign = sign;
	}



	
	public int getPaystatus() {
		return paystatus;
	}



	
	public void setPaystatus(int paystatus) {
		this.paystatus = paystatus;
	}



	public int getGrade() {
		return grade;
	}



	public void setGrade(int grade) {
		this.grade = grade;
	}



	public Set<Dynamic> getDynamic() {
		return dynamic;
	}



	
	public void setDynamic(Set<Dynamic> dynamic) {
		this.dynamic = dynamic;
	}



	
	public Set<DynamicComment> getDynamiccomment() {
		return dynamiccomment;
	}



	public void setDynamiccomment(Set<DynamicComment> dynamiccomment) {
		this.dynamiccomment = dynamiccomment;
	}



	public Set<DynamicAdmire> getDynamicadmire() {
		return dynamicadmire;
	}



	public void setDynamicadmire(Set<DynamicAdmire> dynamicadmire) {
		this.dynamicadmire = dynamicadmire;
	}



	public Set<CommentAdmire> getCommentadmire() {
		return commentadmire;
	}



	public void setCommentadmire(Set<CommentAdmire> commentadmire) {
		this.commentadmire = commentadmire;
	}



	public Set<Fan> getFan() {
		return fan;
	}



	
	public void setFan(Set<Fan> fan) {
		this.fan = fan;
	}



	/**
	 * @return the deadline
	 */
	public String getDeadline() {
		return deadline;
	}



	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}



	/**
	 * @return the starttime
	 */
	public String getStarttime() {
		return starttime;
	}



	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}



	/**
	 * @return the backimg
	 */
	public String getBackimg() {
		return backimg;
	}



	/**
	 * @param backimg the backimg to set
	 */
	public void setBackimg(String backimg) {
		this.backimg = backimg;
	}



	/**
	 * @return the nicesign
	 */
	public String getNicesign() {
		return nicesign;
	}



	/**
	 * @param nicesign the nicesign to set
	 */
	public void setNicesign(String nicesign) {
		this.nicesign = nicesign;
	}



	/**
	 * @return the myelebook
	 */
//	public Set<MyEleBook> getMyelebook() {
//		return myelebook;
//	}



	/**
	 * @param myelebook the myelebook to set
	 */
//	public void setMyelebook(Set<MyEleBook> myelebook) {
//		this.myelebook = myelebook;
//	}
//


	/**
	 * @return the readhistroy
	 */
	public Set<ReadHistroy> getReadhistroy() {
		return readhistroy;
	}



	/**
	 * @param readhistroy the readhistroy to set
	 */
	public void setReadhistroy(Set<ReadHistroy> readhistroy) {
		this.readhistroy = readhistroy;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
