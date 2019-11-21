
package entity;


public class DynamicMessage 
{
	
	//评论id 或者回复id
	private  int id;
	
    private int typestatus;
	private String userid;
	private String describ;
	private String date;
	private int dynamicid;
	private int commentid;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTypestatus() {
		return typestatus;
	}
	
	public void setTypestatus(int typestatus) {
		this.typestatus = typestatus;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDescrib() {
		return describ;
	}
	
	public void setDescrib(String describ) {
		this.describ = describ;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the dynamicid
	 */
	public int getDynamicid() {
		return dynamicid;
	}

	/**
	 * @param dynamicid the dynamicid to set
	 */
	public void setDynamicid(int dynamicid) {
		this.dynamicid = dynamicid;
	}

	/**
	 * @return the commentid
	 */
	public int getCommentid() {
		return commentid;
	}

	/**
	 * @param commentid the commentid to set
	 */
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	
	
	
	
}
