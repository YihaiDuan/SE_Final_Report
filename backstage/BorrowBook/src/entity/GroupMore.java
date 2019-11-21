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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Administrator
 * @date Jul 26, 2017
 * @todoTODO
 */
@Entity
@Table
public class GroupMore {
	
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private int id;
	
	  
	  
	 private String maxnum;
     private String groupnum;
	 private String createdate;
	 private String deadline;
	 @Column(columnDefinition="INT default 0") 
	 private int   showstatus;
	 
	 
	   @OneToMany(targetEntity=GroupBook.class,cascade=CascadeType.ALL)
	   @JoinColumn(name="group_id",updatable=false)
		
	private Set<GroupBook>  groupbook  =new HashSet<GroupBook>();
	
	 
	 
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMaxnum() {
		return maxnum;
	}
	
	public void setMaxnum(String maxnum) {
		this.maxnum = maxnum;
	}
	
	public String getGroupnum() {
		return groupnum;
	}
	
	public void setGroupnum(String groupnum) {
		this.groupnum = groupnum;
	}
	
	public String getCreatedate() {
		return createdate;
	}
	
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	public String getDeadline() {
		return deadline;
	}
	
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the showstatus
	 */
	public int getShowstatus() {
		return showstatus;
	}

	/**
	 * @param showstatus the showstatus to set
	 */
	public void setShowstatus(int showstatus) {
		this.showstatus = showstatus;
	}

	/**
	 * @return the groupbook
	 */
	public Set<GroupBook> getGroupbook() {
		return groupbook;
	}

	/**
	 * @param groupbook the groupbook to set
	 */
	public void setGroupbook(Set<GroupBook> groupbook) {
		this.groupbook = groupbook;
	}
	 
	 
	 
	 
	
	
	
}
