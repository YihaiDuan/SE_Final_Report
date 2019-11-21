package entity;

import java.util.List;


/**
 * 
 * @author hjm
 * @todo 图书分页显示
 */

public class Page<T>


{
	private int pc;//当前页
	private int ps;//页数
	private int all;//总页数
	private String url;//分页url
	private List<T> beanlist; //每页图书记录

	public Page()
	{
		
		
	}
	
	
	
	public int getTp() 
	{    
		int tp=all/ps;
		return all%ps==0 ? tp:tp+1;
	}
	
	
	public int getPc() 
	{
		return pc;
	}
	
	public void setPc(int pc) {
		this.pc = pc;
	}
	
	
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public int getAll() {
		return all;
	}
	public void setAll(int all) {
		this.all = all;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<T> getBeanlist() {
		return beanlist;
	}
	public void setBeanlist(List<T> beanlist) {
		this.beanlist = beanlist;
	}
	
	
	

}
