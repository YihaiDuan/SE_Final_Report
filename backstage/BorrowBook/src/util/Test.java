package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {  
		     public static void main(String[] args) {
				

		    	    /**
		    	     * 日期格式正确
		    	     */
		    	    String date1 = "2017-01-03";
		    	    /**
		    	     * 日期范围不正确---平年二月没有29号
		    	     */
		    	    String date2 = "2014-02-29";
		    	    /**
		    	     * 日期月份范围不正确---月份没有13月
		    	     */
		    	    String date3 = "2014-13-03";
		    	    /**
		    	     * 日期范围不正确---六月没有31号
		    	     */
		    	    String date4 = "2014-06-31";
		    	    /**
		    	     * 日期范围不正确 ----1月超过31天
		    	     */
		    	    String date5 = "2014-01-32";
		    	    /**
		    	     * 这个测试年份
		    	     */
		    	    String date6 = "0014-01-03";
		    	   Test date = new Test();
		    	    /**
		    	     * 打印正确日期格式
		    	     */
		    	    System.out.println(date.isDate(date1));
		    	    /**
		    	     * 打印date1
		    	     */
		    	    System.out.println(date.isDate(date2));
		    	    /**
		    	     * 打印date3
		    	     */
		    	    System.out.println(date.isDate(date3));
		    	    /**
		    	     * 打印date4
		    	     */
		    	    System.out.println(date.isDate(date4));
		    	    /**
		    	     * 打印date5
		    	     */
		    	    System.out.println(date.isDate(date5));
		    	    /**
		    	     * 打印date6
		    	     */
		    	    System.out.println(date.isDate(date6));
			}  
		     
		     public static boolean isDate(String data) {  
		    	  String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		          Pattern pat = Pattern.compile(rexp);
		          Matcher mat = pat.matcher(data);
		          boolean dateType = mat.matches();
		          return dateType; 
		     }
} 
