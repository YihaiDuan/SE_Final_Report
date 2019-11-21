package entity;


public class ComparePrice 
{

	
	private int id;
	
	private String price;
	private String from;
	private String link;
	private String images;
	
	
	
	
	
	/**
	 * @return the images
	 */
	public String getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(String images) {
		this.images = images;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	

}
