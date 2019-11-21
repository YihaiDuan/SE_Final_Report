<%@ page language="java" import="java.util.*,hfhServlet.*,hfhdao.*,entity.*,Extenddao.*"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'changespecialdetail.jsp' starting page</title>
    
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
 <link rel="stylesheet" href="dist/sweetalert.css">
<link rel="stylesheet" href="css/j-select.css" />
<style type="text/css">
.topicimages{
width:80px;
}
.select2-container {
	display: none;
}

.j_select {
	float: left;
	margin-left: 320px;
	border-right: 0;
}

.j_select .current_select .select_icon {
	top: 9px;
}
	.layui-form-checkbox {
		height:24px;
		line-height:22px;
		margin-right:0;
	}
	.layui-form-checkbox span {
   font-size:11px;
}
  .tatb td{
  	height:40px;
  	line-height:40px;
  	
  	
  }
  .layui-input{
  	height:31px;
  	
  }
  input[type="text"]
  {
  	height:32px;
  	background:#fff;
  	width:263px;
  }
  #city_select{
   width:120px;
  }
  .layui-form-select .layui-edge {
    position: absolute;
    right: 160px;
    top: 50%;
    margin-top: -7px;
    cursor: pointer;
    border-width: 6px;
    border-top-color: #c2c2c2;
    border-top-style: solid;
    transition: all .3s;
    -webkit-transition: all .3s;
}
.layui-form-select dl
{
	min-width:263px;
	
}
.layui-form-item{
	margin-bottom:0;
}
	.layui-form-checkbox {
		height:24px;
		line-height:22px;
		margin-right:0;
	}
	.layui-form-checkbox span {
   font-size:11px;
}
  .tatb td{
  	height:40px;
  	line-height:40px;
  	
  	
  }
  .layui-input{
  	height:31px;
  	
  }
  input[type="text"]
  {
  	height:32px;
  	background:#fff;
  	width:263px;
  }
  .layui-form-select .layui-edge {
    position: absolute;
    right: 160px;
    top: 50%;
    margin-top: -7px;
    cursor: pointer;
    border-width: 6px;
    border-top-color: #c2c2c2;
    border-top-style: solid;
    transition: all .3s;
    -webkit-transition: all .3s;
}
.layui-form-select dl
{
	min-width:80px;
	
}
.layui-form-item{
	margin-bottom:0;
}
input[type="checkbox"]{
display:none;
}
.layui-form{
    display:block;
}
input[type="text"] {
    height: 27px;
    background: #fff;
    width: 263px;
}
.slideimage{
    width:20px;
}
.bigslideimage{
display:none;
 width:160px;
 position:absolute;
 top:0;
 left:0;
 z-index:1000;
}
.layui-layer-content{
  padding:8px;
  text-indent:2em;
}
#prompt{
    	  display:none;
    	  position:fixed;
    	  top:90px;
    	  left:50%;
    	  margin-left:-200px;	
          width:300px;
          background:#dfecfa;
          z-index:100;
          padding:20px;
          color:#999;
    	}
    	#chahao{
    	 position:absolute;
    	 top:10px;
    	 right:10px;
    	 width:16px;
    	 height:16px;
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
    		font-size:16px;
    		margin-right:20px;
    	}
    	.search:hover{
    		color:#204D74;	
    	}
    	.searchtext{
    		width:180px;
    		border:0;
    		font-size:12px;
    		color:#eee;
    	}
    	.searchsubmit{
    	   border:0;
    	   width:26px;
    	   height:26px;
    	   border-left:1px solid #F4EA2A;
    	   background:url("assets/img/搜索 .png");
    	    background-size: 100%;
    	    
    	}
    	.deleteimage{
    		width:30px;
    		height:30px;
    		
    		
    	}
    	.addtop{
    		font-size:18px;
    		line-height:40px;
    		text-align: center;
    	
    	}
    	.one,.tow{
    		margin:10px 20px;
    		text-align: center;	
    	}
    	.tow{
    		margin-bottom:20px;
    		
    	}
    	.addsubmit{
    		border:0;
    		background:#204D74;
    		padding:6px 30px;
    		color:#fff;
    		margin-left:100px;
    	}
    	#addmanage{
    		color:#46B8DA;
    		cursor: pointer;
    		
    		
    	}
    	#addmanage:hover{
    		color:#23527C;
    		
    	}
    	.see{
    	   color:#F86731;
    	   text-align:center;
    	}
