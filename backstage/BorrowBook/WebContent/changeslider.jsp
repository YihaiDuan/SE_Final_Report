<%@ page language="java" import="java.util.*,hfhServlet.*,hfhdao.*,entity.*,Extenddao.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'changeslider.jsp' starting page</title>
    
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
input[type="checkbox"]{
display:none;
}
.layui-form{
    display:block;
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
	min-width:263px;
	
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
</style>
  </head>
  
  <body>
   
<div id="content" style="padding-top:20px;">
	  <div id="content-header1">
      <h1 style="font-weight:bold;">修改轮播</h1>
      </div>
      <%
          SlideDao slidedao=new SlideDao();
         Slide slide = slidedao.getSlide(Integer.parseInt(request.getParameter("id")));
         
       %>
      <form class="layui-form" action="ChangesSlideServlet" style="margin-top:10px;"  enctype="multipart/form-data" method="post">
    	 <table style="width:500px;margin:0 auto;font-size:15px;" class="tatb">
    	 	<tr style="padding-bottom:10px;">
    	 		<td>备注</td>
    	 		<td><input type="text" style="height:27px;line-height:27px;width:263px;margin-top:10px;" name="describ" value="<%=slide.getDescrib()%>"></td>
    	 	</tr>
    	 	<tr>
    	 		<td>类型</td>
    	 		<td >
      
      <div class="layui-form-item">
      <select lay-verify="required" style="height:27px;display:none;width:263px;" name="type">
        <option value="0">北京</option>
        <option value="1">上海</option>
        <option value="2">广州</option>
      </select>
  </div>

    	 		</td>
    	 	</tr>
    	 	<tr>
    	 		<td>图片</td>
    	 		<td><input type="file" style="height:27px;line-height:27px;width:263px;margin-top:10px;" name="file"></td>
    	 	</tr>
    	 	<tr>
    	 		<td>跳转地址</td>
    	 		<td><input type="text" style="height:27px;line-height:27px;width:263px;margin-top:10px;" name="url" value="<%=slide.getUrl()%>"></td>
    	 		
    	 	</tr>
    	 	<tr>
    	 		<td>是否显示</td>
    	 		<td>
    	 <div class=" layui-form layui-form-item" style="margin-bottom:0;">
        <input type="checkbox" name="showstatus" title="显示" style="height:28px;line-height:26px;" 
        <%
         if(slide.getShowstatus()==1)
         {
         %>
        checked="checked"
        <%
        }
         %>
        >
       </div>
    	 		</td>
    	 	</tr>
    	 	<tr>
    	 		<td colspan="2" style="padding-left:94px;">
    	 			<input type="submit" class="layui-btn layui-btn-small layui-btn-danger" value="确定" style="width:120px;">
    	 			<input type="button" class="layui-btn layui-btn-small layui-btn-danger" value="取消" style="width:120px;">
    	 		</td>
    	 	</tr>
    	 </table>
    	 <input type="hidden" name="id" value="<%=slide.getId()%>">
    	 </form>
    </div>

<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/maruti.html"></script>
<script src="layui/layui.js"></script>
<script>
var type=<%=slide.getType()%>;
  var str = $("option").map(function(){return $(this).val();}).get().join(", ");
  var arr=str.split(",");
  for(var i=0;i<arr.length;i++)
  {
     if(arr[i]==type)
     {
     $("option:eq("+i+")").attr("selected","selected");
     }
  
  }
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
