package entity;

public class BookTypeNumber {
      private String booktypename;
      private int number;
	public String getBooktypename() {
		return booktypename;
	}
	public void setBooktypename(String booktypename) {
		this.booktypename = booktypename;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public BookTypeNumber() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookTypeNumber(String booktypename, int number) {
		super();
		this.booktypename = booktypename;
		this.number = number;
	}
	@Override
	public String toString() {
		return "BookTypeNumber [booktypename=" + booktypename + ", number="
				+ number + "]";
	}
      
	
	
	
	
}
