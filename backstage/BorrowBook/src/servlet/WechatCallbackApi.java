package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Httppost;
import util.MessageUtil;
import util.SHA1;
import util.access_tokken;


@WebServlet("/WechatCallbackApi")
public class WechatCallbackApi extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
  
	private String TOKEN = "findbook";
	
    public WechatCallbackApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	   // 微信加密签名
	        String signature = request.getParameter("signature");
	        // 随机字符串
	        String echostr = request.getParameter("echostr");
	        // 时间戳
	        String timestamp = request.getParameter("timestamp");
	        // 随机数
	        String nonce = request.getParameter("nonce");

	        String[] str = { TOKEN, timestamp, nonce };
	        Arrays.sort(str); // 字典序排序
	        String bigStr = str[0] + str[1] + str[2];
	        // SHA1加密
	     String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();

	        // 确认请求来至微信
	        if (digest.equals(signature))
	        {
	            response.getWriter().print(echostr);
	            
	            
	            
	            
	            System.out.println("验证成功");
	        }

	
		
		        doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");

		
			
		try {
					// 默认返回的文本消息内容
					//String respContent = "请求处理异常，请稍候尝试！";

					// xml请求解析
					Map<String, String> requestMap = MessageUtil.parseXml(request);

					// 发送方帐号（open_id）
					String fromUserName = requestMap.get("FromUserName");
					// 公众帐号
					String toUserName = requestMap.get("ToUserName");
					// 消息类型
					String msgType = requestMap.get("MsgType");
					
					
					 //消息
			         String content=requestMap.get("Content");
			         
			         
					System.out.println(fromUserName);
					System.out.println(toUserName);
					System.out.println(msgType);
					System.out.println(content);
					
					
					
					String appid="wxb31c7fb23e5d11c2";
					String secret="132de5b5e208efa36294a2516728c477";
					
					String openid=fromUserName;
					
					
					
					//获取access_token
					String access_token=access_tokken.getAccesstoken(appid,secret);
					
					
			String poseMsgApi="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token;
					
			String data=null;
			
				if(content.equals("你好"))
				{
					
				/*	{
					    "touser":"OPENID",
					    "msgtype":"text",
					    "text":
					    {
					         "content":"Hello World"
					    }
					}*/
				data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
					+"{"+"\"content\":"+"\""+"欢迎交流"+"\""+"}}";
					
					
					
				}
				else if(content.indexOf("推荐阅读")!=-1)
				{
					
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t①　功能概述系统可以根据用户的长期阅读及一段时间的多次搜索习惯给用户量身定制，定期给用户推荐书籍。用户可以设置推荐频率，如果用户觉得困扰，可以手动关闭推荐。"+"\""+"}}";						
				}
				else if(content.indexOf("图书转借")!=-1)
				{
					
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t若一人未还，一人想借，用户之间可直接进行图书转借，相当于将图书“过户”。借书者可扫描未还者的“转借”二维码借书成功，还书者也可以顺利还书成功。这样不必到图书馆借书、取书，为用户节约了时间和精力，有更好的用户体验。a.通过“附近的人”“附近的人”会根据地理位置将用户附近的有借书的其他用户展示出来，	线下联系进行图书转借。b.通过相识的人如果用户之间认识，可直接进行转借。"+"\""+"}}";						
				}
				else if(content.indexOf("预订")!=-1)
				{
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t注册用户可以在线预订书籍。指定具体时间去图书馆取书。如果不巧暂时没有藏书，用户可以选择当有用户归还书籍后系统自动给他推送信息提醒"+"\""+"}}";			
					
				}
				else if(content.indexOf("借书")!=-1)
				{
					
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t图书上贴有二维码，用户在图书馆可以使用应用的扫码功能将书放入借书栏。一个用户一次可借2本书。用户出图书馆前出示自己的借书二维码给图书馆管理员。管理员通过自己的管理app的扫码功能扫描用户出示的二维码调出用户的借书单，并与事物进行比较。用户通过微信支付手段的方式提交押金"+"\""+"}}";
				}
				
				else if(content.indexOf("还书")!=-1)
				{
					
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t用户携带书籍去图书馆，出示自己的借书二维码给管理员。管理员通过管理app扫描用户出示的二维码得出借书清单。与实物比较无误后办理书籍入库，完成还书环节。"+"\""+"}}";					
				}
				else if(content.indexOf("分享")!=-1)
				{
					
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t用户先到个人中心页面上传个人位置和个人二维码，然后到从’分享图书'里扫描图书条形码进行上传"+"\""+"}}";					
				}
				else if(content.equals("1"))
				{
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t图书上贴有二维码，用户在图书馆可以使用应用的扫码功能将书放入借书栏。一个用户一次可借2本书。用户出图书馆前出示自己的借书二维码给图书馆管理员。管理员通过自己的管理app的扫码功能扫描用户出示的二维码调出用户的借书单，并与事物进行比较。用户通过微信支付手段的方式提交押金"+"\""+"}}";
					
				}
				else if(content.equals("2"))
				{
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t注册用户可以在线预订书籍。指定具体时间去图书馆取书。如果不巧暂时没有藏书，用户可以选择当有用户归还书籍后系统自动给他推送信息提醒"+"\""+"}}";			
					
				}
				else if(content.equals("3"))
				{
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t用户携带书籍去图书馆，出示自己的借书二维码给管理员。管理员通过管理app扫描用户出示的二维码得出借书清单。与实物比较无误后办理书籍入库，完成还书环节。"+"\""+"}}";					
					
				}
				else if(content.equals("4"))
				{
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t用户先到个人中心页面上传个人位置和个人二维码，然后到从’分享图书'里扫描图书条形码进行上传"+"\""+"}}";					
					
				}
				
				else if(content.equals("5"))
				{
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t①　功能概述系统可以根据用户的长期阅读及一段时间的多次搜索习惯给用户量身定制，定期给用户推荐书籍。用户可以设置推荐频率，如果用户觉得困扰，可以手动关闭推荐。"+"\""+"}}";						
					
				}
				
				else if(content.equals("6"))
				{
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+"\\t\\t\\t\\t若一人未还，一人想借，用户之间可直接进行图书转借，相当于将图书“过户”。借书者可扫描未还者的“转借”二维码借书成功，还书者也可以顺利还书成功。这样不必到图书馆借书、取书，为用户节约了时间和精力，有更好的用户体验。a.通过“附近的人”“附近的人”会根据地理位置将用户附近的有借书的其他用户展示出来，	线下联系进行图书转借。b.通过相识的人如果用户之间认识，可直接进行转借。"+"\""+"}}";						
					
				}
				
				else
				{
					
					data="{"+"\"touser\":"+"\""+openid+"\","+"\"msgtype\":"+"\"text\","+"\"text\":"
							+"{"+"\"content\":"+"\""+" 欢迎来到找我借书客服消息,很高心兴为您服务\\n\\n 1、查看借书流程;\\n 2、查看预订流程;\\n 3、查看还书流程;\\n 4、查看分享图书流程;\\n 5、推荐阅读流程;\\n 6、查看图书转借流程;"+"\""+"}}";
					
					
					
					
				}
					
				System.out.println("输出提示"+data);
				String success=Httppost.sendPost(poseMsgApi,data);
				System.out.println(success);
				
					
			
					
					
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
   
		
		
	}

}
