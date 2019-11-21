package borrowbook;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 将所需数据收集到文件中
 * @author Monly_P
 *
 */
public class CollectData {
	
	//处理单条数据
	private static double calculate(double score,boolean collected,int search,int click){
		double core = 0;
		if(score != 0){//若有评分
			core += score;
			if(collected){//若有收藏
				core += 1;
			}
		}else{//若无评分
			if(collected){//若有收藏
				core += 5;
			}
		}
		double a = search * 0.2;
		double b = click * 0.1;
		if(a + b > 5){
			core += 5;
		}else{
			core += a + b;
		}
		BigDecimal bd = new BigDecimal(core);
		core = bd.setScale(5, RoundingMode.HALF_UP).doubleValue();
		return core;
	}
	
	
	
	public static void main(String[] args) {
		double d = calculate(0, true, 3, 8);
		System.out.println(d);
	}

}
