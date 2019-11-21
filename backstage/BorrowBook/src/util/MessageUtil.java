package util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



/*
import com.thoughtworks.xstream.XStream;*/

public class MessageUtil {
	
	
	 public static Map<String, String> parseXml(HttpServletRequest request){
	        Map<String, String> map = new HashMap<String, String>();
	        SAXReader reader = new SAXReader();
	        try {
	            InputStream ins = request.getInputStream();
	             
	            Document doc = reader.read(ins);
	            Element root = doc.getRootElement();
	             
	            @SuppressWarnings("unchecked")
	            List<Element> list = root.elements();
	            for (Element e : list) {
	                map.put(e.getName(), e.getText());
	                
	                
	                
	            }
	            ins.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return map;
	    }
	     
	    /**
	     * 将对象转化为xml
	     * @param testMessage
	     * @return
	     */
   /* public static String textMessageToXml(TextMessage testMessage){
	        XStream xstream = new XStream();
	        xstream.alias("xml",testMessage.getClass());
	        return xstream.toXML(testMessage);
	    }
	     */
	   

}
