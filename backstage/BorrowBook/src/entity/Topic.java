
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

@Entity
@Table
public class Topic 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String topicname;
	private String topicimg;
	private String describ;
	@Column(columnDefinition="INT default 0") 
	private int showstatus;
	private String date;
	
	
	@OneToMany(targetEntity=TopicBook.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="topic_id",updatable=false)
	private Set<TopicBook>  topicBook  =new HashSet<TopicBook>();
	
	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTopicname() {
		return topicname;
	}
	
	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}
	
	public String getTopicimg() {
		return topicimg;
	}
	
	public void setTopicimg(String topicimg) {
		this.topicimg = topicimg;
	}
	
	public String getDescrib() {
		return describ;
	}
	
	public void setDescrib(String describ) {
		this.describ = describ;
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
