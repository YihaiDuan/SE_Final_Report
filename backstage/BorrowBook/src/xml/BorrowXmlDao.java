 package xml;
   
   import java.io.File;
   
import java.util.ArrayList;
import java.util.List;

   import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
  



  import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import entity.BookTypeNumber;
import entity.BorrowBookTypeNumber;
  
  
  
  public class BorrowXmlDao {
      
      private static String xmlPath = "c:\\borrowbooktypenumber.xml";
      
      //解析xml文件
      public static List<BorrowBookTypeNumber> getFamilyMemebers(){
          DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
          dbf.setIgnoringElementContentWhitespace(true);
          List<BorrowBookTypeNumber> list = new ArrayList<BorrowBookTypeNumber>();
          try {
              DocumentBuilder db = dbf.newDocumentBuilder();
              Document doc = db.parse(xmlPath); // 使用dom解析xml文件
  
              NodeList sonlist = doc.getElementsByTagName("new"); 
              for (int i = 0; i < sonlist.getLength(); i++) // 循环处理对象
              {
                  Element son = (Element)sonlist.item(i);;
                  BorrowBookTypeNumber borrowbooktypenumber = new BorrowBookTypeNumber();
                  for (Node node = son.getFirstChild(); node != null; node = node.getNextSibling()){  
                	  
                      if (node.getNodeType() == Node.ELEMENT_NODE){  
                          String name = node.getNodeName();  
                          if(name.equals("name"))
                          {
                        	  String value = node.getFirstChild().getNodeValue();  
                        	  borrowbooktypenumber.setBorrowbooktypenumber(value);;
                        	  
                          }
                          if(name.equals("num"))
                          {
                        	  String value = node.getFirstChild().getNodeValue();  
                        	  borrowbooktypenumber.setNumber(Integer.parseInt(value));
                        	  
                          }
                         
                      }  
                      
                      
                 }  
                   list.add(borrowbooktypenumber);     
                  
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return list;
         }
      //在XML文件中修改节点
      public static void modifySon(){
          DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
          dbf.setIgnoringElementContentWhitespace(true);
          try{
          
              DocumentBuilder db=dbf.newDocumentBuilder();
              Document xmldoc=db.parse(xmlPath);
         
              Element root = xmldoc.getDocumentElement();
              
              Element per =(Element) selectSingleNode("/father/son[@id='001']", root);
              per.getElementsByTagName("age").item(0).setTextContent("27");
              
            TransformerFactory factory = TransformerFactory.newInstance();
              Transformer former = factory.newTransformer();
              former.transform(new DOMSource(xmldoc), new StreamResult(new File(xmlPath)));
          }catch(Exception e){
              e.printStackTrace();
          }
      }
      //在XML中删除节点信息
      public static void discardSon(){
              
          DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
          dbf.setIgnoringElementContentWhitespace(true);
          
          try{
         
              DocumentBuilder db=dbf.newDocumentBuilder();
              Document xmldoc=db.parse(xmlPath);
          
             Element root = xmldoc.getDocumentElement();
             while(true){
             Element son =(Element) selectSingleNode("/news/*", root);
             if(son==null){
            	 break;
             }else{
            	 root.removeChild(son);
             }
            
             }
              TransformerFactory factory = TransformerFactory.newInstance();
              Transformer former = factory.newTransformer();
              former.transform(new DOMSource(xmldoc), new StreamResult(new File(xmlPath)));
              
          }catch(Exception e){
              e.printStackTrace();
          }
      }
      //增加XML中的节点
      public static void createSon( List<BorrowBookTypeNumber> list) {
          DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(false);
        
        try{
         
             DocumentBuilder db=dbf.newDocumentBuilder();
             Document xmldoc=db.parse(xmlPath);
         
             Element root = xmldoc.getDocumentElement();
             
             //添加节点
             for(int i = 0;i<list.size();i++)
             {
            	 BorrowBookTypeNumber borrowbooktypenumber = list.get(i);
             Element son =xmldoc.createElement("new");
             Element name = xmldoc.createElement("name");
             name.setTextContent(borrowbooktypenumber.getBorrowbooktypenumber());
             son.appendChild(name);
 
             Element num = xmldoc.createElement("num");
             num.setTextContent(String.valueOf(borrowbooktypenumber.getNumber()));
             son.appendChild(num);
            
             root.appendChild(son);
             }
             //保存
             TransformerFactory factory = TransformerFactory.newInstance();
             Transformer former = factory.newTransformer();
             former.transform(new DOMSource(xmldoc), new StreamResult(new File(xmlPath)));
             
         }catch(Exception e){
             e.printStackTrace();      
             }
     }
     
     public static Node selectSingleNode(String express, Element source) {
         Node result=null;
         XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
         try {
             result=(Node) xpath.evaluate(express, source, XPathConstants.NODE);
         } catch (XPathExpressionException e) {
             e.printStackTrace();
         }         
         
         return result;  
         }
     
     public static void main(String[] args){
    	        
    	// getFamilyMemebers();
    	 
 /*   	 List<BookTypeNumber> list = new ArrayList<BookTypeNumber>();
    	 BookTypeNumber  b1 = new BookTypeNumber();
    	 b1.setBooktypename("小说");
    	 b1.setNumber(12);
    	 BookTypeNumber  b2 = new BookTypeNumber();
    	 b2.setBooktypename("散文");
    	 b2.setNumber(20);
    	 list.add(b1);
    	 list.add(b2);
    	 createSon(list);*/
    	/* List<BookTypeNumber> list = getFamilyMemebers();
    	 System.out.println(list);*/
    	 discardSon();
    	         }
     
  }