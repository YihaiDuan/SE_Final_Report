package cn.it.shop.util;

import java.net.InetAddress;
/**
* @author 
* @version ����ʱ�䣺2017��1��17�� ����7:45:06 ��˵��
*/
public class UUIDHexGenerator {
    private static String sep = "";
    private static final int IP;
    private static short counter = (short) 0;
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);
    private static UUIDHexGenerator uuidgen = new UUIDHexGenerator();
    static {
        int ipadd;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }
    public static UUIDHexGenerator getInstance() {
        return uuidgen;
    }
    public static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE;
        }
        return result;
    }
    protected static String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }
    protected static String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }
    protected static int getJVM() {
        return JVM;
    }
    protected synchronized static short getCount() {
        if (counter < 0) {
            counter = 0;
        }
        return counter++;
    }
    protected static int getIP() {
        return IP;
    }
    protected static short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }
    protected static int getLoTime() {
        return (int) System.currentTimeMillis();
    }
    public static String generate() 
    {
        return new StringBuffer(36).append(format(getIP())).append(sep).append(format(getJVM())).append(sep)
                .append(format(getHiTime())).append(sep).append(format(getLoTime())).append(sep)
                .append(format(getCount())).toString();
    }
    
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        String id="";
        UUIDHexGenerator uuid = UUIDHexGenerator.getInstance();
        /*
        for (int i = 0; i < 100; i++) {
            id = uuid.generate();
        }*/
        id = uuid.generate();
        System.out.println(id);
        
        String nonce_str = UUIDHexGenerator.generate();//随机字符串  32位长度
        
        System.out.println("随机字符串"+nonce_str);
        
        
    }
}