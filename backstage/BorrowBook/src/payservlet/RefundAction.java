package payservlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.it.shop.util.MessageUtil;
import cn.it.shop.util.PayUtil;
import cn.it.shop.util.RefundmentPo;
import cn.it.shop.util.UUIDHexGenerator;
import dao.UserDao;
import entity.User;
import net.sf.json.JSONObject;


@WebServlet("/RefundAction")
public class RefundAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    //每个字段具体的意思请查看API文档
    private String appid = "";
    private String mch_id = "";
   
    private String nonce_str = "";
    private String sign = "";
    private String transaction_id = "";
    private String out_trade_no = "";
    private String out_refund_no = "";
    private String refund_fee_type = "CNY";
    private String op_user_id = "";
       

    public RefundAction() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
	
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter();
		
		  String userid=request.getParameter("userid");
		  
		  System.out.println(userid);
		  
		  String   total_fee =request.getParameter("free");
		  String   refund_fee = total_fee;
		  
		  System.out.println(total_fee);
		 
		  
		  UserDao ud=new UserDao();
		   User u=ud.getUserbyid(userid);
		  
		  
		    appid="wxb31c7fb23e5d11c2";    //appid
		   
		    mch_id = "110120119";        //商户号
		    
		    nonce_str=UUIDHexGenerator.generate();//随机字符串  32位长度
		    
		    System.out.println(nonce_str);
		    
		    out_trade_no="123";//支付id
		    out_refund_no="123";//商户内部的退款单号
		    
		    op_user_id="110120119";      //商户号
		    transaction_id ="110";
		    
		    
		    
       RefundmentPo refundmentPo = new RefundmentPo();
	        
	    
		    refundmentPo.setAppid(appid);
		    refundmentPo.setMch_id(mch_id);
		    refundmentPo.setNonce_str(nonce_str);
		    refundmentPo.setTransaction_id(transaction_id);
		    refundmentPo.setOut_refund_no(out_refund_no);
		    refundmentPo.setOp_user_id(op_user_id);
		    refundmentPo.setTotal_fee(total_fee);
		    refundmentPo.setRefund_fee(refund_fee);
		    refundmentPo.setOut_trade_no(out_trade_no);
		    refundmentPo.setRefund_fee_type(refund_fee_type);
		    
		    
		    
		    // 把请求参数打包成数组
	        Map<String,String>  sParaTemp= new HashMap<String,String>();
	        
	        sParaTemp.put("appid", refundmentPo.getAppid());
	        sParaTemp.put("mch_id", refundmentPo.getMch_id());
	        sParaTemp.put("nonce_str", refundmentPo.getNonce_str());
	        sParaTemp.put("transaction_id",  refundmentPo.getTransaction_id());
	        sParaTemp.put("out_trade_no", refundmentPo.getOut_trade_no());
	        sParaTemp.put("out_refund_no",refundmentPo.getOut_refund_no());
	        sParaTemp.put("op_user_id", refundmentPo.getOp_user_id());
	        sParaTemp.put("total_fee",refundmentPo.getTotal_fee());
	        sParaTemp.put("refund_fee", refundmentPo.getRefund_fee());
	        sParaTemp.put("refund_fee_type", refundmentPo.getRefund_fee_type());
	        
	        System.out.println( sParaTemp);
	        
	        // 除去数组中的空值和签名参数
	        Map<String,String> sPara = PayUtil.paraFilter(sParaTemp);
	        
	        System.out.println( sPara);
	        
	        
	        String prestr = PayUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
	       
	        String key = "&key=替换为商户支付密钥"; // 商户支付密钥
	        
	        //MD5运算生成签名
	        String mysign = PayUtil.sign(prestr, key, "utf-8").toUpperCase();
	        
	        
	        refundmentPo.setSign(mysign);
	        
		    
		    System.out.println(refundmentPo);
	        
	        
	        //打包要发送的xml
	        String respXml = MessageUtil.messageToXML(refundmentPo);
	        // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
	        respXml = respXml.replace("__", "_");
	        
	        
	        System.out.println("要申请过去的xml-----"+respXml);
	        
	       String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";//统一退款API接口链接
	        
	       // String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单API接口链接
	        
	        String param = respXml;
	        
	        //String result = SendRequestForUrl.sendRequest(url, param);//发起请求
	        //发送post请求到微信服务器获取prepayid放到result中
	        
	        String result =PayUtil.httpRequest(url, "POST", param);
	        
	        
	        System.out.println("微信的返回结果:"+result);
	        
	        
	        
         // 将解析结果存储在HashMap中
	        
	        Map<String,String> map = new HashMap<String,String>();
	        
	        
	         InputStream in=new ByteArrayInputStream(result.getBytes());  
	        // 读取输入流
	        SAXReader reader = new SAXReader();
	        
	        Document document=null;
			try {
				document = reader.read(in);
			} catch (DocumentException e) {
				
				e.printStackTrace();
			}
	        // 得到xml根元素
	        Element root = document.getRootElement();
	        // 得到根元素的所有子节点
	       
	        @SuppressWarnings("unchecked")
			List<Element> elementList = root.elements();
	        
	        for (Element element : elementList) 
	        {
	            map.put(element.getName(), element.getText());
	        }
	        // 返回信息
	        String return_code = map.get("return_code");//返回状态码
	        String return_msg = map.get("return_msg");//返回信息
	        
	        System.out.println("return_msg"+return_msg);
	        
	        
	        //把接收到的数据转换为json格式输出给前台
	        JSONObject JsonObject=new JSONObject() ;
	        
	        if(return_code=="SUCCESS"||return_code.equals(return_code))
	        {
	          
	        	System.out.println("成功");
	        	
	          }
	        else
	        {
	        	
	        	
	        	System.out.println("失败");
	        }
	      
	        
	        
	        
	            System.out.println(JsonObject);
	            out.write(JsonObject.toString());
	
		    
		    
		    
		    
	}

}
