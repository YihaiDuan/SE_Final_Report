package borrowbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ��Id�任Ϊ��0��ʼ������ ԭId����Id�Ķ��� д���ļ���
 * @author Monly_P
 *
 */
public class SwitchId {

	/**
	 * ת��Id
	 * @param in_path ת��ǰid���ļ�·��
	 * @param out_path ת����id���ļ�·��
	 * @return ����id������
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
			//�����޸�userId��bookId
			toSwitch("C:\\Users\\Monly_P\\Desktop\\borrowbook\\userId_original.txt", "C:\\Users\\Monly_P\\Desktop\\borrowbook\\userId_switch.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}
