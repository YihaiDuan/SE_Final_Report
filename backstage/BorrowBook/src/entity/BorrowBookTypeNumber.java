package entity;

public class BorrowBookTypeNumber {
       private String borrowbooktypenumber;
       private int number;
	public String getBorrowbooktypenumber() {
		return borrowbooktypenumber;
	}
	public void setBorrowbooktypenumber(String borrowbooktypenumber) {
		this.borrowbooktypenumber = borrowbooktypenumber;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public BorrowBookTypeNumber() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BorrowBookTypeNumber(String borrowbooktypenumber, int number) {
		super();
		this.borrowbooktypenumber = borrowbooktypenumber;
		this.number = number;
	}
	@Override
	public String toString() {
		return "BorrowBookTypeNumber [borrowbooktypenumber="
				+ borrowbooktypenumber + ", number=" + number + "]";
	}
       
	
}
