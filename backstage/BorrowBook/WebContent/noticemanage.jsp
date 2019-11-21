<%@ page language="java" import="java.util.*,hfhdao.*,entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'noticemanage.jsp' starting page</title>
    
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
<link rel="stylesheet" href="css/matrix-style2.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="layui/css/layui.css" />
<link rel="stylesheet" href="layui/css/modules/layer/default/layer.css" />
<link rel="stylesheet" href="layui/css/layui.css" />
 <link rel="stylesheet" href="dist/sweetalert.css">
<style>
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
	min-width:263px;
	
}
.layui-form-item{
	margin-bottom:0;
}
</style>
  </head>
  
  <body>
  
<div id="content" style="padding-top:20px;">
  <div class="layui-tab layui-tab-card">
  <ul class="layui-tab-title">
    <li class="layui-this">网站设置</li>
    <li>用户管理</li>
  </ul>
  <div class="layui-tab-content" >
   <div class="layui-tab-item  layui-show">
      	<div style="padding:0 20px;">
			<table class="layui-table">
				<colgroup>
					<col width="280">
					<col width="650">
					<col width="100">
					<col width="140">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th>发布时间</th>
						<th>内容</th>
						<th>发布人群</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<%
				    NoticeDao noticedao =new NoticeDao();
				    List<Notice> list = noticedao.getListNotice();
				    if(list != null && list.size() >= 0)
				    {
				        for(int i=0;i<list.size();i++)
				        {
				         Notice notice = list.get(i);
				 %>
					<tr>
						<td><%=notice.getDate() %></td>
						<td><div
								style="width:550px;"><%=notice.getContent()%></div></td>
						<%
						 if(notice.getTypestatus()==0)
						 {
						 %>
						 <td>管理员</td>
						 <%
						  }else
						  {
						  %>
						  <td>用户</td>
						  <%
						  }
						   %>
						
						<td><a href="DeleteNoticeServlet?id=<%=notice.getId()%>"
							class="layui-btn layui-btn-mini  layui-btn-danger delete"
							style="padding:0 12px;"><i class="layui-icon ">&#xe640;</i></a></td>
					</tr>
					<%
					  }
					 }
					 %>
				</tbody>
			</table>
   
   </div>
   </div>
    <div class="layui-tab-item">
	<div id="content-header1">
      <h1 style="font-weight:bold;">发布公告</h1>
    </div>
    <form action="AddNoticeServlet" method="post">
	 <table style="width:500px;margin:0 auto;font-size:12px;" class="tatb">
    	 	<tr style="padding-bottom:10px;">
    	 		<td>活动类型</td>
    	 		<td> 
    	 <div class=" layui-form layui-form-item" style="margin-bottom:0;display:inline-block;margin-right:20px;">
        <input type="checkbox" name="type" title="管理员" style="height:28px;line-height:26px;" value="manage" checked="checked" class="type">
                </div>
    	 		 <div class=" layui-form layui-form-item" style="margin-bottom:0;display:inline-block;">
        <input type="checkbox" name="type" title="用户" style="height:28px;line-height:26px;" value="user" class="type">
                </div>
    	 		</td>
    	 	</tr>
    	 	<tr style="margin-top:50px;">
    	 		<td>内容</td>
    	 		<td >
    	 			<textarea style="width:330px;height:80px;" name="content"></textarea>
    	 		</td>
    	 	</tr>
    	
    
    	 	 <tr style="margin-top:50px;">
    	 		<td colspan="2" style="padding-left:65px;margin-left:100px;">
    	 			<input type="submit" class="layui-btn layui-btn-small layui-btn-danger" value="确定" style="width:120px;">
    	 			
    	 		</td>
    	 	</tr>
    	 </table>
         </form>
</div>


</div>
</div>
<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></scrip1t> 
<script src="js/bootstrap.min.js"></script> 
<script src="layui/layui.js"></script>
<script src="js/maruti.html"></script>
<script src="layui/lay/modules/layer.js"></script>
<script src="layui/layui.js"></script>
<script src="dist/sweetalert.min.js"></script>
<script src="dist/sweetalert-dev.js"></script>
<script>
      $('.delete').click(function(){
	  var href=this.href;
	  swal({   title: "确定删除这本书吗?",   text: "删除后信息将彻底消失!!!",   type: "warning",   showCancelButton: true,   confirmButtonColor: "#DD6B55",   confirmButtonText: "确定删除",   closeOnConfirm: false }, function(){ 
   window.location.href=href; 
	  
	});
	return false;
	});
	$(".title").click(function(){
		layui.use('layer', function(){
  var layer = layui.layer;
layer.open({
  type: 1,
  skin: 'layui-layer-rim', //加上边框
  area: ['420px', '240px'], //宽高
  content: 'html内容'
});   
});	
});
	$(".content").click(function(){
		layui.use('layer', function(){
  var layer = layui.layer;
layer.open({
  type: 1,
  skin: 'layui-layer-rim', //加上边框
  area: ['420px', '240px'], //宽高
  content: 'html内容'
});   
});	
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


</script>

  </body>
</html>
