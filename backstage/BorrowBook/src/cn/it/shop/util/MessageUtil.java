package cn.it.shop.util;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil 
{
    public static HashMap parseXML(HttpServletRequest request) throws Exception, IOException{
        HashMap map=new HashMap();
        // ͨ��IO���Document
        SAXReader reader = new SAXReader();
        Document doc = reader.read(request.getInputStream());
        //�õ�xml�ĸ��ڵ�
        Element root=doc.getRootElement();
        recursiveParseXML(root,map);
        return map;
    }
    

   
    private static void recursiveParseXML(Element root,HashMap<String, String> map){
        // �õ���Ԫ�ص������ӽڵ�
        List<Element> elementList=root.elements();
        //�ж���û����Ԫ���б�
        if(elementList.size() == 0){
            map.put(root.getName(), root.getText());
        }else{
            //����
            for (Element e : elementList){
                recursiveParseXML(e,map);
            }
        }
    }
    
    
    private static XStream xstream = new XStream(new XppDriver()
    {
        public HierarchicalStreamWriter createWriter(Writer out)
        {
            return new PrettyPrintWriter(out) 
            {
                // ������xml�ڵ㶼����CDATA���
                boolean cdata = true;
                public void startNode(String name, Class clazz)
                {
                    super.startNode(name, clazz);
                }
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) 
                    {
                    	writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }
                    else 
                    {
                        writer.write(text);
                    }
                }
            };
        }
    });
    public static String messageToXML(RefundmentPo refundmentPo)
    {
        xstream.alias("xml",RefundmentPo.class);
        return xstream.toXML(refundmentPo);
    }
    
    public static String messageToXMLpay(PaymentPo paymentPo)
    {
        xstream.alias("xml",PaymentPo.class);
        return xstream.toXML(paymentPo);
    }
}