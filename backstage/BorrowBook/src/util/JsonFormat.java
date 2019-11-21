package util;

public class JsonFormat {
	
	 public static String stringToJson(String s){    
         StringBuffer sb = new StringBuffer();     
         for(int i=0; i<s.length(); i++){     
       
             char c =s.charAt(i);     
             switch(c){     
             case'\"':     
                 sb.append("\\\"");     
                 break;     
//             case'\\':   //如果不处理单引号，可以释放此段代码，若结合下面的方法处理单引号就必须注释掉该段代码
//                 sb.append("\\\\");     
//                 break;     
             case'/':     
                 sb.append("\\/");     
                 break;     
             case'\b':      //退格
                 sb.append("\\b");     
                 break;     
             case'\f':      //走纸换页
                 sb.append("\\f");     
                 break;     
             case'\n':     
                 sb.append("\\n");//换行    
                 break;     
             case'\r':      //回车
                 sb.append("\\r");     
                 break;     
             case'\t':      //横向跳格
                 sb.append("\\t");     
                 break;     
            
             default:     
                 sb.append(c);    
             }}
         return sb.toString();   
         
	 }

}
