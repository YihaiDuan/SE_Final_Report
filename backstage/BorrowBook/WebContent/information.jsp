<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'information.jsp' starting page</title>
    
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
.tb tr td{
	height:40px;
	
	
}
</style>
  </head>
  
  <body>
   
<div id="content" style="padding-top:20px;">
    <div id="content-header1">
      <h1 style="font-weight:bold;">推荐图书</h1>
    </div>
    <div>
    	<div class="span6 offset1" style="border:1px solid #ccc;box-sizing:border-box;padding:20px 30px;">
        <table width="400" class="tb">
        	<tr>
        		<td>时间：</td>
        		<td>2017-07-08</td>
        	</tr>
        	<tr>
        		<td>用户：</td>
        		<td>张三</td>
        	</tr>
        	<tr>
        		<td>书名：</td>
        		<td>钢铁是怎样炼成的</td>
        	</tr>
        	<tr>
        		<td>作者：</td>
        		<td>奥斯托洛夫斯基</td>
        	</tr>
        	<tr>
        		<td>出版社：</td>
        		<td></td>
        	</tr>
        	<tr>
        		<td>推荐理由：</td>
        		<td></td>
        	</tr>
        	<tr>
        		<td colspan="2">
        				 <div class=" layui-form layui-form-item" style="margin-bottom:0;">
                      <input type="checkbox" name="" title="同意" style="height:28px;line-height:26px;" checked="checked">
                 </div>	
        		</td>
        	</tr>
        </table>
    	</div>
    	<div class="span6" style="border:1px solid #ccc;box-sizing:border-box;padding:20px 30px;">
        <table width="400" class="tb">
        	<tr>
        		<td>时间：</td>
        		<td>2017-07-08</td>
        	</tr>
        	<tr>
        		<td>用户：</td>
        		<td>张三</td>
        	</tr>
        	<tr>
        		<td>书名：</td>
        		<td>钢铁是怎样炼成的</td>
        	</tr>
        	<tr>
        		<td>作者：</td>
        		<td>奥斯托洛夫斯基</td>
        	</tr>
        	<tr>
        		<td>出版社：</td>
        		<td></td>
        	</tr>
        	<tr>
        		<td>推荐理由：</td>
        		<td></td>
        	</tr>
        	<tr>
        		<td colspan="2">
        				 <div class=" layui-form layui-form-item" style="margin-bottom:0;">
                      <input type="checkbox" name="" title="同意" style="height:28px;line-height:26px;" checked="checked">
                 </div>	
        		</td>
        	</tr>
        </table>
    	</div>
    </div>
</div>
<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/maruti.html"></script>
<script src="layui/layui.js"></script>
<script>
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
