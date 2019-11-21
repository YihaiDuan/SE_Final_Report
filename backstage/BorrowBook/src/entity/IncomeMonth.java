package entity;

public class IncomeMonth {
    private Integer id;
    private String time;
    private double price;
	public IncomeMonth() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IncomeMonth(Integer id, String time, double price) {
		super();
		this.id = id;
		this.time = time;
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "IncomeMonth [id=" + id + ", time=" + time + ", price=" + price
				+ "]";
	}
    
	
	
	
}
