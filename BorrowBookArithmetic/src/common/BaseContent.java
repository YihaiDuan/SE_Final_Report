package common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import dao.BookDao;
import dao.TagsDao;

/**
 * 基于内容的算法
 * @author Monly_P
 *
 */
public class BaseContent {

	private static void test() throws SQLException{
		BookDao bookDao = new BookDao();
		List<String> tagsList = bookDao.queryTags();
		for(int i = 0; i < 10; i++){
			TagsDao dao = new TagsDao();
			String tags1 = tagsList.get(i);
			if(tags1 != null && !tags1.equals("")){
				String[] tag1 = tags1.split("@");
				Map<String, Double> map = new TreeMap<>();
				for(int j = 0; j < 50; j++){
					String tags2 = tagsList.get(j);
					if(i != j && tags2 != null && !tags2.equals("")){
						int count = 0;
						String[] tag2 = tags2.split("@");
						for(String str1 : tag1){
							for(int x = 0; x < tag2.length; x++){
								String str2 = tag2[x];
								if(str1.equals(str2) || str1.contains(str2) || str2.contains(str1)){
									count += 1;
									tag2[x] = "$$$";
									break;
								}else{
									count += 0;
								}
							}
						}
						double similar = (double)count/(Math.sqrt(tag1.length)*Math.sqrt(tag2.length));
						//System.out.println(tags2+" "+(j+1)+" "+similar+" "+count+" "+tag1.length+" "+tag2.length);
						map.put(String.valueOf(j+1), similar);
					}
				}
				List<Map.Entry<String, Double>> mapEntry = new ArrayList<>(map.entrySet());
				Collections.sort(mapEntry,new Comparator<Map.Entry<String, Double>>(){
					@Override
					public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
						return o2.getValue().compareTo(o1.getValue());
					}
				});
				String similars = "";
				System.out.println("----------"+i+"-------------");
				for(int y = 0; y < 5; y++){
					similars += mapEntry.get(y).getKey()+"@";
					System.out.println(mapEntry.get(y).getKey()+" "+mapEntry.get(y).getValue());
				}
				dao.insert(i, similars);
			}
		}
	} 
	
	public static void main(String[] args) {
		try {
			test();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
