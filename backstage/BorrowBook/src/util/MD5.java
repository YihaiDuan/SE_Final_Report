package util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MD5 {
       
	
	private static String md5(String plainText) {
            try {
                //鐢熸垚瀹炵幇鎸囧畾鎽樿绠楁硶鐨� MessageDigest 瀵硅薄銆�
                MessageDigest md = MessageDigest.getInstance("MD5");
                //浣跨敤鎸囧畾鐨勫瓧鑺傛暟缁勬洿鏂版憳瑕併��
                md.update(plainText.getBytes());
                //閫氳繃鎵ц璇稿濉厖涔嬬被鐨勬渶缁堟搷浣滃畬鎴愬搱甯岃绠椼��
                byte b[] = md.digest();
                //鐢熸垚鍏蜂綋鐨刴d5瀵嗙爜鍒癰uf鏁扮粍
                int i;
                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }
                return buf.toString();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        
        public static String encrypt_10(String str)
        {
            String d = md5(str + md5(str).substring(0, 30).toUpperCase() + "14101").substring(0,10).toUpperCase();
            return d;
        }
       
        public static String encrypt_5(String str)
        {
        String d = md5(str + md5(str).substring(0, 30).toUpperCase() + "14101").substring(0, 5).toUpperCase();
        return d;
         }
        
        public static String encrypt_30(String str)
        {
            String d = md5(str + md5(str).substring(0,30).toUpperCase() + "14101").toUpperCase().substring(0,30);
            return d;
        }
       
        
        
       public static void main(String[] args)
       {
    	  MD5 m=new MD5();
    	  System.out.println(m.encrypt_10("admin"));
    	  System.out.println(m.encrypt_30("systemadmin"));
    	   //AF42B24C86CDA5E362109BCB99A8B6
    	   
       }
       
      
}
