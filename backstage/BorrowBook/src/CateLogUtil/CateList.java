/**
 * 
 */
package CateLogUtil;


public class CateList {
	
	private int id;
	private  int start;
	private int size;
	private int end;
	private String title;
	
  private String content;

  
  

public String getContent() {
	return content;
}


public void setContent(String content) {
	this.content = content;
}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}

	
}
