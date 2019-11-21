/**
 * 
 */
package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table
public class GroupBook 
{
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	
	
	@ManyToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "book_id")
	private Book book;
	
	
   
	@ManyToOne
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "group_id")
	private GroupMore  groupmore;

	
	@OneToMany(targetEntity=GroupMain.class,cascade=CascadeType.ALL)
	@JoinColumn(name="groupbook_id",updatable=false)
   private Set<GroupMain>  groupmain =new HashSet<GroupMain>();
	
	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}




	
	public GroupMore getGroupmore() {
		return groupmore;
	}



	public void setGroupmore(GroupMore groupmore) {
		this.groupmore = groupmore;
	}


	
	public Book getBook() {
		return book;
	}



	public void setBook(Book book) {
		this.book = book;
	}


	
	
	
}
