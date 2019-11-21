package common;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import dao.BookDao;
/**
 * 抓取豆瓣的评分，标签，价格差价
 * @author Monly_P
 *
 */
public class Test {

		private static void test() throws IOException, SAXException, SQLException{
			BookDao bookDao = new BookDao();
			List<String> list = bookDao.query();
			for(int x = 0; x < list.size(); x++){
				String isbn = list.get(x);
				Document docement = Jsoup.connect("http://book.douban.com/subject_search?search_text="+isbn+"&cat=1001").timeout(4000).get();
				Elements e = docement.select("div.article");
				Elements e1 = e.select("ul.subject-list");
				System.out.println("-----------------------"+x+"----------------------");
				if(e1.html() != null && !e1.html().equals("")){
					String url = e1.select("li div.pic a.nbg").attr("href");
					//System.out.println(url);
					Document docu = Jsoup.connect(url).timeout(5000).get();
					
					// 抓取价格比较
					Elements ele1 = docu.select("div#buyinfo-printed ul li");
					Element ele2 = ele1.get(ele1.size() - 1);
					String price_url = ele2.select("a").attr("href");
					Document docu2 = Jsoup.connect(price_url).timeout(5000).get();
					Elements ele_price = docu2.select("table#buylink-table tr");
					StringBuilder builder = new StringBuilder();
					for (int i = 1; i < ele_price.size(); i++) {
						Element ele = ele_price.get(i);
						Elements eles = ele.select("td");
						String img = eles.get(0).select("img").attr("src");
						String store_link = eles.get(1).select("a").attr("href");
						String store = eles.get(1).select("a").text();
						String price = eles.get(2).select("a").text();
						String save = eles.get(3).text();
						String str1 = img + "609*" + store_link + "609*" + store + "609*" + price + "609@";
						builder.append(str1);
					}
					System.out.println(builder);//这里把builder存入数据库
				}else{
					System.out.println("610");//如果没有搜索到，就存入610
				}
					
			}
		}

		public static void main(String[] args) {
			try {
				Test.test();
			} catch (IOException | SAXException | SQLException e) {
				e.printStackTrace();
			}
		}

}
