package MyTotalUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.DynamicMessage;
import entity.User;


@WebServlet("/getReadBorrowData")
public class getReadBorrowData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public getReadBorrowData() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	    	doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 response.setContentType("application/json;charset=utf-8"); 
		  request.setCharacterEncoding("utf-8");
		  PrintWriter  out=response.getWriter();
		  
		  
		  String userid=request.getParameter("userid");
		  
		 System.out.println(userid);
		  DataDao dd=new DataDao();
		  int readnum=dd.getUserReadNum(userid);
		  int borrownum=dd.getUserBorrowNum(userid);
		  
		  
		  
		  UserDao ud=new UserDao();
		  List<User>  ulist=ud.getAllUser();
		  
		  List<UserReadBorrow> urblist=new ArrayList<UserReadBorrow>();
		  if(ulist!=null&&ulist.size()>0)
		  {
			  
			  
			  for(int i=0;i<ulist.size();i++)
			  {
				  User u=ulist.get(i);
		         UserReadBorrow urb=new UserReadBorrow();
				  int totalnum=dd.getUserReadNum(userid)+dd.getUserBorrowNum(userid);
				  urb.setUserid(u.getUserid());
				  urb.setNum(totalnum);
				  urblist.add(urb);
				  
			  }
			 }
		  //进行降序排序
			
			//根据时间进行降序排序
			
			  Collections.sort(urblist, new Comparator<UserReadBorrow>(){  
				  
		            /*  
		             * int compare(Student o1, Student o2) 返回一个基本类型的整型，  
		             * 返回负数表示：o1 小于o2，  
		             * 返回0 表示：o1和o2相等，  
		             * 返回正数表示：o1大于o2。  
		             */  
		            public int compare(UserReadBorrow o1, UserReadBorrow o2) {  
		              
		                //按照学生的年龄进行升序排列  
		                if(o1.getNum()>o2.getNum()){  
		                    return 1;  
		                }  
		                if(o1.getNum()==o2.getNum()){  
		                    return 0;  
		                }  
		                return -1;  
		            }  
		        });   
		  
			  int order=0;
			if(urblist!=null&&urblist.size()>0)
			{
				
				for(int i=0;i<urblist.size();i++)
				{
					UserReadBorrow urb=urblist.get(i);
					
				
					if(urb.getUserid().equals(userid))
					{
						order=i+1;
						break;
					}
					
				}
				
			
				
			}
			  
		  //超过了
			float dis=(float)order/urblist.size();
			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");   
		  System.out.println(dis);
			
out.write("{"+"\"readnum\":"+"\""+readnum+"\","+"\"borrownum\":"+"\""+borrownum+"\","+"\"dis\":"+"\""+df.format( dis*100)+"\""+"}");
		
	}

}
