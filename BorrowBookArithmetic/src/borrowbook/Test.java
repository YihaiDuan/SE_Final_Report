package borrowbook;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Test {

	public static void main(String[] args) {

		/*int sum = 0,ok = 0,usernum = 0,itemnum = 0,core = 0;
		String s2 = "1::323::3::";
		for(int m_ = 0;m_ < s2.length();++m_) {
			if(s2.charAt(m_) != ':'){
				sum = sum * 10 + s2.charAt(m_) - 48;
			}else {
				m_ += 1;
				if(ok == 0) {
					usernum = sum;
					ok = 1;
				}else if(ok == 1){
					itemnum = sum;
					ok = 2;
				}else{
					core = sum;
					break;
				}
				sum = 0;
			}
		}
		System.out.println(usernum+" "+itemnum+" "+core);*/
		
		/*int i = (int)10.3/1;
		System.out.println(i);*/
		
		/*String len = "23:34:34:::";
		String[] array = len.split(":");
		System.out.println(array.length);*/
		
		/*double f = 463645.45674645654756;
		BigDecimal bd = new BigDecimal(f);
		f = bd.setScale(5,BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(f);*/
		
		/*Double i[] = {1.4,4.5,7.5,3.5,9.5,4.5};
		Arrays.sort(i, Collections.reverseOrder());
		for(double s : i){
			System.out.println(s);
		}*/
		
		/*Map<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
			
		});
		map.put(234, 1);
		map.put(234, 2);
		map.put(32, 3);
		map.put(43, 4);
		Set<Map.Entry<Integer, Integer>> set = map.entrySet();
		Iterator<Map.Entry<Integer, Integer>> iter = set.iterator();
		while(iter.hasNext()){
			Map.Entry<Integer, Integer> m = iter.next();
			System.out.println(m.getValue()+" "+m.getKey());
		}*/

		/*double s = 0;
		for(int i = 1; i < 3; i++){
			s += i*2;
			System.out.println(s);
		}*/
		
		/*double[] d1 = {4.5,0,0,4.6,9.0,8.7};
		double[] d2 = {4.3,5.4,0,0,7.0,0};
		System.out.println(test(d1, d2));*/
		
		/*Map<Integer, Integer> map = new TreeMap<>();
		map.put(1, 123);
		map.put(2, 23);
		map.put(3, 433);
		map.put(4, 4);
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());  
		  
		// 然后通过比较器来实现排序  
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {  
		    @Override  
		    public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {  
		        // return 0;  // 降序  
		        // return o2.getValue().compareTo(o1.getValue()); // 降序  
		        return o2.getValue().compareTo(o1.getValue()); // 升序  
		    }  
		}); 
		for (Map.Entry<Integer, Integer> mapping : list) {  
		    System.out.println(mapping.getKey() + ":" + mapping.getValue());  
		} */
		
		String s = "31.2.2.6@";
		String s2 = "we第2部分@(一)@wedfs";
		String s1 = "dfgdfg 134 mijij";
		s2 = s2.replaceAll("(.)(?=(第([\u4e00-\u9fa5]+|\\d+\\.{0,1}\\d*\\.{0,1}\\d*\\.{0,1}\\d*)(章|部分|节))|(\\(.\\))@)", "$1@");
		//s = s.replaceAll("(?<=第([\u4e00-\u9fa5]+|\\d+\\.{0,1}\\d*\\.{0,1}\\d*\\.{0,1}\\d*)(章|部分|节))@", "");
		System.out.println(s2);
	}
	//计算单个相似度
	private static double test(double[] d1,double[] d2){
		double t1 = 0;
		for(double d : d1){
			t1 += d;
		}
		double a1 = t1/6;
		double t2 = 0;
		for(double d : d2){
			t2 += d;
		}
		double a2 = t2/6;
		double total_a = 0,total_b1 = 0,total_b2 = 0;
		for(int i = 0; i < 6; i++){
			if(d1[i] != 0 && d2[i] != 0){
				total_a += (d1[i]-a1)*(d2[i]-a2);
				total_b1 += Math.pow(d1[i]-a1,2);
				total_b2 += Math.pow(d2[i]-a2,2);
			}
		}
		return total_a / (Math.sqrt(total_b1) * Math.sqrt(total_b2));
	}

}
