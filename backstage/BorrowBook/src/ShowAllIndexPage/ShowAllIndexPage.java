package ShowAllIndexPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Extenddao2.RduceDao;
import IndexPageDao.SearchPageDao;
import dao.BookDao;
import dao.BookSonDao;
import entity.Book;
import entity.BookSon;
import entity.GroupBook;
import entity.Reduce;
import util.JsonFormat;


@WebServlet("/ShowAllIndexPage")
public class ShowAllIndexPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ShowAllIndexPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		       doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		response.setContentType("text/html;charset=utf-8");
		   request.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter() ;
		   
		   
		   
		   
		   
		   String action = request.getParameter("action");
			
			String pc=request.getParameter("pc");
		   
			// 降价

			
//			if (action.equals("1")) {
//				
//				
//				
//				
//				AllPageDao spd=new AllPageDao();
//				
//				entity.Page<Reduce>  pagebean=spd.getReduceData(Integer.parseInt(pc));
//							
//						 
//							
//							StringBuilder str=new StringBuilder();
//							 List<Reduce>  tlist=pagebean.getBeanlist();
//							 
//							 
//							 if(tlist!=null&&tlist.size()>0)
//							  {
//								  
//								  for(int i=tlist.size()-1;i>=0;i--)
//								  {
//									  
//									  
//									  
//									  Reduce to=tlist.get(i);
//									  BookDao bd=new BookDao();
//									  Book b=bd.getBookbyid(to.getBookid());
//									  
//									  
//								     String introduce=null;
//									  
//									  if(b.getIntroduce()!=null)
//								         {
//								        	 
//								        	
//								        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
//								         }
//								         else
//								         {
//								        	 introduce="没有相关内容";
//								        	 
//								         }	 
//							      	 
//									  
//										str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
//										.append(",")
//										.append("\"bookimages\":\""+b.getBookimages()+"\"")
//										.append(",")
//										.append("\"author\":\""+b.getAuthor() +"\"")
//										.append(",")
//										.append("\"typeid\":\""+b.getCategory().getCategoryid()+"\"")
//										.append(",")
//										.append("\"booktitle\":\""+b.getBooktitle()+"\"")
//										.append(",")
//										.append("\"introduce\":\""+introduce+"\"")
//										.append(",")
//										.append("\"borrownum\":\""+b.getBooknum()+"\"")
//										.append(",")
//										.append("\"oldprice\":\""+b.getOldprice()+"\"")
//										.append(",")
//										.append("\"newprice\":\""+b.getNewprice()+"\"")
//										
//										.append("}").append(",");
//							    		  
//									 }
//								  
//								  
//								  out.write("{"+"\"size\":"+"\""+pagebean.getAll()+"\","+"\"curnum\":"+"\""+tlist.size()+"\","+"\"curpage\":"+"\""+pc+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");		
//									
//									System.out.println(str);
//								  
//							  }
//							 else
//							 {
//								 
//									out.write("{" + "\"size\":" + "\"" +0+"\","+"\"curnum\":"+"\""+0+"\""+"}" );			 
//								 
//							 }
//							 
//				          } // 最大if结束*/
			
			
			
			       //组团
