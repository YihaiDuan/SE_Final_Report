package hfhdao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作类
 * */
public class StateTime {
	//获得前七天的日期
		 public String[] getStatetime(int number,int tyear,int tmotch,int tday){
			        String arr[]=new String[number+1];
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			       
			        for(int i=number;i>=0;i--)
			        {    
			        	  Calendar c = Calendar.getInstance();
			        	  c.set(tyear, tmotch-1, tday);
			        	  c.add(Calendar.DATE, - i);  
					      Date monday = c.getTime();
					      String preMonday = sdf.format(monday);
					      arr[number-i]=preMonday;
			        }
			        return arr;
			   } 
		 //获得时间间隔之间的天数
		  public int getNumber(int fyear,int fmotch,int fday,int tyear,int tmotch,int tday){
			  Date d1=new Date(fyear,fmotch,fday);
			  Date d2= new Date(tyear,tmotch,tday);
			  int days = (int)((d2.getTime() - d1.getTime())/86400000);		  
			  return days;
		  }

}