</style>
  </head>
  
  <body>
     <div id="content">
     <div id="content-header1">
			<h1 style="font-weight:bold;">专题图书</h1>
		</div>
		 <div class="row" style="margin-left:10px;">
               <a href="changetopic.jsp?id=<%=request.getParameter("id")%>" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:25px;">返回前页</a> 
         </div> 
		<div style="padding:0 20px;">
<table class="layui-table" >
  <colgroup>
    <col width="60">
    <col width="160">
    <col width="180">
    <col width="120">
    <col width="120">
  </colgroup>
				  <thead>
    <tr>
      <th>索书号</th>
      <th>书名</th>
      <th>作者</th>
      <th>藏书量</th>
      <th>操作</th>
    </tr> 
  </thead>
				<tbody id="table">			                   
                 <% 
                    int id = Integer.parseInt(request.getParameter("id"));
                    TopicBookDao topicdao = new TopicBookDao();
                    List<TopicBook> list = new ArrayList<TopicBook>(topicdao.getListTopicBook(id));
                    if(list!=null && list.size()>=0)
                    {
                       for(int i=0 ; i<list.size(); i++)
                       {
                           TopicBook topicbook = list.get(i);
                           TopicBookDao topicbookdap = new TopicBookDao();
                          Book b = topicbookdap.getBook(topicbook.getId());      
                  %>
					<tr >

						<td><div
								style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=b.getBookid()%></div></td>
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=b.getBooktitle()%></div></td>
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=b.getAuthor()%></div></td>
					
						<td><%=b.getBooknum() %></td>
					    <td><a href="DeleteTopicBookServlet?topicbookid=<%=topicbook.getId()%>&id=<%=id %>" name="<%=topicbook.getId()%>" id="<%=id%>"
							class="layui-btn layui-btn-mini  layui-btn-danger delete"
							style="padding:0 12px;"><i class="layui-icon ">&#xe640;</i></a></td>
					</tr>
					<%
					
					}
					}
					 %>
				</tbody>
			</table>
			<div class="content" style="margin-top:90px;">
            <form action="TopicBookSearchServlet?id=<%=id%>" method="post">
			<div class="form">
				<select id="city" class="select" name="searchtype" style="width:80px;">
					<option value="书名">书名</option>
					<option value="ISBN">ISBN</option>
				</select>
			</div>
			<input type="text" class="inputtext" name="content"
				style="height:32px;line-height:32px;box-shadow: 1px 1px 2px #ccc;" />
			<input type="submit"
				class="layui-btn layui-btn-small layui-btn-normal" value="确定"
				style="margin-top:-10px;padding:0 24px; height:32px;line-height:32px;">
			 </form>
		</div>


		<form action="TopicBookSerchServlet?id=<%=id%>" method="post">
			<table class="layui-table">
				<colgroup>
					<col width="160">
					<col width="200">
					<col width="180">
					<col width="160">
					<col width="120">
					<col>
				</colgroup>
				<thead>
					<tr>		   
						<th>索书号</th>
						<th>书名</th>
						<th>作者</th>
						<th>出版社</th>
						<th>藏书量</th>	
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					
					   <% 
					       if(request.getAttribute("list")!=null)
					       {
					         List<Book> listbook =(List<Book>)request.getAttribute("list");
					         for(int i=0;i<listbook.size();i++)
					         {
					           Book book = listbook.get(i);
					   %>
					   <tr>
						<td><%=book.getBookid()%></td>
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getBooktitle()%></div></td>
						<td><div
								style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getAuthor()%></div></td>
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getPublish()%></div></td>
						<td><div
								style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getBooknum()%></div></td>
					    <td><a href="#" name="<%=book.getBookid() %>"
							class="layui-btn layui-btn-mini layui-btn-normal add"
							style="padding:0 12px;"><i class="layui-icon ">&#xe654;</i></a></td>
					</tr>
					<%
					}
					}
					 if(request.getAttribute("book")!=null)
					  {
					  Book book = (Book)request.getAttribute("book");
					 %>
					  <tr>
						<td><%=book.getBookid()%></td>
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getBooktitle()%></div></td>
						<td><div
								style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getAuthor()%></div></td>
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getPublish()%></div></td>
						<td><div
								style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getBooknum()%></div></td>
					    <td><a href="#" name="<%=book.getBookid() %>"
							class="layui-btn layui-btn-mini layui-btn-normal add"
							style="padding:0 12px;"><i class="layui-icon ">&#xe654;</i></a></td>
					</tr>
					 
					 
					 <%
					 }
					  %>
				</tbody>
			</table>
			</form>
		
		</div>
			</div>

   <script src="js/excanvas.min.js"></script> 
    <script src="js/jquery.min.js"></script> 
    <script src="js/jquery.ui.custom.js"></script> 
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script> 
    <script src="js/matrix.js"></script> 
    <script src="js/jquery-jSelect.min.js"></script>
    <script src="layui/layui.js"></script>
