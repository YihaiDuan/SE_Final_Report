package borrowbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * 基于用户间多相似度的协同过滤推荐算法
 * @author Monly_P
 *
 */
public class ForUserMutiSimp {

	private static int userCount;
	//这里list是所有类型书的数量集合
	private static List<Integer> bookCountList = new ArrayList<>(15);
	//这里list是所有类型书的评分集合
	private static List<double[][]> allType_coreArray = new ArrayList<>(15);
	//这里list是所有类型书的用户之间相似度的矩阵集合
	private static List<double[][]> allType_SimiArray = new ArrayList<>(15);
	//这里list是所有类型书的前5个相似度最高的近邻，记录的是近邻的id，而不是评分
	private static List<int[][]> allType_sortSimiArray = new ArrayList<>(15);
	//这里list是所有类型书的用户的预测评分
	private static List<double[][]> allType_preCoreArray = new ArrayList<>(15);
	
	
	/**
	 * 转换Id
	 * @param in_path 转换前id的文件路径
	 * @param out_path 转换后id的文件路径
	 * @return 返回id的数量
	 * @throws IOException
	 */
	private static int toSwitchUserId(String in_path,String out_path) throws IOException{
		int count = 0;
		FileReader fileReader = new FileReader(in_path);
		BufferedReader reader = new BufferedReader(fileReader);
		FileWriter writer = new FileWriter(out_path);
		String len;
		while((len = reader.readLine()) != null){
			writer.write(len+":"+count+"\r\n");
			count++;
		}
		fileReader.close();
		reader.close();
		writer.flush();
		writer.close();
		return count;
	}
	private static List<Integer> toSwitchBookId(String in_path,String out_path) throws IOException{
		List<Integer> list = new ArrayList<>();
		int allCount = 0;
		FileReader fileReader = new FileReader(in_path);
		BufferedReader reader = new BufferedReader(fileReader);
		FileWriter writer = new FileWriter(out_path);
		String len;
		int count = 0;
		while((len = reader.readLine()) != null){
			if(!len.equals("START-NEXT-TYPE")){
				writer.write(len+":"+allCount+"\r\n");
				allCount++;
				count++;
			}else{
				list.add(count);
				count = 0;
			}
		}
		fileReader.close();
		reader.close();
		writer.flush();
		writer.close();
		return list;
	}
	
	/**
	 * 将数据转换成数组
	 * @param in_path 数据文件路径
	 * @throws IOException
	 */
	//格式：23(userId):3.4(图书1评分)：6.7(图书2评分)：0(图书3没有评分)：。。。
	private static void getTrainArray(String in_path) throws IOException{
		FileReader fileReader = new FileReader(in_path);
		BufferedReader reader = new BufferedReader(fileReader);
		String len;
		int count = 0;
		//这里的train[][]是一个类型的矩阵
		double train[][] = new double[userCount][bookCountList.get(0)];
		int type_count = 0;
		while((len = reader.readLine()) != null){
			if(!len.equals("START-NEXT-TYPE")){
				String[] array = len.split(":");
				for(int i = 1; i < array.length;i++){
					//System.out.println(array[i]);
					train[count][i-1] = Double.valueOf(array[i]);
				}
				count++;
			}else{
				allType_coreArray.add(train);
				type_count++;
				if(type_count < bookCountList.size()){
					train = new double[userCount][bookCountList.get(type_count)];
				}
				count = 0;
			}
		}
		fileReader.close();
		reader.close();
	}
	
	/**
	 * 得到相似度矩阵
	 */
	private static void getSimilarArray(){
		for(double[][] train : allType_coreArray){
			//某个类型的用户相似度矩阵
			double[][] singleType_userSimi = new double[userCount][userCount];
			//System.out.println(userCount);
			for(int i = 0; i < userCount; i++){
				for (int j = i + 1; j < userCount; j++) {
					// 计算两个用户的相似度
					List<Integer> sList = new ArrayList<>();
					double total_i = 0;
					double total_j = 0;
					for (int c = 0; c < train[i].length; c++) {
						if (train[i][c] != 0 && train[j][c] != 0) {
							sList.add(c);
						}
						total_i += train[i][c];
						total_j += train[j][c];
					}
					double average_i = total_i / train[i].length;
					double average_j = total_j / train[i].length;
					double x = 0, y = 0, z = 0;
					for (int in : sList) {
						x += (train[i][in] - average_i) * (train[j][in] - average_j);
						y += Math.pow((train[i][in] - average_i), 2);
						z += Math.pow((train[j][in] - average_j), 2);
					}
					double similar;
					if (x != 0 && y != 0 && z != 0) {
						//System.out.println("i=" + i + "j=" + j + " " + x + " " + y + " " + z);
						similar = x / (Math.sqrt(y) * Math.sqrt(z));
						
						BigDecimal bd = new BigDecimal(similar);
						similar = bd.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
					}else{
						similar = 0;
					}
					//System.out.println(i+" "+j+" "+similar);
					singleType_userSimi[i][j] = singleType_userSimi[j][i] = similar;
				}
			}
			allType_SimiArray.add(singleType_userSimi);
		}
	}
	
