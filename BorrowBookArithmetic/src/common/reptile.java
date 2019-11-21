package common;

import java.io.IOException;
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
public class reptile {
	
	private static void test() throws IOException, SAXException, SQLException{
		BookDao bookDao = new BookDao();
		List<String> list = bookDao.query();
		for(int x = 17; x < 50; x++){
			String isbn = list.get(x);
			Document docement = Jsoup.connect("http://book.douban.com/subject_search?search_text="+isbn+"&cat=1001").timeout(4000).get();
			Elements e = docement.select("div.article");
			Elements e1 = e.select("ul.subject-list");
			System.out.println("-----------------------"+x+"----------------------");
			if(e1.html() != null && !e1.html().equals("")){
				String url = e1.select("li div.pic a.nbg").attr("href");
				System.out.println(url);
				// 抓取评分
				Document docu = Jsoup.connect(url).timeout(5000).get();
				Elements ele_rate = docu.select("strong.rating_num");
				String rating_num = ele_rate.get(0).text();
				if(rating_num != null && !rating_num.equals("")){
					System.out.println(rating_num);
				}else{
					rating_num = "5.0";
				}

				// 抓取标签
				Elements ele_tag = docu.select("a.tag");
				String tags = "";
				for (Element tag : ele_tag) {
					System.out.println(tag.text());
					tags += tag.text()+"@";
				}

				//将评分和标签存到数据库
				bookDao.update(x+1, tags, Double.valueOf(rating_num));
				
				// 抓取价格比较
				Elements ele1 = docu.select("div#buyinfo-printed ul li");
				Element ele2 = ele1.get(ele1.size() - 1);
				String price_url = ele2.select("a").attr("href");
				Document docu2 = Jsoup.connect(price_url).timeout(5000).get();
				Elements ele_price = docu2.select("table#buylink-table tr");
				for (int i = 1; i < ele_price.size(); i++) {
					Element ele = ele_price.get(i);
					Elements eles = ele.select("td");
					String img = eles.get(0).select("img").attr("src");
					String store_link = eles.get(1).select("a").attr("href");
					String store = eles.get(1).select("a").text();
					String price = eles.get(2).select("a").text();
					String save = eles.get(3).text();
					String str = img + " " + store_link + " " + store + " " + price + " " + save;
					if(eles.size() > 4){
						String extra = eles.get(4).text();
						str += " "+extra;
					}
					System.out.println(str);
				}
			}else{
				bookDao.update(x+1, "", 5.0);
			}
				
		}
	}

	public static void main(String[] args) {
		try {
			reptile.test();
		} catch (IOException | SAXException | SQLException e) {
			e.printStackTrace();
		}
	}
}
