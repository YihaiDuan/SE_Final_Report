package thread;

import java.util.List;

import xml.BorrowXmlDao;
import xml.XmlDao;
import dao.StatisticsDao;
import entity.BorrowBookTypeNumber;


public class BorrowBookTypeNumberThread extends Thread {
       
	   final long timeInterval = 86400000;  
	    @Override
	    public void run() {
	    	  while (true) { 
	    		  StatisticsDao statisticsdao = new StatisticsDao();
	    		  List<BorrowBookTypeNumber> list = statisticsdao.getBorrowBookTypenumber();
	    		  BorrowXmlDao.discardSon();
	    		  BorrowXmlDao.createSon(list);
	    	  try {  
                 Thread.sleep(timeInterval);  
             } catch (InterruptedException e) {  
                 e.printStackTrace();  
             }  
	    	  
	    	  
	    	  }
	     }
	
	
	
}
