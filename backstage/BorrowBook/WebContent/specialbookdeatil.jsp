<%@ page language="java" import="java.util.*,entity.*,Extenddao.*,newServlet.*,hfhdao.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'specialbookdeatil.jsp' starting page</title>
    
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
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style: none;
}

body {
	font-size: 12px;
	overflow-y: scroll;
}

/*.form {
				width:100px; margin:10px 0;padding:5px;
			}*/
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
    		font-size:22px;
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
    		
    	}
    	#addmanage{
    		color:#46B8DA;
    		cursor: pointer;
    		
    		
    	}
    	#addmanage:hover{
    		color:#23527C;
    		
    	}
    	 	.da
        {
      float:left;
      margin-top:20px;
      padding:4px 7px 0 20%;
      font-size:13px;
      	font-family:微软雅黑
        }
        label{
        display:inline-block;
        }
        div.uploader {
    position: relative;
    overflow: hidden;
    cursor: default;
    background: #DFECFA;
}
        div.uploader {
    width: 184px;
    cursor: pointer;
}
        div.uploader {
    background-position: 0px -297px;
    height: 28px;
}
div.uploader span.action {
   width: 179px;
    text-align: center;
    text-shadow: #fff 0px 1px 0px;
    font-size: 11px;
    font-weight: bold;
}
 div.uploader span.filename {
    padding:0;
    color: #777;
    width: 0px;
    border-right: solid 1px #bbb;
    font-size: 11px;
}
div.uploader span.action {
    background-color:#DFECFA;
}
        
</style>
  </head>
  
  <body>
     <div id="content">
  <div id="content-header1">
      <h1 style="font-weight:bold;">主题图书列表</h1>
  </div>
  <%
   int id = Integer.parseInt(request.getParameter("id"));     
   %>
   <div class="row">
               <a href="topicdetail.jsp?id=<%=id %>" class="layui-btn layui-btn-radius" style="letter-spacing:2px;height:32px;line-height:32px;margin-left:45px;">返回前页</a> 
              
           </div> 
		<div style="padding:0 20px;">
				<table class="layui-table" >
  <colgroup>
    <col width="120">
    <col width="160">
    <col width="180">
    <col width="120">
  
  </colgroup>
  <thead>
    <tr>
      <th>索书号</th>
      <th>书名</th>
      <th>作者</th>
      <th>藏书量</th>
    </tr> 
  </thead>
				<tbody>
				                   
                  <% 
                     
                    TopicBookDao topicdao = new TopicBookDao();
                    List<TopicBook> list = topicdao.getListTopicBook(id);
                    if(list!=null && list.size()>=0)
                    {
                       for(int i=0 ; i<list.size(); i++)
                       {
                           TopicBook topicbook = list.get(i);
                           BookDao bookdao = new BookDao();
                           Book book = bookdao.getIdBook(String.valueOf(topicbook.getBook().getBookid()));     
                  %>
					<tr >
						<td><div
								style="width:120px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getBookid()%></div></td>
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getBooktitle()%></div></td>
						<td><div
								style="width:160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"><%=book.getAuthor()%></div></td>
					
							<td><%=book.getBooknum() %></td>
					
					</tr>
					<%
					
					}
					}
					 %>
				</tbody>
			</table>
		</div>

    <script src="js/jquery.min.js"></script>
	<script src="js/jquery.ui.custom.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.uniform.js"></script>
	<script src="js/select2.min.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script src="js/matrix.js"></script>
	<script src="js/matrix.form_validation.js"></script>
	<script src="layui/layui.js"></script>
	<script src="src/jquery.easydropdown.js"></script>
	<script src="js/nicescroll/jquery-nicescroll.js"></script>
	<script src="js/jquery-jSelect.min.js"></script>
	<script src="dist/sweetalert.min.js"></script>
    <script src="dist/sweetalert-dev.js"></script>
    <script src="js/jquery-jSelect.min.js" ></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
     <script type="text/javascript">
     var u;
        $(".add").click(function(){
         $("#id").val($(this).parent().parent().eq(0).attr("name"));
         $("#p").val($(this).parent().parent().eq(0).attr("pass"));
          $("#u").val($(this).parent().parent().children().children().eq(0).text());
          var age=$(this).parent().parent().children().children().eq(1).text();
          if(age=="女")
          {
           $("#man").attr('checked','true');
          }else{
           $("#woman").attr('checked','true');
          }
          $("#phone").val($(this).parent().parent().children().children().eq(2).text()); 
          
        /*   var args={"id":$(this).attr("name"),"time": new Date()};
          $.post("UpdateServlet",args,function(data){
            news=eval("("+data+")");
            id=news.id;
            u=news.username;
            age=news.age;
            phone=news.phonenumber;
          });
         $("#u").val(u);
          */
         $("#prompt").show();
         return false;
        });
        $("#chahao").click(function(){
        $("#prompt").hide();
        });
  $(".select").jSelect();
  $("#city_select").css("width","80px");
  $('.delete').click(function(){
	  var href=this.href;
	  swal({   title: "确定删除管理员信息吗?",   text: "删除后信息将彻底消失!!!",   type: "warning",   showCancelButton: true,   confirmButtonColor: "#DD6B55",   confirmButtonText: "确定删除",   closeOnConfirm: false }, function(){ 
      window.location.href=href;   
	});
	return false;
	});
       document.querySelector('.example4').onclick = function(){
           var pc=$(this).attr("name");
           var username=$(".username").val();
           var password=$(".password").val();
            var args={"username":username,"password":password,"pc":pc,"time": new Date()};
            $.post("addAdminServlet",args,function(data){    
                  if(data==1)
                  {  
                    	swal("上传成功!", "管理员信息已经上传!", "success");
                  }
                  else
                  {
                    swal("上传失败!", "管理员信息上传失败请重新上传!", "error");
                  }
                 });
           return false;
    
       };
     </script>
  </body>
</html>