	/**
	 * 得到前5个从高到低排好序的相似度最高的近邻用户id的矩阵
	 */
	private static void getSortSimiarArray(){
		for(double[][] similar : allType_SimiArray){
			int[][] sort_similar = new int[userCount][1];
			for(int i = 0; i < similar.length; i++){
				Map<Integer, Double> map = new TreeMap<>();
				for(int j = 0; j < similar[i].length;j++){
					if(i != j){
						map.put(j, similar[i][j]);
					}
				}
				List<Map.Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer, Double>>(map.entrySet());  
				Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {  
				    @Override  
				    public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {  
				        return o2.getValue().compareTo(o1.getValue()); // 升序  
				    }  
				}); 
				int c = 0;
				for (Map.Entry<Integer, Double> mapping : list) {  
					if(c < 1){
						sort_similar[i][c] = mapping.getKey();
						c++;
					}
				} 
			}
			allType_sortSimiArray.add(sort_similar);
		}
	}
	
	private static void getPredict(){
		for(int x = 0; x < allType_sortSimiArray.size();x++){
			double[][] preCore = new double[userCount][bookCountList.get(x)];
			for(int y = 0; y < allType_sortSimiArray.get(x).length;y++){
				//System.out.println(x+" "+y);
				double average_p = calculateAverage(x, y);//这里是要预测用户的评分平均值
				for(int w = 0; w < allType_coreArray.get(x)[y].length;w++){
					double core = allType_coreArray.get(x)[y][w];
					if(core == 0 ){
						double total_a = 0;
						double total_b = 0;
						for(int z = 0; z < allType_sortSimiArray.get(x)[y].length;z++){
							int sort = allType_sortSimiArray.get(x)[y][z];
							//System.out.println("近邻"+x+" "+z);
							double average_s = calculateAverage(x, sort);//这里是其中一个近邻用户的评分平均值
							total_a += allType_SimiArray.get(x)[y][sort] * (allType_coreArray.get(x)[sort][w]-average_s);
							//System.out.println(x+" "+y+" "+w+" "+allType_coreArray.get(x)[z][w]);
							total_b += allType_SimiArray.get(x)[y][sort];
							//System.out.println("这里是第"+x+" "+y+" "+sort+" "+total_a+" "+total_b+"相似度"+allType_SimiArray.get(x)[y][sort]);
						}
						if(total_a == 0 || total_b == 0){
							preCore[y][w] = 0;
						}else{
							preCore[y][w] = average_p + total_a / total_b;//这里得出了该用户对于某本没看过书的预测评分
							BigDecimal bd = new BigDecimal(preCore[y][w]);
							preCore[y][w] = bd.setScale(5, RoundingMode.HALF_UP).doubleValue();
						}
						//System.out.println(x+" "+y+" "+w+" "+total_a+" "+total_b+" "+preCore[y][w]);
					}else{
						preCore[y][w] = 0;
					}
				}
			}
			allType_preCoreArray.add(preCore);
		}
	}
	
	private static double calculateAverage(int type, int userId) {
		double total = 0;
		for (double d : allType_coreArray.get(type)[userId]) {
			total += d;
		}
		double average = total/allType_coreArray.get(type)[userId].length;
		return average;
	}
	
	public static void main(String[] args) {
		try {
			userCount = toSwitchUserId("C:\\Users\\Monly_P\\Desktop\\borrowbook\\userId_original.txt", "C:\\Users\\Monly_P\\Desktop\\borrowbook\\userId_switch.txt");
			bookCountList = toSwitchBookId("C:\\Users\\Monly_P\\Desktop\\borrowbook\\bookId_original.txt", "C:\\Users\\Monly_P\\Desktop\\borrowbook\\bookId_switch.txt");
			getTrainArray("C:\\Users\\Monly_P\\Desktop\\borrowbook\\user_core.txt");
			getSimilarArray();
			getSortSimiarArray();
			getPredict();
			for(double[][] d : allType_preCoreArray){
				for(double[] dd : d){
					for(double ddd : dd){
						System.out.print(ddd+"~~~~~~~~");
					}
					System.out.println();
				}
			}
			for(int[][] d : allType_sortSimiArray){
				for(int[] dd : d){
					for(int ddd : dd){
						System.out.print(ddd+"~~~~~~~~");
					}
					System.out.println();
				}
			}
			for(double d : allType_SimiArray.get(0)[1]){
				System.out.println(d);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
