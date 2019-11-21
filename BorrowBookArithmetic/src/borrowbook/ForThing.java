package borrowbook;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ForThing {

	static int usersum = 20836;     //�û���
	static int itemsum = 200;	//��Ʒ����
	static int N = 3;           //�Ƽ�����
	static int[][] train; //ѵ������user item rate����
	static int[][] test;//���Լ���user item rate����
	static double[][] trainuseritem; //ѵ������user item ��Ȥ�̶� ����
	static int[][] recommend;  //Ϊÿ���û��Ƽ�N����Ʒ
	static simi [][]simiItem; //�����������Ծ���
	static double [][]itemsim; //δ����������Ծ���
	static String road = "data/3 ������/�Ƶ�-���ۣ���ת����.txt";  //����·������ʽΪ�û����::��Ʒ���::����
	static String road2 = "data/10 �Ƽ�/(��ת��)��ӾƵ�ID.txt";
	static String road3 = "data/10 �Ƽ�/��Ʒ�Ƽ�.txt";
	public static String road4 = "data/10 �Ƽ�/(��ת��)�û�ID.txt";
	public static class simi
	{
		double value; //����ֵ
		int num;	 //������Ʒ��
	};
	
	public static void _Run() throws IOException {
		
		get_user_hotel_num();
		System.out.println("usersum: "+usersum);
		System.out.println("itemsum: "+itemsum);
		train = new int[itemsum][usersum]; train[0][0] = 0; //ѵ������user item rate����
		test = new int[itemsum][usersum]; test[0][0] = 0;  //���Լ���user item rate����
		trainuseritem = 
				new double[usersum][itemsum]; trainuseritem[0][0] = 0.0; //ѵ������user item ��Ȥ�̶� ����
		recommend = new int[usersum][N]; recommend[0][0] = 0;  //Ϊÿ���û��Ƽ�N����Ʒ
		simiItem = new simi[itemsum][itemsum]; //�����������Ծ���
		
		itemsim = new double[itemsum][itemsum]; //δ����������Ծ���

		int i,j,k = 8;        //ȥ�û���k������ھӣ����ƶ���ߣ��������Ƽ���Ʒ
		
		for(i = 0 ;i < itemsum;++i)
			for(j = 0 ;j < itemsum;++j) simiItem[i][j] = new simi();
		
		System.out.println("1.ѵ����");
		SplitData(8,1); 
	    //�����ʼ���ľ���
		/*for (i=0;i<10;i++)
	 	{
	 		System.out.println("Item"+i+":  ");
	 		for (j=0;j<5;j++)
	 		{
	 			System.out.print(train[i][j]+"  ");
	 		}
	 		System.out.println();
	 	}*/
		
		
		System.out.println("2.������Ʒ֮�������ԣ��õ������Ծ���");
		for (i=0;i<itemsum;i++) 
		{
			for (j=0;j<itemsum;j++)
			{
				itemsim[i][j] = Simility(train[i],train[j]);
				if(i == j) itemsim[i][j] = 0;   //�˴���bug�����޸�
			}
		}
		//�����Ʒ�����Ծ���
		/*for (i=0;i<5;i++)
	 	{
	 		System.out.println("Item"+":  ");
	 		for (j=0;j<100;j++)
	 		{
	 			System.out.print(itemsim[i][j]+"  ");
	 		}
	 		System.out.println();
	 	}*/
		
		System.out.println("3.��Ʒ���ƶ��ɸߵ�������");
		sort();
		//�����������Ʒ�����Ծ���
		
		/*for(i=0;i<5;i++)
		{
			System.out.println("Item"+i+":  ");
			for(j=0;j<10;j++)
			{
				System.out.print(simiItem[i][j].num+","+simiItem[i][j].value+" ");
			}
			System.out.println();
		}*/
	    
		
		
		
		System.out.println("4.�õ��û�����Ʒ��Ȥ�̶ȵľ���");
		for(i=0;i<usersum;i++)
		{
			for(j=0;j<itemsum;j++)
			{
				if(train[j][i]==0)            //����û�i����Ʒjû�й���Ϊ���ż���i��j��Ԥ����Ȥ�̶�
					//trainuseritem[i][j]=
			    	getUserLikeItem(i,j,k);
				
			}
		}
		//����û�����Ʒ��Ȥ�ľ���
		/*for (i=0;i<5;i++)
	 	{
	 		System.out.println("User_ins"+i+":  ");
	 		for (j=0;j<10;j++)
	 		{
	 			System.out.print(trainuseritem[i][j]+"  ");
	 		}
	 		System.out.println();
	 	}*/
		System.out.println("5.ͨ����Ʒ��Ȥ�̶ȣ��Ƽ�ǰN��");
		getRecommend();
		//����Ƽ�����
		for (i=0;i<200;i++)
	 	{
	 		System.out.println("user"+(i+1));
	 		for (j=0;j<N;j++)
	 		{
	 			if(recommend[i][j] != 0)
	 				System.out.print(recommend[i][j]+" ");
	 		}
	 		System.out.println();
	 	}
		
		System.out.println("6.�����txt");
		out_txt(road2,road3);
		
		
	}
	//��ȡ�Ƶ���û�������
    public static void get_user_hotel_num() throws IOException { 
    	FileReader data_about = new FileReader(road2);
 		BufferedReader read_data_about=new BufferedReader(data_about);
 		int num = 0;
		while(read_data_about.readLine() != null) num++;
 		itemsum = num;
		data_about.close();
		read_data_about.close();
		
		FileReader data_about2 = new FileReader(road4);
		BufferedReader read_data_about2=new BufferedReader(data_about2);
 		num = 0;
		while(read_data_about2.readLine() != null) num++;
 		usersum = num;
		data_about2.close();
		read_data_about2.close();

    }
	
    
    //�����۽ṹ�����txt��
	public static void out_txt(String r1,String r2) throws IOException {
		FileReader data_about=new FileReader(r1);
		BufferedReader read_data_about=new BufferedReader(data_about);
		FileWriter fw=new FileWriter(r2);
		
		String id; //�ݴ��ļ�һ�м�¼
		int id_num = 1;
		String []tmps = new String[5];
		String []hotel = new String[201];
		while((id=read_data_about.readLine())!=null){ 
			tmps = id.split("::");
			String hotelname = tmps[0];
			String number = tmps[1];
			hotel[Integer.parseInt(number)] = hotelname;
		}
		int i,j;
		for (i=0;i<usersum;i++)
	 	{
			fw.write("user"+(i+1));
	 		for (j=0;j<N;j++)
	 		{
	 			if(recommend[i][j] != 0)
	 				fw.write("::"+hotel[recommend[i][j]]);
	 		}
	 		fw.write("\r\n");
	 	}
		data_about.close();
		read_data_about.close();
		fw.close();
	}
	
	//����ȫ��Ϊ���Լ���k��û����
	//������ݼ�Ϊ���Լ�test��ѵ����trainuser������1/mΪ���Լ�,ȡ��ͬ��k<=m-1ֵ ����ͬ���漴�����¿ɵõ���ͬ�Ĳ�/ѵ����
	public static int SplitData(int m, int k)
	{	   
		
		int usernum = 0;
		int itemnum = 0;
		int core = 0;
                
		try {
			//!!!!!!!!!!!!!!!!!ע��  ������ʽΪ  1���û��ţ���234����Ʒ�ţ���8�����֣����������Ҫת����int�ͣ����罫7.8ת����7��������������������������
			FileReader data_about=new FileReader(road);
			BufferedReader read_data_about=new BufferedReader(data_about);
			String s2; //ȡ��һ����¼
			try {
				while((s2=read_data_about.readLine())!=null){
					//��һ�������е��û��ź���Ʒ�ŷֱ�ת�������֣��ֱ�Ϊusernum��itemum��
					int sum = 0,ok = 0;
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
					
					if (usernum <= usersum && itemnum <= itemsum)
					{
						train[itemnum-1][usernum-1] = core;  //�û��ŵ���Ʒ�ž���0��ʼ����
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	 		try {
				data_about.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 		try {
				read_data_about.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return 1;
	}

	//����ѵ������train[][]��������Ʒ֮�����ƶȣ����� ŷ��������ƶ��㷨
	public static double Simility(int[] ItemA, int[] ItemB)
	{
		int comUser = 0;                   //ItemA��ItemB�Ķ����û����۵��û�����
		double simility = 0.0;
		int countIa = 0;
		int countIb = 0;
 
		int i;
		for (i=0;i<usersum;i++)      //�˴���bug�����޸�
		{
			if (ItemA[i]>0&&ItemB[i]>0)
			{
				comUser++;//����ItemA��ItemB�Ķ����û����۵��û�����
			}
			if (ItemA[i]>0){
				countIa++;//����ItemA���û�����
			}
			if (ItemB[i]>0){
				countIb++;//����ItemB���û�����
			}
		}
		double tem = Math.sqrt(countIa*countIb);

		if(tem == 0)
		{
			return 0;
		}
		else
		{
	    	simility = comUser/tem;
		    return simility;
		}
		
	}


	/*��Ʒ�����Ծ������򣨸����������ɸߵ�������*/
	public static void quickSort(int x, int start, int end) {   
	    if (start < end) {   
	    	double base = simiItem[x][start].value; // ѡ���Ļ�׼ֵ����һ����ֵ��Ϊ��׼ֵ��   
	    	double temp; // ��¼��ʱ�м�ֵ   
	    	int i_tmp;
	        int i = start, j = end;   
	        do {   
	            while ((simiItem[x][i].value > base) && (i < end))   
	                i++;   
	            while ((simiItem[x][j].value < base) && (j > start))   
	                j--;   
	            if (i <= j) {    
	                temp = simiItem[x][i].value;   
	                simiItem[x][i].value = simiItem[x][j].value;   
	                simiItem[x][j].value = temp;  
	                i_tmp = simiItem[x][i].num;   
	                simiItem[x][i].num = simiItem[x][j].num;   
	                simiItem[x][j].num = i_tmp;   
	                i++;   
	                j--;   
	            }   
	        } while (i <= j);   
	        if (start < j)   
	            quickSort(x, start, j);   
	        if (end > i)   
	            quickSort(x, i, end);   
	    }   
	}  
	public static int sort()
	{
		for (int i=0;i<itemsum;i++)
		{
			
			for(int j = 0; j < itemsum; ++j) {
				simiItem[i][j].num = j;
				simiItem[i][j].value = itemsim[i][j];
			}
			quickSort(i,0,itemsum-1);
		}
		return 1;

	}

	//�õ��û�i����ƷjԤ����Ȥ�̶ȣ������Ƽ�
	public static double getUserLikeItem(int i,int j,int k)
	{
		for(int x=0;x<k;x++)//����Ʒj�����Ƶ�k����Ʒ�У��ҳ��û�i�й���Ϊ����Ʒ
		{
			//System.out.println(simiItem[j][x].num);
			if(train[simiItem[j][x].num][i]>0)//������û�ͬ����������ƷҲ�й���Ϊ
			{
				trainuseritem[i][j]+=simiItem[j][x].value;
			}
		}
		return trainuseritem[i][j];
	}
	
	/*ͨ����Ʒ��Ȥ�̶ȣ��Ƽ�ǰN��*/ 
	public static int getRecommend() //��bug,���޸�
	{
		int maxnum;//��ǰ�����Ȥ��Ʒ��
		for(int i=0;i<usersum;i++)
		{

			int []finflag = new int[itemsum];

			for (int x=0;x<N;x++)//�Ƽ�N��
			{
				maxnum = 0;
				while(maxnum < itemsum && finflag[maxnum]!=0)
					maxnum++;
				for (int j=0;j<itemsum;j++)  //ÿѭ��һ�ξ�Ѱ�Ҵ˴θ���Ȥ������Ʒ
				{
				
					if (trainuseritem[i][maxnum] < trainuseritem[i][j]&&finflag[j]==0)
						maxnum = j;
				}
				finflag[maxnum] = 1;
				if(trainuseritem[i][maxnum] != 0)
					recommend[i][x]=maxnum+1;//recommend�����1��ʼʹ��
			}
		}
		return 1;
	}

	
}
