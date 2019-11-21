package thread;

import java.util.List;




import xml.XmlDao;
import dao.StatisticsDao;
import entity.BookTypeNumber;

public class BookTypeNumberThread extends Thread

{
	    final long timeInterval = 86400000;  
	    @Override
	    public void run() {
	    	
	    	  while (true) { 
	    		  StatisticsDao statisticsdao = new StatisticsDao();
	    		  List<BookTypeNumber> list = statisticsdao.getBookTypenumber();
	    		  XmlDao.discardSon();
	    		  XmlDao.createSon(list);
	    	  try {  
                  Thread.sleep(timeInterval);  
              } catch (InterruptedException e) {  
                  e.printStackTrace();  
              }  
	    	  
	    	  
	    	  }
	     }

}
