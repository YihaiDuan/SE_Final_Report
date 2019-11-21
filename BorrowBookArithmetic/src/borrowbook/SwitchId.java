package borrowbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 将Id变换为从0开始，并将 原Id和现Id的对照 写入文件中
 * @author Monly_P
 *
 */
public class SwitchId {

	/**
	 * 转换Id
	 * @param in_path 转换前id的文件路径
	 * @param out_path 转换后id的文件路径
	 * @return 返回id的数量
	 * @throws IOException
	 */
	private static int toSwitch(String in_path,String out_path) throws IOException{
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
	
	public static void main(String[] args) {
		try {
			//这里修改userId和bookId
			toSwitch("C:\\Users\\Monly_P\\Desktop\\borrowbook\\userId_original.txt", "C:\\Users\\Monly_P\\Desktop\\borrowbook\\userId_switch.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}
