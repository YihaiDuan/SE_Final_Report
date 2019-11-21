<%@ page language="java" import="java.util.*,hfhServlet.*,hfhdao.*,entity.*,Extenddao.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'bookmanagement.jsp' starting page</title>

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
<!--  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">   -->
	<!-- <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
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
	margin-left: 360px;
	border-right: 0;
}

.j_select .current_select .select_icon {
	top: 9px;
}
  	#prompt,#smallclass{
    	  display:none;
    	  position:fixed;
    	  top:90px;
    	  left:50%;
    	  margin-left:-200px;	
          width:380px;
          background:#dfecfa;
          z-index:100;
          padding:20px;
          color:#999;
    	}
    	#chahao,#chahao1{
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
      padding:4px 7px 0 32%;
      margin-right:6px;
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
    background: #EEEEEE;
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
    background-color:#EEEEEE;
}
.layui-btn-danger {
    background-color: #FF5722;
    color:#fff;
}
a:hover, a:focus {
    text-decoration: none;
    color:#fff;
}  
/* .layui-btn-primary:hover {
    border-color: #005580;
    color: #000;
}    */  
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
.j_select .current_select {
    margin: 0;
    padding: 0;
    height: 32px;
    line-height: 32px;
    text-indent: 1em;
    border: 1px solid #ccc;
        border-right-width: 1px;
        border-right-style: solid;
        border-right-color: rgb(204, 204, 204);
    box-shadow: 1px 1px 2px #ccc;
    outline: none;
    border-right: 0;
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
  	width:100px;
  }
  .layui-form-select .layui-edge {
    position: absolute;
    /* right: 160px; */
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
	min-width:100px;
	
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
    width: 100px;
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
      .uploader{
       margin-top:-10px;
       margin-left:5px;
       height:30px;
      }
      .Prompt{
        font-size:16px;
        width:200px;
        border:1px solid #999;
        padding:40px 80px;
        letter-spacing:3px;
        line-height:24x;
       text-indent:2em; 
       margin:0 auto;
     //  margin-top:20px;
     background:#666;
     color:#fff;
      }
      .action{
      display:none;
      }
</style>
</head>

<body>
	<div id="content">
	    <div id="content-header1" style="padding:20px 0">
		</div>
		<div class="row" style="padding-left:40px;">
		<div class="span4">
		<div class="prompt">
		  请按照规定的模板上传图片，可点击“下载模板”，选择要上传的文件。
		</div>
		</div>
		<div class="span8" style="align:center;">
		<a href="http://localhost：8080/BorrowBook/excel/book.xls" class="layui-btn" style="display:inline-block;padding:0px 80px;height:36px;line-height:36px;margin:20px 100px;">下载模板</a>
        <form action="BatchAddBookServlet" method="post" enctype="multipart/form-data" class="layui-form" id="form">
        <div style="height:36px;line-height:36px;margin:20px 100px;display:none;">
        <input type="file" name="excelname"  style="display:none;" id="file">
        </div>
        <input type="submit" class="layui-btn" value="选择表格" style="display:inline-block;padding:0px 80px;height:36px;line-height:36px;margin:20px 100px;" id="submit">
        </form>
        </div>
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
        <script scr="layui/lay/modules/laypage.js"></script> 
		<script type="text/javascript">
		  <%String action = (String)request.getParameter("action"); %>
	       var action='<%=action%>'; 
	       if(action=='success')
	       {
	        swal("上传成功!", "图书信息已经上传!", "success");
	        }
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
$("#file").change(function(){
	$("#submit").val("点击上传");

});
 var act = 0;
$("#submit").click(function(){
	if(act == 0)
   {
	   $("#file").click();
	   act=1;
		 
   }else{
	   $("#form").submit();
	   act=0;
   }
 

  return false;
});
	    </script>	
</body>
</html>
