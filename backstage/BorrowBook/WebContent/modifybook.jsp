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
<style type="text/css">
 	.prompt{
    		display:none;
    	  position:fixed;
    	  top:180px;
    	  left:540px;	
          width:500px;
          min-height:240px;
          background:#dfecfa;
          z-index:100;
          padding:40px;
          text-indent:2em;
          font-size:16px;
    	}
    	.chahao{
    	 position:absolute;
    	 top:10px;
    	 right:10px;
    	 width:20px;
    	 height:20px;
    	}
    	.look{
    		cursor: pointer;
    		color:#AEC2D4;
    	}
    	.look:hover{
    		
    		color:#68B3C8;
    	
    	}
    	.search{
    		float:right;
    		border:2px solid #f4ea2a;
    		height:28px;
    		
    	}
    	.searchtext{
    		width:170px;
    		
    		border:0;
    		font-size:12px;
    		color:#eee;
    	}
    	.searchsubmit{
    	   border:0;
    	   width:28px;
    	   height:28px;
    	   border-left:1px solid #F4EA2A;
    	   background:url("assets/img/搜索 .png");
    	    background-size: 100%;
    	    
    	}
    	.da
        {
      float:left;
      margin-top:20px;
      padding:4px 7px 0 20%;
      font-size:13px;
      	font-family:微软雅黑
        }
   .deleteimage{
    		width:30px;
    		height:30px;
    		
    		
    	}
    	#addbook{
    	 float:right;
    	 line-height:30px;
    	 margin-left:10px;
    	 color:#6cbbd1;
    	 cursor: pointer;
    	}
    	#addbook:hover{
    	color:#3a96b0;
    	}
    	#addbox{
    	display:none;
    	  position:fixed;
    	  top:180px;
    	  left:540px;	
          width:500px;
          min-height:240px;
          background:#dfecfa;
          z-index:100;
          padding:40px;
          text-indent:2em;
          font-size:16px;
    	}
    	.box{
    	 width:300px;
    	 height:200px;
    	 background:#E6F1FB;
    	 position:fixed;
    	 left:50%;
    	 margin-left:-150px;
    	 top:50%;
    	 margin-top:-100px;
    	 z-index:100;
    	}
    	.image{
    	  width:18px;
    	  height:18px;
    	}
    	td{
    	text-align:center;
    	}
    	.return,.putin
    	{
    	   display:inline-block;
    	   background:#68b3c8;
    	   color:#fff;
    	   padding:6px 10px;
    	   border-radius:8px;
    	   margin-right:10px;
    	   font-size:13px;
    	}
    	.putin:hover{
    	  background:#31708f;
    	  color:#fff;
    	   cursor: pointer;
    	}
    	.return:hover{
    	 background:#31708f;
    	  color:#fff;
    	   cursor: pointer;
    	}
    	.select1{
    	  padding:4px;
    	
    	}
    	#add{
    	cursor:pointer;
    	}
    	.position{
    	 width
    	
    	}
    	.floor,.room{
    	
    	width:134px;
    	
    	}
    	.j_select .current_select .select_icon {
       margin: 0;
       padding: 0;
       position: absolute;
       right: 15px;
       top: 10px;
       }
       input[type="text"]{
       height:30px;
       width:272px;
       }
       input[readonly]{
       background:#fff;
       }
       .layui-select-title{
       padding-top:16px;
       }
    .layui-form-select .layui-edge {
    position: absolute;
    right: 209px;
    top: 55%;
    cursor: pointer;
    border-width: 6px;
    border-top-color: #c2c2c2;
    border-top-style: solid;
    transition: all .3s;
    -webkit-transition: all .3s;
}
.layui-form-select dl {
    display: none;
    position: absolute;
    left: 193px;
    top: 50px;
    padding: 5px 0;
    z-index: 999;
    min-width: 272px;
    border: 1px solid #d2d2d2;
    max-height: 300px;
    overflow-y: auto;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12);
    box-sizing: border-box;
}
 .layui-form-selected dl {
    display: block;
}
.myselect{
 display:inline-block;
 width:272px;
 height:30px;
 line-height:30px;
 overflow:hidden;
 position:relative;
 font-size:18px;
 
}
.floorselect,.roomselect{
 display:inline-block;
 width:134px;
 height:30px;
 line-height:30px;
 overflow:hidden;
 position:relative;
 font-size:18px;
}
.xiajiantou{
position:absolute;
right:1px;
top:1px;
width:37px;
height:28px;
}
a{
text-decoration:none;
}
</style>
  </head>
  <body>
  <div id="content">
     <%              
                    String bookid=request.getParameter("Bookid");
                    String pc=request.getParameter("pc");
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
                    String floor = position.substring(0,3);
                    String room  = position.substring(3);
     %>
		<div id="content-header1">
			<h1 style="font-weight:bold;">修改图书</h1>
		</div>
		<div style="padding:0 20px;">
		 <div class="row">
		      <%
		         if(request.getParameter("booktypeid")!=null)
		         {

		       %>
               <a href="BookPageServlet?pc=${pb.pc}&booktypeid=<%=booktypeid %>&categoryid=<%=categoryid%>&type=<%=type %>&mode=<%=mode%>" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
               <a href="modifylibrary.jsp?Bookid=<%=bookid%>&pc=${pb.pc}&booktypeid=<%=booktypeid %>&categoryid=<%=categoryid%>&type=<%=type %>&mode=<%=mode%>" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;">藏书信息</a> 
              <%
                }else
                {
                    if(request.getParameter("action")==null)
                    {
               %>
                  <a href="BookSearchServlet?pc=${pb.pc}&searchtype=<%=searchtype %>&content=<%=content %>" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
                   <a href="modifylibrary.jsp?Bookid=<%=bookid%>&pc=${pb.pc}&searchtype=<%=searchtype %>&content=<%=content %>" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;">藏书信息</a> 
               <%
               }else
               {
               %>
                  <a href="BookSearchISBN.jsp?Bookid=<%=bookid%>&action=isbn" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
                  <a href="modifylibrary.jsp?Bookid=<%=bookid%>&action=isbn" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;">藏书信息</a> 
               
               <%
               }
                }
                %>
           </div> 
           <form action="ModifyBookServlet?pc=<%=pc%>&booktypeid=<%=booktypeid%>&categoryid=<%=categoryid%>&type=<%=type%>&mode=<%=mode%>" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
			<table class="layui-table">
				<colgroup>
					<col width="400">
				</colgroup>
				<thead>
					<tr>
						<th style="text-align:center;">图书信息</th>
						<th style="text-align:center;">内容</th>
					</tr>
				</thead>
				    <tbody>
                                   <tr>
                                   <td style="text-align:center">图书位置</td>
                                   <td style="text-align:center;">
                                           <div class="floorselect">                                    
                                    	<select name="floor" class="floor form-control" style="display:inline-block;border-radius:0;height:30px;line-height:30px;font-size:13px;">
			                                 <option value="第一层" 
			                                 <%
			                                  if(floor.equals("第一层"))
			                                  {
			                                  %>
			                                  selected="selected"
			                                  <%
			                                   }
			                                   %>
			                                 >第一层</option>
			                                 <option value="第二层" 
			                                  <%
			                                  if(floor.equals("第二层"))
			                                  {
			                                  %>
			                                  selected="selected"
			                                  <%
			                                   }
			                                   %>
			                                 >第二层</option>
			                                 <option value="第三层"
			                                  <% 
			                                  if(floor.equals("第三层"))
			                                  {
			                                  %>
			                                  selected="selected"
			                                  <%
			                                   }
			                                   %>
			                                 >第三层</option>
			                                 <option value="第四层"
			                                  <% 
			                                  if(floor.equals("第四层"))
			                                  {
			                                  %>
			                                  selected="selected"
			                                  <%
			                                   }
			                                   %>
			                                 >第四层</option>
			                                 <option value="第五层"
			                                  <%
			                                  if(floor.equals("第五层"))
			                                  {
			                                  %>
			                                  selected="selected"
			                                  <%
			                                   }
			                                   %>
			                                 >第五层</option>
		                                 </select>
		                                  <image src="images/xiajiantou.png" class="xiajiantou"></image>
		                                 </div>  
		                                 <div class="roomselect">
		                                 <select name="room" class="room form-control" style="display:inline-block;border-radius:0;height:30px;line-height:30px;font-size:13px;">
			                                 <option value="101">101室</option>
			                                 <option value="102">102室</option>
			                                 <option value="103">103室</option>
			                                 <option value="104">104室</option>
		                                 </select>
		                                  <image src="images/xiajiantou.png" class="xiajiantou"></image>
		                                 </div>
                                   </td>
                                   </tr>
                                   <tr>
                                   <td style="text-align:center">目录编号</td>
                                   <td style="text-align:center;"><input type="text" class="bookid" style="margin:8px 0;width:272px;height:30px;" name="bookid" value="<%=book.getBookid()%>"></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">书名</td>
                                   <td style="text-align:center;"><input type="text" class="booktitle" style="margin:8px 0;width:272px;height:30px;" name="booktitle" value="<%=book.getBooktitle() %>"></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">作者</td>
                                   <td style="text-align:center;"><input type="text" class="author" style="margin:8px 0;width:272px;height:30px;" name="author" value="<%=book.getAuthor() %>"></td>
                                   </tr>
                                    <tr>
                                    <td>封面上传</td>
                                    <td style="margin:0 auto;"><input type="file" class="bookimages" style="margin:8px 0;display:inline-block;width:272px;" name="bookimages" ></td>
                                    </tr>
                                    <tr>
                                   <td style="text-align:center">出版社</td>
                                   <td style="text-align:center;"><input type="text" class="publish" style="margin:8px 0;width:272px;height:30px;" name="publish" value="<%=book.getPublish() %>"></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">版本号</td>
                                   <td style="text-align:center;"><input type="text" class="publishnumber" style="margin:8px 0;width:272px;height:30px;" name="publishnumber" value="<%=book.getPublishnumber() %>"></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">图书介绍</td>
                                   <td style="text-align:center;"><textarea  class="introduce" style="margin:8px 0;width:400px;height:100px;" name="introduce"><%=book.getIntroduce() %></textarea> </td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">书评</td>
                                   <td style="text-align:center;"><textarea class="review" style="margin:8px 0;width:400px;height:100px;" name="review"><%=book.getReview() %></textarea></td>
                                   </tr>
                                    <tr>
                                   <td style="text-align:center">导读</td>
                                   <td style="text-align:center;"><textarea class="guidreading" style="margin:8px 0;width:400px;height:100px;" name="guidreading"><%=book.getGuidreading() %></textarea></td>
                                   </tr>
                                   <tr  >
                                   <td colspan="2">
                                      <input type="submit" class="return" value="修改上传" style="border:0;"/>
                                   </td>
                                   </tr>
            </tbody>
			</table>
			</form>
		</div>
    <script src="js/excanvas.min.js"></script> 
    <script src="js/jquery.min.js"></script> 
    <script src="js/jquery.ui.custom.js"></script> 
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script> 
    <script src="js/matrix.js"></script>      
    <script type="text/javascript">
        $("dd").click(function(){
           alert("点击了");
               var floor=$(this).text();       
               	if(floor=="第一层")
			{
				var a=["101室","102室","103室","104室"];
				 $(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
				
				
			}
			if(floor=="第二层")
			{
				var a=["201室","202室","203室","204室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
			}
			if(floor=="第三层")
			{
				var a=["301室","302室","303室","304室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
			}
			if(floor=="第四层")
			{
				var a=["401室","402室","403室","404室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}	
			}
				if(floor=="第五层")
			{
				var a=["501室","502室","503室","504室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}	
			}
    
           });
             var inforation = '<%=floor%>';
             var roominforation = '<%=room%>';
             if(inforation=="第一层")
			{
            	 var a=["101室","102室","103室","104室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
				    if(roominforation == a[i])
				    {
					$(".room").append("<option value='"+a[i]+"'  selected = 'selected'>"+a[i]+"</option>");	
					}else{
					$(".room").append("<option value='"+a[i]+"' >"+a[i]+"</option>");	
					}
				}
			}
			if(inforation=="第二层")
			{
				var a=["201室","202室","203室","204室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
				     if(roominforation == a[i])
				    {
					$(".room").append("<option value='"+a[i]+"'  selected = 'selected'>"+a[i]+"</option>");	
					}else{
					$(".room").append("<option value='"+a[i]+"' >"+a[i]+"</option>");	
					}	
				}
				
			}
			if(inforation=="第三层")
			{
				var a=["301室","302室","303室","304室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					   if(roominforation == a[i])
				    {
					$(".room").append("<option value='"+a[i]+"'  selected = 'selected'>"+a[i]+"</option>");	
					}else{
					$(".room").append("<option value='"+a[i]+"' >"+a[i]+"</option>");	
					}	
				}
				
			}
			if(inforation=="第四层")
			{
				var a=["401室","402室","403室","404室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					   if(roominforation == a[i])
				    {
					$(".room").append("<option value='"+a[i]+"'  selected = 'selected'>"+a[i]+"</option>");	
					}else{
					$(".room").append("<option value='"+a[i]+"' >"+a[i]+"</option>");	
					}	
				}	
			}
				if(inforation=="第五层")
			{
				var a=["501室","502室","503室","504室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
				   if(roominforation == a[i])
				    {
					$(".room").append("<option value='"+a[i]+"'  selected = 'selected'>"+a[i]+"</option>");	
					}else{
					$(".room").append("<option value='"+a[i]+"' >"+a[i]+"</option>");	
					}	
				}	
			}
             $(".floor").change(function(){
			var floor=$(".floor").find("option:selected").val();
			if(floor=="第一层")
			{
				var a=["101室","102室","103室","104室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
				
				
			}
			if(floor=="第二层")
			{
				var a=["201室","202室","203室","204室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
			}
			if(floor=="第三层")
			{
				var a=["301室","302室","303室","304室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}
				
			}
			if(floor=="第四层")
			{
				var a=["401室","402室","403室","404室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}	
			}
				if(floor=="第五层")
			{
				var a=["501室","502室","503室","504室"];
				$(".room").empty();
				for(var i=0;i<a.length;i++)
				{	
					$(".room").append("<option value='"+a[i]+"'>"+a[i]+"</option>");	
				}	
			}
			});
    
    </script>
  </body>
</html>
