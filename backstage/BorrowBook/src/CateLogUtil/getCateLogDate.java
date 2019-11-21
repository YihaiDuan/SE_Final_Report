/**
 * 
 */
package CateLogUtil;


import java.io.IOException;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import entity.Book;

public class getCateLogDate {
	
	
	
	
	public String getCateLogData(String bookid)
	{
		HttpServletRequest request=null;
		
		  BookDao bd=new BookDao();
		  Book b=bd.getBookbyid(bookid);
		  String TxtPath=b.getEletext();	 
	      String filePath="C://Users//BorrowBook//WebContent//txt//";;
		  String Path=filePath+TxtPath;
		try {
			RandomAccessFile rf = new RandomAccessFile(Path, "rw");
			StringBuilder str1=new StringBuilder();
			String line="";
			while((line=rf.readLine())!=null)
			{
				
				String str = line;
				String s = new String(str.getBytes("ISO8859_1"),"utf-8");
				str1.append(s+"\r\n");
				
				
			}
			
			/*for(int i = 0; i <rf; i++)
			{
				
			
			}*/
			
		   
			return str1.toString().replaceAll(" ","");
			
			
			
			
		
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	
	
	//获取章节位置
	public List<CateList>  getCateList(String bookid)
	{
		
	getCateLogDate gcd=new getCateLogDate();
		
		
		
		String str=gcd.getCateLogData(bookid).replaceAll(" ","");
		
		
		List<CateList> clist=new ArrayList<CateList>();
		Pattern p = Pattern.compile("第[一|二|三|四|五|六|七|八|九|十]+章：[\u4e00-\u9fa5]+");
		Matcher m = p.matcher(str);
			
				while(m.find())
				{
					
					int i=0;
					CateList cl=new CateList();
					System.out.println(m.start());
					System.out.println(m.end());
					System.out.println(m.group());
				
					
					cl.setId(i);
					cl.setStart(m.start());
					cl.setEnd(m.end());
					cl.setTitle(m.group());
				
					
				
					clist.add(cl);
					i++;
				
				}
				
				
				if(clist!=null&&clist.size()>0)
				{
					
					for(int j=0;j<clist.size();j++)
					{
						
						if(j<clist.size()-1)
						{
						
						CateList start=clist.get(j+1);
						CateList end=clist.get(j);
						int size=start.getStart()-end.getEnd();
						String content=str.substring(end.getStart(),start.getStart());
						clist.get(j).setEnd(start.getStart());
			            clist.get(j).setSize(size);
						clist.get(j).setContent(content);
						}
						else
						{
							
							
							int size=str.length()-clist.get(j).getEnd();
							clist.get(j).setSize(size);
							String content=str.substring(clist.get(j).getStart(),str.length());
							clist.get(j).setContent(content);
							clist.get(j).setEnd(str.length());
							
						}
						
					}
					
				}
					
					if(clist!=null&&clist.size()>0)
					{
						
						for(int j=0;j<clist.size();j++)
						{
							
							
						
							CateList end=clist.get(j);
							System.out.println(end.getSize());
							System.out.println(end.getContent());
							
						}
							
				  }
		
					
				return clist;
		
		
	}
	
	//获取书本分页索引
	public List<Cate>  getCateIndex(int rows,int cols,String bookid)
	{
		
	
		 getCateLogDate gcd=new getCateLogDate();
		 List<CateList>  clist=gcd.getCateList(bookid);
		 String str=gcd.getCateLogData(bookid).replaceAll(" ","");
			
			System.out.println(str);
		//String str="第一章：黄建明1234512\\r\\n1234512345\\r\\n第二章：黄建明123456\\r\\n1234咯咯咯咯咯gg5121234512345235678888007787766\\r\\n";
			System.out.println(str.length());
		
	   
						
						int pc=0;
					    
				        int start=0;
				        int end=0;
				    
	   List<Cate> blist=new ArrayList<Cate>();
		for(int i=0;i<clist.size();i++)
		{
						
						  if(i==0)
						  {
							  
							  pc=0;
						  }
						  
						  System.out.println("章结尾"+clist.get(i).getEnd());
						  while(end<clist.get(i).getEnd())
						  {
							  pc++;
								end=gcd.getPageSize(rows,cols, start,clist.get(i).getEnd(),str);
					        	System.out.println("结尾"+end);
					        	
					      
					        	 Cate c=new Cate();
					        
					        	 c.setPc(pc);
					        	 c.setStart(start);
					        	 c.setEnd(end);
					        	 c.setCatenum(i);
					        	 c.setTitle(clist.get(i).getTitle());
					        	
					        	  blist.add(c);
					        	 
					        	 start=end;
							  
						  }
			
						
			}
						
						
						

			
			for(int j=0;j<blist.size();j++)
			{
				
				
			
				Cate end1=blist.get(j);
				System.out.println("目录"+end1.getCatenum());
				System.out.println("当前页"+end1.getPc());
				System.out.println("start"+end1.getStart());
				System.out.println("end"+end1.getEnd());
				
				
				
			}
				
	 return blist;
		
		
	
	}
	
	
	//通过屏幕的宽高和区间开始值获得页码的末尾区间
		public int getPageSize(int rows,int cols,int start,int length,String str)
		{
			//int rows=5;
			//int cows=5;
			//int start =0;
		    int han=0;//行
			int index=0;
			int maxsize=rows*cols;
			

		while(han<rows)
		{
			
		
			//行过界
			if(han!=0)
			{
				
				
				if(str.indexOf("\r\n",start)==-1)
				{

					System.out.println("我是越界"+index);
					if(index+2>length)
					{
						return length;
						
					}
					return index+4;
			
					
				}
				else
				{
					
					System.out.println("fdg"+(int) Math.ceil((float)(str.indexOf("\r\n",start)-start+1-2)/cols));
				if(han+(int) Math.ceil((float)(str.indexOf("\r\n",start)-start+1-2)/cols)>rows)
				{
					index=index+2+cols*(rows-han);
					System.out.println("行过界");
					
					if(index>length)
					{
						return length;
						
					}
					return index;
					
				}
				}
			}
			
		
			
		
			
			
			//索引出界
			if(str.indexOf("\r\n",start)==-1)
			{
				
				System.out.println("我是越界"+index);
				if(index+2>length)
				{
					return length;
					
				}
				return index+2;
		
				
			}
			else
			{
				
				index=str.indexOf("\r\n",start);
			}
			System.out.println("更新行后的index"+index);
			
			
			//一行也没有
			if(index>maxsize+start&&han==0)
			{
				
				if(maxsize+start>length)
				{
					return length;
					
				}
				return maxsize+start;
				
			}
			
		
			
			if(han==0)
			{
				
				if((int) Math.ceil((float)(index-start)/cols)==0)
				{
					han=han+1;
				}
				else
				{
			han=han+(int) Math.ceil((float)(index-start)/cols);//行高
				}
			
			}
			else
			{
				if((int) Math.ceil((float)(index-start+1-2)/cols)==0)
				{
					han=han+1;
				}
				else
				{
				han=han+(int) Math.ceil((float)(index-start+1-2)/cols);//行高
				}
			}
			
			
			System.out.println("变化后"+han);
			
			
			
			
			
			start=index+1;
			System.out.println("start"+start);
			
			
		}
		
		if(index+2>length)
		{
			return length;
			
		}
	    return index+2;
			
			
		}
	
	
	public static void main(String []args)
	{
		
		getCateLogDate gcd=new getCateLogDate();
		
	System.out.println(gcd.getCateLogData("1"));
	//System.out.println(gcd.getCateLogData().substring(1096, 1119));
				}
	
}