//                   if (action.equals("2")) 
//                   
//                   {
//				
//				
//				 AllPageDao spd=new AllPageDao();
//				
//				entity.Page<GroupBook>  pagebean=spd.getGroupData(Integer.parseInt(pc));
//							
//						 
//							
//							StringBuilder str=new StringBuilder();
//							 List<GroupBook>  tlist=pagebean.getBeanlist();
//							 
//							 
//							  if(tlist!=null&&tlist.size()>0)
//							  {
//								  
//								  for(int i=tlist.size()-1;i>=0;i--)
//								  {
//									  
//									  
//									  
//									GroupBook to=tlist.get(i);
//									
//									  
//								     String introduce=null;
//									  
//									  if(to.getBook().getIntroduce()!=null)
//								         {
//								        	 
//								        	
//								        	 introduce=JsonFormat.stringToJson(to.getBook().getIntroduce());
//								         }
//								         else
//								         {
//								        	 introduce="没有相关内容";
//								        	 
//								         }	 
//							      	 
//									  
//										str.append("{").append("\"bookid\":\""+to.getBook().getBookid()+"\"")
//										.append(",")
//										.append("\"bookimages\":\""+to.getBook().getBookimages()+"\"")
//										.append(",")
//										.append("\"author\":\""+to.getBook().getAuthor() +"\"")
//										.append(",")
//										.append("\"typeid\":\""+to.getBook().getCategory().getCategoryid()+"\"")
//										.append(",")
//										.append("\"booktitle\":\""+to.getBook().getBooktitle()+"\"")
//										.append(",")
//										.append("\"introduce\":\""+introduce+"\"")
//										.append(",")
//										.append("\"borrownum\":\""+to.getBook().getBooknum()+"\"")
//										
//										.append("}").append(",");
//							    		  
//									 }
//								  
//								  
//								  out.write("{"+"\"size\":"+"\""+pagebean.getAll()+"\","+"\"curnum\":"+"\""+tlist.size()+"\","+"\"curpage\":"+"\""+pc+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");		
//									
//									System.out.println(str);
//							  }
//							
//							 else
//							 {
//								 
//									out.write("{" + "\"size\":" + "\"" +0+"\","+"\"curnum\":"+"\""+0+"\""+"}" );			 
//								 
//							 }
//							 
//				          } // 最大if结束*/
                   
                   
                   
                   
                   //vip免费
                   if (action.equals("3")) 
                   
                   {
				
				
				 AllPageDao spd=new AllPageDao();
				
				entity.Page<Book>  pagebean=spd.getVipBookData(Integer.parseInt(pc));
							
						 
							
							StringBuilder str=new StringBuilder();
							 List<Book>  tlist=pagebean.getBeanlist();
							 
							 
							  if(tlist!=null&&tlist.size()>0)
							  {
								  
								  for(int i=tlist.size()-1;i>=0;i--)
								  {
									  
									  
									  
									 Book b=tlist.get(i);
									
									  
                                          String introduce=null;
									  
									  if(b.getIntroduce()!=null)
								         {
								        	 
								        	
								        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
								         }
								         else
								         {
								        	 introduce="没有相关内容";
								        	 
								         }	 
							      	 
									  
										str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
										.append(",")
										.append("\"bookimages\":\""+b.getBookimages()+"\"")
										.append(",")
										.append("\"author\":\""+b.getAuthor() +"\"")
										.append(",")
										.append("\"typeid\":\""+b.getCategory().getCategoryid()+"\"")
										.append(",")
										.append("\"booktitle\":\""+b.getBooktitle()+"\"")
										.append(",")
										.append("\"introduce\":\""+introduce+"\"")
										.append(",")
										.append("\"borrownum\":\""+b.getBooknum()+"\"")
										.append(",")
										.append("\"oldprice\":\""+b.getOldprice()+"\"")
										.append(",")
										.append("\"newprice\":\""+b.getNewprice()+"\"")
										
										.append("}").append(",");
							    		  
									 }
								  
								  
								  out.write("{"+"\"size\":"+"\""+pagebean.getAll()+"\","+"\"curnum\":"+"\""+tlist.size()+"\","+"\"curpage\":"+"\""+pc+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");		
									
									System.out.println(str);
							  }
							
							 else
							 {
								 
									out.write("{" + "\"size\":" + "\"" +0+"\","+"\"curnum\":"+"\""+0+"\""+"}" );			 
								 
							 }
							 
				          } // 最大if结束*/
                   
                   
                   //fu费
                   if (action.equals("4")) 
                   
                   {
				
				
				 AllPageDao spd=new AllPageDao();
				
				entity.Page<Book>  pagebean=spd.getEleBookData(Integer.parseInt(pc));
							
						 
							
							StringBuilder str=new StringBuilder();
							 List<Book>  tlist=pagebean.getBeanlist();
							 
							 
							  if(tlist!=null&&tlist.size()>0)
							  {
								  
								  for(int i=tlist.size()-1;i>=0;i--)
								  {
									  
									  
									  
									 Book b=tlist.get(i);
									
									  
                                          String introduce=null;
									  
									  if(b.getIntroduce()!=null)
								         {
								        	 
								        	
								        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
								         }
								         else
								         {
								        	 introduce="没有相关内容";
								        	 
								         }	 
							      	 
									  
										str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
										.append(",")
										.append("\"bookimages\":\""+b.getBookimages()+"\"")
										.append(",")
										.append("\"author\":\""+b.getAuthor() +"\"")
										.append(",")
										.append("\"typeid\":\""+b.getCategory().getCategoryid()+"\"")
										.append(",")
										.append("\"booktitle\":\""+b.getBooktitle()+"\"")
										.append(",")
										.append("\"introduce\":\""+introduce+"\"")
										.append(",")
										.append("\"borrownum\":\""+b.getBooknum()+"\"")
										.append(",")
										.append("\"oldprice\":\""+b.getOldprice()+"\"")
										.append(",")
										.append("\"newprice\":\""+b.getNewprice()+"\"")
										
										.append("}").append(",");
							    		  
									 }
								  
								  
								  out.write("{"+"\"size\":"+"\""+pagebean.getAll()+"\","+"\"curnum\":"+"\""+tlist.size()+"\","+"\"curpage\":"+"\""+pc+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");		
									
									System.out.println(str);
							  }
							
							 else
							 {
								 
									out.write("{" + "\"size\":" + "\"" +0+"\","+"\"curnum\":"+"\""+0+"\""+"}" );			 
								 
							 }
							 
				          } // 最大if结束*/
                   
                   
                   
                   
                   //mian费
                   if (action.equals("5")) 
                   
                   {
				
				
				 AllPageDao spd=new AllPageDao();
				
				entity.Page<Book>  pagebean=spd.getFreeBookData(Integer.parseInt(pc));
							
						 
							
							StringBuilder str=new StringBuilder();
							 List<Book>  tlist=pagebean.getBeanlist();
							 
							 
							  if(tlist!=null&&tlist.size()>0)
							  {
								  
								  for(int i=tlist.size()-1;i>=0;i--)
								  {
									  
									  
									  
									 Book b=tlist.get(i);
									
									  
                                          String introduce=null;
									  
									  if(b.getIntroduce()!=null)
								         {
								        	 
								        	
								        	 introduce=JsonFormat.stringToJson(b.getIntroduce());
								         }
								         else
								         {
								        	 introduce="没有相关内容";
								        	 
								         }	 
							      	 
									  
										str.append("{").append("\"bookid\":\""+b.getBookid()+"\"")
										.append(",")
										.append("\"bookimages\":\""+b.getBookimages()+"\"")
										.append(",")
										.append("\"author\":\""+b.getAuthor() +"\"")
										.append(",")
										.append("\"typeid\":\""+b.getCategory().getCategoryid()+"\"")
										.append(",")
										.append("\"booktitle\":\""+b.getBooktitle()+"\"")
										.append(",")
										.append("\"introduce\":\""+introduce+"\"")
										.append(",")
										.append("\"borrownum\":\""+b.getBooknum()+"\"")
										.append(",")
										.append("\"oldprice\":\""+b.getOldprice()+"\"")
										.append(",")
										.append("\"newprice\":\""+b.getNewprice()+"\"")
										
										.append("}").append(",");
							    		  
									 }
								  
								  
								  out.write("{"+"\"size\":"+"\""+pagebean.getAll()+"\","+"\"curnum\":"+"\""+tlist.size()+"\","+"\"curpage\":"+"\""+pc+"\","+"\"list\":"+"["+str.substring(0,str.length()-1)+"]}");		
									
									System.out.println(str);
							  }
							
							 else
							 {
								 
									out.write("{" + "\"size\":" + "\"" +0+"\","+"\"curnum\":"+"\""+0+"\""+"}" );			 
								 
							 }
							 
				          } // 最大if结束*/
                   
                   
			
	}

}
