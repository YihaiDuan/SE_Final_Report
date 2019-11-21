package cn.it.shop.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
/**
* @author 
* @version ����ʱ�䣺2017��1��17�� ����7:46:29 ��˵��
*/
public class PayUtil {
    /**
     * ǩ���ַ���
     * @param text��Ҫǩ�����ַ���
     * @param key ��Կ
     * @param input_charset�����ʽ
     * @return ǩ�����
     */
    public static String sign(String text, String key, String input_charset) {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    /**
     * ǩ���ַ���
     *  @param text��Ҫǩ�����ַ���
     * @param sign ǩ�����
     * @param key��Կ
     * @param input_charset �����ʽ
     * @return ǩ�����
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        if (mysign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5ǩ�������г��ִ���,ָ���ı��뼯����,��Ŀǰָ���ı��뼯��:" + charset);
        }
    }
    /**
     * ����6λ��10λ����� param codeLength(����λ)
     * @return
     */
    public static String createCode(int codeLength) {
        String code = "";
        for (int i = 0; i < codeLength; i++) {
            code += (int) (Math.random() * 9);
        }
        return code;
    }
    private static boolean isValidChar(char ch) {
        if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
            return true;
        if ((ch >= 0x4e00 && ch <= 0x7fff) || (ch >= 0x8000 && ch <= 0x952f))
            return true;// �������ĺ��ֱ���
        return false;
    }
    /**
     * ��ȥ�����еĿ�ֵ��ǩ������
     * @param sArray ǩ��������
     * @return ȥ����ֵ��ǩ�����������ǩ��������
     */
    public static Map<String,String> paraFilter(Map<String,String> sArray) {
        Map<String,String> result = new HashMap<String,String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String   key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }
    /**
     * ����������Ԫ�����򣬲����ա�����=����ֵ����ģʽ�á�&���ַ�ƴ�ӳ��ַ���
     * @param params ��Ҫ���򲢲����ַ�ƴ�ӵĲ�����
     * @return ƴ�Ӻ��ַ���
     */
    public static String createLinkString(Map<String,String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);
            if (i == keys.size() - 1) {// ƴ��ʱ�����������һ��&�ַ�
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }
    /**
     * 
     * @param requestUrl�����ַ
     * @param requestMethod���󷽷�
     * @param outputStr����
     */
    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){
        // ����SSLContext
        StringBuffer buffer=null;
        try{
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.connect();
       
        if(null !=outputStr){
            OutputStream os=conn.getOutputStream();
            os.write(outputStr.getBytes("utf-8"));
            os.close();
        }
       
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "utf-8");
        BufferedReader br = new BufferedReader(isr);
        buffer = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
                      buffer.append(line);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
        }   
    
    
    
    public static String urlEncodeUTF8(String source){
        String result=source;
        try {
            result=java.net.URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}