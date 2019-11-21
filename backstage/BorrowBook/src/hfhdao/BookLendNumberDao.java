package hfhdao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.Book;
import entity.BookSon;

public class BookLendNumberDao {
  //获得当前book的借出量
  public Integer getBookLendNumber(String bookid){
	   BookDao bookdao=new BookDao();
	   BorrowTableDao boorowtabledao=new BorrowTableDao();
	   Book book= bookdao.getIdBook(bookid);
	   List<BookSon> booksons=new ArrayList<BookSon>(book.getBookson());
	   int number=0;
	   for (int i = 0; i < booksons.size(); i++) {
		 number+=boorowtabledao.getBookSonNum(booksons.get(i).getBooksonid());  
	    }
	  
	  return number;
	  
	  
    } 
	
	
}
