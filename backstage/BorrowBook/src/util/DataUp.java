package util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import dao.BookDao;
import dao.BookSonDao;
import dao.CategoryDao;
import entity.Book;
import entity.BookSon;
import entity.Category;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DataUp 
{
	
	public  void  readerExcel(String url)
	{

		
	
		InputStream is=null;
		Sheet sheet=null;
		Workbook book=null;
	
		
		String  excelFile=url;

		try {
			
			
		is=new FileInputStream(excelFile);
		    
		book=Workbook.getWorkbook(is);
		
		sheet =book.getSheet(0);
			
			
			
			int rows=sheet.getRows();
			int cols=sheet.getColumns();
			
			
	
		
		
		
		for(int i=0;i<rows-1;i++)	
		{
			
			
			Cell[] cells=sheet.getRow(i+1);
			
			
			
		
			String bookid=cells[0].getContents();
			
			
		
			//0bookid	1booktitle	2author	3cash	4publish	5publishnumber	6guidreading	7introduce	8review	9bookimages	10list	11bookmain	12booktype
		
		String name=null;
		String booktitle=null;
			
		
		name=cells[1].getContents();
		String array []=name.split("[£¨]");
		
		
		
		
		if(array.length>0)
		{
			booktitle=array[0];
			   
		}
		else
		{
			
			booktitle="Î´Öª";
			
			
		}
		
		
	
		
		  String author=null;
		  
		   if(cells[2].getContents()==null||cells[2].getContents().isEmpty())
           {
        	   
        		author="Î´Öª";
           	
           }
           else
           {
           
        	   author=cells[2].getContents();
           	
           }
		  
		  
		  
		//  double cash=Double.valueOf(cells[3].getContents());
		  double cash=0.00;
           
           if(cells[3].getContents()==null||cells[3].getContents().isEmpty())
           {
        	   
        		cash=35.00;
           	
           }
           else
           {
           
           	cash=Double.valueOf(cells[3].getContents());
           	
           }
		  
		  
		  
		  //String publish=cells[4].getContents();
		  
		  String publish=null;
		  
		   if(cells[4].getContents()==null||cells[4].getContents().isEmpty())
          {
       	   
			   publish="Î´Öª";
          	
          }
          else
          {
          
        	  publish=cells[4].getContents();
          	
          }
		  
		  
		  
		  
		  
		  
		 // String publishnumber=cells[5].getContents();
		  
		  String  publishnumber=null;
		  
		   if(cells[5].getContents()==null||cells[5].getContents().isEmpty())
         {
      	   
			   publishnumber="Î´Öª";
         	
         }
         else
         {
         
        	 publishnumber=cells[5].getContents();
         	
         }
			
		
			
		     String guidreading=null;
	            
	            if(cells[6].getContents()==null||cells[6].getContents().isEmpty())
	            {
	            	guidreading="Î´Öª";
	            	
	            	
	            }
	            else
	            {
	            	guidreading=cells[6].getContents();
	            	
	            	
	            }
	            
	            
	            
	            String introduce=null;
	            
	            if(cells[7].getContents()==null||cells[7].getContents().isEmpty())
	            {
	            	introduce="Î´Öª";
	            	
	            	
	            }
	            else
	            {
	            	introduce=cells[7].getContents();
	            	
	            	
	            }
	            
	            
	            

			
			
            
          String review=null;
            
            if(cells[8].getContents()==null||cells[8].getContents().isEmpty())
            {
            	review="Î´Öª";
            	
            	
            }
            else
            {
            	review=cells[8].getContents();
            	
            	
            }
            
            
            String bookimages=null;
            
            if(cells[9].getContents()==null||cells[9].getContents().isEmpty())
            {
            	bookimages="http://img11.360buyimg.com/n1/jfs/t3097/172/3957519331/214885/1f28bfda/57fb67afN37195ad8.jpg";
            	
            	
            }
            else
            {
            	bookimages=cells[9].getContents().replaceAll("\"\\sdata-url=[\\s\\S]+", "");
            	bookimages="http:"+bookimages;
            	
            }
            
       
        	
            
        	
        	
            
            
	String booklist=null;
        
        if(cells[10].getContents()==null||cells[10].getContents().isEmpty())
        {
        	booklist="Î´Öª";
        	
        	
        }
        else
        {
        	booklist=cells[10].getContents();
        	
        	
        }
        
        
        
        String bookmain=null;
        
        if(cells[11].getContents()==null||cells[11].getContents().isEmpty())
        {
        	bookmain="Î´Öª";
        	
        	
        }
        else
        {
        	bookmain=cells[11].getContents();
        	
        	
        }
        
    	//String booktype=cells[12].getContents();
    	
    	
			
         CategoryDao cd=new CategoryDao();
            
            List<Category>  clist=cd.getAllCategory();
            
            String booktype=null;
     	   System.out.println(cells[12].getContents());
            
            if(clist!=null&&clist.size()>0)
            {
            	
            	for(int j=0;j<clist.size();j++)
            	{
            		
            	   Category c=clist.get(j);
            	   
            
            
            		
            	   
            	   if(c.getName().equals(cells[12].getContents()))
            	   {
            		   System.out.println(c.getName());
            		   booktype=c.getCategoryid();
            		   break;
            	  }
            	
            	
            	}
          
            	
            }
            
            String position="µÚ¶þ²ã 303ÊÒ";
            String oldprice="0.5";
            String evaluatescore="6.0";
     
            if(booktype!=null)
            {
            //Í¼Êé´æÔÚ
            	
            	
        	 System.out.println(bookid);
        	 System.out.println(booktype);
      
            
           
        Category c=cd.getCategoryByid(booktype);

            
            BookDao bd=new BookDao();
            Book b=new Book();
    
          
	        //System.out.println(s);
            
            b.setCash(cash);
            b.setBookid(bookid);
			b.setBooktitle(booktitle);
			
		    ChineseSpelling finder = ChineseSpelling.getInstance();        
			   finder.setResource(booktitle);  
	            
			b.setChinesespelling(finder.getSpelling());
			b.setBookimages(bookimages);
		    b.setAuthor(author);
			b.setIntroduce(introduce);
			b.setList(booklist);
			b.setPublishnumber(publishnumber);
			b.setPublish(publish);
			b.setReview(review);
			b.setAuthor(author);
			b.setGuidreading(guidreading);
			b.setPosition(position);
			b.setCategory(c);
			b.setEvaluatescore(evaluatescore);
			b.setBookmain(bookmain);
			b.setBookkey(cells[12].getContents());
			b.setElestatus(0);
			b.setVipfreestatus(0);
			b.setBorrownum(0);
			b.setEleprice(0.5);
			b.setFreestatus(0);
			b.setTotlenum(5);
			b.setRemainnum(0);
			b.setNewprice(oldprice);
			b.setOldprice(oldprice);
			
			bd.SaveBookData(b);
			
         String bookson[]={"1","2","3","4","5"};
			
			for(int j=0;j<bookson.length;j++)
			{
				
				
			String booksonid=bookid+bookson[j];
			
			 BookSonDao bso=new BookSonDao();
			 BookSon bs=new  BookSon();
		    
		     BookDao bdo=new BookDao();
		     Book b1=bdo.getBookbyid(bookid);
		     
		        bs.setBook(b1);
		        bs.setBooksonid(booksonid);
		        
		        bso.SaveBookSonData(bs);
		    
				
			    }
			  }
       
		}
		
		} 
		
		
		catch (Exception e) 
		{
		e.printStackTrace();
		}
		finally
		{
			if(book!=null)
			{
				book.close();
				
				
			}
			
			
		}
		
		
	
	
	}
	
	
	public  void  readerExcel2()
	{

		
	
		InputStream is=null;
		Sheet sheet=null;
		Workbook book=null;
	
		
		String  excelFile="C://Users//Administrator//Desktop//"+"type.xls";

		try {
			
			
		is=new FileInputStream(excelFile);
		    
		book=Workbook.getWorkbook(is);
		
		sheet =book.getSheet(0);
			
			
			
			int rows=sheet.getRows();
			int cols=sheet.getColumns();
			
			
	
		
		
		
		for(int i=0;i<rows-1;i++)	
		{
			
			
			Cell[] cells=sheet.getRow(i+1);
			
			
			
		
			String id=cells[0].getContents();
			String name=cells[1].getContents();
			String classname=cells[2].getContents();
			
			System.out.println(id);
			System.out.println(name);
			System.out.println(classname);
			
			Category c=new Category();
			
		
			 c.setCategoryid(id);
			 c.setName(name);
			 c.setTypeclass(classname);
			 
			 CategoryDao cd=new CategoryDao();
			 cd.SaveCategory(c);
			
            
       
		}
		
		} 
		
		
		catch (Exception e) 
		{
		e.printStackTrace();
		}
		finally
		{
			if(book!=null)
			{
				book.close();
				
				
			}
			
			
		}
		
		
	
	
	}
	
	
	/* public static void main(String[] args) {        
	     
	      ChineseSpelling finder = ChineseSpelling.getInstance();        
	      finder.setResource("ï¿½Æ½ï¿½ï¿½ï¿½");        
	       System.out.println(finder.getSpelling());  
		 
		DataUp d=new DataUp();
		d.readerExcel();
		d.readerExcel2();
		
		

		
	 }*/
	

}
