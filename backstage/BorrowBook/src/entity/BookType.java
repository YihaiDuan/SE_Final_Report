
package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class BookType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String typeid;
	private String name;
	private String img;
	
	
	@OneToMany(targetEntity=Category.class,cascade=CascadeType.ALL)
    @JoinColumn(name="type_id",updatable=false)
	
	private Set<Category>  category  =new HashSet<Category>();


	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	
	public String getTypeid() {
		return typeid;
	}


	
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}


	
	public String getName() {
		return name;
	}


	
	public void setName(String name) {
		this.name = name;
	}


	
	public String getImg() {
		return img;
	}


	
	public void setImg(String img) {
		this.img = img;
	}


	
	public Set<Category> getCategory() {
		return category;
	}


	
	public void setCategory(Set<Category> category) {
		this.category = category;
	}
	
	
	
	
	
	
	
}
