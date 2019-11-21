<%@ page language="java" import="java.util.*,hfhServlet.*,hfhdao.*,entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'library.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/select2.css" />
<link rel="stylesheet" href="css/matrix-style2.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="layui/css/layui.css" />
<link rel="shortcut icon" type="image/x-icon"
	href="http://kunkalabs.com/im/favicon.ico" />
<link rel="stylesheet" type="text/css" href="demo.css" />
<link rel="stylesheet" type="text/css" href="themes/easydropdown.css" />
<link rel="stylesheet" href="css/j-select.css" />
  <link rel="stylesheet" href="dist/sweetalert.css">
  </head>
  
  <body>
    <div id="content">
     <%
                    String bookid=request.getParameter("Bookid");
                    String pc=null;
                    if(request.getParameter("pc")!=null)
                    {
                    pc=request.getParameter("pc");
                    }
                     String booktypeid ="0";
                    String categoryid ="0";
                    String type = "borrow";
                    String mode = "bigsmall";
                    String searchtype="书名";
                    String content="";
                    if(request.getParameter("searchtype")!=null)
                    {
                       searchtype=request.getParameter("searchtype");
                    
                    }
                    if(request.getParameter("content")!=null){
                    
                        content = request.getParameter("content");
                    
                    }
                    if(request.getParameter("booktypeid")!=null)
                     {
                     booktypeid=request.getParameter("booktypeid");
                     }
                     if(request.getParameter("categoryid")!=null)
                     {
                      categoryid=request.getParameter("categoryid");
                     }
                     if(request.getParameter("type")!=null)
                     {
                     type=request.getParameter("type");
                     
                     }
                     if(request.getParameter("mode")!=null)
                     {
                     mode=request.getParameter("mode"); 
                     }
                 
                  //  BookDao bookdao=new BookDao();
                  //  Book book=bookdao.getIdBook(bookid);
                  BookSonDao booksondao = new BookSonDao();
                  List<BookSon> list=booksondao.getListBook(bookid);
     %>
		<div id="content-header1">
			<h1 style="font-weight:bold;">图书详情</h1>
		</div>
		<div style="padding:0 20px;">
		 <div class="row">
		 <%
		    if(request.getParameter("booktypeid")!=null)
		    {
		  %>
               <a href="detailBook.jsp?pc=${pb.pc}&Bookid=<%=bookid%>&booktypeid=<%=booktypeid %>&categoryid=<%=categoryid%>&type=<%=type %>&mode=<%=mode%>"  class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
         <%
           }else{
           if(request.getParameter("action")==null)
           {
          %>  
            <a href="detailBook.jsp?pc=${pb.pc}&Bookid=<%=bookid%>&searchtype=<%=searchtype %>&content=<%=content %>"  class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
          <%
          }else{
          %>
          
           <a href="detailBook.jsp?Bookid=<%=bookid%>&action=isbn"  class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
          <%
          }
          }
           %>  
           </div> 
			<table class="layui-table">
				<colgroup>
					<col width="140">
					<col width="140">
					<col width="120">
					<col width="120">
					
				</colgroup>
			    <thead>
                                     <tr>
                                     <th> 图书编号</th>
                                     <th>书名 </th>
                                     <th> 作者</th>
                                     <th> 出版社</th>
                                     <th>版本号</th>
                                     <th>是否借出</th>
                                     <th>BookId</th>
                                     <th>操作</th>
                                     </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                    if(list!=null&&list.size()>=0)
                                    {
                                           for(int i=0;i<=list.size()-1;i++)
                                   {
  
                                       BookSon bookson=list.get(i);
                                    
                                     %>
                                    <tr>
                                        <td><div style="width:140px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=bookson.getBook().getBookid()%></div></td>
                                        <td><div style="width:140px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=bookson.getBook().getBooktitle() %></div></td>
                                        <td><div style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=bookson.getBook().getAuthor() %></div></td>
                                        <td><div style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=bookson.getBook().getPublish() %></div></td>
                                        <td><%=bookson.getBook().getPublishnumber() %></td>
                                        <%
                                        if(bookson.getBorrowstatus()==0)
                                       {
                                         %>
                                          <td>未借出</td>
                                         <%
                                       }
                                       else
                                       {
                                       %>
                                       <td>已借出</td>
                                      <%
                                         }
                                         %>
                                        <td><%=bookson.getBooksonid() %></td>
                                        <td><a class="example4 layui-btn layui-btn-mini  layui-btn-danger" name="<%=bookson.getBooksonid() %>"><i class="layui-icon ">&#xe640;</i></a></td>
                                     </tr>
                                    <%
                                       }
                                      }
                           
                                    %>
                                    </tbody>
			</table>
		</div>
    <script src="js/excanvas.min.js"></script> 
    <script src="js/jquery.min.js"></script> 
    <script src="js/jquery.ui.custom.js"></script> 
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script> 
    <script src="js/matrix.js"></script>
    <script src="dist/sweetalert-dev.js"></script>   
    <script type="text/javascript">

      document.querySelector('.example4').onclick = function(){
           var name=$(this).attr("name");
            var args={"booksonid":name,"time": new Date()};
              $.post("deleteBookSonServlet",args,function(data){    
                  if(data==1)
                  {    
                         swal({   title: "确定删除这本书吗?",   text: "删除后信息将彻底消失!!!",   type: "warning",   showCancelButton: true,   confirmButtonColor: "#DD6B55",   confirmButtonText: "确定删除",   closeOnConfirm: false }, function(){ 
                       window.location.reload(true);
                  
	  
	});
                    	
                    
                      
                  }
                  else
                  {
                  
                  }
                 });
           return false;

       };

     </script>   
  </body>
</html>