<script src="js/maruti.html"></script>
<script src="layui/lay/modules/layer.js"></script>
<script src="dist/sweetalert.min.js"></script>
    <script src="dist/sweetalert-dev.js"></script>
    <script type="text/javascript">
      $(".add").click(function(){
     
       var args={"id":<%=id%>,"bookid":$(this).attr("name"),"time":new Date()};
       $.post("AddTopicBookServlet",args,function(data){
       if(data!=1)
       {
              news=eval("("+data+")");
              console.info(news);
           $("#table").append("<tr><td>"+news.bookid+"</td><td>"+news.booktitle+"</td><td>"+news.author+"</td><td>"+news.boknum+"</td><td><a href=\"DeleteTopicBookServlet?topicbookid="+news.topicbookid+"&id="+<%=id %>+"\" class=\"layui-btn layui-btn-mini  layui-btn-danger delete\" style=\"padding:0 12px;\" name=\""+news.topicbookid+"\" id="+<%=id%>+"><i class=\"layui-icon \">&#xe640;</i></a></td></tr>");
          
       }  
       });
       return false;
      });
      	$(".delete").live("click",function () {
        var th =$(this);
	  swal({   title: "确定删除管理员信息吗?",text: "删除后信息将彻底消失!!!",   type: "warning",   showCancelButton: true,   confirmButtonColor: "#DD6B55",   confirmButtonText: "确定删除",   closeOnConfirm: false }, function(){ 
      var args={"topicbookid":th.attr("name"),"id":th.attr("id"),time:new Date()};
      $.post("DeleteTopicBookServlet",args,function(data){
     
        th.parent().parent().empty();
        swal.close();
      }); 
      })
      return false;
            });
      $(".delete").on("click",function(){
    
        var th =$(this);
	  swal({   title: "确定删除管理员信息吗?",text: "删除后信息将彻底消失!!!",   type: "warning",   showCancelButton: true,   confirmButtonColor: "#DD6B55",   confirmButtonText: "确定删除",   closeOnConfirm: false }, function(){ 
      var args={"topicbookid":th.attr("name"),"id":th.attr("id"),time:new Date()};
      $.post("DeleteTopicBookServlet",args,function(data){
     
        th.parent().parent().empty();
        swal.close();
      }); 
      
      
      });
    return false;
	});
    layui.use('form', function(){
  var form = layui.form();
  
  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  });
});
layui.use('element', function(){
  var element = layui.element();
});
   $(".select").jSelect();   
 
    </script>  
  </body>
</html>
