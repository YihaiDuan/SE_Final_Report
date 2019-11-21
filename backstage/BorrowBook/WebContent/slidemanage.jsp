<%@ page language="java" import="java.util.*,hfhServlet.*,hfhdao.*,entity.*,Extenddao.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'slidemanage.jsp' starting page</title>
    
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
}
   	.pation{
  border-radius:4px;
 display:inline-block;
 
}
.pation li{
float:left
}
.pation li a{
    float:left;
    display:inline-block;
     padding: 6px 12px;
    line-height: 1.42857143;
    text-decoration: none;
    color: #428bca;
    background-color: #fff;
    border: 1px solid #ddd;
    margin-left: -1px;
    font-size:11px;
}
.pation li.active a{
background:#428bca;
color:#fff;

}
.pation li.active a:hover{
background:#428bca;
color:#fff;

}
.pation li a:hover{
   background-color: #eee;
   color:#000;
}
 	 	.da
        {
      float:left;
      margin-top:20px;
      padding:4px 7px 0 32%;
      margin-right:6px;
      font-size:13px;
 	font-family:微软雅黑
        }
        select{
        display:none;
        }
</style>
  </head>
  
  <body>
   <div id="content" style="padding-top:20px;">
	<div class="layui-tab layui-tab-card" style="margin:10px 20px;">
  <ul class="layui-tab-title">
    <li class="layui-this">轮播列表</li>
    <li>添加轮播</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
    	<div style="padding:0 20px;">
  	<table class="layui-table" >
  <colgroup>
    <col width="160">
    <col width="160">
    <col width="160">
    <col width="160">
    <col width="160">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>创建时间</th>
      <th>备注</th>
      <th>图片</th>
      <th>跳转地址</th>
      <th>显示</th>
      <th colspan="2" style="text-align: center;">操作</th>
    </tr> 
  </thead>
  <tbody>
  <%
    SlideDao slidedao = new SlideDao();
    List<Slide> list=slidedao.ShowSlideAll();
    if(list!=null && list.size()>=0)
    {
      for(int i=0;i<list.size();i++)
      {
      Slide slide=list.get(i);
   %>
    <tr name="<%=slide.getId()%>">
       <td>
          <%=slide.getDate()%>
       </td>
       <td>
          <%=slide.getDescrib() %>
       </td>
       <td>
          <img src="<%=slide.getImg()%>" class="slideimage"></img>
          <img src="<%=slide.getImg()%>" class="bigslideimage"></img>
       </td>
       <td>
         <%=slide.getUrl() %>
       </td>
       <td>
       	<div class=" layui-form layui-form-item" style="margin-bottom:0;">
        <input type="checkbox" name="" title="显示" style="height:28px;line-height:26px;" 
        <% if(slide.getShowstatus()==1)
           {
         %> 
           checked="checked";
         <% 
         }
         %>
        >
        </div>
       </td>
       <td style="text-align:center;">
       	<a href="changeslider.jsp?id=<%=slide.getId()%>" class="layui-btn layui-btn-mini layui-btn-normal" style="padding:0 12px;"><i class="layui-icon ">&#xe63c;</i></a>
       	</td>
       <td style="text-align:center;">	<a href="DeleteSlideServlet?id=<%=slide.getId() %>" class="layui-btn layui-btn-mini  layui-btn-danger delete" style="padding:0 12px;"><i class="layui-icon ">&#xe640;</i></a></td>
    </tr>
    <%
    }
    }
     %>
  </tbody>
</table>
 
  </div>	
    </div>
    <div class="layui-tab-item" >
     <form class="layui-form" action="AddSlideServlet" style="margin-top:10px;" enctype="multipart/form-data" method="post">
    	 <table style="width:500px;margin:0 auto;font-size:15px;" class="tatb">
    	 	<tr style="padding-bottom:10px;">
    	 		<td>备注</td>
    	 		<td><input type="text" style="height:27px;line-height:27px;width:263px;margin-top:10px;" name="describ"></td>
    	 	</tr>
    	 	<tr>
    	 		<td>类型</td>
    	 		<td >
       <div class="layui-form-item">
   
      <select name="type" lay-verify="required">
     
        <option value="0">专题</option>
        <option value="1">优惠劵</option>
        <option value="2">图书详情</option>
       
      </select>

  </div>

    	 		</td>
    	 	</tr>
    	 	<tr>
    	 		<td>图片</td>
    	 		<td><input type="file"  name="file" style="height:22px;line-height:22px;width:263px;margin-top:10px;font-size:12px;"></td>
    	 	</tr>
    	 	<tr>
    	 		<td>跳转地址</td>
    	 		<td><input type="text" style="height:27px;line-height:27px;width:263px;margin-top:10px;" name="url"></td>
    	 	</tr>
    	 	<tr>
    	 		<td>是否显示</td>
    	 		<td>
    	 <div class=" layui-form layui-form-item" style="margin-bottom:0;">
         <input type="checkbox" name="showstatus" title="显示" style="height:28px;line-height:26px;" checked="checked" value="1">
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
    	 </form>
    </div>
  </div>
</div>
 
</div>

<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/maruti.html"></script>
<script src="layui/layui.js"></script>
<script src="dist/sweetalert.min.js"></script>
<script src="dist/sweetalert-dev.js"></script>
<script>
$(".slideimage").mouseover(function(e){
    var xx = e.originalEvent.x || e.originalEvent.layerX || 0;
   var yy = e.originalEvent.y || e.originalEvent.layerY || 0;  
  $(this).next().show();
   $(this).next().css("top",yy);
   $(this).next().css("left",xx);
  
});
$(".slideimage").mouseout(function(){
  $(this).next().hide();
});
layui.use('form', function(){
  var form = layui.form();
  
  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));

  });
});
layui.use('element', function(){
  var element = layui.element();
});

           $("body").on("click",".layui-form-checkbox",function(){
	    	   var text= $(this).parent().parent().parent().eq(0).attr("name");
               var th=$(this);
               var args={"id":text,"time": new Date()};
              $.post("ChangeSlideServlet",args,function(data){ 
        	 
                    if(data==1)
                    {  
                    
                         if(th.attr("name")==true)
                         {
                          th.attr("name")==false;
                         }else{
                         
                         th.attr("name")==true;
                         }
                    }
              
                   });
			});
      $('.delete').click(function(){
	  var href=this.href;
	  swal({   title: "确定删除这条记录吗?",   text: "删除后信息将彻底消失!!!",   type: "warning",   showCancelButton: true,   confirmButtonColor: "#DD6B55",   confirmButtonText: "确定删除",   closeOnConfirm: false }, function(){ 
      window.location.href=href; 
	  
	});
	return false;
	});



</script>
  </body>
</html>
