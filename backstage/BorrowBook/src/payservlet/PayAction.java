package payservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.it.shop.util.MessageUtil;
import cn.it.shop.util.PayUtil;
import cn.it.shop.util.PaymentPo;
import cn.it.shop.util.UUIDHexGenerator;
import dao.UserDao;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/PayAction")
public class PayAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	  private String total_fee;//总金额     
	    private String body;//商品描述
	    private String detail;//商品详情    
	    private String attach;//附加数据
	    private String time_start;//交易起始时间
	    private String time_expire;//交易结束时间 
	    private String openid;//用户标识
	    
	    private JSONArray jsonArray=new JSONArray();
       
   
    public PayAction() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
				doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=utf-8");
	      request.setCharacterEncoding("utf-8");
	      
	      PrintWriter out=response.getWriter();
	      
	      //用户账号
	      String userid=request.getParameter("userid");
	      
	      System.out.println(userid);
	      
	      String free=request.getParameter("free");
	      
	      System.out.println(free);
	      
	      
	       UserDao ud = new UserDao();
	       
	       
	       User u=ud.getUserbyid(userid);
	       
	       
	       //用户唯一标识号
	        String openid=u.getOpenid();
	        
	        System.out.println(openid);
	      
	      //body = new String(body.getBytes("UTF-8"),"ISO-8859-1"); 
	          body="图书押金支付";
	          
	          System.out.println("body值"+body);
	          
	          
	        String appid = "wxb31c7fb23e5d11c2";//小程序ID
	        String mch_id = "110120119";//商户号
	        
	        String nonce_str = UUIDHexGenerator.generate();//随机字符串  32位长度
	        
	        System.out.println("随机字符串"+nonce_str);
	        
	        
	        
	        
	        
	        //获取下单时间
	        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        
	        
	        System.out.println("下单时间"+today);
	        
	        String code = PayUtil.createCode(8);
	        
	        System.out.println("随机8位号"+code);
	        
	        String out_trade_no = mch_id+today+code;//商户订单号
	        
	        System.out.println("商户订单号"+out_trade_no);
	        
	        String spbill_create_ip = "115.159.157.75";//终端IP
	        
	        String notify_url = "http://www.weixin.qq.com/wxpay/pay.php";//通知地址
	        
	        String trade_type = "JSAPI";//交易类型  
	        
	        /*//商品描述
	        String body="微信支付";
	        String attach="附加微信支付";*/
	        
	        //String openid="替换为用户的openid";//用户标识
	        
	        
	        /**/
	        PaymentPo paymentPo = new PaymentPo();
	        
	        paymentPo.setAppid(appid);
	        paymentPo.setMch_id(mch_id);
	        paymentPo.setNonce_str(nonce_str);
	     //   String newbody=new String(body.getBytes("ISO-8859-1"),"UTF-8");//以utf-8编码放入paymentPo，微信支付要求字符编码统一采用UTF-8字符编码
	        paymentPo.setBody(body);
	        
	        paymentPo.setOut_trade_no(out_trade_no);
	        
	        paymentPo.setTotal_fee("1");//1分
	        paymentPo.setSpbill_create_ip(spbill_create_ip);
	        paymentPo.setNotify_url(notify_url);
	        paymentPo.setTrade_type(trade_type);
	        paymentPo.setOpenid(openid);
	        
	        
	        // 把请求参数打包成数组
	        Map<String,String>  sParaTemp= new HashMap<String,String>();
	        
	        sParaTemp.put("appid", paymentPo.getAppid());
	        sParaTemp.put("mch_id", paymentPo.getMch_id());
	        sParaTemp.put("nonce_str", paymentPo.getNonce_str());
	        sParaTemp.put("body",  paymentPo.getBody());
	        sParaTemp.put("out_trade_no", paymentPo.getOut_trade_no());
	        sParaTemp.put("total_fee",paymentPo.getTotal_fee());
	        sParaTemp.put("spbill_create_ip", paymentPo.getSpbill_create_ip());
	        sParaTemp.put("notify_url",paymentPo.getNotify_url());
	        sParaTemp.put("trade_type", paymentPo.getTrade_type());
	        sParaTemp.put("openid", paymentPo.getOpenid());
	        
	        // 除去数组中的空值和签名参数
	        Map<String,String> sPara = PayUtil.paraFilter(sParaTemp);
	        String prestr = PayUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
	       
	        String key = "&key=替换为商户支付密钥"; // 商户支付密钥
	        
	        //MD5运算生成签名
	        String mysign = PayUtil.sign(prestr, key, "utf-8").toUpperCase();
	        
	        
	        paymentPo.setSign(mysign);
	        
	        //打包要发送的xml
	        String respXml = MessageUtil.messageToXMLpay(paymentPo);
	        // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
	        respXml = respXml.replace("__", "_");
	        
	        
	        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单API接口链接
	        
	        String param = respXml;
	        //String result = SendRequestForUrl.sendRequest(url, param);//发起请求
	        //发送post请求到微信服务器获取prepayid放到result中
	        String result =PayUtil.httpRequest(url, "POST", param);
	       
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
	        
	        if(return_code=="SUCCESS"||return_code.equals(return_code)){
	            // 业务结果
	            String prepay_id = map.get("prepay_id");//返回的预付单信息
	            String nonceStr=UUIDHexGenerator.generate();
	            
	            JsonObject.put("nonceStr", nonceStr);
	            JsonObject.put("package", "prepay_id="+prepay_id);
	            
	            //时间戳从1970年1月1日00:00:00至今的秒数,即当前的时间
	            Long timeStamp= System.currentTimeMillis()/1000;
	            
	            JsonObject.put("timeStamp", timeStamp+"");
	          
	            String stringSignTemp = "appId="+appid+"&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id+ "&signType=MD5&timeStamp=" + timeStamp;
	          
	            //再次签名
	            String paySign=PayUtil.sign(stringSignTemp, "&key=替换为自己的密钥", "utf-8").toUpperCase();
	            
	            
	            JsonObject.put("paySign", paySign);
	            jsonArray.add(JsonObject);
	           }
	      
	        
	            System.out.println(JsonObject);
	            out.write(JsonObject.toString());
	
	
	}
	
	

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

