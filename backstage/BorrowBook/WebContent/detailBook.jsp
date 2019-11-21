<%@ page language="java" import="java.util.*,hfhdao.*,entity.*" pageEncoding="UTF-8"  %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     <script src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>书目详细信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
  </head>
  <body>
  <div id="content">
     <%              
                    String bookid=request.getParameter("Bookid");
                    String  pc = "0";
                    
                    if(request.getParameter("pc")!=null) 
                    {
                    pc=request.getParameter("pc"); 
                    }
                    String searchtype="书名";
                    String content="";
                    String booktypeid ="0";
                    String categoryid ="0";
                    String type = "borrow";
                    String mode = "bigsmall";
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
                 
                     BookDao bookdao=new BookDao();
                    Book book=bookdao.getIdBook(bookid);
                    String position = book.getPosition();
                    String floor = position.substring(0,1);
                    String room = position.substring(1);
     %>
		<div id="content-header1">
			<h1 style="font-weight:bold;">图书详情<%=bookid%></h1>
		</div>
		<div style="padding:0 20px;">
		 <div class="row">
		      <%
		         if(request.getParameter("booktypeid")!=null)
		         {

		       %>
               <a href="BookPageServlet?pc=${pb.pc}&booktypeid=<%=booktypeid %>&categoryid=<%=categoryid%>&type=<%=type %>&mode=<%=mode%>" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
               <a href="library.jsp?Bookid=<%=bookid%>&pc=${pb.pc}&booktypeid=<%=booktypeid %>&categoryid=<%=categoryid%>&type=<%=type %>&mode=<%=mode%>" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;">藏书信息</a> 
              <%
                }else
                {
                    if(request.getParameter("action")==null)
                    {
               %>
                  <a href="BookSearchServlet?pc=${pb.pc}&searchtype=<%=searchtype %>&content=<%=content %>" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
                   <a href="library.jsp?Bookid=<%=bookid%>&pc=${pb.pc}&searchtype=<%=searchtype %>&content=<%=content %>" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;">藏书信息</a> 
               <%
               }else
               {
               %>
                  <a href="BookSearchISBN.jsp?Bookid=<%=bookid%>&action=isbn" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
                  <a href="library.jsp?Bookid=<%=bookid%>&action=isbn" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;">藏书信息</a> 
               
               <%
               }
                }
                %>
           </div> 
			<table class="layui-table">
				<colgroup>
					<col width="400">
				</colgroup>
				<thead>
					<tr>
						<th style="text-align:center;">图书信息<%=bookid %></th>
						<th style="text-align:center;">内容</th>
					</tr>
				</thead>
				     <tbody>
                                   <tr>
                                   <td style="text-align:center">图书位置</td>
                                   <td style="text-align:center;">
                                   <%
                                     if(floor.equals("1"))
                                     {
                                    %>
                                                                                              一层
                                    <%
                                     }
                                     %>
                                   <%
                                     if(floor.equals("2"))
                                     {
                                    %>
                                                                                              二层
                                    <%
                                     }
                                     %>
                                   <%
                                     if(floor.equals("3"))
                                     {
                                    %>
                                                                                              三层
                                    <%
                                     }
                                     %>
                                   <%
                                     if(floor.equals("4"))
                                     {
                                    %>
                                                                                              四层
                                    <%
                                     }
                                     %>
                                   <%
                                     if(floor.equals("5"))
                                     {
                                    %>
                                                                                              五层
                                    <%
                                     }
                                     %>
                                     <%=room %>
                                   </td>
                                   </tr>
                                   <tr>
                                   <td style="text-align:center">目录编号</td>
                                   <td style="text-align:center;"><%=book.getBookid()%></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">书名</td>
                                   <td style="text-align:center;"><%=book.getBooktitle() %></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">作者</td>
                                   <td style="text-align:center;"><%=book.getAuthor() %></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">出版社</td>
                                   <td style="text-align:center;"><%=book.getPublish() %></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">版本号</td>
                                   <td style="text-align:center;"><%=book.getPublishnumber() %></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">图书介绍</td>
                                   <td style="text-align:center;"><%=book.getIntroduce() %> </td>
                                   </tr>
                                    <tr>

                                   <td style="text-align:center">藏书量</td>
                                   <td style="text-align:center;"><%=book.getBooknum()%></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">书评</td>
                                   <td style="text-align:center;"> <%=book.getReview() %></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">导读</td>
                                   <td style="text-align:center;"><%=book.getGuidreading() %></td>
                                   </tr>
            </tbody> 
			</table>
			<%-- <%
			   if(book.getElestatus()==0)
			   {
			 %>
			  <%
		         if(request.getParameter("booktypeid")!=null)
		         {

		       %>
               <a class="layui-btn " style="margin:30px 0 ;margin-left:500px;" href="addebook.jsp?Bookid=<%=bookid%>&pc=${pb.pc}&booktypeid=<%=booktypeid %>&categoryid=<%=categoryid%>&type=<%=type %>&mode=<%=mode%>">上传电子书</a>
              <%
                }else
                {
                    if(request.getParameter("action")==null)
                    {
               %>
                    <a class="layui-btn " style="margin:30px 0 ;margin-left:500px;" href="addebook.jsp?Bookid=<%=bookid%>&pc=${pb.pc}&searchtype=<%=searchtype %>&content=<%=content %>">上传电子书</a>
               <%
               }else
               {
               %>
                    <a class="layui-btn " style="margin:30px 0 ;margin-left:500px;" href="addebook.jsp?Bookid=<%=bookid%>&action=isbn">上传电子书</a>
               
               <%
               }
                }
                %>
			  
			 <%
			 }else{
			  %>
				<table class="layui-table">
				<colgroup>
					<col width="400">
				</colgroup>
				<thead>
					<tr>
						<th style="text-align:center;">电子书信息</th>
						<th style="text-align:center;">内容</th>
					</tr>
				</thead>
				    <tbody>
                                   <tr>
                                   <td style="text-align:center">是否免费</td>
                                   <%
                                     if(book.getFreestatus()==0)
                                     {
                                    %>
                                    
                                   <td style="text-align:center;">是</td>
                                    <%
                                     }else{
                                     %> 
                                    <td style="text-align:center;">否</td>
                                     <%
                                      }
                                      %>
                                   </tr>
                                   <tr>
                                   <td style="text-align:center">VIP是否免费</td>
                                     <%
                                     if(book.getVipfreestatus()==0)
                                     {
                                    %>
                                    
                                   <td style="text-align:center;">是</td>
                                    <%
                                     }else{
                                     %> 
                                    <td style="text-align:center;">否</td>
                                     <%
                                      }
                                      %>   
                                   </tr>
                                   
                                   <tr>
                                   <td style="text-align:center">电子书价格</td>
                                   <td style="text-align:center;"><%=book.getEleprice()%></td>
                                   </tr>
                                   
            </tbody>
			</table>
			<%
			}
			 %> --%>
		</div>
    <script src="js/excanvas.min.js"></script> 
    <script src="js/jquery.min.js"></script> 
    <script src="js/jquery.ui.custom.js"></script> 
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script> 
    <script src="js/matrix.js"></script>      
  </body>
</html>
