package thread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import xml.BorrowXmlDao;
import xml.InComeDao;
import dao.StatisticsDao;
import entity.BorrowBookTypeNumber;
import entity.IncomeMonth;

public class IncomeThread extends Thread{
	  final long timeInterval = 86400000;  
	    @Override
	    public void run() {
      while (true) { 
    	    Calendar cal = Calendar.getInstance();
    	    int year = cal.get(Calendar.YEAR);
    	    int month = cal.get(Calendar.MONTH)+1;
    	    List<String> list = new ArrayList<String>();
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
    	    for(int i=1;i<month;i++)
    	    {
    	    	 Calendar c = Calendar.getInstance();
	        	 c.add(Calendar.MONTH, - (month-i));  
			     Date monday = c.getTime();
			     String preMonday = sdf.format(monday);
    	    	 System.out.println(preMonday);
    	    	 list.add(preMonday);
    	    }
    	 //   System.out.println(list+"=====================");
    	    StatisticsDao statisticsdao = new StatisticsDao();
    	    List<IncomeMonth> ilist = new ArrayList<IncomeMonth>();
    	  
    	    for(int i = 0;i<list.size();i++)
    	    {   
    	    	IncomeMonth incomemonth = new IncomeMonth();
    	        String time = list.get(i);
    	         
    	        double total =statisticsdao.getMonthIncome(time)+statisticsdao.getMonthVipIncome(time) ;
    	    	incomemonth.setTime(time);
    	    	
    	    	incomemonth.setPrice(total);
    	    	System.out.println(incomemonth);
    	        ilist.add(incomemonth);

    	    }
    	   
	        InComeDao.discardSon();
	        InComeDao.createSon(ilist);
	   try {  
               Thread.sleep(timeInterval);  
           } catch (InterruptedException e) {  
               e.printStackTrace();  
           }  
	    	  
	    	  
	    	  }
	     }    
	public static void main(String[] args) {
		Thread thread = new IncomeThread();
		thread.start();
		
		
	}
	
	
}
