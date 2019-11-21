package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * 
 * @author hjm
 * @todo 图书类型实体
 */

@Entity
@Table

public class Category {
	
	
	
	
@Id
private 	String categoryid;
	
private 	String name;
	
private 	String typeclass;



@ManyToOne
@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE })
// 外键
@JoinColumn(name = "type_id")
private BookType  type;

	
	@OneToMany(targetEntity=Book.class,cascade=CascadeType.ALL)
	@JoinColumn(name="category_id",updatable=false)
	
	private Set<Book>  book  =new HashSet<Book>();

	
	
	@OneToMany(targetEntity=Refer.class,cascade=CascadeType.ALL)
	@JoinColumn(name="category_id",updatable=false)
	
	private Set<Refer>  refer  =new HashSet<Refer>();


	
	
	public String getCategoryid() {
		return categoryid;
	}



	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Set<Book> getBook() {
		return book;
	}



	public void setBook(Set<Book> book) {
		this.book = book;
	}



	public String getTypeclass() {
		return typeclass;
	}



	public void setTypeclass(String typeclass) {
		this.typeclass = typeclass;
	}



	public Set<Refer> getRefer() {
		return refer;
	}



	public void setRefer(Set<Refer> refer) {
		this.refer = refer;
	}



	/**
	 * @return the type
	 */
	public BookType getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(BookType type) {
		this.type = type;
	} 
	
	
	

}
